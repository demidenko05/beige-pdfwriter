package org.beigesoft.ttf.service;

/*
 * Copyright (c) 2015-2017 Beigesoft ™
 *
 * Licensed under the GNU General Public License (GPL), Version 2.0
 * (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;

import org.beigesoft.log.ILogger;
import org.beigesoft.ttf.model.Glyph;
import org.beigesoft.ttf.model.TtfFont;
import org.beigesoft.ttf.model.TtfTableDirEntry;
import org.beigesoft.ttf.model.TableForEmbedding;
import org.beigesoft.pdf.exception.ExceptionPdfWr;

/**
 * <p>It makes Compact TTF font for embedding into PDF.</p>
 *
 * @author Yury Demidenko
 */
public class TtfCompactFontMaker implements ITtfCompactFontMaker {

  /**
   * <p>Logger.</p>
   **/
  private ILogger logger;

  /**
   * <p>App-scoped TTF fonts data map.</p>
   **/
  private Map<String, TtfFont> ttfFonts;

  /**
   * <p>App-scoped TTF fonts name to streamer method map.</p>
   **/
  private Map<String, ITtfSourceStreamer> ttfFontsStreamers;

  /**
   * <p>App-scoped TTF fonts name to path map.</p>
   **/
  private Map<String, String> ttfFontsPaths;

  //Debug log preferences:
  /**
   * <p>If show debug messages.</p>
   **/
  private boolean isShowDebugMessages;

  /**
   * <p>It makes compact TTF font for embedding into PDF.</p>
   * @param pAddParam additional params
   * @param pFontName Font Name (same as TTF file name without ".ttf")
   * @param pUsedCids used chars
   * @return result compact for for embedding
   * @throws Exception an Exception
   **/
  @Override
  public final byte[] make(final Map<String, Object> pAddParam,
    final String pFontName,
      final List<Character> pUsedCids) throws Exception {
    ITtfSourceStreamer ttfStreamer = this.ttfFontsStreamers.get(pFontName);
    TtfFont ttfFont = this.ttfFonts.get(pFontName);
    String fntPath = this.ttfFontsPaths.get(pFontName);
    if (ttfFont == null || ttfStreamer == null || fntPath == null) {
      throw new ExceptionPdfWr("There is no TTF font data for "
        + pFontName + "!");
    }
    if (ttfFont.getGlyf().getBufferInputStream() != null) {
      return makeCompact(pAddParam, ttfFont, null, pUsedCids);
    } else {
      this.logger.info(null, TtfCompactFontMaker.class,
        "Make input stream for font " + pFontName);
      TtfInputStream is = null;
      try {
        is = ttfStreamer.makeInputStream(fntPath);
        return makeCompact(pAddParam, ttfFont, is, pUsedCids);
      } finally {
        if (is != null) {
          is.close();
        }
      }
    }
  }

  /**
   * <p>It makes compact TTF font for embedding into PDF form input stream.
   * glyf, head, loca should be enough cause UNICODE to CID, widths etc
   * already written into PDF but many PDF viewers required hmtx.
   * Tables added:
   * cvt, fpgm, gasp, glyf, head, hhea, hmtx, loca, maxp, prep</p>
   * @param pAddParam additional params
   * @param pTtf Font data loaded previously
   * @param pIs input stream, maybe null if TtfGlyf.buffer != null
   * @param pUsedCids used chars
   * @return result compact for for embedding
   * @throws Exception an Exception
   **/
  public final byte[] makeCompact(final Map<String, Object> pAddParam,
    final TtfFont pTtf, final TtfInputStream pIs,
      final List<Character> pUsedCids) throws Exception {
    this.isShowDebugMessages = this.logger
      .getIsShowDebugMessagesFor(getClass());
    TtfOutputStream os = new TtfOutputStream(new ByteArrayOutputStream());
    os.writeUInt32(pTtf.getScalerType()); //uint32 scaler type
    int numTables = pTtf.getTablesForEmbedding().size(); //uint16 numTables
    os.write(0); os.write(numTables);
    //uint16 searchRange (maximum power of 2 <= numTables)*16:
    int mp2lent;
    //nt/mp2lent {2-3/2;4-7/4;8-15/8;16-31/16;32-63/32}
    if (numTables < 4) {
      mp2lent = 2;
    } else if (numTables < 8) {
      mp2lent = 4;
    } else if (numTables < 16) {
      mp2lent = 8;
    } else {
      mp2lent = 16;
    }
    int searchRange = mp2lent * 16;
    os.writeUInt16(searchRange);
    //uint16 entrySelector log2(maximum power of 2 <= numTables)
    int entrySelector = (int) (Math.log(mp2lent) / Math.log(2));
    os.writeUInt16(entrySelector);
    //uint16 rangeShift numTables*16-searchRange
    int rangeShift = numTables * 16 - searchRange;
    os.writeUInt16(rangeShift);
    //TDEs
    Long ofst = os.getSize() + (numTables * 16);
    List<TtfTableDirEntry> tdes = new ArrayList<TtfTableDirEntry>();
    for (TableForEmbedding tfe : pTtf.getTablesForEmbedding()) {
      TtfTableDirEntry tde = tfe.getTdeMaker().makeTde(os, tfe, ofst);
      tdes.add(tde);
      if (ofst != null && tfe.getIsLengthSame()) {
        ofst = tde.getOffset() + tde.getLength();
      } else {
        ofst = null;
      }
    }
    //Tables:
      //used glyphs and length holders:
    Map<Integer, Glyph> gls = new HashMap<Integer, Glyph>();
    long[] currLongChksum = new long[] {-1L, -1L, -1L, -1L};
    for (int i = 0; i < pTtf.getTablesForEmbedding().size(); i++) {
      @SuppressWarnings("rawtypes")
      TableForEmbedding tfe = pTtf
        .getTablesForEmbedding().get(i);
      tfe.getTableMaker().makeTable(pIs, os, tfe, tdes.get(i),
        currLongChksum, pUsedCids, gls);
      if (currLongChksum[0] != -1L) {
        throw new ExceptionPdfWr(
          "Algorithm 4-bytes align ckecksum error!!! Table "
            + tdes.get(i).getTagString());
      }
    }
    //fix TDEs:
    ByteArrayOutputStream baos = (ByteArrayOutputStream) os.getOutputStream();
    byte[] fntData = baos.toByteArray();
    for (TtfTableDirEntry tde : tdes) {
      fixTde(fntData, tde);
    }
    return fntData;
  }

  //Utils:
  /**
   * <p>Fix TDE by replacing UInt32 (offset/length/checksum)
   * in byte array.</p>
   * @param pArr byte array
   * @param pTde TDE
   **/
  public final void fixTde(final byte[] pArr, final TtfTableDirEntry pTde) {
    if (pTde.getChecksumIdx() != null) {
      replaceUInt32(pArr, pTde.getChecksumIdx(), pTde.getChecksum());
      if (this.isShowDebugMessages) {
        this.logger.debug(null, TtfCompactFontMaker.class,
          "Replaced checksum  tde/idx/value " + pTde.getTagString()
            + "/" + pTde.getChecksumIdx() + "/" + pTde.getChecksum());
      }
    }
    if (pTde.getOffsetIdx() != null) {
      replaceUInt32(pArr, pTde.getOffsetIdx(), pTde.getOffset());
      if (this.isShowDebugMessages) {
        this.logger.debug(null, TtfCompactFontMaker.class,
          "Replaced offset tde/idx/value " + pTde.getTagString()
             + "/" + pTde.getOffsetIdx() + "/" + pTde.getOffset());
      }
    }
    if (pTde.getLengthIdx() != null) {
      replaceUInt32(pArr, pTde.getLengthIdx(), pTde.getLength());
      if (this.isShowDebugMessages) {
        this.logger.debug(null, TtfCompactFontMaker.class,
          "Replaced length  tde/idx/value " + pTde.getTagString()
            + "/" + pTde.getLengthIdx() + "/" + pTde.getLength());
      }
    }
  }

  /**
   * <p>Replace UInt32 in byte array.</p>
   * @param pArr byte array
   * @param pIdx index
   * @param pData data
   **/
  public final void replaceUInt32(final byte[] pArr, final int pIdx,
    final long pData) {
    pArr[pIdx] = (byte) (pData >>> 24);
    pArr[pIdx + 1] = (byte) (pData >>> 16);
    pArr[pIdx + 2] = (byte) (pData >>> 8);
    pArr[pIdx + 3] = (byte) (pData);
  }

  /**
   * <p>Loads whole font from resource.</p>
   * @param pAddParam additional params
   * @param pFontName Font Name (same as TTF file name without ".ttf")
   * @param pUsedCids used chars
   * @return result compact for for embedding
   * @throws Exception an Exception
   **/
  public final byte[] loadWholeFromResource(final Map<String, Object> pAddParam,
    final String pFontName,
      final List<Character> pUsedCids) throws Exception {
    String fntFlNm = "/" + pFontName + ".ttf";
    URL url = TtfCompactFontMaker.class
      .getResource(fntFlNm);
    if (url != null) {
      this.logger.info(null, TtfCompactFontMaker.class,
        "Loading font " + pFontName);
      InputStream is = null;
      try {
        is = TtfCompactFontMaker.class.getResourceAsStream(fntFlNm);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int ibyte;
        while ((ibyte = is.read()) != -1) {
          os.write(ibyte);
        }
        return os.toByteArray();
      } finally {
        if (is != null) {
          is.close();
        }
      }
    } else {
      throw new ExceptionPdfWr("There is no file "
        + pFontName + "!");
    }
  }

  //SGS:
  /**
   * <p>Getter for logger.</p>
   * @return ILogger
   **/
  public final ILogger getLogger() {
    return this.logger;
  }

  /**
   * <p>Setter for logger.</p>
   * @param pLogger reference
   **/
  public final void setLogger(final ILogger pLogger) {
    this.logger = pLogger;
  }

  /**
   * <p>Getter for ttfFonts.</p>
   * @return Map<String, TtfFont>
   **/
  public final Map<String, TtfFont> getTtfFonts() {
    return this.ttfFonts;
  }

  /**
   * <p>Setter for ttfFonts.</p>
   * @param pTtfFonts reference
   **/
  public final void setTtfFonts(final Map<String, TtfFont> pTtfFonts) {
    this.ttfFonts = pTtfFonts;
  }

  /**
   * <p>Getter for ttfFontsStreamers.</p>
   * @return Map<String, ITtfSourceStreamer>
   **/
  public final Map<String, ITtfSourceStreamer> getTtfFontsStreamers() {
    return this.ttfFontsStreamers;
  }

  /**
   * <p>Setter for ttfFontsStreamers.</p>
   * @param pTtfFontsStreamers reference
   **/
  public final void setTtfFontsStreamers(
    final Map<String, ITtfSourceStreamer> pTtfFontsStreamers) {
    this.ttfFontsStreamers = pTtfFontsStreamers;
  }

  /**
   * <p>Getter for ttfFontsPaths.</p>
   * @return Map<String, String>
   **/
  public final Map<String, String> getTtfFontsPaths() {
    return this.ttfFontsPaths;
  }

  /**
   * <p>Setter for ttfFontsPaths.</p>
   * @param pTtfFontsPaths reference
   **/
  public final void setTtfFontsPaths(final Map<String, String> pTtfFontsPaths) {
    this.ttfFontsPaths = pTtfFontsPaths;
  }
}

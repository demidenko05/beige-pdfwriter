/*
BSD 2-Clause License

Copyright (c) 2019, Beigesoft™
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this
  list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.beigesoft.ttf.service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;

import org.beigesoft.log.ILog;
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
   * <p>Log.</p>
   **/
  private ILog log;

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
      this.log.info(null, TtfCompactFontMaker.class,
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
    TtfOutputStream os = new TtfOutputStream(new ByteArrayOutputStream());
    os.setLog(this.log);
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
    boolean dbgSh = getLog().getDbgSh(this.getClass(), 4150);
    if (pTde.getChecksumIdx() != null) {
      replaceUInt32(pArr, pTde.getChecksumIdx(), pTde.getChecksum());
      if (dbgSh) {
        this.log.debug(null, TtfCompactFontMaker.class,
          "Replaced checksum  tde/idx/value " + pTde.getTagString()
            + "/" + pTde.getChecksumIdx() + "/" + pTde.getChecksum());
      }
    }
    if (pTde.getOffsetIdx() != null) {
      replaceUInt32(pArr, pTde.getOffsetIdx(), pTde.getOffset());
      if (dbgSh) {
        this.log.debug(null, TtfCompactFontMaker.class,
          "Replaced offset tde/idx/value " + pTde.getTagString()
             + "/" + pTde.getOffsetIdx() + "/" + pTde.getOffset());
      }
    }
    if (pTde.getLengthIdx() != null) {
      replaceUInt32(pArr, pTde.getLengthIdx(), pTde.getLength());
      if (dbgSh) {
        this.log.debug(null, TtfCompactFontMaker.class,
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
      this.log.info(null, TtfCompactFontMaker.class,
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
   * <p>Getter for log.</p>
   * @return ILog
   **/
  public final ILog getLog() {
    return this.log;
  }

  /**
   * <p>Setter for log.</p>
   * @param pLog reference
   **/
  public final void setLog(final ILog pLog) {
    this.log = pLog;
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

package org.beigesoft.ttf.service;

/*
 * Copyright (c) 2017 Beigesoft ™
 *
 * Licensed under the GNU General Public License (GPL), Version 2.0
 * (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */

import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;

import org.beigesoft.log.ILogger;
import org.beigesoft.ttf.model.TtfFont;
import org.beigesoft.ttf.model.TtfTableDirEntry;
import org.beigesoft.ttf.model.TtfConstants;
import org.beigesoft.ttf.model.TtfHead;
import org.beigesoft.ttf.model.TtfHhea;
import org.beigesoft.ttf.model.TtfHmtx;
import org.beigesoft.ttf.model.TtfOs2;
import org.beigesoft.ttf.model.TtfCmap;
import org.beigesoft.ttf.model.TtfPost;
import org.beigesoft.ttf.model.TtfLoca;
import org.beigesoft.ttf.model.TtfGlyf;
import org.beigesoft.ttf.model.TtfMaxp;
import org.beigesoft.ttf.model.Glyph;
import org.beigesoft.ttf.model.CompoundGlyph;
import org.beigesoft.ttf.model.TableForEmbeddingBf;
import org.beigesoft.ttf.model.TableForEmbeddingHhea;
import org.beigesoft.ttf.model.TableForEmbeddingMaxp;
import org.beigesoft.ttf.model.TableForEmbeddingGlyf;
import org.beigesoft.ttf.model.TableForEmbeddingHmtx;
import org.beigesoft.ttf.model.TableForEmbeddingHead;
import org.beigesoft.ttf.model.CmpTableForEmbeddingTag;
import org.beigesoft.ttf.model.TableForEmbeddingLoca;
import org.beigesoft.ttf.model.TtfCmapSubtable;
import org.beigesoft.ttf.model.CmpTableDirEntryOffset;
import org.beigesoft.pdf.exception.ExceptionPdfWr;

/**
 * <p>Load TTF data from resource file or cache.
 * It's usually used a TTF font (for native language).
 * Any TTF loaded once. This data is used for embedding font into PDF
 * in compact mode (only used glyphs) and fast (checksums already computed).
 * This data actually must not be modified any farther.
 * Loading different TTF must not be parallel cause of using class variables
 * to resolve loading problems, i.e. if "glyf" table
 * consist of not 4bytes aligned glyths and loca is after glyf.
 * Use this loader trough PdfFactory. That factory ensure properly usage
 * of this loader, i.e. lazy initializing of TTF and other
 * multi-threading aspects. Chars widths in TtfHmtx are not scaled.
 * </p>
 *
 * @author Yury Demidenko
 */
public class TtfLoader implements ITtfLoader {

  /**
   * <p>Logger.</p>
   **/
  private ILogger logger;

  /**
   * <p>TTF constants.</p>
   **/
  private TtfConstants ttfConstants;

  /**
   * <p>TDE standard maker.</p>
   **/
  private TdeMaker tdeMaker;

  /**
   * <p>Table full copy maker.</p>
   **/
  private TableMakerFc tableMakerFc;

  /**
   * <p>Hhea maker.</p>
   **/
  private TableMakerHhea tableMakerHhea;

  /**
   * <p>Loca maker.</p>
   **/
  private TableMakerLoca tableMakerLoca;

  /**
   * <p>Maxp maker.</p>
   **/
  private TableMakerMaxp tableMakerMaxp;

  /**
   * <p>Hmtx maker.</p>
   **/
  private TableMakerHmtx tableMakerHmtx;

  /**
   * <p>Head maker.</p>
   **/
  private TableMakerHead tableMakerHead;

  /**
   * <p>Glyf maker.</p>
   **/
  private TableMakerGlyf tableMakerGlyf;

  /**
   * <p>Comparator tables for embedding.</p>
   **/
  private CmpTableForEmbeddingTag cmpTableForEmbeddingTag =
    new CmpTableForEmbeddingTag();

  //Debug log preferences:
  /**
   * <p>If show debug messages.</p>
   **/
  private boolean isShowDebugMessages;

  /**
   * <p>Logs detail level.</p>
   **/
  private int logDetailLevel;

  /**
   * <p>Debug GIDs list.</p>
   **/
  private Set<Integer> logGids;

  /**
   * <p>Debug GTI delta according GIDs list.</p>
   **/
  private int logGtiDelta = 0;

  /**
   * <p>Logs unicodes.</p>
   **/
  private Set<Character> logUnicodes;

  /**
   * <p>Glyph index in Glyf table at which occurred error.</p>
   **/
  private int wrongGti;

  /**
   * <p>If cache glyf table.</p>
   **/
  private boolean isCacheGlyf;

  /**
   * <p>Load TTF font from resource/file.</p>
   * @param pName font name
   * @param pPath path
   * @param pStreamer stream maker
   * @return loaded TTF font
   * @throws Exception an Exception
   **/
  @Override
  public final TtfFont loadFontTtf(final String pName, final String pPath,
    final ITtfSourceStreamer pStreamer) throws Exception {
    if (pStreamer.isExists(pPath)) {
      TtfFont ttf = new TtfFont();
      ttf.setFileName(pName);
      this.logger.info(null, TtfLoader.class, "Loading font " + pName);
      TtfInputStream is = null;
      try {
        is = pStreamer.makeInputStream(pPath);
        loadFontTtfFrom(ttf, is);
      } finally {
        if (is != null) {
          is.close();
        }
      }
      is = null;
      if (this.wrongGti != -1 || ttf.getHmtx() == null) {
        // this should never happen
        //glyf is not 4bytes aligned and loca is after glyph, so go 2-nd round:
        this.wrongGti = -1;
        try {
          ttf.getGlyf().getGlyphs().clear(); //clean trash
          ttf.getGlyf().getCompoundGlyphs().clear();
          if (this.isCacheGlyf) {
            ttf.getGlyf().getBufferInputStream().close();
          }
          if (ttf.getHmtx() == null || !this.isCacheGlyf) {
            is = pStreamer.makeInputStream(pPath);
          }
          TtfTableDirEntry hmtx = null;
          if (ttf.getHmtx() == null) { // not loaded without longHorMetrics
            for (TtfTableDirEntry tde : ttf.getTableDirectory()) {
              if (tde.getTagString()
              .equals(this.ttfConstants.getTagHmtx())) {
                hmtx = tde;
              }
            }
          }
          if (hmtx == null) {
            if (this.isCacheGlyf) {
              loadGlyfWithLoca(ttf, ttf.getGlyf().getBufferInputStream());
            } else {
              loadGlyfWithLoca(ttf, is);
            }
          } else if (this.wrongGti != -1) {
            if (hmtx.getOffset() > ttf.getGlyf()
              .getTableDirEntry().getOffset()) {
              if (this.isCacheGlyf) {
                loadGlyfWithLoca(ttf, ttf.getGlyf().getBufferInputStream());
              } else {
                loadGlyfWithLoca(ttf, is);
              }
              loadHmtx(ttf, hmtx, is);
            } else {
              loadHmtx(ttf, hmtx, is);
              if (this.isCacheGlyf) {
                loadGlyfWithLoca(ttf, ttf.getGlyf().getBufferInputStream());
              } else {
                loadGlyfWithLoca(ttf, is);
              }
            }
          }
        } finally {
          if (is != null) {
            is.close();
          }
        }
      }
      if (this.wrongGti != -1) {
        throw new ExceptionPdfWr("Can't load data from glyf!!!");
      }
      prepareAfterLoading(ttf);
      Collections.sort(ttf.getTablesForEmbedding(),
        this.cmpTableForEmbeddingTag);
      return ttf;
    } else {
      throw new ExceptionPdfWr("There is no file " + pPath + "!");
    }
  }

  /**
   * <p>Load TTF font from input stream.</p>
   * @param pTtf font
   * @param pIs input stream
   * @throws Exception an Exception
   **/
  public final void loadFontTtfFrom(final TtfFont pTtf,
    final TtfInputStream pIs) throws Exception {
    this.isShowDebugMessages = this.logger
      .getIsShowDebugMessagesFor(getClass());
    this.logDetailLevel = this.logger.getDetailLevel();
    this.tableMakerFc.setIsShowDebugMessages(this.isShowDebugMessages);
    this.tableMakerHhea.setIsShowDebugMessages(this.isShowDebugMessages);
    this.tableMakerLoca.setIsShowDebugMessages(this.isShowDebugMessages);
    this.tableMakerMaxp.setIsShowDebugMessages(this.isShowDebugMessages);
    this.tableMakerHmtx.setIsShowDebugMessages(this.isShowDebugMessages);
    this.tableMakerHead.setIsShowDebugMessages(this.isShowDebugMessages);
    this.tableMakerGlyf.setIsShowDebugMessages(this.isShowDebugMessages);
    this.tableMakerGlyf.setLogDetailLevel(this.logDetailLevel);
    this.wrongGti = -1;
    pTtf.setScalerType(pIs.readUInt32());
    if (!(pTtf.getScalerType() == this.ttfConstants.getScalerTypeMsw()
      || pTtf.getScalerType() == this.ttfConstants.getScalerTypeOsxIos()
      || pTtf.getScalerType() == this.ttfConstants.getScalerTypeOtto()
      || pTtf.getScalerType() == this.ttfConstants.getScalerTypeTyp1())) {
      this.logger.warn(null, TtfLoader.class, "Unsupported scaler type "
        + pTtf.getScalerType());
    }
    pTtf.setNumTables(pIs.readUInt16());
    pTtf.setSearchRange(pIs.readUInt16());
    pTtf.setEntrySelector(pIs.readUInt16());
    pTtf.setRangeShift(pIs.readUInt16());
    pTtf.setTableDirectory(new ArrayList<TtfTableDirEntry>());
    for (int i = 0; i < pTtf.getNumTables(); i++) {
      TtfTableDirEntry tde = new TtfTableDirEntry();
      tde.setTag(pIs.readTag());
      tde.setTagString(new String(tde.getTag(),
        this.ttfConstants.getCharset()));
      tde.setChecksum(pIs.readUInt32());
      tde.setOffset(pIs.readUInt32());
      tde.setLength(pIs.readUInt32());
      pTtf.getTableDirectory().add(tde);
      if (this.isShowDebugMessages) {
        this.logger.debug(null, TtfLoader.class,
          "Added TDE: TAG/Checksum/Offset/Length " + tde.getTagString()
            + "/" + tde.getChecksum() + "/" + tde.getOffset() + "/"
              + tde.getLength());
      }
    }
    CmpTableDirEntryOffset cmpTdeOffset = new CmpTableDirEntryOffset();
    Collections.sort(pTtf.getTableDirectory(), cmpTdeOffset);
    for (TtfTableDirEntry tde : pTtf.getTableDirectory()) {
      if (tde.getTagString().equals(this.ttfConstants.getTagCmap())) {
        loadCmap(pTtf, tde, pIs);
      } else if (tde.getTagString()
        .equals(this.ttfConstants.getTagHead())) {
        loadHead(pTtf, tde, pIs);
      } else if (tde.getTagString()
        .equals(this.ttfConstants.getTagMaxp())) {
        loadMaxp(pTtf, tde, pIs);
      } else if (tde.getTagString()
        .equals(this.ttfConstants.getTagPost())) {
        loadPost(pTtf, tde, pIs);
      } else if (tde.getTagString()
        .equals(this.ttfConstants.getTagFpgm())) {
        loadFpgm(pTtf, tde, pIs);
      } else if (tde.getTagString()
        .equals(this.ttfConstants.getTagGasp())) {
        loadGasp(pTtf, tde, pIs);
      } else if (tde.getTagString()
        .equals(this.ttfConstants.getTagPrep())) {
        loadPrep(pTtf, tde, pIs);
      } else if (tde.getTagString()
        .equals(this.ttfConstants.getTagCvt())) {
        loadCvt(pTtf, tde, pIs);
      } else if (tde.getTagString()
        .equals(this.ttfConstants.getTagHhea())) {
        loadHhea(pTtf, tde, pIs);
      } else if (tde.getTagString()
        .equals(this.ttfConstants.getTagOs2())) {
        loadOs2(pTtf, tde, pIs);
      } else if (tde.getTagString()
        .equals(this.ttfConstants.getTagLoca())) {
        loadLoca(pTtf, tde, pIs);
      } else if (tde.getTagString()
        .equals(this.ttfConstants.getTagGlyf())) {
        TtfGlyf glyf = new TtfGlyf(tde);
        glyf.setGlyphs(new ArrayList<Glyph>());
        glyf.setCompoundGlyphs(new ArrayList<CompoundGlyph>());
        pTtf.setGlyf(glyf);
        TableForEmbeddingGlyf tfe =
          new TableForEmbeddingGlyf(tde, pTtf);
        tfe.setTdeMaker(this.tdeMaker);
        tfe.setTableMaker(this.tableMakerGlyf);
        pTtf.getTablesForEmbedding().add(tfe);
        if (this.isCacheGlyf) {
          pIs.goAhead(tde.getOffset());
          byte[] buf = new byte[(int) tde.getLength()];
          pIs.read(buf);
          glyf.setBufferInputStream(
            new TtfBufferInputStream(buf, tde.getOffset()));
          if (this.isShowDebugMessages) {
            this.logger.debug(null, TtfLoader.class,
              "Glyf copied into buffer Length: "
                + glyf.getBufferInputStream().getBuffer().length);
          }
        }
        if (this.isCacheGlyf) {
          if (pTtf.getLoca() == null) {
            // the most used standard
            loadGlyf4Aligned(pTtf, glyf.getBufferInputStream());
          } else {
            loadGlyfWithLoca(pTtf, glyf.getBufferInputStream());
          }
        } else {
          if (pTtf.getLoca() == null) {
            // the most used standard
            loadGlyf4Aligned(pTtf, pIs);
          } else {
            loadGlyfWithLoca(pTtf, pIs);
          }
        }
      } else if (tde.getTagString()
        .equals(this.ttfConstants.getTagHmtx())) {
        loadHmtx(pTtf, tde, pIs);
      }
    }
  }

  /**
   * <p>Glyf loader with loca.</p>
   * @param pTtf font
   * @param pIs input stream
   * @throws Exception an Exception
   **/
  public final void loadGlyfWithLoca(final TtfFont pTtf,
      final ITtfInputStream pIs) throws Exception {
    TtfGlyf glyf = pTtf.getGlyf();
    int gidsTotal;
    if (pTtf.getMaxp() != null) {
      gidsTotal = pTtf.getMaxp().getNumGlyphs();
    } else if (pTtf.getLoca().getOffsets16() != null) {
      gidsTotal = pTtf.getLoca().getOffsets16().length - 1;
    } else {
      gidsTotal = pTtf.getLoca().getOffsets32().length - 1;
    }
    long ofst; //from begin of glyf
    long nextOfst;
    for (int gid = 0; gid < gidsTotal; gid++) {
      if (pTtf.getLoca().getOffsets16() != null) {
        ofst = pTtf.getLoca().getOffsets16()[gid];
        nextOfst = pTtf.getLoca().getOffsets16()[gid + 1];
      } else {
        ofst = pTtf.getLoca().getOffsets32()[gid];
        nextOfst = pTtf.getLoca().getOffsets32()[gid + 1];
      }
      if (ofst != nextOfst) { // if exist in glyf
        short numberOfContours = 12321;
        short xMin = 12321;
        short yMin = 12321;
        short xMax = 12321;
        Glyph glyph = null;
        CompoundGlyph cGlyph = null;
        try {
          // may be already at the start of glyph if there is no zero-padding:
          pIs.goAhead(glyf.getTableDirEntry().getOffset() + ofst);
          numberOfContours = pIs.readSInt16();
          if (numberOfContours < 0) { //compound
            cGlyph = new CompoundGlyph();
            cGlyph.setPartsGids(new HashSet<Character>());
            glyph = cGlyph;
            glyf.getCompoundGlyphs().add(cGlyph);
          } else {
            glyph = new Glyph();
            glyf.getGlyphs().add(glyph);
          }
          glyph.setOffset(ofst);
          glyph.setLength(nextOfst - ofst);
          glyph.setGid((char) gid);
          xMin = pIs.readFWord();
          yMin = pIs.readFWord();
          xMax = pIs.readFWord();
          glyph.setMaxY(pIs.readFWord());
          if (numberOfContours < 0) { //compound
            loadCompoundGlyph(cGlyph, pIs);
            if (this.isShowDebugMessages && 4 <= this.logDetailLevel
              && this.logGids != null && this.logGids.contains(gid)) {
              this.logger.debug(null, TtfLoader.class,
      "Added compound glyph, gid/parts size/xMin/yMin/xMax/yMax/offset/length: "
        + gid + "/" + cGlyph.getPartsGids().size() + "/" + xMin + "/" + yMin
          + "/" + xMax + "/" + cGlyph.getMaxY() + "/" + cGlyph.getOffset()
                + "/" + cGlyph.getLength());
            }
          } else { //simple
            // no need to read any more
            if (this.isShowDebugMessages && 4 <= this.logDetailLevel
              && this.logGids != null && this.logGids.contains(gid)) {
              this.logger.debug(null, TtfLoader.class,
              "Added simple glyph, gid/contours/xMin/yMin/xMax/offset/length: "
                + gid + "/" + numberOfContours + "/" + xMin + "/"
                  + yMin + "/" + xMax + "/" + glyph.getOffset()
                    + "/" + glyph.getLength());
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
          this.wrongGti = gid;
          this.logger.error(null, TtfLoader.class,
          "Error during process glyf from loca, wrong GID: " + gid);
          int start = Math.max(0, glyf.getGlyphs().size() - 6);
          for (int i = start; i < glyf.getGlyphs().size() - 1; i++) {
            this.logger.error(null, TtfLoader.class,
              "glyph, gid/yMax/offset/length: "
                + ((int) glyf.getGlyphs().get(i).getGid()) + "/"
                  + glyf.getGlyphs().get(i).getMaxY() + "/"
                    + glyf.getGlyphs().get(i).getOffset()
                      + "/" + glyf.getGlyphs().get(i).getLength());
          }
          if (glyph != null) {
            this.logger.error(null, TtfLoader.class,
              "Wrong glyph, gid/contours/xMin/yMin/xMax/yMax/offset/length: "
                + gid + "/" + numberOfContours + "/" + xMin + "/" + yMin + "/"
                  + xMax + "/" + glyph.getMaxY() + "/" + glyph.getOffset()
                    + "/" + glyph.getLength());
          }
          return;
        }
      }
    }
    if (this.isShowDebugMessages) {
      this.logger.debug(null, TtfLoader.class,
      "Added glyf with loca, total: " + glyf.getGlyphs().size());
    }
  }

  /**
   * <p>Glyf loader when loca is after glyf.
   * The most used standard, glyf data is 4bytes aligned.
   * NanumGothic has Œ glyph#18269 length 154 bytes (not 4bytes aligned)
   * but loca is placed before glyf.</p>
   * @param pTtf font
   * @param pIs input stream
   * @throws Exception an Exception
   **/
  public final void loadGlyf4Aligned(final TtfFont pTtf,
    final ITtfInputStream pIs) throws Exception {
    pIs.goAhead(pTtf.getGlyf().getTableDirEntry().getOffset());
    int gti = 0;
    while (pIs.getOffset() - pTtf.getGlyf().getTableDirEntry()
      .getOffset() < pTtf.getGlyf().getTableDirEntry().getLength()) {
      // offset from begin of the glyf
      long ofst = pIs.getOffset() - pTtf.getGlyf().getTableDirEntry()
        .getOffset();
      short numberOfContours = pIs.readSInt16();
      Glyph glyph;
      CompoundGlyph cGlyph = null;
      if (numberOfContours < 0) { //compound
        cGlyph = new CompoundGlyph();
        cGlyph.setPartsGids(new HashSet<Character>());
        pTtf.getGlyf().getCompoundGlyphs().add(cGlyph);
        glyph = cGlyph;
      } else {
        glyph = new Glyph();
        pTtf.getGlyf().getGlyphs().add(glyph);
      }
      glyph.setOffset(ofst);
      short xMin = pIs.readFWord();
      short yMin = pIs.readFWord();
      short xMax = pIs.readFWord();
      glyph.setMaxY(pIs.readFWord());
      try {
        if (numberOfContours < 0) { //compound
          loadCompoundGlyph(cGlyph, pIs);
        } else { //simple
          skipSimpleGlyph(glyph, pIs, numberOfContours, gti + this.logGtiDelta);
        }
        // padding zeros at the end of glyph for 4byte aligned
        int mod4 = (int) (pIs.getOffset() - pTtf.getGlyf().getTableDirEntry()
          .getOffset()) % 4;
        if (mod4 != 0) {
          pIs.readUInt8Arr(4 - mod4);
          if (this.isShowDebugMessages && 4 <= this.logDetailLevel
            && this.logGids != null
              && this.logGids.contains(gti)) {
            this.logger.debug(null, TtfLoader.class,
              "Added padding zeros/gti " + (4 - mod4) + "/" + gti);
          }
        }
        glyph.setLength(pIs.getOffset() - pTtf.getGlyf().getTableDirEntry()
          .getOffset() - glyph.getOffset());
        if (this.isShowDebugMessages && 4 <= this.logDetailLevel
          && this.logGids != null && this.logGids.contains(gti)) {
          if (cGlyph != null) {
            this.logger.debug(null, TtfLoader.class,
        "Added compound glyph, gti/parts size/xMin/yMin/xMax/offset/length: "
          + gti + "/" + cGlyph.getPartsGids().size() + "/" + xMin + "/" + yMin
            + "/" + xMax + "/" + cGlyph.getOffset() + "/" + cGlyph.getLength());
          } else {
            this.logger.debug(null, TtfLoader.class,
              "Added simple glyph, gti/contours/xMin/yMin/xMax/offset/length: "
                + gti + "/" + numberOfContours + "/" + xMin + "/"
                  + yMin + "/" + xMax + "/" + glyph.getOffset()
                    + "/" + glyph.getLength());
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
        this.wrongGti = gti;
        this.logger.error(null, TtfLoader.class,
        "Error during process glyf, wrongGti: " + this.wrongGti);
        int start = Math.max(0, gti + this.logGtiDelta);
        for (int i = start; i < pTtf.getGlyf().getGlyphs().size() - 1; i++) {
          this.logger.error(null, TtfLoader.class,
        "glyph, gti/yMax/offset/length: " + i + "/" + pTtf.getGlyf().getGlyphs()
        .get(i).getMaxY() + "/" + pTtf.getGlyf().getGlyphs().get(i).getOffset()
          + "/" + pTtf.getGlyf().getGlyphs().get(i).getLength());
        }
        this.logger.error(null, TtfLoader.class,
          "Wrong glyph, gti/contours/xMin/yMin/xMax/yMax/offset/length: "
            + gti + "/" + numberOfContours + "/" + xMin + "/"
              + yMin + "/" + xMax + "/" + glyph.getMaxY() + "/"
                + glyph.getOffset() + "/" + glyph.getLength());
        return;
      }
      gti++;
    }
    if (this.isShowDebugMessages) {
      this.logger.debug(null, TtfLoader.class,
      "Added glyf, total: " + gti);
    }
  }

  /**
   * <p>Skip simple glyph data (for 4-bytes aligned loader).</p>
   * @param pGlyph Glyph
   * @param pIs input stream
   * @param pNumContours Number of Contours
   * @param pGti index in glyf
   * @throws Exception an Exception
   **/
  public final void skipSimpleGlyph(final Glyph pGlyph,
    final ITtfInputStream pIs,
      final int pNumContours, final int pGti) throws Exception {
    int[] endPtsOfContours = pIs.readUInt16Arr(pNumContours);
    int totalPoints = endPtsOfContours[pNumContours - 1] + 1; //points#from 0
    if (pNumContours == 1 && totalPoints == 0xFFFF) {
      //they say someone marks empty glyph in that way
      return;
    }
    int instructionLength = pIs.readUInt16();
    //uint8 instructions[instructionLength]
    if (this.isShowDebugMessages && 4 <= this.logDetailLevel
      && this.logGids != null && this.logGids.contains(pGti)) {
      this.logger.debug(null, TtfLoader.class,
      "simple glyph gti + delta/numContours/points/delta/instrLen " + pGti + "/"
        + pNumContours + "/" + totalPoints + "/" + this.logGtiDelta
          + "/" + instructionLength);
    }
    pIs.skip(instructionLength);
    int[] flags = new int[totalPoints];
    for (int i = 0; i < totalPoints; i++) {
      flags[i] = pIs.readUInt8();
      if ((flags[i] & 0b1000) > 0) {
        int repeat = pIs.readUInt8();
        for (int j = 0; j < repeat; j++) {
          flags[i + j + 1] = flags[i];
        }
        i += repeat;
      }
    }
    for (int flag : flags) {
      //xCoordinates
      if ((flag & 0b10) == 0) { //2bytes
        if ((flag & 0b10000) == 0) { //not same
          pIs.readUInt16();
        }
      } else {
        pIs.readUInt8();
      }
    }
    for (int flag : flags) {
      //yCoordinates
      if ((flag & 0b100) == 0) { //2bytes
        if ((flag & 0b100000) == 0) { //not same
          pIs.readUInt16();
        }
      } else {
        pIs.readUInt8();
      }
    }
  }

  /**
   * <p>Load compound glyph.</p>
   * @param pGlyph Glyph
   * @param pIs input stream
   * @throws Exception an Exception
   **/
  public final void loadCompoundGlyph(final CompoundGlyph pGlyph,
      final ITtfInputStream pIs) throws Exception {
    int flags = 0;
    do {
      flags = pIs.readUInt16();
      int partGid = pIs.readUInt16();
      pGlyph.getPartsGids().add((char) partGid);
      boolean arg1Nad2AreWords = (flags & 0b1) != 0;
      if (arg1Nad2AreWords) {
        pIs.readUInt16(); //argument1
        pIs.readUInt16(); //argument2
      } else {
        pIs.readUInt8(); //argument1
        pIs.readUInt8(); //argument2
      }
      boolean weHaveScale = (flags & 0b1000) != 0;
      if (weHaveScale) {
        pIs.readUInt16(); //scale X Y
      } else {
        boolean weHaveAnXAndYScale = (flags & 0b1000000) != 0;
        if (weHaveAnXAndYScale) {
          pIs.readUInt16(); //scale X
          pIs.readUInt16(); //scale Y
        } else {
          boolean weHaveATwoByTo = (flags & 0b10000000) != 0;
          if (weHaveATwoByTo) {
            pIs.readUInt16(); //scale X
            pIs.readUInt16(); //scale01
            pIs.readUInt16(); //scale10
            pIs.readUInt16(); //scale Y
          }
        }
      }
    } while ((flags & 0b100000) != 0);
    if ((flags & 0b100000000) != 0) {
      int numInstr = pIs.readUInt16();
      pIs.skip(numInstr); //uint8 instr[numInstr]
    }
  }

  /**
   * <p>Prepare TTF after loading.</p>
   * @param pTtf font
   **/
  public final void prepareAfterLoading(final TtfFont pTtf) {
    Character gidH = null; //H
    Character gid1 = null; //1
    if (pTtf.getCmap() != null) {
      gidH = pTtf.getCmap().getUniToCid().get((char) 0x48); //H
      gid1 = pTtf.getCmap().getUniToCid().get((char) 0x31); //1
    }
    Glyph glyphH = null;
    Glyph glyph1 = null;
    int gCount;
    if (pTtf.getLoca().getOffsets16() != null) {
      gCount = pTtf.getLoca().getOffsets16().length - 1;
    } else {
      gCount = pTtf.getLoca().getOffsets32().length - 1;
    }
    if (pTtf.getMaxp() != null && pTtf.getMaxp().getNumGlyphs() != gCount) {
      this.logger.warn(null, TtfLoader.class,
        "loca size is wrong to maxp.numGlyphs!!! loca.length - 1/numGlyphs"
          + gCount + "/" + pTtf.getMaxp().getNumGlyphs());
    }
    boolean needToMakeGids = pTtf.getGlyf().getTableDirEntry().getOffset()
      < pTtf.getLoca().getTableDirEntry().getOffset();
    if (needToMakeGids) {
      for (CompoundGlyph gl : pTtf.getGlyf().getCompoundGlyphs()) {
        for (int gid = 0; gid < gCount; gid++) {
          long currGidOfst;
          long nextGidOfst;
          if (pTtf.getLoca().getOffsets16() != null) {
            currGidOfst = pTtf.getLoca().getOffsets16()[gid];
            nextGidOfst = pTtf.getLoca().getOffsets16()[gid + 1];
          } else {
            currGidOfst = pTtf.getLoca().getOffsets32()[gid];
            nextGidOfst = pTtf.getLoca().getOffsets32()[gid + 1];
          }
          if (currGidOfst == gl.getOffset()
            && nextGidOfst - currGidOfst == gl.getLength()) {
            gl.setGid((char) gid);
            break;
          }
        }
        if (this.isShowDebugMessages && 3 <= this.logDetailLevel
          && this.logGids != null && this.logGids.contains((int) gl.getGid())) {
          this.logger.debug(null, TtfLoader.class,
            "Prepared compound glyph : gid/offset/length " + ((int) gl.getGid())
              + "/" + gl.getOffset() + "/" + gl.getLength());
        }
      }
    }
    for (Glyph gl : pTtf.getGlyf().getGlyphs()) {
      if (needToMakeGids) {
        for (int gid = 0; gid < gCount; gid++) {
          long currGidOfst;
          long nextGidOfst;
          if (pTtf.getLoca().getOffsets16() != null) {
            currGidOfst = pTtf.getLoca().getOffsets16()[gid];
            nextGidOfst = pTtf.getLoca().getOffsets16()[gid + 1];
          } else {
            currGidOfst = pTtf.getLoca().getOffsets32()[gid];
            nextGidOfst = pTtf.getLoca().getOffsets32()[gid + 1];
          }
          if (currGidOfst == gl.getOffset()
            && nextGidOfst - currGidOfst == gl.getLength()) {
            gl.setGid((char) gid);
            break;
          }
        }
        if (this.isShowDebugMessages && 3 <= this.logDetailLevel
          && this.logGids != null && this.logGids.contains((int) gl.getGid())) {
          this.logger.debug(null, TtfLoader.class,
            "Prepared glyph : gid/offset/length " + ((int) gl.getGid())
              + "/" + gl.getOffset() + "/" + gl.getLength());
        }
      } else if (gid1 == null && gidH == null
        || glyph1 != null && glyphH != null) {
        break;
      }
      if (gid1 != null && gid1 == gl.getGid()) {
        glyph1 = gl;
      } else if (gidH != null && gidH == gl.getGid()) {
        glyphH = gl;
      }
    }
    if (pTtf.getOs2() != null && pTtf.getOs2().getSCapHeight() == 0
      && glyphH != null) {
      pTtf.getOs2().setSCapHeight(glyphH.getMaxY());
      if (this.isShowDebugMessages) {
        this.logger.debug(null, TtfLoader.class,
          "Caps height from H yMax = " + pTtf.getOs2().getSCapHeight());
      }
    }
    if (this.isShowDebugMessages && glyph1 != null) {
      this.logger.debug(null, TtfLoader.class,
        "glyph 1 : gid/offset/length " + ((int) glyph1.getGid())
          + "/" + glyph1.getOffset() + "/" + glyph1.getLength());
    }
    if (this.isShowDebugMessages) {
      this.logger.debug(null, TtfLoader.class,
        "It has been removed simple glyphs count "
          + pTtf.getGlyf().getGlyphs().size());
      pTtf.getGlyf().setGlyphs(null);
    }
  }

  /**
   * <p>Hmtx loader.</p>
   * @param pTtf font
   * @param pTde table entry
   * @param pIs input stream
   * @throws Exception an Exception
   **/
  public final void loadHmtx(final TtfFont pTtf,
    final TtfTableDirEntry pTde,
      final TtfInputStream pIs) throws Exception {
    if (pTtf.getHhea() != null) {
      TtfHmtx hmtx = new TtfHmtx(pTde);
      pTtf.setHmtx(hmtx);
      int widthsCount = pTtf.getHhea().getNumOfLongHorMetrics();
      pIs.goAhead(pTde.getOffset());
      hmtx.setWidths(new char[widthsCount]);
      hmtx.setLeftSideBearing(new short[widthsCount]);
      int lsbAddLen = (int) (pTde.getLength() - (widthsCount * 4)) / 2;
      if (lsbAddLen > 0) {
        hmtx.setLeftSideBearingAdd(new short[lsbAddLen]);
      }
      for (int i = 0; i < widthsCount; i++) {
        hmtx.getWidths()[i] = (char) pIs.readUInt16();
        hmtx.getLeftSideBearing()[i] = pIs.readSInt16();
      }
      for (int i = 0; i < lsbAddLen; i++) {
        hmtx.getLeftSideBearingAdd()[i] = pIs.readSInt16();
      }
      TableForEmbeddingHmtx tfe =
        new TableForEmbeddingHmtx(pTde, pTtf);
      tfe.setTdeMaker(this.tdeMaker);
      tfe.setTableMaker(this.tableMakerHmtx);
      pTtf.getTablesForEmbedding().add(tfe);
      if (this.isShowDebugMessages) {
        StringBuffer sb = new StringBuffer();
        sb.append("Added hmtx: size = " + widthsCount);
        if (this.logGids != null) {
          for (int i : this.logGids) {
            if (hmtx.getWidths().length > i) {
              int w = hmtx.getWidths()[i];
              sb.append(", g" + i + "=" + w);
            }
          }
        }
        sb.append("; lsbAddLen = " + lsbAddLen);
        this.logger.debug(null, TtfLoader.class, sb.toString());
      }
    } else {
      this.logger.warn(null, TtfLoader.class,
        "hmtx was not loaded without hhea.numOfLongHorMetrics!!!");
    }
  }

  /**
   * <p>Loca loader.</p>
   * @param pTtf font
   * @param pTde table entry
   * @param pIs input stream
   * @throws Exception an Exception
   **/
  public final void loadLoca(final TtfFont pTtf,
    final TtfTableDirEntry pTde,
      final TtfInputStream pIs) throws Exception {
    TtfLoca loca = new TtfLoca(pTde);
    pTtf.setLoca(loca);
    pIs.goAhead(pTde.getOffset());
    int count = -1;
    if (pTtf.getHead().getIndexToLocFormat() > 0) {
      //32bit
      count = (int) (pTde.getLength() / 4);
      loca.setOffsets32(new long[count]);
      for (int i = 0; i < count; i++) {
        loca.getOffsets32()[i] = pIs.readUInt32();
      }
    } else {
      //16bit
      count = (int) (pTde.getLength() / 2);
      loca.setOffsets16(new int[count]);
      boolean isOverflow = false;
      for (int i = 0; i < count; i++) {
        loca.getOffsets16()[i] = 2 * pIs.readUInt16();
        if (!isOverflow && i != 0
          && loca.getOffsets16()[i] < loca.getOffsets16()[i - 1]) {
          isOverflow = true;
        }
        if (isOverflow) { //old Liberation fonts
          loca.getOffsets16()[i] += 65536;
        }
      }
    }
    TableForEmbeddingLoca tfe =
      new TableForEmbeddingLoca(pTde, loca);
    tfe.setTdeMaker(this.tdeMaker);
    tfe.setTableMaker(this.tableMakerLoca);
    pTtf.getTablesForEmbedding().add(tfe);
    if (this.isShowDebugMessages) {
      StringBuffer sb = new StringBuffer();
      sb.append("Added loca ");
      if (loca.getOffsets16() == null) {
        sb.append("32");
      } else {
        sb.append("16");
      }
      sb.append(" bit, size = " + count);
      if (this.logGids != null) {
        for (int i : this.logGids) {
          int gi = -1;
          if (loca.getOffsets16() == null) {
            if (loca.getOffsets32().length > i) {
              gi = (int) loca.getOffsets32()[i];
            }
          } else {
            if (loca.getOffsets16().length > i) {
              gi = loca.getOffsets16()[i];
            }
          }
          if (gi != -1) {
            sb.append(", g" + i + "=" + gi);
          }
        }
      }
      this.logger.debug(null, TtfLoader.class, sb.toString());
    }
  }

  /**
   * <p>Post loader.</p>
   * @param pTtf font
   * @param pTde table entry
   * @param pIs input stream
   * @throws Exception an Exception
   **/
  public final void loadPost(final TtfFont pTtf,
    final TtfTableDirEntry pTde,
      final TtfInputStream pIs) throws Exception {
    TtfPost post = new TtfPost(pTde);
    pTtf.setPost(post);
    pIs.goAhead(pTde.getOffset());
    pIs.readFixed(); //format
    post.setItalicAngle(pIs.readFixed());
    pIs.readFWord(); //underlinePosition
    pIs.readFWord(); //underlineThickness
    long isFixedPitch = pIs.readUInt32();
    post.setIsFixedPitch(isFixedPitch > 0);
    if (this.isShowDebugMessages) {
      this.logger.debug(null, TtfLoader.class,
        "Added post: ItalicAngle/isFixedPitch "
          + post.getItalicAngle() + "/"  + isFixedPitch);
    }
  }

  /**
   * <p>Head loader.</p>
   * @param pTtf font
   * @param pTde table entry
   * @param pIs input stream
   * @throws Exception an Exception
   **/
  public final void loadHead(final TtfFont pTtf,
    final TtfTableDirEntry pTde,
      final TtfInputStream pIs) throws Exception {
    TtfHead head = new TtfHead(pTde);
    pTtf.setHead(head);
    pIs.goAhead(pTde.getOffset());
    //version, fontRevision
    //checksumAdjustment, magicNumber, flags:
    pIs.read(head.getHeadByteArr());
    head.setUnitsPerEm(pIs.readUInt16());
    pIs.read(head.getCreatedModifiedBuf()); //created, modified
    head.setXMin(pIs.readFWord());
    head.setYMin(pIs.readFWord());
    head.setXMax(pIs.readFWord());
    head.setYMax(pIs.readFWord());
    //macStyle, lowestRecPPEM, fontDirectionHint:
    pIs.read(head.getMsLrpFdhBuf());
    head.setIndexToLocFormat(pIs.readSInt16());
    pIs.read(head.getGlyphDataFormatBuf());
    TableForEmbeddingHead tfe =
      new TableForEmbeddingHead(pTde, head);
    tfe.setTdeMaker(this.tdeMaker);
    tfe.setTableMaker(this.tableMakerHead);
    pTtf.getTablesForEmbedding().add(tfe);
    if (this.isShowDebugMessages) {
      this.logger.debug(null, TtfLoader.class,
        "Added head: UnitsPerEm/XMin/YMin/XMax/YMax/loca "
          + head.getUnitsPerEm() + "/"  + head.getXMin() + "/"
            + head.getYMin() + "/"  + head.getXMax() + "/"  + head.getYMax()
              + "/"  + head.getIndexToLocFormat());
    }
  }

  /**
   * <p>Maxp loader.</p>
   * @param pTtf font
   * @param pTde table entry
   * @param pIs input stream
   * @throws Exception an Exception
   **/
  public final void loadMaxp(final TtfFont pTtf,
    final TtfTableDirEntry pTde,
      final TtfInputStream pIs) throws Exception {
    TtfMaxp maxp = new TtfMaxp(pTde);
    pTtf.setMaxp(maxp);
    pIs.goAhead(pTde.getOffset());
    pIs.read(maxp.getVersion()); //version Fixed
    maxp.setNumGlyphs(pIs.readUInt16());
    byte[] tail = new byte[(int) pTde.getLength() - 6];
    maxp.setTail(tail);
    pIs.read(maxp.getTail());
    TableForEmbeddingMaxp tfe =
      new TableForEmbeddingMaxp(pTde, maxp);
    tfe.setTdeMaker(this.tdeMaker);
    tfe.setTableMaker(this.tableMakerMaxp);
    pTtf.getTablesForEmbedding().add(tfe);
    if (this.isShowDebugMessages) {
      this.logger.debug(null, TtfLoader.class,
        "Added maxp: numGlyphs= " + maxp.getNumGlyphs());
    }
  }

  /**
   * <p>Hhea loader.</p>
   * @param pTtf font
   * @param pTde table entry
   * @param pIs input stream
   * @throws Exception an Exception
   **/
  public final void loadHhea(final TtfFont pTtf,
    final TtfTableDirEntry pTde,
      final TtfInputStream pIs) throws Exception {
    TtfHhea hhea = new TtfHhea(pTde);
    pTtf.setHhea(hhea);
    pIs.goAhead(pTde.getOffset());
    pIs.read(hhea.getVersion()); //version Fixed
    hhea.setAscent(pIs.readFWord());
    hhea.setDescent(pIs.readFWord());
    hhea.setLineGap(pIs.readFWord());
    hhea.setAdvanceWidthMax(pIs.readUFWord());
    hhea.setMinLeftSideBearing(pIs.readFWord());
    hhea.setMinRightSideBearing(pIs.readFWord());
    hhea.setXMaxExtent(pIs.readFWord());
    hhea.setCaretSlopeRise(pIs.readSInt16());
    hhea.setCaretSlopeRun(pIs.readSInt16());
    hhea.setCaretOffset(pIs.readFWord());
    pIs.read(hhea.getReserved()); //reserved1,2,3,4 sint16
    hhea.setMetricDataFormat(pIs.readFWord());
    hhea.setNumOfLongHorMetrics(pIs.readUInt16());
    TableForEmbeddingHhea tfe =
      new TableForEmbeddingHhea(pTde, hhea);
    tfe.setTdeMaker(this.tdeMaker);
    tfe.setTableMaker(this.tableMakerHhea);
    pTtf.getTablesForEmbedding().add(tfe);
    if (this.isShowDebugMessages) {
      this.logger.debug(null, TtfLoader.class,
        "Added hhea: Ascent/Descent/NumOfLongHorMetrics "
          + hhea.getAscent() + "/"  + hhea.getDescent() + "/"
            + hhea.getNumOfLongHorMetrics());
    }
  }

  /**
   * <p>Fpgm loader.</p>
   * @param pTtf font
   * @param pTde table entry
   * @param pIs input stream
   * @throws Exception an Exception
   **/
  public final void loadFpgm(final TtfFont pTtf,
    final TtfTableDirEntry pTde,
      final TtfInputStream pIs) throws Exception {
    pIs.goAhead(pTde.getOffset());
    byte[] buf = new byte[(int) pTde.getLength()];
    pIs.read(buf);
    TableForEmbeddingBf tfebf =
      new TableForEmbeddingBf(pTde, true, true, buf);
    tfebf.setTdeMaker(this.tdeMaker);
    tfebf.setTableMaker(this.tableMakerFc);
    pTtf.getTablesForEmbedding().add(tfebf);
    if (this.isShowDebugMessages) {
      this.logger.debug(null, TtfLoader.class,
        "Added fpgm table");
    }
  }

  /**
   * <p>Gasp loader.</p>
   * @param pTtf font
   * @param pTde table entry
   * @param pIs input stream
   * @throws Exception an Exception
   **/
  public final void loadGasp(final TtfFont pTtf,
    final TtfTableDirEntry pTde,
      final TtfInputStream pIs) throws Exception {
    pIs.goAhead(pTde.getOffset());
    byte[] buf = new byte[(int) pTde.getLength()];
    pIs.read(buf);
    TableForEmbeddingBf tfebf =
      new TableForEmbeddingBf(pTde, true, true, buf);
    tfebf.setTdeMaker(this.tdeMaker);
    tfebf.setTableMaker(this.tableMakerFc);
    pTtf.getTablesForEmbedding().add(tfebf);
    if (this.isShowDebugMessages) {
      this.logger.debug(null, TtfLoader.class,
        "Added gasp table");
    }
  }

  /**
   * <p>Prep loader.</p>
   * @param pTtf font
   * @param pTde table entry
   * @param pIs input stream
   * @throws Exception an Exception
   **/
  public final void loadPrep(final TtfFont pTtf,
    final TtfTableDirEntry pTde,
      final TtfInputStream pIs) throws Exception {
    pIs.goAhead(pTde.getOffset());
    byte[] buf = new byte[(int) pTde.getLength()];
    pIs.read(buf);
    TableForEmbeddingBf tfebf =
      new TableForEmbeddingBf(pTde, true, true, buf);
    tfebf.setTdeMaker(this.tdeMaker);
    tfebf.setTableMaker(this.tableMakerFc);
    pTtf.getTablesForEmbedding().add(tfebf);
    if (this.isShowDebugMessages) {
      this.logger.debug(null, TtfLoader.class,
        "Added prep table");
    }
  }

  /**
   * <p>Cvt loader.</p>
   * @param pTtf font
   * @param pTde table entry
   * @param pIs input stream
   * @throws Exception an Exception
   **/
  public final void loadCvt(final TtfFont pTtf,
    final TtfTableDirEntry pTde,
      final TtfInputStream pIs) throws Exception {
    pIs.goAhead(pTde.getOffset());
    byte[] buf = new byte[(int) pTde.getLength()];
    pIs.read(buf);
    TableForEmbeddingBf tfebf =
      new TableForEmbeddingBf(pTde, true, true, buf);
    tfebf.setTdeMaker(this.tdeMaker);
    tfebf.setTableMaker(this.tableMakerFc);
    pTtf.getTablesForEmbedding().add(tfebf);
    if (this.isShowDebugMessages) {
      this.logger.debug(null, TtfLoader.class,
        "Added cvt table");
    }
  }

  /**
   * <p>OS2 loader.</p>
   * @param pTtf font
   * @param pTde table entry
   * @param pIs input stream
   * @throws Exception an Exception
   **/
  public final void loadOs2(final TtfFont pTtf,
    final TtfTableDirEntry pTde,
      final TtfInputStream pIs) throws Exception {
    TtfOs2 os2 = new TtfOs2(pTde);
    pTtf.setOs2(os2);
    pIs.goAhead(pTde.getOffset());
    os2.setVersion(pIs.readUInt16());
    if (os2.getVersion() < 1) {
      throw new ExceptionPdfWr("Unsupported old version OS2 table! Version = "
        + os2.getVersion());
    }
    os2.setXAvgCharWidth(pIs.readSInt16());
    os2.setUsWeightClass(pIs.readUInt16());
    os2.setUsWidthClass(pIs.readUInt16());
    pIs.readUInt16(); //fsType
    pIs.readSInt16(); //ySubscriptXSize
    pIs.readSInt16(); //ySubscriptYSize
    pIs.readSInt16(); //ySubscriptXOffset
    pIs.readSInt16(); //ySubscriptYOffset
    pIs.readSInt16(); //ySuperscriptXSize
    pIs.readSInt16(); //ySuperscriptYSize
    pIs.readSInt16(); //ySuperscriptXOffset
    pIs.readSInt16(); //ySuperscriptYOffset
    pIs.readSInt16(); //yStrikeoutSize
    pIs.readSInt16(); //yStrikeoutPosition
    os2.setSFamilyClass(pIs.readSInt16());
    pIs.skip(10); //panose
    pIs.readUInt32(); //ulUnicodeRange1
    pIs.readUInt32(); //ulUnicodeRange2
    pIs.readUInt32(); //ulUnicodeRange3
    pIs.readUInt32(); //ulUnicodeRange4
    pIs.skip(4); //int8 achVendID[4]
    os2.setFsSelection(pIs.readUInt16());
    if (os2.getVersion() >= 2) {
      pIs.readUInt16(); //fsFirstCharIndex
      pIs.readUInt16(); //fsLastCharIndex
      pIs.readSInt16(); //sTypoAscender
      pIs.readSInt16(); //sTypoDescender
      pIs.readSInt16(); //sTypoLineGap
      pIs.readUInt16(); //usWinAscent
      pIs.readUInt16(); //usWinDescent
      pIs.readUInt32(); //ulCodePageRange1
      pIs.readUInt32(); //ulCodePageRange2
      os2.setSxHeight(pIs.readSInt16());
      os2.setSCapHeight(pIs.readSInt16());
    }
    if (this.isShowDebugMessages) {
      this.logger.debug(null, TtfLoader.class,
        "Added os2: Version/XAvgCharWidth/UsWeightClass/UsWidthClass "
          + os2.getVersion() + "/" + os2.getXAvgCharWidth() + "/"
              + os2.getUsWeightClass() + "/" + os2.getUsWidthClass());
      this.logger.debug(null, TtfLoader.class,
        "os2: SFamilyClass/FsSelection/SxHeight/SCapHeight "
          + os2.getSFamilyClass() + "/"  + os2.getFsSelection() + "/"
            + os2.getSxHeight() + "/" + os2.getSCapHeight());
    }
  }

  /**
   * <p>CMAP loader.</p>
   * @param pTtf font
   * @param pTde cmap table entry
   * @param pIs input stream
   * @throws Exception an Exception
   **/
  public final void loadCmap(final TtfFont pTtf,
    final TtfTableDirEntry pTde,
      final TtfInputStream pIs) throws Exception {
    TtfCmap cmap = new TtfCmap(pTde);
    cmap.setUniToCid(new HashMap<Character, Character>());
    cmap.setSubtables(new ArrayList<TtfCmapSubtable>());
    pTtf.setCmap(cmap);
    pIs.goAhead(pTde.getOffset());
    pIs.readUInt16(); //version
    cmap.setNumSubTables(pIs.readUInt16());
    for (int i = 0; i < cmap.getNumSubTables(); i++) {
      TtfCmapSubtable cms = new TtfCmapSubtable();
      cms.setPlatformId(pIs.readUInt16());
      cms.setPlatformSpecificId(pIs.readUInt16());
      cms.setOffset(pIs.readUInt32());
      cmap.getSubtables().add(cms);
      if (this.isShowDebugMessages) {
        this.logger.debug(null, TtfLoader.class,
          "Added CMAP subtable: PlatformId/PlatformSpecificId/Offset "
            + cms.getPlatformId() + "/"  + cms.getPlatformSpecificId()
              + "/" + cms.getOffset());
      }
    }
    //load uniToCid map from thirst UNICODE (PlID=0) subtable:
    boolean wasUniLoaded = false;
    for (TtfCmapSubtable cms : cmap.getSubtables()) {
      if (cms.getPlatformId() == 0) { //Unicode
        loadUniToCid(pTtf, cms, pIs);
        wasUniLoaded = true;
        break;
      }
      if (cms.getPlatformId() == 3 && cms.getPlatformSpecificId() == 1) {
        loadUniToCid(pTtf, cms, pIs);
        wasUniLoaded = true;
        break;
      }
    }
    if (!wasUniLoaded) {
      throw new ExceptionPdfWr("Unsupported CMAP table!!!");
    }
  }

  /**
   * <p>Load unicode to CID(GID) CMAP.</p>
   * @param pTtf font
   * @param pCmapSub cmap subtable
   * @param pIs input stream
   * @throws Exception an Exception
   **/
  public final void loadUniToCid(final TtfFont pTtf,
    final TtfCmapSubtable pCmapSub,
      final TtfInputStream pIs) throws Exception {
    pIs.goAhead(pTtf.getCmap().getTableDirEntry().getOffset()
      + pCmapSub.getOffset());
    int frmtNum = pIs.readUInt16();
    if (this.isShowDebugMessages) {
      this.logger.debug(null, TtfLoader.class,
  "Loading uniToCid from subtable: PlatformId/PlatformSpecificId/Offset/Format "
          + pCmapSub.getPlatformId() + "/"  + pCmapSub.getPlatformSpecificId()
            + "/" + pCmapSub.getOffset() + "/" + frmtNum);
    }
    switch (frmtNum) {
      case 4:
      loadUniToCidF4(pTtf, pCmapSub, pIs);
      break;
      default:
      throw new ExceptionPdfWr(
    "Unsupported CMAP format for PlatformId/PlatformSpecificId/Offset/Format"
      + pCmapSub.getPlatformId() + "/"  + pCmapSub.getPlatformSpecificId()
        + "/" + pCmapSub.getOffset() + "/" + frmtNum);
    }
  }

  /**
   * <p>Load unicode to CID(GID) from CMAP format #4.</p>
   * @param pTtf font
   * @param pCmapSub cmap subtable
   * @param pIs input stream
   * @throws Exception an Exception
   **/
  public final void loadUniToCidF4(final TtfFont pTtf,
    final TtfCmapSubtable pCmapSub,
      final TtfInputStream pIs) throws Exception {
    int length = pIs.readUInt16();
    pIs.readUInt16(); //lang
    int segCountX2 = pIs.readUInt16();
    int segCount = segCountX2 / 2;
    pIs.readUInt16(); //searchRange
    pIs.readUInt16(); //entrySelector
    pIs.readUInt16(); //rangeShift
    int[] endCode = pIs.readUInt16Arr(segCount);
    pIs.readUInt16(); //reservedPad
    int[] startCode = pIs.readUInt16Arr(segCount);
    int[] idDelta = pIs.readUInt16Arr(segCount);
    int[] idRangeOffset = pIs.readUInt16Arr(segCount);
    int charCount = length / 2 - 8 - (segCount * 4);
    int[] glyphIndexArray = pIs.readUInt16Arr(charCount);
    for (int i = 0; i < segCount; i++) {
      for (int c = startCode[i]; c <= endCode[i]; c++) {
        if (startCode[i] != 0xFFFF && endCode[i] != 0xFFFF) {
          if (idRangeOffset[i] == 0) {
            int gid = idDelta[i] + c;
            pTtf.getCmap().getUniToCid().put((char) c, (char) gid);
          } else {
//gIdxAdr=idRangeOffset[i] + 2 * (c - startCode[i]) + (Ptr) &idRangeOffset[i]
//gIdx=*(&idRangeOffset[i] + idRangeOffset[i] / 2 + (c - startCode[i]))
            int idxGid = idRangeOffset[i] / 2 - segCount + i + c - startCode[i];
            int gid = glyphIndexArray[idxGid];
            if (gid != 0) {
              gid = idDelta[i] + gid;
              pTtf.getCmap().getUniToCid().put((char) c, (char) gid);
            }
          }
        }
      }
    }
    if (this.isShowDebugMessages) {
      this.logger.debug(null, TtfLoader.class, "UniToCid count = "
        + pTtf.getCmap().getUniToCid().size());
      if (this.logDetailLevel > 0 && this.logUnicodes != null
        && pTtf.getCmap() != null && pTtf.getCmap().getUniToCid() != null) {
        for (char uni : this.logUnicodes) {
          Character gid = pTtf.getCmap().getUniToCid().get(uni);
          if (gid != null) {
            int gidi = (int) gid.charValue();
            this.logger.debug(null, TtfLoader.class, "UniToCid "
              + ((int) uni) + " - " + gidi);
            if (this.logGids != null && !this.logGids.contains(gidi)) {
              this.logGids.add(gidi);
            }
          } else {
            this.logger.debug(null, TtfLoader.class, "There is no gid for uni "
              + ((int) uni));
          }
        }
      }
    }
  }

  //Simple getters and setters:
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
   * <p>Getter for tdeMaker.</p>
   * @return TdeMaker
   **/
  public final TdeMaker getTdeMaker() {
    return this.tdeMaker;
  }

  /**
   * <p>Setter for tdeMaker.</p>
   * @param pTdeMaker reference
   **/
  public final void setTdeMaker(final TdeMaker pTdeMaker) {
    this.tdeMaker = pTdeMaker;
  }

  /**
   * <p>Getter for tableMakerFc.</p>
   * @return TableMakerFc
   **/
  public final TableMakerFc getTableMakerFc() {
    return this.tableMakerFc;
  }

  /**
   * <p>Setter for tableMakerFc.</p>
   * @param pTableMakerFc reference
   **/
  public final void setTableMakerFc(final TableMakerFc pTableMakerFc) {
    this.tableMakerFc = pTableMakerFc;
  }

  /**
   * <p>Getter for tableMakerHmtx.</p>
   * @return TableMakerHmtx
   **/
  public final TableMakerHmtx getTableMakerHmtx() {
    return this.tableMakerHmtx;
  }

  /**
   * <p>Setter for tableMakerHmtx.</p>
   * @param pTableMakerHmtx reference
   **/
  public final void setTableMakerHmtx(final TableMakerHmtx pTableMakerHmtx) {
    this.tableMakerHmtx = pTableMakerHmtx;
  }

  /**
   * <p>Getter for tableMakerHead.</p>
   * @return TableMakerHead
   **/
  public final TableMakerHead getTableMakerHead() {
    return this.tableMakerHead;
  }

  /**
   * <p>Setter for tableMakerHead.</p>
   * @param pTableMakerHead reference
   **/
  public final void setTableMakerHead(final TableMakerHead pTableMakerHead) {
    this.tableMakerHead = pTableMakerHead;
  }

  /**
   * <p>Getter for tableMakerGlyf.</p>
   * @return TableMakerGlyf
   **/
  public final TableMakerGlyf getTableMakerGlyf() {
    return this.tableMakerGlyf;
  }

  /**
   * <p>Setter for tableMakerGlyf.</p>
   * @param pTableMakerGlyf reference
   **/
  public final void setTableMakerGlyf(final TableMakerGlyf pTableMakerGlyf) {
    this.tableMakerGlyf = pTableMakerGlyf;
  }

  /**
   * <p>Getter for tableMakerLoca.</p>
   * @return TableMakerLoca
   **/
  public final TableMakerLoca getTableMakerLoca() {
    return this.tableMakerLoca;
  }

  /**
   * <p>Setter for tableMakerLoca.</p>
   * @param pTableMakerLoca reference
   **/
  public final void setTableMakerLoca(final TableMakerLoca pTableMakerLoca) {
    this.tableMakerLoca = pTableMakerLoca;
  }

  /**
   * <p>Getter for tableMakerMaxp.</p>
   * @return TableMakerMaxp
   **/
  public final TableMakerMaxp getTableMakerMaxp() {
    return this.tableMakerMaxp;
  }

  /**
   * <p>Setter for tableMakerMaxp.</p>
   * @param pTableMakerMaxp reference
   **/
  public final void setTableMakerMaxp(final TableMakerMaxp pTableMakerMaxp) {
    this.tableMakerMaxp = pTableMakerMaxp;
  }

  /**
   * <p>Getter for tableMakerHhea.</p>
   * @return TableMakerHhea
   **/
  public final TableMakerHhea getTableMakerHhea() {
    return this.tableMakerHhea;
  }

  /**
   * <p>Setter for tableMakerHhea.</p>
   * @param pTableMakerHhea reference
   **/
  public final void setTableMakerHhea(final TableMakerHhea pTableMakerHhea) {
    this.tableMakerHhea = pTableMakerHhea;
  }

  /**
   * <p>Getter for ttfConstants.</p>
   * @return TtfConstants
   **/
  public final TtfConstants getTtfConstants() {
    return this.ttfConstants;
  }

  /**
   * <p>Setter for ttfConstants.</p>
   * @param pTtfConstants reference
   **/
  public final void setTtfConstants(final TtfConstants pTtfConstants) {
    this.ttfConstants = pTtfConstants;
  }

  /**
   * <p>Getter for logUnicodes.</p>
   * @return Set<Character>
   **/
  public final Set<Character> getLogUnicodes() {
    return this.logUnicodes;
  }

  /**
   * <p>Setter for logUnicodes.</p>
   * @param pLogUnicodes reference
   **/
  public final void setLogUnicodes(final Set<Character> pLogUnicodes) {
    this.logUnicodes = pLogUnicodes;
  }

  /**
   * <p>Getter for logGids.</p>
   * @return Set<Integer>
   **/
  public final Set<Integer> getLogGids() {
    return this.logGids;
  }

  /**
   * <p>Setter for logGids.</p>
   * @param pLogGids reference
   **/
  public final void setLogGids(final Set<Integer> pLogGids) {
    this.logGids = pLogGids;
  }

  /**
   * <p>Getter for logGtiDelta.</p>
   * @return int
   **/
  public final int getLogGtiDelta() {
    return this.logGtiDelta;
  }

  /**
   * <p>Setter for logGtiDelta.</p>
   * @param pLogGtiDelta reference
   **/
  public final void setLogGtiDelta(final int pLogGtiDelta) {
    this.logGtiDelta = pLogGtiDelta;
  }

  /**
   * <p>Getter for wrongGti.</p>
   * @return int
   **/
  public final int getWrongGti() {
    return this.wrongGti;
  }

  /**
   * <p>Setter for wrongGti.</p>
   * @param pWrongGti Wrong GTI/GID
   **/
  public final void setWrongGti(final int pWrongGti) {
    this.wrongGti = pWrongGti;
  }

  /**
   * <p>Getter for isCacheGlyf.</p>
   * @return boolean
   **/
  public final boolean getIsCacheGlyf() {
    return this.isCacheGlyf;
  }

  /**
   * <p>Setter for isCacheGlyf.</p>
   * @param pIsCacheGlyf reference
   **/
  public final void setIsCacheGlyf(final boolean pIsCacheGlyf) {
    this.isCacheGlyf = pIsCacheGlyf;
  }
}

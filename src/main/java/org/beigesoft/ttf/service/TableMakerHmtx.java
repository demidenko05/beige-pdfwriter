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

import java.util.Map;
import java.util.List;

import org.beigesoft.log.ILog;
import org.beigesoft.ttf.model.Glyph;
import org.beigesoft.ttf.model.TtfTableDirEntry;
import org.beigesoft.ttf.model.TableForEmbeddingHmtx;

/**
 * <p>Makes glyf table.</p>
 *
 * @author Yury Demidenko
 */
public class TableMakerHmtx implements ITableMaker<TableForEmbeddingHmtx> {

  /**
   * <p>Log.</p>
   **/
  private ILog log;

  /**
   * <p>It makes TTF table and write to output stream.</p>
   * @param pIs input stream
   * @param pOs output stream
   * @param pTfe table for embedding buffered
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @param pUsedCids used chars
   * @param pGls used glyphs map
   * @throws Exception an Exception
   **/
  @Override
  public final void makeTable(final TtfInputStream pIs,
    final TtfOutputStream pOs, final TableForEmbeddingHmtx pTfe,
      final TtfTableDirEntry pTde, final long[] pCurrLongChksum,
        final List<Character> pUsedCids,
          final Map<Integer, Glyph> pGls) throws Exception {
    // last CID + 1 (include zero)
    int numGlyphs = pUsedCids.get(pUsedCids.size() - 1) + 1;
    pTde.setOffset(pOs.getSize());
    int widthsLen = Math.min(numGlyphs, pTfe.getTtf().getHhea()
      .getNumOfLongHorMetrics());
    char chz = 0;
    short shz = 0;
    for (int i = 0; i < widthsLen; i++) {
      if (pGls.get(i) != null) {
        pOs.writeUInt16(pTfe.getTtf().getHmtx().getWidths()[i],
          pTde, pCurrLongChksum);
        pOs.writeSInt16(pTfe.getTtf().getHmtx().getLeftSideBearing()[i],
          pTde, pCurrLongChksum);
      } else { //to reduce file
        pOs.writeUInt16(chz, pTde, pCurrLongChksum);
        pOs.writeSInt16(shz, pTde, pCurrLongChksum);
      }
    }
    //add bearing:
    for (int i = widthsLen; i < numGlyphs; i++) {
      if (pGls.get(i) != null) {
        pOs.writeSInt16(pTfe.getTtf().getHmtx()
          .getLeftSideBearingAdd()[i - widthsLen], pTde, pCurrLongChksum);
      } else { //to reduce file
        pOs.writeSInt16(shz, pTde, pCurrLongChksum);
      }
    }
    int mod4 = (int) pTde.getLength() % 4;
    if (mod4 != 0) {
      pOs.addZeroBytesToCheksum(4 - mod4, pTde, pCurrLongChksum);
      if (getLog().getDbgSh() && getLog().getDbgFl() < 4046
        && getLog().getDbgCl() > 4049) {
        this.log.debug(null, TableMakerHmtx.class,
          "hmtx added zeros to checksum " + (4 - mod4));
      }
    }
    if (getLog().getDbgSh() && getLog().getDbgFl() < 4046
      && getLog().getDbgCl() > 4049) {
      this.log.debug(null, TableMakerHmtx.class,
        "Added hmtx checksum/offset/length " + pTde.getChecksum() + "/"
          + pTde.getOffset() + "/" + pTde.getLength());
    }
  }

  //Simple getters and setters:

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
}

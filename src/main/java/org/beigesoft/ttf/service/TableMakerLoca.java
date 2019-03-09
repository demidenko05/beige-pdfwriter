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
import org.beigesoft.ttf.model.TableForEmbeddingLoca;

/**
 * <p>Makes Loca TTF table and write to output stream.</p>
 *
 * @author Yury Demidenko
 */
public class TableMakerLoca implements ITableMaker<TableForEmbeddingLoca> {

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
    final TtfOutputStream pOs, final TableForEmbeddingLoca pTfe,
      final TtfTableDirEntry pTde, final long[] pCurrLongChksum,
        final List<Character> pUsedCids,
          final Map<Integer, Glyph> pGls) throws Exception {
    // last CID + 1 (include zero)
    int numGlyphs = pUsedCids.get(pUsedCids.size() - 1) + 1;
    pTde.setOffset(pOs.getSize());
    int locaLen = numGlyphs + 1;
    int currOfstInLoca = 0;
    for (int i = 0; i < locaLen; i++) {
      if (pTfe.getLoca().getOffsets16() != null) {
        int off = currOfstInLoca / 2;
        if (currOfstInLoca > 65536) {
          off -= 65536;
        }
        pOs.writeUInt16(off, pTde, pCurrLongChksum);
      } else {
        pOs.writeUInt32(currOfstInLoca, pTde, pCurrLongChksum);
      }
      Glyph gl = pGls.get(i);
      if (gl != null && gl.getLength() > 0) {
        currOfstInLoca += (int) gl.getLength();
      }
    }
    int mod4 = (int) pTde.getLength() % 4;
    if (mod4 != 0) {
      pOs.addZeroBytesToCheksum(4 - mod4, pTde, pCurrLongChksum);
      if (getLog().getDbgSh() && this.getLog().getDbgFl() < 4050
        && this.getLog().getDbgCl() > 4053) {
        this.log.debug(null, TableMakerLoca.class,
          "loca added zeros to checksum " + (4 - mod4));
      }
    }
    if (getLog().getDbgSh() && this.getLog().getDbgFl() < 4050
      && this.getLog().getDbgCl() > 4053) {
      this.log.debug(null, TtfCompactFontMaker.class,
        "Added loca checksum/offset/length " + pTde.getChecksum() + "/"
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

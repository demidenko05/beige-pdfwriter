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
import org.beigesoft.ttf.model.TableForEmbeddingHead;

/**
 * <p>Makes head TTF table and write to output stream.</p>
 *
 * @author Yury Demidenko
 */
public class TableMakerHead implements ITableMaker<TableForEmbeddingHead> {

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
    final TtfOutputStream pOs, final TableForEmbeddingHead pTfe,
      final TtfTableDirEntry pTde, final long[] pCurrLongChksum,
        final List<Character> pUsedCids,
          final Map<Integer, Glyph> pGls) throws Exception {
    pTde.setOffset(pOs.getSize());
    //version, fontRevision
    //checksumAdjustment, magicNumber, flags:
    pOs.writeByteArr(pTfe.getHead().getHeadByteArr(), null, null);
    pOs.writeUInt16(pTfe.getHead().getUnitsPerEm(), null, null);
    //created, modified:
    pOs.writeByteArr(pTfe.getHead().getCreatedModifiedBuf(), null, null);
    pOs.writeSInt16(pTfe.getHead().getXMin(), null, null);
    pOs.writeSInt16(pTfe.getHead().getYMin(), null, null);
    pOs.writeSInt16(pTfe.getHead().getXMax(), null, null);
    pOs.writeSInt16(pTfe.getHead().getYMax(), null, null);
    //macStyle, lowestRecPPEM, fontDirectionHint:
    pOs.writeByteArr(pTfe.getHead().getMsLrpFdhBuf(), null, null);
    pOs.writeSInt16(pTfe.getHead().getIndexToLocFormat(), null, null);
    pOs.writeByteArr(pTfe.getHead().getGlyphDataFormatBuf(), null, null);
    if (getLog().getDbgSh() && getLog().getDbgFl() < 4040
      && getLog().getDbgCl() > 4042) {
      this.log.debug(null, TableMakerHead.class,
        "Added head checksum/offset/length " + pTde.getChecksum() + "/"
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

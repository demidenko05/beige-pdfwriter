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

import java.util.Map;
import java.util.List;

import org.beigesoft.log.ILogger;
import org.beigesoft.ttf.model.Glyph;
import org.beigesoft.ttf.model.TtfTableDirEntry;
import org.beigesoft.ttf.model.TableForEmbeddingMaxp;

/**
 * <p>Makes Maxp TTF table and write to output stream.</p>
 *
 * @author Yury Demidenko
 */
public class TableMakerMaxp implements ITableMaker<TableForEmbeddingMaxp> {

  /**
   * <p>Logger.</p>
   **/
  private ILogger logger;

  //Debug log preferences made by master bean for improving speed:
  /**
   * <p>If show debug messages.</p>
   **/
  private boolean isShowDebugMessages;

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
    final TtfOutputStream pOs, final TableForEmbeddingMaxp pTfe,
      final TtfTableDirEntry pTde, final long[] pCurrLongChksum,
        final List<Character> pUsedCids,
          final Map<Integer, Glyph> pGls) throws Exception {
    // last CID + 1 (include zero)
    int numGlyphs = pUsedCids.get(pUsedCids.size() - 1) + 1;
    pTde.setOffset(pOs.getSize());
    pTde.setLength(0); //will be rewritten
    pOs.writeByteArr(pTfe.getMaxp().getVersion(), pTde, pCurrLongChksum);
    pOs.writeUInt16(numGlyphs, pTde, pCurrLongChksum);
    pOs.writeByteArr(pTfe.getMaxp().getTail(), pTde, pCurrLongChksum);
    int mod4 = (int) pTde.getLength() % 4;
    if (mod4 != 0) {
      pOs.addZeroBytesToCheksum(4 - mod4, pTde, pCurrLongChksum);
      if (this.isShowDebugMessages) {
        this.logger.debug(null, TableMakerMaxp.class,
          "maxp added zeros to checksum " + (4 - mod4));
      }
    }
    if (this.isShowDebugMessages) {
      this.logger.debug(null, TableMakerMaxp.class,
        "Added maxp checksum/offset/length "
          + pTde.getChecksum() + "/" + pTde.getOffset()
            + "/" + pTde.getLength());
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
   * <p>Getter for isShowDebugMessages.</p>
   * @return boolean
   **/
  public final boolean getIsShowDebugMessages() {
    return this.isShowDebugMessages;
  }

  /**
   * <p>Setter for isShowDebugMessages.</p>
   * @param pIsShowDebugMessages reference
   **/
  public final void setIsShowDebugMessages(final boolean pIsShowDebugMessages) {
    this.isShowDebugMessages = pIsShowDebugMessages;
  }
}

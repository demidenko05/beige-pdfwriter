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

import org.beigesoft.log.ILogger;
import org.beigesoft.ttf.model.Glyph;
import org.beigesoft.ttf.model.TtfTableDirEntry;
import org.beigesoft.ttf.model.TableForEmbeddingBf;
import org.beigesoft.pdf.exception.ExceptionPdfWr;

/**
 * <p>Makes full copy TTF table and write to output stream.
 * The table is already cached. E.g. cvt, fpgm, gasp, prep.</p>
 *
 * @author Yury Demidenko
 */
public class TableMakerFc implements ITableMaker<TableForEmbeddingBf> {

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
    final TtfOutputStream pOs, final TableForEmbeddingBf pTfe,
      final TtfTableDirEntry pTde, final long[] pCurrLongChksum,
        final List<Character> pUsedCids,
          final Map<Integer, Glyph> pGls) throws Exception {
    if (pTde.getLength() != pTfe.getBuffer().length) {
      throw new ExceptionPdfWr(
        "Algorithm length full copy TTF table error! tde/buffer: "
          + pTde.getLength() + "/" + pTfe.getBuffer().length);
    }
    pTde.setOffset(pOs.getSize());
    if (this.isShowDebugMessages) {
      this.logger.debug(null, TableMakerFc.class,
        "Full copy from buffer - table/offsetOs/length "
          + pTde.getTagString() + "/" + pOs.getSize()
            + "/" + pTde.getLength());
    }
    long[] copyLongChksum = new long[] {-1L, -1L, -1L, -1L};
    TtfTableDirEntry tdeCopy = new TtfTableDirEntry();
    pOs.writeByteArr(pTfe.getBuffer(), tdeCopy, copyLongChksum);
   int mod4 = (int) pTde.getLength() % 4;
    if (mod4 != 0) {
      pOs.addZeroBytesToCheksum(4 - mod4, tdeCopy, copyLongChksum);
    }
    if (this.isShowDebugMessages
      && pTde.getChecksum() != tdeCopy.getChecksum()) {
      this.logger.warn(null, TableMakerFc.class,
        "Checksums don't equals: tag/checksum source/checksum real: "
          + pTde.getTagString() + "/" + pTde.getChecksum()
            + "/" + tdeCopy.getChecksum());
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

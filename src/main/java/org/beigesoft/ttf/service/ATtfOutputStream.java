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

import java.io.IOException;
import java.io.OutputStream;

import org.beigesoft.log.ILogger;
import org.beigesoft.ttf.model.TtfTableDirEntry;

/**
 * <p>Base Write TTF to output stream (usually ByteArrayOutputStream).
 * For single thread only (bean per client's request).</p>
 *
 * @author Yury Demidenko
 */
public abstract class ATtfOutputStream implements ITtfOutputStream {

  /**
   * <p>Logger.</p>
   **/
  private ILogger logger;

  /**
   * <p>OutputStream.</p>
   **/
  protected final OutputStream outputStream;

  //Debug log preferences:
  /**
   * <p>If show debug messages.</p>
   **/
  protected boolean isShowDebugMessages;

  /**
   * <p>Logs detail level.</p>
   **/
  protected int logDetailLevel;

  /**
   * <p>Current size.</p>
   **/
  protected long size = 0;

  /**
   * <p>Only constructor.</p>
   * @param pOutputStream reference
   **/
  public ATtfOutputStream(final OutputStream pOutputStream) {
    this.outputStream = pOutputStream;
  }

  /**
   * <p>Add just read byte (int) to table checksum and length.</p>
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @param pByte data
   * @throws IOException an IOException
   **/
  @Override
  public final void addToChecksumLength(final TtfTableDirEntry pTde,
    final long[] pCurrLongChksum, final int pByte) throws IOException {
    pTde.setLength(pTde.getLength() + 1);
    addToChecksum(pTde, pCurrLongChksum, pByte);
  }

  /**
   * <p>Add just read byte (int) to table checksum.
   * <pre>
   * uint32 CalcTableChecksum(uint32 *table, uint32 numberOfBytesInTable)
   * {
   * uint32 sum = 0;
   * uint32 nLongs = (numberOfBytesInTable + 3) / 4;
   * while (nLongs-- > 0)
   *     sum += *table++;
   * return sum;
   * }
   * </pre>
   * </p>
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @param pByte data
   * @throws IOException an IOException
   **/
  @Override
  public final void addToChecksum(final TtfTableDirEntry pTde,
    final long[] pCurrLongChksum, final int pByte) throws IOException {
    if (pByte < 0) {
      throw new IOException("Algorithm to byte conversion error!!! Byte < 0: "
        + pByte);
    }
    if (pCurrLongChksum[0] < 0) {
      pCurrLongChksum[0] = pByte;
    } else if (pCurrLongChksum[1] < 0) {
      pCurrLongChksum[1] = pByte;
    } else if (pCurrLongChksum[2] < 0) {
      pCurrLongChksum[2] = pByte;
    } else {
      pCurrLongChksum[3] = pByte;
      pCurrLongChksum[0] <<= 24;
      pCurrLongChksum[1] <<= 16;
      pCurrLongChksum[2] <<= 8;
      long checksum = pCurrLongChksum[0] | pCurrLongChksum[1]
        | pCurrLongChksum[2] | pCurrLongChksum[3];
      pTde.setChecksum(pTde.getChecksum() + checksum);
      pCurrLongChksum[0] = -1L;
      pCurrLongChksum[1] = -1L;
      pCurrLongChksum[2] = -1L;
      pCurrLongChksum[3] = -1L;
    }
  }

  /**
   * <p>Get current size.</p>
   * @return long
   **/
  @Override
  public final long getSize() {
    return this.size;
  }

  /**
   * <p>Closed underlying stream.</p>
   * @throws IOException an IOException
   **/
  @Override
  public final void close() throws IOException {
    this.outputStream.close();
  }

  //CGS:
  /**
   * <p>Setter for logger.</p>
   * @param pLogger reference
   **/
  public final void setLogger(final ILogger pLogger) {
    this.logger = pLogger;
    this.isShowDebugMessages = this.logger
      .getIsShowDebugMessagesFor(getClass());
    this.logDetailLevel = this.logger.getDetailLevel();
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
   * <p>Getter for outputStream.</p>
   * @return OutputStream
   **/
  public final OutputStream getOutputStream() {
    return this.outputStream;
  }
}

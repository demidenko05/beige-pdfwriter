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

import java.io.IOException;
import java.io.OutputStream;

import org.beigesoft.log.ILog;
import org.beigesoft.ttf.model.TtfTableDirEntry;

/**
 * <p>Base Write TTF to output stream (usually ByteArrayOutputStream).
 * For single thread only (bean per client's request).</p>
 *
 * @author Yury Demidenko
 */
public abstract class ATtfOutputStream implements ITtfOutputStream {

  /**
   * <p>Log.</p>
   **/
  private ILog log;

  /**
   * <p>OutputStream.</p>
   **/
  protected final OutputStream outputStream;

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

  //SGS:
  /**
   * <p>Setter for log.</p>
   * @param pLog reference
   **/
  public final void setLog(final ILog pLog) {
    this.log = pLog;
  }

  /**
   * <p>Getter for log.</p>
   * @return ILog
   **/
  public final ILog getLog() {
    return this.log;
  }

  /**
   * <p>Getter for outputStream.</p>
   * @return OutputStream
   **/
  public final OutputStream getOutputStream() {
    return this.outputStream;
  }
}

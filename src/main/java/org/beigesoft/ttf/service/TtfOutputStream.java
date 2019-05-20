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

import org.beigesoft.ttf.model.TtfTableDirEntry;

/**
 * <p>Write TTF to output stream (usually ByteArrayOutputStream).
 * For single thread only (bean per client's request).</p>
 *
 * @author Yury Demidenko
 */
public class TtfOutputStream extends ATtfOutputStream {

  /**
   * <p>Only constructor.</p>
   * @param pOutputStream reference
   **/
  public TtfOutputStream(final OutputStream pOutputStream) {
    super(pOutputStream);
  }

  /**
   * <p>Write byte delegate to output.</p>
   * @param pData data
   * @throws IOException an IOException
   **/
  @Override
  public final void write(final int pData) throws IOException {
    this.outputStream.write(pData);
    this.size++;
  }

  /**
   * <p>Write UInt16 to output.</p>
   * @param pData data
   * @throws IOException an IOException
   **/
  @Override
  public final void writeUInt16(final int pData) throws IOException {
    this.outputStream.write(pData >>> 8);
    this.outputStream.write(pData);
    this.size += 2L;
  }

  /**
   * <p>Write UInt32 to output.</p>
   * @param pData data
   * @throws IOException an IOException
   **/
  @Override
  public final void writeUInt32(final long pData) throws IOException {
    this.outputStream.write((int) (pData >>> 24));
    this.outputStream.write((int) (pData >>> 16));
    this.outputStream.write((int) (pData >>> 8));
    this.outputStream.write((int) (pData));
    this.size += 4L;
  }

  /**
   * <p>Write byte to output.</p>
   * @param pData data
   * @throws IOException an IOException
   **/
  @Override
  public final void writeByte(final int pData) throws IOException {
    this.outputStream.write(pData);
    this.size++;
  }

  /**
   * <p>Write byte array to output.</p>
   * @param pData data
   * @throws IOException an IOException
   **/
  @Override
  public final void writeByteArr(final byte[] pData) throws IOException {
    this.outputStream.write(pData);
    this.size += pData.length;
  }

  /**
   * <p>Write zero bytes to output.</p>
   * @param pCount of zero bytes
   * @throws IOException an IOException
   **/
  @Override
  public final void writeZeroBytes(final int pCount) throws IOException {
    for (int i = 0; i < pCount; i++) {
      this.outputStream.write(0);
    }
    this.size += pCount;
  }

  /**
   * <p>Write byte array to output.</p>
   * @param pData data
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @throws IOException an IOException
   **/
  @Override
  public final void writeByteArr(final byte[] pData,
    final TtfTableDirEntry pTde,
      final long[] pCurrLongChksum) throws IOException {
    this.outputStream.write(pData);
    this.size += pData.length;
    if (pTde != null) {
      for (byte bt : pData) {
        int byte1 = bt;
        byte1 &= 0x000000FF;
        addToChecksumLength(pTde, pCurrLongChksum, byte1);
      }
    }
  }

  /**
   * <p>Write UInt16 to output.</p>
   * @param pData data char
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @throws IOException an IOException
   **/
  @Override
  public final void writeUInt16(final char pData,
    final TtfTableDirEntry pTde,
      final long[] pCurrLongChksum) throws IOException {
    int byte1 = (int) (pData >>> 8);
    this.outputStream.write(byte1);
    int byte2 = (int) pData;
    this.outputStream.write(byte2);
    if (pTde != null) {
      addToChecksumLength(pTde, pCurrLongChksum, byte1);
      addToChecksumLength(pTde, pCurrLongChksum, byte2);
    }
    this.size += 2L;
  }

  /**
   * <p>Write SInt16 to output.</p>
   * @param pData data short
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @throws IOException an IOException
   **/
  @Override
  public final void writeSInt16(final short pData,
    final TtfTableDirEntry pTde,
      final long[] pCurrLongChksum) throws IOException {
    int byte1 = (int) (pData >>> 8);
    byte1 &= 0x000000FF;
    this.outputStream.write(byte1);
    int byte2 = (int) pData;
    byte2 &= 0x000000FF;
    this.outputStream.write(byte2);
    if (pTde != null) {
      addToChecksumLength(pTde, pCurrLongChksum, byte1);
      addToChecksumLength(pTde, pCurrLongChksum, byte2);
    }
    this.size += 2L;
  }

  /**
   * <p>Write UInt16 to output.</p>
   * @param pData data
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @throws IOException an IOException
   **/
  @Override
  public final void writeUInt16(final int pData,
    final TtfTableDirEntry pTde,
      final long[] pCurrLongChksum) throws IOException {
    int byte1 = (int) (pData >>> 8);
    this.outputStream.write(byte1);
    int byte2 = (int) pData;
    this.outputStream.write(byte2);
    if (pTde != null) {
      addToChecksumLength(pTde, pCurrLongChksum, byte1);
      addToChecksumLength(pTde, pCurrLongChksum, byte2);
    }
    this.size += 2L;
  }

  /**
   * <p>Write UInt32 to output.</p>
   * @param pData data
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @throws IOException an IOException
   **/
  @Override
  public final void writeUInt32(final long pData,
    final TtfTableDirEntry pTde,
      final long[] pCurrLongChksum) throws IOException {
    int byte1 = (int) (pData >>> 24);
    this.outputStream.write(byte1);
    int byte2 = (int) (pData >>> 16);
    this.outputStream.write(byte2);
    int byte3 = (int) (pData >>> 8);
    this.outputStream.write(byte3);
    int byte4 = (int) pData;
    this.outputStream.write(byte4);
    if (pTde != null) {
      addToChecksumLength(pTde, pCurrLongChksum, byte1);
      addToChecksumLength(pTde, pCurrLongChksum, byte2);
      addToChecksumLength(pTde, pCurrLongChksum, byte3);
      addToChecksumLength(pTde, pCurrLongChksum, byte4);
    }
    this.size += 4L;
  }

  /**
   * <p>Write zero bytes to output.</p>
   * @param pCount of zero bytes
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @throws IOException an IOException
   **/
  @Override
  public final void writeZeroBytes(final int pCount,
    final TtfTableDirEntry pTde,
      final long[] pCurrLongChksum) throws IOException {
    for (int i = 0; i < pCount; i++) {
      this.outputStream.write(0);
      if (pTde != null) {
        addToChecksumLength(pTde, pCurrLongChksum, 0);
      }
    }
    this.size += pCount;
  }

  /**
   * <p>Added zero bytes to ckecksum.</p>
   * @param pCount of zero bytes
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @throws IOException an IOException
   **/
  @Override
  public final void addZeroBytesToCheksum(final int pCount,
    final TtfTableDirEntry pTde,
      final long[] pCurrLongChksum) throws IOException {
    for (int i = 0; i < pCount; i++) {
      if (pTde != null) {
        addToChecksum(pTde, pCurrLongChksum, 0);
      }
    }
  }

  /**
   * <p>Copy bytes from input to output.</p>
   * @param pIn stream
   * @param pCount of zero bytes
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @throws IOException an IOException
   **/
  @Override
  public final void copyBytes(final ITtfInputStream pIn,
    final long pCount, final TtfTableDirEntry pTde,
      final long[] pCurrLongChksum) throws IOException {
    if (getLog().getDbgSh() && getLog().getDbgFl() < 4010
      && getLog().getDbgCl() > 4015) {
      StringBuffer sb = new StringBuffer();
      for (long i = 0; i < pCount; i++) {
        int rez = pIn.read();
        if (rez == -1) {
          throw new IOException("End of stream!!!");
        }
        this.outputStream.write(rez);
        if (i < 20) {
          sb.append(" " + rez);
        }
        if (pTde != null) {
          addToChecksumLength(pTde, pCurrLongChksum, rez);
        }
      }
      getLog().debug(null, TtfCompactFontMaker.class,
        "copied total/first bytes " + pCount
          + "/" + sb.toString());
    } else {
      for (long i = 0; i < pCount; i++) {
        int rez = pIn.read();
        if (rez == -1) {
          throw new IOException("End of stream!!!");
        }
        this.outputStream.write(rez);
        if (pTde != null) {
          addToChecksumLength(pTde, pCurrLongChksum, rez);
        }
      }
    }
    this.size += pCount;
  }
}

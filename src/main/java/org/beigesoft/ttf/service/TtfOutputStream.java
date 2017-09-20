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
    if (this.isShowDebugMessages && this.logDetailLevel > 1000) {
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
      getLogger().debug(null, TtfCompactFontMaker.class,
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

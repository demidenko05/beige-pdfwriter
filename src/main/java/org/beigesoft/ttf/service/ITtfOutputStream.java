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
import java.io.Closeable;

import org.beigesoft.ttf.model.TtfTableDirEntry;

/**
 * <p>Write TTF to output stream (usually ByteArrayOutputStream).</p>
 *
 * @author Yury Demidenko
 */
interface ITtfOutputStream extends Closeable {

  /**
   * <p>Write UInt16 to output.</p>
   * @param pData data
   * @throws IOException an IOException
   **/
  void writeUInt16(int pData) throws IOException;

  /**
   * <p>Write byte delegate to output.</p>
   * @param pData data
   * @throws IOException an IOException
   **/
  void write(int pData) throws IOException;

  /**
   * <p>Write UInt32 to output.</p>
   * @param pData data
   * @throws IOException an IOException
   **/
  void writeUInt32(long pData) throws IOException;

  /**
   * <p>Write byte to output.</p>
   * @param pData data
   * @throws IOException an IOException
   **/
  void writeByte(int pData) throws IOException;

  /**
   * <p>Write byte array to output.</p>
   * @param pData data
   * @throws IOException an IOException
   **/
  void writeByteArr(byte[] pData) throws IOException;

  /**
   * <p>Write byte array to output.</p>
   * @param pData data
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @throws IOException an IOException
   **/
  void writeByteArr(byte[] pData, TtfTableDirEntry pTde,
      long[] pCurrLongChksum) throws IOException;

  /**
   * <p>Write zero bytes to output.</p>
   * @param pCount of zero bytes
   * @throws IOException an IOException
   **/
  void writeZeroBytes(int pCount) throws IOException;

  /**
   * <p>Write UInt16 char to output.</p>
   * @param pData data char
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @throws IOException an IOException
   **/
  void writeUInt16(char pData, TtfTableDirEntry pTde,
      long[] pCurrLongChksum) throws IOException;

  /**
   * <p>Write UInt16 to output.</p>
   * @param pData data
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @throws IOException an IOException
   **/
  void writeUInt16(int pData, TtfTableDirEntry pTde,
      long[] pCurrLongChksum) throws IOException;

  /**
   * <p>Write SInt16 to output.</p>
   * @param pData data short
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @throws IOException an IOException
   **/
  void writeSInt16(short pData, TtfTableDirEntry pTde,
      long[] pCurrLongChksum) throws IOException;

  /**
   * <p>Write UInt32 to output.</p>
   * @param pData data
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @throws IOException an IOException
   **/
  void writeUInt32(long pData, TtfTableDirEntry pTde,
      long[] pCurrLongChksum) throws IOException;

  /**
   * <p>Write zero bytes to output.</p>
   * @param pCount of zero bytes
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @throws IOException an IOException
   **/
  void writeZeroBytes(int pCount, TtfTableDirEntry pTde,
      long[] pCurrLongChksum) throws IOException;

  /**
   * <p>Copy bytes from input to output.</p>
   * @param pIn stream
   * @param pCount of zero bytes
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @throws IOException an IOException
   **/
  void copyBytes(ITtfInputStream pIn,
    long pCount, TtfTableDirEntry pTde,
      long[] pCurrLongChksum) throws IOException;

  /**
   * <p>Added zero bytes to ckecksum.</p>
   * @param pCount of zero bytes
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @throws IOException an IOException
   **/
  void addZeroBytesToCheksum(int pCount, TtfTableDirEntry pTde,
    long[] pCurrLongChksum) throws IOException;

  /**
   * <p>Add just read byte (int) to table checksum and length.</p>
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @param pByte data
   * @throws IOException an IOException
   **/
  void addToChecksumLength(TtfTableDirEntry pTde,
    long[] pCurrLongChksum, int pByte) throws IOException;

  /**
   * <p>Add byte (int) to table checksum.
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
  void addToChecksum(TtfTableDirEntry pTde,
    long[] pCurrLongChksum, int pByte) throws IOException;

  /**
   * <p>Get current size.</p>
   * @return long
   **/
  long getSize();
}

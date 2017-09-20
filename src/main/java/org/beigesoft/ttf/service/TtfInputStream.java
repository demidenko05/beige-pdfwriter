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

import java.io.InputStream;
import java.io.IOException;

/**
 * <p>One way reader of TTF data from input stream (file).
 * For single thread only (bean per client's request).
 * For caching file use dedicated multithread TtfCachedFile.</p>
 *
 * @author Yury Demidenko
 */
public class TtfInputStream implements ITtfInputStream {

  /**
   * <p>Current offset.</p>
   **/
  private long currentOffset = 0;

  /**
   * <p>Wrapped IS.</p>
   **/
  private final InputStream inputStream;

  /**
   * <p>Only constructor.</p>
   * @param pInputStream reference
   **/
  public TtfInputStream(final InputStream pInputStream) {
    this.inputStream = pInputStream;
  }

  /**
   * <p>Read byte in Java standard.</p>
   * @return read int
   * @throws IOException an IOException
   **/
  @Override
  public final int read() throws IOException {
    int byte1 = this.inputStream.read();
    this.currentOffset++;
    return byte1;
  }

  /**
   * <p>Read byte array in Java standard.</p>
   * @param pBuffer byte array buffer
   * @return read total
   * @throws IOException an IOException
   **/
  @Override
  public final int read(final byte[] pBuffer) throws IOException {
    int total = this.inputStream.read(pBuffer);
    if (total == -1) {
      throw new IOException("Cant read anything to array!");
    }
    if (total != pBuffer.length) {
      //it happen on LiberationSans-Regular copy prep 835
      //but it read 632
      while (total != pBuffer.length) {
        int byte1 = this.inputStream.read();
        if (byte1 < 0) {
          throw new IOException(
            "Cant read full array! required/read/offsetStart: "
              + pBuffer.length + "/" + total + "/" + this.currentOffset);
        }
        pBuffer[total - 1] = (byte) byte1;
        total++;
      }
    }
    this.currentOffset += total;
    return total;
  }

  /**
   * <p>Read char (2 bytes) or uint16.</p>
   * @return read int
   * @throws IOException an IOException
   **/
  @Override
  public final int readUInt16() throws IOException {
    int byte1 = this.inputStream.read();
    byte1 <<= 8;
    int byte2 = this.inputStream.read();
    this.currentOffset += 2;
    return byte1 | byte2;
  }

  /**
   * <p>Read char (2 bytes) or uint16.</p>
   * @return read char
   * @throws IOException an IOException
   **/
  @Override
  public final char readChar() throws IOException {
    return (char) readUInt16();
  }

  /**
   * <p>Read 16-bit signed integer that describes a quantity in FUnits,
   * the smallest measurable distance in em space.</p>
   * @return read short
   * @throws IOException an IOException
   **/
  @Override
  public final short readFWord() throws IOException {
    return readSInt16();
  }

  /**
   * <p>16-bit unsigned integer that describes a quantity in FUnits,
   * the smallest measurable distance in em space.</p>
   * @return read int
   * @throws IOException an IOException
   **/
  @Override
  public final int readUFWord() throws IOException {
    return readUInt16();
  }

  /**
   * <p>Read short (2 bytes) or sint16.</p>
   * @return read short
   * @throws IOException an IOException
   **/
  @Override
  public final short readSInt16() throws IOException {
    return (short) readUInt16();
  }

  /**
   * <p>Read unsigned byte flag.</p>
   * @return read int (ubyte)
   * @throws IOException an IOException
   **/
  @Override
  public final int readUInt8() throws IOException {
    return read();
  }

  /**
   * <p>Read byte.</p>
   * @return read byte
   * @throws IOException an IOException
   **/
  @Override
  public final byte readByte() throws IOException {
    return (byte) readUInt8();
  }

  /**
   * <p>Read char (4 bytes) or fixed 16.16.</p>
   * @return read char
   * @throws IOException an IOException
   **/
  @Override
  public final float readFixed() throws IOException {
    float result = readSInt16();
    result += readUInt16() / 65536.0;
    return result;
  }

  /**
   * <p>Read unsigned int (4 bytes)
   * to red uint32 .</p>
   * @return read int
   * @throws IOException an IOException
   **/
  @Override
  public final long readUInt32() throws IOException {
    int byte1 = this.inputStream.read();
    int byte2 = this.inputStream.read();
    int byte3 = this.inputStream.read();
    int byte4 = this.inputStream.read();
    long lbyte1 = byte1;
    lbyte1 <<= 24;
    long lbyte2 = byte2 << 16;
    long lbyte3 = byte3 << 8;
    long lbyte4 = byte4;
    this.currentOffset += 4;
    return lbyte1 | lbyte2 | lbyte3 | lbyte4;
  }

  /**
   * <p>Read table Tag (4 bytes).</p>
   * @return read byte[4]
   * @throws IOException an IOException
   **/
  @Override
  public final byte[] readTag() throws IOException {
    int byte1 = this.inputStream.read();
    int byte2 = this.inputStream.read();
    int byte3 = this.inputStream.read();
    int byte4 = this.inputStream.read();
    byte[] result = new byte[4];
    result[0] = (byte) byte1;
    result[1] = (byte) byte2;
    result[2] = (byte) byte3;
    result[3] = (byte) byte4;
    this.currentOffset += 4;
    return result;
  }

  /**
   * <p>Read UInt16 array.</p>
   * @param pCount of UInt16
   * @return read arrUInt16[pCount]
   * @throws IOException an IOException
   **/
  @Override
  public final int[] readUInt16Arr(final int pCount) throws IOException {
    int[] result = new int[pCount];
    for (int i = 0; i < pCount; i++) {
      result[i] = readUInt16();
    }
    return result;
  }

  /**
   * <p>Read UInt8 flags array.</p>
   * @param pCount of UInt8
   * @return read short arrUInt8[pCount]
   * @throws IOException an IOException
   **/
  @Override
  public final int[] readUInt8Arr(final int pCount) throws IOException {
    int[] result = new int[pCount];
    for (int i = 0; i < pCount; i++) {
      result[i] = readUInt8();
    }
    return result;
  }

  /**
   * <p>Read long (8 bytes)
   * longDateTime The long internal format of a date in seconds
   * since 12:00 midnight, January 1, 1904.
   * It is represented as a signed 64-bit integer.</p>
   * @return read long
   * @throws IOException an IOException
   **/
  @Override
  public final long readLongDateTime() throws IOException {
    int byte1 = this.inputStream.read();
    int byte2 = this.inputStream.read();
    int byte3 = this.inputStream.read();
    int byte4 = this.inputStream.read();
    int byte5 = this.inputStream.read();
    int byte6 = this.inputStream.read();
    int byte7 = this.inputStream.read();
    int byte8 = this.inputStream.read();
    long lbyte1 = byte1;
    lbyte1 <<= 56;
    long lbyte2 = byte2;
    lbyte2 <<= 48;
    long lbyte3 = byte3;
    lbyte3 <<= 40;
    long lbyte4 = byte4;
    lbyte4 <<= 32;
    long lbyte5 = byte5;
    lbyte5 <<= 24;
    long lbyte6 = byte6;
    lbyte6 <<= 16;
    long lbyte7 = byte7;
    lbyte7 <<= 8;
    long lbyte8 = byte8;
    this.currentOffset += 8;
    return lbyte1 | lbyte2 | lbyte3 | lbyte4 | lbyte5
      | lbyte6 | lbyte7 | lbyte8;
  }

  /**
   * <p>Skip N bytes.</p>
   * @param pCount of bytes
   * @throws IOException an IOException
   **/
  @Override
  public final void skip(final int pCount) throws IOException {
    for (int i = 0; i < pCount; i++) {
      int byte1 = this.inputStream.read();
      if (byte1 == -1) {
        throw new IOException("Cant get to offsetTo/current: " + pCount
          + "/" + this.currentOffset);
      }
      this.currentOffset++;
    }
  }

  /**
   * <p>Go to offset.</p>
   * @param pOffset to go
   * @throws IOException an Exception, e.g. end of data
   **/
  @Override
  public final void goAhead(final long pOffset) throws IOException {
    if (this.currentOffset > pOffset) {
      throw new IOException("This offset has passed already requested/current: "
        + pOffset + "/" + this.currentOffset);
    }
    while (this.currentOffset < pOffset) {
      int rez = this.inputStream.read();
      if (rez == -1) {
        throw new IOException("Cant get to offsetTo/current: " + pOffset
          + "/" + this.currentOffset);
      }
      this.currentOffset++;
    }
  }

  /**
   * <p>Closed underlying stream.</p>
   * @throws IOException an IOException
   **/
  @Override
  public final void close() throws IOException {
    this.inputStream.close();
  }

  /**
   * <p>Getter for currentOffset.</p>
   * @return long
   **/
  @Override
  public final long getOffset() {
    return this.currentOffset;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for inputStream.</p>
   * @return InputStream
   **/
  public final InputStream getInputStream() {
    return this.inputStream;
  }
}

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

/**
 * <p>One way reader of TTF data from buffer byte[].
 * The buffer contains of full TTF table. To be simulate real file
 * there is offsetData - offset buffer from start of file
 * (same table reader for buffer and file).</p>
 *
 * @author Yury Demidenko
 */
public class TtfBufferInputStream implements ITtfInputStream {

  /**
   * <p>Current offset in buffer.</p>
   **/
  private long currentOffset = 0;

  /**
   * <p>Simulate offset data e.g. TTF table one from start of file.</p>
   **/
  private final long offsetData;

  /**
   * <p>Buffer.</p>
   **/
  private byte[] buffer;

  /**
   * <p>Only constructor.</p>
   * @param pBuffer Buffer
   * @param pOffsetData Offset Ttf Table
   **/
  public TtfBufferInputStream(final byte[] pBuffer,
    final long pOffsetData) {
    this.buffer = pBuffer;
    this.offsetData = pOffsetData;
  }

  /**
   * <p>Read byte in Java standard.</p>
   * @return read int
   * @throws IOException an IOException
   **/
  @Override
  public final int read() throws IOException {
    int byte1 = this.buffer[(int) this.currentOffset];
    byte1 &= 0x000000FF;
    this.currentOffset++;
    return byte1;
  }

  /**
   * <p>Read byte array.</p>
   * @param pBuffer byte array buffer
   * @return read total
   * @throws IOException an IOException
   **/
  @Override
  public final int read(final byte[] pBuffer) throws IOException {
    if (this.currentOffset + pBuffer.length > this.buffer.length - 1) {
      throw new IOException("Cant copy array array len/offset/buffer len: "
        + pBuffer.length + "/" + this.currentOffset + "/" + this.buffer.length);
    }
    for (int i = 0; i < pBuffer.length; i++) {
      pBuffer[i] = this.buffer[(int) this.currentOffset];
      this.currentOffset++;
    }
    return pBuffer.length;
  }

  /**
   * <p>Read char (2 bytes) or uint16.</p>
   * @return read int
   * @throws IOException an IOException
   **/
  @Override
  public final int readUInt16() throws IOException {
    int byte1 = this.buffer[(int) this.currentOffset];
    byte1 &= 0x000000FF;
    this.currentOffset++;
    byte1 <<= 8;
    int byte2 = this.buffer[(int) this.currentOffset];
    byte2 &= 0x000000FF;
    this.currentOffset++;
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
    int byte1 = this.buffer[(int) this.currentOffset];
    byte1 &= 0x000000FF;
    this.currentOffset++;
    int byte2 = this.buffer[(int) this.currentOffset];
    byte2 &= 0x000000FF;
    this.currentOffset++;
    int byte3 = this.buffer[(int) this.currentOffset];
    byte3 &= 0x000000FF;
    this.currentOffset++;
    int byte4 = this.buffer[(int) this.currentOffset];
    byte4 &= 0x000000FF;
    this.currentOffset++;
    long lbyte1 = byte1;
    lbyte1 <<= 24;
    long lbyte2 = byte2 << 16;
    long lbyte3 = byte3 << 8;
    long lbyte4 = byte4;
    return lbyte1 | lbyte2 | lbyte3 | lbyte4;
  }

  /**
   * <p>Read table Tag (4 bytes).</p>
   * @return read byte[4]
   * @throws IOException an IOException
   **/
  @Override
  public final byte[] readTag() throws IOException {
    int byte1 = this.buffer[(int) this.currentOffset];
    byte1 &= 0x000000FF;
    this.currentOffset++;
    int byte2 = this.buffer[(int) this.currentOffset];
    byte2 &= 0x000000FF;
    this.currentOffset++;
    int byte3 = this.buffer[(int) this.currentOffset];
    byte3 &= 0x000000FF;
    this.currentOffset++;
    int byte4 = this.buffer[(int) this.currentOffset];
    byte4 &= 0x000000FF;
    this.currentOffset++;
    byte[] result = new byte[4];
    result[0] = (byte) byte1;
    result[1] = (byte) byte2;
    result[2] = (byte) byte3;
    result[3] = (byte) byte4;
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
    int byte1 = this.buffer[(int) this.currentOffset];
    byte1 &= 0x000000FF;
    this.currentOffset++;
    int byte2 = this.buffer[(int) this.currentOffset];
    byte2 &= 0x000000FF;
    this.currentOffset++;
    int byte3 = this.buffer[(int) this.currentOffset];
    byte3 &= 0x000000FF;
    this.currentOffset++;
    int byte4 = this.buffer[(int) this.currentOffset];
    byte4 &= 0x000000FF;
    this.currentOffset++;
    int byte5 = this.buffer[(int) this.currentOffset];
    byte5 &= 0x000000FF;
    this.currentOffset++;
    int byte6 = this.buffer[(int) this.currentOffset];
    byte6 &= 0x000000FF;
    this.currentOffset++;
    int byte7 = this.buffer[(int) this.currentOffset];
    byte7 &= 0x000000FF;
    this.currentOffset++;
    int byte8 = this.buffer[(int) this.currentOffset];
    byte8 &= 0x000000FF;
    this.currentOffset++;
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
    if (this.currentOffset + pCount > this.buffer.length - 1) {
      throw new IOException("Cant skip count/offset/buffer len: "
        + pCount + "/" + this.currentOffset + "/" + this.buffer.length);
    }
    this.currentOffset += pCount;
  }

  /**
   * <p>Go to offset.</p>
   * @param pOffset to go
   * @throws IOException an Exception, e.g. end of data
   **/
  @Override
  public final void goAhead(final long pOffset) throws IOException {
    if (this.offsetData + this.currentOffset > pOffset) {
      throw new IOException("This offset has passed already requested/current: "
        + pOffset + "/" + (this.offsetData + this.currentOffset));
    }
    if (pOffset - this.offsetData > this.buffer.length - 1) {
      throw new IOException("Cant get to offsetTo/current/buffer len: "
        + pOffset + "/" + (this.offsetData + this.currentOffset)
          + "/" + this.buffer.length);
    }
    this.currentOffset = pOffset - this.offsetData;
  }

  /**
   * <p>End of data reading.</p>
   * @throws IOException an IOException
   **/
  @Override
  public final void close() throws IOException {
    this.currentOffset = 0L;
  }

  /**
   * <p>Getter for currentOffset.</p>
   * @return long
   **/
  @Override
  public final long getOffset() {
    return this.offsetData + this.currentOffset;
  }

  //Simple getters and setters:
  /**
   * <p>Setter for currentOffset (reset start).</p>
   * @param pCurrentOffset reference
   **/
  public final void setCurrentOffset(final long pCurrentOffset) {
    this.currentOffset = pCurrentOffset;
  }

  /**
   * <p>Getter for offsetData.</p>
   * @return long
   **/
  public final long getOffsetData() {
    return this.offsetData;
  }

  /**
   * <p>Getter for buffer.</p>
   * @return byte[]
   **/
  public final byte[] getBuffer() {
    return this.buffer;
  }
}

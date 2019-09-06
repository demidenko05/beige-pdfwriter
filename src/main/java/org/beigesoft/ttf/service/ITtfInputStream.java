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

import java.io.Closeable;
import java.io.IOException;

/**
 * <p>One way reader of TTF data from input stream.</p>
 *
 * @author Yury Demidenko
 */
public interface ITtfInputStream extends Closeable {

  /**
   * <p>Read byte in Java standard.</p>
   * @return read int
   * @throws IOException an IOException
   **/
  int read() throws IOException;

  /**
   * <p>Read byte array in Java standard.</p>
   * @param pBuffer byte array buffer
   * @return read total
   * @throws IOException an IOException
   **/
  int read(byte[] pBuffer) throws IOException;
  /**
   * <p>Read char (2 bytes) or uint16.</p>
   * @return read int
   * @throws IOException an IOException
   **/
  int readUInt16() throws IOException;

  /**
   * <p>Read char (2 bytes) or uint16.</p>
   * @return read char
   * @throws IOException an IOException
   **/
  char readChar() throws IOException;

  /**
   * <p>Read 16-bit signed integer that describes a quantity in FUnits,
   * the smallest measurable distance in em space.</p>
   * @return read short
   * @throws IOException an IOException
   **/
  short readFWord() throws IOException;

  /**
   * <p>16-bit unsigned integer that describes a quantity in FUnits,
   * the smallest measurable distance in em space.</p>
   * @return read int
   * @throws IOException an IOException
   **/
  int readUFWord() throws IOException;

  /**
   * <p>Read short (2 bytes) or sint16.</p>
   * @return read short
   * @throws IOException an IOException
   **/
  short readSInt16() throws IOException;

  /**
   * <p>Read unsigned byte flag.</p>
   * @return read int (ubyte)
   * @throws IOException an IOException
   **/
  int readUInt8() throws IOException;

  /**
   * <p>Read byte.</p>
   * @return read byte
   * @throws IOException an IOException
   **/
  byte readByte() throws IOException;

  /**
   * <p>Read char (4 bytes) or fixed 16.16.</p>
   * @return read char
   * @throws IOException an IOException
   **/
  float readFixed() throws IOException;

  /**
   * <p>Read unsigned int (4 bytes)
   * to red uint32 .</p>
   * @return read int
   * @throws IOException an IOException
   **/
  long readUInt32() throws IOException;

  /**
   * <p>Read table Tag (4 bytes).</p>
   * @return read byte[4]
   * @throws IOException an IOException
   **/
  byte[] readTag() throws IOException;

  /**
   * <p>Read UInt16 array.</p>
   * @param pCount of UInt16
   * @return read arrUInt16[pCount]
   * @throws IOException an IOException
   **/
  int[] readUInt16Arr(int pCount) throws IOException;

  /**
   * <p>Read UInt8 flags array.</p>
   * @param pCount of UInt8
   * @return read short arrUInt8[pCount]
   * @throws IOException an IOException
   **/
  int[] readUInt8Arr(int pCount) throws IOException;

  /**
   * <p>Read long (8 bytes)
   * longDateTime The long internal format of a date in seconds
   * since 12:00 midnight, January 1, 1904.
   * It is represented as a signed 64-bit integer.</p>
   * @return read long
   * @throws IOException an IOException
   **/
  long readLongDateTime() throws IOException;

  /**
   * <p>Skip N bytes.</p>
   * @param pCount of bytes
   * @throws IOException an IOException
   **/
  void skip(int pCount) throws IOException;

  /**
   * <p>Go to offset ahead.</p>
   * @param pOffset to go
   * @throws IOException an Exception, e.g. end of data
   **/
  void goAhead(long pOffset) throws IOException;

  /**
   * <p>Get for current Offset.</p>
   * @return long
   **/
  long getOffset();
}

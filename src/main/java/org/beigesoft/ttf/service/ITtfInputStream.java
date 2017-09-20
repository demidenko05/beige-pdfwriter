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
  int[] readUInt16Arr(final int pCount) throws IOException;

  /**
   * <p>Read UInt8 flags array.</p>
   * @param pCount of UInt8
   * @return read short arrUInt8[pCount]
   * @throws IOException an IOException
   **/
  int[] readUInt8Arr(final int pCount) throws IOException;

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
  void skip(final int pCount) throws IOException;

  /**
   * <p>Go to offset ahead.</p>
   * @param pOffset to go
   * @throws IOException an Exception, e.g. end of data
   **/
  void goAhead(final long pOffset) throws IOException;

  /**
   * <p>Get for current Offset.</p>
   * @return long
   **/
  long getOffset();
}

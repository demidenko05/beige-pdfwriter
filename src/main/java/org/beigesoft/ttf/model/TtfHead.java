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

package org.beigesoft.ttf.model;

/**
 * <p>TTF head table model.</p>
 *
 * @author Yury Demidenko
 */
public class TtfHead {

  /**
   * <p>unitsPerEm.</p>
   **/
  private int unitsPerEm;

  /**
   * <p>xMin for all glyph bounding boxes.</p>
   **/
  private short xMin;

  /**
   * <p>yMin for all glyph bounding boxes.</p>
   **/
  private short yMin;

  /**
   * <p>xMax for all glyph bounding boxes.</p>
   **/
  private short xMax;

  /**
   * <p>yMax for all glyph bounding boxes.</p>
   **/
  private short yMax;

  /**
   * <p>indexToLocFormat > 0 for 32 bit loca offset.</p>
   **/
  private short indexToLocFormat;

  /**
   * <p>Head byte array 4*4 + 2:
   * Fixed version
   * Fixed fontRevision
   * UInt32 checksumAdjustment
   * UInt32 magicNumber
   * UInt16 flags.</p>
   **/
  private final byte[] headByteArr = new byte[18];

  /**
   * <p> buffer for LongDateTime created
   * LongDateTime modified.</p>
   **/
  private final byte[] createdModifiedBuf = new byte[16];

  /**
   * <p> buffer for UInt16 macStyle
   * readUInt16 lowestRecPPEM
   * SInt16 fontDirectionHint.</p>
   **/
  private final byte[] msLrpFdhBuf = new byte[6];

  /**
   * <p> buffer for SInt16 glyphDataFormat.</p>
   **/
  private byte[] glyphDataFormatBuf = new byte[2];

  /**
   * <p>Its TDE.</p>
   **/
  private final TtfTableDirEntry tableDirEntry;

  /**
   * <p>Only constractor.</p>
   * @param pTableDirEntry reference
   **/
  public TtfHead(final TtfTableDirEntry pTableDirEntry) {
    this.tableDirEntry = pTableDirEntry;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for unitsPerEm.</p>
   * @return int
   **/
  public final int getUnitsPerEm() {
    return this.unitsPerEm;
  }

  /**
   * <p>Setter for unitsPerEm.</p>
   * @param pUnitsPerEm reference
   **/
  public final void setUnitsPerEm(final int pUnitsPerEm) {
    this.unitsPerEm = pUnitsPerEm;
  }

  /**
   * <p>Getter for xMin.</p>
   * @return short
   **/
  public final short getXMin() {
    return this.xMin;
  }

  /**
   * <p>Setter for xMin.</p>
   * @param pXMin reference
   **/
  public final void setXMin(final short pXMin) {
    this.xMin = pXMin;
  }

  /**
   * <p>Getter for yMin.</p>
   * @return short
   **/
  public final short getYMin() {
    return this.yMin;
  }

  /**
   * <p>Setter for yMin.</p>
   * @param pYMin reference
   **/
  public final void setYMin(final short pYMin) {
    this.yMin = pYMin;
  }

  /**
   * <p>Getter for xMax.</p>
   * @return short
   **/
  public final short getXMax() {
    return this.xMax;
  }

  /**
   * <p>Setter for xMax.</p>
   * @param pXMax reference
   **/
  public final void setXMax(final short pXMax) {
    this.xMax = pXMax;
  }

  /**
   * <p>Getter for yMax.</p>
   * @return short
   **/
  public final short getYMax() {
    return this.yMax;
  }

  /**
   * <p>Setter for yMax.</p>
   * @param pYMax reference
   **/
  public final void setYMax(final short pYMax) {
    this.yMax = pYMax;
  }

  /**
   * <p>Getter for indexToLocFormat.</p>
   * @return short
   **/
  public final short getIndexToLocFormat() {
    return this.indexToLocFormat;
  }

  /**
   * <p>Setter for indexToLocFormat.</p>
   * @param pIndexToLocFormat reference
   **/
  public final void setIndexToLocFormat(final short pIndexToLocFormat) {
    this.indexToLocFormat = pIndexToLocFormat;
  }

  /**
   * <p>Getter for headByteArr.</p>
   * @return byte[]
   **/
  public final byte[] getHeadByteArr() {
    return this.headByteArr;
  }

  /**
   * <p>Getter for createdModifiedBuf.</p>
   * @return byte[]
   **/
  public final byte[] getCreatedModifiedBuf() {
    return this.createdModifiedBuf;
  }
  /**
   * <p>Getter for msLrpFdhBuf.</p>
   * @return byte[]
   **/
  public final byte[] getMsLrpFdhBuf() {
    return this.msLrpFdhBuf;
  }

  /**
   * <p>Getter for glyphDataFormatBuf.</p>
   * @return byte[]
   **/
  public final byte[] getGlyphDataFormatBuf() {
    return this.glyphDataFormatBuf;
  }

  /**
   * <p>Getter for tableDirEntry.</p>
   * @return TtfTableDirEntry
   **/
  public final TtfTableDirEntry getTableDirEntry() {
    return this.tableDirEntry;
  }
}

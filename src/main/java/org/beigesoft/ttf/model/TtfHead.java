package org.beigesoft.ttf.model;

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

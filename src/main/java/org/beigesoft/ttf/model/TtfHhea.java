package org.beigesoft.ttf.model;

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


/**
 * <p>TTF hhea table model.</p>
 *
 * @author Yury Demidenko
 */
public class TtfHhea {

  /**
   * <p>ascent.</p>
   **/
  private short ascent;

  /**
   * <p>descent.</p>
   **/
  private short descent;

  /**
   * <p>lineGap.</p>
   **/
  private short lineGap;

  /**
   * <p>advanceWidthMax.</p>
   **/
  private int advanceWidthMax;

  /**
   * <p>minLeftSideBearing.</p>
   **/
  private short minLeftSideBearing;

  /**
   * <p>minRightSideBearing.</p>
   **/
  private short minRightSideBearing;

  /**
   * <p>xMaxExtent.</p>
   **/
  private short xMaxExtent;

  /**
   * <p>caretSlopeRise.</p>
   **/
  private short caretSlopeRise;

  /**
   * <p>caretSlopeRun.</p>
   **/
  private short caretSlopeRun;

  /**
   * <p>caretOffset.</p>
   **/
  private short caretOffset;

  /**
   * <p>metricDataFormat.</p>
   **/
  private short metricDataFormat;

  /**
   * <p>numOfLongHorMetrics.</p>
   **/
  private int numOfLongHorMetrics;

  /**
   * <p>Version buffer Fixed.</p>
   **/
  private byte[] version = new byte[4];

  /**
   * <p>Reserved buffer 4 SInt16.</p>
   **/
  private byte[] reserved = new byte[8];

  /**
   * <p>Its TDE.</p>
   **/
  private final TtfTableDirEntry tableDirEntry;

  /**
   * <p>Only constractor.</p>
   * @param pTableDirEntry reference
   **/
  public TtfHhea(final TtfTableDirEntry pTableDirEntry) {
    this.tableDirEntry = pTableDirEntry;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for tableDirEntry.</p>
   * @return TtfTableDirEntry
   **/
  public final TtfTableDirEntry getTableDirEntry() {
    return this.tableDirEntry;
  }

  /**
   * <p>Getter for ascent.</p>
   * @return short
   **/
  public final short getAscent() {
    return this.ascent;
  }

  /**
   * <p>Setter for ascent.</p>
   * @param pAscent reference
   **/
  public final void setAscent(final short pAscent) {
    this.ascent = pAscent;
  }

  /**
   * <p>Getter for descent.</p>
   * @return short
   **/
  public final short getDescent() {
    return this.descent;
  }

  /**
   * <p>Setter for descent.</p>
   * @param pDescent reference
   **/
  public final void setDescent(final short pDescent) {
    this.descent = pDescent;
  }

  /**
   * <p>Getter for lineGap.</p>
   * @return short
   **/
  public final short getLineGap() {
    return this.lineGap;
  }

  /**
   * <p>Setter for lineGap.</p>
   * @param pLineGap reference
   **/
  public final void setLineGap(final short pLineGap) {
    this.lineGap = pLineGap;
  }

  /**
   * <p>Getter for advanceWidthMax.</p>
   * @return int
   **/
  public final int getAdvanceWidthMax() {
    return this.advanceWidthMax;
  }

  /**
   * <p>Setter for advanceWidthMax.</p>
   * @param pAdvanceWidthMax reference
   **/
  public final void setAdvanceWidthMax(final int pAdvanceWidthMax) {
    this.advanceWidthMax = pAdvanceWidthMax;
  }

  /**
   * <p>Getter for minLeftSideBearing.</p>
   * @return short
   **/
  public final short getMinLeftSideBearing() {
    return this.minLeftSideBearing;
  }

  /**
   * <p>Setter for minLeftSideBearing.</p>
   * @param pMinLeftSideBearing reference
   **/
  public final void setMinLeftSideBearing(final short pMinLeftSideBearing) {
    this.minLeftSideBearing = pMinLeftSideBearing;
  }

  /**
   * <p>Getter for minRightSideBearing.</p>
   * @return short
   **/
  public final short getMinRightSideBearing() {
    return this.minRightSideBearing;
  }

  /**
   * <p>Setter for minRightSideBearing.</p>
   * @param pMinRightSideBearing reference
   **/
  public final void setMinRightSideBearing(final short pMinRightSideBearing) {
    this.minRightSideBearing = pMinRightSideBearing;
  }

  /**
   * <p>Getter for xMaxExtent.</p>
   * @return short
   **/
  public final short getXMaxExtent() {
    return this.xMaxExtent;
  }

  /**
   * <p>Setter for xMaxExtent.</p>
   * @param pXMaxExtent reference
   **/
  public final void setXMaxExtent(final short pXMaxExtent) {
    this.xMaxExtent = pXMaxExtent;
  }

  /**
   * <p>Getter for caretSlopeRise.</p>
   * @return short
   **/
  public final short getCaretSlopeRise() {
    return this.caretSlopeRise;
  }

  /**
   * <p>Setter for caretSlopeRise.</p>
   * @param pCaretSlopeRise reference
   **/
  public final void setCaretSlopeRise(final short pCaretSlopeRise) {
    this.caretSlopeRise = pCaretSlopeRise;
  }

  /**
   * <p>Getter for caretSlopeRun.</p>
   * @return short
   **/
  public final short getCaretSlopeRun() {
    return this.caretSlopeRun;
  }

  /**
   * <p>Setter for caretSlopeRun.</p>
   * @param pCaretSlopeRun reference
   **/
  public final void setCaretSlopeRun(final short pCaretSlopeRun) {
    this.caretSlopeRun = pCaretSlopeRun;
  }

  /**
   * <p>Getter for caretOffset.</p>
   * @return short
   **/
  public final short getCaretOffset() {
    return this.caretOffset;
  }

  /**
   * <p>Setter for caretOffset.</p>
   * @param pCaretOffset reference
   **/
  public final void setCaretOffset(final short pCaretOffset) {
    this.caretOffset = pCaretOffset;
  }

  /**
   * <p>Getter for metricDataFormat.</p>
   * @return short
   **/
  public final short getMetricDataFormat() {
    return this.metricDataFormat;
  }

  /**
   * <p>Setter for metricDataFormat.</p>
   * @param pMetricDataFormat reference
   **/
  public final void setMetricDataFormat(final short pMetricDataFormat) {
    this.metricDataFormat = pMetricDataFormat;
  }

  /**
   * <p>Getter for numOfLongHorMetrics.</p>
   * @return int
   **/
  public final int getNumOfLongHorMetrics() {
    return this.numOfLongHorMetrics;
  }

  /**
   * <p>Setter for numOfLongHorMetrics.</p>
   * @param pNumOfLongHorMetrics reference
   **/
  public final void setNumOfLongHorMetrics(final int pNumOfLongHorMetrics) {
    this.numOfLongHorMetrics = pNumOfLongHorMetrics;
  }

  /**
   * <p>Getter for version.</p>
   * @return byte[]
   **/
  public final byte[] getVersion() {
    return this.version;
  }

  /**
   * <p>Setter for version.</p>
   * @param pVersion reference
   **/
  public final void setVersion(final byte[] pVersion) {
    this.version = pVersion;
  }

  /**
   * <p>Getter for reserved.</p>
   * @return byte[]
   **/
  public final byte[] getReserved() {
    return this.reserved;
  }

  /**
   * <p>Setter for reserved.</p>
   * @param pReserved reference
   **/
  public final void setReserved(final byte[] pReserved) {
    this.reserved = pReserved;
  }
}

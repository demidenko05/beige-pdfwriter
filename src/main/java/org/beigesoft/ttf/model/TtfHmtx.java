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
 * <p>TTF hmtx table model.</p>
 *
 * @author Yury Demidenko
 */
public class TtfHmtx {

  /**
   * <p>width array.</p>
   **/
  private char[] widths;

  /**
   * <p>Primary leftSideBearing array (bounded with widths).</p>
   **/
  private short[] leftSideBearing;

  /**
   * <p>Additional leftSideBearing array.</p>
   **/
  private short[] leftSideBearingAdd;

  /**
   * <p>Its TDE.</p>
   **/
  private final TtfTableDirEntry tableDirEntry;

  /**
   * <p>Only constractor.</p>
   * @param pTableDirEntry reference
   **/
  public TtfHmtx(final TtfTableDirEntry pTableDirEntry) {
    this.tableDirEntry = pTableDirEntry;
  }

  //Utils:
  /**
   * <p>Get width for GID.</p>
   * @param pGid gid
   * @return width
   **/
  public final char getWidthForGid(final char pGid) {
    if (pGid > this.widths.length - 1) {
      // master width glyph:
      return this.widths[this.widths.length - 1];
    }
    return this.widths[pGid];
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
   * <p>Getter for widths.</p>
   * @return char[]
   **/
  public final char[] getWidths() {
    return this.widths;
  }

  /**
   * <p>Setter for widths.</p>
   * @param pWidths reference
   **/
  public final void setWidths(final char[] pWidths) {
    this.widths = pWidths;
  }

  /**
   * <p>Getter for leftSideBearing.</p>
   * @return short[]
   **/
  public final short[] getLeftSideBearing() {
    return this.leftSideBearing;
  }

  /**
   * <p>Setter for leftSideBearing.</p>
   * @param pLeftSideBearing reference
   **/
  public final void setLeftSideBearing(final short[] pLeftSideBearing) {
    this.leftSideBearing = pLeftSideBearing;
  }

  /**
   * <p>Getter for leftSideBearingAdd.</p>
   * @return short[]
   **/
  public final short[] getLeftSideBearingAdd() {
    return this.leftSideBearingAdd;
  }

  /**
   * <p>Setter for leftSideBearingAdd.</p>
   * @param pLeftSideBearingAdd reference
   **/
  public final void setLeftSideBearingAdd(final short[] pLeftSideBearingAdd) {
    this.leftSideBearingAdd = pLeftSideBearingAdd;
  }
}

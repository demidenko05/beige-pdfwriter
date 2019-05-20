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
      // master monospace width glyph:
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

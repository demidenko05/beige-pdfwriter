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
 * <p>TTF OS2 table model.</p>
 *
 * @author Yury Demidenko
 */
public class TtfOs2 {

  /**
   * <p>version.</p>
   **/
  private int version;

  /**
   * <p>xAvgCharWidth.</p>
   **/
  private short xAvgCharWidth;

  /**
   * <p>usWeightClass.</p>
   **/
  private int usWeightClass;

  /**
   * <p>usWidthClass.</p>
   **/
  private int usWidthClass;

  /**
   * <p>sFamilyClass:
   * <pre>
   * 1 OldStyle Serifs
   * 2 Transitional Serifs
   * 3 Modern Serifs
   * 4 Clarendon Serifs
   * 5 Slab Serifs
   * 7 Freeform Serifs
   * 10 Scripts.
   * </pre>
   * </p>
   **/
  private short sFamilyClass;

  /**
   * <p>fsSelection bit 0=ITALIC.</p>
   **/
  private int fsSelection;

  /**
   * <p>sxHeight.</p>
   **/
  private short sxHeight = 0;

  /**
   * <p>sCapHeight.</p>
   **/
  private short sCapHeight = 0;

  /**
   * <p>Its TDE.</p>
   **/
  private final TtfTableDirEntry tableDirEntry;

  /**
   * <p>Only constractor.</p>
   * @param pTableDirEntry reference
   **/
  public TtfOs2(final TtfTableDirEntry pTableDirEntry) {
    this.tableDirEntry = pTableDirEntry;
  }

  //Simple getters and setters:

  /**
   * <p>Getter for version.</p>
   * @return int
   **/
  public final int getVersion() {
    return this.version;
  }

  /**
   * <p>Setter for version.</p>
   * @param pVersion reference
   **/
  public final void setVersion(final int pVersion) {
    this.version = pVersion;
  }

  /**
   * <p>Getter for xAvgCharWidth.</p>
   * @return short
   **/
  public final short getXAvgCharWidth() {
    return this.xAvgCharWidth;
  }

  /**
   * <p>Setter for xAvgCharWidth.</p>
   * @param pXAvgCharWidth reference
   **/
  public final void setXAvgCharWidth(final short pXAvgCharWidth) {
    this.xAvgCharWidth = pXAvgCharWidth;
  }

  /**
   * <p>Getter for usWeightClass.</p>
   * @return int
   **/
  public final int getUsWeightClass() {
    return this.usWeightClass;
  }

  /**
   * <p>Setter for usWeightClass.</p>
   * @param pUsWeightClass reference
   **/
  public final void setUsWeightClass(final int pUsWeightClass) {
    this.usWeightClass = pUsWeightClass;
  }

  /**
   * <p>Getter for usWidthClass.</p>
   * @return int
   **/
  public final int getUsWidthClass() {
    return this.usWidthClass;
  }

  /**
   * <p>Setter for usWidthClass.</p>
   * @param pUsWidthClass reference
   **/
  public final void setUsWidthClass(final int pUsWidthClass) {
    this.usWidthClass = pUsWidthClass;
  }

  /**
   * <p>Getter for sFamilyClass.</p>
   * @return short
   **/
  public final short getSFamilyClass() {
    return this.sFamilyClass;
  }

  /**
   * <p>Setter for sFamilyClass.</p>
   * @param pSFamilyClass reference
   **/
  public final void setSFamilyClass(final short pSFamilyClass) {
    this.sFamilyClass = pSFamilyClass;
  }

  /**
   * <p>Getter for fsSelection.</p>
   * @return int
   **/
  public final int getFsSelection() {
    return this.fsSelection;
  }

  /**
   * <p>Setter for fsSelection.</p>
   * @param pFsSelection reference
   **/
  public final void setFsSelection(final int pFsSelection) {
    this.fsSelection = pFsSelection;
  }

  /**
   * <p>Getter for sxHeight.</p>
   * @return short
   **/
  public final short getSxHeight() {
    return this.sxHeight;
  }

  /**
   * <p>Setter for sxHeight.</p>
   * @param pSxHeight reference
   **/
  public final void setSxHeight(final short pSxHeight) {
    this.sxHeight = pSxHeight;
  }

  /**
   * <p>Getter for sCapHeight.</p>
   * @return short
   **/
  public final short getSCapHeight() {
    return this.sCapHeight;
  }

  /**
   * <p>Setter for sCapHeight.</p>
   * @param pSCapHeight reference
   **/
  public final void setSCapHeight(final short pSCapHeight) {
    this.sCapHeight = pSCapHeight;
  }

  /**
   * <p>Getter for tableDirEntry.</p>
   * @return TtfTableDirEntry
   **/
  public final TtfTableDirEntry getTableDirEntry() {
    return this.tableDirEntry;
  }
}

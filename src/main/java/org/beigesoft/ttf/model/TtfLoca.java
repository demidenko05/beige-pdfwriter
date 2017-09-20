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
 * <p>TTF loca table model.</p>
 *
 * @author Yury Demidenko
 */
public class TtfLoca {

  /**
   * <p>16 bit offsets table.</p>
   **/
  private char[] offsets16;

  /**
   * <p>32 bit offsets table.</p>
   **/
  private long[] offsets32;

  /**
   * <p>Its TDE.</p>
   **/
  private final TtfTableDirEntry tableDirEntry;

  /**
   * <p>Only constractor.</p>
   * @param pTableDirEntry reference
   **/
  public TtfLoca(final TtfTableDirEntry pTableDirEntry) {
    this.tableDirEntry = pTableDirEntry;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for offsets16.</p>
   * @return char[]
   **/
  public final char[] getOffsets16() {
    return this.offsets16;
  }

  /**
   * <p>Setter for offsets16.</p>
   * @param pOffsets16 reference
   **/
  public final void setOffsets16(final char[] pOffsets16) {
    this.offsets16 = pOffsets16;
  }

  /**
   * <p>Getter for offsets32.</p>
   * @return long[]
   **/
  public final long[] getOffsets32() {
    return this.offsets32;
  }

  /**
   * <p>Setter for offsets32.</p>
   * @param pOffsets32 reference
   **/
  public final void setOffsets32(final long[] pOffsets32) {
    this.offsets32 = pOffsets32;
  }

  /**
   * <p>Getter for tableDirEntry.</p>
   * @return TtfTableDirEntry
   **/
  public final TtfTableDirEntry getTableDirEntry() {
    return this.tableDirEntry;
  }
}

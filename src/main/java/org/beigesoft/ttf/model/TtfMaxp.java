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
 * <p>TTF maxp table model.</p>
 *
 * @author Yury Demidenko
 */
public class TtfMaxp {

  /**
   * <p>Version buffer Fixed.</p>
   **/
  private byte[] version = new byte[4];

  /**
   * <p>numGlyphs UInt16.</p>
   **/
  private int numGlyphs;

  /**
   * <p>Maxp.length - 6 tail.</p>
   **/
  private byte[] tail;

  /**
   * <p>Its TDE.</p>
   **/
  private final TtfTableDirEntry tableDirEntry;

  /**
   * <p>Only constructor.</p>
   * @param pTableDirEntry reference
   **/
  public TtfMaxp(final TtfTableDirEntry pTableDirEntry) {
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
   * <p>Getter for numGlyphs.</p>
   * @return int
   **/
  public final int getNumGlyphs() {
    return this.numGlyphs;
  }

  /**
   * <p>Setter for numGlyphs.</p>
   * @param pNumGlyphs reference
   **/
  public final void setNumGlyphs(final int pNumGlyphs) {
    this.numGlyphs = pNumGlyphs;
  }

  /**
   * <p>Getter for tail.</p>
   * @return byte[]
   **/
  public final byte[] getTail() {
    return this.tail;
  }

  /**
   * <p>Setter for tail.</p>
   * @param pTail reference
   **/
  public final void setTail(final byte[] pTail) {
    this.tail = pTail;
  }
}

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

import java.util.Map;
import java.util.List;

/**
 * <p>TTF CMAP model.</p>
 *
 * @author Yury Demidenko
 */
public class TtfCmap {

  /**
   * <p>Number of subtables.</p>
   **/
  private int numSubTables;

  /**
   * <p>Revealed Unicode to CID map.</p>
   **/
  private Map<Character, Character> uniToCid;

  /**
   * <p>Subtables.</p>
   **/
  private List<TtfCmapSubtable> subtables;

  /**
   * <p>Its TDE.</p>
   **/
  private final TtfTableDirEntry tableDirEntry;

  /**
   * <p>Only constractor.</p>
   * @param pTableDirEntry reference
   **/
  public TtfCmap(final TtfTableDirEntry pTableDirEntry) {
    this.tableDirEntry = pTableDirEntry;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for numSubTables.</p>
   * @return int
   **/
  public final int getNumSubTables() {
    return this.numSubTables;
  }

  /**
   * <p>Setter for numSubTables.</p>
   * @param pNumSubTables reference
   **/
  public final void setNumSubTables(final int pNumSubTables) {
    this.numSubTables = pNumSubTables;
  }

  /**
   * <p>Getter for uniToCid.</p>
   * @return Map<Character, Character>
   **/
  public final Map<Character, Character> getUniToCid() {
    return this.uniToCid;
  }

  /**
   * <p>Setter for uniToCid.</p>
   * @param pUniToCid reference
   **/
  public final void setUniToCid(final Map<Character, Character> pUniToCid) {
    this.uniToCid = pUniToCid;
  }

  /**
   * <p>Getter for subtables.</p>
   * @return List<TtfCmapSubtable>
   **/
  public final List<TtfCmapSubtable> getSubtables() {
    return this.subtables;
  }

  /**
   * <p>Setter for subtables.</p>
   * @param pSubtables reference
   **/
  public final void setSubtables(final List<TtfCmapSubtable> pSubtables) {
    this.subtables = pSubtables;
  }

  /**
   * <p>Getter for tableDirEntry.</p>
   * @return TtfTableDirEntry
   **/
  public final TtfTableDirEntry getTableDirEntry() {
    return this.tableDirEntry;
  }
}

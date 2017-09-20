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

import org.beigesoft.ttf.service.ITdeMaker;
import org.beigesoft.ttf.service.ITableMaker;

/**
 * <p>TTF table required for font embedding model.</p>
 *
 * @param <T> table type
 * @author Yury Demidenko
 */
public class TableForEmbedding<T extends TableForEmbedding> {

  //Data (immutable) to make TDE:
  /**
   * <p>Is length same. If length same but content will be changed
   * it's used to quick make offset for changed data.</p>
   **/
  private final boolean isLengthSame;

  /**
   * <p>Is checksum same.</p>
   **/
  private final boolean isChecksumSame;

  /**
   * <p>Its source TDE.</p>
   **/
  private final TtfTableDirEntry sourceTde;

  //Memory friendly delegates,
  //they encapsulate methods but not created for every object:
  /**
   * <p>TDE maker.</p>
   **/
  private ITdeMaker tdeMaker;

  /**
   * <p>Table maker.</p>
   **/
  private ITableMaker<T> tableMaker;

  /**
   * <p>Only constructor.</p>
   * @param pTableDirEntry reference
   * @param pIsLengthSame value
   * @param pIsChecksumSame value
   **/
  public TableForEmbedding(final TtfTableDirEntry pTableDirEntry,
    final boolean pIsLengthSame, final boolean pIsChecksumSame) {
    this.sourceTde = pTableDirEntry;
    this.isLengthSame = pIsLengthSame;
    this.isChecksumSame = pIsChecksumSame;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for isLengthSame.</p>
   * @return boolean
   **/
  public final boolean getIsLengthSame() {
    return this.isLengthSame;
  }

  /**
   * <p>Getter for isChecksumSame.</p>
   * @return boolean
   **/
  public final boolean getIsChecksumSame() {
    return this.isChecksumSame;
  }

  /**
   * <p>Getter for .</p>
   * @return TtfTableDirEntry
   **/
  public final TtfTableDirEntry getSourceTde() {
    return this.sourceTde;
  }

  /**
   * <p>Getter for tdeMaker.</p>
   * @return ITdeMaker
   **/
  public final ITdeMaker getTdeMaker() {
    return this.tdeMaker;
  }

  /**
   * <p>Setter for tdeMaker.</p>
   * @param pTdeMaker reference
   **/
  public final void setTdeMaker(final ITdeMaker pTdeMaker) {
    this.tdeMaker = pTdeMaker;
  }

  /**
   * <p>Getter for tableMaker.</p>
   * @return ITableMaker
   **/
  public final ITableMaker<T> getTableMaker() {
    return this.tableMaker;
  }

  /**
   * <p>Setter for tableMaker.</p>
   * @param pTableMaker reference
   **/
  public final void setTableMaker(final ITableMaker<T> pTableMaker) {
    this.tableMaker = pTableMaker;
  }
}

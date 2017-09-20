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
 * <p>TTF post table model.</p>
 *
 * @author Yury Demidenko
 */
public class TtfPost {

  /**
   * <p>italicAngle.</p>
   **/
  private float italicAngle;

  /**
   * <p>isFixedPitch.</p>
   **/
  private boolean isFixedPitch;

  /**
   * <p>Its TDE.</p>
   **/
  private final TtfTableDirEntry tableDirEntry;

  /**
   * <p>Only constractor.</p>
   * @param pTableDirEntry reference
   **/
  public TtfPost(final TtfTableDirEntry pTableDirEntry) {
    this.tableDirEntry = pTableDirEntry;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for italicAngle.</p>
   * @return float
   **/
  public final float getItalicAngle() {
    return this.italicAngle;
  }

  /**
   * <p>Setter for italicAngle.</p>
   * @param pItalicAngle reference
   **/
  public final void setItalicAngle(final float pItalicAngle) {
    this.italicAngle = pItalicAngle;
  }

  /**
   * <p>Getter for isFixedPitch.</p>
   * @return boolean
   **/
  public final boolean getIsFixedPitch() {
    return this.isFixedPitch;
  }

  /**
   * <p>Setter for isFixedPitch.</p>
   * @param pIsFixedPitch reference
   **/
  public final void setIsFixedPitch(final boolean pIsFixedPitch) {
    this.isFixedPitch = pIsFixedPitch;
  }

  /**
   * <p>Getter for tableDirEntry.</p>
   * @return TtfTableDirEntry
   **/
  public final TtfTableDirEntry getTableDirEntry() {
    return this.tableDirEntry;
  }
}

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
 * <p>TTF CMAP subtable model.</p>
 *
 * @author Yury Demidenko
 */
public class TtfCmapSubtable {

  /**
   * <p>platform ID.</p>
   **/
  private int platformId;

  /**
   * <p>platform specific ID.</p>
   **/
  private int platformSpecificId;

  /**
   * <p>Offset.</p>
   **/
  private long offset;

  //Simple getters and setters:
  /**
   * <p>Getter for platformId.</p>
   * @return int
   **/
  public final int getPlatformId() {
    return this.platformId;
  }

  /**
   * <p>Setter for platformId.</p>
   * @param pPlatformId reference
   **/
  public final void setPlatformId(final int pPlatformId) {
    this.platformId = pPlatformId;
  }

  /**
   * <p>Getter for platformSpecificId.</p>
   * @return int
   **/
  public final int getPlatformSpecificId() {
    return this.platformSpecificId;
  }

  /**
   * <p>Setter for platformSpecificId.</p>
   * @param pPlatformSpecificId reference
   **/
  public final void setPlatformSpecificId(final int pPlatformSpecificId) {
    this.platformSpecificId = pPlatformSpecificId;
  }

  /**
   * <p>Getter for offset.</p>
   * @return long
   **/
  public final long getOffset() {
    return this.offset;
  }

  /**
   * <p>Setter for offset.</p>
   * @param pOffset reference
   **/
  public final void setOffset(final long pOffset) {
    this.offset = pOffset;
  }
}

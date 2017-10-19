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
 * <p>glyph model.</p>
 *
 * @author Yury Demidenko
 */
public class Glyph {

  /**
   * <p>GID of glyph.</p>
   **/
  private char gid;

  /**
   * <p>Max Y (for farther reveal CapsHeight).</p>
   **/
  private short maxY;

  /**
   * <p>Offset to reveal GID from "loca".</p>
   **/
  private long offset;

  /**
   * <p>Length of glyph data in bytes.</p>
   **/
  private long length = 0;

  //Simple getters and setters:
  /**
   * <p>Getter for gid.</p>
   * @return char
   **/
  public final char getGid() {
    return this.gid;
  }

  /**
   * <p>Setter for gid.</p>
   * @param pGid reference
   **/
  public final void setGid(final char pGid) {
    this.gid = pGid;
  }

  /**
   * <p>Getter for maxY.</p>
   * @return long
   **/
  public final short getMaxY() {
    return this.maxY;
  }

  /**
   * <p>Setter for maxY.</p>
   * @param pMaxY reference
   **/
  public final void setMaxY(final short pMaxY) {
    this.maxY = pMaxY;
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

  /**
   * <p>Getter for length.</p>
   * @return long
   **/
  public final long getLength() {
    return this.length;
  }

  /**
   * <p>Setter for length.</p>
   * @param pLength reference
   **/
  public final void setLength(final long pLength) {
    this.length = pLength;
  }
}

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
 * <p>TTF table directory entry model.</p>
 *
 * @author Yury Demidenko
 */
public class TtfTableDirEntry {

  /**
   * <p>String for ease sorting.</p>
   **/
  private String tagString;

  /**
   * <p>4 bytes tag.</p>
   **/
  private byte[] tag;

  /**
   * <p>Check-sum.</p>
   **/
  private long checksum;

  /**
   * <p>Offset from beginning of sfnt.</p>
   **/
  private long offset;

  /**
   * <p>Length.</p>
   **/
  private long length;

  /**
   * <p>Check-sum index in byte array to change (if need).</p>
   **/
  private Integer checksumIdx;

  /**
   * <p>Offset index in byte array to change (if need).</p>
   **/
  private Integer offsetIdx;

  /**
   * <p>Length index in byte array to change (if need).</p>
   **/
  private Integer lengthIdx;

  //Simple getters and setters:
  /**
   * <p>Getter for tagString.</p>
   * @return String
   **/
  public final String getTagString() {
    return this.tagString;
  }

  /**
   * <p>Setter for tagString.</p>
   * @param pTagString reference
   **/
  public final void setTagString(final String pTagString) {
    this.tagString = pTagString;
  }

  /**
   * <p>Getter for tag.</p>
   * @return byte[]
   **/
  public final byte[] getTag() {
    return this.tag;
  }

  /**
   * <p>Setter for tag.</p>
   * @param pTag reference
   **/
  public final void setTag(final byte[] pTag) {
    this.tag = pTag;
  }

  /**
   * <p>Getter for checksum.</p>
   * @return long
   **/
  public final long getChecksum() {
    return this.checksum;
  }

  /**
   * <p>Setter for checksum.</p>
   * @param pChecksum reference
   **/
  public final void setChecksum(final long pChecksum) {
    this.checksum = pChecksum;
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

  /**
   * <p>Getter for checksumIdx.</p>
   * @return Integer
   **/
  public final Integer getChecksumIdx() {
    return this.checksumIdx;
  }

  /**
   * <p>Setter for checksumIdx.</p>
   * @param pChecksumIdx reference
   **/
  public final void setChecksumIdx(final Integer pChecksumIdx) {
    this.checksumIdx = pChecksumIdx;
  }

  /**
   * <p>Getter for offsetIdx.</p>
   * @return Integer
   **/
  public final Integer getOffsetIdx() {
    return this.offsetIdx;
  }

  /**
   * <p>Setter for offsetIdx.</p>
   * @param pOffsetIdx reference
   **/
  public final void setOffsetIdx(final Integer pOffsetIdx) {
    this.offsetIdx = pOffsetIdx;
  }

  /**
   * <p>Getter for lengthIdx.</p>
   * @return Integer
   **/
  public final Integer getLengthIdx() {
    return this.lengthIdx;
  }

  /**
   * <p>Setter for lengthIdx.</p>
   * @param pLengthIdx reference
   **/
  public final void setLengthIdx(final Integer pLengthIdx) {
    this.lengthIdx = pLengthIdx;
  }
}

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

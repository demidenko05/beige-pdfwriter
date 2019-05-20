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

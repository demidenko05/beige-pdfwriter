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

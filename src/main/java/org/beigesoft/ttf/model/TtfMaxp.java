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

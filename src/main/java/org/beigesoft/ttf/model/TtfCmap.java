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

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

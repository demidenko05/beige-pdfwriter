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

import java.util.List;

import org.beigesoft.ttf.service.TtfBufferInputStream;

/**
 * <p>TTF glyf table model.</p>
 *
 * @author Yury Demidenko
 */
public class TtfGlyf {

  /**
   * <p>Glyphs. They will be removed after
   * reveal CapsHeight from "H".
   * </p>
   **/
  private List<Glyph> glyphs;

  /**
   * <p>Compound glyphs for making Used CIDs list.</p>
   **/
  private List<CompoundGlyph> compoundGlyphs;

  /**
   * <p>Its TDE.</p>
   **/
  private final TtfTableDirEntry tableDirEntry;

  /**
   * <p>Buffer that contains of full or part (e.g. through language N)
   * glyf table. If null then non-cached.</p>
   **/
  private TtfBufferInputStream bufferInputStream;

  /**
   * <p>Only constractor.</p>
   * @param pTableDirEntry reference
   **/
  public TtfGlyf(final TtfTableDirEntry pTableDirEntry) {
    this.tableDirEntry = pTableDirEntry;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for glyphs.</p>
   * @return List<Glyph>
   **/
  public final List<Glyph> getGlyphs() {
    return this.glyphs;
  }

  /**
   * <p>Listter for glyphs.</p>
   * @param pGlyphs reference
   **/
  public final void setGlyphs(
    final List<Glyph> pGlyphs) {
    this.glyphs = pGlyphs;
  }

  /**
   * <p>Getter for compoundGlyphs.</p>
   * @return List<CompoundGlyph>
   **/
  public final List<CompoundGlyph> getCompoundGlyphs() {
    return this.compoundGlyphs;
  }

  /**
   * <p>Setter for compoundGlyphs.</p>
   * @param pCompoundGlyphs reference
   **/
  public final void setCompoundGlyphs(
    final List<CompoundGlyph> pCompoundGlyphs) {
    this.compoundGlyphs = pCompoundGlyphs;
  }

  /**
   * <p>Getter for tableDirEntry.</p>
   * @return TtfTableDirEntry
   **/
  public final TtfTableDirEntry getTableDirEntry() {
    return this.tableDirEntry;
  }

  /**
   * <p>Getter for bufferInputStream.</p>
   * @return TtfBufferInputStream
   **/
  public final TtfBufferInputStream getBufferInputStream() {
    return this.bufferInputStream;
  }

  /**
   * <p>Setter for bufferInputStream.</p>
   * @param pBufferInputStream reference
   **/
  public final void setBufferInputStream(
    final TtfBufferInputStream pBufferInputStream) {
    this.bufferInputStream = pBufferInputStream;
  }
}

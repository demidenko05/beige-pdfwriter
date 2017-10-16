package org.beigesoft.ttf.model;

/*
 * Copyright (c) 2015-2017 Beigesoft ™
 *
 * Licensed under the GNU General Public License (GPL), Version 2.0
 * (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */

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

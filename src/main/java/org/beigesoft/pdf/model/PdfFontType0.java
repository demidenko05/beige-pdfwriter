package org.beigesoft.pdf.model;

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

import org.beigesoft.doc.model.IFont;
import org.beigesoft.ttf.model.CompoundGlyph;

/**
 * <p>PDF composite Type0 fonts model, here used as TTF.</p>
 *
 * @author Yury Demidenko
 */
public class PdfFontType0 extends APdfObject<PdfFontType0> implements IFont {

  /**
   * <p>Name of font. Must match to font file name without
   * extension from resources.</p>
   **/
  private String baseFont;

  /**
   * <p>Encoding.</p>
   **/
  private String encoding = "Identity-H";

  /**
   * <p>Descendant fonts.</p>
   **/
  private PdfCidFontType2 descendantFonts;

  /**
   * <p>To unicode map.</p>
   **/
  private PdfToUnicode toUnicode;

  /**
   * <p>TTF compound glyphs.</p>
   **/
  private List<CompoundGlyph> compoundGlyphs;

  /**
   * <p>Get if unicoded.</p>
   * @return if unicoded
   **/
  @Override
  public final boolean getIsUnicoded() {
    return true;
  }

  /**
   * <p>Usually it's simple getter that return model name.</p>
   * @return String model name
   **/
  @Override
  public final String getItsName() {
    return this.baseFont;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for baseFont.</p>
   * @return String
   **/
  public final String getBaseFont() {
    return this.baseFont;
  }

  /**
   * <p>Setter for baseFont.</p>
   * @param pBaseFont reference
   **/
  public final void setBaseFont(final String pBaseFont) {
    this.baseFont = pBaseFont;
  }

  /**
   * <p>Getter for encoding.</p>
   * @return String
   **/
  public final String getEncoding() {
    return this.encoding;
  }

  /**
   * <p>Setter for encoding.</p>
   * @param pEncoding reference
   **/
  public final void setEncoding(final String pEncoding) {
    this.encoding = pEncoding;
  }

  /**
   * <p>Getter for descendantFonts.</p>
   * @return PdfCidFontType2
   **/
  public final PdfCidFontType2 getDescendantFonts() {
    return this.descendantFonts;
  }

  /**
   * <p>Setter for descendantFonts.</p>
   * @param pDescendantFonts reference
   **/
  public final void setDescendantFonts(final PdfCidFontType2 pDescendantFonts) {
    this.descendantFonts = pDescendantFonts;
  }

  /**
   * <p>Getter for toUnicode.</p>
   * @return PdfCidFontType2
   **/
  public final PdfToUnicode getToUnicode() {
    return this.toUnicode;
  }

  /**
   * <p>Setter for toUnicode.</p>
   * @param pToUnicode reference
   **/
  public final void setToUnicode(final PdfToUnicode pToUnicode) {
    this.toUnicode = pToUnicode;
  }

  /**
   * <p>Getter for compoundGlyphs.</p>
   * @return List<CompoundGlyph>
   **/
  public final List<CompoundGlyph> getCompoundGlyphs() {
    return this.compoundGlyphs;
  }

  /**
   * <p>Listter for compoundGlyphs.</p>
   * @param pCompoundGlyphs reference
   **/
  public final void setCompoundGlyphs(
    final List<CompoundGlyph> pCompoundGlyphs) {
    this.compoundGlyphs = pCompoundGlyphs;
  }
}

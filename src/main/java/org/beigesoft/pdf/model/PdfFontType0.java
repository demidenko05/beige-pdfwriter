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

package org.beigesoft.pdf.model;

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

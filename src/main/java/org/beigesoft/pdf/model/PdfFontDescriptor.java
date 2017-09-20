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

/**
 * <p>PDF font descriptor model.</p>
 *
 * @author Yury Demidenko
 */
public class PdfFontDescriptor extends APdfObject<PdfFontDescriptor> {

  /**
   * <p>Name of font. Must match to font file name without
   * extension from resources.</p>
   **/
  private String fontName;

  /**
   * <p>Font file stream.</p>
   **/
  private PdfFontFile fontFile2;

  /**
   * <p>Min X (llX).</p>
   **/
  private float minX;

  /**
   * <p>Min Y (llY).</p>
   **/
  private float minY;

  /**
   * <p>Max X (urX).</p>
   **/
  private float maxX;

  /**
   * <p>Max Y (urY).</p>
   **/
  private float maxY;

  /**
   * <p>Flags.</p>
   **/
  private int flags;

  /**
   * <p>Italic Angle.</p>
   **/
  private float italicAngle;

  /**
   * <p>Font Weight (opt).</p>
   **/
  private float fontWeight = 0f;

  /**
   * <p>Ascent.</p>
   **/
  private float ascent;

  /**
   * <p>Descent.</p>
   **/
  private float descent;

  /**
   * <p>Cap Height.</p>
   **/
  private float capHeight;

  /**
   * <p>X-Height (opt).</p>
   **/
  private float xHeight = 0f;

  /**
   * <p>Stem-V. The thickness measured horizontally off the dominant
   * vertical stems of glyphs in the font.
   * For the same font pdf-writers give different results - e.g. 80, 89, 365.
   * But PDF-viewers don't care about it.</p>
   **/
  private float stemV = 80f; //the most used magic StemV

  //Flags (bits position from 1 to 32):
  /**
   * <p>Is FixedPitch1.</p>
   **/
  private boolean isFixedPitch1 = false;

  /**
   * <p>Is Serif2.</p>
   **/
  private boolean isSerif2 = false;

  /**
   * <p>Is Symboli3c.</p>
   **/
  private boolean isSymbolic3 = true;

  /**
   * <p>Is Script6.</p>
   **/
  private boolean isScript4 = false;

  /**
   * <p>Is Nonsymbolic6.</p>
   **/
  private boolean isNonsymbolic6 = false;

  /**
   * <p>Is Italic7.</p>
   **/
  private boolean isItalic7 = false;

  /**
   * <p>Is isAllCap17.</p>
   **/
  private boolean isAllCap17 = false;

  /**
   * <p>Is SmallCap18.</p>
   **/
  private boolean isSmallCap18 = false;

  /**
   * <p>Is ForceBold19.</p>
   **/
  private boolean isForceBold19 = false;

  /**
   * <p>Evaluate flags.</p>
   * @return int
   **/
  public final int evalFlags() {
    this.flags = 0;
    if (this.isFixedPitch1) {
      this.flags |= 0b1;
    }
    if (this.isSerif2) {
      this.flags |= 0b10;
    }
    if (this.isSymbolic3) {
      this.flags |= 0b100;
    }
    if (this.isScript4) {
      this.flags |= 0b1000;
    }
    if (this.isNonsymbolic6) {
      this.flags |= 0b100000;
    }
    if (this.isItalic7) {
      this.flags |= 0b1000000;
    }
    if (this.isAllCap17) {
      this.flags |= 0b10000000000000000;
    }
    if (this.isSmallCap18) {
      this.flags |= 0b100000000000000000;
    }
    if (this.isForceBold19) {
      this.flags |= 0b1000000000000000000;
    }
    return this.flags;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for flags.</p>
   * @return int
   **/
  public final int getFlags() {
    return this.flags;
  }

  /**
   * <p>Getter for fontName.</p>
   * @return String
   **/
  public final String getFontName() {
    return this.fontName;
  }

  /**
   * <p>Setter for fontName.</p>
   * @param pFontName reference
   **/
  public final void setFontName(final String pFontName) {
    this.fontName = pFontName;
  }

  /**
   * <p>Getter for fontFile2.</p>
   * @return APdfObject
   **/
  public final PdfFontFile getFontFile2() {
    return this.fontFile2;
  }

  /**
   * <p>Setter for fontFile2.</p>
   * @param pFontFile2 reference
   **/
  public final void setFontFile2(final PdfFontFile pFontFile2) {
    this.fontFile2 = pFontFile2;
  }

  /**
   * <p>Getter for minX.</p>
   * @return float
   **/
  public final float getMinX() {
    return this.minX;
  }

  /**
   * <p>Setter for minX.</p>
   * @param pMinX reference
   **/
  public final void setMinX(final float pMinX) {
    this.minX = pMinX;
  }

  /**
   * <p>Getter for minY.</p>
   * @return float
   **/
  public final float getMinY() {
    return this.minY;
  }

  /**
   * <p>Setter for minY.</p>
   * @param pMinY reference
   **/
  public final void setMinY(final float pMinY) {
    this.minY = pMinY;
  }

  /**
   * <p>Getter for maxX.</p>
   * @return float
   **/
  public final float getMaxX() {
    return this.maxX;
  }

  /**
   * <p>Setter for maxX.</p>
   * @param pMaxX reference
   **/
  public final void setMaxX(final float pMaxX) {
    this.maxX = pMaxX;
  }

  /**
   * <p>Getter for maxY.</p>
   * @return float
   **/
  public final float getMaxY() {
    return this.maxY;
  }

  /**
   * <p>Setter for maxY.</p>
   * @param pMaxY reference
   **/
  public final void setMaxY(final float pMaxY) {
    this.maxY = pMaxY;
  }

  /**
   * <p>Setter for flags.</p>
   * @param pFlags reference
   **/
  public final void setFlags(final int pFlags) {
    this.flags = pFlags;
  }

  /**
   * <p>Getter for italicAngle.</p>
   * @return float
   **/
  public final float getItalicAngle() {
    return this.italicAngle;
  }

  /**
   * <p>Setter for italicAngle.</p>
   * @param pItalicAngle reference
   **/
  public final void setItalicAngle(final float pItalicAngle) {
    this.italicAngle = pItalicAngle;
  }

  /**
   * <p>Getter for fontWeight.</p>
   * @return float
   **/
  public final float getFontWeight() {
    return this.fontWeight;
  }

  /**
   * <p>Setter for fontWeight.</p>
   * @param pFontWeight reference
   **/
  public final void setFontWeight(final float pFontWeight) {
    this.fontWeight = pFontWeight;
  }

  /**
   * <p>Getter for ascent.</p>
   * @return float
   **/
  public final float getAscent() {
    return this.ascent;
  }

  /**
   * <p>Setter for ascent.</p>
   * @param pAscent reference
   **/
  public final void setAscent(final float pAscent) {
    this.ascent = pAscent;
  }

  /**
   * <p>Getter for descent.</p>
   * @return float
   **/
  public final float getDescent() {
    return this.descent;
  }

  /**
   * <p>Setter for descent.</p>
   * @param pDescent reference
   **/
  public final void setDescent(final float pDescent) {
    this.descent = pDescent;
  }

  /**
   * <p>Getter for capHeight.</p>
   * @return float
   **/
  public final float getCapHeight() {
    return this.capHeight;
  }

  /**
   * <p>Setter for capHeight.</p>
   * @param pCapHeight reference
   **/
  public final void setCapHeight(final float pCapHeight) {
    this.capHeight = pCapHeight;
  }

  /**
   * <p>Getter for xHeight.</p>
   * @return float
   **/
  public final float getXHeight() {
    return this.xHeight;
  }

  /**
   * <p>Setter for xHeight.</p>
   * @param pXHeight reference
   **/
  public final void setXHeight(final float pXHeight) {
    this.xHeight = pXHeight;
  }

  /**
   * <p>Getter for stemV.</p>
   * @return float
   **/
  public final float getStemV() {
    return this.stemV;
  }

  /**
   * <p>Setter for stemV.</p>
   * @param pStemV reference
   **/
  public final void setStemV(final float pStemV) {
    this.stemV = pStemV;
  }

  /**
   * <p>Getter for isFixedPitch1.</p>
   * @return boolean
   **/
  public final boolean getIsFixedPitch1() {
    return this.isFixedPitch1;
  }

  /**
   * <p>Setter for isFixedPitch1.</p>
   * @param pIsFixedPitch1 reference
   **/
  public final void setIsFixedPitch1(final boolean pIsFixedPitch1) {
    this.isFixedPitch1 = pIsFixedPitch1;
  }

  /**
   * <p>Getter for isSerif2.</p>
   * @return boolean
   **/
  public final boolean getIsSerif2() {
    return this.isSerif2;
  }

  /**
   * <p>Setter for isSerif2.</p>
   * @param pIsSerif2 reference
   **/
  public final void setIsSerif2(final boolean pIsSerif2) {
    this.isSerif2 = pIsSerif2;
  }

  /**
   * <p>Getter for isSymbolic3.</p>
   * @return boolean
   **/
  public final boolean getIsSymbolic3() {
    return this.isSymbolic3;
  }

  /**
   * <p>Setter for isSymbolic3.</p>
   * @param pIsSymbolic3 reference
   **/
  public final void setIsSymbolic3(final boolean pIsSymbolic3) {
    this.isSymbolic3 = pIsSymbolic3;
  }

  /**
   * <p>Getter for isScript4.</p>
   * @return boolean
   **/
  public final boolean getIsScript4() {
    return this.isScript4;
  }

  /**
   * <p>Setter for isScript4.</p>
   * @param pIsScript4 reference
   **/
  public final void setIsScript4(final boolean pIsScript4) {
    this.isScript4 = pIsScript4;
  }

  /**
   * <p>Getter for isNonsymbolic6.</p>
   * @return boolean
   **/
  public final boolean getIsNonsymbolic6() {
    return this.isNonsymbolic6;
  }

  /**
   * <p>Setter for isNonsymbolic6.</p>
   * @param pIsNonsymbolic6 reference
   **/
  public final void setIsNonsymbolic6(final boolean pIsNonsymbolic6) {
    this.isNonsymbolic6 = pIsNonsymbolic6;
  }

  /**
   * <p>Getter for isItalic7.</p>
   * @return boolean
   **/
  public final boolean getIsItalic7() {
    return this.isItalic7;
  }

  /**
   * <p>Setter for isItalic7.</p>
   * @param pIsItalic7 reference
   **/
  public final void setIsItalic7(final boolean pIsItalic7) {
    this.isItalic7 = pIsItalic7;
  }

  /**
   * <p>Getter for isAllCap17.</p>
   * @return boolean
   **/
  public final boolean getIsAllCap17() {
    return this.isAllCap17;
  }

  /**
   * <p>Setter for isAllCap17.</p>
   * @param pIsAllCap17 reference
   **/
  public final void setIsAllCap17(final boolean pIsAllCap17) {
    this.isAllCap17 = pIsAllCap17;
  }

  /**
   * <p>Getter for isSmallCap18.</p>
   * @return boolean
   **/
  public final boolean getIsSmallCap18() {
    return this.isSmallCap18;
  }

  /**
   * <p>Setter for isSmallCap18.</p>
   * @param pIsSmallCap18 reference
   **/
  public final void setIsSmallCap18(final boolean pIsSmallCap18) {
    this.isSmallCap18 = pIsSmallCap18;
  }

  /**
   * <p>Getter for isForceBold19.</p>
   * @return boolean
   **/
  public final boolean getIsForceBold19() {
    return this.isForceBold19;
  }

  /**
   * <p>Setter for isForceBold19.</p>
   * @param pIsForceBold19 reference
   **/
  public final void setIsForceBold19(final boolean pIsForceBold19) {
    this.isForceBold19 = pIsForceBold19;
  }
}

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
 * <p>PDF font file stream model.</p>
 *
 * @author Yury Demidenko
 */
public class PdfFontFile extends APdfObject<PdfFontFile> {

  /**
   * <p>Font font name.</p>
   **/
  private String fontName;

  /**
   * <p>To unicode map.</p>
   **/
  private PdfToUnicode toUnicode;

  //Simple getters and setters:
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
}

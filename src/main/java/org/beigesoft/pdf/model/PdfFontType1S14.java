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

import org.beigesoft.doc.model.IFont;

/**
 * <p>PDF standard (14) Type1 fonts model.</p>
 *
 * @author Yury Demidenko
 */
public class PdfFontType1S14 extends APdfObject<PdfFontType1S14>
  implements IFont {

  /**
   * <p>Name of font.</p>
   **/
  private EFontS14 baseFont = EFontS14.HELVETICA;

  /**
   * <p>Get if unicoded.</p>
   * @return if unicoded
   **/
  @Override
  public final boolean getIsUnicoded() {
    return false;
  }

  /**
   * <p>Usually it's simple getter that return model name.</p>
   * @return String model name
   **/
  @Override
  public final String getItsName() {
    return this.baseFont.toString();
  }

  //Simple getters and setters:
  /**
   * <p>Getter for baseFont.</p>
   * @return String
   **/
  public final EFontS14 getBaseFont() {
    return this.baseFont;
  }

  /**
   * <p>Setter for baseFont.</p>
   * @param pBaseFont reference
   **/
  public final void setBaseFont(final EFontS14 pBaseFont) {
    this.baseFont = pBaseFont;
  }
}

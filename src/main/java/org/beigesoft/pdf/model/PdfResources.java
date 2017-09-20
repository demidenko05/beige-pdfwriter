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

/**
 * <p>PDF resources dictionary model.</p>
 *
 * @author Yury Demidenko
 */
public class PdfResources extends APdfObject<PdfResources> {

  /**
   * <p>Fonts dictionary.</p>
   **/
  private List<IPdfObject> fonts;

  //Simple getters and setters:
  /**
   * <p>Getter for fonts.</p>
   * @return List<IPdfObject>
   **/
  public final List<IPdfObject> getFonts() {
    return this.fonts;
  }

  /**
   * <p>Setter for fonts.</p>
   * @param pFonts reference
   **/
  public final void setFonts(final List<IPdfObject> pFonts) {
    this.fonts = pFonts;
  }
}

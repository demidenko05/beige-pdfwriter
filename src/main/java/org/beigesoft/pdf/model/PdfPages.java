package org.beigesoft.pdf.model;

/*
 * Copyright (c) 2017 Beigesoft ™
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
 * <p>PDF pages dictionary model.</p>
 *
 * @author Yury Demidenko
 */
public class PdfPages extends APdfObject<PdfPages> {

  /**
   * <p>Pages.</p>
   **/
  private List<PdfPage> pages;

  //Simple getters and setters:
  /**
   * <p>Getter for pages.</p>
   * @return List<PdfPage>
   **/
  public final List<PdfPage> getPages() {
    return this.pages;
  }

  /**
   * <p>Setter for pages.</p>
   * @param pPages reference
   **/
  public final void setPages(final List<PdfPage> pPages) {
    this.pages = pPages;
  }
}

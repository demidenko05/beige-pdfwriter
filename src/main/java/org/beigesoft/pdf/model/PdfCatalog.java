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
 * <p>PDF catalog dictionary model.</p>
 *
 * @author Yury Demidenko
 */
public class PdfCatalog extends APdfObject<PdfCatalog> {

  /**
   * <p>Pages.</p>
   **/
  private PdfPages pages;

  //Simple getters and setters:
  /**
   * <p>Getter for pages.</p>
   * @return PdfPages
   **/
  public final PdfPages getPages() {
    return this.pages;
  }

  /**
   * <p>Setter for pages.</p>
   * @param pPages reference
   **/
  public final void setPages(final PdfPages pPages) {
    this.pages = pPages;
  }
}

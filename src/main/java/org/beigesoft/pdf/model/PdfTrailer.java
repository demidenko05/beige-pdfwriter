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
 * <p>PDF trailer model.</p>
 *
 * @author Yury Demidenko
 */
public class PdfTrailer {

  /**
   * <p>Root reference.</p>
   **/
  private PdfCatalog root;

  /**
   * <p>Info reference (opt).</p>
   **/
  private PdfInfo info;

  //Simple getters and setters:
  /**
   * <p>Getter for root.</p>
   * @return APdfObject
   **/
  public final PdfCatalog getRoot() {
    return this.root;
  }

  /**
   * <p>Setter for root.</p>
   * @param pRoot reference
   **/
  public final void setRoot(final PdfCatalog pRoot) {
    this.root = pRoot;
  }

  /**
   * <p>Getter for info.</p>
   * @return APdfObject
   **/
  public final PdfInfo getInfo() {
    return this.info;
  }

  /**
   * <p>Setter for info.</p>
   * @param pInfo reference
   **/
  public final void setInfo(final PdfInfo pInfo) {
    this.info = pInfo;
  }
}

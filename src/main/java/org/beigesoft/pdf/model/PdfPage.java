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

/**
 * <p>PDF page dictionary model.</p>
 *
 * @author Yury Demidenko
 */
public class PdfPage extends APdfObject<PdfPage> {

  /**
   * <p>Pages (parent).</p>
   **/
  private PdfPages parent;

  /**
   * <p>Resource dictionary.</p>
   **/
  private PdfResources resources;

  /**
   * <p>Content.</p>
   **/
  private PdfContent contents;

  /**
   * <p>Media box.</p>
   **/
  private PdfRectangle mediabox = new PdfRectangle();

  //Simple getters and setters:
  /**
   * <p>Getter for parent.</p>
   * @return PdfPages
   **/
  public final PdfPages getParent() {
    return this.parent;
  }

  /**
   * <p>Setter for parent.</p>
   * @param pParent reference
   **/
  public final void setParent(final PdfPages pParent) {
    this.parent = pParent;
  }

  /**
   * <p>Getter for resources.</p>
   * @return PdfResources
   **/
  public final PdfResources getResources() {
    return this.resources;
  }

  /**
   * <p>Setter for resources.</p>
   * @param pResources reference
   **/
  public final void setResources(final PdfResources pResources) {
    this.resources = pResources;
  }

  /**
   * <p>Getter for contents.</p>
   * @return PdfContent
   **/
  public final PdfContent getContents() {
    return this.contents;
  }

  /**
   * <p>Setter for contents.</p>
   * @param pContents reference
   **/
  public final void setContents(final PdfContent pContents) {
    this.contents = pContents;
  }

  /**
   * <p>Getter for mediabox.</p>
   * @return PdfRectangle
   **/
  public final PdfRectangle getMediabox() {
    return this.mediabox;
  }

  /**
   * <p>Setter for mediabox.</p>
   * @param pMediabox reference
   **/
  public final void setMediabox(final PdfRectangle pMediabox) {
    this.mediabox = pMediabox;
  }
}

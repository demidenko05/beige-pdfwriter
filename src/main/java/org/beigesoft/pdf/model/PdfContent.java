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

import org.beigesoft.doc.model.DocPage;

/**
 * <p>PDF stream content model.</p>
 *
 * @author Yury Demidenko
 */
public class PdfContent extends APdfStream<PdfContent> {

  /**
   * <p>Page with content.</p>
   **/
  private DocPage<? extends IHasPdfContent> page;

  /**
   * <p>PDF document.</p>
   **/
  private PdfDocument<? extends IHasPdfContent> document;

  //There are successive steps where each is depend on previous one.
  //E.g. DocString may write "BT" (begin text command)
  //if there is no current "BT".
  //Page current state during writing:
  /**
   * <p>Current X position in dots.</p>
   **/
  private double x;

  /**
   * <p>Current Y position in dots.</p>
   **/
  private double y;

  /**
   * <p>Current font# (from 1).</p>
   **/
  private int fontNumber;

  /**
   * <p>Current font size.</p>
   **/
  private float fontSize;

  /**
   * <p>Current text state.</p>
   **/
  private ETextState textState;

  //Simple getters and setters:
  /**
   * <p>Getter for page.</p>
   * @return DocPage<? extends IHasPdfContent>
   **/
  public final DocPage<? extends IHasPdfContent> getPage() {
    return this.page;
  }

  /**
   * <p>Setter for page.</p>
   * @param pPage reference
   **/
  public final void setPage(final DocPage<? extends IHasPdfContent> pPage) {
    this.page = pPage;
  }

  /**
   * <p>Getter for document.</p>
   * @return PdfDocument
   **/
  public final PdfDocument<? extends IHasPdfContent> getDocument() {
    return this.document;
  }

  /**
   * <p>Setter for document.</p>
   * @param pDocument reference
   **/
  public final void setDocument(
    final PdfDocument<? extends IHasPdfContent> pDocument) {
    this.document = pDocument;
  }

  /**
   * <p>Getter for x.</p>
   * @return double
   **/
  public final double getX() {
    return this.x;
  }

  /**
   * <p>Setter for x.</p>
   * @param pX reference
   **/
  public final void setX(final double pX) {
    this.x = pX;
  }

  /**
   * <p>Getter for y.</p>
   * @return double
   **/
  public final double getY() {
    return this.y;
  }

  /**
   * <p>Setter for y.</p>
   * @param pY reference
   **/
  public final void setY(final double pY) {
    this.y = pY;
  }

  /**
   * <p>Getter for fontNumber.</p>
   * @return int
   **/
  public final int getFontNumber() {
    return this.fontNumber;
  }

  /**
   * <p>Setter for fontNumber.</p>
   * @param pFontNumber reference
   **/
  public final void setFontNumber(final int pFontNumber) {
    this.fontNumber = pFontNumber;
  }

  /**
   * <p>Getter for fontSize.</p>
   * @return float
   **/
  public final float getFontSize() {
    return this.fontSize;
  }

  /**
   * <p>Setter for fontSize.</p>
   * @param pFontSize reference
   **/
  public final void setFontSize(final float pFontSize) {
    this.fontSize = pFontSize;
  }

  /**
   * <p>Getter for textState.</p>
   * @return ETextState
   **/
  public final ETextState getTextState() {
    return this.textState;
  }

  /**
   * <p>Setter for textState.</p>
   * @param pTextState reference
   **/
  public final void setTextState(final ETextState pTextState) {
    this.textState = pTextState;
  }
}

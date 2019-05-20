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

import java.math.BigDecimal;

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
   * <p>Current text position X, null means 0 (start).</p>
   **/
  private BigDecimal x;

  /**
   * <p>Current text position Y, null means 0 (start).</p>
   **/
  private BigDecimal y;

  /**
   * <p>Current font# (from 1).</p>
   **/
  private int fontNumber;

  /**
   * <p>Current font size.</p>
   **/
  private BigDecimal fontSize = BigDecimal.ZERO;

  /**
   * <p>Current text state.</p>
   **/
  private ETextState textState;

  //current graphic state:

  /**
   * <p>Current graphic state.</p>
   **/
  private EGraphicState graphicState;
  /**
   * <p>Current line width.</p>
   **/
  private BigDecimal lineWidth = BigDecimal.ZERO;

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
   * @return BigDecimal
   **/
  public final BigDecimal getX() {
    return this.x;
  }

  /**
   * <p>Setter for x.</p>
   * @param pX reference
   **/
  public final void setX(final BigDecimal pX) {
    this.x = pX;
  }

  /**
   * <p>Getter for y.</p>
   * @return BigDecimal
   **/
  public final BigDecimal getY() {
    return this.y;
  }

  /**
   * <p>Setter for y.</p>
   * @param pY reference
   **/
  public final void setY(final BigDecimal pY) {
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
   * @return BigDecimal
   **/
  public final BigDecimal getFontSize() {
    return this.fontSize;
  }

  /**
   * <p>Setter for fontSize.</p>
   * @param pFontSize reference
   **/
  public final void setFontSize(final BigDecimal pFontSize) {
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

  /**
   * <p>Getter for graphicState.</p>
   * @return EGraphicState
   **/
  public final EGraphicState getGraphicState() {
    return this.graphicState;
  }

  /**
   * <p>Setter for graphicState.</p>
   * @param pGraphicState reference
   **/
  public final void setGraphicState(final EGraphicState pGraphicState) {
    this.graphicState = pGraphicState;
  }

  /**
   * <p>Getter for lineWidth.</p>
   * @return BigDecimal
   **/
  public final BigDecimal getLineWidth() {
    return this.lineWidth;
  }

  /**
   * <p>Setter for lineWidth.</p>
   * @param pLineWidth reference
   **/
  public final void setLineWidth(final BigDecimal pLineWidth) {
    this.lineWidth = pLineWidth;
  }
}

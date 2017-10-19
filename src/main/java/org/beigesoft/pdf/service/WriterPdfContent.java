package org.beigesoft.pdf.service;

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

import java.util.Comparator;
import java.util.Collections;
import java.io.OutputStream;
import java.math.BigDecimal;

import org.beigesoft.doc.model.IElement;
import org.beigesoft.pdf.model.ETextState;
import org.beigesoft.pdf.model.EGraphicState;
import org.beigesoft.pdf.model.PdfContent;
import org.beigesoft.pdf.model.IHasPdfContent;

/**
 * <p>PDF stream content writer.</p>
 *
 * @author Yury Demidenko
 */
public class WriterPdfContent extends AWriterPdfStream<PdfContent, PdfContent> {

  /**
   * <p>IHasPdfContent factory.</p>
   **/
  private IFctHasPdfContent fctHasPdfContent;

  /**
   * <p>App-scoped to comparator to order elements before write.</p>
   **/
  private Comparator<IElement<?>> cmpElement;

  /**
   * <p>Write object to buffer.</p>
   * @param pOut stream
   * @return bytes count
   * @throws Exception an Exception
   **/
  @Override
  public final void writeToBuffer(final PdfContent pContent,
    final OutputStream pOut) throws Exception {
    Collections.sort(pContent.getPage().getElements(), this.cmpElement);
    resetWriteState(pContent);
    IHasPdfContent wi = this.fctHasPdfContent.createHasPdfContent(pContent);
    for (IElement el : pContent.getPage().getElements()) {
      el.write(wi);
    }
    handleEnd(pContent);
  }

  //Utils:
  /**
   * <p>End text/graphic/etc if need.</p>
   * @param pContent PdfContent
   * @throws Exception an Exception
   **/
  public final void handleEnd(final PdfContent pContent) throws Exception {
    if (pContent.getTextState().equals(ETextState.STARTED)) {
      pContent.getBuffer().write("ET\n".getBytes(getWriteHelper().getAscii()));
      pContent.setTextState(ETextState.ENDED);
    }
    if (pContent.getGraphicState().equals(EGraphicState.STARTED)) {
      pContent.getBuffer().write("S\n".getBytes(getWriteHelper().getAscii()));
      pContent.setGraphicState(EGraphicState.ENDED);
    }
  }

  /**
   * <p>Reset write state.</p>
   * @param pContent PdfContent
   * @throws Exception an Exception
   **/
  public final void resetWriteState(
    final PdfContent pContent) throws Exception {
    pContent.setGraphicState(EGraphicState.ENDED);
    pContent.setTextState(ETextState.ENDED);
    pContent.setX(null);
    pContent.setY(null);
    pContent.setFontNumber(0);
    pContent.setFontSize(BigDecimal.ZERO);
    pContent.setLineWidth(BigDecimal.ZERO);
  }

  //Simple getters and setters:
  /**
   * <p>Getter for fctHasPdfContent.</p>
   * @return IFctHasPdfContent
   **/
  public final IFctHasPdfContent getFctHasPdfContent() {
    return this.fctHasPdfContent;
  }

  /**
   * <p>Setter for fctHasPdfContent.</p>
   * @param pFctHasPdfContent reference
   **/
  public final void setFctHasPdfContent(
    final IFctHasPdfContent pFctHasPdfContent) {
    this.fctHasPdfContent = pFctHasPdfContent;
  }

  /**
   * <p>Getter for cmpElement.</p>
   * @return Comparator<IElement<?>>
   **/
  public final Comparator<IElement<?>> getCmpElement() {
    return this.cmpElement;
  }

  /**
   * <p>Setter for cmpElement.</p>
   * @param pCmpElement reference
   **/
  public final void setCmpElement(final Comparator<IElement<?>> pCmpElement) {
    this.cmpElement = pCmpElement;
  }
}

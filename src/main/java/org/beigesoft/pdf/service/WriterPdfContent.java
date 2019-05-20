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

package org.beigesoft.pdf.service;

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

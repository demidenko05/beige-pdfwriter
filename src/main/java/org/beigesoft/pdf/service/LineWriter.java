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

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.beigesoft.doc.model.DocLine;
import org.beigesoft.doc.service.UomHelper;
import org.beigesoft.doc.service.IElementWriter;
import org.beigesoft.pdf.model.HasPdfContent;
import org.beigesoft.pdf.model.PdfContent;
import org.beigesoft.pdf.model.ETextState;
import org.beigesoft.pdf.model.EGraphicState;

/**
 * <p>Service that write document's line into PDF file.</p>
 *
 * @author Yury Demidenko
 */
public class LineWriter
  implements IElementWriter<DocLine<HasPdfContent>, HasPdfContent> {

  /**
   * <p>Write helper.</p>
   **/
  private PdfWriteHelper writeHelper;

  /**
   * <p>App-scoped UOM helper.</p>
   **/
  private UomHelper uomHelper;

  /**
   * <p>Write element to document page in file.</p>
   * @param pLn Element
   * @param pWi PdfContent
   * @throws Exception an Exception
   **/
  @Override
  public final void write(final DocLine<HasPdfContent> pLn,
    final HasPdfContent pWi) throws Exception {
    PdfContent wi = pWi.getPdfContent();
    if (wi.getTextState().equals(ETextState.STARTED)) {
      wi.getBuffer().write("ET\n".getBytes(getWriteHelper().getAscii()));
      wi.setTextState(ETextState.ENDED);
      wi.setX(null);
      wi.setY(null);
    }
    if (!wi.getGraphicState().equals(EGraphicState.STARTED)) {
      wi.getBuffer().write("2 J\n".getBytes(getWriteHelper().getAscii()));
      wi.setGraphicState(EGraphicState.STARTED);
    }
    double lnWdd = this.uomHelper.toPoints(pLn.getWidth(), wi.getDocument()
      .getMainDoc().getResolutionDpi(), wi.getDocument().getMainDoc()
        .getUnitOfMeasure());
    BigDecimal lnWd = BigDecimal.valueOf(lnWdd)
      .setScale(2, RoundingMode.HALF_UP);
    if (wi.getLineWidth().compareTo(lnWd) != 0) {
      wi.setLineWidth(lnWd);
      String lnWdStr = lnWd.toString() + " w\n";
      wi.getBuffer().write(lnWdStr.getBytes(getWriteHelper().getAscii()));
    }
    double lnX1d = this.uomHelper.toPoints(pLn.getX1(), wi.getDocument()
      .getMainDoc().getResolutionDpi(), wi.getDocument().getMainDoc()
        .getUnitOfMeasure());
    BigDecimal lnX1 = BigDecimal.valueOf(lnX1d)
      .setScale(2, RoundingMode.HALF_UP);
    double lnUY1d = this.uomHelper.toPoints(pLn.getY1(), wi.getDocument()
      .getMainDoc().getResolutionDpi(), wi.getDocument().getMainDoc()
        .getUnitOfMeasure());
    double pageHeight = this.uomHelper.toPoints(wi.getPage().getHeight(),
      wi.getDocument().getMainDoc().getResolutionDpi(), wi.getDocument()
        .getMainDoc().getUnitOfMeasure());
    double lnY1d = pageHeight - lnUY1d;
    BigDecimal lnY1 = BigDecimal.valueOf(lnY1d)
      .setScale(2, RoundingMode.HALF_UP);
    String lnX1Y1Str = lnX1.toString() + " " + lnY1 + " m\n";
    wi.getBuffer().write(lnX1Y1Str.getBytes(getWriteHelper().getAscii()));
    double lnX2d = this.uomHelper.toPoints(pLn.getX2(), wi.getDocument()
      .getMainDoc().getResolutionDpi(), wi.getDocument().getMainDoc()
        .getUnitOfMeasure());
    BigDecimal lnX2 = BigDecimal.valueOf(lnX2d)
      .setScale(2, RoundingMode.HALF_UP);
    double lnUY2d = this.uomHelper.toPoints(pLn.getY2(), wi.getDocument()
      .getMainDoc().getResolutionDpi(), wi.getDocument().getMainDoc()
        .getUnitOfMeasure());
    double lnY2d = pageHeight - lnUY2d;
    BigDecimal lnY2 = BigDecimal.valueOf(lnY2d)
      .setScale(2, RoundingMode.HALF_UP);
    String lnX2Y2Str = lnX2.toString() + " " + lnY2 + " l\n";
    wi.getBuffer().write(lnX2Y2Str.getBytes(getWriteHelper().getAscii()));
  }

  //SGS:
  /**
   * <p>Getter for writeHelper.</p>
   * @return PdfWriteHelper
   **/
  public final PdfWriteHelper getWriteHelper() {
    return this.writeHelper;
  }

  /**
   * <p>Setter for writeHelper.</p>
   * @param pWriteHelper reference
   **/
  public final void setWriteHelper(final PdfWriteHelper pWriteHelper) {
    this.writeHelper = pWriteHelper;
  }

  /**
   * <p>Getter for uomHelper.</p>
   * @return UomHelper
   **/
  public final UomHelper getUomHelper() {
    return this.uomHelper;
  }

  /**
   * <p>Setter for uomHelper.</p>
   * @param pUomHelper reference
   **/
  public final void setUomHelper(final UomHelper pUomHelper) {
    this.uomHelper = pUomHelper;
  }
}

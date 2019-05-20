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

import org.beigesoft.doc.model.DocImage;
import org.beigesoft.doc.service.UomHelper;
import org.beigesoft.doc.service.IElementWriter;
import org.beigesoft.pdf.model.HasPdfContent;
import org.beigesoft.pdf.model.PdfContent;
import org.beigesoft.pdf.model.PdfImage;
import org.beigesoft.pdf.model.ETextState;
import org.beigesoft.pdf.model.EGraphicState;

/**
 * <p>Service that write document's line into PDF file.</p>
 *
 * @author Yury Demidenko
 */
public class ImageWriter
  implements IElementWriter<DocImage<HasPdfContent>, HasPdfContent> {

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
   * @param pImg Image
   * @param pWi PdfContent
   * @throws Exception an Exception
   **/
  @Override
  public final void write(final DocImage<HasPdfContent> pImg,
    final HasPdfContent pWi) throws Exception {
    PdfContent wi = pWi.getPdfContent();
    if (wi.getTextState().equals(ETextState.STARTED)) {
      wi.getBuffer().write("ET\n".getBytes(getWriteHelper().getAscii()));
      wi.setTextState(ETextState.ENDED);
      wi.setX(null);
      wi.setY(null);
    }
    if (wi.getGraphicState().equals(EGraphicState.STARTED)) {
      wi.getBuffer().write("S\n".getBytes(getWriteHelper().getAscii()));
      wi.setGraphicState(EGraphicState.ENDED);
    }
    BigDecimal wd = BigDecimal.valueOf(pImg.getImage().getWidth()
      * pImg.getScale()).setScale(2, RoundingMode.HALF_UP);
    BigDecimal ht = BigDecimal.valueOf(pImg.getImage().getHeight()
      * pImg.getScale()).setScale(2, RoundingMode.HALF_UP);
    double imgX1d = this.uomHelper.toPoints(pImg.getX1(), wi.getDocument()
      .getMainDoc().getResolutionDpi(), wi.getDocument().getMainDoc()
        .getUnitOfMeasure());
    BigDecimal imgX1 = BigDecimal.valueOf(imgX1d)
      .setScale(2, RoundingMode.HALF_UP);
    double pageHeight = this.uomHelper.toPoints(wi.getPage().getHeight(),
      wi.getDocument().getMainDoc().getResolutionDpi(), wi.getDocument()
        .getMainDoc().getUnitOfMeasure());
    double imgUY1d = this.uomHelper.toPoints(pImg.getY1(), wi.getDocument()
      .getMainDoc().getResolutionDpi(), wi.getDocument().getMainDoc()
        .getUnitOfMeasure());
    double imgY1d = pageHeight - imgUY1d - ht.doubleValue();
    BigDecimal imgY1 = BigDecimal.valueOf(imgY1d)
      .setScale(2, RoundingMode.HALF_UP);
    int imgNum = 1;
    for (PdfImage pdfImage : wi.getDocument().getImages()) {
      if (pdfImage.getDocImage().equals(pImg)) {
        break;
      }
      imgNum++;
    }
    String img;
    if (pImg.getRotateDegrees() > 0.001) {
      double rad = Math.toRadians(pImg.getRotateDegrees());
      BigDecimal cosr = BigDecimal.valueOf(Math.cos(rad))
        .setScale(4, RoundingMode.HALF_UP);
      BigDecimal sinr = BigDecimal.valueOf(Math.sin(rad))
        .setScale(4, RoundingMode.HALF_UP);
      BigDecimal sinrn = sinr.negate();
      //translate:
      img = "q\n 1 0 0 1 " + imgX1 + " " + imgY1 + " cm\n"
      //rotate:
        + cosr.toString() + " " + sinr + " " + sinrn + " " + cosr
          + " " + " 0 0 cm\n"
      //scale:
      + wd.toString() + " 0 0 " + ht + " 0 0 cm\n/Im" + imgNum + " Do\nQ\n";
    } else {
      img = "q\n" + wd + " 0 0 " + ht + " " + imgX1 + " " + imgY1
        + " cm\n/Im" + imgNum + " Do\nQ\n";
    }
    wi.getBuffer().write(img.getBytes(getWriteHelper().getAscii()));
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

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
    double imgY1d = pageHeight - imgUY1d;
    BigDecimal imgY1 = BigDecimal.valueOf(imgY1d)
      .setScale(2, RoundingMode.HALF_UP);
    int imgNum = 1;
    for (PdfImage pdfImage : wi.getDocument().getImages()) {
      if (pdfImage.getDocImage().equals(pImg)) {
        break;
      }
      imgNum++;
    }
    String img = "q\n" + pImg.getImage().getWidth() * pImg.getScale()
      + " 0 0 " + pImg.getImage().getHeight() * pImg.getScale()
        + " " + imgX1 + " " + imgY1 + " cm\n/Im" + imgNum + " Do\nQ\n";
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

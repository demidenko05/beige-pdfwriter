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

import org.beigesoft.doc.model.DocString;
import org.beigesoft.doc.service.UomHelper;
import org.beigesoft.doc.service.ToHexCoder;
import org.beigesoft.doc.service.IElementWriter;
import org.beigesoft.pdf.model.IPdfObject;
import org.beigesoft.pdf.model.PdfFontType1S14;
import org.beigesoft.pdf.model.PdfFontType0;
import org.beigesoft.pdf.model.HasPdfContent;
import org.beigesoft.pdf.model.PdfContent;
import org.beigesoft.pdf.model.ETextState;
import org.beigesoft.pdf.exception.ExceptionPdfWr;

/**
 * <p>Service that write document's string into PDF file.</p>
 *
 * @author Yury Demidenko
 */
public class StringWriter
  implements IElementWriter<DocString<HasPdfContent>, HasPdfContent> {

  /**
   * <p>Write helper.</p>
   **/
  private PdfWriteHelper writeHelper;

  /**
   * <p>App-scoped to Hex coder.</p>
   **/
  private ToHexCoder toHexCoder;

  /**
   * <p>App-scoped UOM helper.</p>
   **/
  private UomHelper uomHelper;

  /**
   * <p>Write element to document page in file.</p>
   * @param pStr Element
   * @param pWi PdfContent
   * @throws Exception an Exception
   **/
  @Override
  public final void write(final DocString<HasPdfContent> pStr,
    final HasPdfContent pWi) throws Exception {
    PdfContent wi = pWi.getPdfContent();
    if (wi.getTextState().equals(ETextState.ENDED)) {
      wi.getBuffer().write("BT\n".getBytes(getWriteHelper().getAscii()));
      wi.setTextState(ETextState.STARTED);
    }
    StringBuffer sb = new StringBuffer();
    double strFntSzd = this.uomHelper.toPoints(pStr.getFontSize(), wi
      .getDocument().getMainDoc().getResolutionDpi(),
        wi.getDocument().getMainDoc().getUnitOfMeasure());
    BigDecimal strFntSz = BigDecimal.valueOf(strFntSzd)
      .setScale(2, RoundingMode.HALF_UP);
    if (wi.getFontNumber() != pStr.getFontNumber()
      || wi.getFontSize().compareTo(strFntSz) != 0) {
      wi.setFontNumber(pStr.getFontNumber());
      wi.setFontSize(strFntSz);
      String fntStr = "/F" + wi.getFontNumber() + " " + strFntSz
        + " Tf\n";
      wi.getBuffer().write(fntStr.getBytes(getWriteHelper().getAscii()));
    }
    double strXd = this.uomHelper.toPoints(pStr.getX1(), wi.getDocument()
      .getMainDoc().getResolutionDpi(), wi.getDocument().getMainDoc()
        .getUnitOfMeasure());
    double strUlY = this.uomHelper.toPoints(pStr.getY2(), wi.getDocument()
      .getMainDoc().getResolutionDpi(), wi.getDocument().getMainDoc()
        .getUnitOfMeasure());
    double pageHeight = this.uomHelper.toPoints(wi.getPage().getHeight(),
      wi.getDocument().getMainDoc().getResolutionDpi(), wi.getDocument()
        .getMainDoc().getUnitOfMeasure());
    double strYd = pageHeight - strUlY;
    BigDecimal strX = BigDecimal.valueOf(strXd)
      .setScale(2, RoundingMode.HALF_UP);
    BigDecimal strY = BigDecimal.valueOf(strYd)
      .setScale(2, RoundingMode.HALF_UP);
    BigDecimal strDtX;
    BigDecimal strDtY;
    if (wi.getX() == null && wi.getY() == null) {
      strDtX = strX;
      strDtY = strY;
    } else {
      strDtX = strX.subtract(wi.getX());
      strDtY = strY.subtract(wi.getY());
    }
    wi.setX(strX);
    wi.setY(strY);
    String tdStr = String.valueOf(strDtX) + " " + strDtY + " Td\n";
    wi.getBuffer().write(tdStr.getBytes(getWriteHelper().getAscii()));
    IPdfObject fnt = wi.getDocument().getResources()
      .getFonts().get(wi.getFontNumber() - 1);
    if (fnt instanceof PdfFontType1S14) {
      String strStr = "(" + pStr.getValue() + ") Tj\n";
      wi.getBuffer().write(strStr.getBytes(getWriteHelper().getAscii()));
    } else if (fnt instanceof PdfFontType0) {
      PdfFontType0 fnt0 = (PdfFontType0) fnt;
      sb = sb.delete(0, sb.length());
      //sb.append("<FEFF");
      sb.append("<");
      for (int i = 0; i < pStr.getValue().length(); i++) {
        char uni = (char) pStr.getValue().codePointAt(i);
        char cid = fnt0.getToUnicode().getUniToCid().get(uni);
        sb.append(this.toHexCoder.encode(cid));
      }
      sb.append("> Tj\n");
      wi.getBuffer().write(sb.toString()
        .getBytes(getWriteHelper().getAscii()));
    } else {
      throw new ExceptionPdfWr("Unsupported font " + fnt);
    }
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
   * <p>Getter for toHexCoder.</p>
   * @return ToHexCoder
   **/
  public final ToHexCoder getToHexCoder() {
    return this.toHexCoder;
  }

  /**
   * <p>Setter for toHexCoder.</p>
   * @param pToHexCoder reference
   **/
  public final void setToHexCoder(final ToHexCoder pToHexCoder) {
    this.toHexCoder = pToHexCoder;
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

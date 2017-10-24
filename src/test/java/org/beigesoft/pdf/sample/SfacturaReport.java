package org.beigesoft.pdf.sample;

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

import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;

import org.beigesoft.doc.model.EUnitOfMeasure;
import org.beigesoft.doc.model.Document;
import org.beigesoft.doc.model.DocTable;
import org.beigesoft.doc.model.DocImage;
import org.beigesoft.doc.model.Pagination;
import org.beigesoft.doc.model.EWraping;
import org.beigesoft.doc.model.MetricsString;
import org.beigesoft.doc.model.EAlignHorizontal;
import org.beigesoft.doc.model.EPageSize;
import org.beigesoft.doc.model.EPageOrientation;
import org.beigesoft.doc.service.IDocumentMaker;
import org.beigesoft.pdf.model.ERegisteredTtfFont;
import org.beigesoft.pdf.model.PdfDocument;
import org.beigesoft.pdf.service.IPdfFactory;
import org.beigesoft.pdf.service.IPdfMaker;

/**
 * <p>Пример отчета сет-фактура.</p>
 *
 * @param <WI> writing instrument type
 * @author Yury Demidenko
 */
public class SfacturaReport<WI> {

  /**
   * <p>Factory.</p>
   **/
  private IPdfFactory<WI> factory;

  /**
   * <p>Write PDF report for given data to output stream.</p>
   * @param pData data
   * @param pOus output stream
   * @param pIsInsertImg if need image demonstration
   **/
  public final void makePdf(final SfacturaModel pData,
    final OutputStream pOus) throws Exception {
    // it makes document with page A4 and margins LTRB 30*20*20*20, UOM=millimeters,
    // font size 3.5:
    Document<WI> doc = this.factory.lazyGetFctDocument()
      .createDoc(EPageSize.A4, EPageOrientation.LANDSCAPE);
    doc.getPages().get(0).setMarginTop(10);
    doc.getPages().get(0).setMarginLeft(10);
    doc.getPages().get(0).setMarginRight(10);
    doc.getPages().get(0).setMarginBottom(10);
    PdfDocument<WI> docPdf = this.factory.createPdfDoc(doc);
    docPdf.getPdfInfo().setAuthor("Beigesoft (TM) Tester");
    IDocumentMaker docMaker = this.factory.lazyGetDocumentMaker();
    IPdfMaker<WI> pdfMaker = this.factory.lazyGetPdfMaker();
    pdfMaker.addFontTtf(docPdf, "LiberationSerif-Regular");
    //pdfMaker.addFontTtf(docPdf, ERegisteredTtfFont.DEJAVUSERIF.toString());
    double widthNdot = this.factory.lazyGetUomHelper()
      //.fromPoints(1.0, doc.getResolutionDpi(), doc.getUnitOfMeasure());
      .fromPoints(2.0, 300.0, doc.getUnitOfMeasure()); //printer resolution
    doc.setBorder(widthNdot);
    doc.setContentPadding(0.0);
    doc.setContentPaddingBottom(1.0);
    doc.setContentPaddingLeft(1.0);
    doc.setContentPaddingRight(1.0);
    DocTable<WI> tbl1 = docMaker
      .addDocTableNoBorder(doc, 1, 4);
    tbl1.getItsCells().get(0).setItsContent("ПРИЛОЖЕНИЕ N 1");
    tbl1.getItsCells().get(1).setItsContent("к постановлению Правительства");
    tbl1.getItsCells().get(2).setItsContent("Российской Федерации");
    tbl1.getItsCells().get(3).setItsContent("от 26 декабря 2011 г. N 1137");
    tbl1.setAlignHorizontal(EAlignHorizontal.RIGHT);
    docMaker.makeDocTableWrapping(tbl1);
    docMaker.setFontSize(doc, 4.0);
    DocTable<WI> tbl2 = docMaker.addDocTableCustomBorder(doc, 7, 2);
    tbl2.getItsCells().get(0).setItsContent("СЧЕТ-ФАКТУРА N");
    tbl2.getItsCells().get(0).setAlignHorizontal(EAlignHorizontal.RIGHT);
    tbl2.getItsColumns().get(0).setWraping(null);
    tbl2.getItsColumns().get(0).setIsWidthFixed(true);
    tbl2.getItsColumns().get(0).setWidth(70);
    tbl2.getItsCells().get(1).setItsContent(pData.getItsNumber());
    tbl2.getItsCells().get(1).setAlignHorizontal(EAlignHorizontal.CENTER);
    tbl2.getItsCells().get(1).setIsShowBorderBottom(true);
    tbl2.getItsColumns().get(1).setIsWidthFixed(true);
    tbl2.getItsColumns().get(1).setWidthInPercentage(30.0);
    tbl2.getItsCells().get(2).setItsContent("от \"");
    tbl2.getItsColumns().get(2).setWraping(EWraping.WRAP_CONTENT);
    tbl2.getItsColumns().get(2).setPaddingRight(0.0);
    tbl2.getItsCells().get(3).setItsContent(pData.getDay());
    tbl2.getItsCells().get(3).setIsShowBorderBottom(true);
    tbl2.getItsCells().get(3).setAlignHorizontal(EAlignHorizontal.CENTER);
    tbl2.getItsColumns().get(3).setWraping(EWraping.WRAP_CONTENT);
    tbl2.getItsCells().get(4).setItsContent("\"");
    tbl2.getItsColumns().get(4).setWraping(EWraping.WRAP_CONTENT);
    tbl2.getItsColumns().get(4).setPaddingLeft(0.0);
    tbl2.getItsCells().get(5).setItsContent(pData.getMonthYear());
    tbl2.getItsCells().get(5).setAlignHorizontal(EAlignHorizontal.CENTER);
    tbl2.getItsCells().get(5).setIsShowBorderBottom(true);
    tbl2.getItsCells().get(6).setItsContent("(1)");
    tbl2.getItsColumns().get(6).setWraping(null);
    tbl2.getItsColumns().get(6).setIsWidthFixed(true);
    tbl2.getItsColumns().get(6).setWidth(11);
    tbl2.getItsCells().get(0 + 7).setItsContent("ИСПРАВЛЕНИЕ N");
    tbl2.getItsCells().get(0 + 7).setAlignHorizontal(EAlignHorizontal.RIGHT);
    tbl2.getItsCells().get(1 + 7).setItsContent(pData.getChNumber());
    tbl2.getItsCells().get(1 + 7).setAlignHorizontal(EAlignHorizontal.CENTER);
    tbl2.getItsCells().get(1 + 7).setIsShowBorderBottom(true);
    tbl2.getItsCells().get(2 + 7).setItsContent("от \"");
    tbl2.getItsCells().get(3 + 7).setItsContent(pData.getChDay());
    tbl2.getItsCells().get(3 + 7).setAlignHorizontal(EAlignHorizontal.CENTER);
    tbl2.getItsCells().get(3 + 7).setIsShowBorderBottom(true);
    tbl2.getItsCells().get(4 + 7).setItsContent("\"");
    tbl2.getItsCells().get(5 + 7).setItsContent(pData.getChMonthYear());
    tbl2.getItsCells().get(5 + 7).setAlignHorizontal(EAlignHorizontal.CENTER);
    tbl2.getItsCells().get(5 + 7).setIsShowBorderBottom(true);
    tbl2.getItsCells().get(6 + 7).setItsContent("(1а)");
    tbl2.setWraping(null);
    tbl2.setIsWidthFixed(true);
    double headWd = (tbl2.getStartPage().getWidth() - tbl2.getStartPage()
      .getMarginLeft() - tbl2.getStartPage().getMarginRight()) * 0.7;
    tbl2.setWidth(headWd);
    doc.setContainerMarginBottom(0.0);
    DocTable<WI> tbl3 = docMaker.addDocTableCustomBorder(doc, 3, 2);
    tbl3.setWraping(null);
    tbl3.setIsWidthFixed(true);
    tbl3.setWidth(headWd);
    tbl3.getItsCells().get(0).setItsContent("Продавец");
    tbl3.getItsColumns().get(0).setIsWidthFixed(true);
    tbl3.getItsColumns().get(0).setWidthInPercentage(20.0);
    tbl3.getItsCells().get(1).setItsContent(pData.getSeller());
    tbl3.getItsCells().get(1).setAlignHorizontal(EAlignHorizontal.CENTER);
    tbl3.getItsCells().get(1).setIsShowBorderBottom(true);
    tbl3.getItsCells().get(2).setItsContent("(2)");
    tbl3.getItsColumns().get(2).setWraping(null);
    tbl3.getItsColumns().get(2).setIsWidthFixed(true);
    tbl3.getItsColumns().get(2).setWidth(11);
    tbl3.getItsCells().get(0 + 3).setItsContent("Адрес");
    tbl3.getItsCells().get(1 + 3).setItsContent(pData.getSellerAddr());
    tbl3.getItsCells().get(1 + 3).setAlignHorizontal(EAlignHorizontal.CENTER);
    tbl3.getItsCells().get(1 + 3).setIsShowBorderBottom(true);
    tbl3.getItsCells().get(2 + 3).setItsContent("(2а)");
    DocTable<WI> tbl4 = docMaker.addDocTableCustomBorder(doc, 3, 3);
    tbl4.setWraping(null);
    tbl4.setIsWidthFixed(true);
    tbl4.setWidth(headWd);
    tbl4.getItsCells().get(0).setItsContent("ИНН/КПП продавца");
    tbl4.getItsColumns().get(0).setIsWidthFixed(true);
    tbl4.getItsColumns().get(0).setWidthInPercentage(30.0);
    tbl4.getItsCells().get(1).setItsContent(pData.getSellerInnKpp());
    tbl4.getItsCells().get(1).setAlignHorizontal(EAlignHorizontal.CENTER);
    tbl4.getItsCells().get(1).setIsShowBorderBottom(true);
    tbl4.getItsCells().get(2).setItsContent("(2б)");
    tbl4.getItsColumns().get(2).setWraping(null);
    tbl4.getItsColumns().get(2).setIsWidthFixed(true);
    tbl4.getItsColumns().get(2).setWidth(11);
    tbl4.getItsCells().get(0 + 3).setItsContent("Грузоотправитель и его адрес");
    tbl4.getItsCells().get(1 + 3).setItsContent(pData.getGruzootpr());
    tbl4.getItsCells().get(1 + 3).setAlignHorizontal(EAlignHorizontal.CENTER);
    tbl4.getItsCells().get(1 + 3).setIsShowBorderBottom(true);
    tbl4.getItsCells().get(2 + 3).setItsContent("(3)");
    tbl4.getItsCells().get(0 + 6).setItsContent("Грузополучатель и его адрес");
    tbl4.getItsCells().get(1 + 6).setItsContent(pData.getGruzopol());
    tbl4.getItsCells().get(1 + 6).setAlignHorizontal(EAlignHorizontal.CENTER);
    tbl4.getItsCells().get(1 + 6).setIsShowBorderBottom(true);
    tbl4.getItsCells().get(2 + 6).setItsContent("(4)");
    docMaker.deriveElements(doc);
    pdfMaker.prepareBeforeWrite(docPdf);
    //pdfMaker.setIsCompressed(docPdf, false);
    this.factory.lazyGetPdfWriter().write(null, docPdf, pOus);
  }

  //Simple getters and setters:
  /**
   * <p>Getter for factory.</p>
   * @return IPdfFactory
   **/
  public final IPdfFactory<WI> getFactory() {
    return this.factory;
  }

  /**
   * <p>Setter for factory.</p>
   * @param pFactory reference
   **/
  public final void setFactory(final IPdfFactory<WI> pFactory) {
    this.factory = pFactory;
  }
}

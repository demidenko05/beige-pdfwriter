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
import org.beigesoft.doc.model.TableCell;
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
    docPdf.getPdfInfo().setCreationDate(new Date(1462867931627L));
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
    doc.setContentPaddingBottom(0.6);
    DocTable<WI> tbl1 = docMaker
      .addDocTableNoBorder(doc, 1, 4);
    tbl1.getItsCells().get(0).setItsContent("ПРИЛОЖЕНИЕ N 1");
    tbl1.getItsCells().get(1).setItsContent("к постановлению Правительства");
    tbl1.getItsCells().get(2).setItsContent("Российской Федерации");
    tbl1.getItsCells().get(3).setItsContent("от 26 декабря 2011 г. N 1137");
    tbl1.setAlignHorizontal(EAlignHorizontal.RIGHT);
    docMaker.makeDocTableWrapping(tbl1);
    doc.setContentPaddingBottom(1.0);
    doc.setContentPaddingLeft(1.0);
    doc.setContentPaddingRight(1.0);
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
    DocTable<WI> tbl5 = docMaker.addDocTableCustomBorder(doc, 5, 1);
    tbl5.setWraping(null);
    tbl5.setIsWidthFixed(true);
    tbl5.setWidth(headWd);
    tbl5.getItsCells().get(0).setItsContent("К платежно-расчетному документу N");
    tbl5.getItsColumns().get(0).setIsWidthFixed(true);
    tbl5.getItsColumns().get(0).setWidthInPercentage(40.0);
    tbl5.getItsCells().get(1).setItsContent(pData.getPrdNum());
    tbl5.getItsCells().get(1).setAlignHorizontal(EAlignHorizontal.CENTER);
    tbl5.getItsCells().get(1).setIsShowBorderBottom(true);
    tbl5.getItsColumns().get(1).setIsWidthFixed(true);
    tbl5.getItsColumns().get(1).setWidthInPercentage(25.0);
    tbl5.getItsCells().get(2).setItsContent("от");
    tbl5.getItsColumns().get(2).setWraping(EWraping.WRAP_CONTENT);
    tbl5.getItsCells().get(3).setItsContent(pData.getPrdData());
    tbl5.getItsCells().get(3).setAlignHorizontal(EAlignHorizontal.CENTER);
    tbl5.getItsCells().get(3).setIsShowBorderBottom(true);
    tbl5.getItsCells().get(4).setItsContent("(5)");
    tbl5.getItsColumns().get(4).setWraping(null);
    tbl5.getItsColumns().get(4).setIsWidthFixed(true);
    tbl5.getItsColumns().get(4).setWidth(11);
    DocTable<WI> tbl6 = docMaker.addDocTableCustomBorder(doc, 3, 2);
    tbl6.setWraping(null);
    tbl6.setIsWidthFixed(true);
    tbl6.setWidth(headWd);
    tbl6.getItsCells().get(0).setItsContent("Покупатель");
    tbl6.getItsColumns().get(0).setIsWidthFixed(true);
    tbl6.getItsColumns().get(0).setWidthInPercentage(20.0);
    tbl6.getItsCells().get(1).setItsContent(pData.getBuyer());
    tbl6.getItsCells().get(1).setAlignHorizontal(EAlignHorizontal.CENTER);
    tbl6.getItsCells().get(1).setIsShowBorderBottom(true);
    tbl6.getItsCells().get(2).setItsContent("(6)");
    tbl6.getItsColumns().get(2).setWraping(null);
    tbl6.getItsColumns().get(2).setIsWidthFixed(true);
    tbl6.getItsColumns().get(2).setWidth(11);
    tbl6.getItsCells().get(0 + 3).setItsContent("Адрес");
    tbl6.getItsCells().get(1 + 3).setItsContent(pData.getBuyerAddr());
    tbl6.getItsCells().get(1 + 3).setAlignHorizontal(EAlignHorizontal.CENTER);
    tbl6.getItsCells().get(1 + 3).setIsShowBorderBottom(true);
    tbl6.getItsCells().get(2 + 3).setItsContent("(6а)");
    doc.setContainerMarginBottom(3.0);
    DocTable<WI> tbl7 = docMaker.addDocTableCustomBorder(doc, 3, 1);
    tbl7.setWraping(null);
    tbl7.setIsWidthFixed(true);
    tbl7.setWidth(headWd);
    tbl7.getItsCells().get(0).setItsContent("Валюта: наименование, код ");
    tbl7.getItsColumns().get(0).setIsWidthFixed(true);
    tbl7.getItsColumns().get(0).setWidthInPercentage(30.0);
    tbl7.getItsCells().get(1).setItsContent(pData.getCurrency());
    tbl7.getItsCells().get(1).setAlignHorizontal(EAlignHorizontal.CENTER);
    tbl7.getItsCells().get(1).setIsShowBorderBottom(true);
    tbl7.getItsCells().get(2).setItsContent("(7)");
    tbl7.getItsColumns().get(2).setWraping(null);
    tbl7.getItsColumns().get(2).setIsWidthFixed(true);
    tbl7.getItsColumns().get(2).setWidth(11);
    doc.setContainerMarginBottom(4.0);
    doc.setContentPadding(1.0);
    DocTable<WI> tblGoods = docMaker
      .addDocTable(doc, 13, pData.getItems().size() + 4);
    tblGoods.setIsRepeatHead(true);
    tblGoods.getItsRows().get(0).setIsHead(true);
    tblGoods.getItsRows().get(1).setIsHead(true);
    tblGoods.getItsRows().get(2).setIsHead(true);
    tblGoods.getItsCells().get(0).setItsContent("Наименование товара\n(описание выполненных\nработ, оказанных услуг),\nимущественного права");
    tblGoods.getItsCells().get(0).setMergedCell(tblGoods.getItsCells().get(0 + 13));
    tblGoods.getItsCells().get(1).setItsContent("Единица\nизмерения");
    tblGoods.getItsColumns().get(1).setWraping(EWraping.WRAP_CONTENT);
    tblGoods.getItsColumns().get(2).setWraping(EWraping.WRAP_CONTENT);
    tblGoods.getItsCells().get(1).setMergedCell(tblGoods.getItsCells().get(2));
    tblGoods.getItsCells().get(3).setItsContent("Коли-\nчество\n(объем)");
    tblGoods.getItsCells().get(3).setMergedCell(tblGoods.getItsCells().get(3 + 13));
    tblGoods.getItsColumns().get(3).setWraping(EWraping.WRAP_CONTENT);
    tblGoods.getItsCells().get(4).setItsContent("Цена\n(тариф) за\nединицу\nизмерения");
    tblGoods.getItsCells().get(4).setMergedCell(tblGoods.getItsCells().get(4 + 13));
    tblGoods.getItsColumns().get(4).setWraping(EWraping.WRAP_CONTENT);
    tblGoods.getItsCells().get(5).setItsContent("Стоимость\nтоваров (работ,\nуслуг),\nимущественных\nправ без нало-\nга - всего");
    tblGoods.getItsCells().get(5).setMergedCell(tblGoods.getItsCells().get(5 + 13));
    tblGoods.getItsColumns().get(5).setWraping(EWraping.WRAP_CONTENT);
    tblGoods.getItsCells().get(6).setItsContent("В том\nчисле\nсумма\nакциза");
    tblGoods.getItsCells().get(6).setMergedCell(tblGoods.getItsCells().get(6 + 13));
    tblGoods.getItsColumns().get(6).setWraping(EWraping.WRAP_CONTENT);
    tblGoods.getItsCells().get(7).setItsContent("Нало-\nговая\nставка");
    tblGoods.getItsCells().get(7).setMergedCell(tblGoods.getItsCells().get(7 + 13));
    tblGoods.getItsColumns().get(7).setWraping(EWraping.WRAP_CONTENT);
    tblGoods.getItsCells().get(8).setItsContent("Сумма\nналога,\nпредъяв-\nляемая\nпокупателю");
    tblGoods.getItsCells().get(8).setMergedCell(tblGoods.getItsCells().get(8 + 13));
    tblGoods.getItsColumns().get(8).setWraping(EWraping.WRAP_CONTENT);
    tblGoods.getItsCells().get(9).setItsContent("Стоимость\nтоваров (работ,\nуслуг),\nимущественных\nправ с нало-\nгом - всего");
    tblGoods.getItsCells().get(9).setMergedCell(tblGoods.getItsCells().get(9 + 13));
    tblGoods.getItsColumns().get(9).setWraping(EWraping.WRAP_CONTENT);
    tblGoods.getItsCells().get(10).setItsContent("Страна\nпроисхождения\nтовара");
    tblGoods.getItsColumns().get(10).setWraping(EWraping.WRAP_CONTENT);
    tblGoods.getItsColumns().get(11).setWraping(EWraping.WRAP_CONTENT);
    tblGoods.getItsCells().get(10).setMergedCell(tblGoods.getItsCells().get(11));
    tblGoods.getItsCells().get(12).setItsContent("Номер\nтамо-\nженной\nдекла-\nрации");
    tblGoods.getItsCells().get(12).setMergedCell(tblGoods.getItsCells().get(12 + 13));
    tblGoods.getItsColumns().get(12).setWraping(EWraping.WRAP_CONTENT);
    tblGoods.getItsCells().get(1 + 13).setItsContent("код");
    tblGoods.getItsCells().get(2 + 13).setItsContent("условное\nобозначение\n(нацио-\nнальное)");
    tblGoods.getItsCells().get(10 + 13).setItsContent("цифровой\nкод");
    tblGoods.getItsCells().get(11 + 13).setItsContent("краткое\nнаиме-\nнование");
    tblGoods.getItsCells().get(0 + 26).setItsContent("1");
    tblGoods.getItsCells().get(1 + 26).setItsContent("2");
    tblGoods.getItsCells().get(2 + 26).setItsContent("2а");
    tblGoods.getItsCells().get(3 + 26).setItsContent("3");
    tblGoods.getItsCells().get(4 + 26).setItsContent("4");
    tblGoods.getItsCells().get(5 + 26).setItsContent("5");
    tblGoods.getItsCells().get(6 + 26).setItsContent("6");
    tblGoods.getItsCells().get(7 + 26).setItsContent("7");
    tblGoods.getItsCells().get(8 + 26).setItsContent("8");
    tblGoods.getItsCells().get(9 + 26).setItsContent("9");
    tblGoods.getItsCells().get(10 + 26).setItsContent("10");
    tblGoods.getItsCells().get(11 + 26).setItsContent("10а");
    tblGoods.getItsCells().get(12 + 26).setItsContent("11");
    for (int i = 0; i < 13 * 3; i++) {
      tblGoods.getItsCells().get(i).setAlignHorizontal(EAlignHorizontal.CENTER);
    }
    int j = 3;
    for (SfacturaLineModel ln : pData.getItems()) {
      int i = 0;
      tblGoods.getItsCells().get(j * 13 + i++).setItsContent(ln.getItem());
      tblGoods.getItsCells().get(j * 13 + i++).setItsContent(ln.getUomCode());
      tblGoods.getItsCells().get(j * 13 + i++).setItsContent(ln.getUom());
      tblGoods.getItsCells().get(j * 13 + i++).setItsContent(print(ln.getQuantity().toString()));
      tblGoods.getItsCells().get(j * 13 + i++).setItsContent(print(ln.getPrice().toString()));
      tblGoods.getItsCells().get(j * 13 + i++).setItsContent(print(ln.getSubtotal().toString()));
      tblGoods.getItsCells().get(j * 13 + i++).setItsContent(ln.getAkciz());
      tblGoods.getItsCells().get(j * 13 + i++).setItsContent(ln.getTaxRate().toString() + "%");
      tblGoods.getItsCells().get(j * 13 + i++).setItsContent(print(ln.getTotalTaxes().toString()));
      tblGoods.getItsCells().get(j * 13 + i++).setItsContent(print(ln.getTotal().toString()));
      tblGoods.getItsCells().get(j * 13 + i++).setItsContent("-");
      tblGoods.getItsCells().get(j * 13 + i++).setItsContent("-");
      tblGoods.getItsCells().get(j * 13 + i++).setItsContent("-");
      for (int x = 1; x < 13; x++) {
        tblGoods.getItsCells().get(j * 13 + x).setAlignHorizontal(EAlignHorizontal.CENTER);
      }
      j++;
    }
    tblGoods.getItsRows().get(tblGoods.getItsRows().size() - 1).setIfHasCustomBordersBelow(true);
    int lastRowAddr = 13 * (pData.getItems().size() + 3);
    tblGoods.getItsCells().get(0 + lastRowAddr).setItsContent("Всего к оплате");
    tblGoods.getItsCells().get(0 + lastRowAddr).setMergedCell(tblGoods.getItsCells().get(1 + lastRowAddr));
    tblGoods.getItsCells().get(1 + lastRowAddr).setMergedCell(tblGoods.getItsCells().get(2 + lastRowAddr));
    tblGoods.getItsCells().get(2 + lastRowAddr).setMergedCell(tblGoods.getItsCells().get(3 + lastRowAddr));
    tblGoods.getItsCells().get(5 + lastRowAddr).setAlignHorizontal(EAlignHorizontal.CENTER);
    tblGoods.getItsCells().get(5 + lastRowAddr).setItsContent(print(pData.getSubtotal().toString()));
    //System.out.println(pData.getSubtotal().toString()));
    //System.out.println(pData.getTotalTaxes().toString())); // wrong wrapped data can overflow table width
    //System.out.println(pData.getTotal().toString()));
    tblGoods.getItsCells().get(6 + lastRowAddr).setItsContent("X");
    tblGoods.getItsCells().get(6 + lastRowAddr).setAlignHorizontal(EAlignHorizontal.CENTER);
    tblGoods.getItsCells().get(6 + lastRowAddr).setMergedCell(tblGoods.getItsCells().get(7 + lastRowAddr));
    tblGoods.getItsCells().get(8 + lastRowAddr).setItsContent(print(pData.getTotalTaxes().toString()));
    tblGoods.getItsCells().get(8 + lastRowAddr).setAlignHorizontal(EAlignHorizontal.CENTER);
    tblGoods.getItsCells().get(9 + lastRowAddr).setItsContent(print(pData.getTotal().toString()));
    tblGoods.getItsCells().get(9 + lastRowAddr).setAlignHorizontal(EAlignHorizontal.CENTER);
    tblGoods.getItsCells().get(0 + lastRowAddr).setIsShowBorderLeft(true);
    tblGoods.getItsCells().get(0 + lastRowAddr).setIsShowBorderBottom(true);
    tblGoods.getItsCells().get(1 + lastRowAddr).setIsShowBorderBottom(true);
    tblGoods.getItsCells().get(2 + lastRowAddr).setIsShowBorderBottom(true);
    tblGoods.getItsCells().get(3 + lastRowAddr).setIsShowBorderBottom(true);
    tblGoods.getItsCells().get(4 + lastRowAddr).setIsShowBorderBottom(true);
    tblGoods.getItsCells().get(4 + lastRowAddr).setIsShowBorderRight(true);
    tblGoods.getItsCells().get(5 + lastRowAddr).setIsShowBorderBottom(true);
    tblGoods.getItsCells().get(5 + lastRowAddr).setIsShowBorderRight(true);
    tblGoods.getItsCells().get(6 + lastRowAddr).setIsShowBorderBottom(true);
    tblGoods.getItsCells().get(7 + lastRowAddr).setIsShowBorderBottom(true);
    tblGoods.getItsCells().get(7 + lastRowAddr).setIsShowBorderRight(true);
    tblGoods.getItsCells().get(8 + lastRowAddr).setIsShowBorderBottom(true);
    tblGoods.getItsCells().get(8 + lastRowAddr).setIsShowBorderRight(true);
    tblGoods.getItsCells().get(9 + lastRowAddr).setIsShowBorderBottom(true);
    tblGoods.getItsCells().get(9 + lastRowAddr).setIsShowBorderRight(true);
    doc.setContainerMarginBottom(1.0);
    DocTable<WI> tblSi = docMaker.addDocTableCustomBorder(doc, 8, 2);
    tblSi.getItsCells().get(0).setItsContent("Руководитель организации или иное уполномоченное лицо");
    tblSi.getItsColumns().get(0).setIsWidthFixed(true);
    tblSi.getItsColumns().get(0).setWidthInPercentage(22.0);
    tblSi.getItsCells().get(1).setIsShowBorderBottom(true);
    tblSi.getItsColumns().get(2).setWraping(EWraping.WRAP_CONTENT);
    tblSi.getItsCells().get(3).setIsShowBorderBottom(true);
    tblSi.getItsCells().get(4).setItsContent("Главный бухгалтер или иное уполномоченное лицо");
    tblSi.getItsColumns().get(4).setIsWidthFixed(true);
    tblSi.getItsColumns().get(4).setWidthInPercentage(22.0);
    tblSi.getItsCells().get(5).setIsShowBorderBottom(true);
    tblSi.getItsColumns().get(6).setWraping(EWraping.WRAP_CONTENT);
    tblSi.getItsCells().get(7).setIsShowBorderBottom(true);
    tblSi.getItsCells().get(1 + 8).setItsContent("(подпись)");
    tblSi.getItsCells().get(1 + 8).setAlignHorizontal(EAlignHorizontal.CENTER);
    tblSi.getItsCells().get(3 + 8).setItsContent("(ф.и.о.)");
    tblSi.getItsCells().get(3 + 8).setAlignHorizontal(EAlignHorizontal.CENTER);
    tblSi.getItsCells().get(5 + 8).setItsContent("(подпись)");
    tblSi.getItsCells().get(5 + 8).setAlignHorizontal(EAlignHorizontal.CENTER);
    tblSi.getItsCells().get(7 + 8).setItsContent("(ф.и.о.)");
    tblSi.getItsCells().get(7 + 8).setAlignHorizontal(EAlignHorizontal.CENTER);
    DocTable<WI> tblIp = docMaker.addDocTableCustomBorder(doc, 6, 2);
    tblIp.getItsCells().get(0).setItsContent("Индивидуальный предприниматель");
    tblIp.getItsColumns().get(0).setIsWidthFixed(true);
    tblIp.getItsColumns().get(0).setWidthInPercentage(25.0);
    tblIp.getItsCells().get(1).setIsShowBorderBottom(true);
    tblIp.getItsColumns().get(2).setWraping(EWraping.WRAP_CONTENT);
    tblIp.getItsCells().get(3).setIsShowBorderBottom(true);
    tblIp.getItsColumns().get(4).setWraping(EWraping.WRAP_CONTENT);
    tblIp.getItsCells().get(5).setIsShowBorderBottom(true);
    tblIp.getItsColumns().get(5).setIsWidthFixed(true);
    tblIp.getItsColumns().get(5).setWidthInPercentage(40.0);
    tblIp.getItsCells().get(1 + 6).setItsContent("(подпись)");
    tblIp.getItsCells().get(1 + 6).setAlignHorizontal(EAlignHorizontal.CENTER);
    tblIp.getItsCells().get(3 + 6).setItsContent("(ф.и.о.)");
    tblIp.getItsCells().get(3 + 6).setAlignHorizontal(EAlignHorizontal.CENTER);
    tblIp.getItsCells().get(5 + 6).setItsContent("(реквизиты свидетельства о государственной регистрации индивидуального предпринимателя)");
    tblIp.getItsCells().get(5 + 6).setAlignHorizontal(EAlignHorizontal.CENTER);
    docMaker.deriveElements(doc);
    pdfMaker.prepareBeforeWrite(docPdf);
    //pdfMaker.setIsCompressed(docPdf, false);
    this.factory.lazyGetPdfWriter().write(null, docPdf, pOus);
  }


  /**
   * <p>Prints number formatted by given decimal separator, decimal group
   * separator and decimal places after dot. Digits in group is 3.</p>
   * @param pNumber e.g. "12146678.12"
   * @return String internationalized number, e.g. "12 146 678,12"
   **/
  public final String print(final String pNumber) {
    return print(pNumber, ",", " ", 2, 3);
  }

  /**
   * <p>Prints number formatted by given decimal separator, decimal group
   * separator, decimal places after dot and digits in group.</p>
   * @param pNumber e.g. "12146678.12"
   * @param pDigSep decimal separator, e.g. ","
   * @param pDigGrSep decimal group separator, e.g. " "
   * @param pDecPlAfDot decimal places after dot, e.g. 2
   * @param pDigitsInGroup Digits in group, e.g. 3
   * @return String internationalized number, e.g. "12 146 678,12"
   **/
  public final String print(final String pNumber, final String pDigSep,
    final String pDigGrSep, final Integer pDecPlAfDot,
      final Integer pDigitsInGroup) {
    if (pNumber == null || "".equals(pNumber)) {
      return "";
    }
    int dotIdx = pNumber.indexOf(".");
    String leftWing;
    String rightWing;
    if (dotIdx == -1) {
      leftWing = pNumber;
      rightWing = null;
    } else {
      leftWing = pNumber.substring(0, dotIdx);
      rightWing = pNumber.substring(dotIdx + 1);
    }
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < leftWing.length(); i++) {
      char ch = leftWing.charAt(i);
      sb.append(ch);
      int idxFl = leftWing.length() - i;
      if (pDigitsInGroup == 2) {
        //hard-coded Indian style 12,12,14,334
        if (idxFl == 4) {
          sb.append(pDigGrSep);
        } else {
          idxFl -= 3;
        }
      }
      if (idxFl >= pDigitsInGroup) {
        int gc = idxFl / pDigitsInGroup;
        if (gc > 0) {
          int rem;
          if (gc == 1) {
            rem = idxFl % pDigitsInGroup;
          } else {
            rem = idxFl % (pDigitsInGroup * gc);
          }
          if (rem == 1) {
            sb.append(pDigGrSep);
          }
        }
      }
    }
    if (pDecPlAfDot > 0) {
      sb.append(pDigSep);
      for (int i = 0; i < pDecPlAfDot; i++) {
        if (rightWing != null && rightWing.length() > i) {
          sb.append(rightWing.charAt(i));
        } else {
          sb.append("0");
        }
      }
    }
    return sb.toString();
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

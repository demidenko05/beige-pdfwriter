package org.beigesoft.pdf.sample;

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

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.Date;

import org.beigesoft.doc.model.EUnitOfMeasure;
import org.beigesoft.doc.model.Document;
import org.beigesoft.doc.model.DocTable;
import org.beigesoft.doc.model.Pagination;
import org.beigesoft.doc.model.EWraping;
import org.beigesoft.doc.model.MetricsString;
import org.beigesoft.doc.model.EAlignHorizontal;
import org.beigesoft.doc.model.EPageSize;
import org.beigesoft.doc.model.EPageOrientation;
import org.beigesoft.pdf.model.ERegisteredTtfFont;
import org.beigesoft.pdf.model.PdfDocument;
import org.beigesoft.pdf.service.IPdfFactory;

/**
 * <p>Invoice report generic sample.</p>
 *
 * @author Yury Demidenko
 */
public class InvoiceReport<WI> {

  /**
   * <p>Factory.</p>
   **/
  private IPdfFactory<WI> factory;

  /**
   * <p>Date format.</p>
   **/
  private DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

  /**
   * <p>Make PDF file for given invoice.</p>
   **/
  public final void makePdf(final InvoiceModel pInvoice) throws Exception {
    Document<WI> doc = this.factory.lazyGetFctDocument()
      .createDoc(EPageSize.A4, EPageOrientation.PORTRAIT);
    PdfDocument<WI> docPdf = this.factory.createPdfDoc(doc);
    this.factory.lazyGetPdfMaker()
      .addFontTtf(docPdf, ERegisteredTtfFont.DEJAVUSANS_BOLD.toString());
    this.factory.lazyGetPdfMaker()
      .addFontTtf(docPdf, ERegisteredTtfFont.DEJAVUSANS.toString());
    double width1dot = this.factory.lazyGetUomHelper()
      .fromPoints(1.0, doc.getResolutionDpi(), doc.getUnitOfMeasure());
    doc.setBorder(width1dot);
    doc.setContentPadding(0.0);
    doc.setContentPaddingBottom(0.3);
    DocTable<WI> tblOwner = this.factory.lazyGetDocumentMaker()
      .addDocTableNoBorder(doc, 1, pInvoice.getOwnerInfo().size());
    for (int i = 0; i < pInvoice.getOwnerInfo().size(); i++) {
      tblOwner.getItsCells().get(i)
        .setItsContent(pInvoice.getOwnerInfo().get(i));
    }
    tblOwner.getItsCells().get(0).setFontNumber(1);
    tblOwner.setAlignHorizontal(EAlignHorizontal.RIGHT);
    this.factory.lazyGetDocumentMaker().makeDocTableWrapping(tblOwner);
    DocTable<WI> tblTitle = this.factory.lazyGetDocumentMaker()
      .addDocTableNoBorder(doc, 1, 1);
    String title = "Invoice #" + pInvoice.getItsNumber() + " "
      + this.dateFormat.format(pInvoice.getItsDate());
    tblTitle.getItsCells().get(0).setItsContent(title);
    tblTitle.getItsCells().get(0).setFontNumber(1);
    tblTitle.setAlignHorizontal(EAlignHorizontal.CENTER);
    doc.setContainerMarginBottom(1.0);
    this.factory.lazyGetDocumentMaker().makeDocTableWrapping(tblTitle);
    DocTable<WI> tblCustomer = this.factory.lazyGetDocumentMaker()
      .addDocTableNoBorder(doc, 1, pInvoice.getCustomerInfo().size());
    for (int i = 0; i < pInvoice.getCustomerInfo().size(); i++) {
      tblCustomer.getItsCells().get(i)
        .setItsContent(pInvoice.getCustomerInfo().get(i));
    }
    tblCustomer.getItsCells().get(0).setFontNumber(1);
    doc.setContainerMarginBottom(2.0);
    DocTable<WI> tblTiGoods = this.factory.lazyGetDocumentMaker()
      .addDocTableNoBorder(doc, 1, 1);
    tblTiGoods.getItsCells().get(0).setItsContent("Goods:");
    tblTiGoods.getItsCells().get(0).setFontNumber(1);
    tblTiGoods.setAlignHorizontal(EAlignHorizontal.CENTER);
    this.factory.lazyGetDocumentMaker().makeDocTableWrapping(tblTiGoods);
    doc.setContentPadding(1.0);
    DocTable<WI> tblGoods = this.factory.lazyGetDocumentMaker()
      .addDocTable(doc, 8, pInvoice.getItems().size() + 1);
    tblGoods.setIsRepeatHead(true);
    tblGoods.getItsRows().get(0).setIsHead(true);
    tblGoods.getItsCells().get(0).setItsContent("Item");
    tblGoods.getItsColumns().get(0).setIsWidthFixed(true);
    tblGoods.getItsColumns().get(0).setWidthInPercentage(60.0);
    tblGoods.getItsCells().get(1).setItsContent("UOM");
    tblGoods.getItsColumns().get(1).setWraping(EWraping.WRAP_CONTENT);
    tblGoods.getItsCells().get(2).setItsContent("Price");
    tblGoods.getItsColumns().get(2).setWraping(EWraping.WRAP_CONTENT);
    tblGoods.getItsCells().get(3).setItsContent("Quantity");
    tblGoods.getItsColumns().get(3).setWraping(EWraping.WRAP_CONTENT);
    tblGoods.getItsCells().get(4).setItsContent("Subtotal");
    tblGoods.getItsColumns().get(4).setWraping(EWraping.WRAP_CONTENT);
    tblGoods.getItsCells().get(5).setItsContent("Taxes");
    // good trick for wrapping multistring:
    tblGoods.getItsCells().get(6).setItsContent("Total\ntaxes");
    tblGoods.getItsColumns().get(6).setWraping(EWraping.WRAP_CONTENT);
    // it's allowed too:
    //tblGoods.getItsCells().get(6).setItsContent("Total taxes");
    //tblGoods.getItsColumns().get(6).setWraping(null);
    //tblGoods.getItsColumns().get(6).setIsWidthFixed(true);
    //MetricsString msTxs = this.factory.lazyGetEvalMetricsString()
      //.eval("taxes", ERegisteredTtfFont.DEJAVUSANS_BOLD.toString(),
        //3.5, 300.0, 0.0);
    //tblGoods.getItsColumns().get(6).setWidth(msTxs.getWidth() + 4);
    tblGoods.getItsCells().get(7).setItsContent("Total");
    tblGoods.getItsColumns().get(7).setWraping(EWraping.WRAP_CONTENT);
    for (int i = 0; i < 8; i++) {
      tblGoods.getItsCells().get(i).setFontNumber(1);
      tblGoods.getItsCells().get(i).setAlignHorizontal(EAlignHorizontal.CENTER);
    }
    int j = 1;
    for (InvoiceLineModel ln : pInvoice.getItems()) {
      int i = 0;
      tblGoods.getItsCells().get(j * 8 + i++).setItsContent(ln.getItem());
      tblGoods.getItsCells().get(j * 8 + i++).setItsContent(ln.getUom());
      tblGoods.getItsCells().get(j * 8 + i++).setItsContent(ln.getPrice().toString());
      tblGoods.getItsCells().get(j * 8 + i++).setItsContent(ln.getQuantity().toString());
      tblGoods.getItsCells().get(j * 8 + i++).setItsContent(ln.getSubtotal().toString());
      tblGoods.getItsCells().get(j * 8 + i++).setItsContent(ln.getTaxes());
      tblGoods.getItsCells().get(j * 8 + i++).setItsContent(ln.getTotalTaxes().toString());
      tblGoods.getItsCells().get(j * 8 + i++).setItsContent(ln.getTotal().toString());
      j++;
    }
    doc.setAlignHoriCont(EAlignHorizontal.RIGHT);
    DocTable<WI> tblRez = this.factory.lazyGetDocumentMaker()
      .addDocTableNoBorder(doc, 1, 3);
    tblRez.getItsCells().get(0).setFontNumber(1);
    tblRez.getItsCells().get(0).setItsContent("Subtotal: " + pInvoice.getSubtotal());
    tblRez.getItsCells().get(1).setFontNumber(1);
    tblRez.getItsCells().get(1).setItsContent("Total taxes: " + pInvoice.getTotalTaxes());
    tblRez.getItsCells().get(2).setFontNumber(1);
    tblRez.getItsCells().get(2).setItsContent("Total: " + pInvoice.getTotal());
    tblRez.setAlignHorizontal(EAlignHorizontal.RIGHT);
    this.factory.lazyGetDocumentMaker().makeDocTableWrapping(tblRez);
    // Add it at the end, make sure that 1 is current page!:
    Pagination<WI> paging = this.factory.lazyGetDocumentMaker().addPagination(doc);
    //paging.setTitle(this.dateFormat.format(new Date()) + ",    Page ");
    //paging.setFrom(" from ");
    this.factory.lazyGetDocumentMaker().deriveElements(doc);
    this.factory.lazyGetPdfMaker().prepareBeforeWrite(docPdf);
    //this.factory.lazyGetPdfMaker().setIsCompressed(docPdf, false);
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream("invoice" + pInvoice.getItsNumber() + ".pdf");
      this.factory.lazyGetPdfWriter().write(null, docPdf, fos);
      fos.flush();
      fos.close();
    } finally {
      if (fos != null) {
        fos.close();
      }
    }
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

  /**
   * <p>Getter for dateFormat.</p>
   * @return DateFormat
   **/
  public final DateFormat getDateFormat() {
    return this.dateFormat;
  }

  /**
   * <p>Setter for dateFormat.</p>
   * @param pDateFormat reference
   **/
  public final void setDateFormat(final DateFormat pDateFormat) {
    this.dateFormat = pDateFormat;
  }
}

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
 * <p>Invoice report generic sample.</p>
 *
 * @param <WI> writing instrument type
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
   * <p>Write PDF report for given invoice to output stream.</p>
   * @param pInvoice Invoice
   * @param pOus output stream
   * @param pIsInsertImg if need image demonstration
   **/
  public final void makePdf(final InvoiceModel pInvoice,
    final OutputStream pOus, final boolean pIsInsertImg) throws Exception {
    // it makes document with page A4 and margins LTRB 30*20*20*20, UOM=millimeters,
    // font size 3.5:
    Document<WI> doc = this.factory.lazyGetFctDocument()
      .createDoc(EPageSize.A4, EPageOrientation.PORTRAIT);
    //EPageSize.LETTER makes UOM=inch with no margins and font size!!! See source code.
    PdfDocument<WI> docPdf = this.factory.createPdfDoc(doc);
    docPdf.getPdfInfo().setAuthor("Beigesoft (TM) Tester");
    IDocumentMaker docMaker = this.factory.lazyGetDocumentMaker();
    IPdfMaker<WI> pdfMaker = this.factory.lazyGetPdfMaker();
    //just simple image with no mask demonstration:
    if (pIsInsertImg) {
      // X1, Y1 are upper left corner (Y axis starts from top of page)
      // - this is for all elements - images, strings, lines...
      DocImage<WI> dimg2 = docMaker.addImage(doc, "/img/bob-signature.png", 110, 10);
      dimg2.setScale(0.15);
      dimg2.setRotateDegrees(10);
      pdfMaker.addImage(docPdf, dimg2);
    }
    pdfMaker.addFontTtf(docPdf, ERegisteredTtfFont.DEJAVUSERIF_BOLD.toString());
    pdfMaker.addFontTtf(docPdf, ERegisteredTtfFont.DEJAVUSERIF.toString());
    double widthNdot = this.factory.lazyGetUomHelper()
      //.fromPoints(1.0, doc.getResolutionDpi(), doc.getUnitOfMeasure());
      .fromPoints(2.0, 300.0, doc.getUnitOfMeasure()); //printer resolution
    doc.setBorder(widthNdot);
    doc.setContentPadding(0.0);
    doc.setContentPaddingBottom(0.5);
    DocTable<WI> tblOwner = docMaker.addDocTableNoBorder(doc, 1, 1);
    tblOwner.getItsCells().get(0)
      .setItsContent(pInvoice.getOwnerInfo().get(0));
    for (int i = 1; i < pInvoice.getOwnerInfo().size(); i++) {
      docMaker.addRowToDocTable(tblOwner);
      tblOwner.getItsCells().get(i)
        .setItsContent(pInvoice.getOwnerInfo().get(i));
    }
    tblOwner.getItsCells().get(0).setFontNumber(1);
    tblOwner.setAlignHorizontal(EAlignHorizontal.RIGHT);
    docMaker.makeDocTableWrapping(tblOwner);
    DocTable<WI> tblTitle = docMaker.addDocTableNoBorder(doc, 1, 1);
    String title = "Invoice #" + pInvoice.getItsNumber() + " "
      + this.dateFormat.format(pInvoice.getItsDate());
    tblTitle.getItsCells().get(0).setItsContent(title);
    tblTitle.getItsCells().get(0).setFontNumber(1);
    tblTitle.setAlignHorizontal(EAlignHorizontal.CENTER);
    docMaker.makeDocTableWrapping(tblTitle);
    doc.setContainerMarginBottom(1.0);
    DocTable<WI> tblCustomer = docMaker
      .addDocTableNoBorder(doc, 1, pInvoice.getCustomerInfo().size());
    for (int i = 0; i < pInvoice.getCustomerInfo().size(); i++) {
      tblCustomer.getItsCells().get(i)
        .setItsContent(pInvoice.getCustomerInfo().get(i));
    }
    tblCustomer.getItsCells().get(0).setFontNumber(1);
    doc.setContainerMarginBottom(2.0);
    DocTable<WI> tblTiGoods = docMaker
      .addDocTableNoBorder(doc, 1, 1);
    tblTiGoods.getItsCells().get(0).setItsContent("Goods:");
    tblTiGoods.getItsCells().get(0).setFontNumber(1);
    tblTiGoods.setAlignHorizontal(EAlignHorizontal.CENTER);
    docMaker.makeDocTableWrapping(tblTiGoods);
    doc.setContentPadding(1.0);
    DocTable<WI> tblGoods = docMaker
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
    DocTable<WI> tblRez = docMaker
      .addDocTableNoBorder(doc, 1, 3);
    tblRez.getItsCells().get(0).setFontNumber(1);
    tblRez.getItsCells().get(0).setItsContent("Subtotal: " + pInvoice.getSubtotal());
    tblRez.getItsCells().get(1).setFontNumber(1);
    tblRez.getItsCells().get(1).setItsContent("Total taxes: " + pInvoice.getTotalTaxes());
    tblRez.getItsCells().get(2).setFontNumber(1);
    tblRez.getItsCells().get(2).setItsContent("Total: " + pInvoice.getTotal());
    tblRez.setAlignHorizontal(EAlignHorizontal.RIGHT);
    docMaker.makeDocTableWrapping(tblRez);
    // paging.start = 1 by default (from first page), from = "/", so it will be "1/1":
    Pagination<WI> paging = docMaker.addPagination(doc);
    //paging.setTitle(this.dateFormat.format(new Date()) + ",    Page ");
    //paging.setFrom(" from ");
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

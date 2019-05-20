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

package org.beigesoft.pdf.sample;

import java.util.Date;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.io.FileOutputStream;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.beigesoft.log.LogSmp;
import org.beigesoft.pdf.model.HasPdfContent;
import org.beigesoft.pdf.service.PdfFactory;
import org.beigesoft.doc.service.DocumentMaker;
import org.beigesoft.graphic.swing.service.SwingImageLoader;

/**
 * <p>Write Invoice tests.</p>
 *
 * @author Yury Demidenko
 */
public class WriteInvoiceTest {

  private PdfFactory factory;
    
  public WriteInvoiceTest() throws Exception {
    LogSmp logger = new LogSmp();
    logger.setDbgSh(true);
    logger.setDbgFl(4000);
    logger.setDbgCl(4999);
    this.factory = new PdfFactory();
    this.factory.setLog(logger);
    this.factory.init();
    this.factory.lazyGetFctElement().setFctImageRgb(new SwingImageLoader());
  }
  
  @Test
  public void testToPdf() throws Exception {
    assertTrue("Factory's paging deriver must not be null!!!",
      this.factory.lazyGetDeriverElPagination() != null);
    DocumentMaker<HasPdfContent> dm =
      (DocumentMaker<HasPdfContent>) this.factory.lazyGetDocumentMaker();
    assertTrue("DocMaker's paging deriver must not be null!!!", dm != null);
    InvoiceModel im = new InvoiceModel();
    im.setItsNumber("A-68687687");
    im.setItsDate(new Date());
    im.setOwnerInfo(new ArrayList<String>());
    im.getOwnerInfo().add("Bob's Pizza");
    im.getOwnerInfo().add("TIN: 103623-1231-13793");
    im.getOwnerInfo().add("Zip: 12331");
    im.getOwnerInfo().add("Address: Buffalo Grove 12");
    im.getOwnerInfo().add("City: NY");
    im.getOwnerInfo().add("State: NY");
    im.setCustomerInfo(new ArrayList<String>());
    im.getCustomerInfo().add("Carlos's Grocery");
    im.getCustomerInfo().add("TIN: 231213-3232-32242");
    im.getCustomerInfo().add("Zip: 465465");
    im.getCustomerInfo().add("Address: Red Square 11");
    im.getCustomerInfo().add("City: NY");
    im.getCustomerInfo().add("State: NY");
    im.setItems(new ArrayList<InvoiceLineModel>());
    im.getItems().add(new InvoiceLineModel("Pizza with cheese frozen",
      "each", new BigDecimal("7.99"), new BigDecimal("6.0"),
        "Fake Sales Tax 6.0%=2.88",  new BigDecimal("2.88")));
    im.getItems().add(new InvoiceLineModel("Pizza with bacon frozen", "each",
      new BigDecimal("7.99"), new BigDecimal("6.0")));
    for (int i = 0; i < 85; i++) {
      im.getItems().add(new InvoiceLineModel("Pizza#" + i, "each",
      new BigDecimal("7.19"), new BigDecimal("1.0")));
    }
    for (InvoiceLineModel ilm : im.getItems()) {
      im.setSubtotal(im.getSubtotal().add(ilm.getSubtotal()));
      im.setTotalTaxes(im.getTotalTaxes().add(ilm.getTotalTaxes()));
      im.setTotal(im.getTotal().add(ilm.getTotal()));
    }
    InvoiceReport ir = new InvoiceReport();
    ir.setFactory(this.factory);
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream("invoice" + im.getItsNumber() + ".pdf");
      ir.makePdf(im, fos, true);
    } finally {
      if (fos != null) {
        fos.close();
      }
    }
  }
}

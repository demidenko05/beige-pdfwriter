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

import java.util.Date;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import org.beigesoft.log.LoggerSimple;
import org.beigesoft.pdf.service.PdfFactory;

/**
 * <p>Write Invoice tests.</p>
 *
 * @author Yury Demidenko
 */
public class WriteInvoiceTest {

  private PdfFactory factory;
    
  public WriteInvoiceTest() throws Exception {
    LoggerSimple logger = new LoggerSimple();
    logger.setIsShowDebugMessages(true);
    this.factory = new PdfFactory();
    this.factory.setLogger(logger);
    this.factory.init();
  }
  
  @Test
  public void testToPdf() throws Exception {
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
      "each", "7.99", "6.0", "47.94", "Fake Sales Tax 6.0%=2.88", "2.88", "50.82"));
    im.getItems().add(new InvoiceLineModel("Pizza with bacon frozen", "each",
      "7.99", "6.0", "47.94", " ", " ", "47.94"));
    InvoiceReport ir = new InvoiceReport();
    ir.setFactory(this.factory);
    ir.makePdf(im);
  }
}

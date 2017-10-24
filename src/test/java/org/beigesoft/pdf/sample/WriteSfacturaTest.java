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

import java.util.Date;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.io.FileOutputStream;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.beigesoft.log.LoggerSimple;
import org.beigesoft.pdf.model.HasPdfContent;
import org.beigesoft.pdf.service.PdfFactory;
import org.beigesoft.doc.service.DocumentMaker;

/**
 * <p>Write Sfactura tests.</p>
 *
 * @author Yury Demidenko
 */
public class WriteSfacturaTest {

  private PdfFactory factory;
    
  public WriteSfacturaTest() throws Exception {
    LoggerSimple logger = new LoggerSimple();
    logger.setIsShowDebugMessages(true);
    this.factory = new PdfFactory();
    this.factory.setLogger(logger);
    this.factory.init();
  }
  
  @Test
  public void testToPdf() throws Exception {
    SfacturaModel pl = new SfacturaModel();
    pl.setItsNumber("1");
    pl.setDay("22");
    pl.setMonthYear("мая 2017");
    pl.setTotal(new BigDecimal(1452.23));
    pl.setSeller("ООО Березка");
    pl.setSellerAddr("123122, г.Москва, ул. Ленина д.56");
    pl.setSellerInnKpp("154545/124484");
    pl.setGruzootpr("ООО Березка 123122, г.Москва, ул. Ленина д.56");
    pl.setGruzopol("ООО Тополь 1212121, г.Ковров, ул. Ленина д.44");
    SfacturaReport plr = new SfacturaReport();
    plr.setFactory(this.factory);
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream("Sfactura.pdf");
      plr.makePdf(pl, fos);
    } finally {
      if (fos != null) {
        fos.close();
      }
    }
  }
}

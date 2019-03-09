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

import org.beigesoft.log.LogSmp;
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
    LogSmp logger = new LogSmp();
    logger.setDbgSh(true);
    logger.setDbgFl(4000);
    logger.setDbgCl(4999);
    this.factory = new PdfFactory();
    this.factory.setLog(logger);
    this.factory.init();
  }
  
  @Test
  public void testToPdf() throws Exception {
    SfacturaModel pl = new SfacturaModel();
    pl.setItsNumber("1");
    pl.setDay("22");
    pl.setMonthYear("мая 2017");
    pl.setSeller("ООО Березка");
    pl.setSellerAddr("123122, г.Москва, ул. Ленина д.56");
    pl.setSellerInnKpp("154545/124484");
    pl.setGruzootpr("ООО Березка 123122, г.Москва, ул. Ленина д.56");
    pl.setGruzopol("ООО Тополь 1212121, г.Ковров, ул. Ленина д.44");
    pl.setPrdNum("12");
    pl.setPrdData("11.12.2017г.");
    pl.setBuyer("ООО Тополь");
    pl.setBuyerInnKpp("546464/464646");
    pl.setBuyerAddr("1212121, г.Ковров, ул. Ленина д.44");
    pl.setCurrency("российский рубль, 645");
    pl.setItems(new ArrayList<SfacturaLineModel>());
    pl.getItems().add(new SfacturaLineModel("Конфеты \"Мишка на севере\"", "кг", "166",
      new BigDecimal("270.00"), new BigDecimal("30"), new BigDecimal("18")));
    for (int i = 1; i < 20; i++) {
      pl.getItems().add(new SfacturaLineModel("Сахар #" + i, "кг", "166",
        new BigDecimal("50.00"), new BigDecimal("10"), new BigDecimal("18")));
    }
    for (SfacturaLineModel ln : pl.getItems()) {
      pl.setSubtotal(pl.getSubtotal().add(ln.getSubtotal()));
      pl.setTotalTaxes(pl.getTotalTaxes().add(ln.getTotalTaxes()));
      pl.setTotal(pl.getTotal().add(ln.getTotal()));
    }
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

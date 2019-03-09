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
 * <p>Write Platyojka tests.</p>
 *
 * @author Yury Demidenko
 */
public class WritePlatyojkaTest {

  private PdfFactory factory;
    
  public WritePlatyojkaTest() throws Exception {
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
    PlatyojkaModel pl = new PlatyojkaModel();
    pl.setItsNumber("1");
    pl.setItsDate("22.11.2017");
    pl.setTotal(new BigDecimal(1452.23));
    PlatyojkaReport plr = new PlatyojkaReport();
    plr.setFactory(this.factory);
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream("Platyojka.pdf");
      plr.makePdf(pl, fos);
    } finally {
      if (fos != null) {
        fos.close();
      }
    }
  }
}

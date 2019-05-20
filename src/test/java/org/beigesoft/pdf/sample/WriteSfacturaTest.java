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

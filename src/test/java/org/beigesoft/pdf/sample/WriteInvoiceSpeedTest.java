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

import java.io.File;
import java.util.Date;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.io.FileOutputStream;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.beigesoft.log.LogSmp;
import org.beigesoft.pdf.model.HasPdfContent;
import org.beigesoft.pdf.model.ERegisteredTtfFont;
import org.beigesoft.pdf.service.PdfFactory;
//import org.beigesoft.graphic.swing.service.SwingImageLoader;

/**
 * <p>Write Invoice speed tests. Every invoice takes own thread (taking client's request server side behavior).
 * Result for ordinal notebook IntelI3/4GB, 64bit desktop linux (non-server), JVM8 64Bit:
 * <b>Test speed invoices count/time in ms: 100/4316.</b>
 * Usage with Maven example:
 * <pre>
 * mvn exec:java -Dexec.mainClass="org.beigesoft.pdf.sample.WriteInvoiceSpeedTest" -Dexec.args="'/tmp/speed100' 100" -Dexec.classpathScope=test
 * </pre>
 * </p>
 *
 * @author Yury Demidenko
 */
public class WriteInvoiceSpeedTest {

  // server scoped services:
  private PdfFactory factory;
 
  private InvoiceReport invoiceReporter;
  
  // test vars:
  private String dirName = "speed";
  
  private int total = 100;

  // test results:
  private int countFinished = 0;

  private long lastFinishedTime;

  public WriteInvoiceSpeedTest() throws Exception {
    LogSmp logger = new LogSmp();
    logger.setDbgSh(true);
    logger.setDbgFl(4000);
    logger.setDbgCl(4999);
    this.factory = new PdfFactory();
    this.factory.setLog(logger);
    this.factory.init();
    this.factory.lazyGetTtfLoader().setIsCacheGlyf(true);
    //this.factory.lazyGetFctElement().setFctImageRgb(new SwingImageLoader());
    // load fonts - simulate state "server already full initialized before taking client's requests":
    this.factory.lazyGetPdfMaker().lazyGetTtfFont(ERegisteredTtfFont.DEJAVUSERIF.toString());
    this.factory.lazyGetPdfMaker().lazyGetTtfFont(ERegisteredTtfFont.DEJAVUSERIF_BOLD.toString());
    //this.factory.lazyGetPdfMaker().lazyGetTtfFontFromFile(ERegisteredTtfFont.DEJAVUSANS_BOLD.toString(),
      //"/archive/fonts/Dejavu/dejavu-fonts-ttf-2.37/ttf/DejaVuSans-Bold.ttf");
    this.invoiceReporter = new InvoiceReport();
    this.invoiceReporter.setFactory(this.factory);
  }
  
  public static void main(String[] args) throws Exception {
    if (args.length < 2) {
      System.err.println("usage: " + WriteInvoiceSpeedTest.class.getName() +
      "<directory_name> <total_invoices>");
      System.err.println("example: " + WriteInvoiceSpeedTest.class.getName() +
      "speed100 100");
      System.exit(1);
    }
    WriteInvoiceSpeedTest wist = new WriteInvoiceSpeedTest();
    wist.dirName = args[0];
    wist.total = Integer.parseInt(args[1]);
    wist.testToNPdf();
  }
  
  @Test
  public void testToNPdf() throws Exception {
    File dir = new File(this.dirName);
    if (!dir.exists()) {
      dir.mkdir();
    }
    long startTime = System.currentTimeMillis();
    for (int i = 0; i < this.total; i++) {
      WriterInvoiceSp wis = new WriterInvoiceSp();
      wis.setIdx(i);
      wis.start();
    }
    boolean isEnded = false;
    while (!isEnded) {
      Thread.sleep(200);
      synchronized (this) {
        isEnded = (this.countFinished >= this.total);
      }
    }
    assertTrue(this.countFinished == this.total);
    System.out.println("Test speed invoices count/time in ms: "
      + this.total + "/" + (this.lastFinishedTime - startTime));
  }
  
  public void makePdf(int pIdx) throws Exception {
    InvoiceModel im = new InvoiceModel();
    im.setItsNumber("A-68687687-" + pIdx);
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
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(this.dirName + File.separator + "invoice" + im.getItsNumber() + ".pdf");
      this.invoiceReporter.makePdf(im, fos, false);
    } finally {
      if (fos != null) {
        fos.close();
      }
    }
  }

  private class WriterInvoiceSp extends Thread {

    private int idx;

    @Override
    public final void run() {
      try {
        WriteInvoiceSpeedTest.this.makePdf(this.idx);
        synchronized (WriteInvoiceSpeedTest.this) {
          WriteInvoiceSpeedTest.this.countFinished++;
          WriteInvoiceSpeedTest.this.lastFinishedTime = System.currentTimeMillis();
        }
      } catch (Exception e) {
        synchronized (WriteInvoiceSpeedTest.this) {
          WriteInvoiceSpeedTest.this.countFinished = 9999999;
        }
        e.printStackTrace();
      }
    }

    public final void setIdx(final int pIdx) {
      this.idx = pIdx;
    }
  };
}

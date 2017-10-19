package org.beigesoft.pdf.service;

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

import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import org.beigesoft.log.LoggerSimple;
import org.beigesoft.zlib.ZLibStreamer;
import org.beigesoft.pdf.model.EFontS14;
import org.beigesoft.pdf.model.ERegisteredTtfFont;
import org.beigesoft.pdf.model.PdfDocument;
import org.beigesoft.pdf.model.HasPdfContent;
import org.beigesoft.doc.model.EUnitOfMeasure;
import org.beigesoft.doc.model.Document;
import org.beigesoft.doc.model.EPageSize;
import org.beigesoft.doc.model.EPageOrientation;
import org.beigesoft.ttf.model.TtfFont;
import org.beigesoft.ttf.service.TtfCompactFontMaker;
import org.beigesoft.ttf.service.TtfInputStream;
import org.beigesoft.doc.service.DocumentMaker;

/**
 * <p>Write PDF tests.</p>
 *
 * @author Yury Demidenko
 */
public class WriteLiberationPdfTest {

  LoggerSimple logger;

  PdfFactory factory;
    
  PdfMaker<HasPdfContent> pdfMaker;
  
  DocumentMaker<HasPdfContent> docMaker;

  public WriteLiberationPdfTest() throws Exception {
    this.logger = new LoggerSimple();
    this.logger.setIsShowDebugMessages(true);
    this.logger.setDetailLevel(1115);
    this.factory = new PdfFactory();
    this.factory.setLogger(this.logger);
    this.factory.init();
    this.factory.lazyGetTtfLoader().setLogGtiDelta(-5);
    this.factory.lazyGetTtfLoader().setLogGids(new LinkedHashSet<Integer>());
    this.factory.lazyGetTtfLoader().getLogGids().add(0);
    this.factory.lazyGetTtfLoader().getLogGids().add(1);
    this.factory.lazyGetTtfLoader().getLogGids().add(2);
    this.factory.lazyGetTtfLoader().getLogGids().add(3);
    this.factory.lazyGetTtfLoader().getLogGids().add(4);
    this.factory.lazyGetTtfLoader().getLogGids().add(5);
    this.factory.lazyGetTtfLoader().getLogGids().add(6);
    this.factory.lazyGetTtfLoader().getLogGids().add(7);
    this.factory.lazyGetTtfLoader().getLogGids().add(8);
    this.factory.lazyGetTtfLoader().getLogGids().add(946);
    //Привет G = 041F 0440 0438 0432 0435 0442 0020 0047
    this.factory.lazyGetTtfLoader().setLogUnicodes(new LinkedHashSet<Character>());
    this.factory.lazyGetTtfLoader().getLogUnicodes().add((char) 0x41F);
    this.factory.lazyGetTtfLoader().getLogUnicodes().add((char) 0x0440);
    this.factory.lazyGetTtfLoader().getLogUnicodes().add((char) 0x0438);
    this.factory.lazyGetTtfLoader().getLogUnicodes().add((char) 0x0432);
    this.factory.lazyGetTtfLoader().getLogUnicodes().add((char) 0x0435);
    this.factory.lazyGetTtfLoader().getLogUnicodes().add((char) 0x0442);
    this.pdfMaker = this.factory.lazyGetPdfMaker();
    this.docMaker = this.factory.lazyGetDocumentMaker();
  }
  
  //@Test add ttf to path
  public void testLiber() throws Exception {
    Document<HasPdfContent> doc = this.factory.lazyGetFctDocument().createDoc(EPageSize.A4, EPageOrientation.PORTRAIT);
    PdfDocument<HasPdfContent> docPdf = this.factory.createPdfDoc(doc);
    String lsr = "LiberationSans-Regular";
    this.pdfMaker.addFontTtf(docPdf, lsr);
    this.docMaker.setFontSize(doc, 6);
    this.docMaker.addString(doc, "Привет G", 100, 150);
    FileOutputStream fos = null;
    this.pdfMaker.prepareBeforeWrite(docPdf);
    this.pdfMaker.setIsCompressed(docPdf, false);
    try {
      fos = new FileOutputStream("test-libersans.pdf");
      this.factory.lazyGetPdfWriter().write(null, docPdf, fos);
      fos.flush();
      fos.close();
    } finally {
      if (fos != null) {
        fos.close();
      }
    }
    assertEquals(12, docPdf.getPdfToUnicodes().get(0).getUsedCids().size()); //with null and compound "p", "e" and "П"
    System.out.println("Used CIDS:");
    TtfFont ttfOri = this.pdfMaker.getTtfFonts().get(lsr);
    assertTrue("hmth != null", ttfOri.getHmtx() != null);
    for (Map.Entry<Character, Character> entry : docPdf.getPdfToUnicodes().get(0).getUsedCidToUni().entrySet()) {
      char cid = entry.getKey();
      char chr = entry.getValue();
      System.out.println("CID/uni/char/width: " + ((int) cid) + "/"
        + ((int) chr) + "/" + chr + "/" + ((int) ttfOri.getHmtx().getWidthForGid(cid)));
    }
    TtfCompactFontMaker compactFontMaker = this.factory.lazyGetCompactFontMaker();
    byte[] compDjv = compactFontMaker.make(null, lsr, docPdf.getPdfToUnicodes().get(0).getUsedCids());
    ByteArrayInputStream bais = new ByteArrayInputStream(compDjv);
    TtfInputStream is = new TtfInputStream(bais);
    TtfFont ttf = new TtfFont();
    ttf.setFileName(lsr);
    this.logger.info(null, WriteLiberationPdfTest.class, "Loading from byte array Liber:");
    this.factory.lazyGetTtfLoader().loadFontTtfFrom(ttf, is);
    this.factory.lazyGetTtfLoader().prepareAfterLoading(ttf);
    for (char cid : docPdf.getPdfToUnicodes().get(0).getUsedCids()) {
      char chr = 0;
      if (docPdf.getPdfToUnicodes().get(0).getUsedCidToUni().get(cid) != null) {
        chr = docPdf.getPdfToUnicodes().get(0).getUsedCidToUni().get(cid);
      }
      assertEquals("widths equals for CID " + ((int) cid),
        ttfOri.getHmtx().getWidths()[cid], ttf.getHmtx().getWidths()[cid]);
      int ofst;
      int len;
      if (ttf.getLoca().getOffsets16() != null) {
        ofst = ttf.getLoca().getOffsets16()[cid];
        len = ttf.getLoca().getOffsets16()[cid + 1] - ttf.getLoca().getOffsets16()[cid];
      } else {
        ofst = (int) ttf.getLoca().getOffsets32()[cid];
        len = (int) (ttf.getLoca().getOffsets32()[cid + 1] - ttf.getLoca().getOffsets32()[cid]);
      }
      System.out.println("CID/char/width/offset/length: " + ((int) cid) + "/"
        + chr + "/" + ((int) ttf.getHmtx().getWidths()[cid]) + "/" + ofst + "/" + len);
    }
  }
}

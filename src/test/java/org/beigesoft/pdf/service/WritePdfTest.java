package org.beigesoft.pdf.service;

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

import java.util.ArrayList;
import java.util.LinkedHashSet;
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
public class WritePdfTest {

  LoggerSimple logger;

  PdfFactory factory;

  PdfMaker<HasPdfContent> pdfMaker;
  
  DocumentMaker<HasPdfContent> docMaker;
    
  public WritePdfTest() throws Exception {
    this.logger = new LoggerSimple();
    this.logger.setIsShowDebugMessages(true);
    this.logger.setDetailLevel(115);
    this.factory = new PdfFactory();
    this.factory.setLogger(this.logger);
    this.factory.init();
    this.factory.lazyGetTtfLoader().setLogGtiDelta(5);
    this.factory.lazyGetTtfLoader().setLogGids(new LinkedHashSet<Integer>());
    this.factory.lazyGetTtfLoader().getLogGids().add(0);
    this.factory.lazyGetTtfLoader().getLogGids().add(3);
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

  @Test
  public void test1() throws Exception {
    Document<HasPdfContent> doc = this.factory.lazyGetFctDocument().createDoc(EPageSize.A4, EPageOrientation.PORTRAIT);
    PdfDocument<HasPdfContent> docPdf = this.factory.createPdfDoc(doc);
    boolean wasException = false;
    try {
      this.docMaker.addString(doc, "Hello!", 50, 150);
    } catch (Exception e) {
      wasException = true;
    }
    assertTrue("It must be exception on string with no fonts!", wasException);
    this.pdfMaker.addFontT1S14(docPdf, EFontS14.HELVETICA);
    this.pdfMaker.addFontT1S14(docPdf, EFontS14.COURIER_BOLDOBLIQUE);
    wasException = false;
    try {
      this.docMaker.addString(doc, "Привет!", 50, 150);
    } catch (Exception e) {
      wasException = true;
    }
    assertTrue("It must be exception on UTF-8 string!", wasException);
    this.docMaker.setFontSize(doc, 6);
    this.docMaker.addString(doc, "Hello world!", 50, 100);
    this.docMaker.setFont(doc, 2, 9);
    this.docMaker.addString(doc, "Hello world2!", 10, 150);
    this.docMaker.addPage(doc);
    this.docMaker.setFont(doc, 1, 11);
    this.docMaker.addString(doc, "Hello world3!", 10, 150);
    FileOutputStream fos = null;
    this.pdfMaker.prepareBeforeWrite(docPdf);
    this.pdfMaker.setIsCompressed(docPdf, false);
    try {
      fos = new FileOutputStream("test1.pdf");
      this.factory.lazyGetPdfWriter().write(null, docPdf, fos);
      fos.flush();
      fos.close();
    } finally {
      if (fos != null) {
        fos.close();
      }
    }
    this.pdfMaker.setIsCompressed(docPdf, true);
    fos = null;
    try {
      fos = new FileOutputStream("test1c.pdf");
      this.factory.lazyGetPdfWriter().write(null, docPdf, fos);
      fos.flush();
      fos.close();
    } finally {
      if (fos != null) {
        fos.close();
      }
    }
    //TODO read file and check
  }
  
  @Test
  public void testDejavu() throws Exception {
    Document<HasPdfContent> doc = this.factory.lazyGetFctDocument().createDoc(EPageSize.A4, EPageOrientation.PORTRAIT);
    PdfDocument<HasPdfContent> docPdf = this.factory.createPdfDoc(doc);
    this.pdfMaker.addFontTtf(docPdf, ERegisteredTtfFont.DEJAVUSANS.toString());
    this.docMaker.setFontSize(doc, 6);
    this.docMaker.addString(doc, "Привет G", 100, 150);
    FileOutputStream fos = null;
    this.pdfMaker.prepareBeforeWrite(docPdf);
    this.pdfMaker.setIsCompressed(docPdf, false);
    try {
      fos = new FileOutputStream("test-dejavu.pdf");
      this.factory.lazyGetPdfWriter().write(null, docPdf, fos);
      fos.flush();
      fos.close();
    } finally {
      if (fos != null) {
        fos.close();
      }
    }
    assertEquals(11, docPdf.getPdfToUnicodes().get(0).getUsedCids().size()); //with null and compound "p" and "e"
    System.out.println("Used CIDS:");
    TtfFont ttfOri = this.pdfMaker.getTtfFonts().get(ERegisteredTtfFont.DEJAVUSANS.toString());
    for (Map.Entry<Character, Character> entry : docPdf.getPdfToUnicodes().get(0).getUsedCidToUni().entrySet()) {
      char cid = entry.getKey();
      char chr = entry.getValue();
      System.out.println("CID/uni/char/width: " + ((int) cid) + "/"
        + ((int) chr) + "/" + chr + "/" + ((int) ttfOri.getHmtx().getWidthForGid(cid)));
    }
    TtfCompactFontMaker compactFontMaker = this.factory.lazyGetCompactFontMaker();
    //this.logger.setDetailLevel(1315);
    byte[] compDjv = compactFontMaker.make(null, ERegisteredTtfFont.DEJAVUSANS.toString(), docPdf.getPdfToUnicodes().get(0).getUsedCids());
    ByteArrayInputStream bais = new ByteArrayInputStream(compDjv);
    TtfInputStream is = new TtfInputStream(bais);
    TtfFont ttf = new TtfFont();
    ttf.setFileName(ERegisteredTtfFont.DEJAVUSANS.toString());
    this.logger.info(null, WritePdfTest.class, "Loading from byte array DejaVu:");
    this.factory.lazyGetTtfLoader().loadFontTtfFrom(ttf, is);
    this.factory.lazyGetTtfLoader().prepareAfterLoading(ttf);
    for (char cid : docPdf.getPdfToUnicodes().get(0).getUsedCids()) {
      char chr = 0;
      if (docPdf.getPdfToUnicodes().get(0).getUsedCidToUni().get(cid) != null) {
        chr = docPdf.getPdfToUnicodes().get(0).getUsedCidToUni().get(cid);
      }
      assertEquals("widths equals for CID " + ((int) cid),
        ttfOri.getHmtx().getWidthForGid(cid), ttf.getHmtx().getWidthForGid(cid));
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
        + chr + "/" + ((int) ttf.getHmtx().getWidthForGid(cid)) + "/" + ofst + "/" + len);
    }
  }
  
  //@Test
  public void testDejavuMono() throws Exception {
    Document<HasPdfContent> doc = this.factory.lazyGetFctDocument().createDoc(EPageSize.A4, EPageOrientation.PORTRAIT);
    PdfDocument<HasPdfContent> docPdf = this.factory.createPdfDoc(doc);
    this.pdfMaker.addFontTtf(docPdf, "DejaVuSansMono");
    this.docMaker.setFontSize(doc, 6);
    this.docMaker.addString(doc, "Привет G", 100, 150);
    FileOutputStream fos = null;
    this.pdfMaker.prepareBeforeWrite(docPdf);
    this.pdfMaker.setIsCompressed(docPdf, false);
    try {
      fos = new FileOutputStream("test-dejavu-mono.pdf");
      this.factory.lazyGetPdfWriter().write(null, docPdf, fos);
      fos.flush();
      fos.close();
    } finally {
      if (fos != null) {
        fos.close();
      }
    }
    assertEquals(11, docPdf.getPdfToUnicodes().get(0).getUsedCids().size()); //with null and compound "p" and "e"
    System.out.println("Used CIDS:");
    TtfFont ttfOri = this.pdfMaker.getTtfFonts().get("DejaVuSansMono");
    for (Map.Entry<Character, Character> entry : docPdf.getPdfToUnicodes().get(0).getUsedCidToUni().entrySet()) {
      char cid = entry.getKey();
      char chr = entry.getValue();
      System.out.println("CID/uni/char/width: " + ((int) cid) + "/"
        + ((int) chr) + "/" + chr + "/" + ((int) ttfOri.getHmtx().getWidthForGid(cid)));
    }
    TtfCompactFontMaker compactFontMaker = this.factory.lazyGetCompactFontMaker();
    //this.logger.setDetailLevel(1315);
    byte[] compDjv = compactFontMaker.make(null, "DejaVuSansMono", docPdf.getPdfToUnicodes().get(0).getUsedCids());
    ByteArrayInputStream bais = new ByteArrayInputStream(compDjv);
    TtfInputStream is = new TtfInputStream(bais);
    TtfFont ttf = new TtfFont();
    ttf.setFileName("DejaVuSansMono");
    this.logger.info(null, WritePdfTest.class, "Loading from byte array DejaVu mono:");
    this.factory.lazyGetTtfLoader().loadFontTtfFrom(ttf, is);
    this.factory.lazyGetTtfLoader().prepareAfterLoading(ttf);
    for (char cid : docPdf.getPdfToUnicodes().get(0).getUsedCids()) {
      char chr = 0;
      if (docPdf.getPdfToUnicodes().get(0).getUsedCidToUni().get(cid) != null) {
        chr = docPdf.getPdfToUnicodes().get(0).getUsedCidToUni().get(cid);
      }
      assertEquals("widths equals for CID " + ((int) cid),
        ttfOri.getHmtx().getWidthForGid(cid), ttf.getHmtx().getWidthForGid(cid));
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
        + chr + "/" + ((int) ttf.getHmtx().getWidthForGid(cid)) + "/" + ofst + "/" + len);
    }
  }
}

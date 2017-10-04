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
import org.beigesoft.doc.model.MetricsString;
import org.beigesoft.doc.model.EUnitOfMeasure;
import org.beigesoft.doc.model.Document;
import org.beigesoft.doc.model.DocTable;
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
public class WritePdfTableTest {

  LoggerSimple logger;

  PdfFactory factory;

  PdfMaker<HasPdfContent> pdfMaker;
  
  DocumentMaker<HasPdfContent> docMaker;
    
  public WritePdfTableTest() throws Exception {
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
    this.pdfMaker = this.factory.lazyGetPdfMaker();
    this.docMaker = this.factory.lazyGetDocumentMaker();
  }
  
  @Test
  public void testTbl() throws Exception {
    Document<HasPdfContent> doc = this.factory.lazyGetFctDocument().createDoc(EPageSize.A4, EPageOrientation.PORTRAIT);
    PdfDocument<HasPdfContent> docPdf = this.factory.createPdfDoc(doc);
    this.pdfMaker.addFontTtf(docPdf, ERegisteredTtfFont.DEJAVUSANS.toString());
    this.docMaker.setFontSize(doc, 3.6);
    double width1dot = this.factory.lazyGetUomHelper().fromPoints(1.0, doc.getResolutionDpi(), doc.getUnitOfMeasure());
    DocTable<HasPdfContent> dtbl = this.factory.lazyGetFctDocTable().createDocTable(doc, doc.getPages().get(0), 1, 2, width1dot, 1);
    dtbl.getItsCells().get(0).setItsContent("cell1 cell1 cell1 cell1 cell1 cell1 cell1 cell1 cell1 cell1 cell1");
    dtbl.getItsCells().get(1).setItsContent("cell2 cell2 cell2 cell2 cell2 cell2 cell2 cell2 cell2 cell2 cell2");
    String fntNm = dtbl.getDocument().getFonts().get(0).getItsName();
    assertEquals(1, dtbl.getItsCells().get(0).getFontNumber());
    assertEquals(fntNm, ERegisteredTtfFont.DEJAVUSANS.toString());
    //unis: c-46 e-48 l-4f gids: 63, 65, 6c
    MetricsString msTst = this.factory.lazyGetEvalMetricsString().eval("cell1 cell1 cell1 cell1 cell1 cell1",
      fntNm, dtbl.getItsCells().get(0).getFontSize(), 10000.0, 0.0);
    assertEquals(56, msTst.getWidth(), 1);
    this.docMaker.deriveElements(doc);
    FileOutputStream fos = null;
    this.pdfMaker.prepareBeforeWrite(docPdf);
    this.pdfMaker.setIsCompressed(docPdf, false);
    try {
      fos = new FileOutputStream("test-dejavu-table.pdf");
      this.factory.lazyGetPdfWriter().write(null, docPdf, fos);
      fos.flush();
      fos.close();
    } finally {
      if (fos != null) {
        fos.close();
      }
    }
  }
}

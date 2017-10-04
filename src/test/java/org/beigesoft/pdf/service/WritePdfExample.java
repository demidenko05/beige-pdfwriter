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
import org.beigesoft.doc.model.EUnitOfMeasure;
import org.beigesoft.doc.model.Document;
import org.beigesoft.doc.model.EPageSize;
import org.beigesoft.doc.model.EPageOrientation;
import org.beigesoft.ttf.model.TtfFont;
import org.beigesoft.ttf.service.TtfCompactFontMaker;
import org.beigesoft.ttf.service.TtfInputStream;
import org.beigesoft.doc.service.DocumentMaker;

/**
 * <p>Write PDF example.
 * Usage with Maven example:
 * <pre>
 * mvn exec:java -Dexec.mainClass="org.beigesoft.pdf.service.WritePdfExample" -Dexec.args="'hello world' DejaVuSans file1" -Dexec.classpathScope=test
 * </pre>
 * </p>
 *
 * @author Yury Demidenko
 */
public class WritePdfExample {
  
  //VL-Gothic-Regular  クルマ カーラインアップ
  //NanumGothic 본명조는 Adobe Type에서 최근 출시한 두번째
  //SourceHanSans-Regular 北京市景点玩乐

  public static void main(String[] args) throws Exception {
    if (args.length < 3) {
      System.err.println("usage: " + WritePdfExample.class.getName() +
      "<text> <font-without-ttf> <outfile-without-pdf>");
      System.err.println("example: " + WritePdfExample.class.getName() +
      "'hello world' DejaVuSans file1");
      System.exit(1);
    }
    String argTxt = args[0];
    String argFnt = args[1];
    String argFile = args[2];
    double fntSz = 3.6;
    if (args.length > 3) {
      fntSz = Float.valueOf(args[3]);
    }
    String argTxt2 = null;
    if (args.length > 4) {
      argTxt2 = args[4];
    }
    boolean isCompressed = args.length > 4;
    LoggerSimple logger = new LoggerSimple();
    logger.setIsShowDebugMessages(true);
    logger.setDetailLevel(1115);
    PdfFactory factory = new PdfFactory();
    factory.setLogger(logger);
    factory.init();
    char[] unis = new char[argTxt.length()];
    factory.lazyGetTtfLoader().setLogUnicodes(new LinkedHashSet<Character>());
    for (int i = 0; i < argTxt.length(); i++) {
      factory.lazyGetTtfLoader().getLogUnicodes().add(argTxt.charAt(i));
    }
    factory.lazyGetTtfLoader().setLogGtiDelta(3);
    factory.lazyGetTtfLoader().setLogGids(new LinkedHashSet<Integer>());
    factory.lazyGetTtfLoader().getLogGids().add(0);
    factory.lazyGetTtfLoader().getLogGids().add(1);
    factory.lazyGetTtfLoader().getLogGids().add(2);
    factory.lazyGetTtfLoader().getLogGids().add(3);
    factory.lazyGetTtfLoader().getLogGids().add(4);
    factory.lazyGetTtfLoader().getLogGids().add(5);
    factory.lazyGetTtfLoader().getLogGids().add(5);
    factory.lazyGetTtfLoader().getLogGids().add(7);
    factory.lazyGetTtfLoader().getLogGids().add(8);
    //TODO load from non-resource file
    Document<HasPdfContent> doc = factory.lazyGetFctDocument().createDoc(EPageSize.A4, EPageOrientation.PORTRAIT);
    PdfDocument<HasPdfContent> docPdf = factory.createPdfDoc(doc);
    PdfMaker<HasPdfContent> pdfMaker = factory.lazyGetPdfMaker();
    DocumentMaker<HasPdfContent> docMaker = factory.lazyGetDocumentMaker();
    pdfMaker.addFontTtf(docPdf, argFnt);
    docMaker.setFontSize(doc, fntSz);
    docMaker.addString(doc, argTxt, 30, 150);
    if (argTxt2 != null) {
      docMaker.addString(doc, argTxt2, 30, 150 + fntSz);
    }
    FileOutputStream fos = null;
    pdfMaker.prepareBeforeWrite(docPdf);
    pdfMaker.setIsCompressed(docPdf, false);
    try {
      fos = new FileOutputStream(argFile + ".pdf");
      factory.lazyGetPdfWriter().write(null, docPdf, fos);
      fos.flush();
      fos.close();
    } finally {
      if (fos != null) {
        fos.close();
      }
    }
    for (char cid : docPdf.getPdfToUnicodes().get(0).getUsedCids()) {
      char chr = 0;
      if (docPdf.getPdfToUnicodes().get(0).getUsedCidToUni().get(cid) != null) {
        chr = docPdf.getPdfToUnicodes().get(0).getUsedCidToUni().get(cid);
      }
      System.out.println("CID/char: " + ((int) cid) + "/" + chr);
    }
  }
}

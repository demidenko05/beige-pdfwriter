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

package org.beigesoft.pdf.service;

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

import org.beigesoft.log.LogSmp;
import org.beigesoft.zlib.ZLibStreamer;
import org.beigesoft.pdf.model.EFontS14;
import org.beigesoft.pdf.model.ERegisteredTtfFont;
import org.beigesoft.pdf.model.PdfDocument;
import org.beigesoft.pdf.model.HasPdfContent;
import org.beigesoft.doc.model.EUnitOfMeasure;
import org.beigesoft.doc.model.Document;
import org.beigesoft.doc.model.DocImage;
import org.beigesoft.doc.model.EPageSize;
import org.beigesoft.doc.model.EPageOrientation;
import org.beigesoft.ttf.model.TtfFont;
import org.beigesoft.ttf.service.TtfCompactFontMaker;
import org.beigesoft.ttf.service.TtfInputStream;
import org.beigesoft.doc.service.DocumentMaker;
import org.beigesoft.graphic.swing.service.SwingImageLoader;

/**
 * <p>Write image into PDF tests.</p>
 *
 * @author Yury Demidenko
 */
public class WritePdfImageTest {

  LogSmp logger;

  PdfFactory factory;

  PdfMaker<HasPdfContent> pdfMaker;
  
  DocumentMaker<HasPdfContent> docMaker;
    
  public WritePdfImageTest() throws Exception {
    this.logger = new LogSmp();
    logger.setDbgSh(true);
    logger.setDbgFl(4000);
    logger.setDbgCl(4999);
    this.factory = new PdfFactory();
    this.factory.setLog(this.logger);
    this.factory.init();
    this.factory.lazyGetFctElement().setFctImageRgb(new SwingImageLoader());
    this.pdfMaker = this.factory.lazyGetPdfMaker();
    this.docMaker = this.factory.lazyGetDocumentMaker();
  }

  @Test
  public void test1() throws Exception {
    Document<HasPdfContent> doc = this.factory.lazyGetFctDocument().createDoc(EPageSize.A4, EPageOrientation.PORTRAIT);
    PdfDocument<HasPdfContent> docPdf = this.factory.createPdfDoc(doc);
    //DocImage<HasPdfContent> dimg = this.docMaker.addImage(doc, "C:\\doc\\logo-web-store.png", 20, 150);
    DocImage<HasPdfContent> dimg = this.docMaker.addImage(doc, "/img/logo-web-store.png", 20, 50);
    dimg.setScale(0.5);
    this.pdfMaker.addImage(docPdf, dimg);
    DocImage<HasPdfContent> dimg2 = this.docMaker.addImage(doc, "/img/bob-signature.png", 30, 150);
    dimg2.setScale(0.25);
    dimg2.setRotateDegrees(10);
    this.pdfMaker.addImage(docPdf, dimg2);
    FileOutputStream fos = null;
    this.pdfMaker.prepareBeforeWrite(docPdf);
    this.pdfMaker.setIsCompressed(docPdf, false);
    try {
      fos = new FileOutputStream("test-img.pdf");
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

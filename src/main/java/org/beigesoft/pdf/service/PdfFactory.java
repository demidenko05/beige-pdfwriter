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

import java.util.Date;
import java.util.ArrayList;
import java.io.File;

import org.beigesoft.log.ILog;
import org.beigesoft.log.LogFile;
import org.beigesoft.zlib.ZLibStreamer;
import org.beigesoft.doc.model.Document;
import org.beigesoft.doc.model.DocRectangle;
import org.beigesoft.doc.model.CmpElementIdxGrp;
import org.beigesoft.doc.service.DocumentMaker;
import org.beigesoft.doc.service.FctDocument;
import org.beigesoft.doc.service.FctElement;
import org.beigesoft.doc.service.FctDocTable;
import org.beigesoft.doc.service.DeriverElPagination;
import org.beigesoft.doc.service.DeriverElTable;
import org.beigesoft.doc.service.EvalMetricsString;
import org.beigesoft.doc.service.IElementWriter;
import org.beigesoft.doc.service.UomHelper;
import org.beigesoft.doc.service.ToHexCoder;
import org.beigesoft.ttf.model.TtfConstants;
import org.beigesoft.ttf.service.TtfLoader;
import org.beigesoft.ttf.service.TtfResourceStreamer;
import org.beigesoft.ttf.service.TtfFileStreamer;
import org.beigesoft.ttf.service.TtfCompactFontMaker;
import org.beigesoft.ttf.service.TdeMaker;
import org.beigesoft.ttf.service.TableMakerFc;
import org.beigesoft.ttf.service.TableMakerHhea;
import org.beigesoft.ttf.service.TableMakerMaxp;
import org.beigesoft.ttf.service.TableMakerLoca;
import org.beigesoft.ttf.service.TableMakerHmtx;
import org.beigesoft.ttf.service.TableMakerHead;
import org.beigesoft.ttf.service.TableMakerGlyf;
import org.beigesoft.pdf.model.IPdfObject;
import org.beigesoft.pdf.model.PdfDocument;
import org.beigesoft.pdf.model.PdfTrailer;
import org.beigesoft.pdf.model.PdfInfo;
import org.beigesoft.pdf.model.PdfPage;
import org.beigesoft.pdf.model.PdfPages;
import org.beigesoft.pdf.model.PdfResources;
import org.beigesoft.pdf.model.PdfCatalog;
import org.beigesoft.pdf.model.HasPdfContent;
import org.beigesoft.pdf.model.PdfToUnicode;
import org.beigesoft.pdf.model.PdfCidFontType2;
import org.beigesoft.pdf.model.PdfFontDescriptor;
import org.beigesoft.pdf.model.PdfFontFile;
import org.beigesoft.pdf.model.PdfImage;
import org.beigesoft.pdf.model.PdfXref;
import org.beigesoft.pdf.exception.ExceptionPdfWr;

/**
 * <p>PDF objects simple factory without lazy initialization.</p>
 *
 * @author Yury Demidenko
 */
public class PdfFactory implements IPdfFactory<HasPdfContent> {

  /**
   * <p>Log.</p>
   **/
  private ILog log;

  /**
   * <p>App-scoped PDF writer.</p>
   **/
  private PdfWriter<HasPdfContent> pdfWriter;

  /**
   * <p>App-scoped PDF write helper.</p>
   **/
  private PdfWriteHelper writeHelper;

  /**
   * <p>App-scoped ZLIB streamer.</p>
   **/
  private ZLibStreamer zLibStreamer;

  /**
   * <p>App-scoped UOM helper.</p>
   **/
  private UomHelper uomHelper;

  /**
   * <p>App-scoped to Hex coder.</p>
   **/
  private ToHexCoder toHexCoder;

  /**
   * <p>App-scoped to TTF loader.</p>
   **/
  private TtfLoader ttfLoader;

  /**
   * <p>App-scoped to TTF constants.</p>
   **/
  private TtfConstants ttfConstants;

  /**
   * <p>It makes compact TTF font for embedding into PDF.</p>
   **/
  private TtfCompactFontMaker compactFontMaker;

  /**
   * <p>TDE standard maker.</p>
   **/
  private TdeMaker tdeMaker;

  /**
   * <p>Table full copy maker.</p>
   **/
  private TableMakerFc tableMakerFc;

  /**
   * <p>Hhea maker.</p>
   **/
  private TableMakerHhea tableMakerHhea;

  /**
   * <p>Loca maker.</p>
   **/
  private TableMakerLoca tableMakerLoca;

  /**
   * <p>Maxp maker.</p>
   **/
  private TableMakerMaxp tableMakerMaxp;

  /**
   * <p>Hmtx maker.</p>
   **/
  private TableMakerHmtx tableMakerHmtx;

  /**
   * <p>Head maker.</p>
   **/
  private TableMakerHead tableMakerHead;

  /**
   * <p>Glyf maker.</p>
   **/
  private TableMakerGlyf tableMakerGlyf;

  /**
   * <p>App-scoped PDF maker.</p>
   **/
  private PdfMaker<HasPdfContent> pdfMaker;

  /**
   * <p>App-scoped to comparator to order elements before write.</p>
   **/
  private CmpElementIdxGrp cmpElement;

  /**
   * <p>App-scoped document maker.</p>
   **/
  private DocumentMaker<HasPdfContent> documentMaker;

  /**
   * <p>App-scoped factory element.</p>
   **/
  private FctElement<HasPdfContent> fctElement;

  /**
   * <p>App-scoped factory document.</p>
   **/
  private FctDocument<HasPdfContent> fctDocument;

  /**
   * <p>IHasPdfContent factory.</p>
   **/
  private FctHasPdfContent fctHasPdfContent;

  /**
   * <p>String Writer.</p>
   **/
  private StringWriter stringWriter;

  /**
   * <p>Image Writer.</p>
   **/
  private ImageWriter imageWriter;

  /**
   * <p>Writer of PdfPages.</p>
   **/
  private WriterPdfPages writerPdfPages;

  /**
   * <p>Writer of PdfInfo.</p>
   **/
  private WriterPdfInfo writerPdfInfo;

  /**
   * <p>Writer of PdfCatalog.</p>
   **/
  private WriterPdfCatalog writerPdfCatalog;

  /**
   * <p>Writer of PdfResources.</p>
   **/
  private WriterPdfResources writerPdfResources;

  /**
   * <p>Writer of PdfContent.</p>
   **/
  private WriterPdfContent writerPdfContent;

  /**
   * <p>Writer of PdfPage.</p>
   **/
  private WriterPdfPage writerPdfPage;

  /**
   * <p>Writer of PdfToUnicode.</p>
   **/
  private WriterPdfToUnicode writerPdfToUnicode;

  /**
   * <p>Writer of PdfCidFontType2.</p>
   **/
  private WriterPdfCidFontType2 writerPdfCidFontType2;

  /**
   * <p>Writer of PdfFontDescriptor.</p>
   **/
  private WriterPdfFontDescriptor writerPdfFontDescriptor;

  /**
   * <p>Writer of PdfFontFile.</p>
   **/
  private WriterPdfFontFile writerPdfFontFile;

  /**
   * <p>Writer of PdfImage.</p>
   **/
  private WriterPdfImage writerPdfImage;

  /**
   * <p>Writer of PdfFontType0.</p>
   **/
  private WriterPdfFontType0 writerPdfFontType0;

  /**
   * <p>Writer of PdfFontType1S14.</p>
   **/
  private WriterPdfFontType1S14 writerPdfFontType1S14;

  /**
   * <p>App-scoped factory DocTable.</p>
   **/
  private FctDocTable<HasPdfContent> fctDocTable;

  /**
   * <p>App-scoped deriver table elements.</p>
   **/
  private DeriverElTable<HasPdfContent> deriverElTable;

  /**
   * <p>App-scoped deriver pagination elements.</p>
   **/
  private DeriverElPagination<HasPdfContent> deriverElPagination;

  /**
   * <p>App-scoped deriver metrics string evaluator.</p>
   **/
  private EvalMetricsString evalMetricsString;

  /**
   * <p>App-scoped deriver chars width evaluator.</p>
   **/
  private EvalCharWidth evalCharWidth;

  /**
   * <p>Font directory in resources.</p>
   **/
  private String fontDir = "/fonts/";

  /**
   * <p>Line Writer.</p>
   **/
  private LineWriter lineWriter;

  /**
   * <p>Resource streamer.</p>
   **/
  private TtfResourceStreamer ttfResourceStreamer;

  /**
   * <p>File streamer.</p>
   **/
  private TtfFileStreamer ttfFileStreamer;

  /**
   * <p>Simple non-leasy initialization.
   * Invoke it once after external initialization.</p>
   * @throws Exception an Exception
   **/
  public final synchronized void init() throws Exception {
    if (this.log == null) { //usually external
      LogFile lg = new LogFile();
      String currDir = System.getProperty("user.dir") + File.separator;
      String fileBaseName = "pdf-writer";
      lg.setPath(currDir + fileBaseName);
      this.log = lg;
      this.log.setDbgSh(false);
      this.log.setDbgFl(4000);
      this.log.setDbgFl(4999);
    }
    this.compactFontMaker = new TtfCompactFontMaker();
    this.compactFontMaker.setLog(this.log);
    this.tdeMaker = new TdeMaker();
    this.tableMakerFc = new TableMakerFc();
    this.tableMakerFc.setLog(this.log);
    this.tableMakerHmtx = new TableMakerHmtx();
    this.tableMakerHmtx.setLog(this.log);
    this.tableMakerHead = new TableMakerHead();
    this.tableMakerHead.setLog(this.log);
    this.tableMakerGlyf = new TableMakerGlyf();
    this.tableMakerGlyf.setLog(this.log);
    this.tableMakerLoca = new TableMakerLoca();
    this.tableMakerLoca.setLog(this.log);
    this.tableMakerMaxp = new TableMakerMaxp();
    this.tableMakerMaxp.setLog(this.log);
    this.tableMakerHhea = new TableMakerHhea();
    this.tableMakerHhea.setLog(this.log);
    this.ttfConstants = new TtfConstants();
    this.ttfLoader = new TtfLoader();
    this.ttfLoader.setTdeMaker(this.tdeMaker);
    this.ttfLoader.setTableMakerFc(this.tableMakerFc);
    this.ttfLoader.setTableMakerHmtx(this.tableMakerHmtx);
    this.ttfLoader.setTableMakerHead(this.tableMakerHead);
    this.ttfLoader.setTableMakerGlyf(this.tableMakerGlyf);
    this.ttfLoader.setTableMakerLoca(this.tableMakerLoca);
    this.ttfLoader.setTableMakerMaxp(this.tableMakerMaxp);
    this.ttfLoader.setTableMakerHhea(this.tableMakerHhea);
    this.ttfLoader.setLog(this.log);
    this.ttfLoader.setTtfConstants(this.ttfConstants);
    this.writeHelper = new PdfWriteHelper();
    PdfWriter<HasPdfContent> pdfWr = new PdfWriter<HasPdfContent>();
    pdfWr.setWriteHelper(this.writeHelper);
    this.pdfWriter = pdfWr;
    this.zLibStreamer = new ZLibStreamer();
    this.uomHelper = new UomHelper();
    this.toHexCoder = new ToHexCoder();
    this.cmpElement = new CmpElementIdxGrp();
    this.fctHasPdfContent = new FctHasPdfContent();
    this.writerPdfCatalog = new WriterPdfCatalog();
    this.writerPdfCatalog.setWriteHelper(this.writeHelper);
    this.writerPdfCidFontType2 = new WriterPdfCidFontType2();
    this.writerPdfCidFontType2.setWriteHelper(this.writeHelper);
    this.writerPdfFontDescriptor = new WriterPdfFontDescriptor();
    this.writerPdfFontDescriptor.setWriteHelper(this.writeHelper);
    this.writerPdfFontType0 = new WriterPdfFontType0();
    this.writerPdfFontType0.setWriteHelper(this.writeHelper);
    this.writerPdfFontType1S14 = new WriterPdfFontType1S14();
    this.writerPdfFontType1S14.setWriteHelper(this.writeHelper);
    this.writerPdfInfo = new WriterPdfInfo();
    this.writerPdfInfo.setWriteHelper(this.writeHelper);
    this.writerPdfPage = new WriterPdfPage();
    this.writerPdfPage.setWriteHelper(this.writeHelper);
    this.writerPdfPages = new WriterPdfPages();
    this.writerPdfPages.setWriteHelper(this.writeHelper);
    this.writerPdfResources = new WriterPdfResources();
    this.writerPdfResources.setWriteHelper(this.writeHelper);
    this.writerPdfToUnicode = new WriterPdfToUnicode();
    this.writerPdfToUnicode.setWriteHelper(this.writeHelper);
    this.writerPdfToUnicode.setZLibStreamer(this.zLibStreamer);
    this.writerPdfToUnicode.setToHexCoder(this.toHexCoder);
    this.writerPdfFontFile = new WriterPdfFontFile();
    this.writerPdfFontFile.setWriteHelper(this.writeHelper);
    this.writerPdfFontFile.setZLibStreamer(this.zLibStreamer);
    this.writerPdfFontFile.setCompactFontMaker(this.compactFontMaker);
    this.writerPdfImage = new WriterPdfImage();
    this.writerPdfImage.setWriteHelper(this.writeHelper);
    this.writerPdfImage.setZLibStreamer(this.zLibStreamer);
    this.writerPdfContent = new WriterPdfContent();
    this.writerPdfContent.setWriteHelper(this.writeHelper);
    this.writerPdfContent.setZLibStreamer(this.zLibStreamer);
    this.writerPdfContent.setCmpElement(this.cmpElement);
    this.writerPdfContent.setFctHasPdfContent(this.fctHasPdfContent);
    this.ttfResourceStreamer = new TtfResourceStreamer();
    this.ttfFileStreamer = new TtfFileStreamer();
    this.pdfMaker = new PdfMaker<HasPdfContent>();
    this.pdfMaker.setFontDir(this.fontDir);
    this.pdfMaker.setTtfResourceStreamer(this.ttfResourceStreamer);
    this.pdfMaker.setTtfFileStreamer(this.ttfFileStreamer);
    this.pdfMaker.setUomHelper(this.uomHelper);
    this.pdfMaker.setTtfLoader(this.ttfLoader);
    this.pdfMaker.setWriterPdfContent(this.writerPdfContent);
    this.pdfMaker.setWriterPdfFontFile(this.writerPdfFontFile);
    this.pdfMaker.setWriterPdfCidFontType2(this.writerPdfCidFontType2);
    this.pdfMaker.setWriterPdfFontDescriptor(this.writerPdfFontDescriptor);
    this.pdfMaker.setWriterPdfFontType0(this.writerPdfFontType0);
    this.pdfMaker.setWriterPdfFontType1S14(this.writerPdfFontType1S14);
    this.pdfMaker.setWriterPdfToUnicode(this.writerPdfToUnicode);
    this.pdfMaker.setWriterPdfPage(this.writerPdfPage);
    this.pdfMaker.setWriterPdfImage(this.writerPdfImage);
    this.compactFontMaker.setTtfFonts(this.pdfMaker.getTtfFonts());
    this.compactFontMaker
      .setTtfFontsStreamers(this.pdfMaker.getTtfFontsStreamers());
    this.compactFontMaker.setTtfFontsPaths(this.pdfMaker.getTtfFontsPaths());
    this.fctDocument = new FctDocument<HasPdfContent>();
    this.fctElement = new FctElement<HasPdfContent>();
    this.stringWriter = new StringWriter();
    this.stringWriter.setUomHelper(this.uomHelper);
    this.stringWriter.setToHexCoder(this.toHexCoder);
    this.stringWriter.setWriteHelper(this.writeHelper);
    this.fctElement.setWriterString(stringWriter);
    this.imageWriter = new ImageWriter();
    this.imageWriter.setUomHelper(this.uomHelper);
    this.imageWriter.setWriteHelper(this.writeHelper);
    this.fctElement.setWriterImage(imageWriter);
    this.lineWriter = new LineWriter();
    this.lineWriter.setUomHelper(this.uomHelper);
    this.lineWriter.setWriteHelper(this.writeHelper);
    this.fctElement.setWriterLine(lineWriter);
    this.documentMaker = new DocumentMaker<HasPdfContent>();
    this.documentMaker.setElementFactory(this.fctElement);
    this.evalCharWidth = new EvalCharWidth();
    this.evalCharWidth.setTtfFonts(this.pdfMaker.getTtfFonts());
    this.evalMetricsString = new EvalMetricsString();
    this.evalMetricsString.setEvalCharWidth(this.evalCharWidth);
    this.deriverElTable = new DeriverElTable<HasPdfContent>();
    this.deriverElTable.setElementFactory(this.fctElement);
    this.deriverElTable.setEvalMetricsString(this.evalMetricsString);
    this.deriverElTable.setDocumentMaker(this.documentMaker);
    this.fctDocTable = new FctDocTable<HasPdfContent>();
    this.fctDocTable.setDeriverElements(this.deriverElTable);
    this.documentMaker.setDocTableFactory(this.fctDocTable);
    this.deriverElPagination = new DeriverElPagination<HasPdfContent>();
    this.deriverElPagination.setElementFactory(this.fctElement);
    this.deriverElPagination.setEvalMetricsString(this.evalMetricsString);
    this.deriverElPagination.setDocumentMaker(this.documentMaker);
    this.documentMaker.setDeriverElPagination(this.deriverElPagination);
  }

  /**
   * <p>PDF doc creator.</p>
   * @param pBaseDoc Base Document
   * @return PDF document
   * @throws Exception an Exception
   **/
  @Override
  public final PdfDocument<HasPdfContent> createPdfDoc(
    final Document<HasPdfContent> pBaseDoc) throws Exception {
    PdfDocument<HasPdfContent> doc = new PdfDocument<HasPdfContent>();
    doc.setMainDoc(pBaseDoc);
    doc.setPdfXref(new PdfXref());
    doc.setPdfTrailer(new PdfTrailer());
    doc.setPdfObjects(new ArrayList<IPdfObject>());
    doc.setPdfToUnicodes(new ArrayList<PdfToUnicode>());
    doc.setCidType2Fonts(new ArrayList<PdfCidFontType2>());
    doc.setFontDescriptors(new ArrayList<PdfFontDescriptor>());
    doc.setFontFiles(new ArrayList<PdfFontFile>());
    doc.setImages(new ArrayList<PdfImage>());
    PdfInfo pdfInfo = new PdfInfo();
    pdfInfo.setCreationDate(new Date());
    pdfInfo.setWriter(this.writerPdfInfo);
    doc.setPdfInfo(pdfInfo);
    doc.getPdfObjects().add(pdfInfo);
    doc.getPdfTrailer().setInfo(pdfInfo);
    PdfCatalog pdfCatalog = new PdfCatalog();
    pdfCatalog.setWriter(this.writerPdfCatalog);
    doc.getPdfObjects().add(pdfCatalog);
    doc.getPdfTrailer().setRoot(pdfCatalog);
    PdfPages pdfPages = new PdfPages();
    pdfPages.setWriter(this.writerPdfPages);
    pdfPages.setPages(new ArrayList<PdfPage>());
    pdfCatalog.setPages(pdfPages);
    doc.getPdfObjects().add(pdfPages);
    PdfResources pdfRes = new PdfResources();
    pdfRes.setWriter(this.writerPdfResources);
    pdfRes.setFonts(new ArrayList<IPdfObject>());
    pdfRes.setImages(new ArrayList<IPdfObject>());
    doc.setResources(pdfRes);
    doc.getPdfObjects().add(pdfRes);
    return doc;
  }

  /**
   * <p>Getter for String writer.</p>
   * @return StringWriter
   * @throws Exception an Exception
   **/
  @Override
  public final StringWriter lazyGetStringWriter() throws Exception {
    return this.stringWriter;
  }

  /**
   * <p>Getter for Line writer.</p>
   * @return LineWriter
   * @throws Exception an Exception
   **/
  @Override
  public final LineWriter lazyGetLineWriter() throws Exception {
    return this.lineWriter;
  }

  /**
   * <p>Getter for Rectangle writer.</p>
   * @return IElementWriter<DocRectangle<WI>, WI>
   * @throws Exception an Exception
   **/
  @Override
  public final IElementWriter<DocRectangle<HasPdfContent>, HasPdfContent>
    lazyGetRectangleWriter() throws Exception {
    throw new ExceptionPdfWr("Not yet implemented!");
  }

  /**
   * <p>Getter for log.</p>
   * @return ILog
   * @throws Exception an Exception
   **/
  @Override
  public final ILog lazyGetLog() throws Exception {
    return this.log;
  }

  /**
   * <p>Getter for documentMaker.</p>
   * @return DocumentMaker<HasPdfContent>
   * @throws Exception an Exception
   **/
  @Override
  public final DocumentMaker<HasPdfContent>
    lazyGetDocumentMaker() throws Exception {
    return this.documentMaker;
  }

  /**
   * <p>Getter for fctElement.</p>
   * @return FctElement<HasPdfContent>
   * @throws Exception an Exception
   **/
  @Override
  public final FctElement<HasPdfContent> lazyGetFctElement() throws Exception {
    return this.fctElement;
  }

  /**
   * <p>Getter for fctDocument.</p>
   * @return FctDocument<HasPdfContent>
   * @throws Exception an Exception
   **/
  @Override
  public final FctDocument<HasPdfContent>
    lazyGetFctDocument() throws Exception {
    return this.fctDocument;
  }

  /**
   * <p>Getter for writeHelper.</p>
   * @return PdfWriteHelper
   * @throws Exception an Exception
   **/
  @Override
  public final PdfWriteHelper lazyGetWriteHelper() throws Exception {
    return this.writeHelper;
  }

  /**
   * <p>Getter for pdfWriter.</p>
   * @return IPdfWriter
   * @throws Exception an Exception
   **/
  @Override
  public final PdfWriter<HasPdfContent> lazyGetPdfWriter() throws Exception {
    return this.pdfWriter;
  }

  /**
   * <p>Getter for compactFontMaker.</p>
   * @return TtfCompactFontMaker
   * @throws Exception an Exception
   **/
  @Override
  public final TtfCompactFontMaker lazyGetCompactFontMaker() throws Exception {
    return this.compactFontMaker;
  }

  /**
   * <p>Getter for pdfMaker.</p>
   * @return PdfMaker
   * @throws Exception an Exception
   **/
  @Override
  public final PdfMaker<HasPdfContent> lazyGetPdfMaker() throws Exception {
    return this.pdfMaker;
  }

  /**
   * <p>Getter for ttfLoader.</p>
   * @return TtfLoader
   * @throws Exception an Exception
   **/
  @Override
  public final TtfLoader lazyGetTtfLoader() throws Exception {
    return this.ttfLoader;
  }

  /**
   * <p>Getter for zLibStreamer.</p>
   * @return ZLibStreamer
   * @throws Exception an Exception
   **/
  @Override
  public final ZLibStreamer lazyGetZLibStreamer() throws Exception {
    return this.zLibStreamer;
  }

  /**
   * <p>Getter for uomHelper.</p>
   * @return UomHelper
   * @throws Exception an Exception
   **/
  @Override
  public final UomHelper lazyGetUomHelper() throws Exception {
    return this.uomHelper;
  }

  /**
   * <p>Getter for toHexCoder.</p>
   * @return ToHexCoder
   * @throws Exception an Exception
   **/
  @Override
  public final ToHexCoder lazyGetToHexCoder() throws Exception {
    return this.toHexCoder;
  }

  /**
   * <p>Getter for tdeMaker.</p>
   * @return TdeMaker
   * @throws Exception an Exception
   **/
  @Override
  public final TdeMaker lazyGetTdeMaker() throws Exception {
    return this.tdeMaker;
  }

  /**
   * <p>Getter for tableMakerFc.</p>
   * @return TableMakerFc
   * @throws Exception an Exception
   **/
  @Override
  public final TableMakerFc lazyGetTableMakerFc() throws Exception {
    return this.tableMakerFc;
  }

  /**
   * <p>Getter for tableMakerHmtx.</p>
   * @return TableMakerHmtx
   * @throws Exception an Exception
   **/
  @Override
  public final TableMakerHmtx lazyGetTableMakerHmtx() throws Exception {
    return this.tableMakerHmtx;
  }

  /**
   * <p>Getter for tableMakerHead.</p>
   * @return TableMakerHead
   * @throws Exception an Exception
   **/
  @Override
  public final TableMakerHead lazyGetTableMakerHead() throws Exception {
    return this.tableMakerHead;
  }

  /**
   * <p>Getter for tableMakerGlyf.</p>
   * @return TableMakerGlyf
   * @throws Exception an Exception
   **/
  @Override
  public final TableMakerGlyf lazyGetTableMakerGlyf() throws Exception {
    return this.tableMakerGlyf;
  }

  /**
   * <p>Getter for tableMakerLoca.</p>
   * @return TableMakerLoca
   * @throws Exception an Exception
   **/
  @Override
  public final TableMakerLoca lazyGetTableMakerLoca() throws Exception {
    return this.tableMakerLoca;
  }

  /**
   * <p>Getter for tableMakerMaxp.</p>
   * @return TableMakerMaxp
   * @throws Exception an Exception
   **/
  @Override
  public final TableMakerMaxp lazyGetTableMakerMaxp() throws Exception {
    return this.tableMakerMaxp;
  }

  /**
   * <p>Getter for tableMakerHhea.</p>
   * @return TableMakerHhea
   * @throws Exception an Exception
   **/
  @Override
  public final TableMakerHhea lazyGetTableMakerHhea() throws Exception {
    return this.tableMakerHhea;
  }

  /**
   * <p>Getter for cmpElement.</p>
   * @return CmpElementIdxGrp
   * @throws Exception an Exception
   **/
  @Override
  public final CmpElementIdxGrp lazyGetCmpElement() throws Exception {
    return this.cmpElement;
  }

  /**
   * <p>Getter for fctHasPdfContent.</p>
   * @return FctHasPdfContent
   * @throws Exception an Exception
   **/
  @Override
  public final FctHasPdfContent lazyGetFctHasPdfContent() throws Exception {
    return this.fctHasPdfContent;
  }

  /**
   * <p>Getter for writerPdfPages.</p>
   * @return WriterPdfPages
   * @throws Exception an Exception
   **/
  @Override
  public final WriterPdfPages lazyGetWriterPdfPages() throws Exception {
    return this.writerPdfPages;
  }

  /**
   * <p>Getter for writerPdfInfo.</p>
   * @return WriterPdfInfo
   * @throws Exception an Exception
   **/
  @Override
  public final WriterPdfInfo lazyGetWriterPdfInfo() throws Exception {
    return this.writerPdfInfo;
  }

  /**
   * <p>Getter for writerPdfCatalog.</p>
   * @return WriterPdfCatalog
   * @throws Exception an Exception
   **/
  @Override
  public final WriterPdfCatalog lazyGetWriterPdfCatalog() throws Exception {
    return this.writerPdfCatalog;
  }

  /**
   * <p>Getter for writerPdfResources.</p>
   * @return WriterPdfResources
   * @throws Exception an Exception
   **/
  @Override
  public final WriterPdfResources lazyGetWriterPdfResources() throws Exception {
    return this.writerPdfResources;
  }

  /**
   * <p>Getter for writerPdfContent.</p>
   * @return WriterPdfContent
   * @throws Exception an Exception
   **/
  @Override
  public final WriterPdfContent lazyGetWriterPdfContent() throws Exception {
    return this.writerPdfContent;
  }

  /**
   * <p>Getter for writerPdfPage.</p>
   * @return WriterPdfPage
   * @throws Exception an Exception
   **/
  @Override
  public final WriterPdfPage lazyGetWriterPdfPage() throws Exception {
    return this.writerPdfPage;
  }

  /**
   * <p>Getter for writerPdfToUnicode.</p>
   * @return WriterPdfToUnicode
   * @throws Exception an Exception
   **/
  @Override
  public final WriterPdfToUnicode lazyGetWriterPdfToUnicode() throws Exception {
    return this.writerPdfToUnicode;
  }

  /**
   * <p>Getter for writerPdfCidFontType2.</p>
   * @return WriterPdfCidFontType2
   * @throws Exception an Exception
   **/
  @Override
  public final WriterPdfCidFontType2
    lazyGetWriterPdfCidFontType2() throws Exception {
    return this.writerPdfCidFontType2;
  }

  /**
   * <p>Getter for writerPdfFontDescriptor.</p>
   * @return WriterPdfFontDescriptor
   * @throws Exception an Exception
   **/
  @Override
  public final WriterPdfFontDescriptor
    lazyGetWriterPdfFontDescriptor() throws Exception {
    return this.writerPdfFontDescriptor;
  }

  /**
   * <p>Getter for writerPdfFontFile.</p>
   * @return WriterPdfFontFile
   * @throws Exception an Exception
   **/
  @Override
  public final WriterPdfFontFile lazyGetWriterPdfFontFile() throws Exception {
    return this.writerPdfFontFile;
  }

  /**
   * <p>Getter for writerPdfImage.</p>
   * @return WriterPdfImage
   * @throws Exception an Exception
   **/
  @Override
  public final WriterPdfImage lazyGetWriterPdfImage() throws Exception {
    return this.writerPdfImage;
  }

  /**
   * <p>Getter for writerPdfFontType0.</p>
   * @return WriterPdfFontType0
   * @throws Exception an Exception
   **/
  @Override
  public final WriterPdfFontType0 lazyGetWriterPdfFontType0() throws Exception {
    return this.writerPdfFontType0;
  }

  /**
   * <p>Getter for writerPdfFontType1S14.</p>
   * @return WriterPdfFontType1S14
   * @throws Exception an Exception
   **/
  @Override
  public final WriterPdfFontType1S14
    lazyGetWriterPdfFontType1S14() throws Exception {
    return this.writerPdfFontType1S14;
  }

  /**
   * <p>Getter for fctDocTable.</p>
   * @return FctDocTable<HasPdfContent>
   * @throws Exception an Exception
   **/
  @Override
  public final FctDocTable<HasPdfContent>
    lazyGetFctDocTable() throws Exception {
    return this.fctDocTable;
  }

  /**
   * <p>Getter for deriverElTable.</p>
   * @return DeriverElTable<HasPdfContent>
   * @throws Exception an Exception
   **/
  @Override
  public final DeriverElTable<HasPdfContent>
    lazyGetDeriverElTable() throws Exception {
    return this.deriverElTable;
  }

  /**
   * <p>Getter for deriverElPagination.</p>
   * @return DeriverElPagination<HasPdfContent>
   * @throws Exception an Exception
   **/
  @Override
  public final DeriverElPagination<HasPdfContent>
    lazyGetDeriverElPagination() throws Exception {
    return this.deriverElPagination;
  }

  /**
   * <p>Getter for evalMetricsString.</p>
   * @return EvalMetricsString
   * @throws Exception an Exception
   **/
  @Override
  public final EvalMetricsString lazyGetEvalMetricsString() throws Exception {
    return this.evalMetricsString;
  }

  /**
   * <p>Getter for evalCharWidth.</p>
   * @return EvalCharWidth
   * @throws Exception an Exception
   **/
  @Override
  public final EvalCharWidth lazyGetEvalCharWidth() throws Exception {
    return this.evalCharWidth;
  }

  /**
   * <p>Getter for TtfResourceStreamer.</p>
   * @return TtfResourceStreamer
   * @throws Exception an Exception
   **/
  @Override
  public final TtfResourceStreamer
    lazyGetTtfResourceStreamer() throws Exception {
    return this.ttfResourceStreamer;
  }

  /**
   * <p>Getter for TtfFileStreamer.</p>
   * @return TtfFileStreamer
   * @throws Exception an Exception
   **/
  @Override
  public final TtfFileStreamer lazyGetTtfFileStreamer() throws Exception {
    return this.ttfFileStreamer;
  }

  //SGS:
  /**
   * <p>Setter for log.</p>
   * @param pLog reference
   **/
  public final void setLog(final ILog pLog) {
    this.log = pLog;
  }

  /**
   * <p>Getter for fontDir.</p>
   * @return String
   **/
  public final String getFontDir() {
    return this.fontDir;
  }

  /**
   * <p>Setter for fontDir.</p>
   * @param pFontDir reference
   **/
  public final void setFontDir(final String pFontDir) {
    this.fontDir = pFontDir;
  }
}

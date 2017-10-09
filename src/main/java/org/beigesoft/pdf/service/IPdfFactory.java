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

import java.util.Comparator;

import org.beigesoft.log.ILogger;
import org.beigesoft.zlib.IZLibStreamer;
import org.beigesoft.doc.model.Document;
import org.beigesoft.doc.model.DocLine;
import org.beigesoft.doc.model.DocString;
import org.beigesoft.doc.model.DocTable;
import org.beigesoft.doc.model.DocRectangle;
import org.beigesoft.doc.model.Pagination;
import org.beigesoft.doc.model.IElement;
import org.beigesoft.doc.service.IFctDocument;
import org.beigesoft.doc.service.IFctElement;
import org.beigesoft.doc.service.IDocumentMaker;
import org.beigesoft.doc.service.IElementWriter;
import org.beigesoft.doc.service.UomHelper;
import org.beigesoft.doc.service.ToHexCoder;
import org.beigesoft.doc.service.IEvalCharWidth;
import org.beigesoft.doc.service.IEvalMetricsString;
import org.beigesoft.doc.service.IDeriverElements;
import org.beigesoft.doc.service.IFctDocTable;
import org.beigesoft.ttf.service.ITtfLoader;
import org.beigesoft.ttf.service.ITtfSourceStreamer;
import org.beigesoft.ttf.service.ITtfCompactFontMaker;
import org.beigesoft.ttf.service.TdeMaker;
import org.beigesoft.ttf.service.TableMakerFc;
import org.beigesoft.ttf.service.TableMakerHhea;
import org.beigesoft.ttf.service.TableMakerMaxp;
import org.beigesoft.ttf.service.TableMakerLoca;
import org.beigesoft.ttf.service.TableMakerHmtx;
import org.beigesoft.ttf.service.TableMakerHead;
import org.beigesoft.ttf.service.TableMakerGlyf;
import org.beigesoft.pdf.model.PdfDocument;
import org.beigesoft.pdf.model.PdfCatalog;
import org.beigesoft.pdf.model.PdfCidFontType2;
import org.beigesoft.pdf.model.PdfContent;
import org.beigesoft.pdf.model.PdfFontDescriptor;
import org.beigesoft.pdf.model.PdfFontFile;
import org.beigesoft.pdf.model.PdfFontType0;
import org.beigesoft.pdf.model.PdfFontType1S14;
import org.beigesoft.pdf.model.PdfInfo;
import org.beigesoft.pdf.model.PdfPage;
import org.beigesoft.pdf.model.PdfPages;
import org.beigesoft.pdf.model.PdfResources;
import org.beigesoft.pdf.model.PdfToUnicode;

/**
 * <p>Abstraction of app, document, PDF objects factory.</p>
 *
 * @param <WI> writing instrument type
 * @author Yury Demidenko
 */
public interface IPdfFactory<WI> {
  /**
   * <p>PDF doc creator.</p>
   * @param pBaseDoc Base Document
   * @return PDF document
   * @throws Exception an Exception
   **/
  PdfDocument<WI> createPdfDoc(
    Document<WI> pBaseDoc) throws Exception;

  /**
   * <p>Getter for logger.</p>
   * @return ILogger
   * @throws Exception an Exception
   **/
  ILogger lazyGetLogger() throws Exception;

  /**
   * <p>Getter for documentMaker.</p>
   * @return IDocumentMaker<WI>
   * @throws Exception an Exception
   **/
  IDocumentMaker<WI> lazyGetDocumentMaker() throws Exception;

  /**
   * <p>Getter for String writer.</p>
   * @return IElementWriter<DocString<WI>, WI>
   * @throws Exception an Exception
   **/
  IElementWriter<DocString<WI>, WI> lazyGetStringWriter() throws Exception;

  /**
   * <p>Getter for Line writer.</p>
   * @return IElementWriter<DocLine<WI>, WI>
   * @throws Exception an Exception
   **/
  IElementWriter<DocLine<WI>, WI> lazyGetLineWriter() throws Exception;

  /**
   * <p>Getter for Rectangle writer.</p>
   * @return IElementWriter<DocRectangle<WI>, WI>
   * @throws Exception an Exception
   **/
  IElementWriter<DocRectangle<WI>, WI>
    lazyGetRectangleWriter() throws Exception;

  /**
   * <p>Getter for fctElement.</p>
   * @return IFctElement<WI>
   * @throws Exception an Exception
   **/
  IFctElement<WI> lazyGetFctElement() throws Exception;

  /**
   * <p>Getter for fctDocument.</p>
   * @return IFctDocument<WI>
   * @throws Exception an Exception
   **/
  IFctDocument<WI> lazyGetFctDocument() throws Exception;

  /**
   * <p>Getter for writeHelper.</p>
   * @return PdfWriteHelper
   * @throws Exception an Exception
   **/
  PdfWriteHelper lazyGetWriteHelper() throws Exception;
  /**
   * <p>Getter for pdfWriter.</p>
   * @return IPdfWriter
   * @throws Exception an Exception
   **/
  IPdfWriter<WI> lazyGetPdfWriter() throws Exception;

  /**
   * <p>Getter for compactFontMaker.</p>
   * @return ITtfCompactFontMaker
   * @throws Exception an Exception
   **/
  ITtfCompactFontMaker lazyGetCompactFontMaker() throws Exception;

  /**
   * <p>Getter for pdfMaker.</p>
   * @return IPdfMaker<WI>
   * @throws Exception an Exception
   **/
  IPdfMaker<WI> lazyGetPdfMaker() throws Exception;

  /**
   * <p>Getter for ttfLoader.</p>
   * @return ITtfLoader
   * @throws Exception an Exception
   **/
  ITtfLoader lazyGetTtfLoader() throws Exception;

  /**
   * <p>Getter for zLibStreamer.</p>
   * @return IZLibStreamer
   * @throws Exception an Exception
   **/
  IZLibStreamer lazyGetZLibStreamer() throws Exception;

  /**
   * <p>Getter for uomHelper.</p>
   * @return UomHelper
   * @throws Exception an Exception
   **/
  UomHelper lazyGetUomHelper() throws Exception;

  /**
   * <p>Getter for toHexCoder.</p>
   * @return ToHexCoder
   * @throws Exception an Exception
   **/
  ToHexCoder lazyGetToHexCoder() throws Exception;

  /**
   * <p>Getter for tdeMaker.</p>
   * @return TdeMaker
   * @throws Exception an Exception
   **/
  TdeMaker lazyGetTdeMaker() throws Exception;

  /**
   * <p>Getter for tableMakerFc.</p>
   * @return TableMakerFc
   * @throws Exception an Exception
   **/
  TableMakerFc lazyGetTableMakerFc() throws Exception;

  /**
   * <p>Getter for tableMakerHmtx.</p>
   * @return TableMakerHmtx
   * @throws Exception an Exception
   **/
  TableMakerHmtx lazyGetTableMakerHmtx() throws Exception;

  /**
   * <p>Getter for tableMakerHead.</p>
   * @return TableMakerHead
   * @throws Exception an Exception
   **/
  TableMakerHead lazyGetTableMakerHead() throws Exception;

  /**
   * <p>Getter for tableMakerGlyf.</p>
   * @return TableMakerGlyf
   * @throws Exception an Exception
   **/
  TableMakerGlyf lazyGetTableMakerGlyf() throws Exception;

  /**
   * <p>Getter for tableMakerLoca.</p>
   * @return TableMakerLoca
   * @throws Exception an Exception
   **/
  TableMakerLoca lazyGetTableMakerLoca() throws Exception;

  /**
   * <p>Getter for tableMakerMaxp.</p>
   * @return TableMakerMaxp
   * @throws Exception an Exception
   **/
  TableMakerMaxp lazyGetTableMakerMaxp() throws Exception;

  /**
   * <p>Getter for tableMakerHhea.</p>
   * @return TableMakerHhea
   * @throws Exception an Exception
   **/
  TableMakerHhea lazyGetTableMakerHhea() throws Exception;

  /**
   * <p>Getter for cmpElement.</p>
   * @return Comparator<IElement<?>>
   * @throws Exception an Exception
   **/
  Comparator<IElement<?>> lazyGetCmpElement() throws Exception;

  /**
   * <p>Getter for fctHasPdfContent.</p>
   * @return IFctHasPdfContent
   * @throws Exception an Exception
   **/
  IFctHasPdfContent lazyGetFctHasPdfContent() throws Exception;

  /**
   * <p>Getter for writerPdfPages.</p>
   * @return WriterPdfPages
   * @throws Exception an Exception
   **/
  IWriterPdfObject<PdfPages> lazyGetWriterPdfPages() throws Exception;

  /**
   * <p>Getter for writerPdfInfo.</p>
   * @return WriterPdfInfo
   * @throws Exception an Exception
   **/
  IWriterPdfObject<PdfInfo> lazyGetWriterPdfInfo() throws Exception;

  /**
   * <p>Getter for writerPdfCatalog.</p>
   * @return WriterPdfCatalog
   * @throws Exception an Exception
   **/
  IWriterPdfObject<PdfCatalog> lazyGetWriterPdfCatalog() throws Exception;

  /**
   * <p>Getter for writerPdfResources.</p>
   * @return WriterPdfResources
   * @throws Exception an Exception
   **/
  IWriterPdfObject<PdfResources> lazyGetWriterPdfResources() throws Exception;

  /**
   * <p>Getter for writerPdfContent.</p>
   * @return WriterPdfContent
   * @throws Exception an Exception
   **/
  IWriterPdfObject<PdfContent> lazyGetWriterPdfContent() throws Exception;

  /**
   * <p>Getter for writerPdfPage.</p>
   * @return WriterPdfPage
   * @throws Exception an Exception
   **/
  IWriterPdfObject<PdfPage> lazyGetWriterPdfPage() throws Exception;

  /**
   * <p>Getter for writerPdfToUnicode.</p>
   * @return WriterPdfToUnicode
   * @throws Exception an Exception
   **/
  IWriterPdfObject<PdfToUnicode>
    lazyGetWriterPdfToUnicode() throws Exception;

  /**
   * <p>Getter for writerPdfCidFontType2.</p>
   * @return WriterPdfCidFontType2
   * @throws Exception an Exception
   **/
  IWriterPdfObject<PdfCidFontType2>
    lazyGetWriterPdfCidFontType2() throws Exception;

  /**
   * <p>Getter for writerPdfFontDescriptor.</p>
   * @return WriterPdfFontDescriptor
   * @throws Exception an Exception
   **/
  IWriterPdfObject<PdfFontDescriptor>
    lazyGetWriterPdfFontDescriptor() throws Exception;

  /**
   * <p>Getter for writerPdfFontFile.</p>
   * @return WriterPdfFontFile
   * @throws Exception an Exception
   **/
  IWriterPdfObject<PdfFontFile>
    lazyGetWriterPdfFontFile() throws Exception;

  /**
   * <p>Getter for writerPdfFontType0.</p>
   * @return WriterPdfFontType0
   * @throws Exception an Exception
   **/
  IWriterPdfObject<PdfFontType0>
    lazyGetWriterPdfFontType0() throws Exception;

  /**
   * <p>Getter for writerPdfFontType1S14.</p>
   * @return WriterPdfFontType1S14
   * @throws Exception an Exception
   **/
  IWriterPdfObject<PdfFontType1S14>
    lazyGetWriterPdfFontType1S14() throws Exception;


  /**
   * <p>Getter for fctDocTable.</p>
   * @return FctDocTable<HasPdfContent>
   * @throws Exception an Exception
   **/
  IFctDocTable<WI> lazyGetFctDocTable() throws Exception;

  /**
   * <p>Getter for deriverElTable.</p>
   * @return DeriverElTable<WI>
   * @throws Exception an Exception
   **/
  IDeriverElements<WI, DocTable<WI>> lazyGetDeriverElTable() throws Exception;

  /**
   * <p>Getter for deriverElPagination.</p>
   * @return DeriverElTable<WI>
   * @throws Exception an Exception
   **/
  IDeriverElements<WI, Pagination<WI>>
    lazyGetDeriverElPagination() throws Exception;

  /**
   * <p>Getter for evalMetricsString.</p>
   * @return EvalMetricsString
   * @throws Exception an Exception
   **/
  IEvalMetricsString lazyGetEvalMetricsString() throws Exception;

  /**
   * <p>Getter for evalCharWidth.</p>
   * @return EvalCharWidth
   * @throws Exception an Exception
   **/
  IEvalCharWidth lazyGetEvalCharWidth() throws Exception;

  /**
   * <p>Getter for ResourceStreamer.</p>
   * @return ResourceStreamer
   * @throws Exception an Exception
   **/
  ITtfSourceStreamer lazyGetTtfResourceStreamer() throws Exception;

  /**
   * <p>Getter for FileStreamer.</p>
   * @return FileStreamer
   * @throws Exception an Exception
   **/
  ITtfSourceStreamer lazyGetTtfFileStreamer() throws Exception;
}

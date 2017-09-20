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

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

import org.beigesoft.doc.model.DocString;
import org.beigesoft.doc.model.DocPage;
import org.beigesoft.doc.model.IElement;
import org.beigesoft.doc.model.IFont;
import org.beigesoft.doc.service.UomHelper;
import org.beigesoft.ttf.model.TtfFont;
import org.beigesoft.ttf.model.CompoundGlyph;
import org.beigesoft.ttf.service.TtfLoader;
import org.beigesoft.pdf.model.EFontS14;
import org.beigesoft.pdf.model.PdfFontDescriptor;
import org.beigesoft.pdf.model.PdfDocument;
import org.beigesoft.pdf.model.PdfPage;
import org.beigesoft.pdf.model.PdfContent;
import org.beigesoft.pdf.model.IHasPdfContent;
import org.beigesoft.pdf.model.IPdfObject;
import org.beigesoft.pdf.model.PdfCidFontType2;
import org.beigesoft.pdf.model.PdfFontFile;
import org.beigesoft.pdf.model.PdfFontType0;
import org.beigesoft.pdf.model.PdfFontType1S14;
import org.beigesoft.pdf.model.APdfStream;
import org.beigesoft.pdf.model.PdfToUnicode;
import org.beigesoft.pdf.exception.ExceptionPdfWr;

/**
 * <p>Service that makes PDF document.</p>
 *
 * @param <WI> writing instrument type
 * @author Yury Demidenko
 */
public class PdfMaker<WI extends IHasPdfContent> implements IPdfMaker<WI> {

  /**
   * <p>App-scoped UOM helper.</p>
   **/
  private UomHelper uomHelper;

  /**
   * <p>App-scoped to TTF loader.</p>
   **/
  private TtfLoader ttfLoader;

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
   * <p>Writer of PdfFontType0.</p>
   **/
  private WriterPdfFontType0 writerPdfFontType0;

  /**
   * <p>Writer of PdfFontType1S14.</p>
   **/
  private WriterPdfFontType1S14 writerPdfFontType1S14;

  /**
   * <p>App-scoped TTF fonts data map.</p>
   **/
  private final Map<String, TtfFont> ttfFonts = new HashMap<String, TtfFont>();

  /**
   * <p>App-scoped TTF PdfFontDescriptor map.</p>
   **/
  private final Map<String, PdfFontDescriptor> ttfFontDescriptors =
    new HashMap<String, PdfFontDescriptor>();

  /**
   * <p>Prepare before write.</p>
   * @param pDoc PDF document
   * @param pBaseDoc base document
   * @throws Exception an Exception
   **/
  @Override
  public final void prepareBeforeWrite(
    final PdfDocument<WI> pDoc) throws Exception {
    for (DocPage<WI> dpg : pDoc.getMainDoc().getPages()) {
      addPage(pDoc, dpg);
    }
    for (PdfToUnicode toUni : pDoc.getPdfToUnicodes()) {
      toUni.getUsedCids().clear();
      toUni.getUsedCids().add((char) 0); //null always presents
    }
    for (int i = 0; i < pDoc.getPdfObjects().size(); i++) {
      pDoc.getPdfObjects().get(i).setNumber(i + 1);
      if (pDoc.getPdfObjects().get(i) instanceof PdfContent) {
        PdfContent cont = (PdfContent) pDoc.getPdfObjects().get(i);
        for (IElement el : cont.getPage().getElements()) {
          if (el instanceof DocString) {
            @SuppressWarnings("unchecked")
            DocString<WI> pstr = (DocString<WI>) el;
            IPdfObject fnt = pDoc.getResources()
              .getFonts().get(pstr.getFontNumber() - 1);
            if (fnt instanceof PdfFontType0) {
              PdfFontType0 fnt0 = (PdfFontType0) fnt;
              for (int j = 0; j < pstr.getValue().length(); j++) {
                char uni = (char) pstr.getValue().codePointAt(j);
                Character cid = fnt0.getToUnicode().getUniToCid().get(uni);
                if (cid == null) {
                  throw new ExceptionPdfWr("There is no CID for UNI/char: "
                    + ((int) uni) + "/" + uni);
                }
                if (fnt0.getToUnicode().getUsedCids().indexOf(cid) == -1) {
                  fnt0.getToUnicode().getUsedCids().add(cid);
                  fnt0.getToUnicode().getUsedCidToUni().put(cid, uni);
                  for (CompoundGlyph cgl : fnt0.getCompoundGlyphs()) {
                    if (cgl.getGid() == cid) {
                      for (char partGid : cgl.getPartsGids()) {
                        fnt0.getToUnicode().getUsedCids().add(partGid);
                      }
                      break;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    int lastIdx = pDoc.getPdfObjects().size();
    for (int i = 0; i < pDoc.getPdfToUnicodes().size(); i++) {
      pDoc.getPdfToUnicodes().get(i).setNumber(lastIdx + i + 1);
      Collections.sort(pDoc.getPdfToUnicodes().get(i).getUsedCids());
    }
    lastIdx += pDoc.getPdfToUnicodes().size();
    for (int i = 0; i < pDoc.getCidType2Fonts().size(); i++) {
      pDoc.getCidType2Fonts().get(i).setNumber(lastIdx + i + 1);
    }
    lastIdx += pDoc.getCidType2Fonts().size();
    for (int i = 0; i < pDoc.getFontDescriptors().size(); i++) {
      pDoc.getFontDescriptors().get(i).setNumber(lastIdx + i + 1);
    }
    lastIdx += pDoc.getFontDescriptors().size();
    for (int i = 0; i < pDoc.getFontFiles().size(); i++) {
      pDoc.getFontFiles().get(i).setNumber(lastIdx + i + 1);
    }
  }

  /**
   * <p>Setter for if compressed for all streams.</p>
   * @param pDoc document
   * @param pIsCompressed is compressed
   * @throws Exception an Exception
   **/
  @Override
  public final void setIsCompressed(final PdfDocument<WI> pDoc,
    final boolean pIsCompressed) throws Exception {
    pDoc.setIsCompressed(pIsCompressed);
    for (IPdfObject pdfObj : pDoc.getPdfObjects()) {
      if (pdfObj instanceof APdfStream) {
        APdfStream pdfSt = (APdfStream) pdfObj;
        pdfSt.setIsCompressed(pIsCompressed);
      }
    }
    for (PdfToUnicode pdfObj : pDoc.getPdfToUnicodes()) {
      pdfObj.setIsCompressed(pIsCompressed);
    }
  }

  /**
   * <p>Add page.</p>
   * @param pDoc document
   * @param pDocPage document page
   **/
  @Override
  public final void addPage(final PdfDocument<WI> pDoc,
    final DocPage<WI> pDocPage) throws Exception {
    PdfPage pg = new PdfPage();
    double urx = this.uomHelper.toPoints(pDocPage.getWidth(), pDoc
      .getMainDoc().getResolutionDpi(), pDoc.getMainDoc().getUnitOfMeasure());
    double ury = this.uomHelper.toPoints(pDocPage.getHeight(), pDoc
      .getMainDoc().getResolutionDpi(), pDoc.getMainDoc().getUnitOfMeasure());
    pg.getMediabox().setUrX((float) urx);
    pg.getMediabox().setUrY((float) ury);
    pg.setWriter(this.writerPdfPage);
    pg.setParent(pDoc.getPdfTrailer().getRoot().getPages());
    pg.setResources(pDoc.getResources());
    pDoc.getPdfObjects().add(pg);
    pDoc.getPdfTrailer().getRoot().getPages().getPages().add(pg);
    PdfContent pdfCn = new PdfContent();
    pdfCn.setWriter(this.writerPdfContent);
    pdfCn.setDocument(pDoc);
    pdfCn.setPage(pDocPage);
    pg.setContents(pdfCn);
    pDoc.getPdfObjects().add(pdfCn);
  }

  /**
   * <p>Add font type 1 standard 14.</p>
   * @param pDoc document
   * @param pFontT1S14 to add
   * @throws Exception an Exception
   **/
  @Override
  public final void addFontT1S14(final PdfDocument<WI> pDoc,
    final EFontS14 pFontS14) throws Exception {
    for (IFont fnt : pDoc.getMainDoc().getFonts()) {
      if (fnt.getItsName().equals(pFontS14.toString())) {
        throw new ExceptionPdfWr("Font is already added!!! "
          + pFontS14.toString());
      }
    }
    PdfFontType1S14 pdfFn = new PdfFontType1S14();
    pdfFn.setBaseFont(pFontS14);
    pdfFn.setWriter(this.writerPdfFontType1S14);
    pDoc.getMainDoc().getFonts().add(pdfFn);
    pDoc.getMainDoc().setFontNumber(pDoc.getMainDoc().getFonts().size());
    pDoc.getResources().getFonts().add(pdfFn);
    pDoc.getPdfObjects().add(pdfFn);
  }

  /**
   * <p>Add international friendly TTF font.</p>
   * @param pDoc document
   * @param pFontName to add
   * @throws Exception an Exception
   **/
  @Override
  public final void addFontTtf(final PdfDocument<WI> pDoc,
    final String pFontName) throws Exception {
    for (IFont fnt : pDoc.getMainDoc().getFonts()) {
      if (fnt.getItsName().equals(pFontName)) {
        throw new ExceptionPdfWr("Font is already added!!! "
          + pFontName);
      }
    }
    PdfFontType0 pdfFn = new PdfFontType0();
    pdfFn.setBaseFont(pFontName);
    pdfFn.setWriter(this.writerPdfFontType0);
    pDoc.getResources().getFonts().add(pdfFn);
    pDoc.getPdfObjects().add(pdfFn);
    PdfToUnicode toUni = new PdfToUnicode();
    toUni.setWriter(this.writerPdfToUnicode);
    pdfFn.setToUnicode(toUni);
    pDoc.getPdfToUnicodes().add(toUni);
    PdfCidFontType2 cidFnt = new PdfCidFontType2();
    cidFnt.setWriter(this.writerPdfCidFontType2);
    cidFnt.setBaseFont(pFontName);
    cidFnt.setToUnicode(toUni);
    pdfFn.setDescendantFonts(cidFnt);
    pDoc.getCidType2Fonts().add(cidFnt);
    PdfFontFile ff = new PdfFontFile();
    ff.setWriter(this.writerPdfFontFile);
    ff.setFileName(pFontName);
    ff.setToUnicode(toUni);
    pDoc.getFontFiles().add(ff);
    TtfFont ttfFont = lazyGetTtfFontAndDescriptor(pFontName);
    pdfFn.setCompoundGlyphs(ttfFont.getGlyf().getCompoundGlyphs());
    cidFnt.setHmtx(ttfFont.getHmtx());
    cidFnt.setUnitsPerEm(ttfFont.getHead().getUnitsPerEm());
    toUni.setUniToCid(ttfFont.getCmap().getUniToCid());
    PdfFontDescriptor fd = this.ttfFontDescriptors.get(pFontName);
    fd.setFontFile2(ff);
    cidFnt.setFontDescriptor(fd);
    pDoc.getMainDoc().getFonts().add(pdfFn);
    pDoc.getMainDoc().setFontNumber(pDoc.getMainDoc().getFonts().size());
    pDoc.getFontDescriptors().add(fd);
  }

  /**
   * <p>Get in lazy mode TTF data and font descriptor.
   * This is actually private (for internal usage) method.
   * It's public for tests purposes.</p>
   * @param pFontName to add
   * @return TTF font data
   * @throws Exception an Exception
   **/
  public final TtfFont lazyGetTtfFontAndDescriptor(
    final String pFontName) throws Exception {
    TtfFont fnt = this.ttfFonts.get(pFontName);
    if (fnt == null) {
      synchronized (this) {
        fnt = this.ttfFonts.get(pFontName);
        if (fnt == null) {
          fnt = this.ttfLoader.loadFontTtf(pFontName);
          PdfFontDescriptor fd = new PdfFontDescriptor();
          fd.setWriter(this.writerPdfFontDescriptor);
          fd.setFontName(pFontName);
          float scaling = 1000f / fnt.getHead().getUnitsPerEm();
          fd.setMinX(fnt.getHead().getXMin() * scaling);
          fd.setMaxX(fnt.getHead().getXMax() * scaling);
          fd.setMinY(fnt.getHead().getYMin() * scaling);
          fd.setMaxY(fnt.getHead().getYMax() * scaling);
          if (fnt.getOs2() != null) {
            fd.setFontWeight(fnt.getOs2().getUsWeightClass());
            fd.setCapHeight(fnt.getOs2().getSCapHeight() * scaling);
            fd.setXHeight(fnt.getOs2().getSxHeight() * scaling);
            fd.setIsItalic7((fnt.getOs2().getFsSelection() & 0b1) > 0);
            switch (fnt.getOs2().getSFamilyClass()) {
              case 1: //OldStyle Serifs
              case 2: //Transitional Serifs
              case 3: //Modern Serifs
              case 4: //Clarendon Serifs
              case 5: //Slab Serifs
              case 7: //Freeform Serifs
              fd.setIsSerif2(true);
              break;
              case 10: //Scripts
              fd.setIsScript4(true);
              break;
              default:
              break;
            }
          }
          if (fnt.getHhea() != null) {
            fd.setAscent(fnt.getHhea().getAscent() * scaling);
            fd.setDescent(fnt.getHhea().getDescent() * scaling);
          }
          if (fnt.getPost() != null) {
            fd.setIsFixedPitch1(fnt.getPost().getIsFixedPitch());
            fd.setItalicAngle(fnt.getPost().getItalicAngle());
          }
          fd.evalFlags();
          this.ttfFonts.put(pFontName, fnt);
          this.ttfFontDescriptors.put(pFontName, fd);
        }
      }
    }
    return fnt;
  }

  //Synchronized SGS:
  /**
   * <p>Getter for ttfFonts.</p>
   * @return Map<String, TtfFont>
   **/
  public final synchronized Map<String, TtfFont> getTtfFonts() {
    return this.ttfFonts;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for uomHelper.</p>
   * @return UomHelper
   **/
  public final UomHelper getUomHelper() {
    return this.uomHelper;
  }

  /**
   * <p>Setter for uomHelper.</p>
   * @param pUomHelper reference
   **/
  public final void setUomHelper(final UomHelper pUomHelper) {
    this.uomHelper = pUomHelper;
  }

  /**
   * <p>Getter for ttfLoader.</p>
   * @return TtfLoader
   **/
  public final TtfLoader getTtfLoader() {
    return this.ttfLoader;
  }

  /**
   * <p>Setter for ttfLoader.</p>
   * @param pTtfLoader reference
   **/
  public final void setTtfLoader(final TtfLoader pTtfLoader) {
    this.ttfLoader = pTtfLoader;
  }

  /**
   * <p>Getter for writerPdfContent.</p>
   * @return WriterPdfContent
   **/
  public final WriterPdfContent getWriterPdfContent() {
    return this.writerPdfContent;
  }

  /**
   * <p>Setter for writerPdfContent.</p>
   * @param pWriterPdfContent reference
   **/
  public final void setWriterPdfContent(
    final WriterPdfContent pWriterPdfContent) {
    this.writerPdfContent = pWriterPdfContent;
  }

  /**
   * <p>Getter for writerPdfPage.</p>
   * @return WriterPdfPage
   **/
  public final WriterPdfPage getWriterPdfPage() {
    return this.writerPdfPage;
  }

  /**
   * <p>Setter for writerPdfPage.</p>
   * @param pWriterPdfPage reference
   **/
  public final void setWriterPdfPage(final WriterPdfPage pWriterPdfPage) {
    this.writerPdfPage = pWriterPdfPage;
  }

  /**
   * <p>Getter for writerPdfToUnicode.</p>
   * @return WriterPdfToUnicode
   **/
  public final WriterPdfToUnicode getWriterPdfToUnicode() {
    return this.writerPdfToUnicode;
  }

  /**
   * <p>Setter for writerPdfToUnicode.</p>
   * @param pWriterPdfToUnicode reference
   **/
  public final void setWriterPdfToUnicode(
    final WriterPdfToUnicode pWriterPdfToUnicode) {
    this.writerPdfToUnicode = pWriterPdfToUnicode;
  }

  /**
   * <p>Getter for writerPdfCidFontType2.</p>
   * @return WriterPdfCidFontType2
   **/
  public final WriterPdfCidFontType2 getWriterPdfCidFontType2() {
    return this.writerPdfCidFontType2;
  }

  /**
   * <p>Setter for writerPdfCidFontType2.</p>
   * @param pWriterPdfCidFontType2 reference
   **/
  public final void setWriterPdfCidFontType2(
    final WriterPdfCidFontType2 pWriterPdfCidFontType2) {
    this.writerPdfCidFontType2 = pWriterPdfCidFontType2;
  }

  /**
   * <p>Getter for writerPdfFontDescriptor.</p>
   * @return WriterPdfFontDescriptor
   **/
  public final WriterPdfFontDescriptor getWriterPdfFontDescriptor() {
    return this.writerPdfFontDescriptor;
  }

  /**
   * <p>Setter for writerPdfFontDescriptor.</p>
   * @param pWriterPdfFontDescriptor reference
   **/
  public final void setWriterPdfFontDescriptor(
    final WriterPdfFontDescriptor pWriterPdfFontDescriptor) {
    this.writerPdfFontDescriptor = pWriterPdfFontDescriptor;
  }

  /**
   * <p>Getter for writerPdfFontFile.</p>
   * @return WriterPdfFontFile
   **/
  public final WriterPdfFontFile getWriterPdfFontFile() {
    return this.writerPdfFontFile;
  }

  /**
   * <p>Setter for writerPdfFontFile.</p>
   * @param pWriterPdfFontFile reference
   **/
  public final void setWriterPdfFontFile(
    final WriterPdfFontFile pWriterPdfFontFile) {
    this.writerPdfFontFile = pWriterPdfFontFile;
  }

  /**
   * <p>Getter for writerPdfFontType0.</p>
   * @return WriterPdfFontType0
   **/
  public final WriterPdfFontType0 getWriterPdfFontType0() {
    return this.writerPdfFontType0;
  }

  /**
   * <p>Setter for writerPdfFontType0.</p>
   * @param pWriterPdfFontType0 reference
   **/
  public final void setWriterPdfFontType0(
    final WriterPdfFontType0 pWriterPdfFontType0) {
    this.writerPdfFontType0 = pWriterPdfFontType0;
  }

  /**
   * <p>Getter for writerPdfFontType1S14.</p>
   * @return WriterPdfFontType1S14
   **/
  public final WriterPdfFontType1S14 getWriterPdfFontType1S14() {
    return this.writerPdfFontType1S14;
  }

  /**
   * <p>Setter for writerPdfFontType1S14.</p>
   * @param pWriterPdfFontType1S14 reference
   **/
  public final void setWriterPdfFontType1S14(
    final WriterPdfFontType1S14 pWriterPdfFontType1S14) {
    this.writerPdfFontType1S14 = pWriterPdfFontType1S14;
  }
}

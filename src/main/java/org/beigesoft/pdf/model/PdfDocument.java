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

package org.beigesoft.pdf.model;

import java.util.List;

import org.beigesoft.doc.model.Document;

/**
 * <p>PDF mainDoc model.</p>
 *
 * @param <WI> writing instrument type
 * @author Yury Demidenko
 */
public class PdfDocument<WI> {

  /**
   * <p>PDF version integer, 14 means 1.4 and so on.</p>
   **/
  private int version = 15;

  /**
   * <p>PDF objects except fonts, images and resources.</p>
   **/
  private List<IPdfObject> pdfObjects;

  /**
   * <p>PDF to unicode depending of content.</p>
   **/
  private List<PdfToUnicode> pdfToUnicodes;

  /**
   * <p>PDF CIDfontType2 depending of content.</p>
   **/
  private List<PdfCidFontType2> cidType2Fonts;

  /**
   * <p>PDF font descriptors owned by CIDfontType2.</p>
   **/
  private List<PdfFontDescriptor> fontDescriptors;

  /**
   * <p>PDF font files owned by PdfFontDescriptor.</p>
   **/
  private List<PdfFontFile> fontFiles;

  /**
   * <p>PDF images created by ImageWriter during writing content page,
   * so they are the last, and must be cleared "before PDF writing".</p>
   **/
  private List<PdfImage> images;

  /**
   * <p>PDF trailer.</p>
   **/
  private PdfTrailer pdfTrailer;

  /**
   * <p>PDF xref.</p>
   **/
  private PdfXref pdfXref;

  /**
   * <p>PDF resources.</p>
   **/
  private PdfResources resources;

  /**
   * <p>Document main.</p>
   **/
  private Document<WI> mainDoc;

  /**
   * <p>If compressed for all pages.</p>
   **/
  private Boolean isCompressed = true;

  /**
   * <p>PDF info for external initialization.</p>
   **/
  private PdfInfo pdfInfo;

  //Simple getters and setters:
  /**
   * <p>Getter for version.</p>
   * @return String
   **/
  public final int getVersion() {
    return this.version;
  }

  /**
   * <p>Listter for version.</p>
   * @param pVersion reference
   **/
  public final void setVersion(final int pVersion) {
    this.version = pVersion;
  }

  /**
   * <p>Getter for pdfObjects.</p>
   * @return List<IPdfObject>
   **/
  public final List<IPdfObject> getPdfObjects() {
    return this.pdfObjects;
  }

  /**
   * <p>Listter for pdfObjects.</p>
   * @param pPdfObjects reference
   **/
  public final void setPdfObjects(final List<IPdfObject> pPdfObjects) {
    this.pdfObjects = pPdfObjects;
  }

  /**
   * <p>Getter for pdfToUnicodes.</p>
   * @return List<PdfToUnicode>
   **/
  public final List<PdfToUnicode> getPdfToUnicodes() {
    return this.pdfToUnicodes;
  }

  /**
   * <p>Setter for pdfToUnicodes.</p>
   * @param pPdfToUnicodes reference
   **/
  public final void setPdfToUnicodes(final List<PdfToUnicode> pPdfToUnicodes) {
    this.pdfToUnicodes = pPdfToUnicodes;
  }

  /**
   * <p>Getter for cidType2Fonts.</p>
   * @return List<PdfCidFontType2>
   **/
  public final List<PdfCidFontType2> getCidType2Fonts() {
    return this.cidType2Fonts;
  }

  /**
   * <p>Setter for cidType2Fonts.</p>
   * @param pCidType2Fonts reference
   **/
  public final void setCidType2Fonts(
    final List<PdfCidFontType2> pCidType2Fonts) {
    this.cidType2Fonts = pCidType2Fonts;
  }

  /**
   * <p>Getter for fontDescriptors.</p>
   * @return List<PdfFontDescriptor>
   **/
  public final List<PdfFontDescriptor> getFontDescriptors() {
    return this.fontDescriptors;
  }

  /**
   * <p>Setter for fontDescriptors.</p>
   * @param pFontDescriptors reference
   **/
  public final void setFontDescriptors(
    final List<PdfFontDescriptor> pFontDescriptors) {
    this.fontDescriptors = pFontDescriptors;
  }

  /**
   * <p>Getter for fontFiles.</p>
   * @return List<PdfFontFile>
   **/
  public final List<PdfFontFile> getFontFiles() {
    return this.fontFiles;
  }

  /**
   * <p>Setter for fontFiles.</p>
   * @param pFontFiles reference
   **/
  public final void setFontFiles(final List<PdfFontFile> pFontFiles) {
    this.fontFiles = pFontFiles;
  }

  /**
   * <p>Getter for pdfTrailer.</p>
   * @return PdfTrailer
   **/
  public final PdfTrailer getPdfTrailer() {
    return this.pdfTrailer;
  }

  /**
   * <p>Listter for pdfTrailer.</p>
   * @param pPdfTrailer reference
   **/
  public final void setPdfTrailer(final PdfTrailer pPdfTrailer) {
    this.pdfTrailer = pPdfTrailer;
  }

  /**
   * <p>Getter for pdfXref.</p>
   * @return PdfXref
   **/
  public final PdfXref getPdfXref() {
    return this.pdfXref;
  }

  /**
   * <p>Listter for pdfXref.</p>
   * @param pPdfXref reference
   **/
  public final void setPdfXref(final PdfXref pPdfXref) {
    this.pdfXref = pPdfXref;
  }

  /**
   * <p>Getter for resources.</p>
   * @return PdfResources
   **/
  public final PdfResources getResources() {
    return this.resources;
  }

  /**
   * <p>Setter for resources.</p>
   * @param pResources reference
   **/
  public final void setResources(final PdfResources pResources) {
    this.resources = pResources;
  }

  /**
   * <p>Getter for mainDoc.</p>
   * @return Document
   **/
  public final Document<WI> getMainDoc() {
    return this.mainDoc;
  }

  /**
   * <p>Setter for mainDoc.</p>
   * @param pDocument reference
   **/
  public final void setMainDoc(final Document<WI> pDocument) {
    this.mainDoc = pDocument;
  }

  /**
   * <p>If compressed for all streams.</p>
   * @return is compressed
   **/
  public final Boolean getIsCompressed() {
    return this.isCompressed;
  }

  /**
   * <p>Setter for if compressed for all streams.</p>
   * @param pIsCompressed is compressed
   **/
  public final void setIsCompressed(final Boolean pIsCompressed) {
    this.isCompressed = pIsCompressed;
  }

  /**
   * <p>Getter for pdfInfo.</p>
   * @return PdfInfo
   **/
  public final PdfInfo getPdfInfo() {
    return this.pdfInfo;
  }

  /**
   * <p>Setter for pdfInfo.</p>
   * @param pPdfInfo reference
   **/
  public final void setPdfInfo(final PdfInfo pPdfInfo) {
    this.pdfInfo = pPdfInfo;
  }

  /**
   * <p>Getter for images.</p>
   * @return List<PdfImage>
   **/
  public final List<PdfImage> getImages() {
    return this.images;
  }

  /**
   * <p>Setter for images.</p>
   * @param pImages reference
   **/
  public final void setImages(final List<PdfImage> pImages) {
    this.images = pImages;
  }
}

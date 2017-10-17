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
import java.io.OutputStream;

import org.beigesoft.pdf.model.IPdfObject;
import org.beigesoft.pdf.model.PdfToUnicode;
import org.beigesoft.pdf.model.PdfCidFontType2;
import org.beigesoft.pdf.model.PdfFontFile;
import org.beigesoft.pdf.model.PdfFontDescriptor;
import org.beigesoft.pdf.model.PdfDocument;

/**
 * <p>PDF writer.</p>
 *
 * @param <WI> writing instrument type
 * @author Yury Demidenko
 */
public class PdfWriter<WI> implements IPdfWriter<WI> {

  /**
   * <p>Write helper.</p>
   **/
  private PdfWriteHelper writeHelper;

  /**
   * <p>Write document to stream without closing and handling exception.</p>
   * @param pAddParam additional params
   * @param pDoc PDF document
   * @param pOut stream
   * @throws Exception an Exception
   **/
  @Override
  public final void write(final Map<String, Object> pAddParam,
    final PdfDocument<WI> pDoc,
      final OutputStream pOut) throws Exception {
    int writenCount = 0;
    // PDF head:
    switch (pDoc.getVersion()) {
      case 10:
      writenCount += getWriteHelper().writeBytes(getWriteHelper()
        .getPdfVersion10().getBytes(getWriteHelper().getAscii()), pOut);
      break;
      case 11:
      writenCount += getWriteHelper().writeBytes(getWriteHelper()
        .getPdfVersion11().getBytes(getWriteHelper().getAscii()), pOut);
      break;
      case 12:
      writenCount += getWriteHelper().writeBytes(getWriteHelper()
        .getPdfVersion12().getBytes(getWriteHelper().getAscii()), pOut);
      break;
      case 13:
      writenCount += getWriteHelper().writeBytes(getWriteHelper()
        .getPdfVersion13().getBytes(getWriteHelper().getAscii()), pOut);
      break;
      case 15:
      writenCount += getWriteHelper().writeBytes(getWriteHelper()
        .getPdfVersion15().getBytes(getWriteHelper().getAscii()), pOut);
      break;
      case 16:
      writenCount += getWriteHelper().writeBytes(getWriteHelper()
        .getPdfVersion16().getBytes(getWriteHelper().getAscii()), pOut);
      break;
      case 17:
      writenCount += getWriteHelper().writeBytes(getWriteHelper()
        .getPdfVersion17().getBytes(getWriteHelper().getAscii()), pOut);
      break;
      default:
      writenCount += getWriteHelper().writeBytes(getWriteHelper()
        .getPdfVersion14().getBytes(getWriteHelper().getAscii()), pOut);
      break;
    }
    writenCount += getWriteHelper()
      .writeBytes(getWriteHelper().getHeadBinarySign(), pOut);
    // PDF objects:
    for (IPdfObject obj : pDoc.getPdfObjects()) {
      obj.setStart(writenCount);
      writenCount += obj.write(pOut);
    }
    for (PdfToUnicode obj : pDoc.getPdfToUnicodes()) {
      obj.setStart(writenCount);
      writenCount += obj.write(pOut);
    }
    for (PdfCidFontType2 obj : pDoc.getCidType2Fonts()) {
      obj.setStart(writenCount);
      writenCount += obj.write(pOut);
    }
    for (PdfFontDescriptor obj : pDoc.getFontDescriptors()) {
      obj.setStart(writenCount);
      writenCount += obj.write(pOut);
    }
    for (PdfFontFile obj : pDoc.getFontFiles()) {
      obj.setStart(writenCount);
      writenCount += obj.write(pOut);
    }
    // PDF xref:
    int totalObjects = pDoc.getPdfObjects().size() + pDoc.getPdfToUnicodes()
      .size() + pDoc.getCidType2Fonts().size()  + pDoc.getFontDescriptors()
        .size() + pDoc.getFontFiles().size();
    pDoc.getPdfXref().setStart(writenCount);
    getWriteHelper().writeBytes(getWriteHelper().getXrefLf(), pOut);
    String rangeStr = "0 " + (totalObjects + 1) + "\n";
    getWriteHelper()
      .writeBytes(rangeStr.getBytes(getWriteHelper().getAscii()), pOut);
    getWriteHelper().writeBytes(getWriteHelper().getXref1EntryLf(), pOut);
    for (IPdfObject obj : pDoc.getPdfObjects()) {
      String xrefEntryStr =
        String.format("%010d", obj.getStart()) + " "
          + String.format("%05d", obj.getGenNumber()) + " n\n";
      getWriteHelper()
        .writeBytes(xrefEntryStr.getBytes(getWriteHelper().getAscii()), pOut);
    }
    for (IPdfObject obj : pDoc.getPdfToUnicodes()) {
      String xrefEntryStr =
        String.format("%010d", obj.getStart()) + " "
          + String.format("%05d", obj.getGenNumber()) + " n\n";
      getWriteHelper()
        .writeBytes(xrefEntryStr.getBytes(getWriteHelper().getAscii()), pOut);
    }
    for (IPdfObject obj : pDoc.getCidType2Fonts()) {
      String xrefEntryStr =
        String.format("%010d", obj.getStart()) + " "
          + String.format("%05d", obj.getGenNumber()) + " n\n";
      getWriteHelper()
        .writeBytes(xrefEntryStr.getBytes(getWriteHelper().getAscii()), pOut);
    }
    for (IPdfObject obj : pDoc.getFontDescriptors()) {
      String xrefEntryStr =
        String.format("%010d", obj.getStart()) + " "
          + String.format("%05d", obj.getGenNumber()) + " n\n";
      getWriteHelper()
        .writeBytes(xrefEntryStr.getBytes(getWriteHelper().getAscii()), pOut);
    }
    for (IPdfObject obj : pDoc.getFontFiles()) {
      String xrefEntryStr =
        String.format("%010d", obj.getStart()) + " "
          + String.format("%05d", obj.getGenNumber()) + " n\n";
      getWriteHelper()
        .writeBytes(xrefEntryStr.getBytes(getWriteHelper().getAscii()), pOut);
    }
    // PDF trailer:
    getWriteHelper().writeBytes(getWriteHelper().getTrailerStart(), pOut);
    String sizeStr = String.valueOf(totalObjects + 1) + "\n";
    getWriteHelper()
      .writeBytes(sizeStr.getBytes(getWriteHelper().getAscii()), pOut);
    String rootStr = "/Root "
      + pDoc.getPdfTrailer().getRoot().getNumber() + " "
        + pDoc.getPdfTrailer().getRoot().getGenNumber() + " R\n";
    getWriteHelper()
      .writeBytes(rootStr.getBytes(getWriteHelper().getAscii()), pOut);
    if (pDoc.getPdfTrailer().getInfo() != null) {
      String infoStr = "/Info "
        + pDoc.getPdfTrailer().getInfo().getNumber() + " "
          + pDoc.getPdfTrailer().getInfo().getGenNumber() + " R";
      getWriteHelper()
        .writeBytes(infoStr.getBytes(getWriteHelper().getAscii()), pOut);
    }
    getWriteHelper().writeBytes(getWriteHelper().getEndDictLf(), pOut);
    getWriteHelper().writeBytes(getWriteHelper().getStartxrefLf(), pOut);
    String xrefStartStr = pDoc.getPdfXref().getStart().toString() + "\n";
    getWriteHelper()
      .writeBytes(xrefStartStr.getBytes(getWriteHelper().getAscii()), pOut);
    getWriteHelper().writeBytes(getWriteHelper().getEof(), pOut);
  }

  //Simple getters and setters:
  /**
   * <p>Getter for writeHelper.</p>
   * @return PdfWriteHelper
   **/
  public final PdfWriteHelper getWriteHelper() {
    return this.writeHelper;
  }

  /**
   * <p>Setter for writeHelper.</p>
   * @param pWriteHelper reference
   **/
  public final void setWriteHelper(final PdfWriteHelper pWriteHelper) {
    this.writeHelper = pWriteHelper;
  }
}

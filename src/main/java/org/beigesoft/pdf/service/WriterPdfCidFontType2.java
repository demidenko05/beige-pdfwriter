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

import java.util.List;
import java.util.ArrayList;
import java.io.OutputStream;

import org.beigesoft.pdf.model.EFontType;
import org.beigesoft.pdf.model.PdfCidFontType2;

/**
 * <p>PDF CIDFontType2 fonts writer.</p>
 *
 * @author Yury Demidenko
 */
public class WriterPdfCidFontType2 extends AWriterPdfObject<PdfCidFontType2> {

  /**
   * <p>Write object to stream and return bytes count.</p>
   * @param pPdfObj PdfCidFontType2
   * @param pOut stream
   * @return bytes count
   * @throws an Exception
   **/
  @Override
  public final int write(final PdfCidFontType2 pPdfObj,
    final OutputStream pOut) throws Exception {
    makeWidths(pPdfObj);
    int bytesWritten = 0;
    byte[] btsNumSp = (pPdfObj.getNumber().toString() + " ")
      .getBytes(getWriteHelper().getAscii());
    bytesWritten += getWriteHelper().writeBytes(btsNumSp, pOut);
    byte[] btsGenNumSp = (pPdfObj.getGenNumber().toString() + " ")
      .getBytes(getWriteHelper().getAscii());
    bytesWritten += getWriteHelper().writeBytes(btsGenNumSp, pOut);
    bytesWritten += getWriteHelper()
      .writeBytes(getWriteHelper().getStartObj(), pOut);
    String startStr = "/Type /Font\n";
    bytesWritten += getWriteHelper()
      .writeBytes(startStr.getBytes(getWriteHelper().getAscii()), pOut);
    String subtypeStr = "/Subtype /" + EFontType.CIDFONTTYPE2.toString() + "\n";
    bytesWritten += getWriteHelper()
      .writeBytes(subtypeStr.getBytes(getWriteHelper().getAscii()), pOut);
    String baseFontStr = "/BaseFont /" + pPdfObj.getBaseFont() + "\n";
    bytesWritten += getWriteHelper()
      .writeBytes(baseFontStr.getBytes(getWriteHelper().getAscii()), pOut);
    String cidToGidMapStr = "/CIDToGIDMap /" + pPdfObj.getCidToGidMap() + "\n";
    bytesWritten += getWriteHelper()
      .writeBytes(cidToGidMapStr.getBytes(getWriteHelper().getAscii()), pOut);
    String cidInfoStr = "/CIDSystemInfo <</Registry (Adobe)"
      + " /Ordering (Identity)"
      + " /Supplement 0>>\n";
    bytesWritten += getWriteHelper()
      .writeBytes(cidInfoStr.getBytes(getWriteHelper().getAscii()), pOut);
    bytesWritten += getWriteHelper()
      .writeBytes(getWriteHelper().getStartWarray(), pOut);
    for (String wdStr : pPdfObj.getWidthsList()) {
      bytesWritten += getWriteHelper()
        .writeBytes(wdStr.getBytes(getWriteHelper().getAscii()), pOut);
    }
    bytesWritten += getWriteHelper()
      .writeBytes(getWriteHelper().getEndArrLf(), pOut);
    //bytesWritten += getWriteHelper()
      //.writeBytes("/DW 0\n".getBytes(getWriteHelper().getAscii()), pOut);
    String fontDescriptorStr = "/FontDescriptor " + pPdfObj.getFontDescriptor()
      .getNumber() + " " + pPdfObj.getFontDescriptor().getGenNumber() + " R";
    bytesWritten += getWriteHelper().writeBytes(fontDescriptorStr
      .getBytes(getWriteHelper().getAscii()), pOut);
    bytesWritten += getWriteHelper()
      .writeBytes(getWriteHelper().getEndObjLf(), pOut);
    return bytesWritten;
  }

  /**
   * <p>Maker widths.</p>
   * @param pPdfObj PdfCidFontType2
   **/
  public final void makeWidths(final PdfCidFontType2 pPdfObj) {
    //TODO JUnit test
    pPdfObj.getWidthsList().clear();
    float scaling = 1000f / pPdfObj.getUnitsPerEm();
    Character cidStart = null;
    Character cidEnd = null;
    List<Character> wdthsArr = new ArrayList<Character>();
    for (Character cid : pPdfObj.getToUnicode().getUsedCids()) {
      float widthF = pPdfObj.getHmtx().getWidthForGid(cid);
      widthF *= scaling;
      char width = (char) Math.round(widthF);
      if (cidStart == null) {
        //the start:
        cidStart = cid;
        cidEnd = cid;
        wdthsArr.add(width);
      } else if (cid - cidEnd == 1) {
        cidEnd = cid;
        // continue second and more char:
        if (wdthsArr.size() > 1
          || width != wdthsArr.get(0) && cid - cidStart == 1) {
          // e.g. 100 [456 523](char 100 has 456, 101 has 523)
          wdthsArr.add(width);
          cidEnd = cid;
        } else if (wdthsArr.size() == 1
          && width != wdthsArr.get(0) && cid - cidStart != 1) {
          // was mono-space row
          finishWidthsEntry(pPdfObj, cidStart, cidEnd, wdthsArr);
          //continue new:
          cidStart = cid;
          cidEnd = cid;
          wdthsArr.clear();
          wdthsArr.add(width);
          wdthsArr.add(width);
        } else {
          //e.g. 100 110 456 (chars from 100 to 110 has 456)
          cidEnd = cid;
        }
      } else {
        finishWidthsEntry(pPdfObj, cidStart, cidEnd, wdthsArr);
        //continue new:
        cidStart = cid;
        cidEnd = cid;
        wdthsArr.clear();
        wdthsArr.add(width);
      }
    } //the last entry:
    finishWidthsEntry(pPdfObj, cidStart, cidEnd, wdthsArr);
  }

  /**
   * <p>Finish widths entry.</p>
   * @param pPdfObj PdfCidFontType2
   * @param pCidStart CID start
   * @param pCidEnd CID end
   * @param pWdthsArr widths array
   **/
  public final void finishWidthsEntry(final PdfCidFontType2 pPdfObj,
    final Character pCidStart, final Character pCidEnd,
      final List<Character> pWdthsArr) {
    StringBuffer sb = new StringBuffer();
    if (pCidEnd - pCidStart == 0) {
      // e.g. 100 [456](char 100 has 456)
      pPdfObj.getWidthsList().add(String.valueOf((int) pCidStart) + " ["
        + ((int) pWdthsArr.get(0)) + "] ");
    } else if (pWdthsArr.size() > 1) {
      // e.g. 100 [456 523](char 100 has 456, 101 has 523)
      sb.append(String.valueOf((int) pCidStart) + " [");
      for (int i = 0; i < pWdthsArr.size(); i++) {
        if (i > 0) {
          sb.append(" ");
        }
        sb.append(String.valueOf((int) pWdthsArr.get(i)));
      }
      sb.append("] ");
      pPdfObj.getWidthsList().add(sb.toString());
    } else {
      // e.g. 100 110 456 (chars from 100 to 110 has 456)
      pPdfObj.getWidthsList().add(String.valueOf((int) pCidStart) + " "
        + ((int) pCidEnd) + " " + ((int) pWdthsArr.get(0)) + " ");
    }
  }
}

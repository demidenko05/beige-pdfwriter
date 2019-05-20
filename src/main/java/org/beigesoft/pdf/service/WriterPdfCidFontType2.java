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

import java.util.List;
import java.util.ArrayList;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.beigesoft.pdf.model.EFontType;
import org.beigesoft.pdf.model.PdfCidFontType2;
import org.beigesoft.pdf.exception.ExceptionPdfWr;

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
   * @throws Exception an Exception
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
   * @throws Exception an Exception
   **/
  public final void makeWidths(final PdfCidFontType2 pPdfObj) throws Exception {
    //TODO JUnit test
    pPdfObj.getWidthsList().clear();
    double scaling = 1000.0 / pPdfObj.getUnitsPerEm();
    Character cidStart = null;
    Character cidEnd = null;
    List<BigDecimal> wdthsArr = new ArrayList<BigDecimal>();
    for (Character cid : pPdfObj.getToUnicode().getUsedCids()) {
      double widthF = pPdfObj.getHmtx().getWidthForGid(cid);
      BigDecimal width = BigDecimal.valueOf(widthF * scaling)
        .setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
      if (cidStart == null) {
        //the start:
        cidStart = cid;
        cidEnd = cid;
        wdthsArr.add(width);
      } else if (cid - cidEnd == 0) {
        throw new ExceptionPdfWr("Duplicate CID: " + cid);
      } else if (cid - cidEnd == 1) {
        cidEnd = cid;
        // continue second and more char:
        if (wdthsArr.size() > 1
          || width.compareTo(wdthsArr.get(0)) != 0 && cid - cidStart == 1) {
          // e.g. 100 [456 523](char 100 has 456, 101 has 523)
          wdthsArr.add(width);
          cidEnd = cid;
        } else if (wdthsArr.size() == 1
          && width.compareTo(wdthsArr.get(0)) != 0 && cid - cidStart != 1) {
          // was mono-space row
          finishWidthsEntry(pPdfObj, cidStart, cidEnd, wdthsArr);
          //continue new:
          cidStart = cid;
          cidEnd = cid;
          wdthsArr.clear();
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
      final List<BigDecimal> pWdthsArr) {
    StringBuffer sb = new StringBuffer();
    if (pCidEnd - pCidStart == 0) {
      // e.g. 100 [456](char 100 has 456)
      pPdfObj.getWidthsList().add(String.valueOf((int) pCidStart) + " ["
        + pWdthsArr.get(0).toPlainString() + "] ");
    } else if (pWdthsArr.size() > 1) {
      // e.g. 100 [456 523](char 100 has 456, 101 has 523)
      sb.append(String.valueOf((int) pCidStart) + " [");
      for (int i = 0; i < pWdthsArr.size(); i++) {
        if (i > 0) {
          sb.append(" ");
        }
        sb.append(pWdthsArr.get(i).toPlainString());
      }
      sb.append("] ");
      pPdfObj.getWidthsList().add(sb.toString());
    } else {
      // e.g. 100 110 456 (chars from 100 to 110 has 456)
      pPdfObj.getWidthsList().add(String.valueOf((int) pCidStart) + " "
        + ((int) pCidEnd) + " " + pWdthsArr.get(0).toPlainString() + " ");
    }
  }
}

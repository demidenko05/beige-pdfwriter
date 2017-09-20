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

import java.io.OutputStream;

import org.beigesoft.pdf.model.EFontType;
import org.beigesoft.pdf.model.PdfFontType0;

/**
 * <p>PDF composite Type0 TTF fonts writer.</p>
 *
 * @author Yury Demidenko
 */
public class WriterPdfFontType0 extends AWriterPdfObject<PdfFontType0> {

  /**
   * <p>Write object to stream and return bytes count.</p>
   * @param pPdfObj PdfFontType0
   * @param pOut stream
   * @return bytes count
   * @throws an Exception
   **/
  @Override
  public final int write(final PdfFontType0 pPdfObj,
    final OutputStream pOut) throws Exception {
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
    String subtypeStr = "/Subtype /" + EFontType.TYPE0.toString() + "\n";
    bytesWritten += getWriteHelper()
      .writeBytes(subtypeStr.getBytes(getWriteHelper().getAscii()), pOut);
    String baseFontStr = "/BaseFont /" + pPdfObj.getBaseFont() + "\n";
    bytesWritten += getWriteHelper()
      .writeBytes(baseFontStr.getBytes(getWriteHelper().getAscii()), pOut);
    String encodingStr = "/Encoding /" + pPdfObj.getEncoding() + "\n";
    bytesWritten += getWriteHelper()
      .writeBytes(encodingStr.getBytes(getWriteHelper().getAscii()), pOut);
    String descendantFontsStr = "/DescendantFonts [" + pPdfObj
      .getDescendantFonts().getNumber() + " " + pPdfObj.getDescendantFonts()
        .getGenNumber() + " R]\n";
    bytesWritten += getWriteHelper().writeBytes(descendantFontsStr
      .getBytes(getWriteHelper().getAscii()), pOut);
    String toUnicodeStr = "/ToUnicode " + pPdfObj.getToUnicode().getNumber()
      + " " + pPdfObj.getToUnicode().getGenNumber() + " R";
    bytesWritten += getWriteHelper()
      .writeBytes(toUnicodeStr.getBytes(getWriteHelper().getAscii()), pOut);
    bytesWritten += getWriteHelper()
      .writeBytes(getWriteHelper().getEndObjLf(), pOut);
    return bytesWritten;
  }
}

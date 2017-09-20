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
import org.beigesoft.pdf.model.PdfFontType1S14;

/**
 * <p>PDF standard (14) Type1 fonts writer.</p>
 *
 * @author Yury Demidenko
 */
public class WriterPdfFontType1S14 extends AWriterPdfObject<PdfFontType1S14> {

  /**
   * <p>Write object to stream and return bytes count.</p>
   * @param pPdfObj PdfFontType1S14
   * @param pOut stream
   * @return bytes count
   * @throws an Exception
   **/
  @Override
  public final int write(final PdfFontType1S14 pPdfObj,
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
    String subtypeStr = "/Subtype /" + EFontType.TYPE1.toString() + "\n";
    bytesWritten += getWriteHelper()
      .writeBytes(subtypeStr.getBytes(getWriteHelper().getAscii()), pOut);
    String baseFontStr = "/BaseFont /" + pPdfObj.getBaseFont().toString();
    bytesWritten += getWriteHelper()
      .writeBytes(baseFontStr.getBytes(getWriteHelper().getAscii()), pOut);
    bytesWritten += getWriteHelper()
      .writeBytes(getWriteHelper().getEndObjLf(), pOut);
    return bytesWritten;
  }
}

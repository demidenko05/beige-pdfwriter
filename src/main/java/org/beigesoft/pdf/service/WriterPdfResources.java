package org.beigesoft.pdf.service;

/*
 * Copyright (c) 2017 Beigesoft ™
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

import org.beigesoft.pdf.model.PdfResources;

/**
 * <p>PDF resources dictionary writer.</p>
 *
 * @author Yury Demidenko
 */
public class WriterPdfResources extends AWriterPdfObject<PdfResources> {

  /**
   * <p>Write object to stream and return bytes count.</p>
   * @param pPdfObj PdfResources
   * @param pOut stream
   * @return bytes count
   * @throws an Exception
   **/
  @Override
  public final int write(final PdfResources pPdfObj,
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
    StringBuffer sb = new StringBuffer();
    if (pPdfObj.getFonts() != null && pPdfObj.getFonts().size() > 0) {
      sb.append("/Font << ");
      for (int i = 0; i < pPdfObj.getFonts().size(); i++) {
        sb.append("/F" + (i + 1) + " " + pPdfObj.getFonts().get(i).getNumber()
          + " " + pPdfObj.getFonts().get(i).getGenNumber() + " R ");
      }
      sb.append(">>\n");
      bytesWritten += getWriteHelper()
        .writeBytes(sb.toString().getBytes(getWriteHelper().getAscii()), pOut);
    }
    if (pPdfObj.getImages() != null && pPdfObj.getImages().size() > 0) {
      sb.append("/XObject << ");
      for (int i = 0; i < pPdfObj.getImages().size(); i++) {
        sb.append("/Im" + (i + 1) + " " + pPdfObj.getImages().get(i).getNumber()
          + " " + pPdfObj.getImages().get(i).getGenNumber() + " R ");
      }
      sb.append(">>\n");
      bytesWritten += getWriteHelper()
        .writeBytes(sb.toString().getBytes(getWriteHelper().getAscii()), pOut);
    }
    bytesWritten += getWriteHelper()
      .writeBytes(getWriteHelper().getEndObjLf(), pOut);
    return bytesWritten;
  }
}

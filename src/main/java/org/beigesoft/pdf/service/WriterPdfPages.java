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

import org.beigesoft.pdf.model.PdfPage;
import org.beigesoft.pdf.model.PdfPages;

/**
 * <p>PDF pages dictionary writer.</p>
 *
 * @author Yury Demidenko
 */
public class WriterPdfPages extends AWriterPdfObject<PdfPages> {

  /**
   * <p>Write object to stream and return bytes count.</p>
   * @param pPdfObj PdfPages
   * @param pOut stream
   * @return bytes count
   * @throws an Exception
   **/
  @Override
  public final int write(final PdfPages pPdfObj,
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
    String startStr = "/Type /Pages\n/Count " + pPdfObj.getPages().size()
      + "\n/Kids [ ";
    bytesWritten += getWriteHelper()
      .writeBytes(startStr.getBytes(getWriteHelper().getAscii()), pOut);
    for (PdfPage pg : pPdfObj.getPages()) {
      byte[] btsPgNumSp = (pg.getNumber().toString() + " ")
        .getBytes(getWriteHelper().getAscii());
      bytesWritten += getWriteHelper().writeBytes(btsPgNumSp, pOut);
      byte[] btsPgGenNumSp = (pg.getGenNumber().toString() + " R ")
        .getBytes(getWriteHelper().getAscii());
      bytesWritten += getWriteHelper().writeBytes(btsPgGenNumSp, pOut);
    }
    bytesWritten += getWriteHelper()
      .writeBytes(getWriteHelper().getEndArrLf(), pOut);
    bytesWritten += getWriteHelper()
      .writeBytes(getWriteHelper().getEndObjLf(), pOut);
    return bytesWritten;
  }
}

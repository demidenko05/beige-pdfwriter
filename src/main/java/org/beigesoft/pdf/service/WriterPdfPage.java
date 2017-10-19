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

import org.beigesoft.pdf.model.PdfPage;

/**
 * <p>PDF page dictionary writer.</p>
 *
 * @author Yury Demidenko
 */
public class WriterPdfPage extends AWriterPdfObject<PdfPage> {

  /**
   * <p>Write object to stream and return bytes count.</p>
   * @param pPdfObj PdfPage
   * @param pOut stream
   * @return bytes count
   * @throws an Exception
   **/
  @Override
  public final int write(final PdfPage pPdfObj,
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
    String startStr = "/Type /Page\n";
    bytesWritten += getWriteHelper()
      .writeBytes(startStr.getBytes(getWriteHelper().getAscii()), pOut);
    String mediaboxStr = "/MediaBox [" + pPdfObj.getMediabox().getLlX() + " "
      + pPdfObj.getMediabox().getLlY() + " " + pPdfObj.getMediabox().getUrX()
        + " " + pPdfObj.getMediabox().getUrY() + "]\n";
    bytesWritten += getWriteHelper()
      .writeBytes(mediaboxStr.getBytes(getWriteHelper().getAscii()), pOut);
    String contentsStr = "/Contents " + pPdfObj.getContents().getNumber()
      .toString() + " " + pPdfObj.getContents().getGenNumber().toString()
        + " R\n";
    bytesWritten += getWriteHelper()
      .writeBytes(contentsStr.getBytes(getWriteHelper().getAscii()), pOut);
    String resourcesStr = "/Resources " + pPdfObj.getResources().getNumber()
      .toString() + " " + pPdfObj.getResources().getGenNumber().toString()
        + " R\n";
    bytesWritten += getWriteHelper()
      .writeBytes(resourcesStr.getBytes(getWriteHelper().getAscii()), pOut);
    String parentStr = "/Parent " + pPdfObj.getParent().getNumber().toString()
      + " " + pPdfObj.getParent().getGenNumber().toString() + " R";
    bytesWritten += getWriteHelper()
      .writeBytes(parentStr.getBytes(getWriteHelper().getAscii()), pOut);
    bytesWritten += getWriteHelper()
      .writeBytes(getWriteHelper().getEndObjLf(), pOut);
    return bytesWritten;
  }
}

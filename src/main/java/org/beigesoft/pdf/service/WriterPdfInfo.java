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

import org.beigesoft.pdf.model.PdfInfo;

/**
 * <p>PDF info dictionary writer.</p>
 *
 * @author Yury Demidenko
 */
public class WriterPdfInfo extends AWriterPdfObject<PdfInfo> {

  /**
   * <p>Write object to stream and return bytes count.</p>
   * @param pPdfObj PdfInfo
   * @param pOut stream
   * @return bytes count
   * @throws an Exception
   **/
  @Override
  public final int write(final PdfInfo pPdfObj,
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
    String creatorStr = "/Creator (" + getWriteHelper()
      .escapePdf(pPdfObj.getCreator()) + ")\n";
    bytesWritten += getWriteHelper()
      .writeBytes(creatorStr.getBytes(getWriteHelper().getAscii()), pOut);
    if (pPdfObj.getProducer() != null) {
      String producerStr = "/Producer (" + getWriteHelper()
        .escapePdf(pPdfObj.getProducer()) + ")\n";
      bytesWritten += getWriteHelper()
        .writeBytes(producerStr.getBytes(getWriteHelper().getAscii()), pOut);
    }
    if (pPdfObj.getTitle() != null) {
      String titleStr = "/Title (" + getWriteHelper()
        .escapePdf(pPdfObj.getTitle()) + ")\n";
      bytesWritten += getWriteHelper()
        .writeBytes(titleStr.getBytes(getWriteHelper().getAscii()), pOut);
    }
    if (pPdfObj.getAuthor() != null) {
      String authorStr = "/Author (" + getWriteHelper()
        .escapePdf(pPdfObj.getAuthor()) + ")\n";
      bytesWritten += getWriteHelper()
        .writeBytes(authorStr.getBytes(getWriteHelper().getAscii()), pOut);
    }
    if (pPdfObj.getSubject() != null) {
      String subjectStr = "/Subject (" + getWriteHelper()
        .escapePdf(pPdfObj.getSubject()) + ")\n";
      bytesWritten += getWriteHelper()
        .writeBytes(subjectStr.getBytes(getWriteHelper().getAscii()), pOut);
    }
    if (pPdfObj.getKeywords() != null) {
      String keywordsStr = "/Keywords (" + getWriteHelper()
        .escapePdf(pPdfObj.getKeywords()) + ")\n";
      bytesWritten += getWriteHelper()
        .writeBytes(keywordsStr.getBytes(getWriteHelper().getAscii()), pOut);
    }
    if (pPdfObj.getCreationDate() != null) {
      String creationDateStr = "/CreationDate (" + getWriteHelper()
        .dateToString(pPdfObj.getCreationDate()) + ")\n";
      bytesWritten += getWriteHelper().writeBytes(
        creationDateStr.getBytes(getWriteHelper().getAscii()), pOut);
    }
    if (pPdfObj.getModDate() != null) {
      String modDateStr = "/ModDate (" + getWriteHelper()
        .dateToString(pPdfObj.getModDate()) + ")";
      bytesWritten += getWriteHelper()
        .writeBytes(modDateStr.getBytes(getWriteHelper().getAscii()), pOut);
    }
    bytesWritten += getWriteHelper()
      .writeBytes(getWriteHelper().getEndObjLf(), pOut);
    return bytesWritten;
  }
}

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

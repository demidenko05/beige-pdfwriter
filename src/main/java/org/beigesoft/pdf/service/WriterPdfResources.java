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

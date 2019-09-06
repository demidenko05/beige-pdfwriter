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
   * @throws Exception an Exception
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

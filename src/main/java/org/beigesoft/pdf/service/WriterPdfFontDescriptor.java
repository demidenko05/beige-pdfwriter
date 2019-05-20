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

import org.beigesoft.pdf.model.PdfFontDescriptor;

/**
 * <p>PDF font descriptor writer.</p>
 *
 * @author Yury Demidenko
 */
public class WriterPdfFontDescriptor
  extends AWriterPdfObject<PdfFontDescriptor> {

  /**
   * <p>Write object to stream and return bytes count.</p>
   * @param pPdfObj PdfFontDescriptor
   * @param pOut stream
   * @return bytes count
   * @throws an Exception
   **/
  @Override
  public final int write(final PdfFontDescriptor pPdfObj,
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
    String startStr = "/Type /FontDescriptor\n";
    bytesWritten += getWriteHelper()
      .writeBytes(startStr.getBytes(getWriteHelper().getAscii()), pOut);
    String fontNameStr = "/FontName /" + pPdfObj.getFontName() + "\n";
    bytesWritten += getWriteHelper()
      .writeBytes(fontNameStr.getBytes(getWriteHelper().getAscii()), pOut);
    String flagsStr = "/Flags " + pPdfObj.getFlags() + "\n";
    bytesWritten += getWriteHelper()
      .writeBytes(flagsStr.getBytes(getWriteHelper().getAscii()), pOut);
    String italicAngleStr = "/ItalicAngle " + pPdfObj.getItalicAngle() + "\n";
    bytesWritten += getWriteHelper()
      .writeBytes(italicAngleStr.getBytes(getWriteHelper().getAscii()), pOut);
    if (pPdfObj.getFontWeight() != 0f) { //optional
      String fontWeightStr = "/FontWeight " + pPdfObj.getFontWeight() + "\n";
      bytesWritten += getWriteHelper()
        .writeBytes(fontWeightStr.getBytes(getWriteHelper().getAscii()), pOut);
    }
    String ascentStr = "/Ascent " + pPdfObj.getAscent() + "\n";
    bytesWritten += getWriteHelper()
      .writeBytes(ascentStr.getBytes(getWriteHelper().getAscii()), pOut);
    String descentStr = "/Descent " + pPdfObj.getDescent() + "\n";
    bytesWritten += getWriteHelper()
      .writeBytes(descentStr.getBytes(getWriteHelper().getAscii()), pOut);
    String capHeightStr = "/CapHeight " + pPdfObj.getCapHeight() + "\n";
    bytesWritten += getWriteHelper()
      .writeBytes(capHeightStr.getBytes(getWriteHelper().getAscii()), pOut);
    if (pPdfObj.getXHeight() != 0f) { //optional
      String xHeightStr = "/XHeight " + pPdfObj.getXHeight() + "\n";
      bytesWritten += getWriteHelper()
        .writeBytes(xHeightStr.getBytes(getWriteHelper().getAscii()), pOut);
    }
    String stemVStr = "/StemV " + pPdfObj.getStemV() + "\n";
    bytesWritten += getWriteHelper()
      .writeBytes(stemVStr.getBytes(getWriteHelper().getAscii()), pOut);
    String fontBStr = "/FontBBox [" + pPdfObj.getMinX() + " " + pPdfObj
      .getMinY() + " " + pPdfObj.getMaxX() + " " + pPdfObj.getMaxY() + "]\n";
    bytesWritten += getWriteHelper().writeBytes(fontBStr
      .getBytes(getWriteHelper().getAscii()), pOut);
    String fontFile2Str = "/FontFile2 " + pPdfObj.getFontFile2().getNumber()
      + " " + pPdfObj.getFontFile2().getGenNumber() + " R";
    bytesWritten += getWriteHelper()
      .writeBytes(fontFile2Str.getBytes(getWriteHelper().getAscii()), pOut);
    bytesWritten += getWriteHelper()
      .writeBytes(getWriteHelper().getEndObjLf(), pOut);
    return bytesWritten;
  }
}

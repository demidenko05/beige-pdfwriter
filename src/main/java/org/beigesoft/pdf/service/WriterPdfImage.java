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

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;

import org.beigesoft.zlib.IZLibStreamer;
import org.beigesoft.pdf.model.PdfImage;

/**
 * <p>PDF font file stream writer.</p>
 *
 * @author Yury Demidenko
 */
public class WriterPdfImage extends AWriterPdfObject<PdfImage> {

  /**
   * <p>ZLib Streamer.</p>
   **/
  private IZLibStreamer zLibStreamer;

  /**
   * <p>Write object to stream and return bytes count.</p>
   * @param pPdfObj PdfImage
   * @param pOut stream
   * @return bytes count
   * @throws Exception an Exception
   **/
  @Override
  public final int write(final PdfImage pPdfObj,
    final OutputStream pOut) throws Exception {
    int wd = pPdfObj.getDocImage().getImage().getWidth();
    int ht = pPdfObj.getDocImage().getImage().getHeight();
    int sz = 3 * wd * ht;
    byte[] samples = new byte[sz];
    int smpIdx = 0;
    for (int y = 0; y < ht; y++) {
      for (int x = 0; x < wd; x++) {
        int rgb = pPdfObj.getDocImage().getImage().getRgb(x, y);
        int red = rgb & 0x00FF0000;
        red >>= 16;
        samples[smpIdx] = (byte) red;
        smpIdx++;
        int green = rgb & 0x0000FF00;
        green >>= 8;
        samples[smpIdx] = (byte) green;
        smpIdx++;
        samples[smpIdx] = (byte) (rgb & 0x000000FF);
        smpIdx++;
      }
    }
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    ByteArrayInputStream is = new ByteArrayInputStream(samples);
    this.zLibStreamer.compress(null, is, os);
    byte[] cont = os.toByteArray();
    //write to stream:
    int bytesWritten = 0;
    byte[] btsNumSp = (pPdfObj.getNumber().toString() + " ")
      .getBytes(getWriteHelper().getAscii());
    bytesWritten += getWriteHelper().writeBytes(btsNumSp, pOut);
    byte[] btsGenNumSp = (pPdfObj.getGenNumber().toString() + " ")
      .getBytes(getWriteHelper().getAscii());
    bytesWritten += getWriteHelper().writeBytes(btsGenNumSp, pOut);
    bytesWritten += getWriteHelper()
      .writeBytes(getWriteHelper().getStartObj(), pOut);
    bytesWritten += getWriteHelper().writeBytes("/Type /XObject\n"
      .getBytes(getWriteHelper().getAscii()), pOut);
    bytesWritten += getWriteHelper().writeBytes("/Subtype /Image\n"
      .getBytes(getWriteHelper().getAscii()), pOut);
    bytesWritten += getWriteHelper().writeBytes("/BitsPerComponent 8\n"
      .getBytes(getWriteHelper().getAscii()), pOut);
    bytesWritten += getWriteHelper().writeBytes(("/Width " + wd + "\n")
        .getBytes(getWriteHelper().getAscii()), pOut);
    bytesWritten += getWriteHelper().writeBytes(("/Height " + ht + "\n")
        .getBytes(getWriteHelper().getAscii()), pOut);
    bytesWritten += getWriteHelper().writeBytes("/ColorSpace /DeviceRGB\n"
      .getBytes(getWriteHelper().getAscii()), pOut);
    bytesWritten += getWriteHelper().writeBytes("/Filter /FlateDecode\n"
      .getBytes(getWriteHelper().getAscii()), pOut);
    String startStr = "/Length " + cont.length + "\n";
    bytesWritten += getWriteHelper()
      .writeBytes(startStr.getBytes(getWriteHelper().getAscii()), pOut);
    bytesWritten += getWriteHelper()
      .writeBytes(getWriteHelper().getEndDictLf(), pOut);
    bytesWritten += getWriteHelper()
      .writeBytes(getWriteHelper().getStartStreamLf(), pOut);
    bytesWritten += getWriteHelper()
      .writeBytes(cont, pOut);
    bytesWritten += getWriteHelper()
      .writeBytes(getWriteHelper().getEndStreamObjLf(), pOut);
    return bytesWritten;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for zLibStreamer.</p>
   * @return IZLibStreamer
   **/
  public final IZLibStreamer getZLibStreamer() {
    return this.zLibStreamer;
  }

  /**
   * <p>Setter for zLibStreamer.</p>
   * @param pZLibStreamer reference
   **/
  public final void setZLibStreamer(final IZLibStreamer pZLibStreamer) {
    this.zLibStreamer = pZLibStreamer;
  }

}

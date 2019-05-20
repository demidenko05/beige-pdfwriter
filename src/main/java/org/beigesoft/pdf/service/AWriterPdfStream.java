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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.beigesoft.zlib.IZLibStreamer;
import org.beigesoft.pdf.model.APdfStream;

/**
 * <p>PDF base stream writer.</p>
 *
 * @param <T> type of APdfStream
 * @param <O> type of APdfStream
 * @author Yury Demidenko
 */
public abstract class
  AWriterPdfStream<T extends APdfStream<O>, O extends APdfStream>
    implements IWriterPdfObject<T> {

  /**
   * <p>Write helper.</p>
   **/
  private PdfWriteHelper writeHelper;

  /**
   * <p>ZLib Streamer.</p>
   **/
  private IZLibStreamer zLibStreamer;

  /**
   * <p>Write content to buffer.</p>
   * @param pPdfObj object
   * @param pOut stream
   * @throws Exception an Exception
   **/
  public abstract void writeToBuffer(final T pPdfObj,
    final OutputStream pOut) throws Exception;

  /**
   * <p>Write object to stream and return bytes count.</p>
   * @param pPdfObj object
   * @param pOut stream
   * @return bytes count
   * @throws an Exception
   **/
  @Override
  public final int write(final T pPdfObj,
    final OutputStream pOut) throws Exception {
    pPdfObj.getBuffer().reset();
    writeToBuffer(pPdfObj, pOut);
    byte[] cont;
    if (!pPdfObj.getIsCompressed()) {
      cont = pPdfObj.getBuffer().toByteArray();
    } else {
      ByteArrayInputStream is =
        new ByteArrayInputStream(pPdfObj.getBuffer().toByteArray());
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      this.zLibStreamer.compress(null, is, os);
      cont = os.toByteArray();
    }
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
    if (pPdfObj.getIsCompressed()) {
      bytesWritten += getWriteHelper().writeBytes("/Filter /FlateDecode\n"
        .getBytes(getWriteHelper().getAscii()), pOut);
    }
    String startStr = "/Length " + cont.length;
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
   * <p>Getter for writeHelper.</p>
   * @return PdfWriteHelper
   **/
  public final PdfWriteHelper getWriteHelper() {
    return this.writeHelper;
  }

  /**
   * <p>Setter for writeHelper.</p>
   * @param pWriteHelper reference
   **/
  public final void setWriteHelper(final PdfWriteHelper pWriteHelper) {
    this.writeHelper = pWriteHelper;
  }

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

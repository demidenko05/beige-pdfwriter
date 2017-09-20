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

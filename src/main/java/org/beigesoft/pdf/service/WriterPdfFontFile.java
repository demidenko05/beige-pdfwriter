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

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;

import org.beigesoft.zlib.IZLibStreamer;
import org.beigesoft.ttf.service.ITtfCompactFontMaker;
import org.beigesoft.pdf.model.PdfFontFile;

/**
 * <p>PDF font file stream writer.</p>
 *
 * @author Yury Demidenko
 */
public class WriterPdfFontFile extends AWriterPdfObject<PdfFontFile> {

  /**
   * <p>ZLib Streamer.</p>
   **/
  private IZLibStreamer zLibStreamer;

  /**
   * <p>It makes compact TTF font for embedding into PDF.</p>
   **/
  private ITtfCompactFontMaker compactFontMaker;

  /**
   * <p>Write object to stream and return bytes count.</p>
   * @param pPdfObj PdfFontFile
   * @param pOut stream
   * @return bytes count
   * @throws an Exception
   **/
  @Override
  public final int write(final PdfFontFile pPdfObj,
    final OutputStream pOut) throws Exception {
    byte[] fntCompact = this.compactFontMaker
      .make(null, pPdfObj.getFontName(), pPdfObj.getToUnicode().getUsedCids());
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    ByteArrayInputStream is = new ByteArrayInputStream(fntCompact);
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
    bytesWritten += getWriteHelper().writeBytes("/Filter /FlateDecode\n"
      .getBytes(getWriteHelper().getAscii()), pOut);
    String startStr = "/Length " + cont.length + "\n"
      + "/Length1 " + fntCompact.length;
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

  /**
   * <p>Getter for compactFontMaker.</p>
   * @return ITtfCompactFontMaker
   **/
  public final ITtfCompactFontMaker getCompactFontMaker() {
    return this.compactFontMaker;
  }

  /**
   * <p>Setter for compactFontMaker.</p>
   * @param pCompactFontMaker reference
   **/
  public final void setCompactFontMaker(
    final ITtfCompactFontMaker pCompactFontMaker) {
    this.compactFontMaker = pCompactFontMaker;
  }
}

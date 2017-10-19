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

import org.beigesoft.doc.service.ToHexCoder;
import org.beigesoft.pdf.model.PdfToUnicode;

/**
 * <p>PDF stream ToUnicode writer.</p>
 *
 * @author Yury Demidenko
 */
public class WriterPdfToUnicode
  extends AWriterPdfStream<PdfToUnicode, PdfToUnicode> {

  /**
   * <p>App-scoped to Hex coder.</p>
   **/
  private ToHexCoder toHexCoder;

  /**
   * <p>Write object to buffer.</p>
   * @param pPdfObj PdfToUnicode
   * @param pOut stream
   * @return bytes count
   * @throws Exception an Exception
   **/
  @Override
  public final void writeToBuffer(final PdfToUnicode pPdfObj,
    final OutputStream pOut) throws Exception {
    makeBfranges(pPdfObj);
    //write to buffer:
    pPdfObj.getBuffer().write("/CIDInit /ProcSet findresource begin\n"
      .getBytes(getWriteHelper().getAscii()));
    pPdfObj.getBuffer().write("12 dict begin\n".getBytes(getWriteHelper()
      .getAscii()));
    pPdfObj.getBuffer().write("begincmap\n".getBytes(getWriteHelper()
      .getAscii()));
    pPdfObj.getBuffer().write("/CIDSystemInfo\n".getBytes(getWriteHelper()
      .getAscii()));
    pPdfObj.getBuffer().write("<< /Registry (Adobe)\n"
      .getBytes(getWriteHelper().getAscii()));
    pPdfObj.getBuffer().write("/Ordering (UCS)\n"
      .getBytes(getWriteHelper().getAscii()));
    pPdfObj.getBuffer().write("/Supplement 0\n".getBytes(getWriteHelper()
      .getAscii()));
    pPdfObj.getBuffer().write(">> def\n".getBytes(getWriteHelper()
      .getAscii()));
    pPdfObj.getBuffer().write("/CMapName /Adobe-Identity-UCS def\n"
      .getBytes(getWriteHelper().getAscii()));
    pPdfObj.getBuffer().write("/CMapType 2 def\n"
      .getBytes(getWriteHelper().getAscii()));
    pPdfObj.getBuffer().write("1 begincodespacerange\n"
      .getBytes(getWriteHelper().getAscii()));
    pPdfObj.getBuffer().write("<0000> <FFFF>\n".getBytes(getWriteHelper()
      .getAscii()));
    pPdfObj.getBuffer().write("endcodespacerange\n"
      .getBytes(getWriteHelper().getAscii()));
    String stFr = String.valueOf(pPdfObj.getBfranges().size())
      + " beginbfrange\n";
    pPdfObj.getBuffer().write(stFr.getBytes(getWriteHelper().getAscii()));
    for (String bfr : pPdfObj.getBfranges()) {
      pPdfObj.getBuffer().write((bfr + "\n").getBytes(getWriteHelper()
        .getAscii()));
    }
    pPdfObj.getBuffer().write("endbfrange\n".getBytes(getWriteHelper()
      .getAscii()));
    pPdfObj.getBuffer().write("endcmap\n".getBytes(getWriteHelper()
      .getAscii()));
    pPdfObj.getBuffer().write("CMapName currentdict /CMap defineresource pop\n"
      .getBytes(getWriteHelper().getAscii()));
    pPdfObj.getBuffer().write("end\n".getBytes(getWriteHelper().getAscii()));
    pPdfObj.getBuffer().write("end\n".getBytes(getWriteHelper().getAscii()));
  }

  /**
   * <p>Maker bfranges.</p>
   * @param pPdfObj PdfToUnicode
   **/
  public final void makeBfranges(final PdfToUnicode pPdfObj) { //TODO JUnit test
    pPdfObj.getBfranges().clear();
    Character cidStart = null;
    Character cidEnd = null;
    Character uniStart = null;
    for (Character cid : pPdfObj.getUsedCids()) {
      if (pPdfObj.getUsedCidToUni().get(cid) != null) {
        if (cidStart == null) {
          cidStart = cid;
          uniStart = pPdfObj.getUsedCidToUni().get(cid);
          cidEnd = cid;
        } else if (cid - cidEnd == 1
          && pPdfObj.getUsedCidToUni().get(cid)
            - pPdfObj.getUsedCidToUni().get(cidEnd) == 1) {
          // continue second and more char:
          cidEnd = cid;
        } else { //finish entry:
          pPdfObj.getBfranges().add("<" + this.toHexCoder
            .encodeCodePoint(cidStart) + ">" + " <" + this.toHexCoder
              .encodeCodePoint(cidEnd) + ">" + " <"
                + this.toHexCoder.encodeCodePoint(uniStart) + ">");
          cidStart = cid;
          uniStart = pPdfObj.getUsedCidToUni().get(cid);
          cidEnd = cid;
        }
      }
    }
    pPdfObj.getBfranges().add("<" + this.toHexCoder.encodeCodePoint(cidStart)
      + ">" + " <" + this.toHexCoder.encodeCodePoint(cidEnd) + ">"
        + " <" + this.toHexCoder.encodeCodePoint(uniStart) + ">");
  }

  //Simple getters and setters:
  /**
   * <p>Getter for toHexCoder.</p>
   * @return ToHexCoder
   **/
  public final ToHexCoder getToHexCoder() {
    return this.toHexCoder;
  }

  /**
   * <p>Setter for toHexCoder.</p>
   * @param pToHexCoder reference
   **/
  public final void setToHexCoder(final ToHexCoder pToHexCoder) {
    this.toHexCoder = pToHexCoder;
  }
}

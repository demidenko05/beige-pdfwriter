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

package org.beigesoft.pdf.model;

import java.util.List;
import java.util.ArrayList;

import org.beigesoft.ttf.model.TtfHmtx;

/**
 * <p>PDF CIDFontType2 fonts model.</p>
 *
 * @author Yury Demidenko
 */
public class PdfCidFontType2 extends APdfObject<PdfCidFontType2> {

  /**
   * <p>Name of font. Must match to font file name without
   * extension from resources.</p>
   **/
  private String baseFont;

  /**
   * <p>CIDToGIDMap.</p>
   **/
  private String cidToGidMap = "Identity";

  /**
   * <p>Descendant fonts.</p>
   **/
  private PdfFontDescriptor fontDescriptor;

  /**
   * <p>To unicode map.</p>
   **/
  private PdfToUnicode toUnicode;

  /**
   * <p>Widths list prepared.</p>
   **/
  private final List<String> widthsList = new ArrayList<String>();

  /**
   * <p>width source.</p>
   **/
  private TtfHmtx hmtx;

  /**
   * <p>unitsPerEm.</p>
   **/
  private int unitsPerEm;

  //Simple getters and setters:
  /**
   * <p>Getter for baseFont.</p>
   * @return String
   **/
  public final String getBaseFont() {
    return this.baseFont;
  }

  /**
   * <p>Setter for baseFont.</p>
   * @param pBaseFont reference
   **/
  public final void setBaseFont(final String pBaseFont) {
    this.baseFont = pBaseFont;
  }

  /**
   * <p>Getter for cidToGidMap.</p>
   * @return String
   **/
  public final String getCidToGidMap() {
    return this.cidToGidMap;
  }

  /**
   * <p>Setter for cidToGidMap.</p>
   * @param pCidToGidMap reference
   **/
  public final void setCidToGidMap(final String pCidToGidMap) {
    this.cidToGidMap = pCidToGidMap;
  }

  /**
   * <p>Getter for fontDescriptor.</p>
   * @return APdfObject
   **/
  public final PdfFontDescriptor getFontDescriptor() {
    return this.fontDescriptor;
  }

  /**
   * <p>Setter for fontDescriptor.</p>
   * @param pFontDescriptor reference
   **/
  public final void setFontDescriptor(final PdfFontDescriptor pFontDescriptor) {
    this.fontDescriptor = pFontDescriptor;
  }

  /**
   * <p>Getter for widthsList.</p>
   * @return List<String>
   **/
  public final List<String> getWidthsList() {
    return this.widthsList;
  }

  /**
   * <p>Getter for toUnicode.</p>
   * @return PdfCidFontType2
   **/
  public final PdfToUnicode getToUnicode() {
    return this.toUnicode;
  }

  /**
   * <p>Setter for toUnicode.</p>
   * @param pToUnicode reference
   **/
  public final void setToUnicode(final PdfToUnicode pToUnicode) {
    this.toUnicode = pToUnicode;
  }
  /**
   * <p>Getter for hmtx.</p>
   * @return TtfHmtx
   **/
  public final TtfHmtx getHmtx() {
    return this.hmtx;
  }

  /**
   * <p>Setter for hmtx.</p>
   * @param pHmtx reference
   **/
  public final void setHmtx(final TtfHmtx pHmtx) {
    this.hmtx = pHmtx;
  }

  /**
   * <p>Getter for unitsPerEm.</p>
   * @return int
   **/
  public final int getUnitsPerEm() {
    return this.unitsPerEm;
  }

  /**
   * <p>Setter for unitsPerEm.</p>
   * @param pUnitsPerEm reference
   **/
  public final void setUnitsPerEm(final int pUnitsPerEm) {
    this.unitsPerEm = pUnitsPerEm;
  }
}

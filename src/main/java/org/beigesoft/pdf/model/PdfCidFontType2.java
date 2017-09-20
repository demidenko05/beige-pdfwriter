package org.beigesoft.pdf.model;

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

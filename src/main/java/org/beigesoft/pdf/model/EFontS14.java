package org.beigesoft.pdf.model;

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

/**
 * <p>PDF standard fonts 14 type 1 model.</p>
 *
 * @author Yury Demidenko
 */
public enum EFontS14 {

  /**
   * <p>Times-Roman.</p>
   **/
  TIMES_ROMAN("Times-Roman"),

  /**
   * <p>Helvetica.</p>
   **/
  HELVETICA("Helvetica"),

  /**
   * <p>Courier.</p>
   **/
  COURIER("Courier"),

  /**
   * <p>Symbol.</p>
   **/
  SYMBOL("Symbol"),

  /**
   * <p>Times-Bold.</p>
   **/
  TIMES_BOLD("Times-Bold"),

  /**
   * <p>Helvetica-Bold.</p>
   **/
  HELVETICA_BOLD("Helvetica-Bold"),

  /**
   * <p>Courier-Bold.</p>
   **/
  COURIER_BOLD("Courier-Bold"),

  /**
   * <p>ZapfDingbats.</p>
   **/
  ZAPDINGBATS("ZapfDingbats"),

  /**
   * <p>Times-Italic.</p>
   **/
  TIMES_ITALIC("Times-Italic"),

  /**
   * <p>Helvetica-Oblique.</p>
   **/
  HELVETICA_OBLIQUE("Helvetica-Oblique"),

  /**
   * <p>Courier-Oblique.</p>
   **/
  COURIER_OBLIQUE("Courier-Oblique"),

  /**
   * <p>Times-BoldItalic.</p>
   **/
  TIMES_BOLDITALIC("Times-BoldItalic"),

  /**
   * <p>Helvetica-BoldOblique.</p>
   **/
  HELVETICA_BOLDOBLIQUE("Helvetica-BoldOblique"),

  /**
   * <p>Courier-BoldOblique.</p>
   **/
  COURIER_BOLDOBLIQUE("Courier-BoldOblique");

  /**
   * <p>Enum string value.</p>
   **/
  private String value;

  /**
   * <p>Default constructor.</p>
   **/
  EFontS14() {
  }

  /**
   * <p>Constructor with value.</p>
   * @param pValue value
   **/
  EFontS14(final String pValue) {
    this.value = pValue;
  }

  @Override
  public final String toString() {
    return this.value;
  }
}

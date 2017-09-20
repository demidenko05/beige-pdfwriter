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

/**
 * <p>PDF font type model.</p>
 *
 * @author Yury Demidenko
 */
public enum EFontType {

  /**
   * <p>Composite font.</p>
   **/
  TYPE0("Type0"),

  /**
   * <p>Type 1 font.</p>
   **/
  TYPE1("Type1"),

  /**
   * <p>Multiply-master font.</p>
   **/
  MMTYPE1("MMType1"),

  /**
   * <p>Font defined with PDF graphic operators.</p>
   **/
  TYPE3("Type3"),

  /**
   * <p>TrueType Font.</p>
   **/
  TRUETYPE("TrueType"),

  /**
   * <p>CID font type 0.</p>
   **/
  CIDFONTTYPE0("CIDFontType0"),

  /**
   * <p>CID font type 2.</p>
   **/
  CIDFONTTYPE2("CIDFontType2");

  /**
   * <p>Enum string value.</p>
   **/
  private String value;

  /**
   * <p>Default constructor.</p>
   **/
  EFontType() {
  }

  /**
   * <p>Constructor with value.</p>
   * @param pValue value
   **/
  EFontType(final String pValue) {
    this.value = pValue;
  }

  @Override
  public final String toString() {
    return this.value;
  }
}

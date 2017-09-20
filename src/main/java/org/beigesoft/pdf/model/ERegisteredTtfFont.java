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
 * <p>Registered TTF fonts.</p>
 *
 * @author Yury Demidenko
 */
public enum ERegisteredTtfFont {

  /**
   * <p>DejaVuSans.</p>
   **/
  DEJAVUSANS("DejaVuSans"),

  /**
   * <p>DejaVuSans-Bold.</p>
   **/
  DEJAVUSANS_BOLD("DejaVuSans-Bold"),

  /**
   * <p>DejaVuSerif.</p>
   **/
  DEJAVUSERIF("DejaVuSerif"),

  /**
   * <p>DejaVuSerif-Bold.</p>
   **/
  DEJAVUSERIF_BOLD("DejaVuSerif-Bold"),

  /**
   * <p>DejaVuSerif-BoldItalic.</p>
   **/
  DEJAVUSERIF_BOLDITALIC("DejaVuSerif-BoldItalic"),

  /**
   * <p>DejaVuSerif-Italic.</p>
   **/
  DEJAVUSERIF_ITALIC("DejaVuSerif-Italic"),

  /**
   * <p>Japanese VL-Gothic-Regular.</p>
   **/
  VL_GOTHIC_REGULAR("VL-Gothic-Regular"),

  /**
   * <p>Japanese VL-PGothic-Regular.</p>
   **/
  VL_PGOTHIC_REGULAR("VL-PGothic-Regular");

  /**
   * <p>Enum string value.</p>
   **/
  private String value;

  /**
   * <p>Default constructor.</p>
   **/
  ERegisteredTtfFont() {
  }

  /**
   * <p>Constructor with value.</p>
   * @param pValue value
   **/
  ERegisteredTtfFont(final String pValue) {
    this.value = pValue;
  }

  @Override
  public final String toString() {
    return this.value;
  }
}

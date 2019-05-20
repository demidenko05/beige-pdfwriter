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

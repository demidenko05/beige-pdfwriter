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

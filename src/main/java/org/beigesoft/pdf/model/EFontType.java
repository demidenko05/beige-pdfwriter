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

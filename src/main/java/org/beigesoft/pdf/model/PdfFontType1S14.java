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

import org.beigesoft.doc.model.IFont;

/**
 * <p>PDF standard (14) Type1 fonts model.</p>
 *
 * @author Yury Demidenko
 */
public class PdfFontType1S14 extends APdfObject<PdfFontType1S14>
  implements IFont {

  /**
   * <p>Name of font.</p>
   **/
  private EFontS14 baseFont = EFontS14.HELVETICA;

  /**
   * <p>Get if unicoded.</p>
   * @return if unicoded
   **/
  @Override
  public final boolean getIsUnicoded() {
    return false;
  }

  /**
   * <p>Usually it's simple getter that return model name.</p>
   * @return String model name
   **/
  @Override
  public final String getItsName() {
    return this.baseFont.toString();
  }

  //Simple getters and setters:
  /**
   * <p>Getter for baseFont.</p>
   * @return String
   **/
  public final EFontS14 getBaseFont() {
    return this.baseFont;
  }

  /**
   * <p>Setter for baseFont.</p>
   * @param pBaseFont reference
   **/
  public final void setBaseFont(final EFontS14 pBaseFont) {
    this.baseFont = pBaseFont;
  }
}

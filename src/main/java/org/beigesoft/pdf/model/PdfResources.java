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

/**
 * <p>PDF resources dictionary model.</p>
 *
 * @author Yury Demidenko
 */
public class PdfResources extends APdfObject<PdfResources> {

  /**
   * <p>Fonts dictionary.</p>
   **/
  private List<IPdfObject> fonts;

  /**
   * <p>Images dictionary.</p>
   **/
  private List<IPdfObject> images;

  //Simple getters and setters:
  /**
   * <p>Getter for fonts.</p>
   * @return List<IPdfObject>
   **/
  public final List<IPdfObject> getFonts() {
    return this.fonts;
  }

  /**
   * <p>Setter for fonts.</p>
   * @param pFonts reference
   **/
  public final void setFonts(final List<IPdfObject> pFonts) {
    this.fonts = pFonts;
  }

  /**
   * <p>Getter for images.</p>
   * @return List<IPdfObject>
   **/
  public final List<IPdfObject> getImages() {
    return this.images;
  }

  /**
   * <p>Setter for images.</p>
   * @param pImages reference
   **/
  public final void setImages(final List<IPdfObject> pImages) {
    this.images = pImages;
  }
}

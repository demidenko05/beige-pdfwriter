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
 * <p>PDF rectangle model.</p>
 *
 * @author Yury Demidenko
 */
public class PdfRectangle {

  /**
   * <p>Lower left X.</p>
   **/
  private float llX;

  /**
   * <p>Lower left Y.</p>
   **/
  private float llY;

  /**
   * <p>Upper right X.</p>
   **/
  private float urX;

  /**
   * <p>Upper right Y.</p>
   **/
  private float urY;

  /**
   * <p>Name e.g. MediaBox.</p>
   **/
  private String name = "MediaBox";

  //Simple getters and setters:

  /**
   * <p>Getter for llX.</p>
   * @return float
   **/
  public final float getLlX() {
    return this.llX;
  }

  /**
   * <p>Setter for llX.</p>
   * @param pLlX reference
   **/
  public final void setLlX(final float pLlX) {
    this.llX = pLlX;
  }

  /**
   * <p>Getter for llY.</p>
   * @return float
   **/
  public final float getLlY() {
    return this.llY;
  }

  /**
   * <p>Setter for llY.</p>
   * @param pLlY reference
   **/
  public final void setLlY(final float pLlY) {
    this.llY = pLlY;
  }

  /**
   * <p>Getter for urX.</p>
   * @return float
   **/
  public final float getUrX() {
    return this.urX;
  }

  /**
   * <p>Setter for urX.</p>
   * @param pUrX reference
   **/
  public final void setUrX(final float pUrX) {
    this.urX = pUrX;
  }

  /**
   * <p>Getter for urY.</p>
   * @return float
   **/
  public final float getUrY() {
    return this.urY;
  }

  /**
   * <p>Setter for urY.</p>
   * @param pUrY reference
   **/
  public final void setUrY(final float pUrY) {
    this.urY = pUrY;
  }

  /**
   * <p>Getter for name.</p>
   * @return String
   **/
  public final String getName() {
    return this.name;
  }

  /**
   * <p>Setter for name.</p>
   * @param pName reference
   **/
  public final void setName(final String pName) {
    this.name = pName;
  }
}

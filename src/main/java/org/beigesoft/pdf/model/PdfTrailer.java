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
 * <p>PDF trailer model.</p>
 *
 * @author Yury Demidenko
 */
public class PdfTrailer {

  /**
   * <p>Root reference.</p>
   **/
  private PdfCatalog root;

  /**
   * <p>Info reference (opt).</p>
   **/
  private PdfInfo info;

  //Simple getters and setters:
  /**
   * <p>Getter for root.</p>
   * @return APdfObject
   **/
  public final PdfCatalog getRoot() {
    return this.root;
  }

  /**
   * <p>Setter for root.</p>
   * @param pRoot reference
   **/
  public final void setRoot(final PdfCatalog pRoot) {
    this.root = pRoot;
  }

  /**
   * <p>Getter for info.</p>
   * @return APdfObject
   **/
  public final PdfInfo getInfo() {
    return this.info;
  }

  /**
   * <p>Setter for info.</p>
   * @param pInfo reference
   **/
  public final void setInfo(final PdfInfo pInfo) {
    this.info = pInfo;
  }
}

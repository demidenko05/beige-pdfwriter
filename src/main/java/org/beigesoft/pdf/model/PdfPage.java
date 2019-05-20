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
 * <p>PDF page dictionary model.</p>
 *
 * @author Yury Demidenko
 */
public class PdfPage extends APdfObject<PdfPage> {

  /**
   * <p>Pages (parent).</p>
   **/
  private PdfPages parent;

  /**
   * <p>Resource dictionary.</p>
   **/
  private PdfResources resources;

  /**
   * <p>Content.</p>
   **/
  private PdfContent contents;

  /**
   * <p>Media box.</p>
   **/
  private PdfRectangle mediabox = new PdfRectangle();

  //Simple getters and setters:
  /**
   * <p>Getter for parent.</p>
   * @return PdfPages
   **/
  public final PdfPages getParent() {
    return this.parent;
  }

  /**
   * <p>Setter for parent.</p>
   * @param pParent reference
   **/
  public final void setParent(final PdfPages pParent) {
    this.parent = pParent;
  }

  /**
   * <p>Getter for resources.</p>
   * @return PdfResources
   **/
  public final PdfResources getResources() {
    return this.resources;
  }

  /**
   * <p>Setter for resources.</p>
   * @param pResources reference
   **/
  public final void setResources(final PdfResources pResources) {
    this.resources = pResources;
  }

  /**
   * <p>Getter for contents.</p>
   * @return PdfContent
   **/
  public final PdfContent getContents() {
    return this.contents;
  }

  /**
   * <p>Setter for contents.</p>
   * @param pContents reference
   **/
  public final void setContents(final PdfContent pContents) {
    this.contents = pContents;
  }

  /**
   * <p>Getter for mediabox.</p>
   * @return PdfRectangle
   **/
  public final PdfRectangle getMediabox() {
    return this.mediabox;
  }

  /**
   * <p>Setter for mediabox.</p>
   * @param pMediabox reference
   **/
  public final void setMediabox(final PdfRectangle pMediabox) {
    this.mediabox = pMediabox;
  }
}

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

package org.beigesoft.pdf.service;

import org.beigesoft.doc.model.DocPage;
import org.beigesoft.doc.model.DocImage;
import org.beigesoft.pdf.model.PdfDocument;
import org.beigesoft.pdf.model.EFontS14;

/**
 * <p>Abstraction of service that makes PDF document.</p>
 *
 * @param <WI> writing instrument type
 * @author Yury Demidenko
 */
public interface IPdfMaker<WI> {

  /**
   * <p>Prepare before write.</p>
   * @param pDoc document
   * @throws Exception an Exception
   **/
  void prepareBeforeWrite(PdfDocument<WI> pDoc) throws Exception;

  /**
   * <p>Setter for if compressed for all streams.</p>
   * @param pDoc document
   * @param pIsCompressed is compressed
   * @throws Exception an Exception
   **/
  void setIsCompressed(PdfDocument<WI> pDoc,
    boolean pIsCompressed) throws Exception;


  /**
   * <p>Add page.</p>
   * @param pDoc document
   * @param pDocPage document page
   * @throws Exception an Exception
   **/
  void addPage(PdfDocument<WI> pDoc,
    DocPage<WI> pDocPage) throws Exception;

  /**
   * <p>Add font type 1 standard 14.</p>
   * @param pDoc document
   * @param pFontS14 to add
   * @throws Exception an Exception
   **/
  void addFontT1S14(PdfDocument<WI> pDoc,
    EFontS14 pFontS14) throws Exception;


  /**
   * <p>Add international friendly TTF font.</p>
   * @param pDoc document
   * @param pFontName to add
   * @throws Exception an Exception
   **/
  void addFontTtf(PdfDocument<WI> pDoc,
    String pFontName) throws Exception;


  /**
   * <p>Add DocImage.</p>
   * @param pDoc document
   * @param pDocImage DocImage
   * @throws Exception an Exception
   **/
  void addImage(PdfDocument<WI> pDoc,
    DocImage pDocImage) throws Exception;
}

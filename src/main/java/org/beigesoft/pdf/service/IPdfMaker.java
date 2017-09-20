package org.beigesoft.pdf.service;

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

import org.beigesoft.doc.model.DocPage;
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
}

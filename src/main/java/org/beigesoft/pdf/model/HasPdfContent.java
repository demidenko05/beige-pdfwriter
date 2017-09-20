package org.beigesoft.pdf.model;

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

/**
 * <p>Writing instrument that has only PdfContent.</p>
 *
 * @author Yury Demidenko
 */
public class HasPdfContent implements IHasPdfContent {

  /**
   * <p>Main writing instrument PdfContent.</p>
   **/
  private PdfContent pdfContent;

  /**
   * <p>Only constructor.</p>
   * @param pPdfContent reference
   **/
  public HasPdfContent(final PdfContent pPdfContent) {
    this.pdfContent = pPdfContent;
  }

  /**
   * <p>Getter for pdfContent.</p>
   * @return PdfContent
   **/
  @Override
  public final PdfContent getPdfContent() {
    return this.pdfContent;
  }
}

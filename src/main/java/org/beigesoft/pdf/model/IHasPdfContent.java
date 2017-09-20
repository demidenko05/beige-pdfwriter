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
 * <p>Abstraction of writing instrument that has PdfContent.</p>
 *
 * @author Yury Demidenko
 */
public interface IHasPdfContent {

  /**
   * <p>Getter for pdfContent.</p>
   * @return PdfContent
   **/
  PdfContent getPdfContent();
}

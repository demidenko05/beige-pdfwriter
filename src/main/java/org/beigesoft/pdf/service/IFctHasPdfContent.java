package org.beigesoft.pdf.service;

/*
 * Copyright (c) 2017 Beigesoft ™
 *
 * Licensed under the GNU General Public License (GPL), Version 2.0
 * (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html
 */

import org.beigesoft.pdf.model.IHasPdfContent;
import org.beigesoft.pdf.model.PdfContent;

/**
 * <p>Abstraction of factory of writing instrument that has PdfContent.</p>
 *
 * @author Yury Demidenko
 */
public interface IFctHasPdfContent {

  /**
   * <p>IHasPdfContent creator.</p>
   * @param pPdfContent reference
   * @return IHasPdfContent
   * @throws Exception an Exception
   **/
  IHasPdfContent createHasPdfContent(
    PdfContent pPdfContent) throws Exception;
}

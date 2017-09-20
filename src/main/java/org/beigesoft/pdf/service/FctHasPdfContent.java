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

import org.beigesoft.pdf.model.IHasPdfContent;
import org.beigesoft.pdf.model.HasPdfContent;
import org.beigesoft.pdf.model.PdfContent;

/**
 * <p>Factory of writing instrument that has only PdfContent.</p>
 *
 * @author Yury Demidenko
 */
public class FctHasPdfContent implements IFctHasPdfContent {

  /**
   * <p>IHasPdfContent creator.</p>
   * @param pPdfContent reference
   * @return IHasPdfContent
   * @throws Exception an Exception
   **/
  @Override
  public final IHasPdfContent createHasPdfContent(
    final PdfContent pPdfContent) throws Exception {
    return new HasPdfContent(pPdfContent);
  }
}

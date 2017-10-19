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

import java.io.OutputStream;

import org.beigesoft.pdf.model.IPdfObject;

/**
 * <p>Writer of PDF object.</p>
 *
 * @param <T> type of PdfObject
 * @author Yury Demidenko
 */
public interface IWriterPdfObject<T extends IPdfObject> {

  /**
   * <p>Write object to stream and return bytes count.</p>
   * @param pPdfObject PdfObject
   * @param pOut stream
   * @return bytes count
   * @throws Exception an Exception
   **/
  int write(T pPdfObject, OutputStream pOut) throws Exception;
}

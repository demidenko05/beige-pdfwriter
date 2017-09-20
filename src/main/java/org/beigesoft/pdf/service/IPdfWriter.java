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

import java.util.Map;
import java.io.OutputStream;

import org.beigesoft.pdf.model.PdfDocument;

/**
 * <p>PDF writer abstraction.
 * You can substitute writer with new corrected one during runtime.</p>
 *
 * @param <WI> writing instrument type
 * @author Yury Demidenko
 */
public interface IPdfWriter<WI> {

  /**
   * <p>Write document to stream.</p>
   * @param pAddParam additional params
   * @param pDoc PDF document
   * @param pOut stream
   * @throws Exception an Exception
   **/
  void write(Map<String, Object> pAddParam,
    PdfDocument<WI> pDoc,
      OutputStream pOut) throws Exception;
}

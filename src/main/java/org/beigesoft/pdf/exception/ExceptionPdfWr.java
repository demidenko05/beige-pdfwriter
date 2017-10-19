package org.beigesoft.pdf.exception;

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

/**
 * <p>Main PDF writer exception.</p>
 *
 * @author Yury Demidenko
 */
public class ExceptionPdfWr extends Exception {

  /**
   * <p>Constructor default.</p>
   **/
  public ExceptionPdfWr() {
  }

  /**
   * <p>Constructor useful.</p>
   * @param pCause parent exception
   **/
  public ExceptionPdfWr(final Throwable pCause) {
    super(pCause);
  }

  /**
   * <p>Constructor useful.</p>
   * @param pMsg message
   **/
  public ExceptionPdfWr(final String pMsg) {
    super(pMsg);
  }
}

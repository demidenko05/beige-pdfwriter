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

import org.beigesoft.pdf.model.IPdfObject;

/**
 * <p>PDF base object writer.</p>
 *
 * @param <T> type of PdfObject
 * @author Yury Demidenko
 */
public abstract class AWriterPdfObject<T extends IPdfObject>
    implements IWriterPdfObject<T> {

  /**
   * <p>Write helper.</p>
   **/
  private PdfWriteHelper writeHelper;

  //Simple getters and setters:
  /**
   * <p>Getter for writeHelper.</p>
   * @return PdfWriteHelper
   **/
  public final PdfWriteHelper getWriteHelper() {
    return this.writeHelper;
  }

  /**
   * <p>Setter for writeHelper.</p>
   * @param pWriteHelper reference
   **/
  public final void setWriteHelper(final PdfWriteHelper pWriteHelper) {
    this.writeHelper = pWriteHelper;
  }
}

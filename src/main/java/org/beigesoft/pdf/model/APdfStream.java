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

import java.io.ByteArrayOutputStream;

/**
 * <p>PDF base stream model.</p>
 *
 * @param <T> type of PdfObject
 * @author Yury Demidenko
 */
public abstract class APdfStream<T extends IPdfObject> extends APdfObject<T> {

  /**
   * <p>Buffer.</p>
   **/
  private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

  /**
   * <p>If compressed.</p>
   **/
  private Boolean isCompressed = true;

  //Simple getters and setters:
  /**
   * <p>Getter for buffer.</p>
   * @return ByteArrayOutputStream
   **/
  public final ByteArrayOutputStream getBuffer() {
    return this.buffer;
  }

  /**
   * <p>Getter for isCompressed.</p>
   * @return Boolean
   **/
  public final Boolean getIsCompressed() {
    return this.isCompressed;
  }

  /**
   * <p>Setter for isCompressed.</p>
   * @param pIsCompressed reference
   **/
  public final void setIsCompressed(final Boolean pIsCompressed) {
    this.isCompressed = pIsCompressed;
  }
}

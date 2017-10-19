package org.beigesoft.pdf.model;

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
import org.beigesoft.pdf.service.IWriterPdfObject;

/**
 * <p>Any PDF object has start (address), number, generation number.
 * Simple indirect object has value.</p>
 *
 * @param <T> type of PdfObject
 * @author Yury Demidenko
 */
public abstract class APdfObject<T extends IPdfObject> implements IPdfObject {

  /**
   * <p>Writer.</p>
   **/
  private IWriterPdfObject<T> writer;

  /**
   * <p>Start.</p>
   **/
  private Integer start;

  /**
   * <p>Number.</p>
   **/
  private Integer number;

  /**
   * <p>Generation number.</p>
   **/
  private Integer genNumber = 0;

  /**
   * <p>Write object to stream and return bytes count.</p>
   * @param pOut stream
   * @return bytes count
   * @throws Exception an Exception
   **/
  @Override
  public final int write(final OutputStream pOut) throws Exception {
    @SuppressWarnings("unchecked")
    T pdfo = (T) this;
    return this.writer.write(pdfo, pOut);
  }

  /**
   * <p>Getter for start.</p>
   * @return Integer
   **/
  @Override
  public final Integer getStart() {
    return this.start;
  }

  /**
   * <p>Setter for start.</p>
   * @param pStart reference
   **/
  @Override
  public final void setStart(final Integer pStart) {
    this.start = pStart;
  }

  /**
   * <p>Getter for number.</p>
   * @return Integer
   **/
  @Override
  public final Integer getNumber() {
    return this.number;
  }

  /**
   * <p>Setter for number.</p>
   * @param pNumber reference
   **/
  @Override
  public final void setNumber(final Integer pNumber) {
    this.number = pNumber;
  }

  /**
   * <p>Getter for genNumber.</p>
   * @return Integer
   **/
  @Override
  public final Integer getGenNumber() {
    return this.genNumber;
  }

  /**
   * <p>Setter for genNumber.</p>
   * @param pGenNumber reference
   **/
  @Override
  public final void setGenNumber(final Integer pGenNumber) {
    this.genNumber = pGenNumber;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for writer.</p>
   * @return IWriterPdfObject<T>
   **/
  public final IWriterPdfObject<T> getWriter() {
    return this.writer;
  }

  /**
   * <p>Setter for writer.</p>
   * @param pWriter reference
   **/
  public final void setWriter(final IWriterPdfObject<T> pWriter) {
    this.writer = pWriter;
  }
}

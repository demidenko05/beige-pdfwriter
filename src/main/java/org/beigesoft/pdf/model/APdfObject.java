/*
BSD 2-Clause License

Copyright (c) 2019, Beigesoft™
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this
  list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.beigesoft.pdf.model;

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

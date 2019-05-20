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

/**
 * <p>Any PDF object has start (address), number, generation number.
 * Simple indirect object has value.</p>
 *
 * @author Yury Demidenko
 */
public interface IPdfObject {

  /**
   * <p>Write object to stream and return bytes count.</p>
   * @param pOut stream
   * @return bytes count
   * @throws Exception an Exception
   **/
  int write(OutputStream pOut) throws Exception;

  /**
   * <p>Getter for start.</p>
   * @return Integer
   **/
  Integer getStart();

  /**
   * <p>Setter for start.</p>
   * @param pStart reference
   **/
  void setStart(final Integer pStart);

  /**
   * <p>Getter for number.</p>
   * @return Integer
   **/
  Integer getNumber();

  /**
   * <p>Setter for number.</p>
   * @param pNumber reference
   **/
  void setNumber(final Integer pNumber);

  /**
   * <p>Getter for genNumber.</p>
   * @return Integer
   **/
  Integer getGenNumber();

  /**
   * <p>Setter for genNumber.</p>
   * @param pGenNumber reference
   **/
  void setGenNumber(final Integer pGenNumber);
}

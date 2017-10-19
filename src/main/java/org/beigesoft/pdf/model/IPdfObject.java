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

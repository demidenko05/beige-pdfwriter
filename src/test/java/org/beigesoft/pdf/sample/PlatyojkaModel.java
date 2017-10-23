package org.beigesoft.pdf.sample;

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

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

/**
 * <p>Платежное поручение.</p>
 *
 * @author Yury Demidenko
 */
public class PlatyojkaModel {

  /**
   * <p>Number of invoice.</p>
   **/
  private String itsNumber;

  /**
   * <p>Number of invoice.</p>
   **/
  private Date itsDate;

  /**
   * <p>Total.</p>
   **/
  private BigDecimal total = BigDecimal.ZERO;

  //Simple getters and setters:
  /**
   * <p>Getter for itsNumber.</p>
   * @return String
   **/
  public final String getItsNumber() {
    return this.itsNumber;
  }

  /**
   * <p>Setter for itsNumber.</p>
   * @param pItsNumber reference
   **/
  public final void setItsNumber(final String pItsNumber) {
    this.itsNumber = pItsNumber;
  }

  /**
   * <p>Getter for itsDate.</p>
   * @return Date
   **/
  public final Date getItsDate() {
    return this.itsDate;
  }

  /**
   * <p>Setter for itsDate.</p>
   * @param pItsDate reference
   **/
  public final void setItsDate(final Date pItsDate) {
    this.itsDate = pItsDate;
  }

  /**
   * <p>Getter for total.</p>
   * @return BigDecimal
   **/
  public final BigDecimal getTotal() {
    return this.total;
  }

  /**
   * <p>Setter for total.</p>
   * @param pTotal reference
   **/
  public final void setTotal(final BigDecimal pTotal) {
    this.total = pTotal;
  }
}

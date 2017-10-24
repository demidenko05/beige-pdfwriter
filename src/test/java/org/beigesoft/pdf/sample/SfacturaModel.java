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

import java.util.List;
import java.math.BigDecimal;

/**
 * <p>СЧЕТ-ФАКТУРА.</p>
 *
 * @author Yury Demidenko
 */
public class SfacturaModel {

  /**
   * <p>Number.</p>
   **/
  private String itsNumber;

  /**
   * <p>Date.</p>
   **/
  private String day;

  /**
   * <p>Date.</p>
   **/
  private String monthYear;

  /**
   * <p>Total.</p>
   **/
  private BigDecimal total = BigDecimal.ZERO;

  /**
   * <p>Number.</p>
   **/
  private String chNumber = "-";

  /**
   * <p>Date.</p>
   **/
  private String chDay = "-";

  /**
   * <p>Date.</p>
   **/
  private String chMonthYear = "-";

  /**
   * <p>Seller.</p>
   **/
  private String seller;

  /**
   * <p>Seller address.</p>
   **/
  private String sellerAddr;

  /**
   * <p>Seller INN/KPP.</p>
   **/
  private String sellerInnKpp;

  private String gruzootpr;

  private String gruzopol;

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
   * <p>Getter for day.</p>
   * @return String
   **/
  public final String getDay() {
    return this.day;
  }

  /**
   * <p>Setter for day.</p>
   * @param pDay reference
   **/
  public final void setDay(final String pDay) {
    this.day = pDay;
  }

  /**
   * <p>Getter for monthYear.</p>
   * @return String
   **/
  public final String getMonthYear() {
    return this.monthYear;
  }

  /**
   * <p>Setter for monthYear.</p>
   * @param pMonthYear reference
   **/
  public final void setMonthYear(final String pMonthYear) {
    this.monthYear = pMonthYear;
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

  /**
   * <p>Getter for chNumber.</p>
   * @return String
   **/
  public final String getChNumber() {
    return this.chNumber;
  }

  /**
   * <p>Setter for chNumber.</p>
   * @param pChNumber reference
   **/
  public final void setChNumber(final String pChNumber) {
    this.chNumber = pChNumber;
  }

  /**
   * <p>Getter for chDay.</p>
   * @return String
   **/
  public final String getChDay() {
    return this.chDay;
  }

  /**
   * <p>Setter for chDay.</p>
   * @param pChDay reference
   **/
  public final void setChDay(final String pChDay) {
    this.chDay = pChDay;
  }

  /**
   * <p>Getter for chMonthYear.</p>
   * @return String
   **/
  public final String getChMonthYear() {
    return this.chMonthYear;
  }

  /**
   * <p>Setter for chMonthYear.</p>
   * @param pChMonthYear reference
   **/
  public final void setChMonthYear(final String pChMonthYear) {
    this.chMonthYear = pChMonthYear;
  }

  /**
   * <p>Getter for seller.</p>
   * @return String
   **/
  public final String getSeller() {
    return this.seller;
  }

  /**
   * <p>Setter for seller.</p>
   * @param pSeller reference
   **/
  public final void setSeller(final String pSeller) {
    this.seller = pSeller;
  }


  /**
   * <p>Getter for sellerAddr.</p>
   * @return String
   **/
  public final String getSellerAddr() {
    return this.sellerAddr;
  }

  /**
   * <p>Setter for sellerAddr.</p>
   * @param pSellerAddr reference
   **/
  public final void setSellerAddr(final String pSellerAddr) {
    this.sellerAddr = pSellerAddr;
  }

  /**
   * <p>Getter for sellerInnKpp.</p>
   * @return String
   **/
  public final String getSellerInnKpp() {
    return this.sellerInnKpp;
  }

  /**
   * <p>Setter for sellerInnKpp.</p>
   * @param pSellerInnKpp reference
   **/
  public final void setSellerInnKpp(final String pSellerInnKpp) {
    this.sellerInnKpp = pSellerInnKpp;
  }

  /**
   * <p>Getter for gruzootpr.</p>
   * @return String
   **/
  public final String getGruzootpr() {
    return this.gruzootpr;
  }

  /**
   * <p>Setter for gruzootpr.</p>
   * @param pGruzootpr reference
   **/
  public final void setGruzootpr(final String pGruzootpr) {
    this.gruzootpr = pGruzootpr;
  }

  /**
   * <p>Getter for gruzopol.</p>
   * @return String
   **/
  public final String getGruzopol() {
    return this.gruzopol;
  }

  /**
   * <p>Setter for gruzopol.</p>
   * @param pGruzopol reference
   **/
  public final void setGruzopol(final String pGruzopol) {
    this.gruzopol = pGruzopol;
  }
}

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
   * <p>Subtotal.</p>
   **/
  private BigDecimal subtotal = BigDecimal.ZERO;

  /**
   * <p>Total taxes.</p>
   **/
  private BigDecimal totalTaxes = BigDecimal.ZERO;

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

  private String prdNum;

  private String prdData;

  private String buyer;
  
  private String buyerAddr;
  
  private String buyerInnKpp;
  
  private String currency;
  
  private List<SfacturaLineModel> items;

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
   * <p>Getter for subtotal.</p>
   * @return BigDecimal
   **/
  public final BigDecimal getSubtotal() {
    return this.subtotal;
  }

  /**
   * <p>Setter for subtotal.</p>
   * @param pSubtotal reference
   **/
  public final void setSubtotal(final BigDecimal pSubtotal) {
    this.subtotal = pSubtotal;
  }

  /**
   * <p>Getter for totalTaxes.</p>
   * @return BigDecimal
   **/
  public final BigDecimal getTotalTaxes() {
    return this.totalTaxes;
  }

  /**
   * <p>Setter for totalTaxes.</p>
   * @param pTotalTaxes reference
   **/
  public final void setTotalTaxes(final BigDecimal pTotalTaxes) {
    this.totalTaxes = pTotalTaxes;
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

  /**
   * <p>Getter for prdNum.</p>
   * @return String
   **/
  public final String getPrdNum() {
    return this.prdNum;
  }

  /**
   * <p>Setter for prdNum.</p>
   * @param pPrdNum reference
   **/
  public final void setPrdNum(final String pPrdNum) {
    this.prdNum = pPrdNum;
  }

  /**
   * <p>Getter for prdData.</p>
   * @return String
   **/
  public final String getPrdData() {
    return this.prdData;
  }

  /**
   * <p>Setter for prdData.</p>
   * @param pPrdData reference
   **/
  public final void setPrdData(final String pPrdData) {
    this.prdData = pPrdData;
  }

  /**
   * <p>Getter for buyer.</p>
   * @return String
   **/
  public final String getBuyer() {
    return this.buyer;
  }

  /**
   * <p>Setter for buyer.</p>
   * @param pBuyer reference
   **/
  public final void setBuyer(final String pBuyer) {
    this.buyer = pBuyer;
  }

  /**
   * <p>Getter for buyerAddr.</p>
   * @return String
   **/
  public final String getBuyerAddr() {
    return this.buyerAddr;
  }

  /**
   * <p>Setter for buyerAddr.</p>
   * @param pBuyerAddr reference
   **/
  public final void setBuyerAddr(final String pBuyerAddr) {
    this.buyerAddr = pBuyerAddr;
  }

  /**
   * <p>Getter for buyerInnKpp.</p>
   * @return String
   **/
  public final String getBuyerInnKpp() {
    return this.buyerInnKpp;
  }

  /**
   * <p>Setter for buyerInnKpp.</p>
   * @param pBuyerInnKpp reference
   **/
  public final void setBuyerInnKpp(final String pBuyerInnKpp) {
    this.buyerInnKpp = pBuyerInnKpp;
  }

  /**
   * <p>Getter for currency.</p>
   * @return String
   **/
  public final String getCurrency() {
    return this.currency;
  }

  /**
   * <p>Setter for currency.</p>
   * @param pCurrency reference
   **/
  public final void setCurrency(final String pCurrency) {
    this.currency = pCurrency;
  }

  /**
   * <p>Getter for items.</p>
   * @return List<SfacturaLineModel>
   **/
  public final List<SfacturaLineModel> getItems() {
    return this.items;
  }

  /**
   * <p>Setter for items.</p>
   * @param pItems reference
   **/
  public final void setItems(final List<SfacturaLineModel> pItems) {
    this.items = pItems;
  }
}

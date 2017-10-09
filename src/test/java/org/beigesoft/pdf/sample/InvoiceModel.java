package org.beigesoft.pdf.sample;

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

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

/**
 * <p>Invoice model generic sample.</p>
 *
 * @author Yury Demidenko
 */
public class InvoiceModel {

  /**
   * <p>Owner's information, the first is name. It consist of data
   * like phones/addresses, etc.</p>
   **/
  private List<String> ownerInfo;

  /**
   * <p>Customer's data, the first is name. It consist of data
   * like phones/addresses, etc.</p>
   **/
  private List<String> customerInfo;

  /**
   * <p>Number of invoice.</p>
   **/
  private String itsNumber;

  /**
   * <p>Number of invoice.</p>
   **/
  private Date itsDate;

  /**
   * <p>Subtotal.</p>
   **/
  private BigDecimal subtotal = BigDecimal.ZERO;

  /**
   * <p>Total taxes.</p>
   **/
  private BigDecimal totalTaxes = BigDecimal.ZERO;

  /**
   * <p>Total.</p>
   **/
  private BigDecimal total = BigDecimal.ZERO;

  /**
   * <p>Items.</p>
   **/
  private List<InvoiceLineModel> items;

  //Simple getters and setters:
  /**
   * <p>Getter for ownerInfo.</p>
   * @return List<String>
   **/
  public final List<String> getOwnerInfo() {
    return this.ownerInfo;
  }

  /**
   * <p>Setter for ownerInfo.</p>
   * @param pOwnerInfo reference
   **/
  public final void setOwnerInfo(final List<String> pOwnerInfo) {
    this.ownerInfo = pOwnerInfo;
  }

  /**
   * <p>Getter for customerInfo.</p>
   * @return List<String>
   **/
  public final List<String> getCustomerInfo() {
    return this.customerInfo;
  }

  /**
   * <p>Setter for customerInfo.</p>
   * @param pCustomerInfo reference
   **/
  public final void setCustomerInfo(final List<String> pCustomerInfo) {
    this.customerInfo = pCustomerInfo;
  }

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
   * <p>Getter for items.</p>
   * @return List<InvoiceLineModel>
   **/
  public final List<InvoiceLineModel> getItems() {
    return this.items;
  }

  /**
   * <p>Setter for items.</p>
   * @param pItems reference
   **/
  public final void setItems(final List<InvoiceLineModel> pItems) {
    this.items = pItems;
  }
}

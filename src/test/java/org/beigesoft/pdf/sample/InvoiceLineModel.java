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

/**
 * <p>Invoice line model generic sample.</p>
 *
 * @author Yury Demidenko
 */
public class InvoiceLineModel {

  /**
   * <p>Item.</p>
   **/
  private String item;

  /**
   * <p>UOM.</p>
   **/
  private String uom;

  /**
   * <p>Price.</p>
   **/
  private String price;

  /**
   * <p>Quantity.</p>
   **/
  private String quantity;

  /**
   * <p>Subtotal.</p>
   **/
  private String subtotal;

  /**
   * <p>Taxes.</p>
   **/
  private String taxes;

  /**
   * <p>Total taxes.</p>
   **/
  private String totalTaxes;

  /**
   * <p>Total.</p>
   **/
  private String total;

  /**
   * <p>Constructor default.</p>
   **/
  public InvoiceLineModel() {
  }

  /**
   * <p>Constructor.</p>
   * @param pItem reference
   **/
  public InvoiceLineModel(final String pItem, final String pUom,
    final String pPrice, final String pQuantity, final String pSubtotal,
      final String pTaxes, final String pTotalTaxes, final String pTotal) {
    this.total = pTotal;
    this.totalTaxes = pTotalTaxes;
    this.taxes = pTaxes;
    this.subtotal = pSubtotal;
    this.quantity = pQuantity;
    this.price = pPrice;
    this.uom = pUom;
    this.item = pItem;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for item.</p>
   * @return String
   **/
  public final String getItem() {
    return this.item;
  }

  /**
   * <p>Setter for item.</p>
   * @param pItem reference
   **/
  public final void setItem(final String pItem) {
    this.item = pItem;
  }

  /**
   * <p>Getter for uom.</p>
   * @return String
   **/
  public final String getUom() {
    return this.uom;
  }

  /**
   * <p>Setter for uom.</p>
   * @param pUom reference
   **/
  public final void setUom(final String pUom) {
    this.uom = pUom;
  }

  /**
   * <p>Getter for price.</p>
   * @return String
   **/
  public final String getPrice() {
    return this.price;
  }

  /**
   * <p>Setter for price.</p>
   * @param pPrice reference
   **/
  public final void setPrice(final String pPrice) {
    this.price = pPrice;
  }

  /**
   * <p>Getter for quantity.</p>
   * @return String
   **/
  public final String getQuantity() {
    return this.quantity;
  }

  /**
   * <p>Setter for quantity.</p>
   * @param pQuantity reference
   **/
  public final void setQuantity(final String pQuantity) {
    this.quantity = pQuantity;
  }

  /**
   * <p>Getter for subtotal.</p>
   * @return String
   **/
  public final String getSubtotal() {
    return this.subtotal;
  }

  /**
   * <p>Setter for subtotal.</p>
   * @param pSubtotal reference
   **/
  public final void setSubtotal(final String pSubtotal) {
    this.subtotal = pSubtotal;
  }

  /**
   * <p>Getter for taxes.</p>
   * @return String
   **/
  public final String getTaxes() {
    return this.taxes;
  }

  /**
   * <p>Setter for taxes.</p>
   * @param pTaxes reference
   **/
  public final void setTaxes(final String pTaxes) {
    this.taxes = pTaxes;
  }

  /**
   * <p>Getter for totalTaxes.</p>
   * @return String
   **/
  public final String getTotalTaxes() {
    return this.totalTaxes;
  }

  /**
   * <p>Setter for totalTaxes.</p>
   * @param pTotalTaxes reference
   **/
  public final void setTotalTaxes(final String pTotalTaxes) {
    this.totalTaxes = pTotalTaxes;
  }

  /**
   * <p>Getter for total.</p>
   * @return String
   **/
  public final String getTotal() {
    return this.total;
  }

  /**
   * <p>Setter for total.</p>
   * @param pTotal reference
   **/
  public final void setTotal(final String pTotal) {
    this.total = pTotal;
  }
}

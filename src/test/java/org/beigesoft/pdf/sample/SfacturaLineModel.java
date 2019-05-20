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

package org.beigesoft.pdf.sample;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <p>Sfactura line model generic sample.</p>
 *
 * @author Yury Demidenko
 */
public class SfacturaLineModel {

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
  private BigDecimal price;

  /**
   * <p>Quantity.</p>
   **/
  private BigDecimal quantity;

  /**
   * <p>Subtotal.</p>
   **/
  private BigDecimal subtotal;

  /**
   * <p>Tax rate.</p>
   **/
  private BigDecimal taxRate;

  /**
   * <p>Total taxes.</p>
   **/
  private BigDecimal totalTaxes;

  /**
   * <p>Total.</p>
   **/
  private BigDecimal total;

  /**
   * <p>UOM code.</p>
   **/
  private String uomCode;

  /**
   * <p>Akciz.</p>
   **/
  private String akciz = "без акцыза";

  /**
   * <p>Constructor default.</p>
   **/
  public SfacturaLineModel() {
  }

  /**
   * <p>Constructor full.</p>
   * @param pItem item
   * @param pUom UOM
   * @param pUomCode UOM code
   * @param pPrice price
   * @param pQuantity quantity
   * @param pTaxRate tax %
   **/
  public SfacturaLineModel(final String pItem, final String pUom,
    final String pUomCode, final BigDecimal pPrice, final BigDecimal pQuantity,
      final BigDecimal pTaxRate) {
    this.item = pItem;
    this.uom = pUom;
    this.uomCode = pUomCode;
    this.quantity = pQuantity;
    this.price = pPrice;
    this.subtotal = this.price.multiply(this.quantity)
      .setScale(2, RoundingMode.HALF_UP);
    this.taxRate = pTaxRate;
    this.totalTaxes = this.subtotal.multiply(this.taxRate).divide(new BigDecimal("100"))
      .setScale(2, RoundingMode.HALF_UP);
    this.total = this.subtotal.add(this.totalTaxes);
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
   * @return BigDecimal
   **/
  public final BigDecimal getPrice() {
    return this.price;
  }

  /**
   * <p>Setter for price.</p>
   * @param pPrice reference
   **/
  public final void setPrice(final BigDecimal pPrice) {
    this.price = pPrice;
  }

  /**
   * <p>Getter for quantity.</p>
   * @return BigDecimal
   **/
  public final BigDecimal getQuantity() {
    return this.quantity;
  }

  /**
   * <p>Setter for quantity.</p>
   * @param pQuantity reference
   **/
  public final void setQuantity(final BigDecimal pQuantity) {
    this.quantity = pQuantity;
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
   * <p>Getter for taxRate.</p>
   * @return BigDecimal
   **/
  public final BigDecimal getTaxRate() {
    return this.taxRate;
  }

  /**
   * <p>Setter for taxRate.</p>
   * @param pTaxRate reference
   **/
  public final void setTaxRate(final BigDecimal pTaxRate) {
    this.taxRate = pTaxRate;
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
   * <p>Getter for uomCode.</p>
   * @return String
   **/
  public final String getUomCode() {
    return this.uomCode;
  }

  /**
   * <p>Setter for uomCode.</p>
   * @param pUomCode reference
   **/
  public final void setUomCode(final String pUomCode) {
    this.uomCode = pUomCode;
  }

  /**
   * <p>Getter for akciz.</p>
   * @return String
   **/
  public final String getAkciz() {
    return this.akciz;
  }

  /**
   * <p>Setter for akciz.</p>
   * @param pAkciz reference
   **/
  public final void setAkciz(final String pAkciz) {
    this.akciz = pAkciz;
  }
}

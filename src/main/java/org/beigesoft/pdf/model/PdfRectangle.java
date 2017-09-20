package org.beigesoft.pdf.model;

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
 * <p>PDF rectangle model.</p>
 *
 * @author Yury Demidenko
 */
public class PdfRectangle {

  /**
   * <p>Lower left X.</p>
   **/
  private float llX;

  /**
   * <p>Lower left Y.</p>
   **/
  private float llY;

  /**
   * <p>Upper right X.</p>
   **/
  private float urX;

  /**
   * <p>Upper right Y.</p>
   **/
  private float urY;

  /**
   * <p>Name e.g. MediaBox.</p>
   **/
  private String name = "MediaBox";

  //Simple getters and setters:

  /**
   * <p>Getter for llX.</p>
   * @return float
   **/
  public final float getLlX() {
    return this.llX;
  }

  /**
   * <p>Setter for llX.</p>
   * @param pLlX reference
   **/
  public final void setLlX(final float pLlX) {
    this.llX = pLlX;
  }

  /**
   * <p>Getter for llY.</p>
   * @return float
   **/
  public final float getLlY() {
    return this.llY;
  }

  /**
   * <p>Setter for llY.</p>
   * @param pLlY reference
   **/
  public final void setLlY(final float pLlY) {
    this.llY = pLlY;
  }

  /**
   * <p>Getter for urX.</p>
   * @return float
   **/
  public final float getUrX() {
    return this.urX;
  }

  /**
   * <p>Setter for urX.</p>
   * @param pUrX reference
   **/
  public final void setUrX(final float pUrX) {
    this.urX = pUrX;
  }

  /**
   * <p>Getter for urY.</p>
   * @return float
   **/
  public final float getUrY() {
    return this.urY;
  }

  /**
   * <p>Setter for urY.</p>
   * @param pUrY reference
   **/
  public final void setUrY(final float pUrY) {
    this.urY = pUrY;
  }

  /**
   * <p>Getter for name.</p>
   * @return String
   **/
  public final String getName() {
    return this.name;
  }

  /**
   * <p>Setter for name.</p>
   * @param pName reference
   **/
  public final void setName(final String pName) {
    this.name = pName;
  }
}

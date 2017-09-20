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
 * <p>PDF xref model.</p>
 *
 * @author Yury Demidenko
 */
public class PdfXref {

  /**
   * <p>Start.</p>
   **/
  private Integer start;

  //Simple getters and setters:
  /**
   * <p>Getter for start.</p>
   * @return Integer
   **/
  public final Integer getStart() {
    return this.start;
  }

  /**
   * <p>Setter for start.</p>
   * @param pStart reference
   **/
  public final void setStart(final Integer pStart) {
    this.start = pStart;
  }
}

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

import org.beigesoft.doc.model.DocImage;

/**
 * <p>PDF image stream model.</p>
 *
 * @author Yury Demidenko
 */
public class PdfImage extends APdfObject<PdfImage> {

  /**
   * <p>Image.</p>
   **/
  private DocImage docImage;

  //Simple getters and setters:
  /**
   * <p>Getter for image.</p>
   * @return DocImage
   **/
  public final DocImage getDocImage() {
    return this.docImage;
  }

  /**
   * <p>Setter for image.</p>
   * @param pDocImage reference
   **/
  public final void setDocImage(final DocImage pDocImage) {
    this.docImage = pDocImage;
  }
}

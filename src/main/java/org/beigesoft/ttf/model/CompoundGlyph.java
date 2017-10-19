package org.beigesoft.ttf.model;

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

import java.util.Set;

/**
 * <p>Compound glyph model.</p>
 *
 * @author Yury Demidenko
 */
public class CompoundGlyph extends Glyph {

  /**
   * <p>GIDs of parts.</p>
   **/
  private Set<Character> partsGids;

  //Simple getters and setters:
  /**
   * <p>Getter for partsGids.</p>
   * @return Set<Character>
   **/
  public final Set<Character> getPartsGids() {
    return this.partsGids;
  }

  /**
   * <p>Setter for partsGids.</p>
   * @param pPartsGids reference
   **/
  public final void setPartsGids(final Set<Character> pPartsGids) {
    this.partsGids = pPartsGids;
  }
}

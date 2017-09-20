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

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * <p>PDF stream ToUnicode model.</p>
 *
 * @author Yury Demidenko
 */
public class PdfToUnicode extends APdfStream<PdfToUnicode> {

  /**
   * <p>Revealed Unicode to CID map.</p>
   **/
  private Map<Character, Character> uniToCid;

  /**
   * <p>Bfranges.</p>
   **/
  private final List<String> bfranges
    = new ArrayList<String>();

  /**
   * <p>Used cid set to make TTF fonts widths and CMAP.</p>
   **/
  private final List<Character> usedCids
    = new ArrayList<Character>();

  /**
   * <p>Used CID to unicode CMAP.</p>
   **/
  private final Map<Character, Character> usedCidToUni
    = new HashMap<Character, Character>();

  //Simple getters and setters:
  /**
   * <p>Getter for bfranges.</p>
   * @return List<String>
   **/
  public final List<String> getBfranges() {
    return this.bfranges;
  }

  /**
   * <p>Getter for usedCids.</p>
   * @return Set<Character>
   **/
  public final List<Character> getUsedCids() {
    return this.usedCids;
  }

  /**
   * <p>Getter for usedCidToUni.</p>
   * @return Map<Character, Character>
   **/
  public final Map<Character, Character> getUsedCidToUni() {
    return this.usedCidToUni;
  }

  /**
   * <p>Getter for uniToCid.</p>
   * @return Map<Character, Character>
   **/
  public final Map<Character, Character> getUniToCid() {
    return this.uniToCid;
  }

  /**
   * <p>Setter for uniToCid.</p>
   * @param pUniToCid reference
   **/
  public final void setUniToCid(final Map<Character, Character> pUniToCid) {
    this.uniToCid = pUniToCid;
  }
}

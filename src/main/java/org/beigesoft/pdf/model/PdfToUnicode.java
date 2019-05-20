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

package org.beigesoft.pdf.model;

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

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

package org.beigesoft.pdf.service;

import java.util.Map;

import org.beigesoft.doc.service.IEvalCharWidth;
import org.beigesoft.ttf.model.TtfFont;
import org.beigesoft.pdf.exception.ExceptionPdfWr;

/**
 * <p>Service that evaluate character width in current UOM.</p>
 *
 * @author Yury Demidenko
 */
public class EvalCharWidth implements IEvalCharWidth {

  /**
   * <p>App-scoped TTF fonts data map.</p>
   **/
  private Map<String, TtfFont> ttfFonts;

  /**
   * <p>Evaluate char width in current UOM.</p>
   * @param pSource char
   * @param pFntNm font name
   * @param pFntSize font size
   * @return char width in current UOM
   * @throws Exception an Exception
   **/
  @Override
  public final double eval(final char pSource,
    final String pFntNm, final double pFntSize) throws Exception {
    TtfFont ttf = this.ttfFonts.get(pFntNm);
    if (ttf == null) {
      throw new ExceptionPdfWr(
        "Metrics of standard 14 fonts not yet implemented!!!");
    }
    Character gid = ttf.getCmap().getUniToCid().get(pSource);
    if (gid == null) {
      throw new ExceptionPdfWr(
        "Can't find GID for char/uni: " + pSource + "/" + ((int) pSource));
    }
    double wdp = ttf.getHmtx().getWidthForGid(gid);
    double upe = ttf.getHead().getUnitsPerEm();
    return pFntSize * wdp / upe;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for ttfFonts.</p>
   * @return Map<String, TtfFont>
   **/
  public final Map<String, TtfFont> getTtfFonts() {
    return this.ttfFonts;
  }

  /**
   * <p>Setter for ttfFonts.</p>
   * @param pTtfFonts reference
   **/
  public final void setTtfFonts(final Map<String, TtfFont> pTtfFonts) {
    this.ttfFonts = pTtfFonts;
  }
}

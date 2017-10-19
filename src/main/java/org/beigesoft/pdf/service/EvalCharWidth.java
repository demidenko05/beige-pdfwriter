package org.beigesoft.pdf.service;

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

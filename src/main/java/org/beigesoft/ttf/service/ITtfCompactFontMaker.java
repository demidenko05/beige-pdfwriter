package org.beigesoft.ttf.service;

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

import java.util.Map;
import java.util.List;

/**
 * <p>It makes compact TTF font for embedding into PDF.</p>
 *
 * @author Yury Demidenko
 */
public interface ITtfCompactFontMaker {

  /**
   * <p>It makes compact TTF font for embedding into PDF.</p>
   * @param pAddParam additional params
   * @param pFontName Font Name (same as TTF file name without ".ttf")
   * @param pUsedCids used chars
   * @return result compact for for embedding
   * @throws Exception an Exception
   **/
  byte[] make(Map<String, Object> pAddParam,
    String pFontName,
      List<Character> pUsedCids) throws Exception;
}

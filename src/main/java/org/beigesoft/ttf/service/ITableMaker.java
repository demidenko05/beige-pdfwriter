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

import org.beigesoft.ttf.model.Glyph;
import org.beigesoft.ttf.model.TableForEmbedding;
import org.beigesoft.ttf.model.TtfTableDirEntry;

/**
 * <p>Makes TTF table and write to output stream.</p>
 *
 * @param <T> table type
 * @author Yury Demidenko
 */
public interface ITableMaker<T extends TableForEmbedding>  {

  /**
   * <p>It makes TTF table and write to output stream.</p>
   * @param pIs input stream
   * @param pOs output stream
   * @param pTfe table for embedding
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @param pUsedCids used chars
   * @param pGls used glyphs map
   * @throws Exception an Exception
   **/
  void makeTable(TtfInputStream pIs, TtfOutputStream pOs,
    T pTfe, TtfTableDirEntry pTde, long[] pCurrLongChksum,
      List<Character> pUsedCids, Map<Integer, Glyph> pGls) throws Exception;
}

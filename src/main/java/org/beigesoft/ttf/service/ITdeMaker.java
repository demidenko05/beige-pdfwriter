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


import org.beigesoft.ttf.model.TtfTableDirEntry;
import org.beigesoft.ttf.model.TableForEmbedding;

/**
 * <p>Makes TTF TDE and write to output stream.</p>
 *
 * @author Yury Demidenko
 */
public interface ITdeMaker {

  /**
   * <p>It makes standard TDE (almost full copy).</p>
   * @param pOs output stream
   * @param pTfe table for embedding
   * @param pOffset offset if known, otherwise null
   * @return TDE
   * @throws Exception an Exception
   **/
  TtfTableDirEntry makeTde(TtfOutputStream pOs,
    TableForEmbedding pTfe, Long pOffset) throws Exception;
}

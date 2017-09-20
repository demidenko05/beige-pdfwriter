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


import org.beigesoft.ttf.model.TableForEmbedding;
import org.beigesoft.ttf.model.TtfTableDirEntry;

/**
 * <p>Makes TTF TDE and write to output stream.</p>
 *
 * @author Yury Demidenko
 */
public class TdeMaker implements ITdeMaker {

  /**
   * <p>It makes standard TDE (almost full copy).</p>
   * @param pOs output stream
   * @param pTfe table for embedding
   * @param pOffset offset if known, otherwise null
   * @return TDE
   * @throws Exception an Exception
   **/
  @Override
  public final TtfTableDirEntry makeTde(final TtfOutputStream pOs,
    final TableForEmbedding pTfe, final Long pOffset) throws Exception {
    TtfTableDirEntry tde = new TtfTableDirEntry();
    tde.setTag(pTfe.getSourceTde().getTag());
    tde.setTagString(pTfe.getSourceTde().getTagString());
    pOs.writeByteArr(tde.getTag());
    if (pTfe.getIsChecksumSame()) {
      tde.setChecksum(pTfe.getSourceTde().getChecksum());
      pOs.writeUInt32(tde.getChecksum());
    } else {
      tde.setChecksumIdx((int) pOs.getSize());
      pOs.writeZeroBytes(4);
    }
    if (pOffset != null) {
      tde.setOffset(pOffset);
      pOs.writeUInt32(tde.getOffset());
    } else {
      tde.setOffsetIdx((int) pOs.getSize());
      pOs.writeZeroBytes(4);
    }
    if (pTfe.getIsLengthSame()) {
      tde.setLength(pTfe.getSourceTde().getLength());
      pOs.writeUInt32(tde.getLength());
    } else {
      tde.setLengthIdx((int) pOs.getSize());
      pOs.writeZeroBytes(4);
    }
    return tde;
  }
}

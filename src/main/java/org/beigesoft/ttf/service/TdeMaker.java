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

package org.beigesoft.ttf.service;

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

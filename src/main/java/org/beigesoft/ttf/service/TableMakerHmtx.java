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

import java.util.Map;
import java.util.List;

import org.beigesoft.log.ILog;
import org.beigesoft.ttf.model.Glyph;
import org.beigesoft.ttf.model.TtfTableDirEntry;
import org.beigesoft.ttf.model.TableForEmbeddingHmtx;

/**
 * <p>Makes glyf table.</p>
 *
 * @author Yury Demidenko
 */
public class TableMakerHmtx implements ITableMaker<TableForEmbeddingHmtx> {

  /**
   * <p>Log.</p>
   **/
  private ILog log;

  /**
   * <p>It makes TTF table and write to output stream.</p>
   * @param pIs input stream
   * @param pOs output stream
   * @param pTfe table for embedding buffered
   * @param pTde table dir entry
   * @param pCurrLongChksum checksum array buffer
   * @param pUsedCids used chars
   * @param pGls used glyphs map
   * @throws Exception an Exception
   **/
  @Override
  public final void makeTable(final TtfInputStream pIs,
    final TtfOutputStream pOs, final TableForEmbeddingHmtx pTfe,
      final TtfTableDirEntry pTde, final long[] pCurrLongChksum,
        final List<Character> pUsedCids,
          final Map<Integer, Glyph> pGls) throws Exception {
    // last CID + 1 (include zero)
    int numGlyphs = pUsedCids.get(pUsedCids.size() - 1) + 1;
    pTde.setOffset(pOs.getSize());
    int widthsLen = Math.min(numGlyphs, pTfe.getTtf().getHhea()
      .getNumOfLongHorMetrics());
    char chz = 0;
    short shz = 0;
    for (int i = 0; i < widthsLen; i++) {
      if (pGls.get(i) != null) {
        pOs.writeUInt16(pTfe.getTtf().getHmtx().getWidths()[i],
          pTde, pCurrLongChksum);
        pOs.writeSInt16(pTfe.getTtf().getHmtx().getLeftSideBearing()[i],
          pTde, pCurrLongChksum);
      } else { //to reduce file
        pOs.writeUInt16(chz, pTde, pCurrLongChksum);
        pOs.writeSInt16(shz, pTde, pCurrLongChksum);
      }
    }
    //add bearing:
    for (int i = widthsLen; i < numGlyphs; i++) {
      if (pGls.get(i) != null) {
        pOs.writeSInt16(pTfe.getTtf().getHmtx()
          .getLeftSideBearingAdd()[i - widthsLen], pTde, pCurrLongChksum);
      } else { //to reduce file
        pOs.writeSInt16(shz, pTde, pCurrLongChksum);
      }
    }
    int mod4 = (int) pTde.getLength() % 4;
    boolean dbgSh = getLog().getDbgSh(this.getClass(), 4060);
    if (mod4 != 0) {
      pOs.addZeroBytesToCheksum(4 - mod4, pTde, pCurrLongChksum);
      if (dbgSh) {
        this.log.debug(null, TableMakerHmtx.class,
          "hmtx added zeros to checksum " + (4 - mod4));
      }
    }
    if (dbgSh) {
      this.log.debug(null, TableMakerHmtx.class,
        "Added hmtx checksum/offset/length " + pTde.getChecksum() + "/"
          + pTde.getOffset() + "/" + pTde.getLength());
    }
  }

  //Simple getters and setters:

  /**
   * <p>Getter for log.</p>
   * @return ILog
   **/
  public final ILog getLog() {
    return this.log;
  }

  /**
   * <p>Setter for log.</p>
   * @param pLog reference
   **/
  public final void setLog(final ILog pLog) {
    this.log = pLog;
  }
}

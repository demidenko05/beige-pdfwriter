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
import org.beigesoft.ttf.model.TableForEmbeddingHead;

/**
 * <p>Makes head TTF table and write to output stream.</p>
 *
 * @author Yury Demidenko
 */
public class TableMakerHead implements ITableMaker<TableForEmbeddingHead> {

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
    final TtfOutputStream pOs, final TableForEmbeddingHead pTfe,
      final TtfTableDirEntry pTde, final long[] pCurrLongChksum,
        final List<Character> pUsedCids,
          final Map<Integer, Glyph> pGls) throws Exception {
    pTde.setOffset(pOs.getSize());
    //version, fontRevision
    //checksumAdjustment, magicNumber, flags:
    pOs.writeByteArr(pTfe.getHead().getHeadByteArr(), null, null);
    pOs.writeUInt16(pTfe.getHead().getUnitsPerEm(), null, null);
    //created, modified:
    pOs.writeByteArr(pTfe.getHead().getCreatedModifiedBuf(), null, null);
    pOs.writeSInt16(pTfe.getHead().getXMin(), null, null);
    pOs.writeSInt16(pTfe.getHead().getYMin(), null, null);
    pOs.writeSInt16(pTfe.getHead().getXMax(), null, null);
    pOs.writeSInt16(pTfe.getHead().getYMax(), null, null);
    //macStyle, lowestRecPPEM, fontDirectionHint:
    pOs.writeByteArr(pTfe.getHead().getMsLrpFdhBuf(), null, null);
    pOs.writeSInt16(pTfe.getHead().getIndexToLocFormat(), null, null);
    pOs.writeByteArr(pTfe.getHead().getGlyphDataFormatBuf(), null, null);
    if (getLog().getDbgSh() && getLog().getDbgFl() < 4040
      && getLog().getDbgCl() > 4042) {
      this.log.debug(null, TableMakerHead.class,
        "Added head checksum/offset/length " + pTde.getChecksum() + "/"
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

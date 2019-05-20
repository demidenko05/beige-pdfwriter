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
import org.beigesoft.ttf.model.TableForEmbeddingBf;
import org.beigesoft.pdf.exception.ExceptionPdfWr;

/**
 * <p>Makes full copy TTF table and write to output stream.
 * The table is already cached. E.g. cvt, fpgm, gasp, prep.</p>
 *
 * @author Yury Demidenko
 */
public class TableMakerFc implements ITableMaker<TableForEmbeddingBf> {

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
    final TtfOutputStream pOs, final TableForEmbeddingBf pTfe,
      final TtfTableDirEntry pTde, final long[] pCurrLongChksum,
        final List<Character> pUsedCids,
          final Map<Integer, Glyph> pGls) throws Exception {
    if (pTde.getLength() != pTfe.getBuffer().length) {
      throw new ExceptionPdfWr(
        "Algorithm length full copy TTF table error! tde/buffer: "
          + pTde.getLength() + "/" + pTfe.getBuffer().length);
    }
    pTde.setOffset(pOs.getSize());
    if (getLog().getDbgSh() && getLog().getDbgFl() < 4030
      && getLog().getDbgCl() > 4032) {
      this.log.debug(null, TableMakerFc.class,
        "Full copy from buffer - table/offsetOs/length "
          + pTde.getTagString() + "/" + pOs.getSize()
            + "/" + pTde.getLength());
    }
    long[] copyLongChksum = new long[] {-1L, -1L, -1L, -1L};
    TtfTableDirEntry tdeCopy = new TtfTableDirEntry();
    pOs.writeByteArr(pTfe.getBuffer(), tdeCopy, copyLongChksum);
    int mod4 = (int) pTde.getLength() % 4;
    if (mod4 != 0) {
      pOs.addZeroBytesToCheksum(4 - mod4, tdeCopy, copyLongChksum);
      if (getLog().getDbgSh() && getLog().getDbgFl() < 4030
        && getLog().getDbgCl() > 4032) {
        this.log.debug(null, TtfCompactFontMaker.class,
          pTde.getTagString() + " added zeros to checksum " + (4 - mod4));
      }
    }
    if (getLog().getDbgSh() && getLog().getDbgFl() < 4030
      && getLog().getDbgCl() > 4032
        && pTde.getChecksum() != tdeCopy.getChecksum()) {
      this.log.warn(null, TableMakerFc.class,
        "Checksums don't equals: tag/checksum source/checksum real: "
          + pTde.getTagString() + "/" + pTde.getChecksum()
            + "/" + tdeCopy.getChecksum());
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

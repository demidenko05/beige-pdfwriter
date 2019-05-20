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
import java.util.Arrays;

import org.beigesoft.log.ILog;
import org.beigesoft.ttf.model.Glyph;
import org.beigesoft.ttf.model.TtfTableDirEntry;
import org.beigesoft.ttf.model.TableForEmbeddingGlyf;

/**
 * <p>Makes glyf table.</p>
 *
 * @author Yury Demidenko
 */
public class TableMakerGlyf implements ITableMaker<TableForEmbeddingGlyf> {

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
    final TtfOutputStream pOs, final TableForEmbeddingGlyf pTfe,
      final TtfTableDirEntry pTde, final long[] pCurrLongChksum,
        final List<Character> pUsedCids,
          final Map<Integer, Glyph> pGls) throws Exception {
    pTde.setOffset(pOs.getSize());
    for (char gid : pUsedCids) {
      Glyph gl = new Glyph();
      pGls.put((int) gid, gl); // for farther fast exist checking
      if (pTfe.getTtf().getLoca().getOffsets16() != null) {
        gl.setOffset(pTfe.getTtf().getLoca().getOffsets16()[gid]);
        gl.setLength(pTfe.getTtf().getLoca()
          .getOffsets16()[gid + 1] - gl.getOffset());
      } else {
        gl.setOffset(pTfe.getTtf().getLoca().getOffsets32()[gid]);
        gl.setLength(pTfe.getTtf().getLoca()
          .getOffsets32()[gid + 1] - gl.getOffset());
      }
      if (gl.getLength() > 0) {
        if (pIs != null) {
          pIs.goAhead(pTfe.getSourceTde().getOffset()
            + gl.getOffset());
          if (getLog().getDbgSh() && getLog().getDbgFl() < 4033
            && getLog().getDbgCl() > 4035) {
            this.log.debug(null, TableMakerGlyf.class,
              "Copy GID/offsetIs/offsetOs/offset/length " + ((int) gid)
                + "/" + pIs.getOffset() + "/" + pOs.getSize() + "/"
                  + gl.getOffset() + "/" + gl.getLength());
          }
          gl.setOffset(pOs.getSize() - pTde.getOffset()); //new CTTF offset
          pOs.copyBytes(pIs, gl.getLength(), pTde, pCurrLongChksum);
        } else {
          if (getLog().getDbgSh() && getLog().getDbgFl() < 4033
            && getLog().getDbgCl() > 4035) {
            this.log.debug(null, TableMakerGlyf.class,
              "Try to copy GID/offsetOs/offset/length " + ((int) gid)
                + "/" + pOs.getSize() + "/"
                  + gl.getOffset() + "/" + gl.getLength());
          }
          // TTF file size is in int scope:
          byte[] glypfData = Arrays.copyOfRange(pTfe.getTtf().getGlyf()
            .getBufferInputStream().getBuffer(), (int) gl.getOffset(),
              (int) (gl.getOffset() + gl.getLength()));
          pOs.writeByteArr(glypfData, pTde, pCurrLongChksum);
        }
        // padding zeros at the end of glyph for 4byte aligned
        int mod4 = (int) gl.getLength() % 4;
        if (mod4 != 0) {
          if (getLog().getDbgSh() && getLog().getDbgFl() < 4033
            && getLog().getDbgCl() > 4035) {
            this.log.debug(null, TableMakerGlyf.class,
              "4-bytes aligned GID/count zeros " + ((int) gid)
                + "/"  + (4 - mod4));
          }
          pOs.writeZeroBytes(4 - mod4, pTde, pCurrLongChksum);
        }
      }
    }
    if (getLog().getDbgSh() && getLog().getDbgFl() < 4033
      && getLog().getDbgCl() > 4035) {
      this.log.debug(null, TableMakerGlyf.class,
        "Added glyf checksum/offset/length "
          + pTde.getChecksum() + "/" + pTde.getOffset()
            + "/" + pTde.getLength());
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

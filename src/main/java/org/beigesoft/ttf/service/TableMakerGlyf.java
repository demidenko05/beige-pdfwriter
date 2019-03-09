package org.beigesoft.ttf.service;

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

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

import org.beigesoft.log.ILogger;
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
   * <p>Logger.</p>
   **/
  private ILogger logger;

  //Debug log preferences made by master bean for improving speed:
  /**
   * <p>If show debug messages.</p>
   **/
  private boolean isShowDebugMessages;

  /**
   * <p>Logs detail level.</p>
   **/
  private int logDetailLevel;

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
        pIs.goAhead(pTfe.getSourceTde().getOffset()
          + gl.getOffset());
        if (this.isShowDebugMessages && this.logDetailLevel > 114) {
          this.logger.debug(null, TableMakerGlyf.class,
            "Copy GID/offsetIs/offsetOs/offset/length " + ((int) gid)
              + "/" + pIs.getOffset() + "/" + pOs.getSize() + "/"
                + gl.getOffset() + "/" + gl.getLength());
        }
        gl.setOffset(pOs.getSize() - pTde.getOffset()); //new CTTF offset
        pOs.copyBytes(pIs, gl.getLength(), pTde, pCurrLongChksum);
        // padding zeros at the end of glyph for 4byte aligned
        int mod4 = (int) gl.getLength() % 4;
        if (mod4 != 0) {
          if (this.isShowDebugMessages && this.logDetailLevel > 114) {
            this.logger.debug(null, TableMakerGlyf.class,
              "4-bytes aligned GID/count zeros " + ((int) gid)
                + "/"  + (4 - mod4));
          }
          pOs.writeZeroBytes(4 - mod4, pTde, pCurrLongChksum);
        }
      }
    }
    if (this.isShowDebugMessages) {
      this.logger.debug(null, TableMakerGlyf.class,
        "Added glyf checksum/offset/length "
          + pTde.getChecksum() + "/" + pTde.getOffset()
            + "/" + pTde.getLength());
    }
  }

  //Simple getters and setters:

  /**
   * <p>Getter for logger.</p>
   * @return ILogger
   **/
  public final ILogger getLogger() {
    return this.logger;
  }

  /**
   * <p>Setter for logger.</p>
   * @param pLogger reference
   **/
  public final void setLogger(final ILogger pLogger) {
    this.logger = pLogger;
  }

  /**
   * <p>Getter for isShowDebugMessages.</p>
   * @return boolean
   **/
  public final boolean getIsShowDebugMessages() {
    return this.isShowDebugMessages;
  }

  /**
   * <p>Setter for isShowDebugMessages.</p>
   * @param pIsShowDebugMessages reference
   **/
  public final void setIsShowDebugMessages(final boolean pIsShowDebugMessages) {
    this.isShowDebugMessages = pIsShowDebugMessages;
  }

  /**
   * <p>Getter for logDetailLevel.</p>
   * @return int
   **/
  public final int getLogDetailLevel() {
    return this.logDetailLevel;
  }

  /**
   * <p>Setter for logDetailLevel.</p>
   * @param pLogDetailLevel reference
   **/
  public final void setLogDetailLevel(final int pLogDetailLevel) {
    this.logDetailLevel = pLogDetailLevel;
  }
}

package org.beigesoft.ttf.model;

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

/**
 * <p>TTF table required for font embedding model
 * that fully buffered and copied without changes.</p>
 *
 * @author Yury Demidenko
 */
public class TableForEmbeddingBf
  extends TableForEmbedding<TableForEmbeddingBf> {

  /**
   * <p>Full data.</p>
   **/
  private final byte[] buffer;

  /**
   * <p>Only constructor.</p>
   * @param pTableDirEntry reference
   * @param pIsLengthSame value
   * @param pIsChecksumSame value
   * @param pBuffer reference
   **/
  public TableForEmbeddingBf(final TtfTableDirEntry pTableDirEntry,
    final boolean pIsLengthSame, final boolean pIsChecksumSame,
      final byte[] pBuffer) {
    super(pTableDirEntry, pIsLengthSame, pIsChecksumSame);
    this.buffer = pBuffer;
  }

  //Simple getters and setters:

  /**
   * <p>Getter for buffer.</p>
   * @return byte[]
   **/
  public final byte[] getBuffer() {
    return this.buffer;
  }
}

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
 * <p>Loca table for embedding.</p>
 *
 * @author Yury Demidenko
 */
public class TableForEmbeddingLoca
  extends TableForEmbedding<TableForEmbeddingLoca> {

  /**
   * <p>Loca data.</p>
   **/
  private TtfLoca loca;

  /**
   * <p>Only constructor.</p>
   * @param pTableDirEntry reference
   * @param pLoca reference
   **/
  public TableForEmbeddingLoca(final TtfTableDirEntry pTableDirEntry,
    final TtfLoca pLoca) {
    super(pTableDirEntry, false, false);
    this.loca = pLoca;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for loca.</p>
   * @return TtfLoca
   **/
  public final TtfLoca getLoca() {
    return this.loca;
  }
}

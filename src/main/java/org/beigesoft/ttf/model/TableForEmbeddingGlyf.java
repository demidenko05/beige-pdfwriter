package org.beigesoft.ttf.model;

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

/**
 * <p>Glyf table for embedding.</p>
 *
 * @author Yury Demidenko
 */
public class TableForEmbeddingGlyf
  extends TableForEmbedding<TableForEmbeddingGlyf> {

  /**
   * <p>TTF data.</p>
   **/
  private TtfFont ttf;

  /**
   * <p>Only constructor.</p>
   * @param pTableDirEntry reference
   * @param pFont reference
   **/
  public TableForEmbeddingGlyf(final TtfTableDirEntry pTableDirEntry,
    final TtfFont pFont) {
    super(pTableDirEntry, false, false);
    this.ttf = pFont;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for ttf.</p>
   * @return TtfFont
   **/
  public final TtfFont getTtf() {
    return this.ttf;
  }
}

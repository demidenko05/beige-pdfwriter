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
 * <p>Hhea table for embedding.</p>
 *
 * @author Yury Demidenko
 */
public class TableForEmbeddingHhea
  extends TableForEmbedding<TableForEmbeddingHhea> {

  /**
   * <p>Hhea data.</p>
   **/
  private TtfHhea hhea;

  /**
   * <p>Only constructor.</p>
   * @param pTableDirEntry reference
   * @param pHhea reference
   **/
  public TableForEmbeddingHhea(final TtfTableDirEntry pTableDirEntry,
    final TtfHhea pHhea) {
    super(pTableDirEntry, true, false);
    this.hhea = pHhea;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for hhea.</p>
   * @return TtfHhea
   **/
  public final TtfHhea getHhea() {
    return this.hhea;
  }
}

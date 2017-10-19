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
 * <p>Maxp table for embedding.</p>
 *
 * @author Yury Demidenko
 */
public class TableForEmbeddingMaxp
  extends TableForEmbedding<TableForEmbeddingMaxp> {

  /**
   * <p>Maxp data.</p>
   **/
  private TtfMaxp maxp;

  /**
   * <p>Only constructor.</p>
   * @param pTableDirEntry reference
   * @param pMaxp reference
   **/
  public TableForEmbeddingMaxp(final TtfTableDirEntry pTableDirEntry,
    final TtfMaxp pMaxp) {
    super(pTableDirEntry, true, false);
    this.maxp = pMaxp;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for maxp.</p>
   * @return TtfMaxp
   **/
  public final TtfMaxp getMaxp() {
    return this.maxp;
  }
}

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
 * <p>Head table for embedding.</p>
 *
 * @author Yury Demidenko
 */
public class TableForEmbeddingHead
  extends TableForEmbedding<TableForEmbeddingHead> {

  /**
   * <p>Head data.</p>
   **/
  private TtfHead head;

  /**
   * <p>Only constructor.</p>
   * @param pTableDirEntry reference
   * @param pHead reference
   **/
  public TableForEmbeddingHead(final TtfTableDirEntry pTableDirEntry,
    final TtfHead pHead) {
    super(pTableDirEntry, true, false);
    this.head = pHead;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for head.</p>
   * @return TtfHead
   **/
  public final TtfHead getHead() {
    return this.head;
  }
}

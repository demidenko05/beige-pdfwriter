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

import java.util.List;
import java.util.ArrayList;

/**
 * <p>TTF font model.</p>
 *
 * @author Yury Demidenko
 */
public class TtfFont {

  /**
   * <p>Scaler type.</p>
   **/
  private long scalerType;

  /**
   * <p>Number of tables.</p>
   **/
  private int numTables;

  /**
   * <p>Search range = (maximum power of 2 <= numTables)*16.</p>
   **/
  private int searchRange;

  /**
   * <p>Entry selector = log2(maximum power of 2 <= numTables).</p>
   **/
  private int entrySelector;

  /**
   * <p>Range shift = numTables*16-searchRange.</p>
   **/
  private int rangeShift;

  /**
   * <p>Range shift = numTables*16-searchRange.</p>
   **/
  private List<TtfTableDirEntry> tableDirectory;

  /**
   * <p>CMAP.</p>
   **/
  private TtfCmap cmap;

  /**
   * <p>Head.</p>
   **/
  private TtfHead head;

  /**
   * <p>File name.</p>
   **/
  private String fileName;

  /**
   * <p>hmtx.</p>
   **/
  private TtfHmtx hmtx;

  /**
   * <p>hhea.</p>
   **/
  private TtfHhea hhea;

  /**
   * <p>OS2.</p>
   **/
  private TtfOs2 os2;

  /**
   * <p>post.</p>
   **/
  private TtfPost post;

  /**
   * <p>loca.</p>
   **/
  private TtfLoca loca;

  /**
   * <p>glyf.</p>
   **/
  private TtfGlyf glyf;

  /**
   * <p>maxp.</p>
   **/
  private TtfMaxp maxp;

  /**
   * <p>Tables for embedding list.</p>
   **/
  private final List<TableForEmbedding> tablesForEmbedding =
    new ArrayList<TableForEmbedding>();

  //Simple getters and setters:
  /**
   * <p>Getter for numTables.</p>
   * @return int
   **/
  public final int getNumTables() {
    return this.numTables;
  }

  /**
   * <p>Setter for numTables.</p>
   * @param pNumTables reference
   **/
  public final void setNumTables(final int pNumTables) {
    this.numTables = pNumTables;
  }

  /**
   * <p>Getter for scalerType.</p>
   * @return long
   **/
  public final long getScalerType() {
    return this.scalerType;
  }

  /**
   * <p>Setter for scalerType.</p>
   * @param pScalerType reference
   **/
  public final void setScalerType(final long pScalerType) {
    this.scalerType = pScalerType;
  }

  /**
   * <p>Getter for searchRange.</p>
   * @return int
   **/
  public final int getSearchRange() {
    return this.searchRange;
  }

  /**
   * <p>Setter for searchRange.</p>
   * @param pSearchRange reference
   **/
  public final void setSearchRange(final int pSearchRange) {
    this.searchRange = pSearchRange;
  }

  /**
   * <p>Getter for entrySelector.</p>
   * @return int
   **/
  public final int getEntrySelector() {
    return this.entrySelector;
  }

  /**
   * <p>Setter for entrySelector.</p>
   * @param pEntrySelector reference
   **/
  public final void setEntrySelector(final int pEntrySelector) {
    this.entrySelector = pEntrySelector;
  }

  /**
   * <p>Getter for rangeShift.</p>
   * @return int
   **/
  public final int getRangeShift() {
    return this.rangeShift;
  }

  /**
   * <p>Setter for rangeShift.</p>
   * @param pRangeShift reference
   **/
  public final void setRangeShift(final int pRangeShift) {
    this.rangeShift = pRangeShift;
  }

  /**
   * <p>Getter for tableDirectory.</p>
   * @return List<TtfTableDirEntry>
   **/
  public final List<TtfTableDirEntry> getTableDirectory() {
    return this.tableDirectory;
  }

  /**
   * <p>Setter for tableDirectory.</p>
   * @param pTableDirectory reference
   **/
  public final void setTableDirectory(
    final List<TtfTableDirEntry> pTableDirectory) {
    this.tableDirectory = pTableDirectory;
  }

  /**
   * <p>Getter for cmap.</p>
   * @return TtfCmap
   **/
  public final TtfCmap getCmap() {
    return this.cmap;
  }

  /**
   * <p>Setter for cmap.</p>
   * @param pCmap reference
   **/
  public final void setCmap(final TtfCmap pCmap) {
    this.cmap = pCmap;
  }

  /**
   * <p>Getter for head.</p>
   * @return TtfHead
   **/
  public final TtfHead getHead() {
    return this.head;
  }

  /**
   * <p>Setter for head.</p>
   * @param pHead reference
   **/
  public final void setHead(final TtfHead pHead) {
    this.head = pHead;
  }

  /**
   * <p>Getter for fileName.</p>
   * @return String
   **/
  public final String getFileName() {
    return this.fileName;
  }

  /**
   * <p>Setter for fileName.</p>
   * @param pFileName reference
   **/
  public final void setFileName(final String pFileName) {
    this.fileName = pFileName;
  }

  /**
   * <p>Getter for hmtx.</p>
   * @return TtfHmtx
   **/
  public final TtfHmtx getHmtx() {
    return this.hmtx;
  }

  /**
   * <p>Setter for hmtx.</p>
   * @param pHmtx reference
   **/
  public final void setHmtx(final TtfHmtx pHmtx) {
    this.hmtx = pHmtx;
  }

  /**
   * <p>Getter for hhea.</p>
   * @return TtfHhea
   **/
  public final TtfHhea getHhea() {
    return this.hhea;
  }

  /**
   * <p>Setter for hhea.</p>
   * @param pHhea reference
   **/
  public final void setHhea(final TtfHhea pHhea) {
    this.hhea = pHhea;
  }

  /**
   * <p>Getter for os2.</p>
   * @return TtfOs2
   **/
  public final TtfOs2 getOs2() {
    return this.os2;
  }

  /**
   * <p>Setter for os2.</p>
   * @param pOs2 reference
   **/
  public final void setOs2(final TtfOs2 pOs2) {
    this.os2 = pOs2;
  }

  /**
   * <p>Getter for post.</p>
   * @return TtfPost
   **/
  public final TtfPost getPost() {
    return this.post;
  }

  /**
   * <p>Setter for post.</p>
   * @param pPost reference
   **/
  public final void setPost(final TtfPost pPost) {
    this.post = pPost;
  }

  /**
   * <p>Getter for loca.</p>
   * @return TtfLoca
   **/
  public final TtfLoca getLoca() {
    return this.loca;
  }

  /**
   * <p>Setter for loca.</p>
   * @param pLoca reference
   **/
  public final void setLoca(final TtfLoca pLoca) {
    this.loca = pLoca;
  }

  /**
   * <p>Getter for glyf.</p>
   * @return TtfGlyf
   **/
  public final TtfGlyf getGlyf() {
    return this.glyf;
  }

  /**
   * <p>Setter for glyf.</p>
   * @param pGlyf reference
   **/
  public final void setGlyf(final TtfGlyf pGlyf) {
    this.glyf = pGlyf;
  }

  /**
   * <p>Getter for maxp.</p>
   * @return TtfMaxp
   **/
  public final TtfMaxp getMaxp() {
    return this.maxp;
  }

  /**
   * <p>Setter for maxp.</p>
   * @param pMaxp reference
   **/
  public final void setMaxp(final TtfMaxp pMaxp) {
    this.maxp = pMaxp;
  }

  /**
   * <p>Getter for tablesForEmbedding.</p>
   * @return List<TableForEmbedding>
   **/
  public final List<TableForEmbedding> getTablesForEmbedding() {
    return this.tablesForEmbedding;
  }
}

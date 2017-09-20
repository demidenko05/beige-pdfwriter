package org.beigesoft.pdf.model;

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

import java.util.Date;

/**
 * <p>PDF info dictionary model.</p>
 *
 * @author Yury Demidenko
 */
public class PdfInfo extends APdfObject<PdfInfo> {

  /**
   * <p>Creator.</p>
   **/
  private String creator = "Beigesoft (TM) PDF Writer";

  /**
   * <p>Producer.</p>
   **/
  private String producer;

  /**
   * <p>Title.</p>
   **/
  private String title;

  /**
   * <p>Author.</p>
   **/
  private String author;

  /**
   * <p>Subject.</p>
   **/
  private String subject;

  /**
   * <p>Keywords.</p>
   **/
  private String keywords;

  /**
   * <p>Created date.</p>
   **/
  private Date creationDate;

  /**
   * <p>Modified date.</p>
   **/
  private Date modDate;

  //Simple getters and setters:
  /**
   * <p>Getter for creator.</p>
   * @return String
   **/
  public final String getCreator() {
    return this.creator;
  }

  /**
   * <p>Setter for creator.</p>
   * @param pCreator reference
   **/
  public final void setCreator(final String pCreator) {
    this.creator = pCreator;
  }

  /**
   * <p>Getter for producer.</p>
   * @return String
   **/
  public final String getProducer() {
    return this.producer;
  }

  /**
   * <p>Setter for producer.</p>
   * @param pProducer reference
   **/
  public final void setProducer(final String pProducer) {
    this.producer = pProducer;
  }

  /**
   * <p>Getter for title.</p>
   * @return String
   **/
  public final String getTitle() {
    return this.title;
  }

  /**
   * <p>Setter for title.</p>
   * @param pTitle reference
   **/
  public final void setTitle(final String pTitle) {
    this.title = pTitle;
  }

  /**
   * <p>Getter for author.</p>
   * @return String
   **/
  public final String getAuthor() {
    return this.author;
  }

  /**
   * <p>Setter for author.</p>
   * @param pAuthor reference
   **/
  public final void setAuthor(final String pAuthor) {
    this.author = pAuthor;
  }

  /**
   * <p>Getter for subject.</p>
   * @return String
   **/
  public final String getSubject() {
    return this.subject;
  }

  /**
   * <p>Setter for subject.</p>
   * @param pSubject reference
   **/
  public final void setSubject(final String pSubject) {
    this.subject = pSubject;
  }

  /**
   * <p>Getter for keywords.</p>
   * @return String
   **/
  public final String getKeywords() {
    return this.keywords;
  }

  /**
   * <p>Setter for keywords.</p>
   * @param pKeywords reference
   **/
  public final void setKeywords(final String pKeywords) {
    this.keywords = pKeywords;
  }

  /**
   * <p>Getter for creationDate.</p>
   * @return Date
   **/
  public final Date getCreationDate() {
    return this.creationDate;
  }

  /**
   * <p>Setter for creationDate.</p>
   * @param pCreationDate reference
   **/
  public final void setCreationDate(final Date pCreationDate) {
    this.creationDate = pCreationDate;
  }

  /**
   * <p>Getter for modDate.</p>
   * @return Date
   **/
  public final Date getModDate() {
    return this.modDate;
  }

  /**
   * <p>Setter for modDate.</p>
   * @param pModDate reference
   **/
  public final void setModDate(final Date pModDate) {
    this.modDate = pModDate;
  }
}

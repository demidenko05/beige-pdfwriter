/*
BSD 2-Clause License

Copyright (c) 2019, Beigesoft™
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this
  list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.beigesoft.pdf.model;

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
  private final String creator = "Beigesoft (TM) Document Writer";

  /**
   * <p>Producer.</p>
   **/
  private final String producer = "Beigesoft (TM) PDF Writer";

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
   * <p>Getter for producer.</p>
   * @return String
   **/
  public final String getProducer() {
    return this.producer;
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

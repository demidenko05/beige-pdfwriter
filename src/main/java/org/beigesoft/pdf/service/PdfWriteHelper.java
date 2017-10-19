package org.beigesoft.pdf.service;

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

import java.util.Date;
import java.util.Calendar;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * <p>PDF write helper.</p>
 *
 * @author Yury Demidenko
 */
public class PdfWriteHelper {

  /**
   * <p>ASCII charset.</p>
   **/
  private final Charset ascii = Charset.forName("ISO-8859-1");

  /**
   * <p>Start object.</p>
   **/
  private final byte[] startObj = "obj\n<<".getBytes(this.ascii);

  /**
   * <p>End object + LF.</p>
   **/
  private final byte[] endObjLf = ">>\nendobj\n".getBytes(this.ascii);

  /**
   * <p>PDF version 1.0 string.</p>
   **/
  private final String pdfVersion10 = "%PDF-1.0\n";

  /**
   * <p>PDF version 1.1 string.</p>
   **/
  private final String pdfVersion11 = "%PDF-1.1\n";

  /**
   * <p>PDF version 1.2 string.</p>
   **/
  private final String pdfVersion12 = "%PDF-1.2\n";

  /**
   * <p>PDF version 1.3 string.</p>
   **/
  private final String pdfVersion13 = "%PDF-1.3\n";

  /**
   * <p>PDF version 1.4 string.</p>
   **/
  private final String pdfVersion14 = "%PDF-1.4\n";

  /**
   * <p>PDF version 1.5 string.</p>
   **/
  private final String pdfVersion15 = "%PDF-1.5\n";

  /**
   * <p>PDF version 1.6 string.</p>
   **/
  private final String pdfVersion16 = "%PDF-1.6\n";

  /**
   * <p>PDF version 1.7 string.</p>
   **/
  private final String pdfVersion17 = "%PDF-1.7\n";

  /**
   * <p>File contains binary sign - comment with 4
   * bytes 128 (0x80) or grater plus LF (PDF1.7 - 7.5.2
   * and 7.2.2 for white-space chars).</p>
   **/
  private final byte[] headBinarySign = new byte[] {(byte) 0x25,
      (byte) 0x80, (byte) 0x81, (byte) 0x82, (byte) 0x83, (byte) 0x0A};

  /**
   * <p>Comment sign % (PDF1.7 - 7.2.2 for white-space chars).</p>
   **/
  private final byte[] comment = new byte[] {(byte) 0x25};

  /**
   * <p>Line feed (PDF1.7 - 7.2.2 for white-space chars).</p>
   **/
  private final byte[] lf = new byte[] {(byte) 0x0A};

  /**
   * <p>Line feed (PDF1.7 - 7.2.2 for white-space chars).</p>
   **/
  private final byte[] eof = "%%EOF".getBytes(this.ascii);

  /**
   * <p>Trailer start.</p>
   **/
  private final byte[] trailerStart = "trailer\n<</Size ".getBytes(this.ascii);

  /**
   * <p>Startxref + LF.</p>
   **/
  private final byte[] startxrefLf = "startxref\n".getBytes(this.ascii);

  /**
   * <p>Xref + LF.</p>
   **/
  private final byte[] xrefLf = "xref\n".getBytes(this.ascii);

  /**
   * <p>Xref 1-st entry + LF.</p>
   **/
  private final byte[] xref1EntryLf = "0000000000 65535 f\n"
    .getBytes(this.ascii);

  /**
   * <p>Escaped left parentheses.</p>
   **/
  private final char[] escLeParentheses = new char[] {0x5C, 0x28};

  /**
   * <p>Escaped right parentheses.</p>
   **/
  private final char[] escRiParentheses = new char[] {0x5C, 0x29};

  /**
   * <p>Escaped backslash.</p>
   **/
  private final char[] escBackSlash = new char[] {0x5C, 0x5c};

  /**
   * <p>End dictionary + LF.</p>
   **/
  private final byte[] endDictLf = ">>\n".getBytes(this.ascii);

  /**
   * <p>End array + LF.</p>
   **/
  private final byte[] endArrLf = "]\n".getBytes(this.ascii);

  /**
   * <p>Start stream + LF.</p>
   **/
  private final byte[] startStreamLf = "stream\n".getBytes(this.ascii);

  /**
   * <p>Start widths array.</p>
   **/
  private final byte[] startWarray = "/W [ ".getBytes(this.ascii);

  /**
   * <p>End stream object + LF.</p>
   **/
  private final byte[] endStreamObjLf =
    "endstream\nendobj\n".getBytes(this.ascii);

  /**
   * <p>Format date to PDF string.</p>
   * @param pDate date to format
   * @return formatted string
   **/
  public final String dateToString(final Date pDate) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(pDate);
    String yearStr = String.valueOf(cal.get(Calendar.YEAR));
    String monthStr = String.format("%02d", cal.get(Calendar.MONTH) + 1);
    String dayStr = String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));
    String hourStr = String.format("%02d", cal.get(Calendar.HOUR_OF_DAY));
    String minuteStr = String.format("%02d", cal.get(Calendar.MINUTE));
    long zoneOffset = cal.get(Calendar.ZONE_OFFSET); //ms
    String plusMinus;
    if (zoneOffset > 0) {
      plusMinus = "+";
    } else {
      plusMinus = "-";
    }
    long hourDelta = zoneOffset / 3600000;
    long minuteDelta = zoneOffset % 3600000 / 60000;
    String hourDeltaStr = String.format("%02d", hourDelta);
    String minuteDeltaStr = String.format("%02d", minuteDelta);
    return "D:" + yearStr + monthStr + dayStr + hourStr + minuteStr
      + plusMinus + hourDeltaStr + "'" + minuteDeltaStr;
  }

  /**
   * <p>Escape PDF string.</p>
   * @param pSource string
   * @return escaped string
   **/
  public final String escapePdf(final String pSource) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < pSource.length(); i++) {
      char ch = pSource.charAt(i);
      char[] escaped = pdfEscape(ch);
      if (escaped != null) {
        sb.append(escaped);
      } else {
        sb.append(ch);
      }
    }
    return sb.toString();
  }

  /**
   * <p>Escape PDF for given char.</p>
   * @param pChar char
   * @return escaped string or NULL if no need to escape
   **/
  public final char[] pdfEscape(final char pChar) {
    if (pChar == '(') {
      return this.escLeParentheses;
    } else if (pChar == ')') {
      return this.escRiParentheses;
    } else if (pChar == '\\') {
      return this.escBackSlash;
    }
    return null;
  }

  /**
   * <p>Write bytes to stream and return bytes count.</p>
   * @param pBytes bytes to write
   * @param pOut stream
   * @return bytes count
   * @throws Exception an Exception
   **/
  public final int writeBytes(
    final byte[] pBytes,
      final OutputStream pOut) throws Exception {
    pOut.write(pBytes);
    return pBytes.length;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for ascii.</p>
   * @return Charset
   **/
  public final Charset getAscii() {
    return this.ascii;
  }

  /**
   * <p>Getter for startObj.</p>
   * @return byte[]
   **/
  public final byte[] getStartObj() {
    return this.startObj;
  }

  /**
   * <p>Getter for endObjLf.</p>
   * @return byte[]
   **/
  public final byte[] getEndObjLf() {
    return this.endObjLf;
  }

  /**
   * <p>Getter for pdfVersion10.</p>
   * @return String
   **/
  public final String getPdfVersion10() {
    return this.pdfVersion10;
  }

  /**
   * <p>Getter for pdfVersion11.</p>
   * @return String
   **/
  public final String getPdfVersion11() {
    return this.pdfVersion11;
  }

  /**
   * <p>Getter for pdfVersion12.</p>
   * @return String
   **/
  public final String getPdfVersion12() {
    return this.pdfVersion12;
  }

  /**
   * <p>Getter for pdfVersion13.</p>
   * @return String
   **/
  public final String getPdfVersion13() {
    return this.pdfVersion13;
  }

  /**
   * <p>Getter for pdfVersion14.</p>
   * @return String
   **/
  public final String getPdfVersion14() {
    return this.pdfVersion14;
  }

  /**
   * <p>Getter for pdfVersion15.</p>
   * @return String
   **/
  public final String getPdfVersion15() {
    return this.pdfVersion15;
  }

  /**
   * <p>Getter for pdfVersion16.</p>
   * @return String
   **/
  public final String getPdfVersion16() {
    return this.pdfVersion16;
  }

  /**
   * <p>Getter for pdfVersion17.</p>
   * @return String
   **/
  public final String getPdfVersion17() {
    return this.pdfVersion17;
  }

  /**
   * <p>Getter for headBinarySign.</p>
   * @return byte[]
   **/
  public final byte[] getHeadBinarySign() {
    return this.headBinarySign;
  }

  /**
   * <p>Getter for comment.</p>
   * @return byte[]
   **/
  public final byte[] getComment() {
    return this.comment;
  }

  /**
   * <p>Getter for lf.</p>
   * @return byte[]
   **/
  public final byte[] getLf() {
    return this.lf;
  }

  /**
   * <p>Getter for eof.</p>
   * @return byte[]
   **/
  public final byte[] getEof() {
    return this.eof;
  }

  /**
   * <p>Getter for trailerStart.</p>
   * @return byte[]
   **/
  public final byte[] getTrailerStart() {
    return this.trailerStart;
  }

  /**
   * <p>Getter for startxrefLf.</p>
   * @return byte[]
   **/
  public final byte[] getStartxrefLf() {
    return this.startxrefLf;
  }

  /**
   * <p>Getter for xrefLf.</p>
   * @return byte[]
   **/
  public final byte[] getXrefLf() {
    return this.xrefLf;
  }

  /**
   * <p>Getter for xref1EntryLf.</p>
   * @return byte[]
   **/
  public final byte[] getXref1EntryLf() {
    return this.xref1EntryLf;
  }

  /**
   * <p>Getter for escLeParentheses.</p>
   * @return char[]
   **/
  public final char[] getEscLeParentheses() {
    return this.escLeParentheses;
  }

  /**
   * <p>Getter for escRiParentheses.</p>
   * @return char[]
   **/
  public final char[] getEscRiParentheses() {
    return this.escRiParentheses;
  }

  /**
   * <p>Getter for escBackSlash.</p>
   * @return char[]
   **/
  public final char[] getEscBackSlash() {
    return this.escBackSlash;
  }

  /**
   * <p>Getter for endDictLf.</p>
   * @return byte[]
   **/
  public final byte[] getEndDictLf() {
    return this.endDictLf;
  }

  /**
   * <p>Getter for endArrLf.</p>
   * @return byte[]
   **/
  public final byte[] getEndArrLf() {
    return this.endArrLf;
  }

  /**
   * <p>Getter for startStreamLf.</p>
   * @return byte[]
   **/
  public final byte[] getStartStreamLf() {
    return this.startStreamLf;
  }

  /**
   * <p>Getter for endStreamObjLf.</p>
   * @return byte[]
   **/
  public final byte[] getEndStreamObjLf() {
    return this.endStreamObjLf;
  }
  /**
   * <p>Getter for startWarray.</p>
   * @return byte[]
   **/
  public final byte[] getStartWarray() {
    return this.startWarray;
  }
}

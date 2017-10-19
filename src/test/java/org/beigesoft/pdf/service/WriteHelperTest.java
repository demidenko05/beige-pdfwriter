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
import java.util.TimeZone;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * <p>Hex coding tests.
 * </p>
 *
 * @author Yury Demidenko
 */
public class WriteHelperTest {

  PdfWriteHelper writeHelper = new PdfWriteHelper();

  @Test
  public void test1() {
    TimeZone dtz = TimeZone.getDefault();
    TimeZone tz = TimeZone.getTimeZone("GMT+0420");
    TimeZone.setDefault(tz);
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, 2017);
    cal.set(Calendar.MONTH, 0);
    cal.set(Calendar.DAY_OF_MONTH, 30);
    cal.set(Calendar.HOUR_OF_DAY, 17);
    cal.set(Calendar.MINUTE, 3);
    System.out.println(cal.getTime());
    System.out.println(this.writeHelper.dateToString(cal.getTime()));
    assertEquals("D:201701301703+04'20", this.writeHelper.dateToString(cal.getTime()));
    TimeZone.setDefault(dtz);
  }
}

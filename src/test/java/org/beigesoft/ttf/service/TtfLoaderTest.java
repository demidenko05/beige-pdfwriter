package org.beigesoft.ttf.service;

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

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import org.beigesoft.log.LoggerSimple;
import org.beigesoft.ttf.model.Glyph;
import org.beigesoft.ttf.model.TtfFont;
import org.beigesoft.ttf.model.TtfConstants;
import org.beigesoft.ttf.model.TtfCmapSubtable;
import org.beigesoft.ttf.model.TtfTableDirEntry;
import org.beigesoft.pdf.model.ERegisteredTtfFont;
import org.beigesoft.pdf.service.PdfFactory;

/**
 * <p>Load TTF font tests.</p>
 *
 * @author Yury Demidenko
 */
public class TtfLoaderTest {

  LoggerSimple logger;
  
  TtfLoader ttfLoader;

  PdfFactory factory;
  
  public TtfLoaderTest() throws Exception {
    this.logger = new LoggerSimple();
    this.logger.setIsShowDebugMessages(true);
    this.logger.setDetailLevel(5);
    this.factory = new PdfFactory();
    this.factory.setFontDir("/fonts/");
    this.factory.setLogger(this.logger);
    this.factory.init();
    this.ttfLoader = this.factory.lazyGetTtfLoader();
    this.ttfLoader.setLogGtiDelta(5);
    this.ttfLoader.setLogGids(new ArrayList<Integer>());
    this.ttfLoader.getLogGids().add(0);
    this.ttfLoader.getLogGids().add(3);
    this.ttfLoader.getLogGids().add(19);
    this.ttfLoader.getLogGids().add(93);
    this.ttfLoader.getLogGids().add(958);
    this.ttfLoader.getLogGids().add(948);
    this.ttfLoader.getLogGids().add(95);
    this.ttfLoader.getLogGids().add(10516);
    this.ttfLoader.getLogGids().add(11367);
    this.ttfLoader.getLogGids().add(18265);
    this.ttfLoader.getLogGids().add(18266);
    this.ttfLoader.getLogGids().add(18267);
    this.ttfLoader.getLogGids().add(18268);
    this.ttfLoader.getLogGids().add(18269);
    this.ttfLoader.getLogGids().add(18270);
    this.ttfLoader.setLogUnicodes(new char[] {(char) 0x31, (char) 0x32, (char) 0x33}); // 1,2,3
  }

  @Test
  public void test1() throws Exception {
    //test ttf:
    TtfFont ttf = this.ttfLoader.loadFontTtf(ERegisteredTtfFont.DEJAVUSANS.toString());
    assertEquals(this.ttfLoader.getTtfConstants().getScalerTypeMsw(), ttf.getScalerType());
    assertEquals(4 + (4 * 2) + (ttf.getNumTables() * 4 * 4), ttf.getTableDirectory().get(0).getOffset());
    int i = 0;
    assertEquals((char) 3, ttf.getCmap().getUniToCid().get((char) 32).charValue()); //space
    assertEquals((char) 19, ttf.getCmap().getUniToCid().get((char) 0x30).charValue()); //zero
    assertEquals((char) 93, ttf.getCmap().getUniToCid().get((char) 0x7A).charValue()); //z
    assertEquals((char) 957, ttf.getCmap().getUniToCid().get((char) 0x429).charValue()); //Щ mvnrepo
    //assertEquals((char) 958, ttf.getCmap().getUniToCid().get((char) 0x429).charValue()); //Щ new
    assertEquals((char) 947, ttf.getCmap().getUniToCid().get((char) 0x41F).charValue()); //П
    //assertEquals((char) 948, ttf.getCmap().getUniToCid().get((char) 0x41F).charValue()); //П
    assertEquals(0.0, ttf.getPost().getItalicAngle(), 0);
    assertEquals(1303, (int) ttf.getHmtx().getWidths()[19]);
    /*ttf = this.ttfLoader.loadFontTtf(ERegisteredTtfFont.VL_GOTHIC_REGULAR.toString());
    assertEquals(this.ttfLoader.getTtfConstants().getScalerTypeMsw(), ttf.getScalerType());
    assertEquals(4 + (4 * 2) + (ttf.getNumTables() * 4 * 4), ttf.getTableDirectory().get(0).getOffset());
    i = 0;
    assertEquals((char) 5, ttf.getCmap().getUniToCid().get((char) 32).charValue()); //space
    assertEquals((char) 21, ttf.getCmap().getUniToCid().get((char) 0x30).charValue()); //zero
    assertEquals((char) 95, ttf.getCmap().getUniToCid().get((char) 0x7A).charValue()); //z
    assertEquals((char) 10516, ttf.getCmap().getUniToCid().get((char) 0x7B72).charValue()); //筲
    assertEquals((char) 11367, ttf.getCmap().getUniToCid().get((char) 0x812F).charValue()); //脯*/
    //ttf = this.ttfLoader.loadFontTtf(ERegisteredTtfFont.DEJAVUSERIF.toString());
    //ttf = this.ttfLoader.loadFontTtf("SourceHanSans-Regular");
    //ttf = this.ttfLoader.loadFontTtf("NanumGothic");
    //ttf = this.ttfLoader.loadFontTtf("LiberationMono-Regular");
  }
}

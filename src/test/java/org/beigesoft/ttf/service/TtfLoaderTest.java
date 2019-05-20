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

package org.beigesoft.ttf.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import org.beigesoft.log.LogSmp;
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

  LogSmp logger;
  
  TtfLoader ttfLoader;

  PdfFactory factory;
  
  public TtfLoaderTest() throws Exception {
    this.logger = new LogSmp();
    logger.setDbgSh(true);
    logger.setDbgFl(4000);
    logger.setDbgCl(4999);
    this.factory = new PdfFactory();
    this.factory.setLog(this.logger);
    this.factory.init();
    this.ttfLoader = this.factory.lazyGetTtfLoader();
    this.ttfLoader.setLogGtiDelta(5);
    this.ttfLoader.setLogGids(new LinkedHashSet<Integer>());
    this.ttfLoader.getLogGids().add(0);
    this.ttfLoader.getLogGids().add(1);
    this.ttfLoader.getLogGids().add(2);
    this.ttfLoader.getLogGids().add(3);
    this.ttfLoader.getLogGids().add(4);
    this.ttfLoader.getLogGids().add(5);
    this.ttfLoader.getLogGids().add(6);
    this.ttfLoader.getLogGids().add(7);
    this.ttfLoader.getLogGids().add(8);
    this.ttfLoader.getLogGids().add(9);
    this.ttfLoader.getLogGids().add(10);
    this.ttfLoader.getLogGids().add(15);
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
    this.ttfLoader.setLogUnicodes(new LinkedHashSet<Character>());
    this.ttfLoader.getLogUnicodes().add((char) 0x31);// 1,2,3
    this.ttfLoader.getLogUnicodes().add((char) 0x32);
    this.ttfLoader.getLogUnicodes().add((char) 0x33);
  }

  @Test
  public void test1() throws Exception {
    //test ttf:
    TtfFont ttf = this.ttfLoader.loadFontTtf(ERegisteredTtfFont.DEJAVUSANS.toString(),
      this.factory.getFontDir() + ERegisteredTtfFont.DEJAVUSANS.toString() + ".ttf", this.factory.lazyGetTtfResourceStreamer());
    assertEquals(this.ttfLoader.getTtfConstants().getScalerTypeMsw(), ttf.getScalerType());
    assertEquals(4 + (4 * 2) + (ttf.getNumTables() * 4 * 4), ttf.getTableDirectory().get(0).getOffset());
    int i = 0;
    assertEquals((char) 3, ttf.getCmap().getUniToCid().get((char) 32).charValue()); //space
    assertEquals((char) 19, ttf.getCmap().getUniToCid().get((char) 0x30).charValue()); //zero
    assertEquals((char) 93, ttf.getCmap().getUniToCid().get((char) 0x7A).charValue()); //z
    //assertEquals((char) 957, ttf.getCmap().getUniToCid().get((char) 0x429).charValue()); //Щ mvnrepo
    assertEquals((char) 958, ttf.getCmap().getUniToCid().get((char) 0x429).charValue()); //Щ new
    //assertEquals((char) 947, ttf.getCmap().getUniToCid().get((char) 0x41F).charValue()); //П
    assertEquals((char) 948, ttf.getCmap().getUniToCid().get((char) 0x41F).charValue()); //П
    assertEquals(0.0, ttf.getPost().getItalicAngle(), 0);
    assertEquals(1303, (int) ttf.getHmtx().getWidths()[19]);
    ttf = this.ttfLoader.loadFontTtf(ERegisteredTtfFont.DEJAVUSERIF.toString(),
      this.factory.getFontDir() + ERegisteredTtfFont.DEJAVUSERIF.toString() + ".ttf", this.factory.lazyGetTtfResourceStreamer());
    ttf = this.ttfLoader.loadFontTtf(ERegisteredTtfFont.DEJAVUSERIF_BOLD.toString(),
      this.factory.getFontDir() + ERegisteredTtfFont.DEJAVUSERIF_BOLD.toString() + ".ttf", this.factory.lazyGetTtfResourceStreamer());
    ttf = this.ttfLoader.loadFontTtf("LiberationMono-Regular",
      this.factory.getFontDir() + "LiberationMono-Regular.ttf", this.factory.lazyGetTtfResourceStreamer());
    ttf = this.ttfLoader.loadFontTtf("LiberationSerif-Regular",
      this.factory.getFontDir() + "LiberationSerif-Regular.ttf", this.factory.lazyGetTtfResourceStreamer());
    /*ttf = this.ttfLoader.loadFontTtf("DejaVuSansMono",
      this.factory.getFontDir() + "DejaVuSansMono.ttf", this.factory.lazyGetTtfResourceStreamer());
    ttf = this.ttfLoader.loadFontTtf(ERegisteredTtfFont.VL_GOTHIC_REGULAR.toString(),
      this.factory.getFontDir() + ERegisteredTtfFont.VL_GOTHIC_REGULAR.toString() + ".ttf", this.factory.lazyGetTtfResourceStreamer());
    assertEquals(this.ttfLoader.getTtfConstants().getScalerTypeMsw(), ttf.getScalerType());
    assertEquals(4 + (4 * 2) + (ttf.getNumTables() * 4 * 4), ttf.getTableDirectory().get(0).getOffset());
    i = 0;*/
    //assertEquals((char) 5, ttf.getCmap().getUniToCid().get((char) 32).charValue()); //space
    //assertEquals((char) 21, ttf.getCmap().getUniToCid().get((char) 0x30).charValue()); //zero
    //assertEquals((char) 95, ttf.getCmap().getUniToCid().get((char) 0x7A).charValue()); //z
    //assertEquals((char) 10516, ttf.getCmap().getUniToCid().get((char) 0x7B72).charValue()); //筲
    //assertEquals((char) 11367, ttf.getCmap().getUniToCid().get((char) 0x812F).charValue()); //脯
    /*ttf = this.ttfLoader.loadFontTtf(ERegisteredTtfFont.DEJAVUSERIF.toString(),
      this.factory.getFontDir() + ERegisteredTtfFont.DEJAVUSERIF.toString() + ".ttf", this.factory.lazyGetTtfResourceStreamer());
    ttf = this.ttfLoader.loadFontTtf("SourceHanSans-Regular",
      this.factory.getFontDir() + "SourceHanSans-Regular.ttf", this.factory.lazyGetTtfResourceStreamer());
    ttf = this.ttfLoader.loadFontTtf("NanumGothic",
      this.factory.getFontDir() + "NanumGothic.ttf", this.factory.lazyGetTtfResourceStreamer());*/
  }
}

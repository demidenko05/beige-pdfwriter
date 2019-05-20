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
 * <p>Test old liberation-fonts-ttf-1.07.4 LiberationMono-Regular.</p>
 *
 * @author Yury Demidenko
 */
public class LiberLoaderTest {

  LogSmp logger;
  
  TtfLoader ttfLoader;

  PdfFactory factory;
  
  public LiberLoaderTest() throws Exception {
    this.logger = new LogSmp();
    logger.setDbgSh(true);
    logger.setDbgFl(4000);
    logger.setDbgCl(4999);
    this.factory = new PdfFactory();
    this.factory.setLog(this.logger);
    this.factory.init();
    this.ttfLoader = this.factory.lazyGetTtfLoader();
    this.ttfLoader.setIsCacheGlyf(true);
    this.ttfLoader.setLogGtiDelta(5);
    this.ttfLoader.setLogGids(new LinkedHashSet<Integer>());
    this.ttfLoader.getLogGids().add(0);
    this.ttfLoader.getLogGids().add(1);
    this.ttfLoader.getLogGids().add(2);
    this.ttfLoader.getLogGids().add(3);
    this.ttfLoader.getLogGids().add(4);
    this.ttfLoader.getLogGids().add(5);
    this.ttfLoader.getLogGids().add(492);
    this.ttfLoader.getLogGids().add(493);
    this.ttfLoader.getLogGids().add(494);
    this.ttfLoader.getLogGids().add(495);
    this.ttfLoader.getLogGids().add(496);
    this.ttfLoader.getLogGids().add(497);
    this.ttfLoader.getLogGids().add(498);
    this.ttfLoader.getLogGids().add(499);
    this.ttfLoader.getLogGids().add(500);
    this.ttfLoader.setLogUnicodes(new LinkedHashSet<Character>());
    this.ttfLoader.getLogUnicodes().add((char) 21);
    this.ttfLoader.getLogUnicodes().add((char) 22);
    this.ttfLoader.getLogUnicodes().add((char) 453);
    this.ttfLoader.getLogUnicodes().add((char) 454);
    this.ttfLoader.getLogUnicodes().add((char) 455);
  }

  @Test
  public void test1() throws Exception {
    boolean wasErr = false;
    TtfFont ttf = new TtfFont();
    String fnm = "LiberationMono-Regular";
    String path = this.factory.getFontDir() + "LiberationMono-Regular.ttf";
    ttf.setFileName(fnm);
    this.logger.info(null, TtfLoader.class, "Loading font " + fnm);
    TtfInputStream is = null;
    try {
      is = this.factory.lazyGetTtfResourceStreamer().makeInputStream(path);
      this.ttfLoader.loadFontTtfFrom(ttf, is);
/*2017-10-23T09:43:41.845+0300 DEBUG TtfLoader - Added loca 16 bit, size = 676, g0=0, g1=44, g2=44, g3=44, g4=44, g5=84, g6=126
2017-10-23T09:43:41.845+0300 DEBUG TtfLoader - Glyf copied into buffer Length: 92368
2017-10-23T09:43:41.846+0300 DEBUG TtfLoader - Go to gid glyph gid/gid offset/tblOffset/isOffset: 0/0/8488/8488
2017-10-23T09:43:41.846+0300 DEBUG TtfLoader - Added simple glyph, gid/contours/xMin/yMin/xMax/offset/length: 0/2/68/0/612/0/44
2017-10-23T09:43:41.846+0300 DEBUG TtfLoader - Go to gid glyph gid/gid offset/tblOffset/isOffset: 4/44/8488/8498
2017-10-23T09:43:41.846+0300 DEBUG TtfLoader - Added compound glyph, gid/parts size/xMin/yMin/xMax/yMax/offset/length: 4/2/1052/-4814/-19961/1565/44/40
2017-10-23T09:43:41.846+0300 DEBUG TtfLoader - Go to gid glyph gid/gid offset/tblOffset/isOffset: 5/84/8488/25970
java.io.IOException: This offset has passed already requested/current: 8572/25970*/
    } finally {
      if (is != null) {
        is.close();
      }
    }
    wasErr = (this.ttfLoader.getWrongGti() != -1);
    this.ttfLoader.setWrongGti(-1);
    ttf.getGlyf().getBufferInputStream().close();
    this.ttfLoader.loadGlyf4Aligned(ttf, ttf.getGlyf().getBufferInputStream());
/*2017-10-23T10:14:21.180+0300 DEBUG TtfLoader - Added simple glyph, gti/contours/xMin/yMin/xMax/offset/length: 0/2/68/0/612/0/88
2017-10-23T10:14:21.180+0300 DEBUG TtfLoader - simple glyph gti + delta/numContours/points/delta/instrLen 6/2/8/5/41
2017-10-23T10:14:21.180+0300 DEBUG TtfLoader - Added simple glyph, gti/contours/xMin/yMin/xMax/offset/length: 1/2/515/0/713/88/80
2017-10-23T10:14:21.182+0300 DEBUG TtfLoader - Added glyf, total: 672*/
// so 16 bit loca WRONG gid 0 offs 0 len 88, gid 5 off 88 len 80 (multiply to 2)

// another problem g497 offset wrong 200 but actually is 65736 (65536+offset):
/*2017-10-23T10:41:43.565+0300 DEBUG TtfLoader - Added loca 16 bit, size = 676, g0=0, g1=88, g2=88, g3=88, g4=88, g5=168,
 *g495=65420, g496=65468, g497=200, g498=216, g499=252, g500=300*/
    assertTrue(!wasErr);
  }
}

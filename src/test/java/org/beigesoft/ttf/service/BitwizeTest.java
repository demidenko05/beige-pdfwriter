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

import java.nio.charset.Charset;
import java.util.Map;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import org.beigesoft.log.LogSmp;
import org.beigesoft.ttf.model.TtfTableDirEntry;

/**
 * <p>Bitwize tests.</p>
 *
 * @author Yury Demidenko
 */
public class BitwizeTest {

  /**
   * <p>ASCII charset.</p>
   **/
  private final Charset ascii = Charset.forName("ISO-8859-1");

  LogSmp logger;

  TtfCompactFontMaker compactFontMaker;
  
  public BitwizeTest() {
    this.logger = new LogSmp();
    logger.setDbgSh(true);
    logger.setDbgFl(4000);
    logger.setDbgCl(4999);
    this.compactFontMaker = new TtfCompactFontMaker();
    this.compactFontMaker.setLog(this.logger);
  }

  @Test
  public void testBufStr() throws Exception {
    byte[] buffer = new byte[] {(byte) 0x12, (byte) 0xFF, (byte) 0xBF, (byte) 0xFA};
    TtfBufferInputStream tbis = new TtfBufferInputStream(buffer, 10);
    tbis.goAhead(10L);
    assertEquals(10L, tbis.getOffset());
    int byte0 = tbis.read();
    assertEquals(0x00000012, byte0);
    int byteff = tbis.read();
    assertEquals(0x000000FF, byteff);
    tbis.read();
    tbis.read();
    boolean wasEx = true;
    try {
      tbis.read();
      wasEx = false;
    } catch (Exception e) {
      assertTrue(wasEx);
    }
    tbis.setCurrentOffset(0L);
    short sint161 = tbis.readSInt16();
    assertEquals((short) 0x000012FF, sint161);
    int uint161 = tbis.readUInt16();
    assertEquals(0x0000BFFA, uint161);
    tbis.setCurrentOffset(0L);
    tbis.skip(2);
    uint161 = tbis.readUInt16();
    assertEquals(0x0000BFFA, uint161);
    tbis.setCurrentOffset(0L);
    long intd = tbis.readUInt32();
    assertEquals(0x0000000012FFBFFAL, intd);
  }

  @Test
  public void test1() throws Exception {
    //test bitwise:
    char chMax = (char) 0xffff;
    int intChMax = (int) chMax;
    assertEquals(0xffff, intChMax);
    long lbMax =   0xff00000000000000L;
    long lByteMax = 0x00000000000000ffL;
    long lFbMax = lByteMax << 56;
    assertEquals(lbMax, lFbMax);
    this.logger.info(null, BitwizeTest.class, "lbMax 0xff00000000000000 = " + lbMax);
    this.logger.info(null, BitwizeTest.class, "lByteMax 0x00000000000000ff = " + lByteMax);
    this.logger.info(null, BitwizeTest.class, "lFbMax = byteMax << 56 = " + lFbMax);
    byte bFA = (byte) 0xFA;
    int iFbFA = bFA;
    iFbFA &= 0x000000FF;
    assertEquals(0x000000FA, iFbFA);
    byte bF1 = (byte) 0xF1;
    int iFbF1 = bF1;
    iFbF1 &= 0x000000FF;
    assertEquals(0x000000F1, iFbF1);
    byte bMax = (byte) 0xFF;
    int iBmax = 0x000000FF;
    int iFbMax = bMax;
    assertEquals(-1, iFbMax);
    iFbMax &= iBmax;
    assertEquals(iBmax, iFbMax);
    byte bFiBmax = (byte) iBmax;
    assertEquals(bMax, bFiBmax);
    this.logger.info(null, BitwizeTest.class, "byte bMax = 0xFF : " + bMax);
    this.logger.info(null, BitwizeTest.class, "int iBmax = 0x000000FF : " + iBmax);
    this.logger.info(null, BitwizeTest.class, "byte bFiBmax = (byte) iBmax : " + bFiBmax);
    short shMax = (short) 0xFFFF;
    int iShMax = 0x0000FFFF;
    assertEquals(0xFF, ((char) shMax) >>> 8);
    assertTrue("0xFF != 0x0000FFFF >> 8" + (shMax >> 8), 0xFF != shMax >> 8);
    short shFiShMax = (short) iShMax;
    assertEquals(shMax, shFiShMax);
    this.logger.info(null, BitwizeTest.class, "short shMax = (short) 0xFFFF : " + shMax);
    this.logger.info(null, BitwizeTest.class, "int iShMax = 0x0000FFFF : " + iShMax);
    this.logger.info(null, BitwizeTest.class, "short shFiShMax = (short) iShMax : " + shFiShMax);
     //compact storing of 32bit adresses uint32 (it's impossible without odd bitwise manipulation):
    long lMaxUint32 = 0xFFFFFFFFl;
    int iMaxUint32 = (int) lMaxUint32;
    long lFiMaxUint32 = (long) iMaxUint32;
    long lFiMaxUint32bw = 0x0l | iMaxUint32;
    this.logger.info(null, BitwizeTest.class, "long lMaxUint32 = 0xFFFFFFFFl : " + lMaxUint32);
    this.logger.info(null, BitwizeTest.class, "int iMaxUint32 = (int) lMaxUint32 : " + iMaxUint32);
    this.logger.info(null, BitwizeTest.class, "long lFiMaxUint32 = (long) iMaxUint32 : " + lFiMaxUint32);
    this.logger.info(null, BitwizeTest.class, "long lFiMaxUint32bw = 0x0l | iMaxUint32 : " + lFiMaxUint32bw);
    //test ttf reader/writer:
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    TtfOutputStream os = new TtfOutputStream(baos);
    long ofstOus = 0;
    TtfTableDirEntry locaTde = new TtfTableDirEntry();
    locaTde.setOffset(1589653L);
    locaTde.setChecksum(1628239622L);
    locaTde.setLength(19L);
    locaTde.setTagString("loca");
    locaTde.setTag(locaTde.getTagString().getBytes(this.ascii));
    os.writeByteArr(locaTde.getTag());
    locaTde.setChecksumIdx((int) os.getSize());
    os.writeZeroBytes(4);
    os.writeUInt32(locaTde.getOffset());
    os.writeUInt32(locaTde.getLength());
    short shv = -14;
    os.writeSInt16(shv, null, null);
    byte[] locaTdeBytes = baos.toByteArray();
    this.compactFontMaker.fixTde(locaTdeBytes, locaTde);
    ByteArrayInputStream bais = new ByteArrayInputStream (locaTdeBytes);
    TtfInputStream is = new TtfInputStream(bais);
    assertArrayEquals(locaTde.getTag(), is.readTag());
    assertEquals(locaTde.getChecksum(), is.readUInt32());
    assertEquals(locaTde.getOffset(), is.readUInt32());
    assertEquals(locaTde.getLength(), is.readUInt32());
    assertEquals(shv, is.readSInt16());
    long[] zeroLongChksum = new long[] {-1, -1, -1, -1};
    long[] currLongChksum = new long[] {-1, -1, -1, -1};
    TtfTableDirEntry chksumTde = new TtfTableDirEntry();
    os.addToChecksum(chksumTde, currLongChksum, 0);
    assertEquals(0L, currLongChksum[0]);
    os.addToChecksum(chksumTde, currLongChksum, 0);
    os.addToChecksum(chksumTde, currLongChksum, 0);
    os.addToChecksum(chksumTde, currLongChksum, 1);
    assertArrayEquals(zeroLongChksum, currLongChksum);
    assertEquals(1L, chksumTde.getChecksum());
    os.addToChecksum(chksumTde, currLongChksum, 0);
    os.addToChecksum(chksumTde, currLongChksum, 0);
    os.addToChecksum(chksumTde, currLongChksum, 0);
    os.addToChecksum(chksumTde, currLongChksum, 25);
    assertArrayEquals(zeroLongChksum, currLongChksum);
    assertEquals(26L, chksumTde.getChecksum());
  }
}

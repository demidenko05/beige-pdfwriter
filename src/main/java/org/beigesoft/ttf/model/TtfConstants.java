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

package org.beigesoft.ttf.model;

import java.nio.charset.Charset;

/**
 * <p>TTF font's memory friendly (non-static) constants.</p>
 *
 * @author Yury Demidenko
 */
public class TtfConstants {
  /**
   * <p>File charset.</p>
   **/
  private final Charset charset = Charset.forName("ISO-8859-1");

  /**
   * <p>Scaler type 'OTTO' OTF + PostScript outlines
   * that is, a 'CFF ' table instead of a 'glyf' table.</p>
   **/
  private final long scalerTypeOtto = 0x4F54544F;

  /**
   * <p>Scaler type 'typ1'.</p>
   **/
  private final long scalerTypeTyp1 = 0x74797031;

  /**
   * <p>Scaler type MS Windows.</p>
   **/
  private final long scalerTypeMsw = 0x00010000;

  /**
   * <p>Scaler type 'true' OSx IOs.</p>
   **/
  private final long scalerTypeOsxIos = 0x74727565;

  //required tables:
  /**
   * <p>"cmap" character to glyph mapping.</p>
   **/
  private final String tagCmap = "cmap";

  /**
   * <p>"glyf" glyph data.</p>
   **/
  private final String tagGlyf = "glyf";

  /**
   * <p>"head" font header.</p>
   **/
  private final String tagHead = "head";

  /**
   * <p>"hhea" horizontal header.</p>
   **/
  private final String tagHhea = "hhea";

  /**
   * <p>"hmtx" horizontal metrics.</p>
   **/
  private final String tagHmtx = "hmtx";

  /**
   * <p>"loca" index to location.</p>
   **/
  private final String tagLoca = "loca";

  /**
   * <p>"maxp" maximum profile.</p>
   **/
  private final String tagMaxp = "maxp";

  /**
   * <p>"name" naming.</p>
   **/
  private final String tagName = "name";

  /**
   * <p>"post" PostScript.</p>
   **/
  private final String tagPost = "post";

  //optional 1:
  /**
   * <p>"cvt " control value.</p>
   **/
  private final String tagCvt = "cvt ";

  /**
   * <p>"fpgm" font program.</p>
   **/
  private final String tagFpgm = "fpgm";

  /**
   * <p>"hdmx" horizontal device metrics.</p>
   **/
  private final String tagHdmx = "hdmx";

  /**
   * <p>"kern" kerning.</p>
   **/
  private final String tagKern = "kern";

  /**
   * <p>"OS/2" consists of a set of metrics that are required
   * by and Windows. It is not used by OS X.</p>
   **/
  private final String tagOs2 = "OS/2";

  /**
   * <p>"prep" control value program.</p>
   **/
  private final String tagPrep = "prep";

  //optional #2
  /**
   * <p>"acnt" accent attachment table.</p>
   **/
  private final String tagAcnt = "acnt";

  /**
   * <p>"ankr" anchor point table.</p>
   **/
  private final String tagAnkr = "ankr";

  /**
   * <p>"avar" Axis Variation table.</p>
   **/
  private final String tagAvar = "avar";

  /**
   * <p>"bdat" bitmap data table.</p>
   **/
  private final String tagBdat = "bdat";

  /**
   * <p>"bhed" global information about a bitmap font.</p>
   **/
  private final String tagBhed = "bhed";

  /**
   * <p>"bloc" bitmap location table.</p>
   **/
  private final String tagBloc = "bloc";

  /**
   * <p>"bsln" baseline table.</p>
   **/
  private final String tagBsln = "bsln";

  /**
   * <p>"cvar" CVT variations table.</p>
   **/
  private final String tagCvar = "cvar";

  /**
   * <p>"EBSC" provides a mechanism for forcing the TrueType scaler
   * to use a particular size of embedded bitmap when generating glyphs
   * for a different point size.</p>
   **/
  private final String tagEbsc = "EBSC";

  /**
   * <p>"fdsc" font descriptors table.</p>
   **/
  private final String tagFdsc = "fdsc";

  /**
   * <p>"feat" feature name table.</p>
   **/
  private final String tagFeat = "feat";

  /**
   * <p>"fmtx" Font Metrics Table.</p>
   **/
  private final String tagFmtx = "fmtx";

  /**
   * <p>"fond".</p>
   **/
  private final String tagFond = "fond";

  /**
   * <p>"fvar" Apple Advanced Typography variations.</p>
   **/
  private final String tagFvar = "fvar";

  /**
   * <p>"gasp" describes the preferred rasterization techniques
   * for the typeface when it is rendered on grayscale-capable devices.</p>
   **/
  private final String tagGasp = "gasp";

  /**
   * <p>"gcid" data to map characters in the font to CIDs.</p>
   **/
  private final String tagGcid = "gcid";

  /**
   * <p>"gvar" Apple Advanced Typography style variations.</p>
   **/
  private final String tagGvar = "gvar";

  /**
   * <p>"just" justification table.</p>
   **/
  private final String tagJust = "just";

  /**
   * <p>"kerx" contains the values that adjust the positioning of glyphs
   * in a font. Old kern table.</p>
   **/
  private final String tagKerx = "kerx";

  /**
   * <p>"lcar" ligature caret table.</p>
   **/
  private final String tagLcar = "lcar";

  /**
   * <p>"ltag" .</p>
   **/
  private final String tagLtag = "ltag";

  /**
   * <p>"meta" metadata for the font.</p>
   **/
  private final String tagMeta = "meta";

  /**
   * <p>"mort" deprecated glyph metamorphosis table.</p>
   **/
  private final String tagMort = "mort";

  /**
   * <p>"morx" extended glyph metamorphosis table.</p>
   **/
  private final String tagMorx = "morx";

  /**
   * <p>"opbd" optical bounds table.</p>
   **/
  private final String tagOpbd = "opbd";

  /**
   * <p>"prop" glyph properties table.</p>
   **/
  private final String tagProp = "prop";

  /**
   * <p>"sbix" provides access to bitmap data in
   * a standard graphics format (such as PNG, JPEG, TIFF).</p>
   **/
  private final String tagSbix = "sbix";

  /**
   * <p>"trak" tracking table.</p>
   **/
  private final String tagTrak = "trak";

  /**
   * <p>"vhea" vertical header table.</p>
   **/
  private final String tagVhea = "vhea";

  /**
   * <p>"vmtx" vertical metrics table.</p>
   **/
  private final String tagVmtx = "vmtx";

  /**
   * <p>"xref" cross-reference table
   * is used by Apple's font tools ftxdumperfuser and ftxenhancer.</p>
   **/
  private final String tagXref = "xref";

  /**
   * <p>"Zapf" contains information about the individual glyphs.</p>
   **/
  private final String tagZapf = "Zapf";

  //Simple getters and setters:
  /**
   * <p>Getter for file charset.</p>
   * @return Charset
   **/
  public final Charset getCharset() {
    return this.charset;
  }

  /**
   * <p>Getter for scalerTypeOtto.</p>
   * @return long
   **/
  public final long getScalerTypeOtto() {
    return this.scalerTypeOtto;
  }

  /**
   * <p>Getter for scalerTypeTyp1.</p>
   * @return long
   **/
  public final long getScalerTypeTyp1() {
    return this.scalerTypeTyp1;
  }

  /**
   * <p>Getter for scalerTypeMsw.</p>
   * @return long
   **/
  public final long getScalerTypeMsw() {
    return this.scalerTypeMsw;
  }

  /**
   * <p>Getter for scalerTypeOsxIos.</p>
   * @return long
   **/
  public final long getScalerTypeOsxIos() {
    return this.scalerTypeOsxIos;
  }

  /**
   * <p>Getter for tagCmap.</p>
   * @return String
   **/
  public final String getTagCmap() {
    return this.tagCmap;
  }

  /**
   * <p>Getter for tagGlyf.</p>
   * @return String
   **/
  public final String getTagGlyf() {
    return this.tagGlyf;
  }

  /**
   * <p>Getter for tagHead.</p>
   * @return String
   **/
  public final String getTagHead() {
    return this.tagHead;
  }

  /**
   * <p>Getter for tagHhea.</p>
   * @return String
   **/
  public final String getTagHhea() {
    return this.tagHhea;
  }

  /**
   * <p>Getter for tagHmtx.</p>
   * @return String
   **/
  public final String getTagHmtx() {
    return this.tagHmtx;
  }

  /**
   * <p>Getter for tagLoca.</p>
   * @return String
   **/
  public final String getTagLoca() {
    return this.tagLoca;
  }

  /**
   * <p>Getter for tagMaxp.</p>
   * @return String
   **/
  public final String getTagMaxp() {
    return this.tagMaxp;
  }

  /**
   * <p>Getter for tagName.</p>
   * @return String
   **/
  public final String getTagName() {
    return this.tagName;
  }

  /**
   * <p>Getter for tagPost.</p>
   * @return String
   **/
  public final String getTagPost() {
    return this.tagPost;
  }

  /**
   * <p>Getter for tagCvt.</p>
   * @return String
   **/
  public final String getTagCvt() {
    return this.tagCvt;
  }

  /**
   * <p>Getter for tagHdmx.</p>
   * @return String
   **/
  public final String getTagHdmx() {
    return this.tagHdmx;
  }

  /**
   * <p>Getter for tagKern.</p>
   * @return String
   **/
  public final String getTagKern() {
    return this.tagKern;
  }

  /**
   * <p>Getter for tagOs2.</p>
   * @return String
   **/
  public final String getTagOs2() {
    return this.tagOs2;
  }

  /**
   * <p>Getter for tagPrep.</p>
   * @return String
   **/
  public final String getTagPrep() {
    return this.tagPrep;
  }

  /**
   * <p>Getter for tagAcnt.</p>
   * @return String
   **/
  public final String getTagAcnt() {
    return this.tagAcnt;
  }

  /**
   * <p>Getter for tagAnkr.</p>
   * @return String
   **/
  public final String getTagAnkr() {
    return this.tagAnkr;
  }

  /**
   * <p>Getter for tagAvar.</p>
   * @return String
   **/
  public final String getTagAvar() {
    return this.tagAvar;
  }

  /**
   * <p>Getter for tagBdat.</p>
   * @return String
   **/
  public final String getTagBdat() {
    return this.tagBdat;
  }

  /**
   * <p>Getter for tagBhed.</p>
   * @return String
   **/
  public final String getTagBhed() {
    return this.tagBhed;
  }

  /**
   * <p>Getter for tagBloc.</p>
   * @return String
   **/
  public final String getTagBloc() {
    return this.tagBloc;
  }

  /**
   * <p>Getter for tagBsln.</p>
   * @return String
   **/
  public final String getTagBsln() {
    return this.tagBsln;
  }

  /**
   * <p>Getter for tagCvar.</p>
   * @return String
   **/
  public final String getTagCvar() {
    return this.tagCvar;
  }

  /**
   * <p>Getter for tagEbsc.</p>
   * @return String
   **/
  public final String getTagEbsc() {
    return this.tagEbsc;
  }

  /**
   * <p>Getter for tagFdsc.</p>
   * @return String
   **/
  public final String getTagFdsc() {
    return this.tagFdsc;
  }

  /**
   * <p>Getter for tagFeat.</p>
   * @return String
   **/
  public final String getTagFeat() {
    return this.tagFeat;
  }

  /**
   * <p>Getter for tagFmtx.</p>
   * @return String
   **/
  public final String getTagFmtx() {
    return this.tagFmtx;
  }

  /**
   * <p>Getter for tagFond.</p>
   * @return String
   **/
  public final String getTagFond() {
    return this.tagFond;
  }

  /**
   * <p>Getter for tagFpgm.</p>
   * @return String
   **/
  public final String getTagFpgm() {
    return this.tagFpgm;
  }

  /**
   * <p>Getter for tagFvar.</p>
   * @return String
   **/
  public final String getTagFvar() {
    return this.tagFvar;
  }

  /**
   * <p>Getter for tagGasp.</p>
   * @return String
   **/
  public final String getTagGasp() {
    return this.tagGasp;
  }

  /**
   * <p>Getter for tagGcid.</p>
   * @return String
   **/
  public final String getTagGcid() {
    return this.tagGcid;
  }

  /**
   * <p>Getter for tagGvar.</p>
   * @return String
   **/
  public final String getTagGvar() {
    return this.tagGvar;
  }

  /**
   * <p>Getter for tagJust.</p>
   * @return String
   **/
  public final String getTagJust() {
    return this.tagJust;
  }

  /**
   * <p>Getter for tagKerx.</p>
   * @return String
   **/
  public final String getTagKerx() {
    return this.tagKerx;
  }

  /**
   * <p>Getter for tagLcar.</p>
   * @return String
   **/
  public final String getTagLcar() {
    return this.tagLcar;
  }

  /**
   * <p>Getter for tagLtag.</p>
   * @return String
   **/
  public final String getTagLtag() {
    return this.tagLtag;
  }

  /**
   * <p>Getter for tagMeta.</p>
   * @return String
   **/
  public final String getTagMeta() {
    return this.tagMeta;
  }

  /**
   * <p>Getter for tagMort.</p>
   * @return String
   **/
  public final String getTagMort() {
    return this.tagMort;
  }

  /**
   * <p>Getter for tagMorx.</p>
   * @return String
   **/
  public final String getTagMorx() {
    return this.tagMorx;
  }

  /**
   * <p>Getter for tagOpbd.</p>
   * @return String
   **/
  public final String getTagOpbd() {
    return this.tagOpbd;
  }

  /**
   * <p>Getter for tagProp.</p>
   * @return String
   **/
  public final String getTagProp() {
    return this.tagProp;
  }

  /**
   * <p>Getter for tagSbix.</p>
   * @return String
   **/
  public final String getTagSbix() {
    return this.tagSbix;
  }

  /**
   * <p>Getter for tagTrak.</p>
   * @return String
   **/
  public final String getTagTrak() {
    return this.tagTrak;
  }

  /**
   * <p>Getter for tagVhea.</p>
   * @return String
   **/
  public final String getTagVhea() {
    return this.tagVhea;
  }

  /**
   * <p>Getter for tagVmtx.</p>
   * @return String
   **/
  public final String getTagVmtx() {
    return this.tagVmtx;
  }

  /**
   * <p>Getter for tagXref.</p>
   * @return String
   **/
  public final String getTagXref() {
    return this.tagXref;
  }

  /**
   * <p>Getter for tagZapf.</p>
   * @return String
   **/
  public final String getTagZapf() {
    return this.tagZapf;
  }
}

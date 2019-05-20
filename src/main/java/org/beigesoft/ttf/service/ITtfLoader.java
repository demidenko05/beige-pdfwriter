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

import org.beigesoft.ttf.model.TtfFont;

/**
 * <p>Load TTF data from resource file or cache.
 * It's usually used a TTF font (for native language).
 * Any TTF loaded once. This data is used for embedding font into PDF
 * in compact mode (only used glyphs) and fast (checksums already computed).
 * This data actually must not be modified any farther.
 * Loading different TTF must not be parallel cause of using class variables
 * to resolve loading problems, i.e. if "glyf" table
 * consist of not 4bytes aligned glyths and loca is after glyf.
 * Use this loader trough PdfFactory. That factory ensure properly usage
 * of this loader, i.e. lazy initializing of TTF and other
 * multi-threading aspects. Chars widths in TtfHmtx are not scaled.
 * </p>
 *
 * @author Yury Demidenko
 */
public interface ITtfLoader {

  /**
   * <p>Load TTF font from resource/file.</p>
   * @param pName font name
   * @param pPath path
   * @param pStreamer stream maker
   * @return loaded TTF font
   * @throws Exception an Exception
   **/
  TtfFont loadFontTtf(String pName, String pPath,
    ITtfSourceStreamer pStreamer) throws Exception;
}

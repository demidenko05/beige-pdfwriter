package org.beigesoft.ttf.service;

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

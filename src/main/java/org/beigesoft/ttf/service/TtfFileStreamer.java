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

import java.io.File;
import java.io.FileInputStream;

/**
 * <p>Service that check if TTF file exists
 * in file system and make InputStream.</p>
 *
 * @author Yury Demidenko
 */
public class TtfFileStreamer implements ITtfSourceStreamer  {

  /**
   * <p>Check if file exists.</p>
   * @param pPath string of file name/path
   * @return if exists
   * @throws Exception an Exception
   **/
  @Override
  public final boolean isExists(final String pPath) throws Exception {
    File file = new File(pPath);
    return file.exists();
  }

  /**
   * <p>Make TtfInputStream from file.</p>
   * @param pPath string of file name/path
   * @return TtfInputStream
   * @throws Exception an Exception
   **/
  @Override
  public final TtfInputStream makeInputStream(
    final String pPath) throws Exception {
    File file = new File(pPath);
    return new TtfInputStream(new FileInputStream(file));
  }
}

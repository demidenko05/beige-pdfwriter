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

/**
 * <p>Abstraction of service that check if TTF file exists
 * in class-path, file-system or somewhere else, and make InputStream.</p>
 *
 * @author Yury Demidenko
 */
public interface ITtfSourceStreamer  {

  /**
   * <p>Check if file exists.</p>
   * @param pPath string of file name/path
   * @return if exists
   * @throws Exception an Exception
   **/
  boolean isExists(String pPath) throws Exception;

  /**
   * <p>Make InputStream from file.</p>
   * @param pPath string of file name/path
   * @return TtfInputStream
   * @throws Exception an Exception
   **/
  TtfInputStream makeInputStream(String pPath) throws Exception;
}

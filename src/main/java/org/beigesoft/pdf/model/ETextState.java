package org.beigesoft.pdf.model;

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
 * <p>Text state constants.</p>
 *
 * @author Yury Demidenko
 */
public enum ETextState {

  /**
   * <p>There is BT.</p>
   **/
  STARTED,

  /**
   * <p>There is no BT.</p>
   **/
  ENDED;
}

package org.beigesoft.ttf.model;

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

import java.util.Comparator;

/**
 * <p>TTF table directory entry comparator by offset.</p>
 *
 * @author Yury Demidenko
 */
public class CmpTableDirEntryOffset implements Comparator<TtfTableDirEntry> {

  @Override
  public final int compare(final TtfTableDirEntry pVal1,
    final TtfTableDirEntry pVal2) {
    return Long.compare(pVal1.getOffset(), pVal2.getOffset());
  }
}

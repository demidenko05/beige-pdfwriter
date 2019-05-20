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

/**
 * <p>TTF table required for font embedding model
 * that fully buffered and copied without changes.</p>
 *
 * @author Yury Demidenko
 */
public class TableForEmbeddingBf
  extends TableForEmbedding<TableForEmbeddingBf> {

  /**
   * <p>Full data.</p>
   **/
  private final byte[] buffer;

  /**
   * <p>Only constructor.</p>
   * @param pTableDirEntry reference
   * @param pIsLengthSame value
   * @param pIsChecksumSame value
   * @param pBuffer reference
   **/
  public TableForEmbeddingBf(final TtfTableDirEntry pTableDirEntry,
    final boolean pIsLengthSame, final boolean pIsChecksumSame,
      final byte[] pBuffer) {
    super(pTableDirEntry, pIsLengthSame, pIsChecksumSame);
    this.buffer = pBuffer;
  }

  //Simple getters and setters:

  /**
   * <p>Getter for buffer.</p>
   * @return byte[]
   **/
  public final byte[] getBuffer() {
    return this.buffer;
  }
}

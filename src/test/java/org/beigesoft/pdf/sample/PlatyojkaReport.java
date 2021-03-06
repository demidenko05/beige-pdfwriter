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

package org.beigesoft.pdf.sample;

import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;

import org.beigesoft.doc.model.EUnitOfMeasure;
import org.beigesoft.doc.model.Document;
import org.beigesoft.doc.model.DocTable;
import org.beigesoft.doc.model.DocImage;
import org.beigesoft.doc.model.Pagination;
import org.beigesoft.doc.model.EWraping;
import org.beigesoft.doc.model.MetricsString;
import org.beigesoft.doc.model.EAlignHorizontal;
import org.beigesoft.doc.model.EPageSize;
import org.beigesoft.doc.model.EPageOrientation;
import org.beigesoft.doc.service.IDocumentMaker;
import org.beigesoft.pdf.model.ERegisteredTtfFont;
import org.beigesoft.pdf.model.PdfDocument;
import org.beigesoft.pdf.service.IPdfFactory;
import org.beigesoft.pdf.service.IPdfMaker;

/**
 * <p>Платежное поручение. Пример моноспэйсного отчета.
 * Monospace report sample.</p>
 *
 * @param <WI> writing instrument type
 * @author Yury Demidenko
 */
public class PlatyojkaReport<WI> {

  /**
   * <p>Factory.</p>
   **/
  private IPdfFactory<WI> factory;

  /**
   * <p>Write PDF report for given data to output stream.</p>
   * @param pData data
   * @param pOus output stream
   * @param pIsInsertImg if need image demonstration
   **/
  public final void makePdf(final PlatyojkaModel pData,
    final OutputStream pOus) throws Exception {
    // it makes document with page A4 and margins LTRB 30*20*20*20, UOM=millimeters,
    // font size 3.5:
    Document<WI> doc = this.factory.lazyGetFctDocument()
      .createDoc(EPageSize.A4, EPageOrientation.PORTRAIT);
    doc.getPages().get(0).setMarginRight(14);
    PdfDocument<WI> docPdf = this.factory.createPdfDoc(doc);
    docPdf.getPdfInfo().setCreationDate(new Date(1462867931627L));
    docPdf.getPdfInfo().setAuthor("Beigesoft (TM) Tester");
    IDocumentMaker docMaker = this.factory.lazyGetDocumentMaker();
    IPdfMaker<WI> pdfMaker = this.factory.lazyGetPdfMaker();
    pdfMaker.addFontTtf(docPdf, "LiberationMono-Regular");
    pdfMaker.addFontTtf(docPdf, "LiberationSerif-Regular");
    docMaker.setFontSize(doc, 5.0);
    doc.setAlignHoriCont(EAlignHorizontal.RIGHT);
    doc.setContentPadding(0.0);
    doc.setContentPaddingBottom(0.8);
    DocTable<WI> tblRez = docMaker
      .addDocTableNoBorder(doc, 1, 5);
    tblRez.setAlignHorizontal(EAlignHorizontal.RIGHT);
    tblRez.getItsCells().get(0).setItsContent("Приложение 2");
    tblRez.getItsCells().get(1).setItsContent("к Положению Банка России");
    tblRez.getItsCells().get(2).setItsContent("от 19 июня 2012 года N 383-П");
    tblRez.getItsCells().get(3).setItsContent("\"О правилах осуществления");
    tblRez.getItsCells().get(4).setItsContent("перевода денежных средств\"");
    docMaker.makeDocTableWrapping(tblRez);
    double fnSz = 3.5;
    docMaker.setFont(doc, 1, fnSz);
    double fnSzPd = 4.0;
    double top = 54.0;
    double lft = 30.0;
    int i = 0;
    docMaker.addString(doc, "                                                                  ┌───────┐", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "                                                                  │0401060│", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "______________________  ______________________                    └───────┘", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, " Поступ. в банк плат.    Списано со сч. плат.", lft, top + (fnSzPd * ++i));
    i++;
    docMaker.addString(doc, "                                                                    ┌─────┐", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "ПЛАТЕЖНОЕ ПОРУЧЕНИЕ N            ______________ _________________   │     │", lft, top + (fnSzPd * ++i));
    if (pData != null && pData.getItsNumber() != null) {
      MetricsString msForNum = this.factory.lazyGetEvalMetricsString()
        .eval("ПЛАТЕЖНОЕ ПОРУЧЕНИЕ N ", "LiberationMono-Regular", fnSz, 300.0, 0.0);
      docMaker.addString(doc, pData.getItsNumber(), lft + msForNum.getWidth(), top + (fnSzPd * i));
      MetricsString msForDt = this.factory.lazyGetEvalMetricsString()
        .eval("ПЛАТЕЖНОЕ ПОРУЧЕНИЕ N            __", "LiberationMono-Regular", fnSz, 300.0, 0.0);
      docMaker.addString(doc, pData.getItsDate(), lft + msForDt.getWidth(), top + (fnSzPd * i) - 0.5);
    }
    docMaker.addString(doc, "                                      Дата         Вид платежа      └─────┘", lft, top + (fnSzPd * ++i));
    i++;
    docMaker.addString(doc, "Сумма   │", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "прописью│", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "        │", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "────────┴───────┬────────────────────┬────────┬────────────────────────────", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "ИНН             │КПП                 │Сумма   │", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "────────────────┴────────────────────┤        │", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "                                     ├────────┼────────────────────────────", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "                                     │Сч. N   │", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "Плательщик                           │        │", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "─────────────────────────────────────┼────────┤", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "                                     │БИК     │", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "                                     ├────────┤", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "                                     │Сч. N   │", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "Банк плательщика                     │        │", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "─────────────────────────────────────┼────────┼────────────────────────────", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "                                     │БИК     │", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "                                     ├────────┤", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "                                     │Сч. N   │", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "Банк получателя                      │        │", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "────────────────┬────────────────────┼────────┤", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "ИНН             │КПП                 │Сч. N   │", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "────────────────┴────────────────────┤        │", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "                                     ├────────┼──────┬───────────┬─────────", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "                                     │Вид оп. │      │Срок плат. │", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "                                     ├────────┤      ├───────────┤", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "                                     │Наз. пл.│      │Очер. плат.│", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "                                     ├────────┤      ├───────────┤", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "Получатель                           │Код     │      │Рез. поле  │", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "──────────────┬────────────┬─────┬───┴────┬───┴──────┴───┬───────┴───┬─────", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "              │            │     │        │              │           │", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "──────────────┴────────────┴─────┴────────┴──────────────┴───────────┴─────", lft, top + (fnSzPd * ++i));
    i++;
    docMaker.addString(doc, "Назначение платежа", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "___________________________________________________________________________", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "                                  Подписи             Отметки банка", lft, top + (fnSzPd * ++i));
    i++;
    docMaker.addString(doc, "                         _________________________", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "            М.П.", lft, top + (fnSzPd * ++i));
    docMaker.addString(doc, "                         _________________________", lft, top + (fnSzPd * ++i));
    docMaker.deriveElements(doc);
    pdfMaker.prepareBeforeWrite(docPdf);
    //pdfMaker.setIsCompressed(docPdf, false);
    this.factory.lazyGetPdfWriter().write(null, docPdf, pOus);
  }

  //Simple getters and setters:
  /**
   * <p>Getter for factory.</p>
   * @return IPdfFactory
   **/
  public final IPdfFactory<WI> getFactory() {
    return this.factory;
  }

  /**
   * <p>Setter for factory.</p>
   * @param pFactory reference
   **/
  public final void setFactory(final IPdfFactory<WI> pFactory) {
    this.factory = pFactory;
  }
}

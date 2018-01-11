site: https://sites.google.com/site/beigesoftware

Beigesoft ™ PDF Writer.

This is light-weight (all JARs size is about 250KB), international friendly and fast PDF writer. You will not get performance problems on a high load server application, e.g. if hundred users push print (to PDF) button at the same time. It's cross-platform writer - Standard Java and Android. There are not 3-d party dependencies except Java, Android and TTF fonts.

For internationalization use free TTF fonts:
* DejaVu fonts that support a lot of languages - https://dejavu-fonts.github.io
* Japanese VL-Gothic fonts - http://vlgothic.dicey.org
* Korean Nanum fonts - https://slackbuilds.org/repository/14.0/system/nanum-fonts-ttf/
* Chinese Han Sans - https://github.com/be5invis/source-han-sans-ttf/releases
* Liberation fonts - https://pagure.io/liberation-fonts

TTF fonts will be embedded into PDF in compact form, i.e. only used characters will be present.

Version 1.0.1:
Added JAR signing.
Fixed crossplatform file.separator.

licenses:
GNU General Public License version 2
http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html

3-D PARTY LICENSES (TTFs included in test package):

DejaVu fonts license:
https://dejavu-fonts.github.io/License.html

Liberation 1.07.4 fonts license (GNU General Public License version 2 with exceptions):
https://fedoraproject.org/wiki/Licensing/LiberationFontLicense

Files test-dejavu-table.pdf, Platyojka.pdf, Sfactura.pdf and invoiceA-68687687.pdf are included into the source code to quickly demonstrate what can Beige-PDFWriter does.

Examples see in the test folder.
For Android see example https://github.com/demidenko05/beigesoft-android-sqlite-test
org.beigesoft.androidtest.WritePdfImageTest.java

Speed test (org.beigesoft.pdf.sample.WriteInvoiceSpeedTest) shows that 100 invoices (3pages each) take less than 4 seconds on ordinal notebook IntelI3/4GB, 64bit desktop linux (non-server), JVM8 64Bit. Doing each invoice takes its own thread.

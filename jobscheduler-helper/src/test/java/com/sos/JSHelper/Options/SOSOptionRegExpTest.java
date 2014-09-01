package com.sos.JSHelper.Options;
import static org.junit.Assert.assertEquals;

import java.util.regex.Pattern;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.sos.JSHelper.DataElements.JSDataElementDate;

/**
* \class SOSOptionRegExpTest
*
* \brief SOSOptionRegExpTest -
*
* \details
*
* \section SOSOptionRegExpTest.java_intro_sec Introduction
*
* \section SOSOptionRegExpTest.java_samples Some Samples
*
* \code
*   .... code goes here ...
* \endcode
*
* <p style="text-align:center">
* <br />---------------------------------------------------------------------------
* <br /> APL/Software GmbH - Berlin
* <br />##### generated by ClaviusXPress (http://www.sos-berlin.com) #########
* <br />---------------------------------------------------------------------------
* </p>
* \author KB
* @version $Id$13.09.2010
* \see reference
*
* Created on 13.09.2010 10:32:15
 */
public class SOSOptionRegExpTest {
	private final String	conClassName	= "SOSOptionRegExpTest";
	private final Logger	logger			= Logger.getLogger(this.getClass());
	private SOSOptionRegExp	objRE			= null;

	public SOSOptionRegExpTest() {
		//
	}

	@Test
	public void WrongRegExp() {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::WrongRegExp";
		objRE = new SOSOptionRegExp(null, "test", "TestOption", "^test_dh000000.err", "", false);
		Pattern p = objRE.getPattern();
		p = objRE.getPattern("/\\.swf(\\?\\.*)?$/i");
	} // private void WrongRegExp

	@Test
	public void testMatcher() {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::testMatcher";
		objRE = new SOSOptionRegExp(null, "test", "TestOption", "^(.*/*)2.*\\.txt$", "", false);
		boolean flgMatched = objRE.match("/home/test/kb/4_abc.txt");
		Assert.assertFalse("do not match", flgMatched);
		flgMatched = objRE.match("/2_abc.txt");
		Assert.assertTrue("must match", flgMatched);
		flgMatched = objRE.match("/home/test/kb/2_abc.txt1");
		Assert.assertFalse("must not match", flgMatched);
		flgMatched = objRE.match("2_abc.txt");
		Assert.assertTrue("must match", flgMatched);
		flgMatched = objRE.match("/home/test/kb/2_abc.txt");
		Assert.assertTrue("must match", flgMatched);
		String strGroupText = objRE.getGroup(1);
		Assert.assertEquals("group found", "/home/test/kb/", strGroupText);
	} // private void testMatcher

	@Test
	public void testRegExpWithPlaceHolders() throws Exception {
		String strDateformat = "yyyy-MM-dd";
		String strDate = JSDataElementDate.getCurrentTimeAsString(strDateformat);
		System.out.println(strDate);
		objRE = new SOSOptionRegExp(new JSOptionsClass(), "test", "TestOption", String.format("^.*_[date:%1$s]_\\.txt$", strDateformat), "", false);
		String strV = objRE.Value();
		System.out.println("value after replace is: " + strV);
		assertEquals("place holders 1", String.format("^.*_%1$s_\\.txt$", strDate), strV);
	}

	@Test
	public void testDoReplace() throws Exception {
		objRE = new SOSOptionRegExp(null, "test", "TestOption", "", "", false);
		String strReplaceWhat = "";
		String strReplaceWith = "";
		String strStringToWorkOn = "";
		/**
		 * simple textrecplacing: replace the full string
		 */
		//		doTest ("Prefix", "Hello", "\\-;\\2",  "prefix1abc12def123.txt", "1abc12def123.txt");
		strReplaceWhat = "Hello";
		strReplaceWith = "World";
		strStringToWorkOn = "Hello";
		objRE.Value(strReplaceWhat);
		Assert.assertEquals("RegExp Value failed", strReplaceWhat, objRE.Value());
		Assert.assertEquals("replace failed", strReplaceWith, objRE.doReplace(strStringToWorkOn, strReplaceWith));
		strReplaceWhat = "Hello";
		strReplaceWith = "";
		strStringToWorkOn = "Hello  World";
		objRE.Value(strReplaceWhat);
		assertEquals("RegExp Value failed", strReplaceWhat, objRE.Value());
		Assert.assertEquals("replace failed", "  World", objRE.doReplace(strStringToWorkOn, strReplaceWith));
		/**
		 * simple example: replace the groups by absolute values
		 */
		strReplaceWhat = "(1)abc(12)def(.*)";
		strReplaceWith = "A;BB;CCC";
		strStringToWorkOn = "1abc12def123.txt";
		objRE.Value(strReplaceWhat);
		Assert.assertEquals("RegExp Value failed", strReplaceWhat, objRE.Value());
		Assert.assertEquals("replace failed", "AabcBBdefCCC", objRE.doReplace(strStringToWorkOn, strReplaceWith));
		strReplaceWhat = "(INT_)(.*)";
		//		strReplaceWith = ";\\2";
		strReplaceWith = "\\-;\\2";
		strStringToWorkOn = "INT_4711-0815.txt";
		objRE.Value(strReplaceWhat);
		Assert.assertEquals("RegExp Value failed", strReplaceWhat, objRE.Value());
		Assert.assertEquals("replace failed", "4711-0815.txt", objRE.doReplace(strStringToWorkOn, strReplaceWith));
		/**
		 * a little bit more complicated:
		 * the first 5 and the last chars are part of the new string. the 6 chars in the middle are discarded.
		 * the "non-standard" pattern \- is used to indicate, that this group has to be discarded in the
		 * resulting text. As an alternative it is possible to use an empty item ";;".
		 */
		strReplaceWhat = "(.{5})(.{6})(.*)";
		//		strReplaceWith = "\\1;\\-;\\3;";
		strReplaceWith = "\\1;;\\3;";
		strStringToWorkOn = "abcba123456hallo.txt";
		objRE.Value(strReplaceWhat);
		Assert.assertEquals("RegExp Value failed", strReplaceWhat, objRE.Value());
		Assert.assertEquals("replace failed", "abcbahallo.txt", objRE.doReplace(strStringToWorkOn, strReplaceWith));
		/**
		 * An example for swapping the groups
		 */
		doTestReplace("Swapping", "(1)abc(12)def(.*)", "\\2;\\1;CCC", "1abc12def123.txt", "12abc1defCCC");
		/**
		 * Delete a group at the end of the string
		 */
		doTestReplace("Swapping", "(1)abc(12)def(.*)", "\\2;\\1;\\-", "1abc12def123.txt", "12abc1def");
		/**
		 * put a prefix to the new string-value
		 */
		doTestReplace("Prefix", "(.*)", "prefix\\1", "1abc12def123.txt", "prefix1abc12def123.txt");
		/**
		 * a suffix is possible, too
		 */
		doTestReplace("Prefix", "(.*)", "\\1suffix", "1abc12def123.txt", "1abc12def123.txtsuffix");
		/**
		 * a suffix and a prefix is possible. makes it more flexible
		 */
		doTestReplace("Prefix", "(.*)", "prefix\\1suffix", "1abc12def123.txt", "prefix1abc12def123.txtsuffix");
		/**
		 * now we have a prefix with the value but we have to delete it on the result-value
		 */
		doTestReplace("Prefix", "(prefix)(.*)", "\\-;\\2", "prefix1abc12def123.txt", "1abc12def123.txt");
		/**
		 * now we have a prefix with the value but we have to delete it on the result-value
		 */
		doTestReplace("Prefix", "(prefix)(.*)", "\\2", "prefix1abc12def123.txt", "1abc12def123.txt");
		/*
		 * Introducing a "non-regexp" feature: date-variable.
		 * The format has to be given as a valid java-daten-format mask.
		 *
		 */
		String strDate = JSDataElementDate.getCurrentTimeAsString("yyyyMMddHHmm");
		doTestReplace("Date insertion", "(.*)(.txt)", "\\1_[date:yyyyMMddHHmm];\\2", "1.txt", "1_" + strDate + ".txt");
		strDate = JSDataElementDate.getCurrentTimeAsString();
		doTestReplace("Date insertion without date-format", "(.*)(.txt)", "\\1_[date:];\\2", "1.txt", "1_" + strDate + ".txt");
		strDate = JSDataElementDate.getCurrentTimeAsString();
		doTestReplace("Date suffix", "(.*)(.txt)", "\\1;\\2_[date:]", "1.txt", "1.txt_" + strDate);
		strDate = JSDataElementDate.getCurrentTimeAsString();
		doTestReplace("Date prefix", "(.*)(.txt)", "[date:]_\\1;\\2", "1.txt", strDate + "_1.txt");
		/**
		 * just another variable: filename
		 * uppercase- or lowercase-conversion is possible.
		 * the type of conversion is indicated by writing "uppercase" oder "lowercase" after the colon.
		 */
		doTestReplace("FileName uppercase 1", ".*", "[filename:uppercase]", "1.txt", "1.TXT");
		doTestReplace("FileName lowercase 2", ".*", "[filename:lowercase]", "1.txt", "1.txt");
		String strExpectedFileName = "20120613_144343_SHRTIS001_0000000001011689.DAT";
		String strFileName4Test = "20120613_144343_SHRTIS001_0000000001011689.DAT.SHRTIS001_DOR";
		doTestReplace("ausschneiden 1", "(.*)(\\.DAT)(\\.SHRTIS001_DOR)", "\\1;\\2;", strFileName4Test, strExpectedFileName);
		doTestReplace("ausschneiden 2", "(.*)(\\.DAT)(\\.SHRTIS001_DOR)", "\\1;\\2; ;", strFileName4Test, strExpectedFileName);
		doTestReplace("ausschneiden 3", "(.*)(\\.DAT)(.*$)", "\\1;\\2", strFileName4Test, strExpectedFileName);
		doTestReplace("ausschneiden 4", "(.*)(\\.DAT).*$", "\\1;\\2", strFileName4Test, strFileName4Test);
		// anstelle von timestamp kann auch 
	}

	@Test
	public void testUnixTimeStamp() throws Exception {
		objRE = new SOSOptionRegExp(null, "test", "TestOption", "", "", false);
		doTestReplace("timestamp", "(.*)(.txt)", "[timestamp:]_\\1;\\2", "abc.txt", null);
	}

	@Test
	public void testTempFile() throws Exception {
		objRE = new SOSOptionRegExp(null, "test", "TestOption", "", "", false);
		doTestReplace("tempfile", "(.*)(.txt)", "[tempfile:]_\\1;\\2", "abc.txt", null);
	}

	@Test
	public void testUUID() throws Exception {
		objRE = new SOSOptionRegExp(null, "test", "TestOption", "", "", false);
		doTestReplace("UUID", "(.*)(.txt)", "[uuid:]_\\1;\\2", "1.txt", null);
	}

	@Test
	public void testSQLTimeStamp() throws Exception {
		objRE = new SOSOptionRegExp(null, "test", "TestOption", "", "", false);
		doTestReplace("SQLTimeStamp", "(.*)(.txt)", "[sqltimestamp:]_\\1;\\2", "1.txt", null);
	}

	@Test
	public void testRegExpSQLTimeStamp() throws Exception {
		objRE = new SOSOptionRegExp(null, "test", "TestOption", "", "", false);
		doRegExTest("SQLTimeStamp", "(.*)(.txt)", "[sqltimestamp:]_$1$2", "abcd.txt", null);
	}

	@Test
	public void testDoRegExReplace() throws Exception {
		objRE = new SOSOptionRegExp(null, "test", "TestOption", "", "", false);
		//		doRegExTest ("ausschneiden", "(.*)(\\.DAT)(\\.SHRTIS001_DOR)", "[filename:]$1$2", "20120613_144343_SHRTIS001_0000000001011689.DAT.SHRTIS001_DOR", "20120613_144343_SHRTIS001_0000000001011689.DAT" );
		//		doRegExTest ("ausschneiden", "(.*)(\\.DAT)(\\.SHRTIS001_DOR)", "[date:]$1$2", "20120613_144343_SHRTIS001_0000000001011689.DAT.SHRTIS001_DOR", "20120613_144343_SHRTIS001_0000000001011689.DAT" );
		doRegExTest("ausschneiden", "(.*)(\\.DAT)(\\.SHRTIS001_DOR)", "$1$2", "20120613_144343_SHRTIS001_0000000001011689.DAT.SHRTIS001_DOR",
				"20120613_144343_SHRTIS001_0000000001011689.DAT");
	}

	private void doTestReplace(final String strText, final String strReplaceWhat, final String strReplaceWith, final String strWork,
			final String strExpectedResult) throws Exception {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::doTest";
		objRE.Value(strReplaceWhat);
		Assert.assertEquals(strText, strReplaceWhat, objRE.Value());
		String strResult = objRE.doReplace(strWork, strReplaceWith);
		if (strExpectedResult != null) {
			assertEquals(strText, strExpectedResult, strResult);
		}
		System.out.println(strResult);
		logger.debug(strResult);
	} // private void doTest

	private void doRegExTest(final String strText, final String strReplaceWhat, final String strReplaceWith, final String strWork,
			final String strExpectedResult) throws Exception {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::doTest";
		objRE.Value(strReplaceWhat);
		Assert.assertEquals(strText, strReplaceWhat, objRE.Value());
		String strResult = objRE.doRegExpReplace(strWork, strReplaceWith);
		if (strExpectedResult != null) {

			Assert.assertEquals(strText, strExpectedResult, strResult);
		}
		System.out.println(strResult);
		logger.debug(strResult);
	} // private void doTest
}

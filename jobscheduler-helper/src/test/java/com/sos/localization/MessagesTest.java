package com.sos.localization;
import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
* \class MessagesTest
*
* \brief MessagesTest -
*
* \details
*
* \section MessagesTest.java_intro_sec Introduction
*
* \section MessagesTest.java_samples Some Samples
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
* @version $Id$03.09.2010
* \see reference
*
* Created on 03.09.2010 13:47:20
 */
/**
 * @author KB
 *
 */
public class MessagesTest {
	@SuppressWarnings("unused")
	private final String	conClassName	= "MessagesTest";
	private Messages		Messages		= null;

	public MessagesTest() {
		//
	}

	/**
	 * \brief setUpBeforeClass
	 *
	 * \details
	 *
	 * \return void
	 *
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * \brief tearDownAfterClass
	 *
	 * \details
	 *
	 * \return void
	 *
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * \brief setUp
	 *
	 * \details
	 *
	 * \return void
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Messages = new Messages("com/sos/localization/messages", Locale.getDefault());
//		Messages = new Messages("com/sos/localization/messages", Locale.ENGLISH);
	}

	/**
	 * \brief tearDown
	 *
	 * \details
	 *
	 * \return void
	 *
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void TestVarArgs () {
		String strM = "das ist '%1$s', '%2$s' und '%3$s' ...";
		strM = Messages.getMsg(strM, "eins", "zwei", "drei", Locale.getDefault());
//		System.out.println(strM);
		assertEquals("get message-text with parameter", "das ist 'eins', 'zwei' und 'drei' ...", strM);
		strM = "das ist '%1$s', '%2$d' und '%3$s' ...";
		strM = Messages.getMsg(strM, "eins", 2, "drei", Locale.getDefault());
//		System.out.println(strM);
		assertEquals("get message-text with parameter", "das ist 'eins', '2' und 'drei' ...", strM);
	}
	@Test
	public void testGetMsgENGLISHSingle() {
		String strM = Messages.getMsg("JSJ-I-106", Locale.ENGLISH);
		System.out.println(strM);
		assertEquals("JSJ-I-106", "JSJ-I-106: %1$s - ended without errors", strM);
	}

	@Test
	public void testGetMsgENGLISHAlias() {
		String strM = Messages.getMsg("JSJ-I-111", Locale.ENGLISH);
		assertEquals("JSJ-I-111", "JSJ-I-111: %1$s - ended without errors", strM);
	}

	@Test
	public void testGetMsg_en_US() {
		String strM = Messages.getMsg("JSJ-I-111", new Locale("en", "US"));
		System.out.println(strM);

		assertEquals("JSJ-I-111", "JSJ-I-111: %1$s - ended without errors", strM);
	}

	@Test
	public void testGetMsgFrenchSingle() {
		String strM = Messages.getMsg("JSJ-I-106", Locale.FRENCH);
		assertEquals("JSJ-I-106", "JSJ-I-106: %1$s - s'est termin�e sans erreurs", strM);
	}

	@Test
	public void testGetMsgFrenchAlias() {
		String strM = Messages.getMsg("JSJ-I-111", Locale.FRENCH);
		assertEquals("JSJ-I-111", "JSJ-I-111: %1$s - s'est termin�e sans erreurs", strM);
	}

	@Test
	public void testGetMsgSingle() {
		String strM = Messages.getMsg("JSJ-I-106", new Locale("de", "DE"));
		assertEquals("JSJ-I-106", "JSJ-I-106: %1$s - wurde ohne Fehler beendet", strM);
	}

	@Test
	public void testGetMsgSingle_de_CH() {
		String strM = Messages.getMsg("JSJ-I-106", new Locale("de", "CH"));
		assertEquals("JSJ-I-106", "JSJ-I-106: %1$s - wurde ohne Fehler beendet", strM);
	}

	@Test
	public void testGetMsgSingle_it() {
		String strM = Messages.getMsg("JSJ-I-106", Locale.ITALIAN);
		System.out.println(strM);
		assertEquals("JSJ-I-106", "JSJ-I-106: %1$s - wurde ohne Fehler beendet", strM);
	}

	// @Test
	public void testGetMsgSingle_ja() {
		String strM = Messages.getMsg("JSJ-I-105", Locale.JAPANESE);
		System.out.println(strM);
		assertEquals("JSJ-I-106", "JSJ-I-105: %1$s - wurde ohne Fehler beendet", strM);
	}

	@Test
	public void testGetMsgAlias() {
		String strM = Messages.getMsg("JSJ-I-111", Locale.getDefault());
		assertEquals("JSJ-I-111", "JSJ-I-111: %1$s - wurde ohne Fehler beendet", strM);
	}

	@Test
	public void testMissingMessage () {
		String conM = "This Message is not in the property file";
		String strM = Messages.getMsg(conM);
		assertEquals("JSJ-I-111", conM, strM);

	}
}

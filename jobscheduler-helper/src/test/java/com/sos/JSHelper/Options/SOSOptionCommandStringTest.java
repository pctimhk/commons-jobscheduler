package com.sos.JSHelper.Options;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sos.JSHelper.io.Files.JSFile;

/**
* \class SOSOptionCommandStringTest
*
* \brief SOSOptionCommandStringTest -
*
* \details
*
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
* \version $Id$
* \see reference
*
* Created on 09.05.2012 13:21:08
 */

public class SOSOptionCommandStringTest {

	@SuppressWarnings("unused")
	private final String		conClassName	= "SOSOptionCommandStringTest";
	private static final String  conSVNVersion = "$Id$";
	private static final Logger	logger					= Logger.getLogger(SOSOptionCommandStringTest.class);

	private SOSOptionCommandString objCS = null;

	public SOSOptionCommandStringTest () {
		//
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {}

	@Before
	public void setUp() throws Exception {
		objCS = new SOSOptionCommandString(null, "test", "Description", null, null, false);
	}

	@After
	public void tearDown() throws Exception {}

	@Test
	public final void testValue(){
		String strT = "Hello, world ...";
		objCS.Value(strT);
		assertEquals("strT", strT, objCS.Value());
	}

	@Test
	public final void testSOSOptionCommandString(){
	}

	@Test
	public final void testFromHexString(){
		String strV = "61626364";
		objCS.Value(strV);
		String strT = new String(objCS.fromHexString());
		assertEquals("must be equal", strT, "abcd");
	}

	@Test
	public final void testIsHex(){
		objCS.Value("ABCDEF");
		boolean flgT = objCS.isHex();
		assertTrue("must be true", flgT);

		objCS.Value("Hello");
		assertFalse("must be false", objCS.isHex());

		objCS.Value(null);
		assertFalse("must be false", objCS.isHex());

		objCS.Value("3132333435");
		System.out.println("Value is " + objCS.Value());
		assertEquals("Value is hex", "12345", objCS.Value());
	}

	@Test
	public final void testIsHexStringChar(){
//	fail("Not yet implemented"); 
	}

	@Test
	public final void testValues(){
//	fail("Not yet implemented"); 
	}

	@Test
	public void testValueString() throws Exception {
		JSOptionsClass objO = new JSOptionsClass();
		String strF = objO.TempDir() + "testSOSOptionFileString.txt";
		JSFile objF = new JSFile(strF);
		objF.deleteOnExit();
		String strT = "Select * from table;";
		objF.Write(strT);
		objF.close();

		objCS.Value(strF); // the filename is the value
		System.out.println(objCS.Value());
		assertEquals("select", strT, objCS.Value());
	}
}
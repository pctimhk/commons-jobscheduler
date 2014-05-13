package com.sos.JSHelper.io.Files;


import static org.junit.Assert.assertEquals;

import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sos.JSHelper.Listener.JSListenerClass;
import com.sos.JSHelper.Options.JSOptionsClass;

/**
 * \class JSCsvFileTest
 *
 * \brief JSCsvFileTest -
 *
 * \details
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
 * \author EQALS
* @version $Id$18.05.2009
 * \see reference
 *
 * Created on 18.05.2009 15:49:49
 */

public class JSCsvFileTest extends JSListenerClass { 

	@SuppressWarnings({ "unused", "hiding" })
	private final String	conClassName	= "JSCsvFileTest";
	private static JSFile fleTestdataDirectory = null;

	public JSCsvFileTest() {
		//
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		JSOptionsClass objOptionsClass = new JSOptionsClass();
		fleTestdataDirectory = new JSFile("R:/backup/sos/java/junittests/testdata/JSCsvFileTest/");
		BasicConfigurator.configure();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 *
	 * \brief testReadCsvFile1
	 *
	 * \details
	 * Test CSV-File mit Kommentaren
	 *
	 * \return void
	 *
	 * @throws Exception
	 */
	@Test
//	@Ignore
	public void testReadCsvFile1() throws Exception {
		String[] headers = null;
		String[] strValues = null;

		String strF = fleTestdataDirectory + "/StoppageList.csv";
		JSCsvFile objF = new JSCsvFile(strF);
		objF.CheckColumnCount(false);
		objF.deleteOnExit();
		objF.WriteLine("Reporter;StoppageType;FromDate;ToDate");
		objF.WriteLine("## StoppageType: S=Stop, R=Run; ; ; ");
		objF.WriteLine("### Inbound; ");
		objF.WriteLine("# P2R             ; ; ; ");
		objF.WriteLine("2001            ;S;        13.02.2009 23:00:00;15.02.2009 22:00:00");
		
		objF.close();
		
		JSCsvFile objCsvFile = new JSCsvFile(fleTestdataDirectory + "/StoppageList.csv");
		objCsvFile.CheckColumnCount(false);
		objCsvFile.loadHeaders();
		headers =  objCsvFile.Headers();
		assertEquals("Header1 must be Reporter", headers[0], "Reporter");
		assertEquals("Header2 must be StoppageType", headers[1], "StoppageType");
		assertEquals("Header3 must be FromDate", headers[2], "FromDate");
		assertEquals("Header4 must be ToDate", headers[3], "ToDate");

		strValues = objCsvFile.readCSVLine();
		assertEquals("Field1 must be ## StoppageType: S=Stop, R=Run", strValues[0], "## StoppageType: S=Stop, R=Run");

		strValues = objCsvFile.readCSVLine();
		assertEquals("Field1 must be ### Inbound: S=Stop, R=Run", strValues[0], "### Inbound");

		strValues = objCsvFile.readCSVLine();
		assertEquals("Field1 must be # P2R             ", strValues[0], "# P2R             ");

		strValues = objCsvFile.readCSVLine();
		assertEquals("Field1 must be 2001            ", strValues[0], "2001            ");
		assertEquals("Field2 must be S", strValues[1], "S");
		assertEquals("Field3 must be         13.02.2009 23:00:00", strValues[2], "        13.02.2009 23:00:00");
		assertEquals("Field4 must be 15.02.2009 22:00:00", strValues[3], "15.02.2009 22:00:00");
	}

	/**
	 *
	 * \brief testReadCsvFile2
	 *
	 * \details
	 * CSV-File ohne Kommentare
	 *
	 * \return void
	 *
	 * @throws Exception
	 */
	@Test
	public void testReadCsvFile2() throws Exception {
		String[] headers = null;
		String[] strValues = null;

		JSCsvFile objCsvFile = new JSCsvFile(fleTestdataDirectory + "/test.csv");
		objCsvFile.registerMessageListener(this);
		objCsvFile.loadHeaders();
		headers =  objCsvFile.Headers();
		assertEquals("Header1 must be Reporter", headers[0], "Reporter");
		assertEquals("Header2 must be StoppageType", headers[1], "StoppageType");
		assertEquals("Header3 must be FromDate", headers[2], "FromDate");
		assertEquals("Header4 must be ToDate", headers[3], "ToDate");

		strValues = objCsvFile.readCSVLine();
		assertEquals("Field1 must be 2001            ", strValues[0], "2001            ");
		assertEquals("Field2 must be S", strValues[1], "S");
		assertEquals("Field3 must be         13.02.2009 23:00:00", strValues[2], "        13.02.2009 23:00:00");
		assertEquals("Field4 must be 15.02.2009 22:00:00", strValues[3], "15.02.2009 22:00:00");

		strValues = objCsvFile.readCSVLine();
		assertEquals("Field1 must be 0032            ", strValues[0], "0032            ");
		assertEquals("Field2 must be S", strValues[1], "S");
		assertEquals("Field3 must be         13.02.2009 23:00:00", strValues[2], "        13.02.2009 23:00:00");
		assertEquals("Field4 must be 15.02.2009 22:00:00", strValues[3], "15.02.2009 22:00:00");

	}
}
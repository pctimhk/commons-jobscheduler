package com.sos.JSHelper.Options;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
* \class JSOptionIndexedItemTest 
* 
* \brief JSOptionIndexedItemTest - 
* 
* \details
*
* \section JSOptionIndexedItemTest.java_intro_sec Introduction
*
* \section JSOptionIndexedItemTest.java_samples Some Samples
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
* @version $Id$19.10.2009
* \see reference
*
* Created on 19.10.2009 14:40:12
 */

public class JSOptionIndexedItemTest {

	@SuppressWarnings("unused")
	private final String	conClassName	= "JSOptionIndexedItemTest";
	
	private static HashMap<String, String>	settings		= null;
	private static JSOptionsClass	 		objOptions		= null;
	private static JSOptionIndexedItem objII = null;
	
	public JSOptionIndexedItemTest() {
		//
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		settings = new HashMap<String, String>();
		objOptions = new JSOptionsClass(settings);
		objII = new JSOptionIndexedItem(objOptions, 
				"dummyKey", 
				"Descr", 
				"", 
				"", false);
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
	
	@Test
	public void testAddItem1() throws Exception {
		
		assertTrue( "Keine Indexed Options vorhanden", objII.IsEmpty());
		assertEquals( "Anzahl Indexed Options = 0", 0, objII.value().length);
		
	}
	
	@Test
	public void testAddItem2() throws Exception {
		
		objII.AddItem("Item 1");		
		assertFalse( "Indexed Options vorhanden", objII.IsEmpty());
		assertEquals( "Anzahl Indexed Options = 1", 1, objII.value().length);
	}
	
	@Test
	public void testAddItem3() throws Exception {
		
		objII.AddItem("Item 2a;Item 2b;");		
		assertEquals( "Anzahl Indexed Options = 2", 2, objII.value().length);
	}
	
	@Test
	public void testAddItem4() throws Exception {
		
		assertEquals( "Indexed gefunden", "Item 1", objII.value()[0]);
		assertEquals( "Indexed gefunden", "Item 2a;Item 2b;", objII.value()[1]);
	}
	
}

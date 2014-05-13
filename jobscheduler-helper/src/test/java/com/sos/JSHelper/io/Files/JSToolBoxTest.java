package com.sos.JSHelper.io.Files;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sos.JSHelper.Basics.JSToolBox;

/**
 * \class JSToolBoxTest 
 * 
 * \brief JSToolBoxTest - 
 * 
 * \details
 *
 * \section JSToolBoxTest.java_intro_sec Introduction
 *
 * \section JSToolBoxTest.java_samples Some Samples
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
* @version $Id$10.06.2009
 * \see reference
 *
 * Created on 10.06.2009 14:28:05
 */

public class JSToolBoxTest {

	@SuppressWarnings("unused")
	private final String	conClassName	= "JSToolBoxTest";

	public JSToolBoxTest() {
		//
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void TestConstructor () {
		JSToolBox objT = new JSToolBox();
		
		assertEquals("Constructor-Test", true, (objT != null));
	}
}
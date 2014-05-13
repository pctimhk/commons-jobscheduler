package com.sos.JSHelper.Options;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sos.JSHelper.Annotations.JSOptionClass;

/**
* \class SOSOptionElementTest 
* 
* \brief SOSOptionElementTest - 
* 
* \details
*
* \section SOSOptionElementTest.java_intro_sec Introduction
*
* \section SOSOptionElementTest.java_samples Some Samples
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
* @version $Id$03.08.2010
* \see reference
*
* Created on 03.08.2010 13:47:30
 */

/**
 * @author KB
 *
 */
public class SOSOptionElementTest {

	private final String	conClassName	= "SOSOptionElementTest";
	private SOSOptionElement objOption = null;
	
	 
	public SOSOptionElementTest() {
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
		objOption = new SOSOptionElement (null, "key", "Description","value", "DefaultValue", true);

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

	/**
	 * 
	 * \brief testSetPrefix
	 * 
	 * \details
	 *
	 * \return void
	 *
	 */
	@Test
	public void testSetPrefix () {
		String strPrefix = "alternate_";
		String strKey = "key";
		String strNewKey = objOption.setPrefix(strPrefix);
		Assert.assertEquals("Key has wrong prefix", strPrefix + strKey, strNewKey);
		
		objOption.strKey = conClassName + ".key";
		strNewKey = objOption.setPrefix(strPrefix);
		Assert.assertEquals("Key has wrong prefix", conClassName + "." + strPrefix + strKey, strNewKey);
	}
	@Test
	public void testHide () {
		String strT = objOption.toString();
		Assert.assertEquals("toString", "key (Description): value", strT);
		objOption.setHideValue(true);
		Assert.assertEquals("toString", "key (Description): *****", objOption.toString());
		objOption.setHideOption(true);
		Assert.assertEquals("toString", "", objOption.toString());
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#toString()}.
	 */
	@Test
	public void testToString() {
		String strString = objOption.toString();
		Assert.assertEquals("Expected Value not got", "key (Description): value", strString);
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#SOSOptionElement(com.sos.JSHelper.Options.JSOptionsClass, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean)}.
	 */
	@Test
	public void testSOSOptionElement() {
		
		SOSOptionElement objOpt = new SOSOptionElement (null, "key", "Description","value", "DefaultValue", true);
		Assert.assertEquals("Key failed", "key", objOpt.getKey());
		Assert.assertEquals("Description failed", "Description", objOpt.Description());
		Assert.assertEquals("Value failed", "value", objOpt.Value());
		Assert.assertEquals("DefaultValue failed", "DefaultValue", objOpt.DefaultValue());
		Assert.assertTrue("Is Mandatory failed", objOpt.isMandatory());
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#ColumnHeader(java.lang.String)}.
	 */
	@Test
	public void testColumnHeaderString() {
		Assert.assertEquals("ColumnHeader failed", "key", objOption.ColumnHeader());
		objOption.ColumnHeader("Column");
		Assert.assertEquals("ColumnHeader failed", "Column", objOption.ColumnHeader());
		
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#ColumnHeader()}.
	 */
	@Test
	public void testColumnHeader() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#Title(java.lang.String)}.
	 */
	@Test
	public void testTitleString() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#Title()}.
	 */
	@Test
	public void testTitle() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#DefaultValue()}.
	 */
	@Test
	public void testDefaultValue() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#getKey()}.
	 */
	@Test
	public void testGetKey() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#Value(java.lang.String)}.
	 */
	@Test
	public void testValueString() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#Value()}.
	 */
	@Test
	public void testValue() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#Description(java.lang.String)}.
	 */
	@Test
	public void testDescriptionString() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#Description()}.
	 */
	@Test
	public void testDescription() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#Size(int)}.
	 */
	@Test
	public void testSizeInt() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#Size(java.lang.Integer)}.
	 */
	@Test
	public void testSizeInteger() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#Size()}.
	 */
	@Test
	public void testSize() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#ISize()}.
	 */
	@Test
	public void testISize() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#XMLTagName(java.lang.String)}.
	 */
	@Test
	public void testXMLTagNameString() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#XMLTagName()}.
	 */
	@Test
	public void testXMLTagName() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#toXml()}.
	 */
	@Test
	public void testToXml() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#toXml(com.sos.JSHelper.io.Files.JSXMLFile)}.
	 */
	@Test
	public void testToXmlJSXMLFile() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#FormatString(java.lang.String)}.
	 */
	@Test
	public void testFormatStringString() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#FormatString()}.
	 */
	@Test
	public void testFormatString() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#doInit()}.
	 */
	@Test
	public void testDoInit() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#FormattedValue()}.
	 */
	@Test
	public void testFormattedValue() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#IsEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#IsNotEmpty()}.
	 */
	@Test
	public void testIsNotEmpty() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#MapValue()}.
	 */
	@Test
	public void testMapValue() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#CheckMandatory()}.
	 */
	@Test
	public void testCheckMandatory() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#toOut()}.
	 */
	@Test
	public void testToOut() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#OptionType()}.
	 */
	@Test
	public void testOptionType() {
		
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#OptionType(int)}.
	 */
	@Test
	public void testOptionTypeInt() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#isMandatory(boolean)}.
	 */
	@Test
	public void testIsMandatoryBoolean() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#isMandatory()}.
	 */
	@Test
	public void testIsMandatory() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sos.JSHelper.Options.SOSOptionElement#SetAlias(java.lang.String)}.
	 */
	@Test
	public void testSetAlias() {
		objOption.SetAlias("newKey");
		objOption.SetAlias("AliasKey");
	}
	
	@Test
	public void testSetDirty() {
	
	@SuppressWarnings("unused")
	final String	conMethodName	= conClassName + "::testSetDirty";
	
	Assert.assertFalse("Must be not Dirty", objOption.isDirty());
	objOption.Value("Dirty");
	Assert.assertTrue("Must be Dirty", objOption.isDirty());
	
} // private void testSetDirty

	@Test
	public void testEnvVarAsValue () {
		SOSOptionElement objOpt = new SOSOptionElement (null, "key", "Description","env:APPDATA", "env:LOCALAPPDATA", true);
		System.out.println("value = " + objOpt.Value());
		System.out.println("default value = " + objOpt.DefaultValue());
	}
	
	@Test
	public void testEnvVarAsValue2 () {
		SOSOptionElement objOpt = new SOSOptionElement (null, "key", "Description","file:${APPDATA}", "env:LOCALAPPDATA", true);
		System.out.println("value = " + objOpt.Value());
		System.out.println("default value = " + objOpt.DefaultValue());
		objOpt = new SOSOptionElement (null, "key", "Description","file:${APPDATA}/config/live", "env:LOCALAPPDATA", true);
		System.out.println("value = " + objOpt.Value());
		System.out.println("default value = " + objOpt.DefaultValue());
		objOpt = new SOSOptionElement (null, "key", "Description","${APPDATA}/config/live", "env:LOCALAPPDATA", true);
		System.out.println("value = " + objOpt.Value());
		System.out.println("default value = " + objOpt.DefaultValue());
		objOpt = new SOSOptionElement (null, "key", "Description","${APPDATA}", "env:LOCALAPPDATA", true);
		System.out.println("value = " + objOpt.Value());
		System.out.println("default value = " + objOpt.DefaultValue());
	}
	
	@Test 
	public void testSOSLocale () {
		
	SOSOptionLocale				Locale					= new SOSOptionLocale( // ...
			null, // ....
			conClassName + ".Locale", // ...
			"I18N is for internationalization of Application", // ...
			"env:SOS_LOCALE", // ...
			java.util.Locale.getDefault().toString(), // ...
			true);

	System.out.println("Locale = " + Locale.Value());
	}

	@Test
	public void testSystemProperty () {
		System.setProperty("log4j.configuration", "test-config.properties");
		JSOptionsClass objO = new JSOptionsClass();
		assertEquals ("propertyfile name", "test-config.properties",  objO.log4jPropertyFileName.Value());
		
	}

}
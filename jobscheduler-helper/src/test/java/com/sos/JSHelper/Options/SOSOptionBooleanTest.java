package com.sos.JSHelper.Options;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/** \class SOSOptionBooleanTest
 * 
 * \brief SOSOptionBooleanTest -
 * 
 * \details
 *
 * \section SOSOptionBooleanTest.java_intro_sec Introduction
 *
 * \section SOSOptionBooleanTest.java_samples Some Samples
 *
 * \code .... code goes here ... \endcode
 *
 * <p style="text-align:center">
 * <br />
 * --------------------------------------------------------------------------- <br />
 * APL/Software GmbH - Berlin <br />
 * ##### generated by ClaviusXPress (http://www.sos-berlin.com) ######### <br />
 * ---------------------------------------------------------------------------
 * </p>
 * \author KB
 * 
 * @version $Id$02.08.2010 \see reference
 *
 *          Created on 02.08.2010 13:26:50 */

/** @author KB */
public class SOSOptionBooleanTest {

    @SuppressWarnings("unused")
    private final String conClassName = "SOSOptionBooleanTest";
    SOSOptionBoolean objOption = null;

    public SOSOptionBooleanTest() {
        //
    }

    /** \brief setUpBeforeClass
     * 
     * \details
     *
     * \return void
     *
     * @throws java.lang.Exception */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /** \brief tearDownAfterClass
     * 
     * \details
     *
     * \return void
     *
     * @throws java.lang.Exception */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /** \brief setUp
     * 
     * \details
     *
     * \return void
     *
     * @throws java.lang.Exception */
    @Before
    public void setUp() throws Exception {
        objOption = new SOSOptionBoolean(null, ".TestOption", "Title", "true", "true", true);
        objOption.value(true);
        objOption.Value("true");
    }

    /** \brief tearDown
     * 
     * \details
     *
     * \return void
     *
     * @throws java.lang.Exception */
    @After
    public void tearDown() throws Exception {
    }

    /** Test method for
     * {@link com.sos.JSHelper.Options.SOSOptionBoolean#Value(java.lang.String)}
     * . */
    @Test
    public void testValueString() {
        assertEquals("Value must be true", "true", objOption.Value());
        objOption.Value("false");
        assertEquals("Value must be false", "false", objOption.Value());

        // fail("Not yet implemented");
    }

    /** Test method for
     * {@link com.sos.JSHelper.Options.SOSOptionBoolean#SOSOptionBoolean(com.sos.JSHelper.Options.JSOptionsClass, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean)}
     * . */
    @Test
    public void testSOSOptionBoolean() {
        // fail("Not yet implemented");
    }

    /** Test method for
     * {@link com.sos.JSHelper.Options.SOSOptionBoolean#value(boolean)}. */
    @Test
    public void testValueBoolean() {
        // fail("Not yet implemented");
    }

    /** Test method for {@link com.sos.JSHelper.Options.SOSOptionBoolean#value()}
     * . */
    @Test
    public void testValue() {
        objOption.Value("true");
        assertTrue("Value must be true", objOption.value());
        objOption.Value("1");
        assertTrue("Value must be true", objOption.value());
        objOption.Value("false");
        assertFalse("Value must be false", objOption.value());
    }

    /** Test method for
     * {@link com.sos.JSHelper.Options.SOSOptionBoolean#String2Bool(java.lang.String)}
     * . */
    @Test
    public void testString2Bool() {
        // fail("Not yet implemented");
        assertTrue("Must be true", objOption.String2Bool("on"));
        assertTrue("Must be true", objOption.String2Bool("true"));
        assertTrue("Must be true", objOption.String2Bool("1"));
        assertTrue("Must be true", objOption.String2Bool("yes"));

        assertFalse("Must be false", objOption.String2Bool("no"));
        assertFalse("Must be false", objOption.String2Bool("false"));
        assertFalse("Must be false", objOption.String2Bool("0"));
        assertFalse("Must be false", objOption.String2Bool("off"));
    }

    /** Test method for
     * {@link com.sos.JSHelper.Options.SOSOptionBoolean#isTrue()}. */
    @Test
    public void testIsTrue() {
        assertTrue("Value must be true", objOption.isTrue());
        assertTrue("Value must be true", objOption.value() == true);
    }

    /** Test method for
     * {@link com.sos.JSHelper.Options.SOSOptionBoolean#isFalse()}. */
    @Test
    public void testIsFalse() {
        assertFalse("Value must be false", objOption.isFalse());
        objOption.value(false);
        assertTrue("Value must be false", objOption.value() == false);

    }
}

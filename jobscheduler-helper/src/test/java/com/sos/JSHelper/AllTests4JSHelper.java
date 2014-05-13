package com.sos.JSHelper;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.sos.JSHelper.DataElements.JSDataElementDateTest;
import com.sos.JSHelper.DataElements.JSDataElementDateTimeTest;
import com.sos.JSHelper.DataElements.JSDataElementNumericTest;
import com.sos.JSHelper.DataElements.JSDataElementTimeStampISOTest;
import com.sos.JSHelper.Options.JSOptionIndexedItemTest;
import com.sos.JSHelper.Options.JSOptionValueListTest;
import com.sos.JSHelper.Options.JSOptionsClass;
import com.sos.JSHelper.Options.SOSOptionBooleanTest;
import com.sos.JSHelper.Options.SOSOptionElementTest;
import com.sos.JSHelper.Options.SOSOptionIntegerArrayTest;
import com.sos.JSHelper.Options.SOSOptionRegExpTest;
import com.sos.JSHelper.io.Files.JSCsvFileTest;
import com.sos.JSHelper.io.Files.JSFileTest;
import com.sos.JSHelper.io.Files.JSToolBoxTest;
import com.sos.JSHelper.io.Files.JSXMLFileTest;

/**
* \class AllTests4VirtualFileSystem 
* 
* \brief AllTests4VirtualFileSystem - 
* 
* \details
* \see http://www.torsten-horn.de/techdocs/java-junit.htm
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
* \version 24.01.2011
* \see reference
*
* Created on 24.01.2011 12:28:30
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ JSDataElementDateTest.class, JSDataElementDateTimeTest.class,
	JSDataElementNumericTest.class,
	JSDataElementTimeStampISOTest.class,
	/* */
	JSCsvFileTest.class,
	JSFileTest.class,
	JSOptionsClass.class,
	JSToolBoxTest.class,
	JSXMLFileTest.class,
	/* */
	/* */
	JSOptionIndexedItemTest.class,
	JSOptionValueListTest.class,
	SOSOptionBooleanTest.class,
	SOSOptionElementTest.class,
	SOSOptionIntegerArrayTest.class,
	SOSOptionRegExpTest.class
})
public class AllTests4JSHelper{
	// the class remains completely empty,
	// being used only as a holder for the above annotations
}
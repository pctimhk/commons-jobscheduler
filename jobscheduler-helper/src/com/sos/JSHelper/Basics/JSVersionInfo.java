package com.sos.JSHelper.Basics;

/**
* \class JSVersionInfo 
* 
* \brief JSVersionInfo - 
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
* \author KB
* \version 15.06.2011
* \see reference
*
* Created on 15.06.2011 10:55:37
 */

/**
 * @author KB
 *
 */
public class JSVersionInfo {

	private final static String	conClassName		= "JSVersionInfo";
	// private static final Logger logger = Logger.getLogger(JSVersionInfo.class);

	public static final String	conVersionNumber	= "1.6.{properties.timestamp_YDDD}";
	public static final String	conVersionDate		= "{build-date}";
	public static final String	conCopyrightText	= "Copyright 2003-2014 SOS GmbH Berlin";
	public static final String  conSVNVersion = "$Id$";
	public JSVersionInfo() {
		//
	}

	public static final String getVersionString() {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::getVersionString";

		String strRet = conVersionNumber + " (" + conVersionDate + ") " + conCopyrightText;

		return strRet;
	} // private String toString

}
package com.sos.JSHelper.Options;


/**
* \class SOSOptionCommandString
*
* \brief SOSOptionCommandString -
*
* \details
*
* \section SOSOptionCommandString.java_intro_sec Introduction
*
* \section SOSOptionCommandString.java_samples Some Samples
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
* @version $Id$16.05.2010
* \see reference
*
* Created on 16.05.2010 22:36:24
 */

/**
 * @author KB
 *
 */
public class SOSOptionCommandString extends SOSOptionHexString {

	private static final long	serialVersionUID	= 2326011361040152247L;
	private final String		conClassName		= "SOSOptionCommandString";
	public SOSOptionRegExp		command_delimiter	= new SOSOptionRegExp(null, conClassName + ".command_delimiter", // HashMap-Key
															"Command delimiter characters are specified using this par", // Titel
															"%%", // InitiValue
															"%%", // DefaultValue
															true // isMandatory
													);							// Command delimiter characters are specified using this par

	/**
	 * \brief SOSOptionCommandString
	 *
	 * \details
	 *
	 * @param pPobjParent
	 * @param pPstrKey
	 * @param pPstrDescription
	 * @param pPstrValue
	 * @param pPstrDefaultValue
	 * @param pPflgIsMandatory
	 */
	public SOSOptionCommandString(final JSOptionsClass pPobjParent, final String pPstrKey, final String pPstrDescription, final String pPstrValue, final String pPstrDefaultValue,
			final boolean pPflgIsMandatory) {
		super(pPobjParent, pPstrKey, pPstrDescription, pPstrValue, pPstrDefaultValue, pPflgIsMandatory);
	}

	public String[] values() throws Exception {
		// TODO commons lang?
		return this.Value().split(command_delimiter.Value());
	}
	public String[] split() throws Exception {
		// TODO commons lang?
		return this.Value().split(";");
	}
}
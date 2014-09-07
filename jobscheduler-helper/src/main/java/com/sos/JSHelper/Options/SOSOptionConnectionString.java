package com.sos.JSHelper.Options;

/**
* \class JSOptionString 
* 
* \brief JSOptionString - 
* 
* \details
*
* \section JSOptionString.java_intro_sec Introduction
*
* \section JSOptionString.java_samples Some Samples
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
* \author eqbfd
* @version $Id$23.01.2009
* \see reference
*
* Created on 23.01.2009 15:45:36
 */

/**
 * @author eqbfd
 *
 */
public class SOSOptionConnectionString extends SOSOptionStringWVariables {

	private static final long	serialVersionUID	= -7931744980509663560L;
	@SuppressWarnings("unused")
	private final String	conClassName	= "JSOptionString";
	public final String ControlType = "text";

	/**
	 * \brief JSOptionString
	 *
	 * \details
	 *
	 * @param pPobjParent
	 * @param pPstrKey
	 * @param pPstrDescription
	 * @param pPstrValue
	 * @param pPstrDefaultValue
	 * @param pPflgIsMandatory
	 * @throws Exception
	 */
	public SOSOptionConnectionString(final JSOptionsClass pPobjParent, final String pPstrKey, final String pPstrDescription, final String pPstrValue, 
			final String pPstrDefaultValue,
			final boolean pPflgIsMandatory) {
		super(pPobjParent, pPstrKey, pPstrDescription, pPstrValue, pPstrDefaultValue, pPflgIsMandatory);
		
		intOptionType = isOptionTypeString;
		
	}
	
	public SOSOptionConnectionString(final String pstrConnectionString) {
		this(null, "", "", pstrConnectionString, pstrConnectionString, false);
	}
}

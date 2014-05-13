package com.sos.JSHelper.Options;

/**
* \class SOSOptionScript 
* 
* \brief SOSOptionScript - 
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
* \author oh
* @version $Id$
* \see reference
*
* Created on 28.02.2012 01:15:52
 */
public class SOSOptionScript extends SOSOptionString {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -8612680759349930309L;
	@SuppressWarnings("unused")
	private final String		conClassName	= "SOSOptionScript";
	
	/**
	 * \brief SOSOptionSchedulerID
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
	public SOSOptionScript(final JSOptionsClass pPobjParent, final String pPstrKey, final String pPstrDescription, final String pPstrValue, final String pPstrDefaultValue,
		final boolean pPflgIsMandatory) {
		super(pPobjParent, pPstrKey, pPstrDescription, pPstrValue, pPstrDefaultValue, pPflgIsMandatory);
	}
}

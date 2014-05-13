package com.sos.JSHelper.Options;

/**
* \class SOSOptionDBDriver 
* 
* \brief SOSOptionDBDriver - 
* 
* \details
*
* \section SOSOptionDBDriver.java_intro_sec Introduction
*
* \section SOSOptionDBDriver.java_samples Some Samples
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
* Created on 28.02.2012 01:09:43
 */
public class SOSOptionDBDriver extends SOSOptionJavaClassName {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 444334234851227468L;
	@SuppressWarnings("unused")
	private final String		conClassName		= "SOSOptionDBDriver";

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
	public SOSOptionDBDriver(final JSOptionsClass pPobjParent, final String pPstrKey, final String pPstrDescription, final String pPstrValue, final String pPstrDefaultValue,
			final boolean pPflgIsMandatory) {
		super(pPobjParent, pPstrKey, pPstrDescription, pPstrValue, pPstrDefaultValue, pPflgIsMandatory);
	}
}

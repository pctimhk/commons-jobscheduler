package com.sos.JSHelper.Options;
/**
* \class SOSOptionJSTransferMethod
*
* \brief SOSOptionJSTransferMethod -
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
* @version $Id$12.07.2010
* \see reference
*
* Created on 12.07.2010 14:31:02
 */
/**
 * @author KB
 *
 */
public class SOSOptionJSTransferMethod extends SOSOptionStringValueList {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 559611781725991697L;

	public enum enuJSTransferModes {
		tcp("tcp"), udp("udp"), jms("jms"), http("http"), telnet("telnet");

		public final String	description;

		private enuJSTransferModes() {
			this(null);
		}

		private enuJSTransferModes(final String name) {
			String k;
			if (name == null) {
				k = this.name();
			}
			else {
				k = name;
			}
			description = k;
		}
	}
	/**
	 *
	 */
	@SuppressWarnings("unused")
	private final String		conClassName		= "SOSOptionJSTransferMethod";

	/**
	 * \brief SOSOptionJSTransferMethod
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
	public SOSOptionJSTransferMethod(final JSOptionsClass pPobjParent, final String pPstrKey, final String pPstrDescription, final String pPstrValue, final String pPstrDefaultValue,
			final boolean pPflgIsMandatory) {
		super(pPobjParent, pPstrKey, pPstrDescription, pPstrValue, pPstrDefaultValue, pPflgIsMandatory);
		// TODO Auto-generated constructor stub
	}

	/**
	 * \brief SOSOptionJSTransferMethod
	 *
	 * \details
	 *
	 * @param pobjParent
	 * @param pstrIndexedKey
	 * @param pstrDescription
	 * @param pstrDefaultValue
	 * @param pflgIsMandatory
	 */
	public SOSOptionJSTransferMethod(final JSOptionsClass pobjParent, final String pstrIndexedKey, final String pstrDescription, final String pstrDefaultValue, final boolean pflgIsMandatory) {
		super(pobjParent, pstrIndexedKey, pstrDescription, pstrDefaultValue, pflgIsMandatory);
	}

	public boolean isTcp() {
		if (this.Value().equalsIgnoreCase(enuJSTransferModes.tcp.description)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isUdp() {
		if (this.Value().equalsIgnoreCase(enuJSTransferModes.udp.description)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isJMS() {
		if (this.Value().equalsIgnoreCase(enuJSTransferModes.jms.description)) {
			return true;
		}
		else {
			return false;
		}
	}

	public String getDescription() {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::getDescription";
		String strT = "???";
		if (this.isTcp()) {
			strT = enuJSTransferModes.tcp.description;
		}
		if (this.isUdp()) {
			strT = enuJSTransferModes.udp.description;
		}
		if (this.isJMS()) {
			strT = enuJSTransferModes.jms.description;
		}
		return strT;
	} // private String getDescription
}
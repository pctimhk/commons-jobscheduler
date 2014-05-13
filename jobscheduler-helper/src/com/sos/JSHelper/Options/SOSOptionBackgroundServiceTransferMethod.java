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
* @version $Id: SOSOptionJSTransferMethod.java 15777 2011-11-25 12:27:39Z kb $12.07.2010
* \see reference
*
* Created on 12.07.2010 14:31:02
 */
/**
 * @author KB
 *
 */
public class SOSOptionBackgroundServiceTransferMethod extends SOSOptionStringValueList {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 559611781725991697L;

	public enum enuBackgroundServiceTransferMethods {
		tcp("tcp"), udp("udp");

		public final String	description;

		private enuBackgroundServiceTransferMethods() {
			this(null);
		}

		private enuBackgroundServiceTransferMethods(final String name) {
			String k;
			if (name == null) {
				k = this.name();
			}
			else {
				k = name;
			}
			description = k;
		}

		public static String[] getArray () {
			String[] strA = new String[2];
			int i = 0;
			for (enuBackgroundServiceTransferMethods enuType : enuBackgroundServiceTransferMethods.values()) {
				strA[i++] = enuType.name();
			}
			return strA;
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
	public SOSOptionBackgroundServiceTransferMethod(final JSOptionsClass pPobjParent, final String pPstrKey, final String pPstrDescription, final String pPstrValue, final String pPstrDefaultValue,
			final boolean pPflgIsMandatory) {
		super(pPobjParent, pPstrKey, pPstrDescription, pPstrValue, pPstrDefaultValue, pPflgIsMandatory);
		super.valueList(enuBackgroundServiceTransferMethods.getArray());
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
	public SOSOptionBackgroundServiceTransferMethod(final JSOptionsClass pobjParent, final String pstrIndexedKey, final String pstrDescription, final String pstrDefaultValue, final boolean pflgIsMandatory) {
		super(pobjParent, pstrIndexedKey, pstrDescription, pstrDefaultValue, pflgIsMandatory);
	}

	public boolean isTcp() {
		if (this.Value().equalsIgnoreCase(enuBackgroundServiceTransferMethods.tcp.description)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isUdp() {
		if (this.Value().equalsIgnoreCase(enuBackgroundServiceTransferMethods.udp.description)) {
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
			strT = enuBackgroundServiceTransferMethods.tcp.description;
		}
		if (this.isUdp()) {
			strT = enuBackgroundServiceTransferMethods.udp.description;
		}
		return strT;
	} // private String getDescription
}
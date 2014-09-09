package com.sos.JSHelper.DataElements;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
* \class JSDataElementDateTime
*
* \brief JSDataElementDateTime -
*
* \details
*
* \section JSDataElementDateTime.java_intro_sec Introduction
*
* \section JSDataElementDateTime.java_samples Some Samples
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
* @version $Id$11.02.2009
* \see reference
*
* Created on 11.02.2009 16:03:19
 */

/**
 * @author eqbfd
 *
 */
public class JSDataElementDateTime extends JSDataElement {

	private final String		conClassName			= "JSDataElementDateTime";

	protected Date				dteDateTime				= new Date();

	public static final String	SIMPLE_DATE_FORMAT		= "yyyy-MM-dd";
	public static final String	FULL_DATETIME_FORMAT	= "yyyy-MM-dd HH:mm:ss";
	private final String				strDateFormat			= "yyyy.MM.dd HH:mm:ss";

	private String				strTimestamp			= null;					// "yyyy.MM.dd hh:mm:ss"

	// private int intYearPos = 0;
	// private int intMonthPos = 5;
	// private final int intDayPos = 8;
	// private final int intHoursPos = 11;
	// private final int intMinutePos = 14;
	// private final int intSecondsPos = 17;

	private SimpleDateFormat	dateFormatter			= null;

	public JSDataElementDateTime() {
		//
	}

	public JSDataElementDateTime(final Date pdteDateTime) {
		dteDateTime = pdteDateTime;
	}

	/**
	 * \brief JSDataElementDateTime
	 *
	 * \details
	 *
	 * @param pPstrValue
	 */
	public JSDataElementDateTime(final String pstrValue) {
		super(pstrValue);

		String2Date(pstrValue);
		// dateFormatter = new SimpleDateFormat(strDateFormat);
		// strTimestamp = dateFormatter.format(pobjTimestamp);
		// SignalDebug("JSTimestamp(java.sql.Timestamp), timestamp=" + strTimestamp, JSListenerClass.DEBUG_LEVEL9);

	}

	/**
	 *
	 * \brief String2Date
	 *
	 * \details
	 *
	 * \return void
	 *
	 * @param pstrDateTime
	 */
	private void String2Date(final String pstrDateTime) {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::String2Date";

		DateFormat objDF = DateFormat.getDateTimeInstance();
		try {
			dteDateTime = objDF.parse(pstrDateTime);

		} // try
		catch (Exception objException) {
			super.Value("");
			dteDateTime = null;
		}
		finally {
			//
		} // finally

	} // private void String2Date

	/**
	 * \brief JSDataElementDateTime
	 *
	 * \details
	 *
	 * @param pPstrValue
	 * @param pPstrDescription
	 */
	public JSDataElementDateTime(final String pPstrValue, final String pPstrDescription) {
		super(pPstrValue, pPstrDescription);
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 * \brief doInit
	 *
	 * \details

	 * \return void
	 *
	 */
	@Override
	public void doInit() {
		// super.doInit();
//  strDateFormat isn't initialized in com.sos.JSHelper.DataElements.JSDataElementDateTime.doInit() when invoked from constructor for superclass [Scary(8), High confidence]
//		super.FormatString(strDateFormat);
		super.FormatString("yyyy.MM.dd HH:mm:ss");
		super.Description("DateAndTime");
		super.ColumnHeader("DateTime");
		super.XMLTagName("DateTime");
	}

	/**
	 *
	 * \brief Value
	 *
	 * \details
	 *
	 * \return
	 *
	 * @param pstrDateTime
	 */
	@Override
	public void Value(final String pstrDateTime) {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::Value";

		String2Date(pstrDateTime);
		super.Value(pstrDateTime);

	} // public void Value}

	public void Value(final Date pdteDateTime) {
		dteDateTime = pdteDateTime;
		super.Value(this.FormattedValue());
	}

	public Date value() {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::value";

		return dteDateTime;
	} // public Date value}

	/**
	 *
	 * \brief FormattedValue - Liefert den Wert des Elements formatiert
	 *
	 * \details
	 * das Format (die Edit-Maske) wird �ber die Eigenschaft FormatString
	 * definiert.
	 *
	 * Wenn kein Format-String definiert ist, so wird der Wert als String
	 * zur�ckgegeben.
	 *
	 * \return String
	 *
	 * @return
	 */
	@Override
	public String FormattedValue() {

		if (dteDateTime == null) {
			strTimestamp = "";
		}
		else {
			if (isEmpty(this.FormatString())) {
				this.FormatString(FULL_DATETIME_FORMAT);
			}

			dateFormatter = new SimpleDateFormat(this.FormatString());
			strTimestamp = dateFormatter.format(dteDateTime);
		}
		return strTimestamp;
	}

	/**
	 *
	 * \brief ActualDateTime
	 *
	 * \details
	 *
	 * \return Date
	 *
	 * @return
	 */
	public Date ActualDateTime() {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::ActualDateTime";

		Calendar now = Calendar.getInstance();
		return now.getTime();

	} // public Date ActualDateTime}
}

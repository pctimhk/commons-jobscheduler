package com.sos.JSHelper.DataElements;

import java.text.ParseException;

import com.sos.JSHelper.Exceptions.FormatPatternException;

/**
 * \class JSTimeStampISO
 *
 * \brief JSTimeStampISO -
 *
 * \details
 *
 * \section JSTimeStampISO.java_intro_sec Introduction
 *
 * \section JSTimeStampISO.java_samples Some Samples
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
* @version $Id$16.06.2009
 * \see reference
 *
 * Created on 16.06.2009 12:16:33
 */

/**
 * @author eqbfd
 *
 */
public class JSDataElementTimeStampISO extends JSDataElementDate {

	private final String	conClassName	= "JSDataElementTimeStampISO";

	JSDataElementTimeStampISO() {
		init();
		//
	}

	private void init() {

		@SuppressWarnings("unused")
		final String	conMethodName	= conClassName + "::init";

//		this.XMLTagName(JSXMLTagNames.conStockLevelTimeStamp);
		setParsePattern(JSDateFormat.dfTIMESTAMPS);
		setFormatPattern(JSDateFormat.dfISO);


//		return void;
	} // private void init
	/**
	 * \brief JSTimeStampISO
	 *
	 * \details
	 *
	 * @param pPstrValue
	 */
	public JSDataElementTimeStampISO(final String pPstrValue) {
		super(pPstrValue);
		init();
	}

	public JSDataElementTimeStampISO(final JSDataElementDate pdteDate) {
		this(pdteDate.Value());
	}
	/**
	 * \brief JSTimeStampISO
	 *
	 * \details
	 *
	 * @param pPstrValue
	 * @param pPstrDescription
	 */
	public JSDataElementTimeStampISO(final String pPstrValue, final String pPstrDescription) {
		super(pPstrValue, pPstrDescription);
		init();
	}

	/**
	 * \brief JSTimeStampISO
	 *
	 * \details
	 *
	 * @param pPstrValue
	 * @param pPstrDescription
	 * @param pPintSize
	 * @param pPintPos
	 * @param pPstrFormatString
	 * @param pPstrColumnHeader
	 * @param pPstrXMLTagName
	 */
	public JSDataElementTimeStampISO(final String pPstrValue, final String pPstrDescription, final int pPintSize, final int pPintPos, final String pPstrFormatString,
			final String pPstrColumnHeader, final String pPstrXMLTagName) {
		super(pPstrValue, pPstrDescription, pPintSize, pPintPos, pPstrFormatString, pPstrColumnHeader, pPstrXMLTagName);
	}

	@Override
	public String FormattedValue()  {
//		final String strT = Value();
//		if (FormatString().equals(JSDateFormat.dfDATE.toPattern()) 
//				|| FormatString().equals(JSDateFormat.dfDATE_SHORT.toPattern())) {
//			strT = strT.substring(0, 9 + 1);
//		}
		try {
			this.Value(ValueISO());
		}
		catch (final Exception objException) {
			/**
			 * Original-Wert zur�cliefern, wenn es nicht konvertiert werden kann.
			 */
		}

		return this.Value();
	}

	public String  ValueISO() throws Exception {

		final String	conMethodName	= conClassName + "::Value";

		setParsePattern(JSDateFormat.dfTIMESTAMPS);
		setFormatPattern(JSDateFormat.dfISO);

		try {
			getParsePattern().parse(this.Value());
		}
		catch (final ParseException e) {
			throw new FormatPatternException(conMethodName + ": the value '" + this.Value() + "' does not correspond with the pattern " + getParsePattern().toPattern());
		}

		final JSDateFormat df = new JSDateFormat(JSDateFormat.dfISO.toPattern());
		return df.format(getDateObject());

	} // public String  Value}

}
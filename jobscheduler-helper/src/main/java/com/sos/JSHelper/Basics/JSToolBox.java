package com.sos.JSHelper.Basics;

import java.io.BufferedWriter;
import java.io.File;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import com.sos.JSHelper.Exceptions.JobSchedulerException;
import com.sos.JSHelper.Listener.JSListenerClass;
import com.sos.localization.Messages;

/**
 * \class JSToolBox
 *
 * \brief JSToolBox - Klasse mit kleinen Helferleins
 *
 * \details
 * Diese Klasse ist immer als SuperKlasse zu verwenden und stellt eine Reihe von Methoden bereit, die immer wieder benoetigt werden
 *
 * \section JSToolBox_intro_sec Introduction
 *
 * \section JSToolBox_samples Some Samples
 *
 * \code
 *   .... code goes here ...
 * \endcode
 *
 * <p style="text-align:center">
 * <br />---------------------------------------------------------------------------
 * <br /> APL/Software GmbH - Berlin
 * <br />##### generated by ClaviusXPress (http://www.sos-berlin.com) #########
 * <br />Sonntag, 26. Oktober 2008, sgx2343 (sgx2343)
 * <br />---------------------------------------------------------------------------
 * </p>
 * \author sgx2343
* @version $Id: JSToolBox.java 28072 2014-11-11 15:26:37Z oh $0.9
 * \see reference
 *
 */

public class JSToolBox extends JSListenerClass {
	private static final String	conEnvVarSOS_LOCALE	= "SOS_LOCALE";
	@SuppressWarnings("hiding")
	private final String	conClassName			= "JSToolBox";
	protected final String	EMPTY_STRING			= "";
	BufferedWriter			objOut					= null;
	protected Messages		Messages				= null;
	protected boolean		flgStackTracePrinted	= false;

	public JSToolBox() {
//		BasicConfigurator.configure();
	} // public JSToolBox

	public JSToolBox(final String pstrResourceBundleName) {
		setMessageResource(pstrResourceBundleName);
	} // public JSToolBox

	public JSToolBox(final String pstrResourceBundleName, final String pstrLocale) {
		Messages = new Messages(pstrResourceBundleName, new Locale(pstrLocale));
	} // public JSToolBox

	public Messages getMessageObject() {
		return Messages;
	}

	public String getMethodName() {

		try {
			StackTraceElement trace[] = new Throwable().getStackTrace();
			String lineNumber = trace[1].getLineNumber() > 0 ? "(" + trace[1].getLineNumber() + ")" : "";
			return trace[1].getClassName() + "." + trace[1].getMethodName() + lineNumber;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return "";

	}

	/**
	 * Liefert den Namen der Klasse zurck
	 *
	 * @return String Name der Klasse
	 * @throws java.lang.Exception
	 */
	public String getClassName() throws Exception {
		StackTraceElement trace[] = new Throwable().getStackTrace();
		return trace[1].getClassName();
	}


	protected void setMessageResource(final String pstrResourceBundleName) {
		String strSOSLocale = System.getenv(conEnvVarSOS_LOCALE);
		if (strSOSLocale == null) {
			Messages = new Messages(pstrResourceBundleName, Locale.getDefault());
		}
		else {
			Messages = new Messages(pstrResourceBundleName, new Locale(strSOSLocale));
		}
	}

	/**
	 *
	 * \brief MakeFullPathName
	 *
	 * \details
	 *
	 * \return String
	 *
	 * @param pstrPathname
	 * @param pstrFileName
	 * @return
	 */
	protected String MakeFullPathName(final String pstrPathname, final String pstrFileName) {
		String strT = pstrFileName;
		String normalizedFilename = pstrFileName.replace('\\', '/');
		String normalizedPathname = pstrPathname.replace('\\', '/');

		if (normalizedFilename.startsWith(normalizedPathname)) {
			//
		}
		else {
			if (normalizedPathname.endsWith("/")) {
				strT = pstrPathname + strT;
			}
			else {
				strT = pstrPathname + "/" + strT;
			}
		}

		return strT;
	}

	/**
	 *
	 * \brief getI18N
	 *
	 * \details
	 *
	 * \return String
	 *
	 * @param pstrI18NKey
	 * @return
	 */
	public String getI18N(final String pstrI18NKey) {
		String strM = Messages.getMsg(pstrI18NKey);
		strM = pstrI18NKey + ": " + strM;
		return strM;
	}

	/**
	 *
	 * \brief Bool2String
	 *
	 * \details
	 *
	 * \return String
	 *
	 * @param pflgFlag
	 * @return
	 */
	public String boolean2String(final boolean pflgFlag) {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::Bool2String";

		String strRet = "true";
		if (pflgFlag == false) {
			strRet = "false";
		}

		return strRet;
	} // private String Bool2String

	/**
	 *
	 * \brief AddSingleQuotes
	 *
	 * \details
	 *
	 * \return String
	 *
	 * @param pstrS
	 */
	public String AddSingleQuotes(final String pstrS) {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::AddSingleQuotes";

		final String strT = "'" + pstrS.replaceAll("'", "''") + "'";

		return strT;
	} // private String AddSingleQuotes

	/**
	 *
	 * \brief AddQuotes
	 *
	 * \details
	 *
	 * \return String
	 *
	 * @param pstrS
	 * @return
	 */
	protected String AddQuotes(final String pstrS) {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::AddQuotes";

		return "\"" + Quotes2DoubleQuotes(pstrS) + "\"";

	} // private String AddQuotes

	/**
	 *
	 * \brief Quotes2DoubleQuotes
	 *
	 * \details
	 *
	 * \return String
	 *
	 * @param pstrS
	 * @return
	 */
	protected String Quotes2DoubleQuotes(final String pstrS) {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::Quotes2DoubleQuotes";
		String strT = pstrS;
		if (strT != null) {
			strT = pstrS.replaceAll("\"", "\"\"");
		}

		return strT;
	} // private String Quotes2DoubleQuotes

	/**
	 *
	 * \brief toDouble
	 *
	 * \details
	 *
	 * \return double
	 *
	 * @param pstrV
	 * @return
	 * @throws Exception
	 */
	protected double toDouble(final String pstrV) throws Exception {
		final String conMethodName = conClassName + "::toDouble";
		double dblT = 0.0;
		String strT = pstrV.trim();
		final int intLen = strT.length();
		if (intLen > 0) {
			if (strT.endsWith("-")) {
				strT = "-" + strT.substring(0, intLen - 1);
			}
			else {
				if (strT.startsWith("+")) {
					strT = strT.substring(1, intLen);
				}
				else {
					if (strT.endsWith("+")) {
						strT = strT.substring(0, intLen - 1);
					}
				}
			}
			try {
				dblT = new Double(strT);
			} // try
			catch (final Exception objException) {
				try {
					// DecimalFormat decimalF = new DecimalFormat();
					// DecimalFormatSymbols objDFS = decimalF.getDecimalFormatSymbols();
					// objDFS.setDecimalSeparator('.');
					// objDFS.setGroupingSeparator(',');
					// System.out.println("DecimalSeparator = " + objDFS.getDecimalSeparator());
					// System.out.println("GroupingSeparator = " + objDFS.getGroupingSeparator());
					// numberF.setParseBigDecimal(true);
					final NumberFormat numberF = NumberFormat.getNumberInstance(Locale.GERMAN);
					numberF.setGroupingUsed(false);
					numberF.setParseIntegerOnly(false);
					numberF.setMaximumFractionDigits(5);
					numberF.setMinimumFractionDigits(0);
					final Number number = numberF.parse(strT);
					dblT = number instanceof Double ? number.doubleValue() : new Double(number.doubleValue());
				}
				catch (final ParseException e) {
					try {
						final NumberFormat numberF = NumberFormat.getNumberInstance(Locale.US);
						numberF.setGroupingUsed(false);
						numberF.setParseIntegerOnly(false);
						final Number number = numberF.parse(strT);
						dblT = number instanceof Double ? number.doubleValue() : new Double(number.doubleValue());
					}
					catch (final ParseException e1) {
						e1.printStackTrace();
						SignalError(conMethodName + ": could not convert '" + strT + "' to double");
					}
				}

				catch (final NumberFormatException e) {
					SignalError(conMethodName + ": could not convert '" + strT + "' to double");
					dblT = 0.0;
				}
			}
			finally {
				//
			} // finally
		}

		return dblT;
	}

	/**
	 *
	 * \brief CreationTimeStamp
	 *
	 * \details
	 *
	 * \return String
	 *
	 * @return
	 */
	public String CreationTimeStamp() {
		return getISODate();
	}

	/**
	 *
	 * \brief CreationTimeStamp
	 *
	 * \details
	 *
	 * \return String
	 *
	 * @param pstrDate
	 * @return
	 * @throws Exception
	 */
	public String CreationTimeStamp(final String pstrDate) throws Exception {
		return this.CreationTimeStamp(pstrDate, "010203");
	}

	/**
	 * \brief Liefert einen Zeitstempel in der Form YYYY-MM-DDTHH:MM:SS
	 *
	 * Fuer ein anzugebendes Datum und eine anzugebende Uhrzeit wird ein Zeitstempel im
	 * ISO-Datumsformat geliefert.
	 *
	 * \return String - YYYY-MM-DDTHH:MM:SS
	 *
	 * @param pstrDate - Datum in der Form YYYYMMDD
	 * @param pstrTime Zeit in der Form HHMMSS
	 * @throws Exception
	 */
	public String CreationTimeStamp(final String pstrDate, final String pstrTime) throws Exception {
		/*
		 * pstrDate is YYYYMMDD
		 */
		String strD = "";
		String strT = "";

		// ToDo: check for valid date and time values
		if (pstrDate.length() > 0 && pstrTime.length() > 0) {
			try {
				@SuppressWarnings("unused")
				long lngTemp = Integer.parseInt(pstrDate);
				lngTemp = Integer.parseInt(pstrTime);
				strD = pstrDate.substring(0, 3 + 1) + "-" + pstrDate.substring(4, 5 + 1) + "-" + pstrDate.substring(6, 7 + 1) + "T";
				strT = pstrTime.substring(0, 1 + 1) + ":" + pstrTime.substring(2, 3 + 1) + ":" + pstrTime.substring(4, 5 + 1);
			}
			catch (final RuntimeException e) {
				// nothing to do. Date and/or Time not valid?
				strD = pstrDate + "T";
				strT = "??:??:??";
			}
		}
		return strD + strT;
	}

	/**
	 *
	 * \brief getDateTimeFormatted
	 *
	 * \details
	 *
	 * \return
	 *
	 * @param pstrEditMask
	 * @return
	 */
	@Override
	public String getDateTimeFormatted(final String pstrEditMask) {
		final java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(pstrEditMask);
		final java.util.Calendar now = java.util.Calendar.getInstance();
		return formatter.format(now.getTime()).toString();
	}

	public Date Now() {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::Now";

		final java.util.Calendar now = java.util.Calendar.getInstance();
		return now.getTime();
	} // public Date Now}

	/**
	 *
	 * \brief getISODate
	 *
	 * \details
	 *
	 * \return
	 *
	 * @return
	 */
	@Override
	public String getISODate() {
		return getDateTimeFormatted("yyyy-MM-dd'T'HH:mm:ss");
	}

	/**
	 *
	 * \brief getTime
	 *
	 * \details
	 *
	 * \return
	 *
	 * @return
	 */
	@Override
	public String getTime() {
		return getDateTimeFormatted("HH:mm:ss");
	}

	/**
	 *
	 * \brief getHHIISS
	 *
	 * \details
	 *
	 * \return String
	 *
	 * @return
	 */
	public String getHHIISS() {
		return getDateTimeFormatted("HHmmss");
	}

	/**
	 *
	 * \brief getDate
	 *
	 * \details
	 *
	 * \return String
	 *
	 * @return
	 */
	public String getDate() {
		return getDateTimeFormatted("yyyy-MM-dd");
	}

	/**
	 *
	 * \brief getYYYYMMDD
	 *
	 * \details
	 *
	 * \return String
	 *
	 * @return
	 */
	public String getYYYYMMDD() {
		return getDateTimeFormatted("yyyyMMdd");
	}

	/**
	 *
	 * \brief Liefert den Wert einer Environment Variable
	 *
	 * \details
	 *
	 * \return String - der Wert der Variablen oder null
	 *
	 * @param pstrVariableName - Der Name der Variablen, deren Wert geliefert werden soll
	 * @throws Exception
	 */
	public String EnvironmentVariable(final String pstrVariableName) {

		final String conMethodName = conClassName + "::EnvironmentVariable";

		String strValue = null;

		if (isNotEmpty(pstrVariableName)) {
			strValue = System.getenv(pstrVariableName);
			if (isNotEmpty(strValue)) {
				SignalDebug(String.format("%s: %s = %s", conMethodName, pstrVariableName, strValue));
			}
			else {
				strValue = System.getProperty(pstrVariableName);
			}
			if (isNotEmpty(strValue)) {
				strValue = StripQuotes(strValue);
			}
		}

		return strValue;
	}

	public String StripQuotes(final String pstrS) {
		String strR = pstrS;
		//		if (pstrS.substring(0, 1).equals("\"") && pstrS.substring(pstrS.length() - 1).equals("\"")) {
		if (pstrS.startsWith("\"") && pstrS.endsWith("\"")) {
			strR = pstrS.substring(1, pstrS.length() - 1);
			strR = strR.replaceAll("\"\"", "\"");
		}
		return strR;
	}


	/**
	 * \brief vereinfachter Stringvergleich
	 *
	 * \details
	 * Liefert "true", wenn beide Strings einen unterschiedlichen Wert haben oder sich nicht
	 * vergleichen lassen, weil null-pointer.
	 *
	 * Es wird immer mit equalsIgnoreCase verglichen.
	 *
	 * \return boolean true, wenn beide Strings einen unterschiedlichen Wert haben
	 *
	 * @param pstrActual
	 * @param pstrNew
	 */
	protected boolean isNotEqual(final String pstrActual, final String pstrNew) {
		Boolean flgT = false;
		if (pstrActual == null && pstrNew == null) {
			flgT = false;
		}
		else {
			if (pstrActual == null || !pstrActual.equalsIgnoreCase(pstrNew.toString())) {
				flgT = true;
			}
		}
		return flgT;
	}

	/**
	 * @brief Helperfunktion - liefert true, wenn String weder null noch leer
	 *
	 * \details
	 * StringObjekt auf null/empty pruefen
	 *
	 * @param pstrValue zu pruefendes Stringobjekt
	 *
	 * @return boolean true, wenn String sinnvollen Wert enthaelt.
	 */

	public boolean isNotEmpty(final String pstrValue) {
		return pstrValue != null && pstrValue.trim().length() > 0;
	}

	public boolean isNotEmpty(final StringBuffer pstrS) {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::isNotEmpty";

		if (pstrS != null && pstrS.length() > 0) {
			return true;
		}
		return false;
	} // private boolean isNotEmpty

	/**
	 * \brief Helperfunktion - liefert true, wenn String null oder leer ist
	 *
	 * StringObjekt auf null/empty pruefen
	 *
	 * \return boolean
	 *
	 * @param pstrFileName
	 */
	protected boolean isEmpty(final String pstrFileName) {
		return pstrFileName == null || pstrFileName.length() <= 0;
	}

	public boolean isNotNull(final Object pobjObject) {
		return pobjObject != null;
	}

	public boolean isNull(final Object pobjObject) {
		return pobjObject == null;
	}

	/**
	 * \brief liefert den Wert eines String-Objekts oder ein leeres Objekt
	 *
	 * \return String
	 *
	 * @param pstrS
	 */
	protected String notNull(final String pstrS) {
		if (pstrS == null) {
			return "";
		}
		else {
			return pstrS;
		}
	}

	public final static String repeatString(final String str, int length) {
		final StringBuffer sb = new StringBuffer();
		if (str != null) {
			while (length > 0) {
				sb.append(str);
				--length;
			}
		}
		return sb.toString();
	}

	public void raiseJSException(final String pstrExceptionText) throws Exception {
		throw new JobSchedulerException(pstrExceptionText);
	}

	/**
	 *
	 * \brief StackTrace2String
	 *
	 * \details
	 * This Method creates a String with all infos from the stack as a trace.
	 *
	 * \return String
	 *
	 * @param e
	 */
	public String StackTrace2String(final Exception e) {

		String strT = e.getMessage() + "\n";
		final StackTraceElement arrStack[] = e.getStackTrace();
		for (final StackTraceElement objS : arrStack) {
			strT += objS.toString() + "\n";
		}

		return strT;
	} // void ShowStackTrace (Exception e)

	public static void notImplemented() {
		throw new JobSchedulerException("Method/Functionality not implemented presently.");
	}

	public String addFileSeparator(final String str) {
		return str.endsWith("/") || str.endsWith("\\") ? str : str + "/";
	}

	public String adjustFileSeparator(final String pstrValue) {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::adjustFileSeparator";
		String str = pstrValue;
		if (str.startsWith("\\\\") == true || str.startsWith("//")) {  // UNC name, don'T touch

		}
		else {
		 str = pstrValue.replaceAll("\\\\", "/");
		str = str.replaceAll("//", "/");
		}


//		return addFileSeparator(str);  // no. could a be a filename and then it leads into troubles (File not found).
		return str;
	} // private String adjustFileSeparator


    public static URL fileAsURL(final File file) {
        URL result = null;
        try {
            result = file.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new JobSchedulerException("Error to convert file object to URL.",e);
        }
        return result;
    }

    public static File urlAsFile(final URL url) {
        File result = null;
        try {
            result = new File(url.toURI());
        } catch (URISyntaxException e) {
            throw new JobSchedulerException("Error to convert URL object to File.",e);
        }
        return result;
    }

	protected String BigInt2String(final BigInteger pbigI) {
		if (pbigI != null) {
			long lngT = pbigI.longValue();
			return String.valueOf(lngT);
		}
		return "";
	}

	/**
	 *
	 * \brief String to Boolean
	 *
	 * \details
	 *
	 * \return boolean
	 *
	 * @param pstrS
	 */
	public boolean String2Bool(final String pstrVal) {
		boolean flgT = false;

		if (isNotEmpty(pstrVal)) {
			if (pstrVal.equals("1") || pstrVal.equalsIgnoreCase("y") || pstrVal.equalsIgnoreCase("yes") || pstrVal.equalsIgnoreCase("j")
					|| pstrVal.equalsIgnoreCase("on") || pstrVal.equalsIgnoreCase("true") || pstrVal.equalsIgnoreCase("wahr")) {
				flgT = true;
			}
		}

		return flgT;

	}
	
	
	public static String escapeHTML(final String s){
		   StringBuffer sb = new StringBuffer();
		   int n = s.length();
		   for (int i = 0; i < n; i++) {
		      char c = s.charAt(i);
		      switch (c) {
			         case '<': sb.append("&lt;"); break;
			         case '>': sb.append("&gt;"); break;
			         case '&': sb.append("&amp;"); break;
			         case '"': sb.append("&quot;"); break;
			         case '\u00E0': sb.append("&agrave;");break;
			         case '\u00C0': sb.append("&Agrave;");break;
			         case '\u00E2': sb.append("&acirc;");break;
			         case '\u00C2': sb.append("&Acirc;");break;
			         case '\u00E4': sb.append("&auml;");break;
			         case '\u00C4': sb.append("&Auml;");break;
			         case '\u00E5': sb.append("&aring;");break;
			         case '\u00C5': sb.append("&Aring;");break;
			         case '\u00E6': sb.append("&aelig;");break;
			         case '\u00C6': sb.append("&AElig;");break;
			         case '\u00E7': sb.append("&ccedil;");break;
			         case '\u00C7': sb.append("&Ccedil;");break;
			         case '\u00E9': sb.append("&eacute;");break;
			         case '\u00C9': sb.append("&Eacute;");break;
			         case '\u00E8': sb.append("&egrave;");break;
			         case '\u00C8': sb.append("&Egrave;");break;
			         case '\u00EA': sb.append("&ecirc;");break;
			         case '\u00CA': sb.append("&Ecirc;");break;
			         case '\u00EB': sb.append("&euml;");break;
			         case '\u00CB': sb.append("&Euml;");break;
			         case '\u00EF': sb.append("&iuml;");break;
			         case '\u00CF': sb.append("&Iuml;");break;
			         case '\u00F4': sb.append("&ocirc;");break;
			         case '\u00D4': sb.append("&Ocirc;");break;
			         case '\u00F6': sb.append("&ouml;");break;
			         case '\u00D6': sb.append("&Ouml;");break;
			         case '\u00F8': sb.append("&oslash;");break;
			         case '\u00D8': sb.append("&Oslash;");break;
			         case '\u00DF': sb.append("&szlig;");break;
			         case '\u00F9': sb.append("&ugrave;");break;
			         case '\u00D9': sb.append("&Ugrave;");break;
			         case '\u00FB': sb.append("&ucirc;");break;
			         case '\u00DB': sb.append("&Ucirc;");break;
			         case '\u00FC': sb.append("&uuml;");break;
			         case '\u00DC': sb.append("&Uuml;");break;
			         case '\u00AE': sb.append("&reg;");break;
			         case '\u00A9': sb.append("&copy;");break;
			         case '\u20AC': sb.append("&euro;"); break;
			         // be carefull with this one (non-breaking white space)
			         case ' ': sb.append("&#160;");break;

			         default:  sb.append(c); break;
			      }
		   }
		   return sb.toString();
		}

} // public class JSToolBox

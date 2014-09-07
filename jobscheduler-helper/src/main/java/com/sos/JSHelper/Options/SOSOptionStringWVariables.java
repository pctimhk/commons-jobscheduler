package com.sos.JSHelper.Options;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sos.JSHelper.DataElements.JSDataElementDate;
import com.sos.JSHelper.Exceptions.JobSchedulerException;
import com.sos.JSHelper.System.SOSCommandline;
import com.sos.JSHelper.io.Files.JSFile;

/**
* \class JSOptionFileName
*
* \brief JSOptionFileName -
*
* \details
*
* \section JSOptionFileName.java_intro_sec Introduction
*
* \section JSOptionFileName.java_samples Some Samples
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
* Created on 23.01.2009 16:58:58
 */
/**
 * @author eqbfd
 *
 */
public class SOSOptionStringWVariables extends SOSOptionElement {
	private static final long	serialVersionUID	= 3890065543134955852L;
	private final String		conClassName		= "SOSOptionFileName";
	protected String			strOriginalValue	= "";

	/**
	 * \brief SOSOptionStringWVariables
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
	public SOSOptionStringWVariables(final JSOptionsClass pPobjParent, final String pPstrKey, final String pPstrDescription, final String pPstrValue,
			final String pPstrDefaultValue, final boolean pPflgIsMandatory) {
		super(pPobjParent, pPstrKey, pPstrDescription, pPstrValue, pPstrDefaultValue, pPflgIsMandatory);
		intOptionType = isOptionTypeString;
	}

	public SOSOptionStringWVariables(final String pstrValue) {
		this(null, "", "", pstrValue, pstrValue, false);
	}

	private boolean hasPlaceHolder(final String pstrString) {
		boolean flgRet = false;
		int intKlAuf = pstrString.indexOf("[");
		int intKlZu = pstrString.indexOf("]");
		if (intKlAuf > -1 && intKlZu > -1 && intKlZu > intKlAuf) {
			flgRet = true;
		}
		return flgRet;
	}

	public final String substituteAllDate() throws Exception {
		// TODO substituteAllDate in JSFile abhandeln
		//		String targetFileName = new File(strValue).getPath();  // nicht verwenden:
		//  hier werden die "/" brutal in "\" umgebogen bei JADE
		String temp = OriginalValue();
		if (hasPlaceHolder(strOriginalValue)) {
			String targetFileName = strValue;
			temp = substituteDateMask();
			while (!targetFileName.equals(temp)) {
				targetFileName = temp;
				temp = substituteDateMask();
			}
		}
		else {
		}
		return temp;
	}

	private String substituteDateMask() throws Exception {
		final String conVarName = "[date:";
		String targetFilename = OriginalValue();
		try {
			// check for a date format string given in the file mask
			if (targetFilename.matches("(.*)(\\" + conVarName + ")([^\\]]*)(\\])(.*)")) {
				int posBegin = targetFilename.indexOf(conVarName);
				if (posBegin > -1) {
					int posEnd = targetFilename.indexOf("]", posBegin + 6);
					if (posEnd > -1) {
						String strDateMask = targetFilename.substring(posBegin + 6, posEnd);
						if (strDateMask.length() <= 0) {
							strDateMask = SOSOptionTime.dateTimeFormat;
						}
						String strDateTime = SOSOptionTime.getCurrentTimeAsString(strDateMask);
						String strT = (posBegin > 0 ? targetFilename.substring(0, posBegin) : "") + strDateTime;
						if (targetFilename.length() > posEnd) {
							strT += targetFilename.substring(posEnd + 1);
						}
						targetFilename = strT;
					}
				}
			}
			return targetFilename;
		}
		catch (Exception e) {
			throw new JobSchedulerException("error substituting [date:]: " + e.getMessage(), e);
		}
	}

	public String OriginalValue() {
		String strT = strOriginalValue;
		if (isEmpty(strT)) {
			strT = strValue;
		}
		return strT;
	}

	public void doReSubstitution() {
		try {
			String strT = substituteAllDate();
			super.Value(strT);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override public String Value() {
		String strT = strValue;
		try {
			if (objParentClass != null) {
				if (objParentClass.gflgSubsituteVariables == true) {
					strT = doReplace();
				}
			}
			else {
				strT = doReplace();
			}
		}
		catch (Exception e) {
			throw new JobSchedulerException(e);
		}
		return strT;
	}

	@Override public void Value(final String pstrStringValue) {
		@SuppressWarnings("unused") final String conMethodName = conClassName + "::Value";
		strOriginalValue = pstrStringValue;
		super.Value(pstrStringValue);
	} // public void Value

	/**
	 *
	 * \brief doReplace
	 *
	 * \details
	 *
	 * \return String
	 *
	 * @param pstrSourceString
	 * @param pstrReplacementPattern
	 * @return
	 * @throws Exception
	 */
	public String doReplace(/* final String pstrSourceString, final String pstrReplacementPattern */) {
		final String conMethodName = conClassName + "::doReplace";
		try {
			strValue = OriginalValue();
			strValue = substituteAllDate(strValue);
			//			strValue = substituteAllFilename(strValue, pstrSourceString);
			// TODO allow timestamp: as an alternative date-pattern
			strValue = substituteTimeStamp(strValue);
			// TODO implement uuid: as an additional pattern for substitution.
			strValue = substituteUUID(strValue);
			// TODO implement sqltimestamp: as an additional pattern for substitution
			strValue = substituteSQLTimeStamp(strValue);
			strValue = substituteTempFile(strValue);
			strValue = substituteSQLTimeStamp(strValue);
			strValue = substituteEnvironmenVariable(strValue);
			strValue = substituteFileContent(strValue);
			strValue = substituteShell(strValue);
			// // should any opening and closing brackets be found in the file name, then this is an error
			//			Matcher m = Pattern.compile("\\[[^\\]]*\\]").matcher(strValue);
			//			if (m.find()) {
			//				throw new JobSchedulerException(String.format("unsupported variable found: ' %1$s'", m.group()));
			//			}
			return strValue;
		}
		catch (Exception e) {
			e.printStackTrace(System.err);
			throw new JobSchedulerException(conMethodName + ": " + e.getMessage());
		}
	}

	public String substituteAllDate(String targetFilename) throws Exception {
		String temp = substituteFirstDate(targetFilename);
		while (!targetFilename.equals(temp)) {
			targetFilename = temp;
			temp = substituteFirstDate(targetFilename);
		}
		return temp;
	}

	private String substituteFirstDate(String targetFilename) {
		final String conVarName = "[date:";
		try {
			// check for a date format string given in the file mask
			String strDateMask = getVariablePart(targetFilename, conVarName);
			if (isEmpty(strDateMask)) {
				strDateMask = SOSOptionTime.dateTimeFormat;
			}
			String strDateTime = JSDataElementDate.getCurrentTimeAsString(strDateMask);
			String strReplaceWhat = "\\" + conVarName + strDateMask + "\\]";
			String strReplaceWith = Matcher.quoteReplacement(strDateTime);
			targetFilename = targetFilename.replaceAll(strReplaceWhat, strReplaceWith);
			return targetFilename;
		}
		catch (Exception e) {
			throw new JobSchedulerException("error substituting [date:]: " + e.getMessage(), e);
		}
	}

	/**
	 * 
	*
	* \brief getVariablePart
	*
	* \details
	*  Return the variable part of a placeholder pattern.
	*  
	* \return String
	*
	 */
	public String getVariablePart(final String pstrWhere, final String pstrVariablePattern) {
		String strVariablePart = "";
		int intLen = pstrVariablePattern.length();
		if (pstrWhere.matches("(.*)(\\" + pstrVariablePattern + ")([^\\]]*)(\\])(.*)")) {
			int posBegin = pstrWhere.indexOf(pstrVariablePattern);
			if (posBegin > -1) {
				int posEnd = pstrWhere.indexOf("]", posBegin + intLen);
				if (posEnd > -1) {
					strVariablePart = pstrWhere.substring(posBegin + intLen, posEnd);
				}
			}
		}
		return strVariablePart;
	}

	public String substituteFileContent(final String pstrValue) {
		String strT = pstrValue;
		final String conVarName = "[file:";
		try {
			String strFileName = getVariablePart(pstrValue, conVarName);
			if (isNotEmpty(strFileName)) {
				strFileName = strFileName.replaceAll("\\\\", "\\\\\\\\"); // thx to Microsoft WIndows
				String strContent = new JSFile(strFileName).getContent();
				if (strContent.endsWith("\n")) {
					strContent = strContent.replaceAll("\n", "");
					strContent = strContent.replaceAll("\r", "");
				}
				String strReplaceWhat = "\\" + conVarName + strFileName + "\\]";
				String strReplaceWith = Matcher.quoteReplacement(strContent);
				strT = strT.replaceAll(strReplaceWhat, strReplaceWith);
			}
		}
		catch (Exception e) {
			throw new JobSchedulerException(String.format("error substituting '%1$s': %2$s ", conVarName, e.getMessage()), e);
		}
		return strT;
	}

	public String substituteEnvironmenVariable(final String pstrValue) {
		String strT = pstrValue;
		final String conVarName = "[env:";
		try {
			String strEnvironmentVariableName = getVariablePart(pstrValue, conVarName);
			if (isNotEmpty(strEnvironmentVariableName)) {
				String strContent = System.getenv(strEnvironmentVariableName);
				if (isEmpty(strContent)) {
					strContent = System.getProperty(strEnvironmentVariableName);
				}
				if (isNotEmpty(strContent)) {
					String strReplaceWhat = "\\" + conVarName + strEnvironmentVariableName + "\\]";
					String strReplaceWith = Matcher.quoteReplacement(strContent);
					strT = strT.replaceAll(strReplaceWhat, strReplaceWith);
				}
			}
		}
		catch (Exception e) {
			throw new JobSchedulerException(String.format("error substituting '%1$s': %2$s ", conVarName, e.getMessage()), e);
		}
		return strT;
	}

	public String substituteShell(final String pstrValue) {
		String strT = pstrValue;
		final String conVarName = "[shell:";
		try {
			String strScript2Execute = getVariablePart(pstrValue, conVarName);
			if (isNotEmpty(strScript2Execute)) {
				String strContent = executeShellScript(strScript2Execute);
				if (isNotEmpty(strContent)) {
					strScript2Execute = strScript2Execute.replaceAll("\\\\", "\\\\\\\\");
					String strReplaceWhat = "\\" + conVarName + strScript2Execute + "\\]";
					String strReplaceWith = Matcher.quoteReplacement(strContent);
					strT = strT.replaceAll(strReplaceWhat, strReplaceWith);
				}
			}
		}
		catch (Exception e) {
			throw new JobSchedulerException(String.format("error substituting '%1$s': %2$s ", conVarName, e.getMessage()), e);
		}
		return strT;
	}

	public String executeShellScript(final String pstrShellScriptName) {
		String strRet = "";
		String strShellScriptName = pstrShellScriptName;
		logger.debug(strShellScriptName);
		SOSCommandline objShell = new SOSCommandline();
		Vector<String> returnValues = objShell.execute(strShellScriptName);
		logger.debug(returnValues);
		int exitValue = objShell.getExitValue();
		if (exitValue == 0) {
			strRet = objShell.getStdOut();
		}
		else {
			strRet = "exit code of " + strShellScriptName + " is " + exitValue + "\nStdError = " + objShell.getStdError();
		}
		return strRet;
	}

	public String substituteAllFilename(String targetFilename, final String original) throws Exception {
		// original ist das replacement; es ist der urspruengliche Dateiname inklusive Endung
		String temp = substituteFirstFilename(targetFilename, original);
		while (!targetFilename.equals(temp)) {
			targetFilename = temp;
			temp = substituteFirstFilename(targetFilename, original);
		}
		return temp;
	}

	public String substituteUUID(String pstrValue) {
		String strT = checkAndSubstitute("uuid", pstrValue, getUUID());
		return strT;
		//		Matcher matcher1 = Pattern.compile("\\[uuid:([^\\]]*)\\]", intRegExpFlags).matcher(strValue1);
		//		if (matcher1.find()) {
		//			if (matcher1.group(1).equals("")) {
		//				strValue1 = strValue1.replaceFirst("\\[uuid:\\]", getUUID());
		//			}
		//		}
		//		return strValue1;
	}

	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	public String substituteTimeStamp(String pstrValue) {
		String strT = checkAndSubstitute("timestamp", pstrValue, getUnixTimeStamp());
		return strT;
	}

	public String substituteTempFile(String pstrValue) {
		String strT = pstrValue;
		try {
			if (pstrValue.toLowerCase().contains("tempfile")) {
				String strTempFileName = JSFile.createTempFileName();
				strT = checkAndSubstitute("tempfile", strT, strTempFileName);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return strT;
	}

	public String checkAndSubstitute(final String pstrPlaceHolderName, final String pstrWhere, final String pstrWhat) {
		final String conVarName = "[" + pstrPlaceHolderName + ":";
		String pstrValue = pstrWhere;
		Matcher matcher1 = getMatcher(conVarName, pstrWhere);
		if (matcher1.find()) {
			if (matcher1.group(1).equals("")) {
				pstrValue = pstrValue.replaceFirst("\\" + conVarName + "\\]", pstrWhat);
			}
		}
		return pstrValue;
	}

	private Matcher getMatcher(final String pstrPlaceHolderName, final String pstrWhere) {
		Matcher matcher1 = Pattern.compile("\\" + pstrPlaceHolderName + "([^\\]]*)\\]", intRegExpFlags).matcher(pstrWhere);
		return matcher1;
	}

	public static String getUnixTimeStamp() {
		return String.valueOf(System.nanoTime());
	}

	private String substituteSQLTimeStamp(String strValue) throws Exception {
		Matcher matcher1 = Pattern.compile("\\[sqltimestamp:([^\\]]*)\\]", intRegExpFlags).matcher(strValue);
		if (matcher1.find()) {
			if (matcher1.group(1).equals("")) {
				strValue = strValue.replaceFirst("\\[sqltimestamp:\\]", new Timestamp(new Date().getTime()).toString());
			}
		}
		return strValue;
	}

	public static String getSqlTimeStamp() {
		return new Timestamp(new Date().getTime()).toString();
	}

	private String substituteFirstFilename(String targetFilename, final String pstrReplaceWith) throws Exception {
		Matcher matcher1 = Pattern.compile("\\[filename:([^\\]]*)\\]", intRegExpFlags).matcher(targetFilename);
		if (matcher1.find()) {
			if (matcher1.group(1).equals("")) {
				targetFilename = targetFilename.replaceFirst("\\[filename:\\]", pstrReplaceWith);
			}
			else
				if (matcher1.group(1).equals("lowercase")) {
					targetFilename = targetFilename.replaceFirst("\\[filename:lowercase\\]", pstrReplaceWith.toLowerCase());
				}
				else
					if (matcher1.group(1).equals("uppercase")) {
						targetFilename = targetFilename.replaceFirst("\\[filename:uppercase\\]", pstrReplaceWith.toUpperCase());
					}
		}
		return targetFilename;
	}
	protected final int	intRegExpFlags	= Pattern.CASE_INSENSITIVE;
}

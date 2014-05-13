/**
 * 
 */
package com.sos.JSHelper.Options;

import java.io.File;

import com.sos.JSHelper.io.Files.JSFile;

/**
 * @author KB
 *
 */
public class SOSOptionMailSubject extends SOSOptionString {

	@SuppressWarnings("unused")
	private final String	conSVNVersion		= "$Id: JobSchedulerExistsFile.java 17751 2012-08-03 11:51:07Z kb $";
	private final String	conClassName		= this.getClass().getName();

	private boolean			flgSubjectFromFile	= false;
	private String			strSubjectFileName	= "";

	/**
	 * @param pPobjParent
	 * @param pPstrKey
	 * @param pPstrDescription
	 * @param pPstrValue
	 * @param pPstrDefaultValue
	 * @param pPflgIsMandatory
	 */
	public SOSOptionMailSubject(JSOptionsClass pPobjParent, String pPstrKey, String pPstrDescription, String pPstrValue, String pPstrDefaultValue,
			boolean pPflgIsMandatory) {
		super(pPobjParent, pPstrKey, pPstrDescription, pPstrValue, pPstrDefaultValue, pPflgIsMandatory);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Value(final String pstrValue) {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::Value";

		JSFile objF = new JSFile(pstrValue);
		if (objF.exists()) {
			this.strSubjectFileName = pstrValue;
			flgSubjectFromFile = true;
			super.Value(objF.getContent());
		}
		else {
			super.Value(pstrValue);
		}

	} // public void Value

	public String getSubjectFile() {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::getSubjectFile";

		return strSubjectFileName;

	} // private String getSubjectFile

	public boolean isSubjectFile() {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::isSubjectFile";

		return flgSubjectFromFile;

	} // private boolean isSubjectFile
}
package com.sos.JSHelper.Options;
import java.io.File;
import java.util.HashMap;

import com.sos.JSHelper.Annotations.JSOptionDefinition;
import com.sos.JSHelper.Exceptions.JobSchedulerException;
import com.sos.JSHelper.io.Files.JSFile;
import com.sos.JSHelper.io.Files.JSFolder;

/**
* \class JSOptionFolderName
*
* \brief JSOptionFolderName -
*
* \details
*
* \section JSOptionFolderName.java_intro_sec Introduction
*
* \section JSOptionFolderName.java_samples Some Samples
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
* @version $Id: SOSOptionFolderName.java 27463 2014-10-01 16:10:56Z kb $14.06.2009
* \see reference
*
* Created on 14.06.2009 16:52:26
 */
/**
 * @author KB
 *
 */
public class SOSOptionFolderName extends SOSOptionFileName {
	private static final long	serialVersionUID	= 1197392401084895147L;
	private final String		conClassName		= "JSOptionFolderName";
	@SuppressWarnings("hiding")
	public final String			ControlType			= "folder";

	public SOSOptionFolderName(final String pstrFolderName) {
		super(null, "", "description", pstrFolderName, "", false);
	}
	/**
	* \brief CreateFolder - Option: Folder anlegen, wenn noch nicht vorhanden
	*
	* \details
	*
	*/
	@JSOptionDefinition(name = "CreateFolder", value = "true", description = "Folder anlegen, wenn noch nicht vorhanden", key = "CreateFolder", type = "JSOptionBoolean", mandatory = false)
	public SOSOptionBoolean	CreateFolder	= new SOSOptionBoolean(objParentClass, // Verweis auf die SOSOptionClass-Instanz
													".CreateFolder", // Schl�ssel, i.d.r. identisch mit dem Namen der Option
													"Folder anlegen, wenn noch nicht vorhanden", // Kurzbeschreibung
													"true", // Wert
													"true", // defaultwert
													false // Option muss einen Wert haben
											);

	/**
	 * \brief JSOptionFolderName
	 *
	 * \details
	 *
	 * @param pobjParent
	 * @param pstrKey
	 * @param pstrDescription
	 * @param pstrValue
	 * @param pstrDefaultValue
	 * @param pflgIsMandatory
	 */
	public SOSOptionFolderName(final JSOptionsClass pobjParent, final String pstrKey, final String pstrDescription, final String pstrValue,
			final String pstrDefaultValue, final boolean pflgIsMandatory) {
		super(pobjParent, pstrKey, pstrDescription, pstrValue, pstrDefaultValue, pflgIsMandatory);
		intOptionType = isOptionTypeFolder;
	}

	@Override
	public void Value(final String pstrValue) {
		super.Value(pstrValue);   // respect changed listener
		if (isNotNull(pstrValue)) {
//			if (pstrValue.equals(".")) {
//				strValue += "/";
//			}
		}
	}

	/**
	 * \brief Value - Wert der Option liefern
	 *
	 * \details
	 *
	 * @param pstrValue
	 * @return
	 */
	@Override
	public String Value() {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::Value";
		if (strValue == null) {
			strValue = "";
		}
		String strLValue = super.Value();
		if (IsNotEmpty()) {
			if (strLValue.endsWith("/") || strLValue.endsWith("\\") || isDotFolder()) {
			}
			else {
				strLValue = strLValue + "/";
			}
			if (objParentClass != null) {
				// pr�fen, ob es den Folder gibt ...
				//				this.strValue = this.objParentClass.CheckFolder(this.strValue, conMethodName, this.CreateFolder.flgValue);
			}
		}
		return strLValue;
	}

	public boolean isDotFolder() {
		String strT = super.Value();
		return strT.equals(".") || strT.equals("..");
	}

	public File[] listFiles() {
		File[] objFL = this.JSFile().listFiles();
		if (objFL != null) {
		}
		else {
			throw new JobSchedulerException(String.format("No Files found for pathname '%1$s'", strValue));
		}
		return objFL;
	}

	public String[] getSubFolderArray() {
		String[] strRet = null;
		try {
			String path = strValue.trim().replaceAll("/(\\s*/)+", "/");
			String strPath = "";
			String strSlash = "";
			int iStart = 0;
			if (path.startsWith("/")) {
				strSlash = "/";
				iStart = 1;
			}
			String[] pathArray = path.substring(iStart).split("/");
			strRet = new String[pathArray.length];
			int i = 0;
			for (String strSubFolder : pathArray) {
				strPath += strSlash + strSubFolder;
				strSlash = "/";
				strRet[i] = strPath;
				i++;
			}
		}
		catch (Exception e) {
		}
		return strRet;
	}

	public String[] getSubFolders() {
		String[] pathArray = null;
		try {
			String path = strValue.trim().replaceAll("/(\\s*/)+", "/");
			String strPath = "";
			String strSlash = "";
			int iStart = 0;
			if (path.startsWith("/")) {
				strSlash = "/";
				iStart = 1;
			}
			pathArray = path.substring(iStart).split("/");
		}
		catch (Exception e) {
		}
		return pathArray;
	}

	public String[] getSubFolderArrayReverse() {
		String[] strRet = null;
		try {
			String path = strValue.trim().replaceAll("/(\\s*/)+", "/");
			String strPath = "";
			String strSlash = "";
			int iStart = 0;
			if (path.startsWith("/")) {
				strSlash = "/";
				iStart = 1;
			}
			String[] pathArray = path.substring(iStart).split("/");
			strRet = new String[pathArray.length];
			int i = pathArray.length - 1;
			for (String strSubFolder : pathArray) {
				strPath += strSlash + strSubFolder;
				strSlash = "/";
				strRet[i] = strPath;
				i--;
			}
		}
		catch (Exception e) {
		}
		return strRet;
	}

	public SOSOptionFolderName getSubFolder(final String pstrSubFolderName) {
		String strF = this.getAdjustedValue() + pstrSubFolderName;
		SOSOptionFolderName objF = new SOSOptionFolderName(strF);
		if (objF.JSFile().exists()) {
			if (objF.JSFile().isDirectory()) {
				//				
			}
			else {
				throw new JobSchedulerException(String.format("Path '%1$s' is not a folder", strF));
			}
		}
		else {
			throw new JobSchedulerException(String.format("Folder '%1$s' does not exists", strF));
		}
		return objF;
	}

	public boolean isAbsolutPath() {
		boolean strT = true;
		if (strValue.startsWith("/") == false) {
			strT = false;
		}
		return strT;
	}

	public boolean isNotHiddenFile() {
		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::isNotHiddenFile";
		if (strValue.equalsIgnoreCase("..") == false && strValue.equalsIgnoreCase(".") == false) {
			return true; // not a hidden file
		}
		return false; // it is a hidden-file
	} // public boolean isNotHiddenFile

	public String getName() {
		return new File(strValue).getName();
	}

	public String getParentFolderName() {
		String strParent = new File(strValue).getParent();
		if (strParent == null) {
			strParent = ".";
		}
		strParent = strParent.replaceAll("\\\\", "/");
		if (strParent == null || strParent.equals("/")) {
			strParent = ".";
		}
		return strParent;
	}

	public String getAdjustedValue() {
		String strRet = strValue.replaceAll("//", "/");
		strRet = strRet.replaceAll("\\\\\\\\", "\\");
		if (strRet.endsWith("/") == false) {
			strRet += "/";
		}
		return strRet;
	}

	public boolean hasSubFolders() {
		boolean flgRet = false;
		flgRet = strValue.contains("/") || strValue.contains("\\");
		return flgRet;
	}

	public JSFile createFile(String pstrNewFileName) {
		String strF = this.getAdjustedValue() + pstrNewFileName;
		JSFile objF = new JSFile(strF);
		if (objF.exists()) {
			if (objF.isDirectory()) {
				throw new JobSchedulerException(String.format("Path '%1$s' is a folder", strF));
			}
			else {
				objF.delete();
			}
		}
		return objF;
	}

	public JSFolder getFolder() {
		return new JSFolder(strValue);
	}
	
	private static final HashMap <String, String> defaultProposals = new HashMap<>();
	
	@Override
	public void addProposal (final String pstrProposal) {
		if (pstrProposal != null && pstrProposal.trim().length() > 0) {
			String strT = pstrProposal.trim();
			SOSOptionFolderName.defaultProposals.put(strT, strT);
		}
	}
	
	@Override
	public String[] getAllProposals(String text) {
		String[] proposals = SOSOptionFolderName.defaultProposals.keySet().toArray(new String[0]);
		return proposals;
	}


}

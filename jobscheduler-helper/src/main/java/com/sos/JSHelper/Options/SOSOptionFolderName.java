package com.sos.JSHelper.Options;

import java.io.File;
import java.util.HashMap;

import com.sos.JSHelper.Annotations.JSOptionDefinition;
import com.sos.JSHelper.Exceptions.JobSchedulerException;
import com.sos.JSHelper.io.Files.JSFolder;

/** @author KB */
public class SOSOptionFolderName extends SOSOptionFileName {

    private static final long serialVersionUID = 1197392401084895147L;
    private static final HashMap<String, String> defaultProposals = new HashMap<>();

    public SOSOptionFolderName(final String pstrFolderName) {
        super(null, "", "description", pstrFolderName, "", false);
    }

    public SOSOptionFolderName(final JSOptionsClass pobjParent, final String pstrKey, final String pstrDescription, final String pstrValue,
            final String pstrDefaultValue, final boolean pflgIsMandatory) {
        super(pobjParent, pstrKey, pstrDescription, pstrValue, pstrDefaultValue, pflgIsMandatory);
        intOptionType = isOptionTypeFolder;
    }

    @Override
    public String getValue() {
        if (strValue == null) {
            strValue = "";
        }
        String strLValue = super.getValue();
        if (isNotEmpty()) {
                strLValue = strLValue + "/";
            }
        return strLValue;
    }

    public boolean isDotFolder() {
        String strT = super.getValue();
        return ".".equals(strT) || "..".equals(strT);
    }

    public File[] listFiles() {
        File[] objFL = this.getJSFile().listFiles();
        if (objFL == null) {
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
        } catch (Exception e) {
            //
        }
        return strRet;
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
        } catch (Exception e) {
            //
        }
        return strRet;
    }

    public JSFolder getFolder() {
        return new JSFolder(strValue);
    }

    @Override
    public void addProposal(final String pstrProposal) {
        if (pstrProposal != null && !pstrProposal.trim().isEmpty()) {
            String strT = pstrProposal.trim();
            SOSOptionFolderName.defaultProposals.put(strT, strT);
        }
    }

    @Override
    public String[] getAllProposals(String text) {
        return SOSOptionFolderName.defaultProposals.keySet().toArray(new String[0]);
    }

}
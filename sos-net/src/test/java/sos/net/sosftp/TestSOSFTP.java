package sos.net.sosftp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import sos.net.SOSFTP;

import com.sos.JSHelper.io.Files.JSFile;

/** @author KB */
public class TestSOSFTP {

    private static final String C_TEMP_TEST = "c:/temp/test";
    private static final Logger LOGGER = Logger.getLogger(TestSOSFTP.class);
    private static final String TEST_FOLDER_NAME = "./TestSOSFtp";
    private static final String TEST_FILE_NAME = "text.txt";
    private static final String TEST_PATH_NAME = "R:\\backup\\sos\\java\\junittests\\testdata\\SOSDataExchange/";
    private static SOSFTP sosftp = null;
    private String[] strArguments = null;
    public static final String conSettingFILE_SPEC = "file_spec";

    @Test
    public void testUml() {
        String strU = "übersicht_PMFHSBC.csv";
        strU = strU.replaceAll("ü", "�");
        LOGGER.info(strU);
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        sosftp = new SOSFTP("WILMA", 21, "", 123);
        sosftp.login("kb", "kb");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        if (sosftp != null) {
            sosftp.disconnect();
            sosftp = null;
        }
    }

    @Test
    public void testHiddenFile() {
        assertEquals("Ist hidden file", false, sosftp.isNotHiddenFile(".."));
        assertEquals("Ist hidden file", false, sosftp.isNotHiddenFile("."));
        assertEquals("Ist nothidden file", true, sosftp.isNotHiddenFile("/home/kb"));
    }

    @Test
    public void getUUID() {
        for (int i = 0; i <= 100; i++) {
            UUID objUUID = UUID.randomUUID();
            LOGGER.info(objUUID.toString());
        }
    }

    @Test
    public void testMKDIR() {
        try {
            boolean flgOK = sosftp.mkdir(TEST_FOLDER_NAME);
            assertTrue("Folder created", flgOK);
            flgOK = sosftp.changeWorkingDirectory(TEST_FOLDER_NAME);
            assertTrue("CD is possible", flgOK);
            if (flgOK) {
                sosftp.cdup();
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Test
    public void testRMDIR() {
        try {
            sosftp.rmdir(TEST_FOLDER_NAME);
            boolean flgOK = sosftp.changeWorkingDirectory(TEST_FOLDER_NAME);
            assertFalse("Folder is deleted", flgOK);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Test
    public void testnList() throws Exception {
        try {
            LOGGER.info("~~~~~~~~~~~~~Test mit nlist~~~~~~~~~~~~~~~~~~~");
            long s1 = System.currentTimeMillis();
            Vector<String> va = sosftp.nList(".");
            long e1 = System.currentTimeMillis();
            long r1 = e1 - s1;
            LOGGER.info("elapsed Time is : " + r1);
            for (int i = 0; i < va.size(); i++) {
                LOGGER.info("VA File: " + va.get(i));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Test
    public void testnListRecurseSubFolder() throws Exception {
        try {
            long s1 = System.currentTimeMillis();
            Vector<String> strListOfAllFiles = sosftp.nList(".", false);
            long e1 = System.currentTimeMillis();
            long r1 = e1 - s1;
            LOGGER.info("elapsed Time is : " + r1);
            for (int i = 0; i < strListOfAllFiles.size(); i++) {
                LOGGER.info("VA File: " + strListOfAllFiles.get(i));
                String strFileName = strListOfAllFiles.get(i);
                LOGGER.info("FileName is " + new File(strFileName).getName());
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Test
    public void testStripRemoteDirName() throws Exception {
        String strT = "";
        strT = stripRemoteDirName(C_TEMP_TEST, C_TEMP_TEST + "huhu.txt");
        assertEquals("FileName expected", "huhu.txt", strT);
        strT = stripRemoteDirName(C_TEMP_TEST, C_TEMP_TEST + "1/huhu.txt");
        assertEquals("FileName expected", "." + File.separator + "1" + File.separator + "huhu.txt", strT);
        strT = stripRemoteDirName(C_TEMP_TEST, C_TEMP_TEST + "1/2/3/huhu.txt");
        assertEquals("FileName expected", adjustSeparator("./1/2//3/huhu.txt"), strT);
    }

    private String stripRemoteDirName(final String pstrRootPath, final String pstrPathName) throws Exception {
        String strResult = pstrPathName;
        String strR = new File(pstrRootPath).getAbsolutePath();
        String strP = new File(pstrPathName).getAbsolutePath();
        if (strP.startsWith(strR) == true) {
            strResult = strP.substring(strR.length());
            if (strResult.contains(File.separator)) {
                if (strResult.startsWith(File.separator)) {
                    strResult = strResult.substring(1);
                }
                if (strResult.contains(File.separator)) {
                    strResult = "." + File.separator + strResult;
                }
            }
            strResult = adjustSeparator(strResult);
        }
        return strResult;
    }

    private String adjustSeparator(final String pstrPathName) {
        String strRet = pstrPathName;
        String[] strA = pstrPathName.split("[/\\\\]");
        if (strA.length > 0) {
            strRet = "";
            for (String string : strA) {
                if (!string.isEmpty()) {
                    strRet = strRet + string + File.separator;
                }
            }
            strRet = strRet.substring(0, strRet.length() - 1);
        }
        return strRet;
    }

    private boolean check4MultipleFileSpecs(final String pstrRegExp) {
        boolean flgMultipleFileSpecs = false;
        int intFileSpecSize = conSettingFILE_SPEC.length();
        if (pstrRegExp.startsWith(conSettingFILE_SPEC) && pstrRegExp.length() > intFileSpecSize) {
            String strFileSpecIndex = pstrRegExp.substring(intFileSpecSize);
            if (strFileSpecIndex.startsWith("_")) {
                strFileSpecIndex = strFileSpecIndex.substring(1);
            }
            int intIndex = 0;
            try {
                intIndex = new Integer(strFileSpecIndex);
            } catch (NumberFormatException e) {
                //
            }
            if (intIndex > 0) {
                flgMultipleFileSpecs = true;
            }
        }
        return flgMultipleFileSpecs;
    }

    @Test
    public void testFileSpec() {
        assertTrue("is numeric file_spec", check4MultipleFileSpecs("file_spec_17"));
        assertTrue("is numeric file_spec", check4MultipleFileSpecs("file_spec17"));
    }

    @Test
    public void testFileSpec2() {
        assertFalse("is regular file_spec", check4MultipleFileSpecs("file_spec"));
    }

    @Test
    public void testFileSpec3() {
        assertFalse("is invalid numeric file_spec", check4MultipleFileSpecs("file_spec_abc"));
        assertFalse("is invalid numeric file_spec", check4MultipleFileSpecs("file_spec1o"));
    }

    @Test
    public void testSendUsingFilePathAndLocalDir() {
        createTestFile();
        strArguments = new String[] { "-verbose=9", "-remote_dir=./relative", "-operation=send", "-host=wilma.sos", "-user=kb", "-password=kb",
                "-local_dir=" + TEST_PATH_NAME, "-file_path=" + TEST_PATH_NAME + TEST_FILE_NAME };
        callSOSFtp(strArguments);
    }

    private void callSOSFtp(final String[] pstrParameter) {
        SOSFTPCommandSend.gflgUseSystemExit = false;
        SOSFTPCommandSend.main(pstrParameter);
    }

    @Test
    public void testSendUsingFilePathAndLocalDir2() {
        createTestFile();
        String[] strArguments = { "-verbose=9", "-remote_dir=./relative", "-operation=send", "-host=wilma.sos", "-user=kb", "-password=kb",
                "-local_dir=" + TEST_PATH_NAME + "hugo/", "-file_path=" + TEST_PATH_NAME + TEST_FILE_NAME };
        callSOSFtp(strArguments);
    }

    @Test
    public void testSendUsingFilePathAndLocalDir3() {
        createTestFile();
        String[] strArguments = { "-verbose=9", "-remote_dir=./relative", "-operation=send", "-host=wilma.sos", "-user=kb", "-password=kb",
                "-local_dir=" + TEST_PATH_NAME, "-file_path=" + TEST_FILE_NAME };
        callSOSFtp(strArguments);
    }

    @Test
    public void testSendUsingFilePathAndLocalDir4() {
        createTestFile();
        String[] strArguments = { "-verbose=9", "-remote_dir=./relative", "-operation=send", "-host=wilma.sos", "-user=kb", "-password=kb",
                "-local_dir=R:/backup/sos/java/junittests/testdata/", "-file_path=./SOSDataExchange/" + TEST_FILE_NAME };
        callSOSFtp(strArguments);
    }

    @Test
    public void testSendUsingFilePathAndLocalDir5() {
        createTestFile();
        String[] strArguments = { "-verbose=9", "-remote_dir=./relative", "-operation=send", "-host=wilma.sos", "-user=kb", "-password=kb",
                "-file_path=" + TEST_PATH_NAME + TEST_FILE_NAME };
        callSOSFtp(strArguments);
    }

    @Test
    public void testSendUsingFilePathAndLocalDir6() {
        createTestFile();
        String[] strArguments = { "-verbose=9", "-remote_dir=./relative", "-operation=send", "-host=wilma.sos", "-user=kb", "-password=kb",
                "-local_dir=\"\" ", "-file_path=" + TEST_PATH_NAME + TEST_FILE_NAME };
        callSOSFtp(strArguments);
    }

    private void createTestFile() {
        createTestFile(TEST_FILE_NAME);
    }

    private void createTestFile(final String pstrFileName) {
        JSFile objFile = new JSFile(TEST_PATH_NAME, pstrFileName);
        try {
            objFile.writeLine("This is a simple Testfile. nothing else.");
            objFile.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
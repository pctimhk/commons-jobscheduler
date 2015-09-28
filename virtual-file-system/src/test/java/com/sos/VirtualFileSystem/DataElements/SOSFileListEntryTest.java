package com.sos.VirtualFileSystem.DataElements;

import com.sos.JSHelper.Listener.JSListenerClass;
import com.sos.JSHelper.io.Files.JSFile;
import com.sos.VirtualFileSystem.DataElements.SOSFileListEntry.enuTransferStatus;
import com.sos.VirtualFileSystem.Factory.VFSFactory;
import com.sos.VirtualFileSystem.Interfaces.ISOSVFSHandler;
import com.sos.VirtualFileSystem.Interfaces.ISOSVfsFileTransfer;
import com.sos.VirtualFileSystem.Options.SOSFTPOptions;
import org.apache.log4j.Logger;
import org.junit.*;

import java.io.IOException;
import java.lang.management.ManagementFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
* \class SOSFileListEntryTest 
* 
* \brief SOSFileListEntryTest - 
* 
* \details
*
* \section SOSFileListEntryTest.java_intro_sec Introduction
*
* \section SOSFileListEntryTest.java_samples Some Samples
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
* @version $Id$15.08.2010
* \see reference
*
* Created on 15.08.2010 11:52:20
 */

public class SOSFileListEntryTest extends JSListenerClass {

	@SuppressWarnings("unused")
	private final String		conClassName	= "SOSFileListEntryTest";

	private static Logger		logger			= Logger.getLogger(SOSFileListEntryTest.class);

	private final String				strTestFileName	= "text.txt";
	private final String				strTestPathName	= "c:\\temp\\";
	private final String				strAPrefix		= "~~";

	private SOSFTPOptions		objOptions		= null;
	private SOSFileListEntry	objE			= null;

	private ISOSVFSHandler		objVFS					= null;
	private ISOSVfsFileTransfer	objFileSystemHandler	= null;
	
	public SOSFileListEntryTest() {
		//
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		objOptions = new SOSFTPOptions();
		objVFS = VFSFactory.getHandler("local");
		objFileSystemHandler = (ISOSVfsFileTransfer) objVFS;
		
	}

	@After
	public void tearDown() throws Exception {
	}

	// @Test
	public void testVfsHandler() {
		fail("Not yet implemented");
	}

	// @Test
	public void testSOSFileListEntry() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testSourceTransferName() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testTargetTransferName() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testGetFile() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testGetZipFileName() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testIsEmptyFile() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testExists() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testNotExists() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testLength() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testGetAbsolutePath() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testGetName() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testDelete() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testGetTransferStatus() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testTransferStatus() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testSetTransferSkipped() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testTargetFileName() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testNoOfBytesTransferredLong() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testSetTransactionalRemoteFile() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testGetTransactionalLocalFile() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testSetTransactionalLocalFile() {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetTargetFile() throws IOException {
		JSFile objFile = new JSFile(strTestPathName + strTestFileName);
		objFile.deleteOnExit();
		objFile.WriteLine("Das ist eine Testdatei. Weiter nichts");
		objFile.close();

		objOptions.atomic_prefix.Value(strAPrefix);
		objOptions.compress_files.value(true);
		objOptions.compressed_file_extension.Value(".zip");
		SOSFileListEntry objE = new SOSFileListEntry(strTestPathName + strTestFileName);
		objE.setDataSourceClient(objFileSystemHandler);
		objE.Options(objOptions);
		objE.getTargetFile();

		logger.info("SourceFileName         = " + objE.SourceFileName());
		logger.info("SourceTransferFileName = " + objE.SourceTransferName());
		logger.info("TargetTransferFileName = " + objE.TargetTransferName());
		logger.info("TargetFileName         = " + objE.TargetFileName());

		assertEquals("Source-File Name", strTestPathName + strTestFileName, objE.SourceFileName());
		//		assertEquals("Source-Transfer-File Name", strTestPathName + strTestFileName, objE.SourceTransferName());
		//		assertEquals("intermediate Atomic-File TargetTransferName", objOptions.atomic_prefix.Value() + strTestFileName, objE.TargetTransferName());
		assertEquals("final TargetFileName", strTestFileName + objOptions.compressed_file_extension.Value(), objE.TargetFileName());
	}

	private void CreateTestFile() {

		JSFile objFile = new JSFile(strTestPathName + strTestFileName);
		objFile.deleteOnExit();
		try {
			objFile.WriteLine("Das ist eine Testdatei. Weiter nichts");
			objFile.close();
			objE = new SOSFileListEntry(strTestPathName + strTestFileName);
			objE.setDataSourceClient(objFileSystemHandler);
			objE.Options(objOptions);
			objE.getTargetFile();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetTargetFile2() throws IOException {
		
		objOptions.atomic_prefix.Value(strAPrefix);
		objOptions.compress_files.value(true);
		objOptions.compressed_file_extension.Value(".zip");
		objOptions.ReplaceWhat.Value("(t)ext\\.t(x)t");
		objOptions.ReplaceWith.Value("u;u");
		CreateTestFile();

		objE.Log4Debug();
		assertEquals("Source-File Name", strTestPathName + strTestFileName, objE.SourceFileName());
		//		assertEquals("Source-Transfer-File Name", strTestPathName + strTestFileName, objE.SourceTransferName());
		//		assertEquals("intermediate Atomic-File TargetTransferName", objOptions.atomic_prefix.Value() + strTestFileName, objE.TargetTransferName());
		assertEquals("final TargetFileName", "uext.tut" + objOptions.compressed_file_extension.Value(), objE.TargetFileName());
	}

	@Test
	public void testNormalized() {
		CreateTestFile();
		String strN = objE.normalized(strTestPathName);
		logger.debug(strN);

		assertEquals("normalized path name", "c:/temp/", strN);
	}

	@Test
	public void testMakeAtomicFileName() {
		objOptions.atomic_prefix.Value(strAPrefix);
		SOSFileListEntry objE = new SOSFileListEntry(strTestPathName + strTestFileName);
		objE.setDataSourceClient(objFileSystemHandler);
		objE.Options(objOptions);
		objE.getTargetFile();
		objE.Log4Debug();

		assertEquals("Source-File Name", strTestPathName + strTestFileName, objE.SourceFileName());
		assertEquals("Source-Transfer-File Name", strTestPathName + strTestFileName, objE.SourceTransferName());
		assertEquals("intermediate Atomic-File TargetTransferName", objOptions.atomic_prefix.Value() + strTestFileName, objE.TargetTransferName());
		assertEquals("final TargetFileName", strTestFileName, objE.TargetFileName());
	}

	@Test
	public void testMakeAtomicFileName2() {
		objOptions.atomic_prefix.Value(strAPrefix);
		objOptions.atomic_suffix.Value(strAPrefix + strAPrefix);
		CreateTestFile();
		
		assertEquals("Source-File Name", strTestPathName + strTestFileName, objE.SourceFileName());
		assertEquals("Source-Transfer-File Name", strTestPathName + strTestFileName, objE.SourceTransferName());
		assertEquals("intermediate Atomic-File TargetTransferName", objOptions.atomic_prefix.Value() + strTestFileName + objOptions.atomic_suffix.Value(),
				objE.TargetTransferName());
		assertEquals("final TargetFileName", strTestFileName, objE.TargetFileName());
	}

	@Test
	public void testMakeAtomicFileName3() {
		SOSFileListEntry objE = new SOSFileListEntry(strTestPathName + strTestFileName);
		objE.setDataSourceClient(objFileSystemHandler);
		objE.Options(objOptions);
		objE.getTargetFile();
		assertEquals("Source-File Name", strTestPathName + strTestFileName, objE.SourceFileName());
		assertEquals("Source-Transfer-File Name", strTestPathName + strTestFileName, objE.SourceTransferName());
		assertEquals("intermediate Atomic-File TargetTransferName", strTestFileName, objE.TargetTransferName());
		assertEquals("final TargetFileName", strTestFileName, objE.TargetFileName());
	}

	@Test
	public void testSetStatus() {
		CreateTestFile();
		objE.setStatus(enuTransferStatus.waiting4transfer);
		objE.setStatus(enuTransferStatus.transferring);
		objE.setDataTargetClient(objFileSystemHandler);
		for (int i = 1; i < 10; i++) {
			objE.setTransferProgress(i*100);			
		}
		objE.setStatus(enuTransferStatus.transferred);
	}

	// @Test
	public void testSOSFileListEntryString() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testSOSFileListEntryStringStringLong() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testToString() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testRemoteFileName() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testSetRemoteFileName() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testLocalFileName() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testSetLocalFileName() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testNoOfBytesTransferred() {
		// fail("Not yet implemented");
	}

	/**
	 * 
	 * \brief testSetNoOfBytesTransferred
	 * 
	 * \details
	 * This test is to test wether a file-size-modification will be detected by the Routine.
	 * If the file-size differs, an exception is raised.
	 * \return void
	 *
	 */
	@Test(expected = com.sos.JSHelper.Exceptions.JobSchedulerException.class)
	public void testSetNoOfBytesTransferred() {
		CreateTestFile();
		objE.setNoOfBytesTransferred(1234);
	}

	@Test
	public void testFileExists() {
		CreateTestFile();

	}

	// @Test
	public void testDeleteFile() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testGetFileString() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testGetFilePermissions() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testGetFileSize() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testIsDirectory() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testPutFileFile() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testPutFileString() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testSetFilePermissions() {
		// fail("Not yet implemented");
	}

	// @Test
	public void testTransferMode() {
		// fail("Not yet implemented");
	}
	
	@Test
	public void testPid () {
		/**
		 * this hack is tested for SUN-JVM only. No guarantee is made for other JVMs
		 */
		String pid = ManagementFactory.getRuntimeMXBean().getName();
		String strA[] = pid.split("@");
//		pid = strA[0];

		System.out.println("name = " + pid + ", pid = " + strA[0]);
	}
}

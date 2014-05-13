package sos.scheduler.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sos.JSHelper.Basics.JSToolBox;
import com.sos.JSHelper.Logging.Log4JHelper;
import com.sos.JSHelper.io.Files.JSFile;

/**
* \class ExistsFileTest
*
* \brief ExistsFileTest -
*
* \details
*
* \section ExistsFileTest.java_intro_sec Introduction
*
* \section ExistsFileTest.java_samples Some Samples
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
* \author oh
* @version $Id$
* \see reference
*
* Created on 26.03.2012 15:56:18
 */
public class ExistsFileTest extends JSToolBox {
	@SuppressWarnings("unused")
	private final String		conClassName	= "ExistsFileTest";
	private static final Logger	logger			= Logger.getLogger(ExistsFileTest.class);
	private static Log4JHelper	objLogger		= null;

	public ExistsFileTest() {
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
		String strLog4JFileName = "./log4j.properties";
		String strT = new File(strLog4JFileName).getAbsolutePath();
		objLogger = new Log4JHelper(strLog4JFileName);
		objLogger.setLevel(Level.DEBUG);
		// objLogger.setLevel(Level.INFO);
		logger.info("log4j properties filename = " + strT);
	}

	@After
	public void tearDown() throws Exception {
	}

	public void createFiles(final HashMap <String, String> params) throws Exception {

		int fileSize;
		long fileAge;

			if (params.containsKey("create_file") && isNotEmpty(params.get("create_file"))) {

				if (params.containsKey("file_size") && isNotEmpty(params.get("file_size"))) {
					fileSize  = Integer.parseInt(params.get("file_size"));
				} else {
					fileSize = 10;
				}
				if (params.containsKey("file_age") && isNotEmpty(params.get("file_age"))) {
					fileAge  = System.currentTimeMillis() - 1000 * Long.parseLong(params.get("file_age"));
				} else {
					fileAge  = System.currentTimeMillis();
				}
				populateFile(new File(params.get("create_file")), fileSize, fileAge);
			}


			for (int i=0; i<20; i++) {
				if (params.containsKey("create_file_"+i) && isNotEmpty(params.get("create_file_"+i))) {

					if (params.containsKey("file_size_"+i) && isNotEmpty(params.get("file_size_"+i))) {
						fileSize  = Integer.parseInt(params.get("file_size_"+i));
					} else {
						fileSize = 10;
					}
					if (params.containsKey("file_age_"+i) && isNotEmpty(params.get("file_age_"+i))) {
						fileAge = System.currentTimeMillis() - 1000 * Long.parseLong(params.get("file_age_"+i));
					} else {
						fileAge = System.currentTimeMillis();
					}
					populateFile(new File(params.get("create_file_"+i)), fileSize, fileAge);
				}
			}
	}


	public void populateFile(final File file, final int fileSize, final long lastModified) throws Exception {

		FileOutputStream out = null;

		try {

			out  = new FileOutputStream(file);
			out.write(new byte[fileSize]);
			logger.info(file.getAbsolutePath() + " created: SIZE[" + fileSize + "] MODIFIED[" + lastModified + "]");

		} catch (Exception e) {
			throw new Exception ("could not populate file [" + file.getAbsolutePath() + "]: " + e.getMessage());
		} finally {
			if (out != null)
				try {
					out.flush(); out.close(); file.setLastModified(lastModified);
				} catch (Exception x) {}
		}
	}



	@Test
	public void testExistsFile() throws Exception {
		String location = "Z:/scheduler.test/testsuite_files/files/file_operations/exists/13/in/";
		HashMap <String, String> objT = new HashMap <String, String>();
		objT.put("file", location);
		objT.put("file_spec", ".*");
		objT.put("min_file_age", "60");
		objT.put("max_file_age", "24:30:00");
		objT.put("expected_size_of_result_set", "3");
		objT.put("raise_error_if_result_set_is", "!=");

		objT.put("create_file_1", location+"1.dat");
		objT.put("create_file_2", location+"2.dat");
		objT.put("create_file_3", location+"3.dat");
		objT.put("create_file_4", location+"4.dat");
		objT.put("create_file_5", location+"5.dat");
		objT.put("create_file_6", location+"6.dat");
		objT.put("create_file_7", location+"7.dat");
		objT.put("create_file_8", location+"8.dat");

		objT.put("file_age_1", "0");	 //now
		objT.put("file_age_2", "10");    //now - 10s
		objT.put("file_age_3", "30");    //now - 30s
		objT.put("file_age_4", "120");   //now - 3min
		objT.put("file_age_5", "3600");  //now - 1h
		objT.put("file_age_6", "86400"); //now - 24h
		objT.put("file_age_7", "90000"); //now - 25h
		objT.put("file_age_8", "172800");//now - 48h


		//expected 4.dat, 5.dat, 6.dat
		createFiles(objT);

		JSExistsFile objR = new JSExistsFile();
		JSExistsFileOptions objO = objR.Options();
		objO.setAllOptions(objT);
		logger.info(objO.on_empty_result_set.isDirty());

//		objR.setJSJobUtilites(objR);
		objR.Execute();

		Vector<File> lstResultList = objR.getResultList();

		logger.info(lstResultList.size());
		logger.info(lstResultList);
	}

	private 		JSFile objFile = null;
	private final String strTestFileName = System.getProperty(JobSchedulerFileOperationBase.conPropertyJAVA_IO_TMPDIR) + "/testcheckSteadyStateOfFiles.t";

	class WriteToFile implements Runnable {
	    @Override
		public void run() {
	    	for (int i = 0; i < 15; i++) {
	    		logger.debug(i);
				try {
					objFile.Write(i + ": This is a test");
					objFile.WriteLine(i + ": This is a test");
					objFile.WriteLine(i + ": This is a test");
					Thread.sleep(500);
					objFile.WriteLine(i + ": This is a test");
					Thread.sleep(500);
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    	logger.debug("finished");
	    	try {
				objFile.close();
				objFile = null;
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}

//	@Test
//	public final void testcheckSteadyStateOfFiles() {
//		params.put(JobSchedulerFileOperationBase.conParameterCHECK_STEADYSTATEOFFILE, "true");
//		objFile = new JSFile(strTestFileName);
//		objFOP.lstResultList.add(objFile);
//
//		Thread thread = new Thread(new WriteToFile());		// Create and start the thread
//		thread.start();
//		JSExistsFile objR = new JSExistsFile();
//		JSExistsFileOptions objO = objR.Options();
//		objO.
//		objO.setAllOptions(objT);
//		logger.info(objO.on_empty_result_set.isDirty());
//
////		objR.setJSJobUtilites(objR);
//		objR.Execute();
//}

}
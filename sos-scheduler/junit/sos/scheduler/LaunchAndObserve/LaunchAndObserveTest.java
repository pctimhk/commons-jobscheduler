package sos.scheduler.LaunchAndObserve;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sos.JSHelper.Listener.JSListenerClass;
import com.sos.JSHelper.Logging.Log4JHelper;

/**
* \class LaunchAndObserveTest 
* 
* \brief LaunchAndObserveTest - 
* 
* \details
*
* \section LaunchAndObserveTest.java_intro_sec Introduction
*
* \section LaunchAndObserveTest.java_samples Some Samples
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
* Created on 25.11.2011 13:13:19
 */
public class LaunchAndObserveTest {
	@SuppressWarnings("unused")
	private final String		conClassName	= "LaunchAndObserveTest";
	@SuppressWarnings("unused")
	private static final Logger	logger			= Logger.getLogger(LaunchAndObserveTest.class);
	
	@SuppressWarnings("unused")
	private static Log4JHelper	objLogger		= null;
	private JobSchedulerLaunchAndObserve objE 	= null;

	private JobSchedulerLaunchAndObserveOptions	objOptions			= null;
	
	private String 	schedulerHost	= "8of9.sos";
	private int 	schedulerPort	= 4210;

	public LaunchAndObserveTest() {
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
		objLogger = new Log4JHelper("./log4j.properties"); //$NON-NLS-1$
		objE = new JobSchedulerLaunchAndObserve();
		objOptions = objE.Options();
		objOptions.scheduler_host.Value(schedulerHost);
		objOptions.scheduler_port.value(schedulerPort);
		JSListenerClass.bolLogDebugInformation = true;
		JSListenerClass.intMaxDebugLevel = 9;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testStandaloneJobExecute() throws Exception {
		objOptions.job_name.Value("/LaunchAndObserve/GenericShellExecutor");
		objOptions.check_for_regexp.Value("Antwort von");
		objOptions.check_interval.value(11);
		objE.Execute();
	}
	
	@Test
	public void testOrderJobExecute() throws Exception {
		objOptions.job_name.Value("/LaunchAndObserve/GenericOrderShellExecutor");
		objOptions.order_jobchain_name.Value("/LaunchAndObserve/ShellExecutor");
		objOptions.OrderId.Value("Execute1");
		objOptions.check_for_regexp.Value("Antwort von");
		objOptions.check_interval.value(11);
		objE.Execute();
	}
}

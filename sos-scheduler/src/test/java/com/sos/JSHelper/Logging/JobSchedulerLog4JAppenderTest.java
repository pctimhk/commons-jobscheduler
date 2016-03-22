package com.sos.JSHelper.Logging;

import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sos.JSHelper.interfaces.IJobSchedulerLoggingAppender;
import com.sos.scheduler.BufferedJobSchedulerLog4JAppender;
import com.sos.scheduler.JobSchedulerLog4JAppender;

/** \class JobSchedulerLog4JAppenderTest
 * 
 * \brief JobSchedulerLog4JAppenderTest -
 * 
 * \details
 *
 * \section JobSchedulerLog4JAppenderTest.java_intro_sec Introduction
 *
 * \section JobSchedulerLog4JAppenderTest.java_samples Some Samples
 *
 * \code .... code goes here ... \endcode
 *
 * <p style="text-align:center">
 * <br />
 * --------------------------------------------------------------------------- <br />
 * APL/Software GmbH - Berlin <br />
 * ##### generated by ClaviusXPress (http://www.sos-berlin.com) ######### <br />
 * ---------------------------------------------------------------------------
 * </p>
 * \author KB
 * 
 * @version $Id: JobSchedulerLog4JAppenderTest.java 14732 2011-07-05 20:53:36Z
 *          sos $08.11.2010 \see reference
 *
 *          Created on 08.11.2010 12:16:00 */

public class JobSchedulerLog4JAppenderTest {

    @SuppressWarnings("unused")
    private final String conClassName = "JobSchedulerLog4JAppenderTest";

    public JobSchedulerLog4JAppenderTest() {
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
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testActivateOptions() {
        // fail("Not yet implemented");
    }

    @Test
    public void testJobSchedulerLog4JAppender() {
        // fail("Not yet implemented");
    }

    @Test
    public void testJobSchedulerLog4JAppenderLayout() {
        // fail("Not yet implemented");
    }

    @Test
    public void testSetSchedulerLogger() {
        // fail("Not yet implemented");
    }

    // @Test
    public void testSubAppendLoggingEvent() {
        Logger logger = null;

        logger = Logger.getRootLogger();
        IJobSchedulerLoggingAppender objJSAppender = null;
        /** the JobSchedulerLog4JAppender is used as the stdout-appender
         * Therefore the code-snippet below asked log4j what the stdout-appender
         * is and if it is the JobSchedulerLog4JAppender, the Instance of the
         * sosJobSchedulerLogger is set.
         * 
         * All Log4J-Messages are redirected to the sosLogger. */
        Appender objStdoutAppender = logger.getAppender("stdout"); //$NON-NLS-1$
        if (objStdoutAppender instanceof IJobSchedulerLoggingAppender) {
            objJSAppender = (IJobSchedulerLoggingAppender) objStdoutAppender;
            logger.info("JobSchedulerLog4JAppender is configured as log4j-appender");
        }

        if (objJSAppender == null) {
            SimpleLayout layout = new SimpleLayout();
            Appender consoleAppender = new JobSchedulerLog4JAppender(layout);
            logger.addAppender(consoleAppender);

            // ALL | DEBUG | INFO | WARN | ERROR | FATAL | OFF:
            logger.setLevel(Level.DEBUG);
            logger.debug("Log4J configured programmatically");

        }

        // objJSAppender.setSchedulerLogger(new SOSSchedulerLogger(null));
        logger.info("User-Dir : " + System.getProperty("user.dir")); //$NON-NLS-1$
    }

    @Test
    public void testBufferedLog4jAppender() {
        Logger logger = null;
        logger = Logger.getRootLogger();

        IJobSchedulerLoggingAppender objJSAppender = null;
        /** the JobSchedulerLog4JAppender is used as the stdout-appender
         * Therefore the code-snippet below asked log4j what the stdout-appender
         * is and if it is the JobSchedulerLog4JAppender, the Instance of the
         * sosJobSchedulerLogger is set.
         * 
         * All Log4J-Messages are redirected to the sosLogger. */
        Appender objStdoutAppender = logger.getAppender("buffered"); //$NON-NLS-1$
        if (objStdoutAppender instanceof BufferedJobSchedulerLog4JAppender) {
            objJSAppender = (IJobSchedulerLoggingAppender) objStdoutAppender;
            logger.info("JobSchedulerLog4JAppender is configured as buffered log4j-appender");
        }

        if (objJSAppender == null) {
            SimpleLayout layout = new SimpleLayout();
            Appender consoleAppender = new JobSchedulerLog4JAppender(layout);
            logger.addAppender(consoleAppender);

            // ALL | DEBUG | INFO | WARN | ERROR | FATAL | OFF:
            logger.setLevel(Level.DEBUG);
            logger.debug("Log4J configured programmatically");

        }

        // objJSAppender.setSchedulerLogger(new SOSSchedulerLogger(null));
        logger.info("User-Dir : " + System.getProperty("user.dir")); //$NON-NLS-1$
    }

}

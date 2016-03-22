package com.sos.scheduler.model.objects;

import org.apache.log4j.Logger;

import com.sos.scheduler.model.SchedulerObjectFactory;

/** \class JSObjConfigurationFile
 * 
 * \brief JSObjConfigurationFile -
 * 
 * \details
 *
 * \section JSObjConfigurationFile.java_intro_sec Introduction
 *
 * \section JSObjConfigurationFile.java_samples Some Samples
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
 * \author oh
 * 
 * @version $Id$ \see reference
 *
 *          Created on 09.02.2011 14:27:52 */

/** @author oh */
public class JSObjConfigurationFile extends ConfigurationFile {

    @SuppressWarnings("unused")
    private final String conClassName = "JSObjConfigurationFile";
    @SuppressWarnings("unused")
    private static final Logger logger = Logger.getLogger(JSObjConfigurationFile.class);

    public JSObjConfigurationFile(SchedulerObjectFactory schedulerObjectFactory) {
        super();
        objFactory = schedulerObjectFactory;
    }
}

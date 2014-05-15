package com.sos.scheduler.model.commands;

import org.apache.log4j.Logger;

import com.sos.scheduler.model.SchedulerObjectFactory;

/**
* \class JSCmdParamGet 
* 
* \brief JSCmdParamGet - 
* 
* \details
*
* \section JSCmdParamGet.java_intro_sec Introduction
*
* \section JSCmdParamGet.java_samples Some Samples
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
* Created on 08.02.2011 14:51:12
 */

/**
 * @author oh
 *
 */
public class JSCmdParamGet extends ParamGet {

	@SuppressWarnings("unused")
	private final String		conClassName	= "JSCmdParamGet";
	@SuppressWarnings("unused")
	private static final Logger	logger			= Logger.getLogger(JSCmdParamGet.class);

	public JSCmdParamGet (SchedulerObjectFactory schedulerObjectFactory) {
		super();
		objFactory = schedulerObjectFactory;
	}
}

package com.sos.scheduler.model.commands;

import org.apache.log4j.Logger;

import com.sos.scheduler.model.SchedulerObjectFactory;

/**
* \class JSCmdShowHistory 
* 
* \brief JSCmdShowHistory - 
* 
* \details
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
* @version $Id$18.01.2011
* \see reference
*
* Created on 18.01.2011 18:28:20
 */

public class JSCmdShowHistory extends ShowHistory {

	@SuppressWarnings("unused")
	private final String		conClassName	= "JSCmdShowHistory";
	@SuppressWarnings("unused")
	private static final Logger	logger			= Logger.getLogger(JSCmdShowHistory.class);

	public JSCmdShowHistory(SchedulerObjectFactory schedulerObjectFactory) {
		super();
		objFactory = schedulerObjectFactory;
	}
 
}
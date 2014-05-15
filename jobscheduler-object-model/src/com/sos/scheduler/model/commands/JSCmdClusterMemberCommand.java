package com.sos.scheduler.model.commands;

import org.apache.log4j.Logger;

import com.sos.scheduler.model.SchedulerObjectFactory;

/**
* \class JSCmdClusterMemberCommand 
* 
* \brief JSCmdClusterMemberCommand - 
* 
* \details
*
* \section JSCmdClusterMemberCommand.java_intro_sec Introduction
*
* \section JSCmdClusterMemberCommand.java_samples Some Samples
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
* Created on 08.02.2011 12:27:33
 */

/**
 * @author oh
 *
 */
public class JSCmdClusterMemberCommand extends ClusterMemberCommand {

	@SuppressWarnings("unused")
	private final String		conClassName	= "JSCmdClusterMemberCommand";
	@SuppressWarnings("unused")
	private static final Logger	logger			= Logger.getLogger(JSCmdClusterMemberCommand.class);

	public JSCmdClusterMemberCommand (SchedulerObjectFactory schedulerObjectFactory) {
		super();
		objFactory = schedulerObjectFactory;
	}
}

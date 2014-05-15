package com.sos.scheduler.model.commands;

import org.apache.log4j.Logger;

import com.sos.scheduler.model.SchedulerObjectFactory;

/**
* \class JSCmdSchedulerLogLogCategoriesSet 
* 
* \brief JSCmdSchedulerLogLogCategoriesSet - 
* 
* \details
*
* \section JSCmdSchedulerLogLogCategoriesSet.java_intro_sec Introduction
*
* \section JSCmdSchedulerLogLogCategoriesSet.java_samples Some Samples
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
* Created on 08.02.2011 15:37:53
 */

/**
 * @author oh
 *
 */
public class JSCmdSchedulerLogLogCategoriesSet extends SchedulerLogLogCategoriesSet {

	@SuppressWarnings("unused")
	private final String		conClassName	= "JSCmdSchedulerLogLogCategoriesSet";
	@SuppressWarnings("unused")
	private static final Logger	logger			= Logger.getLogger(JSCmdSchedulerLogLogCategoriesSet.class);

	public JSCmdSchedulerLogLogCategoriesSet (SchedulerObjectFactory schedulerObjectFactory) {
		super();
		objFactory = schedulerObjectFactory;
	}
}
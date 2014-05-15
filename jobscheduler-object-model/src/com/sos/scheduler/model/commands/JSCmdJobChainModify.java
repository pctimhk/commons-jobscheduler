package com.sos.scheduler.model.commands;

import org.apache.log4j.Logger;

import com.sos.scheduler.model.SchedulerObjectFactory;

/**
* \class JSCmdJobChainModify 
* 
* \brief JSCmdJobChainModify - 
* 
* \details
*
* \section JSCmdJobChainModify.java_intro_sec Introduction
*
* \section JSCmdJobChainModify.java_samples Some Samples
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
* Created on 08.02.2011 14:28:21
 */

/**
 * @author oh
 *
 */
public class JSCmdJobChainModify extends JobChainModify {

	private final String		conClassName	= "JSCmdJobChainModify";
	@SuppressWarnings("unused")
	private static final Logger	logger			= Logger.getLogger(JSCmdJobChainModify.class);
	
	public static enum enu4State {
    	STOPPED, RUNNING

		/**/;
		public String Text() {
			String strT = this.name().toLowerCase();
			return strT;
		}
	}

	public JSCmdJobChainModify (SchedulerObjectFactory schedulerObjectFactory) {
		super();
		objFactory = schedulerObjectFactory;
	}
	
	/**
	 * 
	 * \brief setState
	 * 
	 * \details
	 *
	 * @param penuT
	 */
	public void setState(enu4State penuT) {
	
		@SuppressWarnings("unused")
		final String	conMethodName	= conClassName + "::setState";
	
		super.setState(penuT.Text()); 
	
	} // private void setState
}
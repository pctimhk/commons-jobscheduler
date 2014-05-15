package com.sos.scheduler.model.commands;

import org.apache.log4j.Logger;

import com.sos.scheduler.model.SchedulerObjectFactory;
import com.sos.scheduler.model.SchedulerObjectFactory.enu4What;
import com.sos.scheduler.model.answers.State;

/**
* \class JSCmdShowState 
* 
* \brief JSCmdShowState - 
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

public class JSCmdShowState extends ShowState {

	private final String		conClassName	= "JSCmdShowState";
	@SuppressWarnings("unused")
	private static final Logger	logger			= Logger.getLogger(JSCmdShowState.class);

	public JSCmdShowState(SchedulerObjectFactory schedulerObjectFactory) {
		super();
		objFactory = schedulerObjectFactory;
	}
 
	public State getState() {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::getState";
		State objState = null;
		objState = this.getAnswer().getState();
//		objState.setParent(objFactory);
		return objState;
	} // private State getState

	/**
	 * 
	 * \brief setWhat
	 * 
	 * \details
	 *
	 * \return void
	 *
	 * @param penuT
	 */
	public void setWhat(enu4What penuT) {
	
		@SuppressWarnings("unused")
		final String	conMethodName	= conClassName + "::setWhat";
	
		super.setWhat(penuT.Text()); 
	
	} // private void setWhat

	/**
	 * 
	 * \brief setWhat
	 * 
	 * \details
	 *
	 * \return void
	 *
	 * @param penuT
	 */
	public void setWhat(enu4What[] penuT) {
		
		@SuppressWarnings("unused")
		final String	conMethodName	= conClassName + "::setWhat";
		
		String strT = "";
		for (enu4What enuState4What : penuT) {
			strT += enuState4What.Text() + " ";
		}
		super.setWhat(strT); 
		
	} // private JSCmdShowTask setWhat

	
}
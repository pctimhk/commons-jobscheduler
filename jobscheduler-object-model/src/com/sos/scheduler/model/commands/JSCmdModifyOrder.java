package com.sos.scheduler.model.commands;

import org.apache.log4j.Logger;

import com.sos.scheduler.model.SchedulerObjectFactory;
import com.sos.scheduler.model.objects.Params;

/**
* \class JSCmdModifyOrder 
* 
* \brief JSCmdModifyOrder - 
* 
* \details
*
* \section JSCmdModifyOrder.java_intro_sec Introduction
*
* \section JSCmdModifyOrder.java_samples Some Samples
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
* Created on 08.02.2011 14:46:47
 */

/**
 * @author oh
 *
 */
public class JSCmdModifyOrder extends ModifyOrder {

	@SuppressWarnings("unused")
	private final String		conClassName	= "JSCmdModifyOrder";
	@SuppressWarnings("unused")
	private static final Logger	logger			= Logger.getLogger(JSCmdModifyOrder.class);

	public JSCmdModifyOrder (SchedulerObjectFactory schedulerObjectFactory) {
		super();
		objFactory = schedulerObjectFactory;
	}
	
	   /**
     * 
     * \brief setParams
     * 
     * \details
     *
     * \return Params
     *
     * @param pstrParamArray
     * @return
     */
    public Params setParams(String[] pstrParamArray) {

        @SuppressWarnings("unused")
        final String conMethodName = conClassName + "::setParams";
 
        Params objParams = objFactory.setParams(pstrParamArray);
        super.setParams(objParams);

        return objParams;
    } // private Params setParams

}

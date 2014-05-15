package com.sos.scheduler.model;

import java.io.File;

/**
* \class IJSCmdBase 
* 
* \brief IJSCmdBase - 
* 
* \details
*
* \section IJSCmdBase.java_intro_sec Introduction
*
* \section IJSCmdBase.java_samples Some Samples
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
* Created on 18.01.2011 19:13:22
 */

public interface IJSCmdBase {

	public abstract void run ();
	/**
	 * 
	 * \brief unMarshall
	 * 
	 * \details
	 *
	 * \return Object
	 *
	 * @param pobjFile
	 * @return
	 */
	public abstract Object unMarshal(File pobjFile); // private Object unMarshall

	/**
	 * 
	 * \brief marshal
	 * 
	 * \details
	 *
	 * \return Object
	 *
	 * @param objO
	 * @param objF
	 */
	public abstract Object marshal(Object objO, File objF); // private SchedulerObjectFactoryOptions marshal

	public abstract String toXMLString(Object objO); // private SchedulerObjectFactoryOptions marshal

	public abstract String toXMLString(); // private SchedulerObjectFactoryOptions marshal

}
package com.sos.JSHelper.Logging;

import org.apache.log4j.Logger;

import com.sos.JSHelper.Exceptions.JobSchedulerException;

/**
 * \file MissingAppenderException.java
 * \brief Appender ist nicht in Konfigurationsfile vorhanden
 *  
 * \class MissingAppenderException 
 * \brief Appender ist nicht in Konfigurationsfile vorhanden
 * 
 * \details
 * Diese Exception wird ausgel�st, wenn ein erwarteter Appender nicht im Konfigurationsfile
 * vorhanden ist.
 *
 * \author EQCPN
* @version $Id$13.05.2009 11:13:33
 *
 * <div class="sos_branding">
 *   <p>� 2009 APL/Software GmbH - Berlin - generated by ClaviusXPress (<a style="color:silver" href="http://www.sos-berlin.com" target="_blank">http://www.sos-berlin.com</a>)</p>
 * </div>
 */
public class MissingAppenderException extends JobSchedulerException {
	
	@SuppressWarnings("unused")
	private static final String	conClassName		= "MissingAppenderException";
	private static final long	serialVersionUID	= 1L;
	private static Logger		logger				= Logger.getRootLogger();

	/*!
	 * @brief Construtor with message.
	 *
	 * @param msg the message-text of the exception
	 */
	public MissingAppenderException(String pstrMessage) {
		super(pstrMessage);
		this.Message(pstrMessage);
		this.Status(JobSchedulerException.PENDING);
		logger.error(this.ExceptionText());
		//        this.Category(CategoryOptions);
		//        this.Typ(TypeOptionMissing);
	}

	public MissingAppenderException() {
		this("exception 'MissingAppenderException' raised ...");
		logger.error(this.ExceptionText());
	}

	public String ExceptionText() {
		String strT = null;
		strT = super.ExceptionText();
		return strT;
	}
	
}
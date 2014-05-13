package com.sos.hibernate.layer;
 
import java.util.Date;
import java.util.GregorianCalendar;

import com.sos.hibernate.classes.SOSHibernateIntervalFilter;
 

/**
* \class SOSHibernateDBLayer 
* 
* \brief SOSHibernateDBLayer - 
* 
* \details
*
* \section SOSHibernateDBLayer.java_intro_sec Introduction
*
* \section SOSHibernateDBLayer.java_samples Some Samples
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
* \author Uwe Risse
* \version 06.10.2011
* \see reference
*
* Created on 06.10.2011 14:23:43
 */

public abstract class SOSHibernateIntervalDBLayer extends SOSHibernateDBLayer {
 
	@SuppressWarnings("unused")
	private final String	conClassName	= "SOSHibernateDBLayer";
	
	public abstract SOSHibernateIntervalFilter getFilter();
	public abstract int deleteInterval();
	 

	public SOSHibernateIntervalDBLayer() {
		super();
	}
	
 
 
	public int deleteInterval(int interval) {
		if (interval > 0) {
			GregorianCalendar to = new GregorianCalendar();
			to.add(GregorianCalendar.DAY_OF_YEAR, -interval);
			this.getFilter().setIntervalFrom(null);
			this.getFilter().setIntervalTo(to.getTime());
			int ret =  this.deleteInterval();
			return ret;
		} else {
			return interval;
		}
	}

	public int deleteInterval(String interval) {
		int actInterval = -1;
		try {
			actInterval = Integer.parseInt(interval);
			return deleteInterval(actInterval);
		} catch (Exception e) {
			return -1;
		}
	}
	
	
	
	

}
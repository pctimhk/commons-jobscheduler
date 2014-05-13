package com.sos.hibernate.interfaces;

import com.sos.hibernate.classes.DbItem;

 
/**
* \class Filter 
* 
* \brief Filter - 
* 
* \details
*
* \section Filter.java_intro_sec Introduction
*
* \section Filter.java_samples Some Samples
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
* \version 19.01.2012
* \see reference
*
* Created on 19.01.2012 12:52:09
 */

public interface ISOSHibernateFilter {
	public boolean isFiltered(DbItem h);
	public String getTitle();
}
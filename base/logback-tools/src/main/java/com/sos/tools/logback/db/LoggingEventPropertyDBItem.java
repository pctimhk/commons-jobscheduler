package com.sos.tools.logback.db;

/**
* \class JobnetPlanDBItem 
* 
* \brief JobnetPlanDBItem - 
* 
* \details
*
* \section JobnetPlanDBItem.java_intro_sec Introduction
*
* \section JobnetPlanDBItem.java_samples Some Samples
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
* \version 23.09.2011
* \see reference
*
* Created on 23.09.2011 15:08:05
 */

import com.sos.hibernate.classes.DbItem;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
@Table(name="LOGGING_EVENT_PROPERTY")
public class LoggingEventPropertyDBItem extends DbItem {

	private static Logger logger = LoggerFactory.getLogger(LoggingEventPropertyDBItem.class);
	
	 private LoggingEventDBItem loggingEventDBItem = null;
     private Long eventId;
     private String mappedKey;
     private String mappedValue;

     private LoggingEventPropertyPK primaryKey;


 	public LoggingEventPropertyDBItem() {
 	   super();
 	}

    @EmbeddedId
    public LoggingEventPropertyPK getPrimaryKey() {
        return primaryKey;
    }

    @EmbeddedId
    public void setPrimaryKey(LoggingEventPropertyPK primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Column(name="EVENT_ID", insertable = false, updatable = false)
    public Long getEventId() {
		return eventId;
	}

    @Column(name="EVENT_ID", insertable = false, updatable = false)
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

    @Column(name="MAPPED_KEY", insertable = false, updatable = false)
    public String getMappedKey() {
		return mappedKey;
	}

    @Column(name="MAPPED_KEY", insertable = false, updatable = false)
	public void setMappedKey(String mappedKey) {
		this.mappedKey = mappedKey;
	}

    @Column(name="MAPPED_VALUE")
    public String getMappedValue() {
		return mappedValue;
	}

    @Column(name="MAPPED_VALUE")
	public void setMappedValue(String mappedValue) {
		this.mappedValue = mappedValue;
	}

    @ManyToOne (optional=false)
    @NotFound( action = NotFoundAction.EXCEPTION )
    @Cascade(CascadeType.ALL)
    @JoinColumn (name="`EVENT_ID`", insertable = false, updatable = false)
    public LoggingEventDBItem getLoggingEventDBItem() {
        return loggingEventDBItem;
    }
    
    public void setLoggingEventDBItem(LoggingEventDBItem loggingEventDBItem)  {
       this.loggingEventDBItem = loggingEventDBItem;
    }
    
}

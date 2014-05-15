package com.sos.scheduler.model.objects;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import com.sos.JSHelper.DataElements.JSDataElementDate;
import com.sos.JSHelper.DataElements.JSDateFormat;
import com.sos.JSHelper.Exceptions.JobSchedulerException;
import com.sos.VirtualFileSystem.Interfaces.ISOSVirtualFile;
import com.sos.scheduler.model.SchedulerObjectFactory;
import com.sos.scheduler.model.objects.Holidays.Weekdays.Day;
import com.sos.scheduler.model.tools.RunTimeElement;
import com.sos.scheduler.model.tools.RunTimeElements;

/**
* \class JSObjHolidays 
* 
* \brief JSObjHolidays - 
* 
* \details
*
* \section JSObjHolidays.java_intro_sec Introduction
*
* \section JSObjHolidays.java_samples Some Samples
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
* Created on 08.02.2011 12:03:17
 */

/**
 * @author oh
 *
 */
public class JSObjHolidays extends Holidays {

	private final String		conClassName	= "JSObjHolidays";
	private static final Logger	logger			= Logger.getLogger(JSObjHolidays.class);
	
	private List<JSObjInclude> includes = null;
	
	public JSObjHolidays(SchedulerObjectFactory schedulerObjectFactory) {
		super();
		objFactory = schedulerObjectFactory;
	}

	public JSObjHolidays(SchedulerObjectFactory schedulerObjectFactory, ISOSVirtualFile pobjVirtualFile) {
		this(schedulerObjectFactory);
		Holidays objHolidays = (Holidays) unMarshal(pobjVirtualFile);
		setObjectFieldsFrom(objHolidays);
		setHotFolderSrc(pobjVirtualFile);
	}

	public boolean isAHoliday(final Calendar pobjCalendar) {
	
	@SuppressWarnings("unused")
	final String	conMethodName	= conClassName + "::isAHoliday";
	
	return isAHoliday(pobjCalendar.getTime());
} // private boolean isAHoliday

	public boolean isAHoliday(Date pobjDate) {

		@SuppressWarnings("unused")
		final String conMethodName = conClassName + "::isAHoliday";

		boolean flgIsAHoliday = false;
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(pobjDate);
		int intDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		intDayOfWeek -= 1;
		if (intDayOfWeek == 0) {
			intDayOfWeek = 7;
		}
		
		for (Object objO : this.getWeekdaysOrHolidayOrInclude()) {
			if (objO instanceof Weekdays) {
				Weekdays objW = (Weekdays) objO;
				for (Day objDay : objW.day) {
					for (String strD : objDay.day) {
//						logger.info("day = " + strD);
						if (intDayOfWeek == new Integer(strD)) {
							flgIsAHoliday = true;
							return flgIsAHoliday;
						}
					}
				}
			}
		}

		for (Object objO : this.getWeekdaysOrHolidayOrInclude()) {
			if (objO instanceof Holiday) {
				Holiday objH = (Holiday) objO;
				String strD = objH.getDate();
				Date objD = new JSDataElementDate(strD, JSDateFormat.dfDATE_SHORT).getDateObject();
//				long l1 = objD.getTime();
//				long l2 = pobjDate.getTime();
//				System.out.println("l1 = " + l1 + ", l2 = " + l2);
				if (objD.equals(pobjDate)) {
					flgIsAHoliday = true;
					return flgIsAHoliday;
				}
			}
		}

		for (Object objO : this.getWeekdaysOrHolidayOrInclude()) {
			if (objO instanceof Include) {

			}
		}

		return flgIsAHoliday;
	} // private boolean isAHoliday
	
	public boolean isHoliday(DateTime date) {
		DateTime from = JodaTools.getStartOfDay(date);
		List<DateTime> result = getHolidays(new Interval(from,from.plusDays(1)));
		return (result.size() == 0) ? false : true;
	}
	
	public List<DateTime> getHolidays(Interval timeRange) {
		List<DateTime> result = new ArrayList<DateTime>();
		result.addAll(getDtHoliday(timeRange));
		result.addAll(getDtWeekdays(timeRange));
		result.addAll(getDtInclude(timeRange));		// holidays via the <include> element
		return result;
	}
	
	public DateTime getNextNonHoliday(DateTime date) {
		DateTime result = date;
		while (isHoliday(result)) {
			result = result.plusDays(1);
		}
		return result;
	}
	
	public DateTime getPreviousNonHoliday(DateTime date) {
		DateTime result = date;
		while (isHoliday(result)) {
			result = result.minusDays(1);
		}
		return result;
	}
	
	private List<DateTime> getDtWeekdays(Interval timeRange) {
		List<DateTime> result = new ArrayList<DateTime>();
		Iterator<Object> it = getWeekdaysOrHolidayOrInclude().iterator();
		while(it.hasNext()) {
			Object o = it.next();
			if (o instanceof Weekdays) {
				Weekdays w = (Weekdays)o;
				JSObjHolidaysWeekdays weekdays = new JSObjHolidaysWeekdays(objFactory);
				weekdays.setObjectFieldsFrom(w);
				result.addAll(weekdays.getDtHolidays(timeRange));
			}
		}
		return result;
	}
	
	private List<DateTime> getDtHoliday(Interval timeRange) {
		List<DateTime> result = new ArrayList<DateTime>();
		Iterator<Object> it = getWeekdaysOrHolidayOrInclude().iterator();
		while(it.hasNext()) {
			Object o = it.next();
			if (o instanceof Holiday) {
				Holiday h = (Holiday)o;
				JSObjHoliday holiday = new JSObjHoliday(objFactory);
				holiday.setObjectFieldsFrom(h);
				DateTime d = holiday.getDtHoliday();
				if (timeRange.contains(d))
					result.add(d);
			}
		}
		return result;
	}
	
	private List<DateTime> getDtInclude(Interval timeRange) {
		List<DateTime> result = new ArrayList<DateTime>();
		Iterator<Object> it = getWeekdaysOrHolidayOrInclude().iterator();
		while(it.hasNext()) {
			Object o = it.next();
			if (o instanceof Include) {
				Include i = (Include)o;
				logger.warn("the <include> element is not parsed yet.");
				logger.debug(i.getLiveFile());
				logger.debug(i.getHotFolderSrc());
				logger.debug(getHotFolderSrc());
			}
		}
		return result;
	}
	
	/**
	 * \brief move the calculated start dates if it is demanded by holidays definiton 
	 * \detail
	 *
	 * @param runTimes
	 * @return
	 */
	public List<DateTime> getStartDatesAwareHolidays(RunTimeElements runTimes) {
		List<DateTime> result = new ArrayList<DateTime>();
		Interval timeRange = runTimes.getTimeRange();
		if (getHolidays(timeRange).size() == 0)
			return runTimes.getStartTimes();
		
		for(RunTimeElement runTime : runTimes.values()) {
			if (isHoliday(runTime.getStartDate())) {
				switch (runTime.getWhenHoliday()) {
					case SUPPRESS:
						break;
					case IGNORE_HOLIDAY:
						if (!result.contains(runTime.getStartDate())) 
							result.add(runTime.getStartDate());
						break;
					case NEXT_NON_HOLIDAY:
						DateTime nextStart = getNextNonHoliday(runTime.getStartDate());
						if (timeRange.contains(nextStart) && !result.contains(nextStart))
							result.add(nextStart);
						break;
					case PREVIOUS_NON_HOLIDAY:
						DateTime previousStart = getPreviousNonHoliday(runTime.getStartDate());
						if (timeRange.contains(previousStart) && !result.contains(previousStart))
							result.add(previousStart);
						break;
				}
			}
		}
		
		return result;
	}
	
	public List<JSObjInclude> getJsObjInclude() {
		if (includes == null) {
			includes = new ArrayList<JSObjInclude>();
			Iterator<Object> it = getWeekdaysOrHolidayOrInclude().iterator();
			while(it.hasNext()) {
				Object o = it.next();
				if (o instanceof Include) {
					Include i = (Include)o;
					JSObjInclude include = new JSObjInclude(objFactory);
					include.setObjectFieldsFrom(i);
					include.setHotFolderSrc(getHotFolderSrc());
					includes.add(include);
				}
			}
		}
		return includes;
	}
	
	/**
	 * \brief resolving the include for the holidays
	 * \detail
	 * resolves the include element inside the holidays element and integrates the result into 
	 * the holidays object.
	 * The resolution of the includes works similiar the include resolution inside the jobscheduler
	 * except the file attribute could not contain a relative path. Thats why the -config folder
	 * is not present here.
	 */
	public void resolveIncludes() {
		List<JSObjInclude> list = getJsObjInclude();
		for(JSObjInclude include : list) {
			JSObjHolidays includeHolidays = new JSObjHolidays(objFactory, include.getHotFolderSrc() );
			weekdaysOrHolidayOrInclude.addAll(includeHolidays.getWeekdaysOrHolidayOrInclude());
		}
		for(int i = getWeekdaysOrHolidayOrInclude().size() - 1; i >= 0; i--) {
			Object o = getWeekdaysOrHolidayOrInclude().get(i);
			if(o instanceof Include)
				weekdaysOrHolidayOrInclude.remove(o);
		}
	}
	
	
}
package com.event.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.event.domain.CalendarEvent;

@Repository("CalendarDao")
public class CalendarDaoImpl implements CalendarDao {

	private static Logger log = LoggerFactory.getLogger(CalendarDaoImpl.class);
	
	private static final int DAY = 1;
	private static final int WEEK = 7;
	private static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
	
	@PersistenceContext
	protected EntityManager entityManager;
	
    @Transactional(readOnly = true)
    @Override	
	public List<CalendarEvent> getSingleDayEvents(String user, int year, int month, int day) {
    	
    	return getEvents(user, year, month, day, DAY);
 	}
    
    @Transactional(readOnly = true)
    @Override	
	public List<CalendarEvent> getWeekEvents(String user, int year, int month, int day) {
    	
    	return getEvents(user, year, month, day, WEEK);
 	}
    
    @Transactional(readOnly = true)
    @Override	
	public List<CalendarEvent> getMonthEvents(String user, int year, int month) {
    	
    	List<CalendarEvent> rtnList;

    	SimpleDateFormat format  = new SimpleDateFormat("dd-MM-yyyy:HH:mm:SS");
    	Date lowDate = new Date(year - 1900, month - 1, 1);
    	Date highDate = new Date(year, month, 1);
    	
    	log.debug("lowDate " + format.format(lowDate));
    	log.debug("highDate " + format.format(highDate));    	
    	
		TypedQuery<CalendarEvent> query = entityManager
				.createQuery("SELECT c FROM CalendarEvent c WHERE c.calendar.user = :user  AND eventDate >= :lowDate AND eventDate < :highDate", CalendarEvent.class)
				.setParameter("user", user)
				.setParameter("lowDate", lowDate)
				.setParameter("highDate", highDate);
 
    	rtnList =  query.getResultList();
    	
    	return rtnList;

 	}    

    @Transactional  
    public boolean saveEvent(CalendarEvent event) {
    	entityManager.merge(event.getCalendar());
    	entityManager.merge(event);
    	return true;
    }
    
    /**
     * get calendar event for the next ten minutes to sendreminder
     * 
     * @return
     * 	List of calendar events to send reminder
     */
    @Transactional(readOnly = true)
    @Override	    
    public List<CalendarEvent> getComingEvents() {
    	List<CalendarEvent> rtnList;
    	
    	Calendar date = Calendar.getInstance();
    	long t= date.getTimeInMillis();
    	
		TypedQuery<CalendarEvent> query = entityManager
				.createQuery("SELECT c FROM CalendarEvent c WHERE reminder = 0 AND eventDate >= :lowDate AND eventDate < :highDate", CalendarEvent.class)
				.setParameter("lowDate", new Date())
				.setParameter("highDate", new Date(t + 10 * ONE_MINUTE_IN_MILLIS));
 
    	rtnList =  query.getResultList();  
    	
    	return rtnList;
    }
    
	private List<CalendarEvent> getEvents(String user, int year, int month, int day, int addedDays) {
		
    	List<CalendarEvent> rtnList;

    	SimpleDateFormat format  = new SimpleDateFormat("dd-MM-yyyy:HH:mm:SS");
    	Date lowDate = new Date(year - 1900, month - 1, day);
    	Date highDate = addDays(lowDate, addedDays);
    	log.debug("lowDate " + format.format(lowDate));
    	log.debug("highDate " + format.format(highDate));
    	
    	
		TypedQuery<CalendarEvent> query = entityManager
				.createQuery("SELECT c FROM CalendarEvent c WHERE c.calendar.user = :user  AND eventDate >= :lowDate AND eventDate < :highDate", CalendarEvent.class)
				.setParameter("user", user)
				.setParameter("lowDate", lowDate)
				.setParameter("highDate", highDate);
    	

    	rtnList =  query.getResultList();

    	
    	return rtnList;
 	}
    
    private Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }    
}

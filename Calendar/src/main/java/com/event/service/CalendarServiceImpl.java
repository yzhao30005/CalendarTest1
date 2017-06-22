package com.event.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.dao.CalendarDao;
import com.event.domain.CalendarEvent;


/**
 * @author Yan
 *
 */
@Service(value = "CalendarService")
public class CalendarServiceImpl implements CalendarService {
	
	private static Logger log = LoggerFactory.getLogger(CalendarServiceImpl.class);
	private static SimpleDateFormat format  = new SimpleDateFormat("yyyy/MM/dd :HH:mm:SS");
	
    @Autowired
    private CalendarDao calendarDao;
  
 	@Override
	public List<CalendarEvent> getSingleDayEvents(String user, int year, int month, int day) {
 		
 		log.debug("enter method getSingleDayEvents");
 		List<CalendarEvent> list =  calendarDao.getSingleDayEvents(user, year, month, day);
		for(CalendarEvent event : list) {
			event.setEventDateStr(format.format(event.getEventDate()));
			event.setReminderStr(format.format(event.getReminder()));
		}
		
		log.debug("exit method getSingleDayEvents");
		return list;
	}
	
	@Override
	public List<CalendarEvent> getWeekEvents(String user, int year, int month, int day) {
		
 		log.debug("enter method getWeekEvents");
 		
		List<CalendarEvent> list = calendarDao.getWeekEvents(user, year, month, day);
		
		for(CalendarEvent event : list) {
			event.setEventDateStr(format.format(event.getEventDate()));
			event.setReminderStr(format.format(event.getReminder()));
		}
		
 		log.debug("exit method getWeekEvents");
		
		return list;
	}

	@Override
	public List<CalendarEvent> getMonthEvents(String user, int year, int month) {
		List<CalendarEvent> list = calendarDao.getMonthEvents(user, year, month);

		log.debug("enter method getMonthEvents");
		
		for(CalendarEvent event : list) {
			event.setEventDateStr(format.format(event.getEventDate()));
			event.setReminderStr(format.format(event.getReminder()));
		}
		
 		log.debug("exit method getMonthEvents");

 		return list;		
	}
	
    public boolean saveEvent(CalendarEvent event) {
    	event.getEventDateStr();
    	
    	try {
    	if(event.getEventDateStr() != null)
    		event.setEventDate(format.parse(event.getEventDateStr()));
    	
    	if(event.getReminderStr() != null)
    		event.setReminder(format.parse(event.getReminderStr()));   	
  	 
    	} catch(java.text.ParseException e) {
    		e.printStackTrace();
    		log.error("Parse error: " + e.getMessage());
    	}
    	return calendarDao.saveEvent(event);
    }
}

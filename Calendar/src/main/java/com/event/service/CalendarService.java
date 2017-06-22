package com.event.service;

import java.util.List;

import com.event.domain.CalendarEvent;

public interface CalendarService {

	List<CalendarEvent> getSingleDayEvents(String user, int year, int month, int day);

	List<CalendarEvent> getWeekEvents(String user, int year, int month, int day);
	
	List<CalendarEvent> getMonthEvents(String user, int year, int month);
	
    boolean saveEvent(CalendarEvent event);
    
}

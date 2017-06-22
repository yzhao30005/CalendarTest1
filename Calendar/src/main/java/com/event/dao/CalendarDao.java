package com.event.dao;

import java.util.Date;
import java.util.List;

import com.event.domain.CalendarEvent;

public interface CalendarDao {

	List<CalendarEvent> getSingleDayEvents(String user, int year, int month, int day);

	List<CalendarEvent> getWeekEvents(String user, int year, int month, int day);
	
	List<CalendarEvent> getMonthEvents(String user, int year, int month);
	
	List<CalendarEvent> getComingEvents();
	
	boolean saveEvent(CalendarEvent event);
}

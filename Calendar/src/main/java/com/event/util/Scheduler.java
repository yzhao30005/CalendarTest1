package com.event.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.event.dao.CalendarDao;
import com.event.domain.CalendarEvent;

@Component
public class Scheduler {

    private static final SimpleDateFormat dateFormat = 
            new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    	@Autowired
        private CalendarDao calendarDao;
    	
        @Scheduled(fixedRate = 60000)
        public void sendReminder() {
        	
        	List<CalendarEvent> events = calendarDao.getComingEvents();
        	
        	for(CalendarEvent event : events) {
        		System.out.println("Calendar event: " + event.getTitle());
        		
        		event.setReminderSent(1);
        		calendarDao.saveEvent(event);
        	}



        }	
}

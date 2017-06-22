package com.event.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.event.dao.CalendarDaoImpl;
import com.event.domain.CalendarEvent;
import com.event.service.CalendarService;


/**
 * @author Yan
 * Calendar REST controller
 */
@RestController
@RequestMapping("/calendar")
public class CalendarController {

	private static Logger log = LoggerFactory.getLogger(CalendarController.class);

	@Autowired
	CalendarService calendarService;

	/**
	 * get calendar events of a day
	 * @param user
	 * @param date
	 * @return
	 */
    @RequestMapping(value="/day/{user}/{date}", method = RequestMethod.GET, produces = "application/json")
    public List<CalendarEvent> getSingleDayEvents(@PathVariable("user") String user,
    		@PathVariable("date") String date) {
    	log.debug("getSingleDayEvents..");

    	int year = Integer.parseInt(date.substring(0, 4));
    	int month = Integer.parseInt(date.substring(4, 6));
    	int day = Integer.parseInt(date.substring(6, 8));

        return calendarService.getSingleDayEvents(user, year, month, day);
    }

	/**
	 * get calendar events of a week
	 * @param user
	 * @param date
	 * @return
	 */
    @RequestMapping(value="/week/{user}/{date}", method = RequestMethod.GET, produces = "application/json")
    public List<CalendarEvent> getWeekEvents(@PathVariable("user") String user,
    		@PathVariable("date") String date) {
    	log.debug("getWeekEvents..");

    	int year = Integer.parseInt(date.substring(0, 4));
    	int month = Integer.parseInt(date.substring(4, 6));
    	int day = Integer.parseInt(date.substring(6, 8));

        return calendarService.getWeekEvents(user, year, month, day);
    }

	/**
	 * get calendar events of a month
	 * @param user
	 * @param date
	 * @return
	 */
    @RequestMapping(value="/month/{user}/{date}", method = RequestMethod.GET, produces = "application/json")
    public List<CalendarEvent> getMonthEvents(@PathVariable("user") String user,
    		@PathVariable("date") String date) {
    	log.debug("getMonthEvents..");
    	int year = Integer.parseInt(date.substring(0, 4));
    	int month = Integer.parseInt(date.substring(4, 6));

        return calendarService.getMonthEvents(user, year, month);
    }

    /**
     * REST API to save a calendar event
     * @param event
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public boolean saveEvent(@RequestBody CalendarEvent event) {
        return calendarService.saveEvent(event);
    }

}

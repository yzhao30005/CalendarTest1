package com.event.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "CALENDAR_EVENT")
public class CalendarEvent {

	@Id
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "CALENDAR_ID")
	protected Calendar calendar;
	
	protected String title;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EVENT_DATE")
	protected Date eventDate;
	
	@Transient
	protected String eventDateStr;
	
	protected String location;
	
	protected String Attendees;
	
	@Temporal(TemporalType.TIMESTAMP)	
	protected Date reminder;
	
	@Transient
	protected String reminderStr;
	
	protected int reminderSent;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAttendees() {
		return Attendees;
	}

	public void setAttendees(String attendees) {
		Attendees = attendees;
	}

	public Date getReminder() {
		return reminder;
	}

	public void setReminder(Date reminder) {
		this.reminder = reminder;
	}

	public int getReminderSent() {
		return reminderSent;
	}

	public void setReminderSent(int reminderSent) {
		this.reminderSent = reminderSent;
	}

	public String getEventDateStr() {
		return eventDateStr;
	}

	public void setEventDateStr(String eventDateStr) {
		this.eventDateStr = eventDateStr;
	}

	public String getReminderStr() {
		return reminderStr;
	}

	public void setReminderStr(String reminderStr) {
		this.reminderStr = reminderStr;
	}
}

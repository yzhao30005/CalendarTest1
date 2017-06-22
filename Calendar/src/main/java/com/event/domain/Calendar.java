package com.event.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Calendar {
	
	@Id
	protected int id;
	
	protected String name;
	
	protected String user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	
}

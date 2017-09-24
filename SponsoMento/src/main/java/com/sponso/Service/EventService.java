package com.sponso.Service;

import java.util.List;

import com.sponso.Pojo.Event;

public interface EventService {

	public boolean save(Event e);
	
	public List<Event>getall();
	
	public List<Event> getbyskill(String skill);
}

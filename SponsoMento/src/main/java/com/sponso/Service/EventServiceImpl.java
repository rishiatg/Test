package com.sponso.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sponso.Pojo.Event;
import com.sponso.repo.EventRepo;

@Service
@Component
@Transactional
public class EventServiceImpl implements EventService {


	@Autowired
	private EventRepo rep;
	
	public boolean save(Event e) {
		 if(rep.save(e)!=null)
			 return true;
		 else
			 return false;
	}

	public List<Event> getall() {
		return (List<Event>) rep.findAll();
	}
	
	public List<Event> getbyskill(String skill) {
		return (List<Event>) rep.findBySkill(skill);
	}

}

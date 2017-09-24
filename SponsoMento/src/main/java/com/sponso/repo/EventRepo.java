package com.sponso.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sponso.Pojo.Event;

public interface EventRepo extends CrudRepository<Event, String> {

	public List<Event> findBySkill(String skill);
}

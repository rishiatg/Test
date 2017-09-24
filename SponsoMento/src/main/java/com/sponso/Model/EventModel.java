package com.sponso.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sponso.Pojo.Sponser;

public class EventModel {

	private String name;
	
	private String skill;
	 
	private String location;

	public int sponserId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getSponserId() {
		return sponserId;
	}

	public void setSponserId(int sponserId) {
		this.sponserId = sponserId;
	}

	
}

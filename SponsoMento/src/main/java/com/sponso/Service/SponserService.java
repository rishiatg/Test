package com.sponso.Service;

import java.util.List;


import com.sponso.Model.LoginModel;
import com.sponso.Pojo.Sponser;
import com.sponso.Pojo.SponserLink;
import com.sponso.Pojo.Student;

public interface SponserService {

	public String save(Sponser s);
	
	public List<Sponser> getall();
	
	public Sponser get(LoginModel model);
	
	public Sponser getbyid(int id);
	
	public List<SponserLink> getsponsored(int id);
	
	public boolean fund(SponserLink link);
	
	public List<Student> getbyskill(String skill);
}

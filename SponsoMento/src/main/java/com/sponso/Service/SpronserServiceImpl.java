package com.sponso.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sponso.Model.LoginModel;
import com.sponso.Pojo.Sponser;
import com.sponso.Pojo.SponserLink;
import com.sponso.Pojo.Student;
import com.sponso.repo.SponserLinkRepo;
import com.sponso.repo.SponserRepo;
import com.sponso.repo.StudentRepo;

@Service
@Component
@Transactional
public class SpronserServiceImpl implements SponserService {

	@Autowired
	private SponserRepo rep;
	
	@Autowired
	private SponserLinkRepo srepo;
	
	@Autowired
	private StudentRepo strepo;
	
	public String save(Sponser s){
		if(rep.save(s)!=null)
			return "success";
		else 
			return "failure";
		
	}
	
	public List<Sponser> getall(){
	 	return (List<Sponser>) rep.findAll();
	}
	
	public Sponser get(LoginModel model){
		return rep.findbyemail(model.getEmail(), model.getPassword());
	}
	
	public Sponser getbyid(int id){
		return rep.findById(id);
	
	}
	
	public List<SponserLink> getsponsored(int id){
		return  srepo.findBySponsorId(id);
	}
	
	public List<Student> getbyskill(String skill){
		return strepo.getbyskill(skill);
	}
	
	public boolean fund(SponserLink link){
	 if(srepo.save(link)!=null)
		 return true;
	 else
		 return false;			
	}
	
	
}

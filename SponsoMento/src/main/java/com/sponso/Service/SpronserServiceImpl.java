package com.sponso.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sponso.Model.LoginModel;
import com.sponso.Pojo.Sponser;
import com.sponso.Pojo.SponserLink;
import com.sponso.repo.SponserLinkRepo;
import com.sponso.repo.SponserRepo;

@Service
@Component
@Transactional
public class SpronserServiceImpl implements SponserService {

	@Autowired
	private SponserRepo rep;
	
	@Autowired
	private SponserLinkRepo srepo;
	
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
}

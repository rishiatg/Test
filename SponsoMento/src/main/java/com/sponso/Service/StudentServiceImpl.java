package com.sponso.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sponso.Model.LoginModel;
import com.sponso.Pojo.Sponser;
import com.sponso.Pojo.Student;
import com.sponso.repo.StudentRepo;

@Service
@Component
@Transactional
public class StudentServiceImpl implements StudentService{

	@Autowired
	public StudentRepo rep;
	
	public String save(Student s){
		if(rep.save(s)!=null)
			return "success";
		else 
			return "failure";
		}
	
	public List<Student> getall(){
	return (List<Student>) rep.findAll();
	}

	public Student get(LoginModel model) {
		
		return rep.findbyemail(model.getEmail(), model.getPassword());
	}
	
	public Student getbyid(int id){
		return rep.findById(id);
	}
	
	public List<Student> getstudenttosponser(){
		return rep.getstudenttosponser();
	}
	
	}

package com.sponso.Service;

import java.util.List;

import com.sponso.Model.LoginModel;
import com.sponso.Pojo.Student;

public interface StudentService {

	public String save(Student s);
	
	public List<Student> getall();
	
	public Student get(LoginModel model);
	
	public Student getbyid(int id);
	
	public List<Student> getstudenttosponser();
}

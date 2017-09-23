package com.sponso.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sponso.Pojo.Sponser;
import com.sponso.Pojo.Student;

@Repository
public interface StudentRepo extends CrudRepository<Student, Integer>  {

	public Student findById(int id);
	
	@Query("SELECT s FROM Student s WHERE s.fund_needed > 0")
	public List<Student> getstudenttosponser();
	
	@Query("SELECT s FROM Student s WHERE LOWER(s.email) = LOWER(:email) and s.password =:password")
	public Student findbyemail(@Param("email")String email,@Param("password")String password);
}

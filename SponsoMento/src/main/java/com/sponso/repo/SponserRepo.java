package com.sponso.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sponso.Pojo.Sponser;

@Repository
public interface SponserRepo extends CrudRepository<Sponser, Integer>{

	public Sponser findById(int id);

	@Query("SELECT s FROM Sponser s WHERE LOWER(s.email) = LOWER(:email) and s.password =:password")
	public Sponser findbyemail(@Param("email")String email,@Param("password")String password);
}

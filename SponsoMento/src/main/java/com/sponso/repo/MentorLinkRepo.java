package com.sponso.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sponso.Pojo.MentorLink;

@Repository
public interface MentorLinkRepo extends CrudRepository<MentorLink, Integer>{

	//@Query("SELECT s FROM mentorlink s WHERE s.sponsoId =:sponsor_id")
	public MentorLink findBySponsorId(@Param("sponsor_id")int sponsor_id);
}

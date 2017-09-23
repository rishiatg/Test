package com.sponso.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sponso.Pojo.SponserLink;

@Repository
public interface SponserLinkRepo extends CrudRepository<SponserLink, String>{

	//@Query("SELECT s FROM Sponserlink s WHERE sponsor_id = sponsor_id")
	public List<SponserLink> findBySponsorId(int sponsor_id);
}

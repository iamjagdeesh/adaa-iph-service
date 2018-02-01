package com.emc.ps.appmod.adaa.iph.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.emc.ps.appmod.adaa.iph.entities.CountryGroup;

public interface CountryGroupRepo extends CrudRepository<CountryGroup, Integer>{

	@Query("from CountryGroup c order by c.id asc")
	public List<CountryGroup> findAll();
	
}

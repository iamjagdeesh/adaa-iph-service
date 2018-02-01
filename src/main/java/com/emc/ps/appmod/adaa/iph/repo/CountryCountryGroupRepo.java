package com.emc.ps.appmod.adaa.iph.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.emc.ps.appmod.adaa.iph.entities.CountryCountryGroup;

public interface CountryCountryGroupRepo extends CrudRepository<CountryCountryGroup, Integer>{
	
	@Query("from CountryCountryGroup ccg order by ccg.countryGroup.id, ccg.country.id")
	List<CountryCountryGroup> findAll();

}

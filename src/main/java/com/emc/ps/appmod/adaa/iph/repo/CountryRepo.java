package com.emc.ps.appmod.adaa.iph.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.emc.ps.appmod.adaa.iph.entities.Country;

public interface CountryRepo extends CrudRepository<Country, String>{
	
	@Query("from Country c where c.id <> 'IL' order by c.nameEn asc")
	public List<Country> findAll();
	
	@Query("from Country c where c.id <> 'IL' order by c.nameAr asc")
	public List<Country> findAllArabic();
	
	@Query("from Country c where c.id in (Select ccg.country.id from CountryCountryGroup ccg where ccg.countryGroup.id=:countryGroupId) and c.id <> 'IL' order by c.id")
	public List<Country> findByCountryGroup(@Param("countryGroupId") Integer countryGroupId);
	
	@Query("from Country c where c.id not in (Select distinct k.country.id from KpiResult k where k.kpi.id = :kpiId) and c.id <> 'IL'")
	public List<Country> findCountiresHavingNoData(@Param("kpiId")Integer kpiId);

}

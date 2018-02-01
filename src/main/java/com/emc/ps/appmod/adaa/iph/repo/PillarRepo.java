package com.emc.ps.appmod.adaa.iph.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emc.ps.appmod.adaa.iph.entities.Pillar;

@Repository
public interface PillarRepo extends CrudRepository<Pillar, Integer>{
	
	@Query("from Pillar p order by p.id asc")
	public List<Pillar> findAll();

}

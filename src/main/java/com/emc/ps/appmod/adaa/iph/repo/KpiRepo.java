package com.emc.ps.appmod.adaa.iph.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emc.ps.appmod.adaa.iph.entities.Kpi;

@Repository
public interface KpiRepo extends CrudRepository<Kpi, Integer>{
	
	@Query("from Kpi k where k.subtopic.id=:subtopicId")
	public List<Kpi> findBySubtopicIdOrderByIdAsc(@Param("subtopicId") Integer subtopicId);
	
	@Query("from Kpi k order by k.id")
	public List<Kpi> findAllKpis();
	
	@Query("from Kpi k where k.subtopic.id in (select s.id from Subtopic s where s.pillar.id=:pillarId) order by k.id")
	public List<Kpi> findByPillarId(@Param("pillarId") Integer pillarId);

}

package com.emc.ps.appmod.adaa.iph.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.emc.ps.appmod.adaa.iph.entities.KpiResult;

public interface KpiResultRepo extends CrudRepository<KpiResult, Integer> {

	@Query("from KpiResult k where k.kpi.id=:kpiId and k.year=:year and k.country.id <> 'IL' order by k.country.id")
	public List<KpiResult> findByKpiIdAndYearOrderByIdAsc(@Param("kpiId") Integer kpiId, @Param("year") Integer year);

	@Query("from KpiResult k where k.country.id=:countryId and k.year=:year and k.kpi.id in (select k1.id from Kpi k1 where k1.subtopic.id in (select s.id from Subtopic s where s.pillar.id=:pillarId)) order by k.kpi.id")
	public List<KpiResult> findByPillarIdAndCountryIdAndYearOrderByIdAsc(@Param("pillarId") Integer pillarId,
			@Param("countryId") String countryId, @Param("year") Integer year);

	@Query("from KpiResult k where k.kpi.id=:kpiId and k.country.id <> 'IL' order by k.year asc, k.country.id asc")
	public List<KpiResult> findByKpiId(@Param("kpiId") Integer kpiId);

	@Query("from KpiResult k where k.year=:year and k.kpi.id in (select k1.id from Kpi k1 where k1.subtopic.id in (select s.id from Subtopic s where s.pillar.id=:pillarId)) and k.country.id in :countries order by k.country.id")
	public List<KpiResult> findByPillarIdAndYearAndCountryIds(@Param("pillarId") Integer pillarId,
			@Param("countries") List<String> countries, @Param("year") Integer year);

	@Query("from KpiResult k where k.kpi.id=:kpiId and k.year=:year and k.country.id in :countries order by k.country.id")
	public List<KpiResult> findByKpiIdAndYearAndCountryIds(@Param("kpiId") Integer kpiId, @Param("year") Integer year,
			@Param("countries") List<String> countries);

	@Query("from KpiResult k where k.kpi.id=:kpiId and k.year=:year and k.data is not null and k.country.id in (select ccg.country.id from CountryCountryGroup ccg where ccg.countryGroup.id=:countryGroupId and ccg.country.id <> 'IL') order by k.data desc")
	public List<KpiResult> findByKpiIdAndYearAndCountryGroup(@Param("kpiId") Integer kpiId, @Param("year") Integer year,
			@Param("countryGroupId") Integer countryGroupId);

	@Query("SELECT min(t.year) FROM #{#entityName} t where t.id = :kpiId and t.country.id <> 'IL'")
	String findBeginningYearForKpi(@Param("kpiId") Integer kpiId);

	@Query("SELECT min(t.year) as mi, max(t.year) as mx FROM #{#entityName} t where t.country.id <> 'IL'")
	String findMinAndMaxYearForKpi();

	@Query("SELECT min(k.data) as mi, max(k.data) as mx FROM #{#entityName} k where k.kpi.id=:kpiId and k.year=:year and k.country.id <> 'IL'")
	String findMinAndMaxScoreForKpiAndYear(@Param("kpiId") Integer kpiId, @Param("year") Integer year);

	@Query("SELECT k.data FROM #{#entityName} k where k.kpi.id=:kpiId and k.year=:year and k.country.id <> 'IL' order by k.data asc")
	List<Double> findOnlyScoresForKpiAndYear(@Param("kpiId") Integer kpiId, @Param("year") Integer year);

	@Query(value = "select * from kpi_results  where kpi_id = :kpiId and year = :year  and country_id in (select country_id from country_country_group g where g.country_group_id = :groupId and g.country_id <> 'IL')", nativeQuery = true)
	public List<KpiResult> findByKpiIdAndYearForGroupOrderByIdAsc(@Param("kpiId") Integer kpiId,
			@Param("year") Integer year, @Param("groupId") Integer groupId);

	@Query(value = "select data from kpi_results  where kpi_id = :kpiId and year = :year  and country_id in (select country_id from country_country_group g where g.country_group_id = :groupId and g.country_id <> 'IL') order by data asc", nativeQuery = true)
	public List<Double> findOnlyScoresByKpiIdAndYearForGroupOrderByIdAsc(@Param("kpiId") Integer kpiId,
			@Param("year") Integer year, @Param("groupId") Integer groupId);
}

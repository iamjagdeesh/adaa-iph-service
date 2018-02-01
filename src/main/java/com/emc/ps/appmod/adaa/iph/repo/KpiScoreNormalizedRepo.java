package com.emc.ps.appmod.adaa.iph.repo;

import java.util.List;

import com.emc.ps.appmod.adaa.iph.domain.KpiCountriesCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emc.ps.appmod.adaa.iph.domain.GroupScoreAverage;
import com.emc.ps.appmod.adaa.iph.domain.KpiCountriesCount;
import com.emc.ps.appmod.adaa.iph.entities.KpiScoreNormalized;

@Repository
public interface KpiScoreNormalizedRepo extends CrudRepository<KpiScoreNormalized, Integer> {

	/*
	 * @Query(
	 * "from KpiScoreNormalized k where k.kpi.id=:kpiId and k.year=:year order by k.country.id"
	 * ) public List<KpiScoreNormalized>
	 * findByKpiIdAndYearOrderByIdAsc(@Param("kpiId") Integer
	 * kpiId, @Param("year") Integer year);
	 */

	@Query("from KpiScoreNormalized k where k.country.id=:countryId and k.year=:year and k.countryGroup.id=:groupId and k.kpi.id in (select k1.id from Kpi k1 where k1.subtopic.id in (select s.id from Subtopic s where s.pillar.id=:pillarId)) order by k.kpi.id")
	public List<KpiScoreNormalized> findByPillarIdAndCountryIdAndYearOrderByIdAsc(@Param("pillarId") Integer pillarId,
			@Param("countryId") String countryId, @Param("year") Integer year, @Param("groupId") Integer groupId);

	@Query("from KpiScoreNormalized k where k.kpi.id=:kpiId and k.countryGroup.id=:groupId and k.country.id <> 'IL' order by k.year asc, k.country.id asc")
	public List<KpiScoreNormalized> findByKpiId(@Param("kpiId") Integer kpiId, @Param("groupId") Integer groupId);

	@Query("from KpiScoreNormalized k where k.year=:year and k.countryGroup.id=:groupId and k.kpi.id in (select k1.id from Kpi k1 where k1.subtopic.id in (select s.id from Subtopic s where s.pillar.id=:pillarId)) and k.country.id in :countries order by k.country.id")
	public List<KpiScoreNormalized> findByPillarIdAndYearAndCountryIds(@Param("pillarId") Integer pillarId,
			@Param("countries") List<String> countries, @Param("year") Integer year, @Param("groupId") Integer groupId);

	@Query("from KpiScoreNormalized k where k.year=:year and k.countryGroup.id=:groupId and k.country.id <> 'IL' and k.kpi.id in (select k1.id from Kpi k1 where k1.subtopic.id in (select s.id from Subtopic s where s.pillar.id=:pillarId)) order by k.country.id")
	public List<KpiScoreNormalized> findByPillarIdAndYear(@Param("pillarId") Integer pillarId,
			@Param("year") Integer year, @Param("groupId") Integer groupId);

	/*
	 * @Query(
	 * "from KpiScoreNormalized k where k.kpi.id=:kpiId and k.year=:year and k.country.id in :countries order by k.country.id"
	 * ) public List<KpiScoreNormalized>
	 * findByKpiIdAndYearAndCountryIds(@Param("kpiId") Integer
	 * kpiId, @Param("year") Integer year,
	 * 
	 * @Param("countries") List<String> countries);
	 */

	@Query("from KpiScoreNormalized k where k.kpi.id=:kpiId and k.year=:year and k.countryGroup.id=:countryGroupId and k.country.id <> 'IL' order by k.rank asc")
	public List<KpiScoreNormalized> findByKpiIdAndYearAndCountryGroup(@Param("kpiId") Integer kpiId,
			@Param("year") Integer year, @Param("countryGroupId") Integer countryGroupId);

	@Query(value = "truncate table kpi_score_normalized", nativeQuery = true)
	public void tuncateTable();

	@Query("SELECT new com.emc.ps.appmod.adaa.iph.domain.GroupScoreAverage( AVG(COALESCE(k.data, 0)) as average , k.countryGroup.id as groupId, k.year as year, AVG(COALESCE(k.colorRankNormalized, 0)) as averageColor) FROM KpiScoreNormalized k WHERE k.kpi.id=:kpiId and k.country.id <> 'IL' group by k.countryGroup.id, k.year order by k.year,k.countryGroup.id")
	public List<GroupScoreAverage> findGroupAverageScoreForKpi(@Param("kpiId") Integer kpiId);

	@Query("SELECT new com.emc.ps.appmod.adaa.iph.domain.GroupScoreAverage( AVG(COALESCE(k.data, 0)) as average , k.countryGroup.id as groupId, k.year as year, k.kpi.id as kpiId, AVG(COALESCE(k.colorRankNormalized, 0)) as averageColor) FROM KpiScoreNormalized k WHERE k.country.id <> 'IL' group by k.countryGroup.id, k.year, k.kpi.id")
	public List<GroupScoreAverage> findGroupAverageScore();
	
	@Query("SELECT new com.emc.ps.appmod.adaa.iph.domain.GroupScoreAverage( AVG(COALESCE(k.data, 0)) as average , k.countryGroup.id as groupId, k.year as year, k.kpi.id as kpiId, AVG(COALESCE(k.colorRankNormalized, 0)) as averageColor) FROM KpiScoreNormalized k WHERE k.kpi.id in (select k1.id from Kpi k1 where k1.subtopic.id in (select s.id from Subtopic s where s.pillar.id=:pillarId)) and k.year=:year and k.country.id <> 'IL' group by k.countryGroup.id, k.kpi.id, k.year")
	public List<GroupScoreAverage> findGroupAverageScoreForPillarAndYear(@Param("pillarId") Integer pillarId, @Param("year") Integer year);

	@Query("SELECT new com.emc.ps.appmod.adaa.iph.domain.KpiCountriesCount( k.kpi.id as kpiId, (CAST(max(k.rank) as long)) as countriesCount) FROM KpiScoreNormalized k WHERE k.kpi.id in (select k1.id from Kpi k1 where k1.subtopic.id in (select s.id from Subtopic s where s.pillar.id=:pillarId)) and k.data is not null and k.year=:year and k.countryGroup.id = 1 group by k.kpi.id")
	public List<KpiCountriesCount> findCountryCountForKpiInPillar(@Param("pillarId") Integer pillarId, @Param("year") Integer year);

}

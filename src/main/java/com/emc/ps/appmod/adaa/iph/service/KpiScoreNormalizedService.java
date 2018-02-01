package com.emc.ps.appmod.adaa.iph.service;

import java.util.List;

import com.emc.ps.appmod.adaa.iph.constants.Locale;
import com.emc.ps.appmod.adaa.iph.domain.GroupScoreAverage;
import com.emc.ps.appmod.adaa.iph.domain.KpiCountriesCount;
import com.emc.ps.appmod.adaa.iph.domain.KpiResults;
import com.emc.ps.appmod.adaa.iph.entities.KpiScoreNormalized;

/**
 * This class deals with KpiScoreNormalized data
 * 
 * @author bj3
 *
 */
public interface KpiScoreNormalizedService {

	/**
	 * This method returns the list of kpiScoreNormalized entity for the given
	 * kpiId
	 * 
	 * @param kpiId
	 * @param locale
	 * @return
	 */
	public List<KpiScoreNormalized> getKpiScoreNormalizedListByKpiId(Integer kpiId);

	/**
	 * This method returns the list of kpi(All kpis under the pillar) result
	 * object where in each object will contain the kpi scores and other details
	 * 
	 * @param pillarId
	 * @param countryId
	 * @param year
	 * @param locale
	 * @return
	 */
	public List<KpiResults> getAllKpiResultList(Integer pillarId, String countryId, Integer year, Locale locale);

	/**
	 * This method returns the list of kpiScoreNormalized entity for the kpis
	 * under the given pillarId and for the particular countryIDs and for a
	 * particular year
	 * 
	 * @param pillarId
	 * @param countryIds
	 * @param year
	 * @param locale
	 * @return
	 */
	public List<KpiScoreNormalized> getKpiScoreNormalizedListByPillarIdAndYearAndCountryIds(Integer pillarId,
			List<String> countryIds, Integer year, Locale locale);

	/**
	 * This method returns the list of kpiScoreNormalized entity for the kpis
	 * under the given pillarId and for all countries and groups and for a
	 * particular year
	 * 
	 * @param pillarId
	 * @param year
	 * @return
	 */
	public List<KpiScoreNormalized> getKpiScoreNormalizedListByPillarIdAndYear(Integer pillarId, Integer year);

	/**
	 * This method returns the list of kpiScoreNormalized entity for the given
	 * kpiId and for the countries under the countryGroupId
	 * 
	 * @param kpiId
	 * @param year
	 * @param countryGroupId
	 * @return
	 */
	public List<KpiScoreNormalized> getKpiScoreNormalizedListByKpiIdAndYearAndCountryGroup(Integer kpiId, Integer year,
			Integer countryGroupId);

	/**
	 * This method will get the group average score for all years for the given
	 * KPI
	 * 
	 * @param kpiId
	 * @return
	 */
	public List<GroupScoreAverage> getGroupAverageForAllYearsAndGivenKpi(Integer kpiId);

	/**
	 * This method will get the group average score for given year for the kpis
	 * under given pillar
	 * 
	 * @param pillarId
	 * @param year
	 * @return
	 */
	public List<GroupScoreAverage> getGroupAverageForPillarAndYear(Integer pillarId, Integer year);
	
	/**
	 * This method will get the total number of countries for a given year for the
	 * kpis under given pillar
	 * 
	 * @param pillarId
	 * @param year
	 * @return
	 */
	public List<KpiCountriesCount> getCountryCountForKpiId(Integer pillarId, Integer year);

}

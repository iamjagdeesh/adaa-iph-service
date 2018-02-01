package com.emc.ps.appmod.adaa.iph.service;

import java.util.List;
import java.util.Map;

import com.emc.ps.appmod.adaa.iph.constants.Locale;
import com.emc.ps.appmod.adaa.iph.domain.Countries;
import com.emc.ps.appmod.adaa.iph.domain.CountriesAlphabetsDomain;
import com.emc.ps.appmod.adaa.iph.domain.CountryGroupRankingsDomain;
import com.emc.ps.appmod.adaa.iph.domain.CountryGroups;
import com.emc.ps.appmod.adaa.iph.domain.KpiResultForAllCountriesAllYear;
import com.emc.ps.appmod.adaa.iph.domain.KpiResults;
import com.emc.ps.appmod.adaa.iph.domain.KpisForAllCountriesDomain;
import com.emc.ps.appmod.adaa.iph.domain.KpisForSomeCountriesDomain;
import com.emc.ps.appmod.adaa.iph.domain.KpisGroupsCountriesDomain;
import com.emc.ps.appmod.adaa.iph.domain.Pillars;
import com.emc.ps.appmod.adaa.iph.domain.ScoresObject;

/**
 * 
 * @author bj3
 *
 */
public interface IphService {

	/**
	 * This method returns the list of pillar objects where each object contains
	 * SubtopicList and KpiList under that particular pillar
	 * 
	 * @param locale
	 * @return
	 */
	public List<Pillars> getPillarList(Locale locale);

	/**
	 * This method returns the list of countries
	 * 
	 * @param locale
	 * @return
	 */
	public List<Countries> getCountryList(Locale locale);

	/**
	 * This method returns the list of countries grouped by first character of
	 * the countryName
	 * 
	 * @param locale
	 * @return
	 */
	public List<CountriesAlphabetsDomain> getAlphabeticCountryList(Locale locale);

	/**
	 * This method returns the map of kpiscores of all countries, all years of a
	 * particular kpi in the format used to plot world map
	 * 
	 * @param kpiId
	 * @param locale
	 * @return
	 */
	public Map<String, Object> getKpiResultListForMap(Integer kpiId, Locale locale);

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
	 * This method returns a list of objects where in each object will have
	 * details of the country and the scores associated with it
	 * 
	 * @param pillarId
	 * @param countries
	 * @param year
	 * @param locale
	 * @return
	 */
	public List<KpisForSomeCountriesDomain> getAllKpiForCountries(Integer pillarId, List<String> countries,
			Integer year, Locale locale);

	/**
	 * This method returns the kpis' scores for all countries and groups under
	 * the given pillar and year
	 * 
	 * @param pillarId
	 * @param year
	 * @param locale
	 * @return
	 */
	public KpisGroupsCountriesDomain getAllKpiScoreForPillarAndYear(Integer pillarId, Integer year, Locale locale);

	/**
	 * This method returns the list of countryGroups
	 * 
	 * @param locale
	 * @return
	 */
	public List<CountryGroups> getGroups(Locale locale);

	/**
	 * This method returns list of rank objects where in each object will have
	 * details of the country in the group and the scores
	 * 
	 * @param kpiId
	 * @param year
	 * @param countryGroupId
	 * @param locale
	 * @return
	 */
	public List<CountryGroupRankingsDomain> getRankingsForCountryGroup(Integer kpiId, Integer year,
			Integer countryGroupId, Locale locale);

	/**
	 * This method returns the list of kpi scores of all countries for all the
	 * years for a kpi
	 * 
	 * @param kpiId
	 * @param locale
	 * @return
	 */
	public List<KpiResultForAllCountriesAllYear> getAllKpiResultListForKpi(Integer kpiId, Locale locale);

	/**
	 * This method returns the kpiResultMap from kpiResultList for a given kpiId
	 * 
	 * @param kpiId
	 * @param locale
	 * @return
	 */
	public Map<String, Object> getKpiScoreAll(Integer kpiId, Locale locale);

	public Map<String, Object> mGetKpiScoreAll(Integer kpiId, Locale locale);

}

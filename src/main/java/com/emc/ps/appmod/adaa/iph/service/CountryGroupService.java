package com.emc.ps.appmod.adaa.iph.service;

import java.util.List;
import java.util.Map;

import com.emc.ps.appmod.adaa.iph.constants.Locale;
import com.emc.ps.appmod.adaa.iph.domain.CountryGroups;
import com.emc.ps.appmod.adaa.iph.domain.GroupScoreAverage;

/**
 * This interface deals with country groups
 */
public interface CountryGroupService {

	/**
	 * The method returns the list of country groups.
	 * 
	 * @param locale
	 * @return
	 */
	public List<CountryGroups> getGroups(Locale locale);

	/**
	 * This method will get the country-group map. This will be the master
	 * object for country and groups mapping.
	 * 
	 * @param locale
	 * @return
	 */
	public Map<Integer, CountryGroups> getCountryGroupsMap(Locale locale);

	/**
	 * This method will reconstruct the group average list into the map format
	 * for all the years.
	 * 
	 * @param groupAverageForKpi
	 * @param locale
	 * @return
	 */
	public Map<Integer, List<CountryGroups>> getGroupScoreAsMapByYear(List<GroupScoreAverage> groupAverageForKpi,
			Locale locale);
	
	/**
	 * This method will reconstruct the group average list into the map format
	 * for all the kpis.
	 * 
	 * @param groupAverageForKpi
	 * @param locale
	 * @return
	 */
	public Map<Integer, List<CountryGroups>> getGroupScoreAsMapByKpi(List<GroupScoreAverage> groupAverageForKpi,
			Locale locale);
}

package com.emc.ps.appmod.adaa.iph.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.emc.ps.appmod.adaa.iph.constants.Color;
import com.emc.ps.appmod.adaa.iph.constants.Constants;
import com.emc.ps.appmod.adaa.iph.constants.Locale;
import com.emc.ps.appmod.adaa.iph.domain.Countries;
import com.emc.ps.appmod.adaa.iph.domain.CountriesAlphabetsDomain;
import com.emc.ps.appmod.adaa.iph.domain.CountriesNew;
import com.emc.ps.appmod.adaa.iph.domain.CountryGroupRankingsDomain;
import com.emc.ps.appmod.adaa.iph.domain.CountryGroups;
import com.emc.ps.appmod.adaa.iph.domain.GroupScoreAverage;
import com.emc.ps.appmod.adaa.iph.domain.KpiCountriesCount;
import com.emc.ps.appmod.adaa.iph.domain.KpiDomainForFullStats;
import com.emc.ps.appmod.adaa.iph.domain.KpiResultForAllCountriesAllYear;
import com.emc.ps.appmod.adaa.iph.domain.KpiResults;
import com.emc.ps.appmod.adaa.iph.domain.KpiScores;
import com.emc.ps.appmod.adaa.iph.domain.KpiValue;
import com.emc.ps.appmod.adaa.iph.domain.Kpis;
import com.emc.ps.appmod.adaa.iph.domain.KpisForAllCountriesDomain;
import com.emc.ps.appmod.adaa.iph.domain.KpisForAllGroupsDomain;
import com.emc.ps.appmod.adaa.iph.domain.KpisForSomeCountriesDomain;
import com.emc.ps.appmod.adaa.iph.domain.KpisGroupsCountriesDomain;
import com.emc.ps.appmod.adaa.iph.domain.KpisNew;
import com.emc.ps.appmod.adaa.iph.domain.Pillars;
import com.emc.ps.appmod.adaa.iph.domain.ScoresObject;
import com.emc.ps.appmod.adaa.iph.domain.ScoresObjectNew;
import com.emc.ps.appmod.adaa.iph.entities.Country;
import com.emc.ps.appmod.adaa.iph.entities.KpiResult;
import com.emc.ps.appmod.adaa.iph.entities.KpiScoreNormalized;

/**
 * 
 * @author bj3
 *
 */
@Service
public class IphServiceImpl implements IphService {

	private static final Logger LOGGER = LoggerFactory.getLogger(IphServiceImpl.class);

	@Autowired
	private CountryService countryService;

	@Autowired
	private CountryGroupService countryGroupService;

	@Autowired
	private PillarService pillarService;

	@Autowired
	private KpiService kpiService;

	@Autowired
	private KpiScoreNormalizedService kpiScoreNormalizedService;

	@Autowired
	private KpiResultService kpiResultService;

	@Value("${" + Constants.MAPVALUE + "}")
	private String MAPVALUE;

	@Value("${" + Constants.MAPDATAVALUE + "}")
	private String MAPDATAVALUE;

	@Value("${" + Constants.CONTENT + "}")
	private String CONTENT;

	@Value("${" + Constants.TOOLTIP + "}")
	private String TOOLTIP;

	@Value("${" + Constants.MAPAREAS + "}")
	private String MAPAREAS;

	@Value("${" + Constants.MAPGROUPS + "}")
	private String MAPGROUPS;

	@Value("${" + Constants.SPANCLASS + "}")
	private String SPANCLASS;

	@Value("${" + Constants.SPANCLASSEND + "}")
	private String SPANCLASSEND;

	@Value("${" + Constants.SPANSCOREDATA + "}")
	private String SPANSCOREDATA;

	@Value("${" + Constants.SPANCLOSE + "}")
	private String SPANCLOSE;

	/**
	 * This method returns the list of pillar objects where each object contains
	 * SubtopicList and KpiList under that particular pillar
	 * 
	 * @param locale
	 * @return
	 */
	@Override
	public List<Pillars> getPillarList(Locale locale) {
		return pillarService.getPillarList(locale);
	}

	/**
	 * This method returns the list of countries
	 * 
	 * @param locale
	 * @return
	 */
	@Override
	public List<Countries> getCountryList(Locale locale) {
		return countryService.getCountryList(locale);
	}

	/**
	 * This method returns the list of countries grouped by first character of
	 * the countryName
	 * 
	 * @param locale
	 * @return
	 */
	@Override
	public List<CountriesAlphabetsDomain> getAlphabeticCountryList(Locale locale) {
		return countryService.getAlphabeticCountryList(locale);
	}

	/**
	 * This method returns the map of kpiscores of all countries, all years of a
	 * particular kpi in the format used to plot world map
	 * 
	 * @param kpiId
	 * @param locale
	 * @return
	 */
	@Override
	public Map<String, Object> getKpiResultListForMap(Integer kpiId, Locale locale) {
		LOGGER.debug("Getting data for world map!");
		List<KpiScoreNormalized> kpiScoreNormalizedEntityList = kpiScoreNormalizedService
				.getKpiScoreNormalizedListByKpiId(kpiId);
		Map<String, Object> map = createMapForCountriesKpi(kpiId, kpiScoreNormalizedEntityList, locale);
		return map;
	}

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
	@Override
	public List<KpiResults> getAllKpiResultList(Integer pillarId, String countryId, Integer year, Locale locale) {
		List<KpiResults> kpiResultList = kpiScoreNormalizedService.getAllKpiResultList(pillarId, countryId, year,
				locale);
		return kpiResultList;
	}

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
	@Override
	public List<KpisForSomeCountriesDomain> getAllKpiForCountries(Integer pillarId, List<String> countries,
			Integer year, Locale locale) {
		LOGGER.debug("Getting all the kpis' score under a pillar for a list of countries for an year!");

		List<KpisForSomeCountriesDomain> kpisForSomeCountriesDomainList = new ArrayList<KpisForSomeCountriesDomain>();
		List<KpiScoreNormalized> kpiScoreNormalizedEntityList = kpiScoreNormalizedService
				.getKpiScoreNormalizedListByPillarIdAndYearAndCountryIds(pillarId, countries, year, locale);
		
		//Getting total number of countries with data available for a given kpi in a map
		List<KpiCountriesCount> countryCountList = kpiScoreNormalizedService.getCountryCountForKpiId(pillarId, year);
		Map<Integer, Integer> countryCountMap = new HashMap<Integer, Integer>();
		for(KpiCountriesCount countryCountListItem : countryCountList)
		{
			countryCountMap.put(countryCountListItem.getKpiId(), countryCountListItem.getCountriesCount());
		}


		Map<String, Map<Integer, KpiScores>> countryMap = new HashMap<String, Map<Integer, KpiScores>>();
		KpiScores kpiScores = null;
		for (KpiScoreNormalized kpiScoreNormalizedEntity : kpiScoreNormalizedEntityList) {
			kpiScores = new KpiScores();
			Integer kpiId = kpiScoreNormalizedEntity.getKpi().getId();

			/*
			 * Constructing the kpiScores object and construct
			 * kpiMap(kpiId:KpiScore) and put it in countryMap(countryId:kpiMap)
			 * for the data which will be later used to construct data in
			 * required format
			 */
			kpiScores = convertToKpiScores(kpiScoreNormalizedEntity, locale);
			
			//Setting the total country count to the kpiScore object
			kpiScores.setCountryCount(countryCountMap.get(kpiId));

			
			
			String countryId = kpiScoreNormalizedEntity.getCountry().getId();
			Map<Integer, KpiScores> temp = countryMap.get(countryId);
			if (null == temp) {
				Map<Integer, KpiScores> tempObj = new HashMap<Integer, KpiScores>();
				tempObj.put(kpiId, kpiScores);
				countryMap.put(countryId, tempObj);
			} else {
				temp.put(kpiId, kpiScores);
			}
		}

		/* Construct the data in required format. */
		for (String countryId : countries) {
			if (countryMap.containsKey(countryId)) {
				Map<Integer, KpiScores> kpiScoreMap = countryMap.get(countryId);
				List<KpiScores> kpiScoresList = new ArrayList<KpiScores>(kpiScoreMap.values());
				Countries countryDomain = countryService.getCountry(countryId, locale);
				KpisForSomeCountriesDomain kpisForSomeCountriesDomain = null;
				kpisForSomeCountriesDomain = populateKpisForSomeCountriesDomainObject(countryDomain, kpiScoresList);
				kpisForSomeCountriesDomainList.add(kpisForSomeCountriesDomain);
			}
		}

		return kpisForSomeCountriesDomainList;
	}
	
	/**
	 * This method returns the kpis' scores for all countries and groups under
	 * the given pillar and year
	 * 
	 * @param pillarId
	 * @param year
	 * @param locale
	 * @return
	 */
	@Override
	public KpisGroupsCountriesDomain getAllKpiScoreForPillarAndYear(Integer pillarId, Integer year, Locale locale) {
		LOGGER.debug("Getting all the kpis' score under a pillar for all countries and groups for an year!");
		
		List<KpiScoreNormalized> kpiScoreNormalizedEntityList = kpiScoreNormalizedService.getKpiScoreNormalizedListByPillarIdAndYear(pillarId, year);
		//List<KpisForAllCountriesDomain> kpisForAllCountriesDomainList = new ArrayList<KpisForAllCountriesDomain>();
		Map<String, Map<Integer, KpiValue>> countryMap = new HashMap<String, Map<Integer, KpiValue>>();
		KpiValue kpiValue = null;
		for (KpiScoreNormalized kpiScoreNormalizedEntity : kpiScoreNormalizedEntityList) {
			
			Integer kpiId = kpiScoreNormalizedEntity.getKpi().getId();
			kpiValue = convertToKpiValue(kpiScoreNormalizedEntity);
			String countryId = kpiScoreNormalizedEntity.getCountry().getId();
			Map<Integer, KpiValue> temp = countryMap.get(countryId);
			if (null == temp) {
				Map<Integer, KpiValue> tempObj = new HashMap<Integer, KpiValue>();
				tempObj.put(kpiId, kpiValue);
				countryMap.put(countryId, tempObj);
			} else {
				temp.put(kpiId, kpiValue);
			}	
		}
		
		/* Construct the data in required format. */
		Map<String, KpisForAllCountriesDomain> kpiCountryMap = new HashMap<String, KpisForAllCountriesDomain>();
		Object [] countries = countryMap.keySet().toArray();
		for (Object countryId : countries) {
			Map<Integer, KpiValue> kpiValueMap = countryMap.get(countryId);
			List<KpiValue> kpiValuesList = new ArrayList<KpiValue>(kpiValueMap.values());
			Countries countryDomain = countryService.getCountry((String) countryId, locale);
			KpisForAllCountriesDomain kpisForAllCountriesDomain = null;
			kpisForAllCountriesDomain = populateKpisForAllCountriesDomainObject(countryDomain, kpiValuesList);
			kpiCountryMap.put((String) countryId, kpisForAllCountriesDomain);
			//kpisForAllCountriesDomainList.add(kpisForAllCountriesDomain);
		}
		
		List<KpiDomainForFullStats> kpisDomainList = kpiService.getKpisFromPillarId(pillarId, locale);
		Map<Integer, KpiDomainForFullStats> kpiMap = new HashMap<Integer, KpiDomainForFullStats>();
		for (KpiDomainForFullStats kpiDomainForFullStats : kpisDomainList) {
			kpiMap.put(kpiDomainForFullStats.getKpiId(), kpiDomainForFullStats);
		}
		
		// Get the group average for the given year across all kpis under the pillar
		List<GroupScoreAverage> groupAverageForKpi = kpiScoreNormalizedService
				.getGroupAverageForPillarAndYear(pillarId, year);
		Map<Integer, Map<Integer, KpiValue>> groupMap = new HashMap<Integer, Map<Integer, KpiValue>>();
		
		for (GroupScoreAverage groupScoreAverage : groupAverageForKpi) {
			Integer kpiId = groupScoreAverage.getKpiId();
			kpiValue = convertToGroupKpiValue(groupScoreAverage);
			Integer groupId = groupScoreAverage.getGroupId();
			Map<Integer, KpiValue> temp = groupMap.get(groupId);
			if (null == temp) {
				Map<Integer, KpiValue> tempObj = new HashMap<Integer, KpiValue>();
				tempObj.put(kpiId, kpiValue);
				groupMap.put(groupId, tempObj);
			} else {
				temp.put(kpiId, kpiValue);
			}
		}
		
		/* Construct the data in required format. */
		Map<Integer, KpisForAllGroupsDomain> kpiGroupsmap = new HashMap<Integer, KpisForAllGroupsDomain>();
		List<KpisForAllGroupsDomain> kpisForAllGroupsDomainList = new ArrayList<KpisForAllGroupsDomain>();
		Map<Integer, CountryGroups> countryGroupsMap = countryGroupService.getCountryGroupsMap(locale);
		Object [] groups = groupMap.keySet().toArray();
		for (Object groupId : groups) {
			Map<Integer, KpiValue> kpiValueMap = groupMap.get(groupId);
			List<KpiValue> kpiValuesList = new ArrayList<KpiValue>(kpiValueMap.values());
			CountryGroups countryGroupsDomain = countryGroupsMap.get(groupId);
			KpisForAllGroupsDomain kpisForAllGroupsDomain = null;
			kpisForAllGroupsDomain = populateKpisForAllGroupsDomainObject(countryGroupsDomain, kpiValuesList);
			kpiGroupsmap.put((Integer) groupId, kpisForAllGroupsDomain);
			kpisForAllGroupsDomainList.add(kpisForAllGroupsDomain);
		}
		
		KpisGroupsCountriesDomain kpisGroupsCountriesDomain = new KpisGroupsCountriesDomain();
		kpisGroupsCountriesDomain.setKpiGroupsmap(kpiGroupsmap);
		kpisGroupsCountriesDomain.setKpiMap(kpiMap);
		kpisGroupsCountriesDomain.setKpiCountryMap(kpiCountryMap);
		
		return kpisGroupsCountriesDomain;
	}

	/**
	 * This method returns the list of countryGroups
	 * 
	 * @param locale
	 * @return
	 */
	@Override
	public List<CountryGroups> getGroups(Locale locale) {
		return countryGroupService.getGroups(locale);
	}

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
	@Override
	public List<CountryGroupRankingsDomain> getRankingsForCountryGroup(Integer kpiId, Integer year,
			Integer countryGroupId, Locale locale) {
		LOGGER.debug("Getting the list of countries and their score in the group, sorted based on the rank!");
		List<CountryGroupRankingsDomain> countryGroupRankingsDomainList = new ArrayList<CountryGroupRankingsDomain>();
		List<KpiScoreNormalized> kpiScoreNormalizedEntityList = kpiScoreNormalizedService
				.getKpiScoreNormalizedListByKpiIdAndYearAndCountryGroup(kpiId, year, countryGroupId);
		for (KpiScoreNormalized kpiScoreNormalizedEntity : kpiScoreNormalizedEntityList) {
			// if rank is null, then data is null, so not sending that record to
			// response
			if (kpiScoreNormalizedEntity.getRank() == null) {
				continue;
			}
			KpiScores kpiScores = convertToKpiScores(kpiScoreNormalizedEntity, locale);
			Countries countries = countryService.convertToDomain(kpiScoreNormalizedEntity.getCountry(), locale);
			CountryGroupRankingsDomain countryGroupRankingsDomain = new CountryGroupRankingsDomain();
			countryGroupRankingsDomain = constructCountryGroupRankingsDomain(kpiScores, countries);
			countryGroupRankingsDomain.setYear(year);
			countryGroupRankingsDomainList.add(countryGroupRankingsDomain);
		}
		return countryGroupRankingsDomainList;
	}

	/**
	 * This method returns the list of kpi scores of all countries for all the
	 * years for a kpi
	 * 
	 * @param kpiId
	 * @param locale
	 * @return
	 */
	@Override
	public List<KpiResultForAllCountriesAllYear> getAllKpiResultListForKpi(Integer kpiId, Locale locale) {
		LOGGER.debug("Getting the kpi scores of all countries for all the years for a kpi!");
		List<KpiScoreNormalized> kpiScoreNormalizedEntityList = kpiScoreNormalizedService
				.getKpiScoreNormalizedListByKpiId(kpiId);
		List<KpiResultForAllCountriesAllYear> allCountriesAllYears = new ArrayList<KpiResultForAllCountriesAllYear>();
		Countries country = null;

		Map<Integer, Map<String, CountryGroupRankingsDomain>> yearMap = new HashMap<Integer, Map<String, CountryGroupRankingsDomain>>();

		CountryGroupRankingsDomain countryGroupRankingsDomain = null;

		for (KpiScoreNormalized kpiScoreNormalizedEntity : kpiScoreNormalizedEntityList) {

			Integer year = kpiScoreNormalizedEntity.getYear();
			KpiScores kpiScores = convertToKpiScores(kpiScoreNormalizedEntity, locale);
			country = countryService.convertToDomain(kpiScoreNormalizedEntity.getCountry(), locale);
			countryGroupRankingsDomain = constructCountryGroupRankingsDomain(kpiScores, country);
			countryGroupRankingsDomain.setYear(year);

			String countryId = kpiScoreNormalizedEntity.getCountry().getId();

			Map<String, CountryGroupRankingsDomain> temp = yearMap.get(year);
			if (null == temp) {
				Map<String, CountryGroupRankingsDomain> tempObj = new HashMap<String, CountryGroupRankingsDomain>();
				tempObj.put(countryId, countryGroupRankingsDomain);
				yearMap.put(year, tempObj);
			} else {
				temp.put(countryId, countryGroupRankingsDomain);
			}

		}

		// Construct the data in required format.
		Set<Integer> yearMapKeySet = yearMap.keySet();
		List<Integer> yearMapKeyLst = new ArrayList<Integer>(yearMapKeySet);
		Collections.sort(yearMapKeyLst);
		for (Integer year : yearMapKeyLst) {
			Map<String, CountryGroupRankingsDomain> countryGroupRankingsDomainMap = yearMap.get(year);
			List<CountryGroupRankingsDomain> countryInfo = new ArrayList<CountryGroupRankingsDomain>(
					countryGroupRankingsDomainMap.values());
			Collections.sort(countryInfo);
			KpiResultForAllCountriesAllYear kpiResultForAllCountriesAllYear = new KpiResultForAllCountriesAllYear();
			kpiResultForAllCountriesAllYear.setYear(year);
			kpiResultForAllCountriesAllYear.setCountryInfo(countryInfo);
			allCountriesAllYears.add(kpiResultForAllCountriesAllYear);
		}
		return allCountriesAllYears;
	}

	/**
	 * This method returns the kpiResultMap from kpiResultList for a given kpiId
	 * 
	 * @param kpiId
	 * @param locale
	 * @return
	 */
	@Override
	public Map<String, Object> getKpiScoreAll(Integer kpiId, Locale locale) {

		List<KpiResult> kpiResultEntityList = kpiResultService.getKpiResultListByKpiId(kpiId, locale);
		CountriesNew countryDomain = null;
		Map<Integer, Map<String, Object>> yearMap = new HashMap<Integer, Map<String, Object>>();
		Map<Integer, Object> resultMap = new HashMap<Integer, Object>();

		for (KpiResult kpiResultEntity : kpiResultEntityList) {
			Integer year = kpiResultEntity.getYear();
			countryDomain = countryService.convertToNewDomain(kpiResultEntity.getCountry(), locale);
			String score = null;
			if(kpiResultEntity.getScore() != null){
				score = kpiResultEntity.getScore().toString();
			}
			ScoresObjectNew scoresObject = constructScoresObjectNew(countryDomain, score,
					kpiResultEntity.getData(), kpiResultEntity.getWeightedScore(), null);
			Map<String, Object> temp = yearMap.get(year);
			if (null == temp) {
				Map<String, Object> tempObj = new HashMap<String, Object>();
				tempObj.put(countryDomain.getCountryId(), scoresObject);
				yearMap.put(year, tempObj);
			} else {
				temp.put(countryDomain.getCountryId(), scoresObject);
			}
		}
		
		// Construct the data in required format.
		Set<Integer> yearMapKeyLst = yearMap.keySet();
		for (Integer year : yearMapKeyLst) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put(MAPAREAS, yearMap.get(year));
			dataMap.put(MAPGROUPS, countryGroupService.getCountryGroupsMap(locale));
			resultMap.put(year, dataMap);
		}
		
		KpisNew kpisNewDomain = kpiService.getKpisNew(kpiId, locale);
		Map<String, Object> outerMap = new HashMap<String, Object>();
		outerMap.put("kpi", kpisNewDomain);
		outerMap.put("data", resultMap);
		
		return outerMap;
	}
	
	@Override
	public Map<String, Object> mGetKpiScoreAll(Integer kpiId, Locale locale) {
		List<KpiScoreNormalized> kpiScoreNormalizedList = kpiScoreNormalizedService.getKpiScoreNormalizedListByKpiId(kpiId);
		CountriesNew countryDomain = null;
		Map<Integer, Map<String, Object>> yearMap = new HashMap<Integer, Map<String, Object>>();
		Map<Integer, Object> resultMap = new HashMap<Integer, Object>();
		
		// Get the group average for the given KPI across all years
		List<GroupScoreAverage> groupAverageForKpi = kpiScoreNormalizedService
				.getGroupAverageForAllYearsAndGivenKpi(kpiId);

		// Convert the group average into usable map format
		Map<Integer, List<CountryGroups>> groupScoreAverageMapByYear = countryGroupService
				.getGroupScoreAsMapByYear(groupAverageForKpi, locale);
		
		for (KpiScoreNormalized kpiScoreNormalizedEntity : kpiScoreNormalizedList) {
			Integer year = kpiScoreNormalizedEntity.getYear();
			countryDomain = countryService.convertToNewDomain(kpiScoreNormalizedEntity.getCountry(), locale);
			ScoresObjectNew scoresObject = constructScoresObjectNew(countryDomain, kpiScoreNormalizedEntity.getDataFormatted(),
					kpiScoreNormalizedEntity.getData(), kpiScoreNormalizedEntity.getColorRankNormalized(), kpiScoreNormalizedEntity.getRank());
			Map<String, Object> temp = yearMap.get(year);
			if (null == temp) {
				Map<String, Object> tempObj = new HashMap<String, Object>();
				tempObj.put(countryDomain.getCountryId(), scoresObject);
				yearMap.put(year, tempObj);
			} else {
				temp.put(countryDomain.getCountryId(), scoresObject);
			}
		}
		
		// Construct the data in required format.
		Set<Integer> yearMapKeyLst = yearMap.keySet();
		for (Integer year : yearMapKeyLst) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put(MAPAREAS, yearMap.get(year));
			dataMap.put(MAPGROUPS, groupScoreAverageMapByYear.get(year));
			resultMap.put(year, dataMap);
		}
		
		KpisNew kpisNewDomain = kpiService.getKpisNew(kpiId, locale);
		Map<String, Object> outerMap = new HashMap<String, Object>();
		outerMap.put("kpi", kpisNewDomain);
		outerMap.put("data", resultMap);
		
		return outerMap;
	}

	/**
	 * This method will create the countries map across all years. The response
	 * will be structured to support maps in UI.
	 * 
	 * @param kpiId
	 * @param kpiScoreNormalizedEntityList
	 * @param locale
	 * @return
	 */
	private Map<String, Object> createMapForCountriesKpi(Integer kpiId,
			List<KpiScoreNormalized> kpiScoreNormalizedEntityList, Locale locale) {

		// Get the group average for the given KPI across all years
		List<GroupScoreAverage> groupAverageForKpi = kpiScoreNormalizedService
				.getGroupAverageForAllYearsAndGivenKpi(kpiId);

		// Convert the group average into usable map format
		Map<Integer, List<CountryGroups>> groupScoreAverageMapByYear = countryGroupService
				.getGroupScoreAsMapByYear(groupAverageForKpi, locale);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<Integer, Map<String, Object>> yearMap = new HashMap<Integer, Map<String, Object>>();

		// This map will hold each country score details.
		Map<String, Object> countryMap = null;

		// Get the KPI domain object
		Kpis kpisDomain = kpiService.getKpis(kpiId, locale);

		// First restructure the list into map. Key will be the year and value
		// will be the countries data for that year
		for (KpiScoreNormalized kpiScoreNormalized : kpiScoreNormalizedEntityList) {

			countryMap = new HashMap<String, Object>();
			String countryCode = kpiScoreNormalized.getCountry().getId();
			String scoreFormatted = kpiScoreNormalized.getDataFormatted();
			Double dataValue = kpiScoreNormalized.getData();
			Map<String, Object> tooltip = new HashMap<String, Object>();
			String colorCode = null;

			// Get the color coding score.
			Double colorCodingScore = kpiScoreNormalized.getColorRankNormalized();
			if (null == colorCodingScore) {
				colorCode = "-1";
			} else {
				colorCode = colorCodingScore.toString();
			}

			// Put the color coding info into the country map
			countryMap.put(MAPVALUE, colorCode);

			// Populate the tooltip and score as required by UI.
			String toolTipValue = null;
			if (kpiScoreNormalized.getData() != null) {
				// Setting tool tip
				toolTipValue = getToolTipValue(kpiScoreNormalized.getCountry(), kpisDomain, scoreFormatted,
						colorCodingScore, locale);

				// Set the score
				countryMap.put(Constants.score, scoreFormatted);
				countryMap.put(MAPDATAVALUE, AuxiliaryClass.formatToTwoDecimalPlaces(dataValue));
			} else {
				// Setting tool tip
				toolTipValue = getToolTipValue(kpiScoreNormalized.getCountry(), kpisDomain, scoreFormatted,
						colorCodingScore, locale);

				// Set the score
				countryMap.put(Constants.score, 0.0);
				countryMap.put(MAPDATAVALUE, 0.0);
			}
			tooltip.put(CONTENT, toolTipValue);
			countryMap.put(TOOLTIP, tooltip);

			// Add the country map into another map with year as the key. This
			// will be used later for constructing the
			// data in required format.
			Integer year = kpiScoreNormalized.getYear();
			Map<String, Object> temp = yearMap.get(year);
			if (null == temp) {
				Map<String, Object> tempObj = new HashMap<String, Object>();
				tempObj.put(countryCode, countryMap);
				yearMap.put(year, tempObj);
			} else {
				temp.put(countryCode, countryMap);
			}
		}

		// Construct the data in required format.
		Set<Integer> yearMapKeyLst = yearMap.keySet();
		for (Integer year : yearMapKeyLst) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put(MAPAREAS, yearMap.get(year));
			dataMap.put(MAPGROUPS, groupScoreAverageMapByYear.get(year));
			resultMap.put(year.toString(), dataMap);
		}

		return resultMap;
	}

	/**
	 * This method will create the tool tip value as required by UI.
	 * 
	 * @param country
	 * @param kpisDomain
	 * @param scoreFormatted
	 * @return
	 */
	private String getToolTipValue(Country country, Kpis kpisDomain, String scoreFormatted, Double colourCodingScore,
			Locale locale) {

		StringBuffer sb = new StringBuffer();
		String noData = null;
		sb.append(Constants.DIVSTARTFLAG);
		sb.append(country.getId());
		sb.append(Constants.DIVENDFLAG);
		sb.append(SPANCLASS);
		switch (locale) {
		case EN:
			sb.append(country.getNameEn());
			noData = Constants.NODATA;
			break;

		case AR:
			sb.append(country.getNameAr());
			noData = Constants.NODATAARABIC;
			break;
		}
		sb.append(SPANCLASSEND);
		if (null != scoreFormatted && !scoreFormatted.equals("null")) {
			String color = getColorForScore(colourCodingScore);
			sb.append(SPANSCOREDATA).append(color).append(Constants.SINGLEQUOTE).append(Constants.ANGULARBRACKETCLOSE)
					.append(scoreFormatted).append(Constants.SPACE).append(Constants.SLASH).append(Constants.SPACE)
					.append(kpisDomain.getDenominator()).append(SPANCLOSE);
		} else {
			sb.append(SPANSCOREDATA).append(Color.colorMinus.getColor()).append(Constants.SINGLEQUOTE)
					.append(Constants.ANGULARBRACKETCLOSE).append(noData).append(SPANCLOSE);
		}
		return sb.toString();
	}

	/**
	 * This method will return the html color code corresponding to the
	 * weightedScore(ColorRankNormalized)
	 * 
	 * @param colourCodingScore
	 * @return
	 */
	private String getColorForScore(Double colourCodingScore) {
		
		if(colourCodingScore == null){
			colourCodingScore = 0.0;
		}

		// Multiplying by 2 the score(0-5) as it has to be mapped to 10 colors
		Integer score = (int) (colourCodingScore * 2);

		String color;
		switch (score) {
		case 0:
			color = Color.color0.getColor();
			break;
		case 1:
			color = Color.color1.getColor();
			break;
		case 2:
			color = Color.color2.getColor();
			break;
		case 3:
			color = Color.color3.getColor();
			break;
		case 4:
			color = Color.color4.getColor();
			break;
		case 5:
			color = Color.color5.getColor();
			break;
		case 6:
			color = Color.color6.getColor();
			break;
		case 7:
			color = Color.color7.getColor();
			break;
		case 8:
			color = Color.color8.getColor();
			break;
		case 9:
			color = Color.color9.getColor();
			break;
		default:
			/**
			 * If the weightedScore is above 5, then also same color as for
			 * 0.45-0.50
			 */
			color = Color.color9.getColor();
			break;
		}
		return color;
	}

	/**
	 * This method creates a KpiScores object from the list of
	 * kpiScoreNormalizedEntity
	 * 
	 * @param kpiScoreNormalizedEntity
	 * @param locale
	 * @return
	 */
	private KpiScores convertToKpiScores(KpiScoreNormalized kpiScoreNormalizedEntity, Locale locale) {
		KpiScores kpiScores = null;
		switch (locale) {
		case EN:
			kpiScores = populateKpiScores(kpiScoreNormalizedEntity.getKpi().getId(),
					kpiScoreNormalizedEntity.getKpi().getNameEn(),
					kpiScoreNormalizedEntity.getKpi().getDescriptionLongEn(),
					kpiScoreNormalizedEntity.getKpi().getDenominatorEn(), kpiScoreNormalizedEntity.getDataFormatted(),
					AuxiliaryClass.formatToTwoDecimalPlaces(kpiScoreNormalizedEntity.getData()),
					kpiScoreNormalizedEntity.getRank(), kpiScoreNormalizedEntity.getColorRankNormalized(),
					kpiScoreNormalizedEntity.getKpi().getSource().getNameEn(),
					kpiScoreNormalizedEntity.getKpi().getDataUpdateFrequency());
			break;
		case AR:
			kpiScores = populateKpiScores(kpiScoreNormalizedEntity.getKpi().getId(),
					kpiScoreNormalizedEntity.getKpi().getNameAr(),
					kpiScoreNormalizedEntity.getKpi().getDescriptionLongAr(),
					kpiScoreNormalizedEntity.getKpi().getDenominatorAr(), kpiScoreNormalizedEntity.getDataFormatted(),
					AuxiliaryClass.formatToTwoDecimalPlaces(kpiScoreNormalizedEntity.getData()),
					kpiScoreNormalizedEntity.getRank(), kpiScoreNormalizedEntity.getColorRankNormalized(),
					kpiScoreNormalizedEntity.getKpi().getSource().getNameAr(),
					kpiScoreNormalizedEntity.getKpi().getDataUpdateFrequencyAr());

			break;
		}
		return kpiScores;
	}

	/**
	 * This method returns the KpiScores object populated with the given data.
	 * 
	 * @param id
	 * @param name
	 * @param descriptionLong
	 * @param denominator
	 * @param dataFormatted
	 * @param dataValue
	 * @param rank
	 * @param colorRankNormalized
	 * @param sourceName
	 * @param dataUpdateFrequency
	 * @return
	 */
	private KpiScores populateKpiScores(Integer id, String name, String descriptionLong, String denominator,
			String dataFormatted, Double dataValue, Integer rank, Double colorRankNormalized, String sourceName,
			String dataUpdateFrequency) {

		KpiScores kpiScores = new KpiScores();
		kpiScores.setKpiId(id);
		kpiScores.setKpiName(name);
		kpiScores.setDescriptionLong(descriptionLong);
		kpiScores.setDenominator(denominator);
		kpiScores.setData(dataFormatted);
		kpiScores.setDataValue(dataValue);
		kpiScores.setScore(rank);
		kpiScores.setWeightedScore(colorRankNormalized);
		kpiScores.setSourceName(sourceName);
		kpiScores.setFrequency(dataUpdateFrequency);
		return kpiScores;

	}

	/**
	 * This returns the CountryGroupRankingsDomain from the KpiScores and
	 * countries domain objects
	 * 
	 * @param kpiScores
	 * @param countries
	 * @return
	 */
	private CountryGroupRankingsDomain constructCountryGroupRankingsDomain(KpiScores kpiScores, Countries countries) {
		CountryGroupRankingsDomain countryGroupRankingsDomain = new CountryGroupRankingsDomain();
		countryGroupRankingsDomain.setCountryId(countries.getCountryId());
		countryGroupRankingsDomain.setCountryName(countries.getCountryName());
		countryGroupRankingsDomain.setCountryShortName(countries.getShortName());
		countryGroupRankingsDomain.setData(kpiScores.getData());
		countryGroupRankingsDomain.setDataValue(kpiScores.getDataValue());
		countryGroupRankingsDomain.setScore(kpiScores.getScore());
		countryGroupRankingsDomain.setWeightedScore(kpiScores.getWeightedScore());
		countryGroupRankingsDomain.setKpiId(kpiScores.getKpiId());
		countryGroupRankingsDomain.setKpiName(kpiScores.getKpiName());
		countryGroupRankingsDomain.setDescriptionLong(kpiScores.getDescriptionLong());
		countryGroupRankingsDomain.setDenominator(kpiScores.getDenominator());
		countryGroupRankingsDomain.setSourceName(kpiScores.getSourceName());
		countryGroupRankingsDomain.setFrequency(kpiScores.getFrequency());
		return countryGroupRankingsDomain;
	}

	/**
	 * This method returns the KpisForSomeCountriesDomain populated with the
	 * data given
	 * 
	 * @param countryDomain
	 * @param kpiScoresList
	 * @return
	 */
	private KpisForSomeCountriesDomain populateKpisForSomeCountriesDomainObject(Countries countryDomain,
			List<KpiScores> kpiScoresList) {
		KpisForSomeCountriesDomain kpisForSomeCountriesDomain = new KpisForSomeCountriesDomain();
		kpisForSomeCountriesDomain.setCountryId(countryDomain.getCountryId());
		kpisForSomeCountriesDomain.setCountryName(countryDomain.getCountryName());
		kpisForSomeCountriesDomain.setCountryShortName(countryDomain.getShortName());
		kpisForSomeCountriesDomain.setKpiScores(kpiScoresList);
		return kpisForSomeCountriesDomain;
	}

	/**
	 * This method constructs the scoresObject from the given parameters
	 * 
	 * @param countryDomain
	 * @param score
	 * @param data
	 * @param weightedScore
	 * @return
	 */
	private ScoresObject constructScoresObject(Countries countryDomain, Double score, Double data,
			Double weightedScore) {

		ScoresObject scoresObject = new ScoresObject();
		scoresObject.setCountry(countryDomain);
		scoresObject.setData(data);
		scoresObject.setScore(score);
		scoresObject.setWeightedScore(weightedScore);
		scoresObject.setColorCode(getColorForScore(weightedScore));
		return scoresObject;
	}
	
	/**
	 * This method constructs the scoresObject from the given parameters
	 * 
	 * @param countryDomain
	 * @param score
	 * @param data
	 * @param weightedScore
	 * @return
	 */
	private ScoresObjectNew constructScoresObjectNew(CountriesNew countryDomain, String score, Double data,
			Double weightedScore, Integer rank) {

		ScoresObjectNew scoresObject = new ScoresObjectNew();
		scoresObject.setCountry(countryDomain);
		scoresObject.setData(data);
		scoresObject.setScore(score);
		scoresObject.setWeightedScore(weightedScore);
		scoresObject.setRank(rank);
		return scoresObject;
	}
	
	/**
	 * This method converts kpiScoreNormalized entity into kpiValue domain object
	 * 
	 * @param kpiScoreNormalizedEntity
	 * @return
	 */
	private KpiValue convertToKpiValue(KpiScoreNormalized kpiScoreNormalizedEntity) {
		
		KpiValue kpiValue = new KpiValue();
		kpiValue.setKpiId(kpiScoreNormalizedEntity.getKpi().getId());
		kpiValue.setData(kpiScoreNormalizedEntity.getDataFormatted());
		kpiValue.setDataValue(AuxiliaryClass.formatToTwoDecimalPlaces(kpiScoreNormalizedEntity.getData()));
		kpiValue.setScore(kpiScoreNormalizedEntity.getRank());
		kpiValue.setWeightedScore(kpiScoreNormalizedEntity.getColorRankNormalized());
		return kpiValue;
	}
	
	/**
	 * This method returns the KpisForAllCountriesDomain populated with the
	 * data given 
	 * 
	 * @param countryDomain
	 * @param kpiValuesList
	 * @return
	 */
	private KpisForAllCountriesDomain populateKpisForAllCountriesDomainObject(Countries countryDomain,
			List<KpiValue> kpiValuesList) {
		
		KpisForAllCountriesDomain kpisForAllCountriesDomain = new KpisForAllCountriesDomain();
		kpisForAllCountriesDomain.setCountryId(countryDomain.getCountryId());
		kpisForAllCountriesDomain.setCountryName(countryDomain.getCountryName());
		kpisForAllCountriesDomain.setCountryShortName(countryDomain.getShortName());
		kpisForAllCountriesDomain.setKpiValues(kpiValuesList);
		return kpisForAllCountriesDomain;
		
	}
	
	/**
	 * 
	 * @param groupScoreAverage
	 * @return
	 */
	private KpiValue convertToGroupKpiValue(GroupScoreAverage groupScoreAverage) {
		
		KpiValue kpiValue = new KpiValue();
		kpiValue.setKpiId(groupScoreAverage.getKpiId());
		kpiValue.setDataValue(AuxiliaryClass.formatToTwoDecimalPlaces(groupScoreAverage.getAverage()));
		kpiValue.setData(groupScoreAverage.getFormattedAverage());
		kpiValue.setWeightedScore(AuxiliaryClass.formatToTwoDecimalPlaces(groupScoreAverage.getAverageColor()));
		kpiValue.setScore(null);
		return kpiValue;
	}
	
	/**
	 * 
	 * @param countryGroupsDomain
	 * @param kpiValuesList
	 * @return
	 */
	private KpisForAllGroupsDomain populateKpisForAllGroupsDomainObject(CountryGroups countryGroupsDomain,
			List<KpiValue> kpiValuesList) {
		KpisForAllGroupsDomain kpisForAllGroupsDomain = new KpisForAllGroupsDomain();
		kpisForAllGroupsDomain.setGroupId(countryGroupsDomain.getGroupId());
		kpisForAllGroupsDomain.setGroupName(countryGroupsDomain.getGroupName());
		kpisForAllGroupsDomain.setGroupUid(countryGroupsDomain.getGroupUid());
		kpisForAllGroupsDomain.setKpiValues(kpiValuesList);
		return kpisForAllGroupsDomain;
	}

}

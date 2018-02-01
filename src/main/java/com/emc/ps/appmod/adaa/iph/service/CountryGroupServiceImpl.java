package com.emc.ps.appmod.adaa.iph.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.emc.ps.appmod.adaa.iph.constants.Locale;
import com.emc.ps.appmod.adaa.iph.domain.CountryGroups;
import com.emc.ps.appmod.adaa.iph.domain.GroupScoreAverage;
import com.emc.ps.appmod.adaa.iph.entities.CountryGroup;
import com.emc.ps.appmod.adaa.iph.repo.CountryGroupRepo;

/**
 * This class deals with country groups
 */
@Service
public class CountryGroupServiceImpl implements CountryGroupService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryGroupServiceImpl.class);

	@Autowired
	private CountryGroupRepo countryGroupRepo;

	/**
	 * The method returns the list of country groups.
	 * 
	 * @param locale
	 * @return
	 */
	@Override
	@Cacheable("countryGroups")
	public List<CountryGroups> getGroups(Locale locale) {
		LOGGER.debug("Getting all the groups' info");
		List<CountryGroup> countryGroupEntityList = countryGroupRepo.findAll();
		List<CountryGroups> countryGroupsList = convertToDomainCountryGroupList(countryGroupEntityList, locale);
		return countryGroupsList;
	}

	/**
	 * This method will get the country-group map. This will be the master
	 * object for country and groups mapping.
	 * 
	 * @param locale
	 * @return
	 */
	@Override
	@Cacheable("countryGroupsMap")
	public Map<Integer, CountryGroups> getCountryGroupsMap(Locale locale) {
		List<CountryGroup> countryGroupsList = countryGroupRepo.findAll();
		Map<Integer, CountryGroups> countryGroupsMap = new HashMap<Integer, CountryGroups>();

		CountryGroups countryGroupsDomain = null;
		for (CountryGroup countryGroup : countryGroupsList) {
			countryGroupsDomain = convertToDomainCountryGroup(countryGroup, locale);
			countryGroupsMap.put(countryGroup.getId(), countryGroupsDomain);
		}
		return countryGroupsMap;
	}

	/**
	 * This method will reconstruct the group average list into the map format
	 * for all the years.
	 * 
	 * @param groupAverageForKpi
	 * @param locale
	 * @return
	 */
	@Override
	public Map<Integer, List<CountryGroups>> getGroupScoreAsMapByYear(List<GroupScoreAverage> groupAverageForKpi,
			Locale locale) {
		Map<Integer, List<CountryGroups>> groupScoreMap = new HashMap<Integer, List<CountryGroups>>();
		List<CountryGroups> groupScoreAverageLst = null;
		Map<Integer, CountryGroups> groupMasterMap = getCountryGroupsMap(locale);
		for (GroupScoreAverage groupAverage : groupAverageForKpi) {
			CountryGroups countryGroups = getCountryGroups(groupAverage, groupMasterMap);
			/**
			 * If the year is not present in the map, then add the year as key
			 * and list of countryGroups as value. Otherwise add the
			 * countryGroup into the same list present in the map
			 */
			if (null == groupScoreMap.get(groupAverage.getYear())) {
				groupScoreAverageLst = new ArrayList<CountryGroups>();
				groupScoreAverageLst.add(countryGroups);
				groupScoreMap.put(groupAverage.getYear(), groupScoreAverageLst);
			} else {
				List<CountryGroups> groupAverageLstTmp = groupScoreMap.get(groupAverage.getYear());
				groupAverageLstTmp.add(countryGroups);
			}
		}
		return groupScoreMap;
	}
	
	/**
	 * This method will reconstruct the group average list into the map format
	 * for all the kpis.
	 * 
	 * @param groupAverageForKpi
	 * @param locale
	 * @return
	 */
	@Override
	public Map<Integer, List<CountryGroups>> getGroupScoreAsMapByKpi(List<GroupScoreAverage> groupAverageForKpi,
			Locale locale) {
		Map<Integer, List<CountryGroups>> groupScoreMap = new HashMap<Integer, List<CountryGroups>>();
		List<CountryGroups> groupScoreAverageLst = null;
		Map<Integer, CountryGroups> groupMasterMap = getCountryGroupsMap(locale);
		for (GroupScoreAverage groupAverage : groupAverageForKpi) {
			CountryGroups countryGroups = getCountryGroups(groupAverage, groupMasterMap);
			/**
			 * If the kpi is not present in the map, then add the kpi as key
			 * and list of countryGroups as value. Otherwise add the
			 * countryGroup into the same list present in the map
			 */
			if (null == groupScoreMap.get(groupAverage.getKpiId())) {
				groupScoreAverageLst = new ArrayList<CountryGroups>();
				groupScoreAverageLst.add(countryGroups);
				groupScoreMap.put(groupAverage.getKpiId(), groupScoreAverageLst);
			} else {
				List<CountryGroups> groupAverageLstTmp = groupScoreMap.get(groupAverage.getKpiId());
				groupAverageLstTmp.add(countryGroups);
			}
		}
		return groupScoreMap;
	}

	/**
	 * This method converts the list of country group entity objects to list of
	 * country group domains
	 * 
	 * @param countryGroupEntityList
	 * @param locale
	 * @return
	 */
	private List<CountryGroups> convertToDomainCountryGroupList(List<CountryGroup> countryGroupEntityList,
			Locale locale) {
		List<CountryGroups> countryGroupsList = new ArrayList<CountryGroups>();
		CountryGroups countryGroups = new CountryGroups();
		for (CountryGroup countryGroupEntity : countryGroupEntityList) {
			countryGroups = convertToDomainCountryGroup(countryGroupEntity, locale);
			countryGroupsList.add(countryGroups);
		}
		return countryGroupsList;
	}

	/**
	 * This method converts the country group entity to country group domain
	 * object
	 * 
	 * @param countryGroupEntity
	 * @param locale
	 * @return
	 */
	private CountryGroups convertToDomainCountryGroup(CountryGroup countryGroupEntity, Locale locale) {
		CountryGroups countryGroups = new CountryGroups();
		countryGroups.setGroupId(countryGroupEntity.getId());
		switch (locale) {
		case EN:
			countryGroups.setGroupName(countryGroupEntity.getNameEn());
			break;
		case AR:
			countryGroups.setGroupName(countryGroupEntity.getNameAr());
			break;
		}
		countryGroups.setGroupUid(countryGroupEntity.getUid());
		countryGroups.setGroupScore(0.0);
		countryGroups.setGroupWeightedScore(0.0);
		countryGroups.setFormattedGroupScore("0.0");
		return countryGroups;
	}

	/**
	 * This method reconstructs the object with required information from master
	 * object.
	 * 
	 * @param groupId
	 * @return
	 */
	private CountryGroups getCountryGroups(GroupScoreAverage groupAverage, Map<Integer, CountryGroups> groupMasterMap) {

		CountryGroups countryGroupMaster = groupMasterMap.get(groupAverage.getGroupId());
		CountryGroups countryGroups = new CountryGroups();
		countryGroups.setGroupId(countryGroupMaster.getGroupId());
		countryGroups.setGroupName(countryGroupMaster.getGroupName());
		countryGroups.setGroupUid(countryGroupMaster.getGroupUid());
		countryGroups.setGroupScore(AuxiliaryClass.formatToTwoDecimalPlaces((groupAverage.getAverage())));
		countryGroups.setGroupWeightedScore(AuxiliaryClass.formatToTwoDecimalPlaces(groupAverage.getAverageColor()));
		countryGroups.setFormattedGroupScore(groupAverage.getFormattedAverage());
		return countryGroups;

	}

}

package com.emc.ps.appmod.adaa.iph.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.emc.ps.appmod.adaa.iph.constants.Constants;
import com.emc.ps.appmod.adaa.iph.constants.Locale;
import com.emc.ps.appmod.adaa.iph.domain.GroupScoreAverage;
import com.emc.ps.appmod.adaa.iph.domain.KpiCountriesCount;
import com.emc.ps.appmod.adaa.iph.domain.KpiResults;
import com.emc.ps.appmod.adaa.iph.entities.KpiScoreNormalized;
import com.emc.ps.appmod.adaa.iph.repo.KpiScoreNormalizedRepo;

/**
 * This class deals with KpiScoreNormalized data
 * 
 * @author bj3
 *
 */
@Service
public class KpiScoreNormalizedServiceImpl implements KpiScoreNormalizedService {

	private static final Logger LOGGER = LoggerFactory.getLogger(KpiScoreNormalizedServiceImpl.class);

	@Autowired
	private KpiScoreNormalizedRepo kpiScoreNormalizedRepo;

	@Autowired
	private CountryService countryService;

	@Autowired
	private KpiService kpiService;

	@Value("${" + Constants.WORLDGROUPID + "}")
	private String WORLDGROUPID;

	/**
	 * This method returns the list of kpiScoreNormalized entity for the given
	 * kpiId
	 * 
	 * @param kpiId
	 * @param locale
	 * @return
	 */
	@Override
	@Cacheable("kpiScoreNormalizedListByKpiId")
	public List<KpiScoreNormalized> getKpiScoreNormalizedListByKpiId(Integer kpiId) {
		LOGGER.debug("Getting all the scores for the kpiId : " + kpiId);
		List<KpiScoreNormalized> kpiScoreNormalizedEntityList = kpiScoreNormalizedRepo.findByKpiId(kpiId,
				Integer.valueOf(WORLDGROUPID));
		return kpiScoreNormalizedEntityList;
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
		LOGGER.debug("Getting all the kpis' score under a pillar for a country for an year!");
		List<KpiScoreNormalized> kpiScoreNormalizedEntityList = kpiScoreNormalizedRepo
				.findByPillarIdAndCountryIdAndYearOrderByIdAsc(pillarId, countryId, year,
						Integer.valueOf(WORLDGROUPID));
		List<KpiResults> kpiResultList = convertToDomainKpiResultList(kpiScoreNormalizedEntityList, locale);
		return kpiResultList;
	}

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
	@Override
	public List<KpiScoreNormalized> getKpiScoreNormalizedListByPillarIdAndYearAndCountryIds(Integer pillarId,
			List<String> countryIds, Integer year, Locale locale) {
		List<KpiScoreNormalized> kpiScoreNormalizedEntityList = kpiScoreNormalizedRepo
				.findByPillarIdAndYearAndCountryIds(pillarId, countryIds, year, Integer.valueOf(WORLDGROUPID));
		return kpiScoreNormalizedEntityList;
	}

	/**
	 * This method returns the list of kpiScoreNormalized entity for the kpis
	 * under the given pillarId and for all countries and groups and for a
	 * particular year
	 * 
	 * @param pillarId
	 * @param year
	 * @return
	 */
	@Override
	public List<KpiScoreNormalized> getKpiScoreNormalizedListByPillarIdAndYear(Integer pillarId, Integer year) {
		List<KpiScoreNormalized> kpiScoreNormalizedEntityList = kpiScoreNormalizedRepo.findByPillarIdAndYear(pillarId,
				year, Integer.valueOf(WORLDGROUPID));
		return kpiScoreNormalizedEntityList;
	}

	/**
	 * This method returns the list of kpiScoreNormalized entity for the given
	 * kpiId and for the countries under the countryGroupId
	 * 
	 * @param kpiId
	 * @param year
	 * @param countryGroupId
	 * @return
	 */
	@Override
	public List<KpiScoreNormalized> getKpiScoreNormalizedListByKpiIdAndYearAndCountryGroup(Integer kpiId, Integer year,
			Integer countryGroupId) {
		List<KpiScoreNormalized> kpiScoreNormalizedEntityList = kpiScoreNormalizedRepo
				.findByKpiIdAndYearAndCountryGroup(kpiId, year, countryGroupId);
		return kpiScoreNormalizedEntityList;
	}

	/**
	 * This method will get the group average score for all years for the given
	 * KPI
	 * 
	 * @param kpiId
	 * @return
	 */
	@Override
	@Cacheable("groupAverageForKpi")
	public List<GroupScoreAverage> getGroupAverageForAllYearsAndGivenKpi(Integer kpiId) {
		// return kpiScoreNormalizedRepo.findGroupAverageScoreForKpi(kpiId);
		List<GroupScoreAverage> groupAverageForKpi = kpiScoreNormalizedRepo.findGroupAverageScoreForKpi(kpiId);
		List<GroupScoreAverage> formattedGroupAverageForKpi = new ArrayList<GroupScoreAverage>();
		for (GroupScoreAverage groupScoreAverage : groupAverageForKpi) {
			/**
			 * Calculating the formatted score and also appending it to the
			 * domain object
			 */
			String formattedAverage = AuxiliaryClass.truncateNumber(groupScoreAverage.getAverage());
			groupScoreAverage.setFormattedAverage(formattedAverage);
			formattedGroupAverageForKpi.add(groupScoreAverage);
		}
		return formattedGroupAverageForKpi;
	}
	
	/**
	 * This method will get the group average score for given year for the kpis
	 * under given pillar
	 * 
	 * @param pillarId
	 * @param year
	 * @return
	 */
	@Override
	public List<GroupScoreAverage> getGroupAverageForPillarAndYear(Integer pillarId, Integer year) {
		List<GroupScoreAverage> groupAverageForKpi = kpiScoreNormalizedRepo.findGroupAverageScoreForPillarAndYear(pillarId, year);
		List<GroupScoreAverage> formattedGroupAverageForKpi = new ArrayList<GroupScoreAverage>();
		for (GroupScoreAverage groupScoreAverage : groupAverageForKpi) {
			/**
			 * Calculating the formatted score and also appending it to the
			 * domain object
			 */
			String formattedAverage = AuxiliaryClass.truncateNumber(groupScoreAverage.getAverage());
			groupScoreAverage.setFormattedAverage(formattedAverage);
			formattedGroupAverageForKpi.add(groupScoreAverage);
		}
		return formattedGroupAverageForKpi;
	}

	/**
	 * This method converts the list of KpiScoreNormalized entity into list of
	 * KpiResults domain object
	 * 
	 * @param kpiScoreNormalizedEntityList
	 * @param locale
	 * @return
	 */
	private List<KpiResults> convertToDomainKpiResultList(List<KpiScoreNormalized> kpiScoreNormalizedEntityList,
			Locale locale) {

		List<KpiResults> kpiResultList = new ArrayList<KpiResults>();
		KpiResults kpiResult = new com.emc.ps.appmod.adaa.iph.domain.KpiResults();
		for (KpiScoreNormalized kpiScoreNormalizedEntity : kpiScoreNormalizedEntityList) {
			kpiResult = convertToDomainKpiResult(kpiScoreNormalizedEntity, locale);
			kpiResultList.add(kpiResult);
		}
		return kpiResultList;

	}

	/**
	 * This method converts the KpiScoreNormalized entity into KpiResults domain
	 * object.
	 * 
	 * @param kpiScoreNormalizedEntity
	 * @param locale
	 * @return
	 */
	private KpiResults convertToDomainKpiResult(KpiScoreNormalized kpiScoreNormalizedEntity, Locale locale) {

		KpiResults kpiResult = new KpiResults();
		kpiResult.setId(kpiScoreNormalizedEntity.getId());
		kpiResult.setCountry(countryService.convertToDomain(kpiScoreNormalizedEntity.getCountry(), locale));
		kpiResult.setYear(kpiScoreNormalizedEntity.getYear());
		kpiResult.setKpi(kpiService.convertToDomainKpis(kpiScoreNormalizedEntity.getKpi(), locale));
		kpiResult.setScore(kpiScoreNormalizedEntity.getRank());
		kpiResult.setData(kpiScoreNormalizedEntity.getDataFormatted());
		kpiResult.setDataValue(kpiScoreNormalizedEntity.getData());
		kpiResult.setWeightedScore(kpiScoreNormalizedEntity.getColorRankNormalized());
		return kpiResult;

	}

	@Override
	public List<KpiCountriesCount> getCountryCountForKpiId(Integer pillarId,
			Integer year) {
		
		List<KpiCountriesCount> countryCountForApi = kpiScoreNormalizedRepo.findCountryCountForKpiInPillar(pillarId, year);
		
		
		return countryCountForApi;
	}

}

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
import com.emc.ps.appmod.adaa.iph.domain.Kpi;
import com.emc.ps.appmod.adaa.iph.domain.KpiDomainForFullStats;
import com.emc.ps.appmod.adaa.iph.domain.Kpis;
import com.emc.ps.appmod.adaa.iph.domain.KpisNew;
import com.emc.ps.appmod.adaa.iph.repo.KpiRepo;

/**
 * This class deals with kpi data
 * 
 * @author bj3
 *
 */
@Service
public class KpiServiceImpl implements KpiService {

	private static final Logger LOGGER = LoggerFactory.getLogger(KpiServiceImpl.class);

	@Autowired
	KpiRepo kpiRepo;
	
	@Value("${" + Constants.KPIDESCRIPTIONEN + "}")
	private String kpiDescriptionEn;
	
	@Value("${" + Constants.KPIDESCRIPTIONAR + "}")
	private String kpiDescriptionAr;

	/**
	 * This method returns the list of kpi under the given subtopic
	 * 
	 * @param subtopicId
	 * @param locale
	 * @return
	 */
	@Override
	public List<Kpi> getKpisFromSubtopic(Integer subtopicId, Locale locale) {
		LOGGER.debug("Getting list of Kpis under the subtopicId: " + subtopicId);
		List<com.emc.ps.appmod.adaa.iph.entities.Kpi> kpiEntityList = kpiRepo.findBySubtopicIdOrderByIdAsc(subtopicId);
		List<Kpi> kpiList = convertToKpiDomain(kpiEntityList, locale);
		return kpiList;
	}

	/**
	 * This method returns kpis domain object for the given kpiId
	 * 
	 * @param kpiId
	 * @param locale
	 * @return
	 */
	@Override
	@Cacheable("singleKpis")
	public Kpis getKpis(Integer kpiId, Locale locale) {
		com.emc.ps.appmod.adaa.iph.entities.Kpi kpiEntity = kpiRepo.findOne(kpiId);
		Kpis kpis = convertToDomainKpis(kpiEntity, locale);
		return kpis;
	}

	/**
	 * This method returns the Kpis domain object for the given kpi entity
	 * 
	 * @param kpi
	 * @param locale
	 * @return
	 */
	@Override
	public Kpis convertToDomainKpis(com.emc.ps.appmod.adaa.iph.entities.Kpi kpi, Locale locale) {

		LOGGER.debug("Converting the kpi " + kpi.getNameEn() + " to domain kpi");
		Kpis kpis = null;
		String kpiDescriptionLong;
		switch (locale) {
		case EN:
			kpiDescriptionLong = kpi.getDescriptionLongEn();
			if (kpiDescriptionLong == null || kpiDescriptionLong.equals(Constants.EMPTY) || kpiDescriptionLong.equals("0")) {
				kpiDescriptionLong = kpiDescriptionEn;
			}
			kpis = populateKpisDomain(kpi.getId(), kpi.getColor(), kpi.getNameEn(), kpiDescriptionLong,
					kpi.getDenominatorEn(), kpi.getNameAr());
			break;
		case AR:
			kpiDescriptionLong = kpi.getDescriptionLongAr();
			if (kpiDescriptionLong == null || kpiDescriptionLong.equals(Constants.EMPTY) || kpiDescriptionLong.equals("0")) {
				kpiDescriptionLong = kpiDescriptionAr;
			}
			kpis = populateKpisDomain(kpi.getId(), kpi.getColor(), kpi.getNameAr(), kpiDescriptionLong,
					kpi.getDenominatorAr(), kpi.getNameEn());
			break;
		}
		return kpis;

	}

	/**
	 * This method returns new kpis domain object for the given kpiId
	 * 
	 * @param kpiId
	 * @param locale
	 * @return
	 */
	@Override
	public KpisNew getKpisNew(Integer kpiId, Locale locale) {
		com.emc.ps.appmod.adaa.iph.entities.Kpi kpiEntity = kpiRepo.findOne(kpiId);
		KpisNew kpisNew = convertToKpisNewDomain(kpiEntity, locale);
		return kpisNew;
	}

	/**
	 * This method returns the list of KpiDomainForFullStats under the given
	 * pillar
	 * 
	 * @param pillarId
	 * @param locale
	 * @return
	 */
	@Override
	public List<KpiDomainForFullStats> getKpisFromPillarId(Integer pillarId, Locale locale) {
		List<com.emc.ps.appmod.adaa.iph.entities.Kpi> kpiEntityList = kpiRepo.findByPillarId(pillarId);
		List<KpiDomainForFullStats> kpisDomainList = new ArrayList<KpiDomainForFullStats>();
		for (com.emc.ps.appmod.adaa.iph.entities.Kpi kpiEntity : kpiEntityList) {
			KpiDomainForFullStats kpis = new KpiDomainForFullStats();
			kpis = convertToKpiDomainForFullStats(kpiEntity, locale);
			kpisDomainList.add(kpis);
		}
		return kpisDomainList;
	}

	/**
	 * This method returns the count of all kpis
	 * 
	 * @return
	 */
	public Integer getCountOfKpis() {
		List<com.emc.ps.appmod.adaa.iph.entities.Kpi> kpiEntityList = kpiRepo.findAllKpis();
		return kpiEntityList.size();
	}

	/**
	 * This method converts the given kpi entity to new Kpi domain object
	 * 
	 * @param kpiEntity
	 * @param locale
	 * @return
	 */
	private KpisNew convertToKpisNewDomain(com.emc.ps.appmod.adaa.iph.entities.Kpi kpiEntity, Locale locale) {
		LOGGER.debug("Converting the kpi " + kpiEntity.getNameEn() + " to domain kpi");
		KpisNew kpisNew = null;
		String kpiDescriptionLong;
		switch (locale) {
		case EN:
			kpiDescriptionLong = kpiEntity.getDescriptionLongEn();
			if (kpiDescriptionLong == null || kpiDescriptionLong.equals(Constants.EMPTY) || kpiDescriptionLong.equals("0")) {
				kpiDescriptionLong = kpiDescriptionEn;
			}
			kpisNew = populateKpisNewDomain(kpiEntity.getId(), kpiEntity.getNameEn(), kpiDescriptionLong,
					kpiEntity.getDenominatorEn(), kpiEntity.getDesiredValue(), kpiEntity.getNameAr(),
					kpiEntity.getSource().getNameEn(), kpiEntity.getDataUpdateFrequency());
			break;
		case AR:
			kpiDescriptionLong = kpiEntity.getDescriptionLongAr();
			if (kpiDescriptionLong == null || kpiDescriptionLong.equals(Constants.EMPTY) || kpiDescriptionLong.equals("0")) {
				kpiDescriptionLong = kpiDescriptionAr;
			}
			kpisNew = populateKpisNewDomain(kpiEntity.getId(), kpiEntity.getNameEn(), kpiDescriptionLong,
					kpiEntity.getDenominatorAr(), kpiEntity.getDesiredValue(), kpiEntity.getNameAr(),
					kpiEntity.getSource().getNameAr(), kpiEntity.getDataUpdateFrequencyAr());
			break;
		}
		return kpisNew;
	}

	/**
	 * This method populates the KpisNew domain object with the data provided
	 * 
	 * @param id
	 * @param nameEn
	 * @param descriptionLong
	 * @param denominator
	 * @param desiredValue
	 * @param nameAr
	 * @return
	 */
	private KpisNew populateKpisNewDomain(Integer id, String nameEn, String descriptionLong, String denominator,
			String desiredValue, String nameAr, String sourceName, String frequency) {

		KpisNew kpisNew = new KpisNew();
		kpisNew.setId(id);
		kpisNew.setNameEn(nameEn);
		kpisNew.setNameAr(nameAr);
		kpisNew.setDescriptionLong(descriptionLong);
		kpisNew.setDenominator(denominator);
		kpisNew.setDesiredValue(desiredValue);
		kpisNew.setSourceName(sourceName);
		kpisNew.setFrequency(frequency);
		return kpisNew;
	}

	/**
	 * This method converts the list of kpi entity into list of kpi domain
	 * object
	 * 
	 * @param kpiEntityList
	 * @param locale
	 * @return
	 */
	private List<Kpi> convertToKpiDomain(List<com.emc.ps.appmod.adaa.iph.entities.Kpi> kpiEntityList, Locale locale) {
		LOGGER.debug("Converting the KpiList to domain KpiList");
		List<Kpi> kpiList = new ArrayList<Kpi>();
		for (com.emc.ps.appmod.adaa.iph.entities.Kpi kpiEntity : kpiEntityList) {
			Kpi kpi = convertToDomain(kpiEntity, locale);
			kpiList.add(kpi);
		}
		return kpiList;
	}

	/**
	 * This method converts the given kpi entity to Kpi domain object
	 * 
	 * @param kpiEntity
	 * @param locale
	 * @return
	 */
	private Kpi convertToDomain(com.emc.ps.appmod.adaa.iph.entities.Kpi kpiEntity, Locale locale) {
		LOGGER.debug("Converting the Kpi " + kpiEntity.getNameEn() + " to domain Kpi");
		Kpi kpi = null;
		String kpiDescriptionLong;
		switch (locale) {
		case EN:
			kpiDescriptionLong = kpiEntity.getDescriptionLongEn();
			if (kpiDescriptionLong == null || kpiDescriptionLong.equals(Constants.EMPTY) || kpiDescriptionLong.equals("0")) {
				kpiDescriptionLong = kpiDescriptionEn;
			}
			kpi = populateKpi(kpiEntity.getId(), kpiEntity.getNameEn(), kpiEntity.getDenominatorEn(),
					kpiEntity.getNameAr(), kpiDescriptionLong, kpiEntity.getSource().getNameEn(),
					kpiEntity.getDataUpdateFrequency());
			break;
		case AR:
			kpiDescriptionLong = kpiEntity.getDescriptionLongAr();
			if (kpiDescriptionLong == null || kpiDescriptionLong.equals(Constants.EMPTY) || kpiDescriptionLong.equals("0")) {
				kpiDescriptionLong = kpiDescriptionAr;
			}
			kpi = populateKpi(kpiEntity.getId(), kpiEntity.getNameAr(), kpiEntity.getDenominatorAr(),
					kpiEntity.getNameEn(), kpiDescriptionLong, kpiEntity.getSource().getNameAr(),
					kpiEntity.getDataUpdateFrequencyAr());
			break;
		}
		return kpi;
	}

	/**
	 * This method populates the Kpi domain object with the data provided
	 * 
	 * @param id
	 * @param name
	 * @param denominator
	 * @return
	 */
	private Kpi populateKpi(Integer id, String name, String denominator, String nameOtherLang, String descriptionLong,
			String sourceName, String frequency) {

		Kpi kpi = new Kpi();
		kpi.setId(id);
		kpi.setName(name);
		kpi.setDenominator(denominator);
		kpi.setNameOtherLang(nameOtherLang);
		kpi.setDescriptionLong(descriptionLong);
		kpi.setSourceName(sourceName);
		kpi.setFrequency(frequency);
		return kpi;

	}

	/**
	 * This method populates the Kpis domain object with the data provided
	 * 
	 * @param id
	 * @param color
	 * @param name
	 * @param descriptionLong
	 * @param denominator
	 * @return
	 */
	private Kpis populateKpisDomain(Integer id, String color, String name, String descriptionLong, String denominator,
			String nameOtherLang) {
		Kpis kpis = new Kpis();
		kpis.setId(id);
		kpis.setColor(color);
		kpis.setName(name);
		kpis.setDescriptionLong(descriptionLong);
		kpis.setDenominator(denominator);
		kpis.setNameOtherLang(nameOtherLang);
		return kpis;
	}

	/**
	 * Converts the given kpiEntity into KpiDomainForFullStats
	 * 
	 * @param kpiEntity
	 * @param locale
	 * @return
	 */
	private KpiDomainForFullStats convertToKpiDomainForFullStats(com.emc.ps.appmod.adaa.iph.entities.Kpi kpiEntity,
			Locale locale) {
		KpiDomainForFullStats kpiDomainForFullStats = null;
		String kpiDescriptionLong;
		switch (locale) {
		case EN:
			kpiDescriptionLong = kpiEntity.getDescriptionLongEn();
			if (kpiDescriptionLong == null || kpiDescriptionLong.equals(Constants.EMPTY) || kpiDescriptionLong.equals("0")) {
				kpiDescriptionLong = kpiDescriptionEn;
			}
			kpiDomainForFullStats = populateKpiDomainForFullStats(kpiEntity.getId(), kpiEntity.getNameEn(),
					kpiDescriptionLong, kpiEntity.getDenominatorEn(), kpiEntity.getSource().getNameEn(),
					kpiEntity.getDataUpdateFrequency());
			break;

		case AR:
			kpiDescriptionLong = kpiEntity.getDescriptionLongAr();
			if (kpiDescriptionLong == null || kpiDescriptionLong.equals(Constants.EMPTY) || kpiDescriptionLong.equals("0")) {
				kpiDescriptionLong = kpiDescriptionAr;
			}
			kpiDomainForFullStats = populateKpiDomainForFullStats(kpiEntity.getId(), kpiEntity.getNameAr(),
					kpiDescriptionLong, kpiEntity.getDenominatorAr(), kpiEntity.getSource().getNameAr(),
					kpiEntity.getDataUpdateFrequencyAr());
			break;
		}
		return kpiDomainForFullStats;
	}

	/**
	 * Populate KpiDomainForFullStats with the given data
	 * 
	 * @param id
	 * @param name
	 * @param descriptionLong
	 * @param denominator
	 * @param sourceName
	 * @param dataUpdateFrequency
	 * @return
	 */
	private KpiDomainForFullStats populateKpiDomainForFullStats(Integer id, String name, String descriptionLong,
			String denominator, String sourceName, String dataUpdateFrequency) {
		KpiDomainForFullStats kpiDomainForFullStats = new KpiDomainForFullStats();
		kpiDomainForFullStats.setkpiId(id);
		kpiDomainForFullStats.setName(name);
		kpiDomainForFullStats.setDenominator(denominator);
		kpiDomainForFullStats.setDescriptionLong(descriptionLong);
		kpiDomainForFullStats.setSourceName(sourceName);
		kpiDomainForFullStats.setFrequency(dataUpdateFrequency);
		return kpiDomainForFullStats;
	}

}

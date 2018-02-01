package com.emc.ps.appmod.adaa.iph.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.emc.ps.appmod.adaa.iph.constants.Locale;
import com.emc.ps.appmod.adaa.iph.domain.Kpi;
import com.emc.ps.appmod.adaa.iph.domain.Pillars;
import com.emc.ps.appmod.adaa.iph.domain.Subtopics;
import com.emc.ps.appmod.adaa.iph.entities.Pillar;
import com.emc.ps.appmod.adaa.iph.entities.Subtopic;
import com.emc.ps.appmod.adaa.iph.repo.PillarRepo;

/**
 * This class deals with pillar data
 * 
 * @author bj3
 *
 */
@Service
public class PillarServiceImpl implements PillarService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PillarServiceImpl.class);

	@Autowired
	private PillarRepo pillarRepo;

	@Autowired
	private KpiService kpiService;
	
	@Autowired
	private CountryService countryService;

	/**
	 * This method returns the list of pillar domain
	 * 
	 * @param locale
	 * @return
	 */
	@Override
	@Cacheable("pillarList")
	public List<Pillars> getPillarList(Locale locale) {
		LOGGER.debug("Getting Pillar list");
		List<Pillar> pillarEntityList = pillarRepo.findAll();
		List<Pillars> pillarList = convertToDomainPillarList(pillarEntityList, locale);
		return pillarList;
	}

	/**
	 * This method converts the list of pillar entity into list of pillars
	 * domain
	 * 
	 * @param pillarEntityList
	 * @param locale
	 * @return
	 */
	private List<Pillars> convertToDomainPillarList(List<Pillar> pillarEntityList, Locale locale) {
		LOGGER.debug("Converting the PillarList to domain PillarList");
		List<Pillars> pillarList = new ArrayList<Pillars>();
		for (Pillar pillarEntity : pillarEntityList) {
			Pillars pillar = convertToDomain(pillarEntity, locale);
			pillar.setPillarsCount(pillarEntityList.size());
			pillar.setKpisCount(kpiService.getCountOfKpis());
			pillar.setCountriesCount(countryService.getCountriesCount());
			pillarList.add(pillar);
		}
		return pillarList;
	}

	/**
	 * This method converts the pillar entity into pillars domain
	 * 
	 * @param pillarEntity
	 * @param locale
	 * @return
	 */
	private Pillars convertToDomain(Pillar pillarEntity, Locale locale) {
		LOGGER.debug("Converting the Pillar " + pillarEntity.getNameEn() + " to domain Pillar");
		Pillars pillar = null;
		List<Subtopic> subtopicEntityList = pillarEntity.getSubtopics();
		List<Subtopics> subtopicList = convertToDomain(subtopicEntityList, locale);
		switch (locale) {
		case EN:
			pillar = populatePillarDetails(pillarEntity.getId(), pillarEntity.getNameEn(),
					pillarEntity.getDescriptionLongEn(), subtopicList, pillarEntity.getNameAr());
			break;
		case AR:
			pillar = populatePillarDetails(pillarEntity.getId(), pillarEntity.getNameAr(),
					pillarEntity.getDescriptionLongAr(), subtopicList, pillarEntity.getNameEn());
			break;
		}
		return pillar;
	}

	/**
	 * This method returns the given list of subtopic entity into list of
	 * subtopics domain
	 * 
	 * @param subtopicEntityList
	 * @param locale
	 * @return
	 */
	private List<Subtopics> convertToDomain(List<Subtopic> subtopicEntityList, Locale locale) {
		LOGGER.debug("Converting the SubtopicList to domain SubtopicList");
		List<Subtopics> subtopicList = new ArrayList<Subtopics>();
		for (Subtopic subtopicEntity : subtopicEntityList) {
			Subtopics subtopic = convertToDomain(subtopicEntity, locale);
			subtopicList.add(subtopic);
		}
		return subtopicList;
	}

	/**
	 * This method returns the given subtopic entity into subtopics domain
	 * 
	 * @param subtopicEntity
	 * @param locale
	 * @return
	 */
	private Subtopics convertToDomain(Subtopic subtopicEntity, Locale locale) {
		LOGGER.debug("Converting the Subtopic " + subtopicEntity.getNameEn() + " to domain Subtopic");
		Subtopics subtopic = null;
		switch (locale) {
		case EN:
			subtopic = populateSubtopic(subtopicEntity.getId(), subtopicEntity.getTopic().getId(),
					subtopicEntity.getPillar().getId(), subtopicEntity.getNameEn(),
					subtopicEntity.getDescriptionShortEn(), subtopicEntity.getDescriptionLongEn(),
					subtopicEntity.getNameAr());
			break;
		case AR:
			subtopic = populateSubtopic(subtopicEntity.getId(), subtopicEntity.getTopic().getId(),
					subtopicEntity.getPillar().getId(), subtopicEntity.getNameAr(),
					subtopicEntity.getDescriptionShortAr(), subtopicEntity.getDescriptionLongAr(),
					subtopicEntity.getNameEn());
			break;
		}
		List<Kpi> kpiList = kpiService.getKpisFromSubtopic(subtopic.getSubTopicId(), locale);
		subtopic.setKpiList(kpiList);
		return subtopic;
	}

	/**
	 * This method populates the subtopics domain with the data provided and
	 * return the domain object.
	 * 
	 * @param subTopicId
	 * @param topicId
	 * @param pillarId
	 * @param name
	 * @param descriptionShort
	 * @param descriptionLong
	 * @return
	 */
	private Subtopics populateSubtopic(int subTopicId, int topicId, int pillarId, String name, String descriptionShort,
			String descriptionLong, String nameOtherLang) {
		Subtopics subtopic = new Subtopics();
		subtopic.setSubTopicId(subTopicId);
		subtopic.setTopicId(topicId);
		subtopic.setPillarId(pillarId);
		subtopic.setTopicName(name);
		subtopic.setDescriptionShort(descriptionShort);
		subtopic.setDescriptionLong(descriptionLong);
		subtopic.setNameOtherLang(nameOtherLang);
		return subtopic;
	}

	/**
	 * This method returns the pillars domain by populating it with the data
	 * provided.
	 * 
	 * @param pillarId
	 * @param pillarName
	 * @param pillarDescription
	 * @param subtopicList
	 * @return
	 */
	private Pillars populatePillarDetails(Integer pillarId, String pillarName, String pillarDescription,
			List<Subtopics> subtopicList, String pillarNameOtherLang) {
		Pillars pillar = new Pillars();
		pillar.setPillarId(pillarId);
		pillar.setPillarName(pillarName);
		pillar.setDescription(pillarDescription);
		pillar.setSubTopicList(subtopicList);
		pillar.setPillarNameOtherLang(pillarNameOtherLang);
		return pillar;
	}

}

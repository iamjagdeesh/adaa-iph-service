package com.emc.ps.appmod.adaa.iph.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.emc.ps.appmod.adaa.iph.constants.Locale;
import com.emc.ps.appmod.adaa.iph.domain.Pillars;
import com.emc.ps.appmod.adaa.iph.domain.Subtopics;
import com.emc.ps.appmod.adaa.iph.entities.Kpi;
import com.emc.ps.appmod.adaa.iph.entities.Pillar;
import com.emc.ps.appmod.adaa.iph.entities.Source;
import com.emc.ps.appmod.adaa.iph.entities.Subtopic;
import com.emc.ps.appmod.adaa.iph.entities.Topic;
import com.emc.ps.appmod.adaa.iph.repo.PillarRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class PillarServiceTest {

	@Autowired
	private PillarService pillarService;

	@Autowired
	private PillarRepo pillarRepo;

	@Autowired
	private KpiService kpiService;
	
	@Autowired
	private CountryService countryService;

	@Configuration
	static class PillarServiceTestContextConfiguration {

		@Bean
		public PillarService pillarService() {
			return new PillarServiceImpl();
		}

		@Bean
		public PillarRepo pillarRepo() {
			return Mockito.mock(PillarRepo.class);
		}

		@Bean
		public KpiService kpiService() {
			return Mockito.mock(KpiService.class);
		}
		
		@Bean
		public CountryService countryService() {
			return Mockito.mock(CountryService.class);
		}

	}

	@Before
	public void setup() {

		Pillar pillar = new Pillar();
		pillar.setId(11);
		pillar.setNameEn("Social");
		pillar.setNameAr("التنمية الاجتماعية");
		pillar.setDescriptionLongEn("The Social Development Pillar assess the impact of social elements on the overall prosperity of a country. A strong correlation exists between social cohesion and economic development. The Pillar comprises three main topics, Equality, Wellbeing and Community.");
		Subtopic subtopicForPillar = new Subtopic();
		subtopicForPillar.setId(39);
		subtopicForPillar.setNameEn("Equality");
		subtopicForPillar.setNameAr("المساواة");
		subtopicForPillar.setDescriptionLongEn("The Equality topic provides insight on the level of equality between citizens as well as any disparities in rights that may exist. Furthermore, it provides insight on the distribution of wealth, the vulnerability and susceptibility to poverty and the participation of different demographics, such as women, within the community.");
		subtopicForPillar.setDescriptionShortEn("Overview on the level of equality between citizens and highlights disparities in rights that may exist");
		Pillar pillarForSubtopic = new Pillar();
		pillarForSubtopic.setId(11);
		pillarForSubtopic.setNameEn("Social");
		pillarForSubtopic.setNameAr("التنمية الاجتماعية");
		subtopicForPillar.setPillar(pillarForSubtopic);
		Topic topicForSubtopic = new Topic();
		topicForSubtopic.setId(29);
		subtopicForPillar.setTopic(topicForSubtopic);
		// subtopicForPillar.setKpis(kpiListForSubtopic);
		List<Subtopic> subtopicForPillarList = new ArrayList<Subtopic>();
		subtopicForPillarList.add(subtopicForPillar);
		pillar.setSubtopics(subtopicForPillarList);

		List<Pillar> pillarEntityList = new ArrayList<Pillar>();
		pillarEntityList.add(pillar);
		Mockito.when(pillarRepo.findAll()).thenReturn(pillarEntityList);

		Kpi kpi = new Kpi();
		kpi.setId(268);
		kpi.setNameEn("Women parliamentary seats");
		kpi.setNameAr("المقاعد في البرلمان التي تشغلها النساء");
		kpi.setDenominatorEn("Of total parl. seats");
		kpi.setDescriptionLongEn("Proportion of parliamentary seats in a single or lower chamber held by women as percentage of total parliamentary seats");
		kpi.setDataUpdateFrequency("Annual");
		
		Source source = new Source();
		source.setId(35);
		source.setNameEn("World Bank");
		source.setNameAr("البنك الدولي");
		
		kpi.setSource(source);
		
		
		/*
		 * List<Kpi> kpiEntityList = new ArrayList<Kpi>();
		 * kpiEntityList.add(kpi);
		 */

		com.emc.ps.appmod.adaa.iph.domain.Kpi kpiDomain = new com.emc.ps.appmod.adaa.iph.domain.Kpi();
		kpiDomain.setId(kpi.getId());
		kpiDomain.setName(kpi.getNameEn());
		kpiDomain.setDenominator(kpi.getDenominatorEn());
		kpiDomain.setDescriptionLong(kpi.getDescriptionLongEn());
		kpiDomain.setNameOtherLang(kpi.getNameAr());
		kpiDomain.setSourceName(kpi.getSource().getNameEn());
		kpiDomain.setFrequency(kpi.getDataUpdateFrequency());
		List<com.emc.ps.appmod.adaa.iph.domain.Kpi> kpiDomainList = new ArrayList<com.emc.ps.appmod.adaa.iph.domain.Kpi>();
		kpiDomainList.add(kpiDomain);

		Mockito.when(kpiService.getKpisFromSubtopic(subtopicForPillar.getId(), Locale.EN)).thenReturn(kpiDomainList);
		Mockito.when(kpiService.getCountOfKpis()).thenReturn(264);
		Mockito.when(countryService.getCountriesCount()).thenReturn(217);

	}

	@Test()
	public void testGetPillarList() {
		Pillars pillars = new Pillars();
		pillars.setPillarId(11);
		pillars.setPillarName("Social");
		pillars.setDescription("The Social Development Pillar assess the impact of social elements on the overall prosperity of a country. A strong correlation exists between social cohesion and economic development. The Pillar comprises three main topics, Equality, Wellbeing and Community.");
		pillars.setPillarNameOtherLang("التنمية الاجتماعية");
		pillars.setPillarsCount(1);
		pillars.setKpisCount(264);
		pillars.setCountriesCount(217);
		Subtopics subtopics = new Subtopics();
		subtopics.setSubTopicId(39);
		subtopics.setTopicName("Equality");
		subtopics.setNameOtherLang("المساواة");
		subtopics.setTopicId(29);
		subtopics.setPillarId(11);
		subtopics.setDescriptionLong("The Equality topic provides insight on the level of equality between citizens as well as any disparities in rights that may exist. Furthermore, it provides insight on the distribution of wealth, the vulnerability and susceptibility to poverty and the participation of different demographics, such as women, within the community.");
		subtopics.setDescriptionShort("Overview on the level of equality between citizens and highlights disparities in rights that may exist");
		com.emc.ps.appmod.adaa.iph.domain.Kpi kpi = new com.emc.ps.appmod.adaa.iph.domain.Kpi();
		kpi.setId(268);
		kpi.setName("Women parliamentary seats");
		kpi.setDenominator("Of total parl. seats");
		kpi.setDescriptionLong("Proportion of parliamentary seats in a single or lower chamber held by women as percentage of total parliamentary seats");
		kpi.setSourceName("World Bank");
		kpi.setNameOtherLang("المقاعد في البرلمان التي تشغلها النساء");
		kpi.setFrequency("Annual");
		List<com.emc.ps.appmod.adaa.iph.domain.Kpi> kpiList = new ArrayList<com.emc.ps.appmod.adaa.iph.domain.Kpi>();
		kpiList.add(kpi);
		subtopics.setKpiList(kpiList);
		List<Subtopics> subTopicList = new ArrayList<Subtopics>();
		subTopicList.add(subtopics);
		pillars.setSubTopicList(subTopicList);
		List<Pillars> expectedList = new ArrayList<Pillars>();
		expectedList.add(pillars);
		List<Pillars> list = pillarService.getPillarList(Locale.EN);
		assertEquals(expectedList, list);
	}

}

package com.emc.ps.appmod.adaa.iph.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.emc.ps.appmod.adaa.iph.constants.Constants;
import com.emc.ps.appmod.adaa.iph.constants.Locale;
import com.emc.ps.appmod.adaa.iph.domain.Kpis;
import com.emc.ps.appmod.adaa.iph.entities.Kpi;
import com.emc.ps.appmod.adaa.iph.entities.Pillar;
import com.emc.ps.appmod.adaa.iph.entities.Source;
import com.emc.ps.appmod.adaa.iph.entities.Subtopic;
import com.emc.ps.appmod.adaa.iph.repo.KpiRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@TestPropertySource("classpath:application.properties")
public class KpiServiceTest {
	
	@Autowired
	private KpiService kpiService;
	
	@Autowired
	private KpiRepo kpiRepo;
	
	@Value("${" + Constants.KPIDESCRIPTIONEN + "}")
	private String kpiDescriptionEn;
	
	@Value("${" + Constants.KPIDESCRIPTIONAR + "}")
	private String kpiDescriptionAr;
	
	@Configuration
	static class KpiServiceTestContextConfiguration {
		
		@Bean
		public KpiService kpiService() {
			return new KpiServiceImpl();
		}
		
		@Bean
		public KpiRepo kpiRepo() {
			return Mockito.mock(KpiRepo.class);
		}
		
	}
	
	@Before
	public void setup() {
		
		Pillar pillar = new Pillar();
		pillar.setId(11);
		pillar.setNameEn("Social");
		pillar.setNameAr("التنمية الاجتماعية");

		Subtopic subtopic = new Subtopic();
		subtopic.setId(39);
		subtopic.setNameEn("Equality");
		subtopic.setNameAr("المساواة");
		subtopic.setPillar(pillar);
		
		Source source = new Source();
		source.setId(35);
		source.setNameEn("World Bank");
		source.setNameAr("البنك الدولي");
		
		Kpi kpi = new Kpi();
		kpi.setId(268);
		kpi.setNameEn("Women parliamentary seats");
		kpi.setNameAr("المقاعد في البرلمان التي تشغلها النساء");
		kpi.setDenominatorEn("Of total parl. seats");
		kpi.setDenominatorAr("نسبة من");
		kpi.setSubtopic(subtopic);
		kpi.setDataUpdateFrequency("Annual");
		kpi.setSource(source);
		
		List<Kpi> kpiEntityList = new ArrayList<Kpi>();
		kpiEntityList.add(kpi);
		
		Mockito.when(kpiRepo.findOne(268)).thenReturn(kpi);
		
		Mockito.when(kpiRepo.findBySubtopicIdOrderByIdAsc(39)).thenReturn(kpiEntityList);

		
	}
	
	@Test
	public void testGetKpisFromSubtopic() {
		com.emc.ps.appmod.adaa.iph.domain.Kpi kpiDomain = new com.emc.ps.appmod.adaa.iph.domain.Kpi();
		kpiDomain.setId(268);
		kpiDomain.setName("Women parliamentary seats");
		kpiDomain.setDenominator("Of total parl. seats");
		kpiDomain.setDescriptionLong(kpiDescriptionEn);
		kpiDomain.setSourceName("World Bank");
		kpiDomain.setNameOtherLang("المقاعد في البرلمان التي تشغلها النساء");
		kpiDomain.setFrequency("Annual");
		
		List<com.emc.ps.appmod.adaa.iph.domain.Kpi> expectedKpiDomainList = new ArrayList<com.emc.ps.appmod.adaa.iph.domain.Kpi>();
		expectedKpiDomainList.add(kpiDomain);
		List<com.emc.ps.appmod.adaa.iph.domain.Kpi> kpiList = kpiService.getKpisFromSubtopic(39, Locale.EN);
		assertEquals(expectedKpiDomainList, kpiList);
	}
	
	@Test
	public void testGetKpis() {
		Kpis expectedKpis = new Kpis();
		expectedKpis.setId(268);
		expectedKpis.setName("Women parliamentary seats");
		expectedKpis.setDenominator("Of total parl. seats");
		expectedKpis.setDescriptionLong(kpiDescriptionEn);
		Kpis kpis = kpiService.getKpis(268, Locale.EN);
		assertEquals(expectedKpis, kpis);
	}

	@Test
	public void testConvertToDomainKpis() {
		
		Kpi kpi = new Kpi();
		kpi.setId(268);
		kpi.setNameEn("Women parliamentary seats");
		kpi.setNameAr("المقاعد في البرلمان التي تشغلها النساء");
		kpi.setDenominatorEn("Of total parl. seats");
		kpi.setDenominatorAr("نسبة من");
		
		Kpis expectedKpis = new Kpis();
		expectedKpis.setId(268);
		expectedKpis.setName("Women parliamentary seats");
		expectedKpis.setDenominator("Of total parl. seats");
		expectedKpis.setDescriptionLong(kpiDescriptionEn);
		
		Kpis kpis = kpiService.convertToDomainKpis(kpi, Locale.EN);
		assertEquals(expectedKpis, kpis);
	}
}

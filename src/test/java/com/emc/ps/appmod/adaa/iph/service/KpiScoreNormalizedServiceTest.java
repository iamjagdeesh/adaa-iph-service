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
import com.emc.ps.appmod.adaa.iph.domain.Countries;
import com.emc.ps.appmod.adaa.iph.domain.GroupScoreAverage;
import com.emc.ps.appmod.adaa.iph.domain.KpiResults;
import com.emc.ps.appmod.adaa.iph.domain.Kpis;
import com.emc.ps.appmod.adaa.iph.entities.Country;
import com.emc.ps.appmod.adaa.iph.entities.CountryGroup;
import com.emc.ps.appmod.adaa.iph.entities.Kpi;
import com.emc.ps.appmod.adaa.iph.entities.KpiScoreNormalized;
import com.emc.ps.appmod.adaa.iph.entities.Pillar;
import com.emc.ps.appmod.adaa.iph.entities.Subtopic;
import com.emc.ps.appmod.adaa.iph.repo.KpiScoreNormalizedRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@TestPropertySource("classpath:application.properties")
public class KpiScoreNormalizedServiceTest {

	@Autowired
	private KpiScoreNormalizedService kpiScoreNormalizedService;

	@Autowired
	private KpiScoreNormalizedRepo kpiScoreNormalizedRepo;

	@Autowired
	private CountryService countryService;
	
	@Autowired
	private IphScoreRankingService iphScoreRankingService;

	@Autowired
	private KpiService kpiService;

	@Value("${" + Constants.WORLDGROUPID + "}")
	private String WORLDGROUPID;

	@Configuration
	static class KpiScoreNormalizedServiceTestContextConfiguration {

		@Bean
		public KpiScoreNormalizedService kpiScoreNormalizedService() {
			return new KpiScoreNormalizedServiceImpl();
		}

		@Bean
		public KpiScoreNormalizedRepo kpiScoreNormalizedRepo() {
			return Mockito.mock(KpiScoreNormalizedRepo.class);
		}

		@Bean
		public CountryService countryService() {
			return Mockito.mock(CountryService.class);
		}

		@Bean
		public KpiService kpiService() {
			return Mockito.mock(KpiService.class);
		}
		
		@Bean
		public IphScoreRankingService iphScoreRankingService() {
			return Mockito.mock(IphScoreRankingService.class);
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

		Kpi kpi = new Kpi();
		kpi.setId(268);
		kpi.setNameEn("Women parliamentary seats");
		kpi.setNameAr("المقاعد في البرلمان التي تشغلها النساء");
		kpi.setDenominatorEn("Of total parl. seats");
		kpi.setDenominatorAr("نسبة من");
		kpi.setSubtopic(subtopic);

		Kpis kpiDomain = new Kpis();
		kpiDomain.setId(268);
		kpiDomain.setName("Women parliamentary seats");
		kpiDomain.setDenominator("Of total parl. seats");

		Country country = new Country();
		country.setId("AE");
		country.setNameEn("United Arab Emirates");
		country.setNameAr("الإمارات العربية المتحدة ");

		List<String> countryIds = new ArrayList<String>();
		countryIds.add(country.getId());

		Countries countryDomain = new Countries();
		countryDomain.setCountryId("AE");
		countryDomain.setCountryName("United Arab Emirates");

		CountryGroup countryGroup = new CountryGroup();
		countryGroup.setId(1);
		countryGroup.setUid("GLO");
		countryGroup.setNameEn("World");
		countryGroup.setNameAr("عالم");

		List<KpiScoreNormalized> kpiScoreNormalizedEntityList = new ArrayList<KpiScoreNormalized>();
		KpiScoreNormalized kpiScoreNormalizedEntity = new KpiScoreNormalized();
		kpiScoreNormalizedEntity.setId(3311349);
		kpiScoreNormalizedEntity.setKpi(kpi);
		kpiScoreNormalizedEntity.setCountry(country);
		kpiScoreNormalizedEntity.setData(22.5);
		kpiScoreNormalizedEntity.setDataFormatted("22.5");
		kpiScoreNormalizedEntity.setRank(55);
		kpiScoreNormalizedEntity.setColorRankNormalized(1.7);
		kpiScoreNormalizedEntity.setYear(2016);
		kpiScoreNormalizedEntity.setCountryGroup(countryGroup);
		kpiScoreNormalizedEntity.setGroupUid("GLO");
		kpiScoreNormalizedEntityList.add(kpiScoreNormalizedEntity);

		GroupScoreAverage groupScoreAverage = new GroupScoreAverage(22.5, 1, 2016, 268, 1.7);
		List<GroupScoreAverage> groupScoreAverages = new ArrayList<GroupScoreAverage>();
		groupScoreAverages.add(groupScoreAverage);

		Mockito.when(kpiScoreNormalizedRepo.findByKpiId(268, Integer.valueOf(WORLDGROUPID)))
				.thenReturn(kpiScoreNormalizedEntityList);

		Mockito.when(kpiScoreNormalizedRepo.findByPillarIdAndCountryIdAndYearOrderByIdAsc(11, "AE", 2016,
				Integer.valueOf(WORLDGROUPID))).thenReturn(kpiScoreNormalizedEntityList);

		Mockito.when(countryService.convertToDomain(country, Locale.EN)).thenReturn(countryDomain);

		Mockito.when(kpiService.convertToDomainKpis(kpiScoreNormalizedEntity.getKpi(), Locale.EN))
				.thenReturn(kpiDomain);

		Mockito.when(kpiScoreNormalizedRepo.findByPillarIdAndYearAndCountryIds(11, countryIds, 2016,
				Integer.valueOf(WORLDGROUPID))).thenReturn(kpiScoreNormalizedEntityList);

		Mockito.when(kpiScoreNormalizedRepo.findByKpiIdAndYearAndCountryGroup(268, 2016, 1))
				.thenReturn(kpiScoreNormalizedEntityList);

		Mockito.when(kpiScoreNormalizedRepo.findGroupAverageScoreForKpi(268)).thenReturn(groupScoreAverages);
		
		Mockito.when(iphScoreRankingService.truncateNumber(groupScoreAverage.getAverage())).thenReturn(groupScoreAverage.getAverage().toString());

	}

	@Test
	public void testGetKpiScoreNormalizedListByKpiId() {

		List<KpiScoreNormalized> expectedKpiScoreNormalizedEntityList = new ArrayList<KpiScoreNormalized>();
		KpiScoreNormalized kpiScoreNormalizedEntity = new KpiScoreNormalized();
		kpiScoreNormalizedEntity.setId(3311349);
		kpiScoreNormalizedEntity.setKpi(null);
		kpiScoreNormalizedEntity.setCountry(null);
		kpiScoreNormalizedEntity.setData(22.5);
		kpiScoreNormalizedEntity.setDataFormatted("22.5");
		kpiScoreNormalizedEntity.setRank(55);
		kpiScoreNormalizedEntity.setColorRankNormalized(1.7);
		kpiScoreNormalizedEntity.setYear(2016);
		kpiScoreNormalizedEntity.setCountryGroup(null);
		kpiScoreNormalizedEntity.setGroupUid("GLO");
		expectedKpiScoreNormalizedEntityList.add(kpiScoreNormalizedEntity);

		List<KpiScoreNormalized> kpiScoreNormalizedEntityList = kpiScoreNormalizedService
				.getKpiScoreNormalizedListByKpiId(268);
		assertEquals(expectedKpiScoreNormalizedEntityList, kpiScoreNormalizedEntityList);
	}

	@Test
	public void testGetAllKpiResultList() {

		Kpis kpi = new Kpis();
		kpi.setId(268);
		kpi.setName("Women parliamentary seats");
		kpi.setDenominator("Of total parl. seats");
		Countries country = new Countries();
		country.setCountryId("AE");
		country.setCountryName("United Arab Emirates");
		KpiResults kpiResults = new KpiResults();
		kpiResults.setId(3311349);
		kpiResults.setScore(55);
		kpiResults.setData("22.5");
		kpiResults.setDataValue(22.5);
		kpiResults.setWeightedScore(1.7);
		kpiResults.setYear(2016);
		kpiResults.setCountry(country);
		kpiResults.setKpi(kpi);
		List<KpiResults> expectedkpiResultList = new ArrayList<KpiResults>();
		expectedkpiResultList.add(kpiResults);
		List<KpiResults> kpiResultList = kpiScoreNormalizedService.getAllKpiResultList(11, "AE", 2016, Locale.EN);
		assertEquals(expectedkpiResultList, kpiResultList);
	}

	@Test
	public void testGetKpiScoreNormalizedListByPillarIdAndYearAndCountryIds() {

		List<KpiScoreNormalized> expectedKpiScoreNormalizedEntityList = new ArrayList<KpiScoreNormalized>();
		KpiScoreNormalized kpiScoreNormalizedEntity = new KpiScoreNormalized();
		kpiScoreNormalizedEntity.setId(3311349);
		kpiScoreNormalizedEntity.setKpi(null);
		kpiScoreNormalizedEntity.setCountry(null);
		kpiScoreNormalizedEntity.setData(22.5);
		kpiScoreNormalizedEntity.setDataFormatted("22.5");
		kpiScoreNormalizedEntity.setRank(55);
		kpiScoreNormalizedEntity.setColorRankNormalized(1.7);
		kpiScoreNormalizedEntity.setYear(2016);
		kpiScoreNormalizedEntity.setCountryGroup(null);
		kpiScoreNormalizedEntity.setGroupUid("GLO");
		expectedKpiScoreNormalizedEntityList.add(kpiScoreNormalizedEntity);

		String countryId = "AE";
		List<String> countryIds = new ArrayList<String>();
		countryIds.add(countryId);
		List<KpiScoreNormalized> kpiScoreNormalizedEntityList = kpiScoreNormalizedService
				.getKpiScoreNormalizedListByPillarIdAndYearAndCountryIds(11, countryIds, 2016, Locale.EN);
		assertEquals(expectedKpiScoreNormalizedEntityList, kpiScoreNormalizedEntityList);
	}

	@Test
	public void testGetKpiScoreNormalizedListByKpiIdAndYearAndCountryGroup() {

		List<KpiScoreNormalized> expectedKpiScoreNormalizedEntityList = new ArrayList<KpiScoreNormalized>();
		KpiScoreNormalized kpiScoreNormalizedEntity = new KpiScoreNormalized();
		kpiScoreNormalizedEntity.setId(3311349);
		kpiScoreNormalizedEntity.setKpi(null);
		kpiScoreNormalizedEntity.setCountry(null);
		kpiScoreNormalizedEntity.setData(22.5);
		kpiScoreNormalizedEntity.setDataFormatted("22.5");
		kpiScoreNormalizedEntity.setRank(55);
		kpiScoreNormalizedEntity.setColorRankNormalized(1.7);
		kpiScoreNormalizedEntity.setYear(2016);
		kpiScoreNormalizedEntity.setCountryGroup(null);
		kpiScoreNormalizedEntity.setGroupUid("GLO");
		expectedKpiScoreNormalizedEntityList.add(kpiScoreNormalizedEntity);

		List<KpiScoreNormalized> kpiScoreNormalizedEntityList = kpiScoreNormalizedService
				.getKpiScoreNormalizedListByKpiIdAndYearAndCountryGroup(268, 2016, 1);
		assertEquals(expectedKpiScoreNormalizedEntityList, kpiScoreNormalizedEntityList);
	}

	@Test
	public void testGetGroupAverageForAllYearsAndGivenKpi() {

		GroupScoreAverage groupScoreAverage = new GroupScoreAverage(22.5, 1, 2016, 268, 1.7);
		List<GroupScoreAverage> expectedGroupScoreAverageList = new ArrayList<GroupScoreAverage>();
		expectedGroupScoreAverageList.add(groupScoreAverage);

		List<GroupScoreAverage> groupScoreAverageList = kpiScoreNormalizedService
				.getGroupAverageForAllYearsAndGivenKpi(268);
		assertEquals(expectedGroupScoreAverageList, groupScoreAverageList);
	}

}

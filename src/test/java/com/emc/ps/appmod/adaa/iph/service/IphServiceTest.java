package com.emc.ps.appmod.adaa.iph.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.emc.ps.appmod.adaa.iph.domain.CountriesAlphabetsDomain;
import com.emc.ps.appmod.adaa.iph.domain.CountryGroupRankingsDomain;
import com.emc.ps.appmod.adaa.iph.domain.CountryGroups;
import com.emc.ps.appmod.adaa.iph.domain.GroupScoreAverage;
import com.emc.ps.appmod.adaa.iph.domain.KpiResultForAllCountriesAllYear;
import com.emc.ps.appmod.adaa.iph.domain.KpiResults;
import com.emc.ps.appmod.adaa.iph.domain.KpiScores;
import com.emc.ps.appmod.adaa.iph.domain.Kpis;
import com.emc.ps.appmod.adaa.iph.domain.KpisForSomeCountriesDomain;
import com.emc.ps.appmod.adaa.iph.domain.Pillars;
import com.emc.ps.appmod.adaa.iph.domain.Subtopics;
import com.emc.ps.appmod.adaa.iph.entities.Country;
import com.emc.ps.appmod.adaa.iph.entities.CountryCountryGroup;
import com.emc.ps.appmod.adaa.iph.entities.CountryGroup;
import com.emc.ps.appmod.adaa.iph.entities.Kpi;
import com.emc.ps.appmod.adaa.iph.entities.KpiScoreNormalized;
import com.emc.ps.appmod.adaa.iph.entities.Pillar;
import com.emc.ps.appmod.adaa.iph.entities.Source;
import com.emc.ps.appmod.adaa.iph.entities.Subtopic;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@TestPropertySource("classpath:application.properties")
public class IphServiceTest {

	@Autowired
	private IphService iphService;

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

	@Configuration
	static class IphServiceTestContextConfiguration {

		@Bean
		public IphService iphService() {
			return new IphServiceImpl();
		}

		@Bean
		public CountryService countryService() {
			return Mockito.mock(CountryService.class);
		}

		@Bean
		public CountryGroupService countryGroupService() {
			return Mockito.mock(CountryGroupService.class);
		}

		@Bean
		public KpiService kpiService() {
			return Mockito.mock(KpiService.class);
		}

		@Bean
		public PillarService pillarService() {
			return Mockito.mock(PillarService.class);
		}

		@Bean
		public KpiScoreNormalizedService kpiScoreNormalizedService() {
			return Mockito.mock(KpiScoreNormalizedService.class);
		}
		
		@Bean
		public KpiResultService kpiResultService() {
			return Mockito.mock(KpiResultService.class);
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
		kpi.setSource(source);

		Country country = new Country();
		country.setId("AE");
		country.setNameEn("United Arab Emirates");
		country.setNameAr("الإمارات العربية المتحدة ");

		List<Country> countryEntityList = new ArrayList<Country>();
		countryEntityList.add(country);

		CountryGroup countryGroup = new CountryGroup();
		countryGroup.setId(1);
		countryGroup.setUid("GLO");
		countryGroup.setNameEn("World");
		countryGroup.setNameAr("عالم");

		CountryCountryGroup countryCountryGroup = new CountryCountryGroup();
		countryCountryGroup.setId(139);
		countryCountryGroup.setCountry(country);
		countryCountryGroup.setCountryGroup(countryGroup);

		List<CountryCountryGroup> countryCountryGroupEntityList = new ArrayList<CountryCountryGroup>();
		countryCountryGroupEntityList.add(countryCountryGroup);

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

		List<String> countries = new ArrayList<String>();
		countries.add("AE");

		Countries countryDomain = new Countries();
		countryDomain.setCountryName("United Arab Emirates");
		countryDomain.setCountryId("AE");
		List<Countries> countryDomainList = new ArrayList<Countries>();
		countryDomainList.add(countryDomain);

		CountriesAlphabetsDomain countriesAlphabetsDomain = new CountriesAlphabetsDomain();
		countriesAlphabetsDomain.setAlphabet('U');
		countriesAlphabetsDomain.setCountriesList(countryDomainList);
		List<CountriesAlphabetsDomain> countriesAlphabetsDomainList = new ArrayList<CountriesAlphabetsDomain>();
		countriesAlphabetsDomainList.add(countriesAlphabetsDomain);

		CountryGroups countryGroups = new CountryGroups();
		countryGroups.setGroupId(1);
		countryGroups.setGroupName("World");
		countryGroups.setGroupUid("GLO");
		countryGroups.setGroupScore(22.5);
		countryGroups.setGroupWeightedScore(1.7);
		List<CountryGroups> countryGroupsList = new ArrayList<CountryGroups>();
		countryGroupsList.add(countryGroups);
		Map<Integer, List<CountryGroups>> groupScoreMap = new HashMap<Integer, List<CountryGroups>>();
		groupScoreMap.put(2016, countryGroupsList);

		Kpis kpisDomain = new Kpis();
		kpisDomain.setId(268);
		kpisDomain.setName("Women parliamentary seats");
		kpisDomain.setDenominator("Of total parl. seats");

		KpiResults kpiResults = new KpiResults();
		kpiResults.setId(3311349);
		kpiResults.setScore(55);
		kpiResults.setData("22.5");
		kpiResults.setDataValue(22.5);
		kpiResults.setWeightedScore(1.7);
		kpiResults.setYear(2016);
		kpiResults.setCountry(countryDomain);
		kpiResults.setKpi(kpisDomain);
		List<KpiResults> kpiResultList = new ArrayList<KpiResults>();
		kpiResultList.add(kpiResults);

		Mockito.when(kpiScoreNormalizedService.getKpiScoreNormalizedListByKpiId(268))
				.thenReturn(kpiScoreNormalizedEntityList);

		Mockito.when(countryService.getCountryList(Locale.EN)).thenReturn(countryDomainList);

		Mockito.when(kpiScoreNormalizedService.getGroupAverageForAllYearsAndGivenKpi(268))
				.thenReturn(groupScoreAverages);

		Mockito.when(countryService.getAlphabeticCountryList(Locale.EN)).thenReturn(countriesAlphabetsDomainList);

		Mockito.when(countryGroupService.getGroupScoreAsMapByYear(groupScoreAverages, Locale.EN))
				.thenReturn(groupScoreMap);

		Mockito.when(kpiService.getKpis(268, Locale.EN)).thenReturn(kpisDomain);

		Mockito.when(kpiScoreNormalizedService.getAllKpiResultList(11, "AE", 2016, Locale.EN))
				.thenReturn(kpiResultList);

		Mockito.when(kpiScoreNormalizedService.getKpiScoreNormalizedListByPillarIdAndYearAndCountryIds(11, countries,
				2016, Locale.EN)).thenReturn(kpiScoreNormalizedEntityList);

		Mockito.when(countryService.getCountry("AE", Locale.EN)).thenReturn(countryDomain);

		Mockito.when(countryGroupService.getGroups(Locale.EN)).thenReturn(countryGroupsList);

		Mockito.when(kpiScoreNormalizedService.getKpiScoreNormalizedListByKpiIdAndYearAndCountryGroup(268, 2016, 1))
				.thenReturn(kpiScoreNormalizedEntityList);
		
		Mockito.when(countryService.convertToDomain(kpiScoreNormalizedEntity.getCountry(), Locale.EN)).thenReturn(countryDomain);

	}

	@Before
	public void setUpForPillarList() {

		Pillars pillars = new Pillars();
		pillars.setPillarId(11);
		pillars.setPillarName("Social");
		Subtopics subtopics = new Subtopics();
		subtopics.setSubTopicId(39);
		subtopics.setTopicName("Equality");
		subtopics.setTopicId(29);
		subtopics.setPillarId(11);
		com.emc.ps.appmod.adaa.iph.domain.Kpi kpi = new com.emc.ps.appmod.adaa.iph.domain.Kpi();
		kpi.setId(268);
		kpi.setName("Women parliamentary seats");
		List<com.emc.ps.appmod.adaa.iph.domain.Kpi> kpiList = new ArrayList<com.emc.ps.appmod.adaa.iph.domain.Kpi>();
		kpiList.add(kpi);
		subtopics.setKpiList(kpiList);
		List<Subtopics> subTopicList = new ArrayList<Subtopics>();
		subTopicList.add(subtopics);
		pillars.setSubTopicList(subTopicList);
		List<Pillars> pillarList = new ArrayList<Pillars>();
		pillarList.add(pillars);
		Mockito.when(pillarService.getPillarList(Locale.EN)).thenReturn(pillarList);

	}

	@Test()
	public void testGetPillarList() {

		Pillars pillars = new Pillars();
		pillars.setPillarId(11);
		pillars.setPillarName("Social");
		Subtopics subtopics = new Subtopics();
		subtopics.setSubTopicId(39);
		subtopics.setTopicName("Equality");
		subtopics.setTopicId(29);
		subtopics.setPillarId(11);
		com.emc.ps.appmod.adaa.iph.domain.Kpi kpi = new com.emc.ps.appmod.adaa.iph.domain.Kpi();
		kpi.setId(268);
		kpi.setName("Women parliamentary seats");
		List<com.emc.ps.appmod.adaa.iph.domain.Kpi> kpiList = new ArrayList<com.emc.ps.appmod.adaa.iph.domain.Kpi>();
		kpiList.add(kpi);
		subtopics.setKpiList(kpiList);
		List<Subtopics> subTopicList = new ArrayList<Subtopics>();
		subTopicList.add(subtopics);
		pillars.setSubTopicList(subTopicList);
		List<Pillars> expectedPillarList = new ArrayList<Pillars>();
		expectedPillarList.add(pillars);
		List<Pillars> pillarList = iphService.getPillarList(Locale.EN);
		assertEquals(expectedPillarList, pillarList);

	}

	@Test
	public void testGetCountryList() {

		Countries countryDomain = new Countries();
		countryDomain.setCountryName("United Arab Emirates");
		countryDomain.setCountryId("AE");
		List<Countries> expectedCountryList = new ArrayList<Countries>();
		expectedCountryList.add(countryDomain);
		List<Countries> countryList = iphService.getCountryList(Locale.EN);
		assertEquals(expectedCountryList, countryList);

	}

	@Test
	public void testGetAlphabeticCountryList() {

		CountriesAlphabetsDomain countriesAlphabetsDomain = new CountriesAlphabetsDomain();
		Countries countries = new Countries();
		countries.setCountryId("AE");
		countries.setCountryName("United Arab Emirates");
		List<Countries> countriesList = new ArrayList<Countries>();
		countriesList.add(countries);
		countriesAlphabetsDomain.setAlphabet('U');
		countriesAlphabetsDomain.setCountriesList(countriesList);
		List<CountriesAlphabetsDomain> expectedList = new ArrayList<CountriesAlphabetsDomain>();
		expectedList.add(countriesAlphabetsDomain);

		List<CountriesAlphabetsDomain> alphabeticCountryList = iphService.getAlphabeticCountryList(Locale.EN);
		assertEquals(expectedList, alphabeticCountryList);
	}

	@Test
	public void testGetKpiResultListForMap() {

		Map<String, Object> expectedMap = new HashMap<String, Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> temp = new HashMap<String, Object>();
		Map<String, Object> countryMap = new HashMap<String, Object>();
		Map<String, Object> tooltip = new HashMap<String, Object>();
		String toolTipValue = "<div class=\"country-flag sprite sprite-AE\"></div><span class='countryHover'>United Arab Emirates</span><br /><span class='scoreData' style='color:#FFA019'>22.5 / Of total parl. seats</span>";
		tooltip.put(CONTENT, toolTipValue);
		countryMap.put(MAPVALUE, "1.7");
		countryMap.put(Constants.score, "22.5");
		countryMap.put(MAPDATAVALUE, 22.5);
		countryMap.put(TOOLTIP, tooltip);
		temp.put("AE", countryMap);
		dataMap.put(MAPAREAS, temp);
		List<CountryGroups> countryGroupsList = new ArrayList<CountryGroups>();
		CountryGroups countryGroups = new CountryGroups();
		countryGroups.setGroupId(1);
		countryGroups.setGroupName("World");
		countryGroups.setGroupUid("GLO");
		countryGroups.setGroupScore(22.5);
		countryGroups.setGroupWeightedScore(1.7);
		countryGroupsList.add(countryGroups);
		dataMap.put(MAPGROUPS, countryGroupsList);
		expectedMap.put("2016", dataMap);

		Map<String, Object> map = iphService.getKpiResultListForMap(268, Locale.EN);
		assertEquals(expectedMap, map);
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

		List<KpiResults> kpiResultList = iphService.getAllKpiResultList(11, "AE", 2016, Locale.EN);
		assertEquals(expectedkpiResultList, kpiResultList);

	}

	@Test
	public void testGetAllKpiForCountries() {

		KpisForSomeCountriesDomain kpisForSomeCountriesDomain = new KpisForSomeCountriesDomain();
		kpisForSomeCountriesDomain.setCountryId("AE");
		kpisForSomeCountriesDomain.setCountryName("United Arab Emirates");
		KpiScores kpiScore = new KpiScores();
		kpiScore.setData("22.5");
		kpiScore.setDataValue(22.5);
		kpiScore.setKpiId(268);
		kpiScore.setKpiName("Women parliamentary seats");
		kpiScore.setDenominator("Of total parl. seats");
		kpiScore.setScore(55);
		kpiScore.setWeightedScore(1.7);
		List<KpiScores> kpiScores = new ArrayList<KpiScores>();
		kpiScores.add(kpiScore);
		kpisForSomeCountriesDomain.setKpiScores(kpiScores);
		List<KpisForSomeCountriesDomain> expectedList = new ArrayList<KpisForSomeCountriesDomain>();
		expectedList.add(kpisForSomeCountriesDomain);

		List<String> countries = new ArrayList<String>();
		countries.add("AE");
		List<KpisForSomeCountriesDomain> kpisForSomeCountriesDomainList = iphService.getAllKpiForCountries(11,
				countries, 2016, Locale.EN);

		assertEquals(expectedList, kpisForSomeCountriesDomainList);
	}

	@Test
	public void testGetGroups() {

		CountryGroups countryGroups = new CountryGroups();
		countryGroups.setGroupId(1);
		countryGroups.setGroupName("World");
		countryGroups.setGroupUid("GLO");
		countryGroups.setGroupScore(22.5);
		countryGroups.setGroupWeightedScore(1.7);
		List<CountryGroups> expectedCountryGroupsList = new ArrayList<CountryGroups>();
		expectedCountryGroupsList.add(countryGroups);

		List<CountryGroups> countryGroupsList = iphService.getGroups(Locale.EN);
		assertEquals(expectedCountryGroupsList, countryGroupsList);
	}

	@Test
	public void testGetRankingsForCountryGroup() {
		CountryGroupRankingsDomain countryGroupRankingsDomain = new CountryGroupRankingsDomain();
		countryGroupRankingsDomain.setCountryId("AE");
		countryGroupRankingsDomain.setCountryName("United Arab Emirates");
		countryGroupRankingsDomain.setData("22.5");
		countryGroupRankingsDomain.setDataValue(22.5);
		countryGroupRankingsDomain.setKpiId(268);
		countryGroupRankingsDomain.setKpiName("Women parliamentary seats");
		countryGroupRankingsDomain.setDenominator("Of total parl. seats");
		countryGroupRankingsDomain.setScore(55);
		countryGroupRankingsDomain.setWeightedScore(1.7);
		countryGroupRankingsDomain.setYear(2016);
		List<CountryGroupRankingsDomain> expectedList = new ArrayList<CountryGroupRankingsDomain>();
		expectedList.add(countryGroupRankingsDomain);

		List<CountryGroupRankingsDomain> countryGroupRankingsDomainList = iphService.getRankingsForCountryGroup(268,
				2016, 1, Locale.EN);
		assertEquals(expectedList, countryGroupRankingsDomainList);
	}
	
	@Test
	public void testGetAllKpiResultListForKpi() {
		
		List<KpiResultForAllCountriesAllYear> expectedList = new ArrayList<KpiResultForAllCountriesAllYear>();
		KpiResultForAllCountriesAllYear kpiResultForAllCountriesAllYear = new KpiResultForAllCountriesAllYear();
		kpiResultForAllCountriesAllYear.setYear(2016);
		List<CountryGroupRankingsDomain> countryInfo = new ArrayList<CountryGroupRankingsDomain>();
		CountryGroupRankingsDomain countryGroupRankingsDomain = new CountryGroupRankingsDomain();
		countryGroupRankingsDomain.setCountryId("AE");
		countryGroupRankingsDomain.setCountryName("United Arab Emirates");
		countryGroupRankingsDomain.setData("22.5");
		countryGroupRankingsDomain.setDataValue(22.5);
		countryGroupRankingsDomain.setKpiId(268);
		countryGroupRankingsDomain.setKpiName("Women parliamentary seats");
		countryGroupRankingsDomain.setDenominator("Of total parl. seats");
		countryGroupRankingsDomain.setScore(55);
		countryGroupRankingsDomain.setWeightedScore(1.7);
		countryGroupRankingsDomain.setYear(2016);
		countryInfo.add(countryGroupRankingsDomain);
		kpiResultForAllCountriesAllYear.setCountryInfo(countryInfo);
		expectedList.add(kpiResultForAllCountriesAllYear);
		
		List<KpiResultForAllCountriesAllYear> allCountriesAllYears = iphService.getAllKpiResultListForKpi(268, Locale.EN);
		assertEquals(expectedList, allCountriesAllYears);
	}

	/**
	 * 
	 * @After(value = "") public void verify() {
	 *              Mockito.verify(countryGroupRepo,
	 *              VerificationModeFactory.times(1)).findOne(Mockito.anyInt());
	 *              Mockito.reset(countryGroupRepo);
	 * 
	 *              Mockito.verify(kpiResultRepo,
	 *              VerificationModeFactory.times(2)).findOne(Mockito.anyInt());
	 *              Mockito.reset(kpiResultRepo); }
	 */

}

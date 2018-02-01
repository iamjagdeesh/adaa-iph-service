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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.emc.ps.appmod.adaa.iph.constants.Locale;
import com.emc.ps.appmod.adaa.iph.domain.CountryGroups;
import com.emc.ps.appmod.adaa.iph.domain.GroupScoreAverage;
import com.emc.ps.appmod.adaa.iph.entities.CountryGroup;
import com.emc.ps.appmod.adaa.iph.repo.CountryGroupRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class CountryGroupServiceTest {
	
	@Autowired
	private CountryGroupService countryGroupService;
	
	@Autowired
	private CountryGroupRepo countryGroupRepo;
	
	@Configuration
	static class CountryGroupServiceTestContextConfiguration {
		
		@Bean
		public CountryGroupService countryGroupService() {
			return new CountryGroupServiceImpl();
		}
		
		@Bean
		public CountryGroupRepo countryGroupRepo() {
			return Mockito.mock(CountryGroupRepo.class);
		}
		
	}
	
	@Before
	public void setup() {
		
		List<CountryGroup> countryGroupEntityList = new ArrayList<CountryGroup>();
		for (int i = 1; i < 6; i++) {
			CountryGroup countryGroup = new CountryGroup();
			countryGroup.setId(i);
			if (i == 1) {
				countryGroup.setNameEn("World");
				countryGroup.setUid("GLO");
			}
			countryGroupEntityList.add(countryGroup);
		}
		Mockito.when(countryGroupRepo.findAll()).thenReturn(countryGroupEntityList);
		
	}
	
	@Test()
	public void testGetGroups() {
		List<CountryGroups> expectedCountryGroupList = new ArrayList<CountryGroups>();
		for (int i = 1; i < 6; i++) {
			CountryGroups countryGroups = new CountryGroups();
			countryGroups.setGroupId(i);
			if (i == 1) {
				countryGroups.setGroupName("World");
				countryGroups.setGroupUid("GLO");
			}
			countryGroups.setGroupScore(0.0);
			countryGroups.setGroupWeightedScore(0.0);
			expectedCountryGroupList.add(countryGroups);
		}
		List<CountryGroups> countryGroupsList = countryGroupService.getGroups(Locale.EN);
		assertEquals(expectedCountryGroupList, countryGroupsList);
	}
	
	@Test()
	public void testGetCountryGroupsMap() {
		Map<Integer, CountryGroups> expectedCountryGroupsMap = new HashMap<Integer, CountryGroups>();
		for (int i = 1; i < 6; i++) {
			CountryGroups countryGroups = new CountryGroups();
			countryGroups.setGroupId(i);
			if (i == 1) {
				countryGroups.setGroupName("World");
				countryGroups.setGroupUid("GLO");
			}
			countryGroups.setGroupScore(0.0);
			countryGroups.setGroupWeightedScore(0.0);
			expectedCountryGroupsMap.put(i, countryGroups);
		}
		Map<Integer, CountryGroups> countryGroupsMap = countryGroupService.getCountryGroupsMap(Locale.EN);
		assertEquals(expectedCountryGroupsMap, countryGroupsMap);
	}
	
	@Test()
	public void testGetGroupScoreAsMapByYear() {
		Map<Integer, List<CountryGroups>> expectedGroupScoreMap = new HashMap<Integer, List<CountryGroups>>();
		List<GroupScoreAverage> groupAverageForKpi = new ArrayList<GroupScoreAverage>();
		GroupScoreAverage groupScoreAverage = new GroupScoreAverage(16.0859296482412, 1, 2016, 268, 1.21653266331658);
		groupAverageForKpi.add(groupScoreAverage);
		CountryGroups countryGroups = new CountryGroups();
		countryGroups.setGroupId(1);
		countryGroups.setGroupName("World");
		countryGroups.setGroupUid("GLO");
		countryGroups.setGroupScore(16.09);
		countryGroups.setGroupWeightedScore(1.22);
		List<CountryGroups> countryGroupsList = new ArrayList<CountryGroups>();
		countryGroupsList.add(countryGroups);
		expectedGroupScoreMap.put(2016, countryGroupsList);
		Map<Integer, List<CountryGroups>> groupScoreMap = countryGroupService.getGroupScoreAsMapByYear(groupAverageForKpi, Locale.EN);
		assertEquals(expectedGroupScoreMap, groupScoreMap);
	}

}

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
import com.emc.ps.appmod.adaa.iph.domain.Countries;
import com.emc.ps.appmod.adaa.iph.domain.CountriesAlphabetsDomain;
import com.emc.ps.appmod.adaa.iph.entities.Country;
import com.emc.ps.appmod.adaa.iph.repo.CountryRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class CountryServiceTest {
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private CountryRepo countryRepo;
	
	@Configuration
	static class CountryServiceTestContextConfiguration {
		
		@Bean
		public CountryService countryService() {
			return new CountryServiceImpl();
		}
		
		@Bean
		public CountryRepo countryRepo() {
			return Mockito.mock(CountryRepo.class);
		}
		
	}
	
	@Before
	public void setup() {
		
		Country country = new Country();
		country.setId("AE");
		country.setNameEn("United Arab Emirates");
		country.setNameAr("الإمارات العربية المتحدة ");

		List<Country> countryEntityList = new ArrayList<Country>();
		countryEntityList.add(country);

		Mockito.when(countryRepo.findAll()).thenReturn(countryEntityList);

		Mockito.when(countryRepo.findOne("AE")).thenReturn(country);
		
	}
	
	@Test()
	public void testGetCountryList() {
		Countries countries = new Countries();
		countries.setCountryId("AE");
		countries.setCountryName("United Arab Emirates");
		List<Countries> expectedList = new ArrayList<Countries>();
		expectedList.add(countries);
		List<Countries> countriesList = countryService.getCountryList(Locale.EN);
		assertEquals(expectedList, countriesList);
	}
	
	@Test()
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
		List<CountriesAlphabetsDomain> list = countryService.getAlphabeticCountryList(Locale.EN);
		assertEquals(expectedList, list);
	}
	
	@Test()
	public void testGetCountry() {
		Countries expectedCountry = new Countries();
		expectedCountry.setCountryId("AE");
		expectedCountry.setCountryName("United Arab Emirates");
		Countries countries = countryService.getCountry("AE", Locale.EN);
		assertEquals(expectedCountry, countries);
	}
	
	@Test
	public void testConvertToDomain() {
		Countries expectedCountry = new Countries();
		expectedCountry.setCountryId("AE");
		expectedCountry.setCountryName("United Arab Emirates");
		Country country = new Country();
		country.setId("AE");
		country.setNameEn("United Arab Emirates");
		country.setNameAr("الإمارات العربية المتحدة ");
		Countries countries = countryService.convertToDomain(country, Locale.EN);
		assertEquals(expectedCountry, countries);
	}

}

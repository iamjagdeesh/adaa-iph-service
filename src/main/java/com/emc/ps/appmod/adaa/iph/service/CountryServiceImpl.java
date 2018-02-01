package com.emc.ps.appmod.adaa.iph.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.emc.ps.appmod.adaa.iph.constants.Locale;
import com.emc.ps.appmod.adaa.iph.domain.Countries;
import com.emc.ps.appmod.adaa.iph.domain.CountriesAlphabetsDomain;
import com.emc.ps.appmod.adaa.iph.domain.CountriesNew;
import com.emc.ps.appmod.adaa.iph.entities.Country;
import com.emc.ps.appmod.adaa.iph.repo.CountryRepo;

/**
 * This interface deals with countries object
 */
@Service
public class CountryServiceImpl implements CountryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryServiceImpl.class);

	@Autowired
	private CountryRepo countryRepo;

	/**
	 * This method returns the list of countries domain object
	 * 
	 * @param locale
	 * @return
	 */
	@Override
	@Cacheable("countryList")
	public List<Countries> getCountryList(Locale locale) {
		LOGGER.debug("Getting Country list");
		List<Country> countryEntityList = getCountryListFromRepo(locale);
		List<Countries> countryList = convertToCountryDomain(countryEntityList, locale);
		return countryList;
	}

	/**
	 * This method returns the list of countries grouped by first character of
	 * the countryName
	 * 
	 * @param locale
	 * @return
	 */
	@Override
	@Cacheable("alphabeticCountryList")
	public List<CountriesAlphabetsDomain> getAlphabeticCountryList(Locale locale) {
		LOGGER.debug("Getting Country list for alphabetic grouping!");
		List<Country> countryEntityList = getCountryListFromRepo(locale);
		List<CountriesAlphabetsDomain> groupedList = groupAlphabeticCountryList(countryEntityList, locale);
		return groupedList;
	}

	/**
	 * This method returns the country domain object given countryId
	 * 
	 * @param countryId
	 * @param locale
	 * @return
	 */
	@Override
	public Countries getCountry(String countryId, Locale locale) {
		LOGGER.debug("Getting Country for the given countryId!");
		Country countryEntity = countryRepo.findOne(countryId);
		Countries countryDomain = convertToDomain(countryEntity, locale);
		return countryDomain;
	}

	/**
	 * This method converts the country entity object into country domain object
	 * and returns it.
	 * 
	 * @param countryEntity
	 * @param locale
	 * @return
	 */
	@Override
	public Countries convertToDomain(Country countryEntity, Locale locale) {
		LOGGER.debug("Converting the country " + countryEntity.getNameEn() + " to domain country");
		Countries country = new Countries();
		country.setCountryId(countryEntity.getId());
		country.setShortName(countryEntity.getShortName());
		country.setCountryCode(countryEntity.getAlpha3());
		switch (locale) {
		case EN:
			country.setCountryName(countryEntity.getNameEn());
			country.setCountryNameOtherLang(countryEntity.getNameAr());
			break;
		case AR:
			country.setCountryName(countryEntity.getNameAr());
			country.setCountryNameOtherLang(countryEntity.getNameEn());
			break;
		}
		return country;
	}
	
	/**
	 * This method converts the country entity object into new country domain object
	 * and returns it.
	 * 
	 * @param countryEntity
	 * @param locale
	 * @return
	 */
	@Override
	public CountriesNew convertToNewDomain(Country countryEntity, Locale locale) {
		LOGGER.debug("Converting the country " + countryEntity.getNameEn() + " to domain country");
		CountriesNew countriesNew = new CountriesNew();
		countriesNew.setCountryId(countryEntity.getId());
		countriesNew.setCountryNameEn(countryEntity.getNameEn());
		countriesNew.setCountryNameAr(countryEntity.getNameAr());
		countriesNew.setShortName(countryEntity.getShortName());
		countriesNew.setCountryCode(countryEntity.getAlpha3());
		return countriesNew;
	}
	
	/**
	 * This method provides the count of countries
	 * 
	 * @return
	 */
	@Override
	public Integer getCountriesCount() {
		List<Country> countryEntityList = countryRepo.findAll();
		return countryEntityList.size();
	}

	/**
	 * This method calls the repo for countryList sorted based on locale
	 * 
	 * @param locale
	 * @return
	 */
	private List<Country> getCountryListFromRepo(Locale locale) {
		List<Country> countryEntityList = null;
		if (locale == Locale.EN) {
			countryEntityList = countryRepo.findAll();
		} else {
			countryEntityList = countryRepo.findAllArabic();
		}
		return countryEntityList;
	}

	/**
	 * This method converts the given list of country entity into list of
	 * country domain and return the same.
	 * 
	 * @param countryEntityList
	 * @param locale
	 * @return
	 */
	private List<Countries> convertToCountryDomain(List<Country> countryEntityList, Locale locale) {
		LOGGER.debug("Converting countryEntityList to domain list");
		List<Countries> countryList = new ArrayList<Countries>();
		for (Country countryEntity : countryEntityList) {
			Countries country = convertToDomain(countryEntity, locale);
			countryList.add(country);
		}
		return countryList;
	}

	/**
	 * This method converts the given list of country entity into list of
	 * country domain but grouped by first character of the country name.
	 * 
	 * @param countryEntityList
	 * @param locale
	 * @return
	 */
	private List<CountriesAlphabetsDomain> groupAlphabeticCountryList(List<Country> countryEntityList, Locale locale) {
		LOGGER.debug("Converting the countryEntity list into CountriesAlphabetsDomain list!");
		List<CountriesAlphabetsDomain> countriesAlphaList = new ArrayList<CountriesAlphabetsDomain>();
		List<Countries> countriesList = null;

		// Temporary map to store the values in required format
		Map<String, List<Countries>> countriesMap = new HashMap<String, List<Countries>>();

		for (Country countryEntity : countryEntityList) {
			String firstChar = null;
			if (locale == Locale.EN) {
				firstChar = Character.toString(countryEntity.getNameEn().charAt(0));
			} else {
				firstChar = Character.toString(countryEntity.getNameAr().charAt(0));
			}

			if (null == countriesMap.get(firstChar)) {
				// If the first character is not yet added to map, then add it
				countriesList = new ArrayList<Countries>();
				countriesList.add(convertToDomain(countryEntity, locale));
				countriesMap.put(firstChar, countriesList);
			} else {
				countriesList = countriesMap.get(firstChar);
				countriesList.add(convertToDomain(countryEntity, locale));
				countriesMap.put(firstChar, countriesList);
			}
		}

		// Convert the map to list as per format required
		Set<String> keySet = countriesMap.keySet();
		for (String firstChar : keySet) {
			CountriesAlphabetsDomain countriesAlphabetsDomain = new CountriesAlphabetsDomain();
			countriesAlphabetsDomain.setAlphabet(firstChar.charAt(0));
			countriesAlphabetsDomain.setCountriesList(countriesMap.get(firstChar));
			countriesAlphaList.add(countriesAlphabetsDomain);
		}

		return countriesAlphaList;
	}

}

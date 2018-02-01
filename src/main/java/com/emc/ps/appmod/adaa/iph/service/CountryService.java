package com.emc.ps.appmod.adaa.iph.service;

import java.util.List;

import com.emc.ps.appmod.adaa.iph.constants.Locale;
import com.emc.ps.appmod.adaa.iph.domain.Countries;
import com.emc.ps.appmod.adaa.iph.domain.CountriesAlphabetsDomain;
import com.emc.ps.appmod.adaa.iph.domain.CountriesNew;
import com.emc.ps.appmod.adaa.iph.entities.Country;

/**
 * This interface deals with countries object
 */
public interface CountryService {

	/**
	 * This method returns the list of countries domain object
	 * 
	 * @param locale
	 * @return
	 */
	public List<Countries> getCountryList(Locale locale);

	/**
	 * This method returns the list of countries grouped by first character of
	 * the countryName
	 * 
	 * @param locale
	 * @return
	 */
	public List<CountriesAlphabetsDomain> getAlphabeticCountryList(Locale locale);

	/**
	 * This method returns the country domain object given countryId
	 * 
	 * @param countryId
	 * @param locale
	 * @return
	 */
	public Countries getCountry(String countryId, Locale locale);

	/**
	 * This method converts the country entity object into country domain object
	 * and returns it.
	 * 
	 * @param countryEntity
	 * @param locale
	 * @return
	 */
	public Countries convertToDomain(Country countryEntity, Locale locale);
	
	/**
	 * This method converts the country entity object into new country domain object
	 * and returns it.
	 * 
	 * @param countryEntity
	 * @param locale
	 * @return
	 */
	public CountriesNew convertToNewDomain(Country countryEntity, Locale locale);
	
	/**
	 * This method provides the count of countries
	 * 
	 * @return
	 */
	public Integer getCountriesCount();

}

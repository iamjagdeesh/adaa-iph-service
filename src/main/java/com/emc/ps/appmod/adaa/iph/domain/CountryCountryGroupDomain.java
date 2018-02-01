package com.emc.ps.appmod.adaa.iph.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class CountryCountryGroupDomain {

	CountryGroups countryGroups;
	
	List<Countries> countriesList;

	public CountryGroups getCountryGroups() {
		return countryGroups;
	}

	public void setCountryGroups(CountryGroups countryGroups) {
		this.countryGroups = countryGroups;
	}

	public List<Countries> getCountriesList() {
		return countriesList;
	}

	public void setCountriesList(List<Countries> countriesList) {
		this.countriesList = countriesList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countriesList == null) ? 0 : countriesList.hashCode());
		result = prime * result + ((countryGroups == null) ? 0 : countryGroups.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CountryCountryGroupDomain other = (CountryCountryGroupDomain) obj;
		if (countriesList == null) {
			if (other.countriesList != null)
				return false;
		} else if (!countriesList.equals(other.countriesList))
			return false;
		if (countryGroups == null) {
			if (other.countryGroups != null)
				return false;
		} else if (!countryGroups.equals(other.countryGroups))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}

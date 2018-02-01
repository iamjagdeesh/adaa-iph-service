package com.emc.ps.appmod.adaa.iph.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class CountriesAlphabetsDomain {
	
	Character alphabet;
	
	List<Countries> countriesList;

	public Character getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(Character alphabet) {
		this.alphabet = alphabet;
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
		result = prime * result + ((alphabet == null) ? 0 : alphabet.hashCode());
		result = prime * result + ((countriesList == null) ? 0 : countriesList.hashCode());
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
		CountriesAlphabetsDomain other = (CountriesAlphabetsDomain) obj;
		if (alphabet == null) {
			if (other.alphabet != null)
				return false;
		} else if (!alphabet.equals(other.alphabet))
			return false;
		if (countriesList == null) {
			if (other.countriesList != null)
				return false;
		} else if (!countriesList.equals(other.countriesList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}

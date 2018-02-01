package com.emc.ps.appmod.adaa.iph.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class KpiResultForAllCountriesAllYear {
	
	private Integer year;
	
	private List<CountryGroupRankingsDomain> countryInfo;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<CountryGroupRankingsDomain> getCountryInfo() {
		return countryInfo;
	}

	public void setCountryInfo(List<CountryGroupRankingsDomain> countryInfo) {
		this.countryInfo = countryInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryInfo == null) ? 0 : countryInfo.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		KpiResultForAllCountriesAllYear other = (KpiResultForAllCountriesAllYear) obj;
		if (countryInfo == null) {
			if (other.countryInfo != null)
				return false;
		} else if (!countryInfo.equals(other.countryInfo))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}

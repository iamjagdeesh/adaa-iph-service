package com.emc.ps.appmod.adaa.iph.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class KpisForSomeCountriesDomain {

	private String countryName;
	
	private String countryId;
	
	private String countryShortName;
	
	private List<KpiScores> kpiScores;

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryShortName() {
		return countryShortName;
	}

	public void setCountryShortName(String countryShortName) {
		this.countryShortName = countryShortName;
	}

	public List<KpiScores> getKpiScores() {
		return kpiScores;
	}

	public void setKpiScores(List<KpiScores> kpiScores) {
		this.kpiScores = kpiScores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryId == null) ? 0 : countryId.hashCode());
		result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
		result = prime * result + ((countryShortName == null) ? 0 : countryShortName.hashCode());
		result = prime * result + ((kpiScores == null) ? 0 : kpiScores.hashCode());
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
		KpisForSomeCountriesDomain other = (KpisForSomeCountriesDomain) obj;
		if (countryId == null) {
			if (other.countryId != null)
				return false;
		} else if (!countryId.equals(other.countryId))
			return false;
		if (countryName == null) {
			if (other.countryName != null)
				return false;
		} else if (!countryName.equals(other.countryName))
			return false;
		if (countryShortName == null) {
			if (other.countryShortName != null)
				return false;
		} else if (!countryShortName.equals(other.countryShortName))
			return false;
		if (kpiScores == null) {
			if (other.kpiScores != null)
				return false;
		} else if (!kpiScores.equals(other.kpiScores))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}

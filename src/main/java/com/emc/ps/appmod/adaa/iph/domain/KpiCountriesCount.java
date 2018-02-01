package com.emc.ps.appmod.adaa.iph.domain;

public class KpiCountriesCount {

	private Integer kpiId;

	private Integer countriesCount;

	public KpiCountriesCount(Integer kpiId, Long countriesCount) {
		this.kpiId = kpiId;
		if (null != countriesCount) {
			this.countriesCount = countriesCount.intValue();
		}
		else {
			this.countriesCount = 0;
		}

	}

	public Integer getKpiId() {
		return kpiId;
	}

	public void setKpiId(Integer kpiId) {
		this.kpiId = kpiId;
	}

	public Integer getCountriesCount() {
		return countriesCount;
	}

	public void setCountriesCount(Integer countriesCount) {
		this.countriesCount = countriesCount;
	}
}

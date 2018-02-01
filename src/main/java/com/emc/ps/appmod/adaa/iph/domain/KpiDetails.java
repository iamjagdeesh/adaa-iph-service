package com.emc.ps.appmod.adaa.iph.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * This class contains the KPI details for the Pillar and for the specific year
 * 
 * @author jawala
 *
 */
public class KpiDetails {

	private String pillarId;

	private String pillarName;

	private String kpiId;

	private String kpiName;

	private Integer year;

	private List<Kpi> kpis;

	public String getPillarId() {
		return pillarId;
	}

	public void setPillarId(String pillarId) {
		this.pillarId = pillarId;
	}

	public String getPillarName() {
		return pillarName;
	}

	public void setPillarName(String pillarName) {
		this.pillarName = pillarName;
	}

	public String getKpiId() {
		return kpiId;
	}

	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
	}

	public String getKpiName() {
		return kpiName;
	}

	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<Kpi> getKpis() {
		return kpis;
	}

	public void setKpis(List<Kpi> kpis) {
		this.kpis = kpis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kpiId == null) ? 0 : kpiId.hashCode());
		result = prime * result + ((kpiName == null) ? 0 : kpiName.hashCode());
		result = prime * result + ((kpis == null) ? 0 : kpis.hashCode());
		result = prime * result + ((pillarId == null) ? 0 : pillarId.hashCode());
		result = prime * result + ((pillarName == null) ? 0 : pillarName.hashCode());
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
		KpiDetails other = (KpiDetails) obj;
		if (kpiId == null) {
			if (other.kpiId != null)
				return false;
		} else if (!kpiId.equals(other.kpiId))
			return false;
		if (kpiName == null) {
			if (other.kpiName != null)
				return false;
		} else if (!kpiName.equals(other.kpiName))
			return false;
		if (kpis == null) {
			if (other.kpis != null)
				return false;
		} else if (!kpis.equals(other.kpis))
			return false;
		if (pillarId == null) {
			if (other.pillarId != null)
				return false;
		} else if (!pillarId.equals(other.pillarId))
			return false;
		if (pillarName == null) {
			if (other.pillarName != null)
				return false;
		} else if (!pillarName.equals(other.pillarName))
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

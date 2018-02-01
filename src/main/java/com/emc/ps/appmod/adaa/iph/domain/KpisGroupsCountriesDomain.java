/**
 * 
 */
package com.emc.ps.appmod.adaa.iph.domain;

import java.util.List;
import java.util.Map;

/**
 * @author bj3
 *
 */
public class KpisGroupsCountriesDomain {

	Map<Integer, KpiDomainForFullStats> kpiMap;

	Map<Integer, KpisForAllGroupsDomain> kpiGroupsmap;

	Map<String, KpisForAllCountriesDomain> kpiCountryMap;

	public Map<Integer, KpiDomainForFullStats> getKpiMap() {
		return kpiMap;
	}

	public void setKpiMap(Map<Integer, KpiDomainForFullStats> kpiMap) {
		this.kpiMap = kpiMap;
	}

	public Map<Integer, KpisForAllGroupsDomain> getKpiGroupsmap() {
		return kpiGroupsmap;
	}

	public void setKpiGroupsmap(Map<Integer, KpisForAllGroupsDomain> kpiGroupsmap) {
		this.kpiGroupsmap = kpiGroupsmap;
	}

	public Map<String, KpisForAllCountriesDomain> getKpiCountryMap() {
		return kpiCountryMap;
	}

	public void setKpiCountryMap(Map<String, KpisForAllCountriesDomain> kpiCountryMap) {
		this.kpiCountryMap = kpiCountryMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kpiCountryMap == null) ? 0 : kpiCountryMap.hashCode());
		result = prime * result + ((kpiGroupsmap == null) ? 0 : kpiGroupsmap.hashCode());
		result = prime * result + ((kpiMap == null) ? 0 : kpiMap.hashCode());
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
		KpisGroupsCountriesDomain other = (KpisGroupsCountriesDomain) obj;
		if (kpiCountryMap == null) {
			if (other.kpiCountryMap != null)
				return false;
		} else if (!kpiCountryMap.equals(other.kpiCountryMap))
			return false;
		if (kpiGroupsmap == null) {
			if (other.kpiGroupsmap != null)
				return false;
		} else if (!kpiGroupsmap.equals(other.kpiGroupsmap))
			return false;
		if (kpiMap == null) {
			if (other.kpiMap != null)
				return false;
		} else if (!kpiMap.equals(other.kpiMap))
			return false;
		return true;
	}

}

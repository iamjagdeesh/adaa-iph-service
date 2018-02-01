/**
 * 
 */
package com.emc.ps.appmod.adaa.iph.domain;

import java.util.List;

/**
 * @author bj3
 *
 */
public class KpisForAllGroupsDomain {
	
	private Integer groupId;
	
	private String groupName;
	
	private String groupUid;
	
	private List<KpiValue> kpiValues;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupUid() {
		return groupUid;
	}

	public void setGroupUid(String groupUid) {
		this.groupUid = groupUid;
	}

	public List<KpiValue> getKpiValues() {
		return kpiValues;
	}

	public void setKpiValues(List<KpiValue> kpiValues) {
		this.kpiValues = kpiValues;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result + ((groupUid == null) ? 0 : groupUid.hashCode());
		result = prime * result + ((kpiValues == null) ? 0 : kpiValues.hashCode());
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
		KpisForAllGroupsDomain other = (KpisForAllGroupsDomain) obj;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		if (groupUid == null) {
			if (other.groupUid != null)
				return false;
		} else if (!groupUid.equals(other.groupUid))
			return false;
		if (kpiValues == null) {
			if (other.kpiValues != null)
				return false;
		} else if (!kpiValues.equals(other.kpiValues))
			return false;
		return true;
	}

}

package com.emc.ps.appmod.adaa.iph.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class CountryGroups {

	private Integer groupId;

	private String groupUid;

	private String groupName;
	
	private Double groupScore;
	
	private Double groupWeightedScore;
	
	private String formattedGroupScore;
	
	public Double getGroupScore() {
		return groupScore;
	}

	public void setGroupScore(Double groupScore) {
		this.groupScore = groupScore;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupUid() {
		return groupUid;
	}

	public void setGroupUid(String groupUid) {
		this.groupUid = groupUid;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Double getGroupWeightedScore() {
		return groupWeightedScore;
	}

	public void setGroupWeightedScore(Double groupWeightedScore) {
		this.groupWeightedScore = groupWeightedScore;
	}

	public String getFormattedGroupScore() {
		return formattedGroupScore;
	}

	public void setFormattedGroupScore(String formattedGroupScore) {
		this.formattedGroupScore = formattedGroupScore;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result + ((groupScore == null) ? 0 : groupScore.hashCode());
		result = prime * result + ((groupUid == null) ? 0 : groupUid.hashCode());
		result = prime * result + ((groupWeightedScore == null) ? 0 : groupWeightedScore.hashCode());
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
		CountryGroups other = (CountryGroups) obj;
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
		if (groupScore == null) {
			if (other.groupScore != null)
				return false;
		} else if (!groupScore.equals(other.groupScore))
			return false;
		if (groupUid == null) {
			if (other.groupUid != null)
				return false;
		} else if (!groupUid.equals(other.groupUid))
			return false;
		if (groupWeightedScore == null) {
			if (other.groupWeightedScore != null)
				return false;
		} else if (!groupWeightedScore.equals(other.groupWeightedScore))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}

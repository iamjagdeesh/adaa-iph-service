package com.emc.ps.appmod.adaa.iph.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Subtopics {

	private Integer subTopicId;

	private Integer pillarId;

	private Integer topicId;

	private String topicName;
	
	private String nameOtherLang;

	public String getNameOtherLang() {
		return nameOtherLang;
	}

	public void setNameOtherLang(String nameOtherLang) {
		this.nameOtherLang = nameOtherLang;
	}

	private String descriptionLong;

	private String descriptionShort;
	
	private List<Kpi> kpiList;

	public Integer getSubTopicId() {
		return subTopicId;
	}

	public void setSubTopicId(Integer subTopicId) {
		this.subTopicId = subTopicId;
	}

	public Integer getPillarId() {
		return pillarId;
	}

	public void setPillarId(Integer pillarId) {
		this.pillarId = pillarId;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getDescriptionLong() {
		return descriptionLong;
	}

	public void setDescriptionLong(String descriptionLong) {
		this.descriptionLong = descriptionLong;
	}

	public String getDescriptionShort() {
		return descriptionShort;
	}

	public void setDescriptionShort(String descriptionShort) {
		this.descriptionShort = descriptionShort;
	}

	public List<Kpi> getKpiList() {
		return kpiList;
	}

	public void setKpiList(List<Kpi> kpiList) {
		this.kpiList = kpiList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descriptionLong == null) ? 0 : descriptionLong.hashCode());
		result = prime * result + ((descriptionShort == null) ? 0 : descriptionShort.hashCode());
		result = prime * result + ((kpiList == null) ? 0 : kpiList.hashCode());
		result = prime * result + ((pillarId == null) ? 0 : pillarId.hashCode());
		result = prime * result + ((subTopicId == null) ? 0 : subTopicId.hashCode());
		result = prime * result + ((topicId == null) ? 0 : topicId.hashCode());
		result = prime * result + ((topicName == null) ? 0 : topicName.hashCode());
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
		Subtopics other = (Subtopics) obj;
		if (descriptionLong == null) {
			if (other.descriptionLong != null)
				return false;
		} else if (!descriptionLong.equals(other.descriptionLong))
			return false;
		if (descriptionShort == null) {
			if (other.descriptionShort != null)
				return false;
		} else if (!descriptionShort.equals(other.descriptionShort))
			return false;
		if (kpiList == null) {
			if (other.kpiList != null)
				return false;
		} else if (!kpiList.equals(other.kpiList))
			return false;
		if (pillarId == null) {
			if (other.pillarId != null)
				return false;
		} else if (!pillarId.equals(other.pillarId))
			return false;
		if (subTopicId == null) {
			if (other.subTopicId != null)
				return false;
		} else if (!subTopicId.equals(other.subTopicId))
			return false;
		if (topicId == null) {
			if (other.topicId != null)
				return false;
		} else if (!topicId.equals(other.topicId))
			return false;
		if (topicName == null) {
			if (other.topicName != null)
				return false;
		} else if (!topicName.equals(other.topicName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}

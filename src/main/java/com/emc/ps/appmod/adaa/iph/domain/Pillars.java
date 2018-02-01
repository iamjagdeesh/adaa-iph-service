package com.emc.ps.appmod.adaa.iph.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Pillars {

	private Integer pillarId;

	private String pillarName;

	private String description;
	
	private String pillarNameOtherLang;

	private List<Subtopics> subTopicList;
	
	private Integer pillarsCount;
	
	private Integer kpisCount;
	
	private Integer countriesCount;
	
	public String getPillarNameOtherLang() {
		return pillarNameOtherLang;
	}

	public void setPillarNameOtherLang(String pillarNameOtherLang) {
		this.pillarNameOtherLang = pillarNameOtherLang;
	}

	public Integer getPillarId() {
		return pillarId;
	}

	public void setPillarId(Integer pillarId) {
		this.pillarId = pillarId;
	}

	public String getPillarName() {
		return pillarName;
	}

	public void setPillarName(String pillarName) {
		this.pillarName = pillarName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Subtopics> getSubTopicList() {
		return subTopicList;
	}

	public void setSubTopicList(List<Subtopics> subTopicList) {
		this.subTopicList = subTopicList;
	}

	public Integer getPillarsCount() {
		return pillarsCount;
	}

	public void setPillarsCount(Integer pillarsCount) {
		this.pillarsCount = pillarsCount;
	}

	public Integer getKpisCount() {
		return kpisCount;
	}

	public void setKpisCount(Integer kpisCount) {
		this.kpisCount = kpisCount;
	}

	public Integer getCountriesCount() {
		return countriesCount;
	}

	public void setCountriesCount(Integer countriesCount) {
		this.countriesCount = countriesCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countriesCount == null) ? 0 : countriesCount.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((kpisCount == null) ? 0 : kpisCount.hashCode());
		result = prime * result + ((pillarId == null) ? 0 : pillarId.hashCode());
		result = prime * result + ((pillarName == null) ? 0 : pillarName.hashCode());
		result = prime * result + ((pillarNameOtherLang == null) ? 0 : pillarNameOtherLang.hashCode());
		result = prime * result + ((pillarsCount == null) ? 0 : pillarsCount.hashCode());
		result = prime * result + ((subTopicList == null) ? 0 : subTopicList.hashCode());
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
		Pillars other = (Pillars) obj;
		if (countriesCount == null) {
			if (other.countriesCount != null)
				return false;
		} else if (!countriesCount.equals(other.countriesCount))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (kpisCount == null) {
			if (other.kpisCount != null)
				return false;
		} else if (!kpisCount.equals(other.kpisCount))
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
		if (pillarNameOtherLang == null) {
			if (other.pillarNameOtherLang != null)
				return false;
		} else if (!pillarNameOtherLang.equals(other.pillarNameOtherLang))
			return false;
		if (pillarsCount == null) {
			if (other.pillarsCount != null)
				return false;
		} else if (!pillarsCount.equals(other.pillarsCount))
			return false;
		if (subTopicList == null) {
			if (other.subTopicList != null)
				return false;
		} else if (!subTopicList.equals(other.subTopicList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}

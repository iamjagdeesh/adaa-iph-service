package com.emc.ps.appmod.adaa.iph.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * This class contains the KPI score for the country along with the color code
 * 
 * @author jawala
 *
 */
public class Kpi {

	private Integer id;

	private String name;
	
	private String nameOtherLang;
	
	private String denominator;
	
	private String descriptionLong;
	
	private String sourceName;
	
	private String frequency;

	private Integer year;
	
	private List<ScoresObject> scores;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNameOtherLang() {
		return nameOtherLang;
	}

	public void setNameOtherLang(String nameOtherLang) {
		this.nameOtherLang = nameOtherLang;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<ScoresObject> getScores() {
		return scores;
	}

	public void setScores(List<ScoresObject> scores) {
		this.scores = scores;
	}

	public String getDenominator() {
		return denominator;
	}

	public void setDenominator(String denominator) {
		this.denominator = denominator;
	}

	public String getDescriptionLong() {
		return descriptionLong;
	}

	public void setDescriptionLong(String descriptionLong) {
		this.descriptionLong = descriptionLong;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((denominator == null) ? 0 : denominator.hashCode());
		result = prime * result + ((descriptionLong == null) ? 0 : descriptionLong.hashCode());
		result = prime * result + ((frequency == null) ? 0 : frequency.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nameOtherLang == null) ? 0 : nameOtherLang.hashCode());
		result = prime * result + ((scores == null) ? 0 : scores.hashCode());
		result = prime * result + ((sourceName == null) ? 0 : sourceName.hashCode());
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
		Kpi other = (Kpi) obj;
		if (denominator == null) {
			if (other.denominator != null)
				return false;
		} else if (!denominator.equals(other.denominator))
			return false;
		if (descriptionLong == null) {
			if (other.descriptionLong != null)
				return false;
		} else if (!descriptionLong.equals(other.descriptionLong))
			return false;
		if (frequency == null) {
			if (other.frequency != null)
				return false;
		} else if (!frequency.equals(other.frequency))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nameOtherLang == null) {
			if (other.nameOtherLang != null)
				return false;
		} else if (!nameOtherLang.equals(other.nameOtherLang))
			return false;
		if (scores == null) {
			if (other.scores != null)
				return false;
		} else if (!scores.equals(other.scores))
			return false;
		if (sourceName == null) {
			if (other.sourceName != null)
				return false;
		} else if (!sourceName.equals(other.sourceName))
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

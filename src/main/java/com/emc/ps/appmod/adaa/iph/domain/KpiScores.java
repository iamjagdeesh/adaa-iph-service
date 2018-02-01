package com.emc.ps.appmod.adaa.iph.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class KpiScores {
	
	private Integer kpiId;
	
	private String kpiName;
	
	private String data;
	
	private Double dataValue;
	
	private Integer score;
	
	private Double weightedScore;
	
	private String denominator;
	
	private String descriptionLong;
	
	private String sourceName;
	
	private String frequency;
	
	private Integer countryCount;

	public Integer getKpiId() {
		return kpiId;
	}

	public void setKpiId(Integer kpiId) {
		this.kpiId = kpiId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Double getWeightedScore() {
		return weightedScore;
	}

	public void setWeightedScore(Double weightedScore) {
		this.weightedScore = weightedScore;
	}

	public String getKpiName() {
		return kpiName;
	}

	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
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
	
	public Double getDataValue() {
		return dataValue;
	}

	public void setDataValue(Double dataValue) {
		this.dataValue = dataValue;
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

	public Integer getCountryCount() {
		return countryCount;
	}

	public void setCountryCount(Integer countryCount) {
		this.countryCount = countryCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((dataValue == null) ? 0 : dataValue.hashCode());
		result = prime * result + ((denominator == null) ? 0 : denominator.hashCode());
		result = prime * result + ((descriptionLong == null) ? 0 : descriptionLong.hashCode());
		result = prime * result + ((kpiId == null) ? 0 : kpiId.hashCode());
		result = prime * result + ((kpiName == null) ? 0 : kpiName.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		result = prime * result + ((weightedScore == null) ? 0 : weightedScore.hashCode());
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
		KpiScores other = (KpiScores) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (dataValue == null) {
			if (other.dataValue != null)
				return false;
		} else if (!dataValue.equals(other.dataValue))
			return false;
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
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		if (weightedScore == null) {
			if (other.weightedScore != null)
				return false;
		} else if (!weightedScore.equals(other.weightedScore))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
}

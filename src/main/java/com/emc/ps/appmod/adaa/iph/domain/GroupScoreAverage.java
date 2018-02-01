package com.emc.ps.appmod.adaa.iph.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class GroupScoreAverage {

    private Double average;
    
    private Double averageColor;

    private Integer groupId;

    private Integer year;

    private Integer kpiId;
    
    private String formattedAverage;

	public GroupScoreAverage(Double average, Integer groupId, Integer year, Integer kpiId, Double averageColor) {
        super();
        this.average = average;
        this.groupId = groupId;
        this.year = year;
        this.kpiId = kpiId;
        this.averageColor = averageColor;
    }

    public GroupScoreAverage(Double average, Integer groupId, Integer year, Double averageColor) {
        super();
        this.average = average;
        this.groupId = groupId;
        this.year = year;
        this.averageColor = averageColor;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getKpiId() {
        return kpiId;
    }

    public void setKpiId(Integer kpiId) {
        this.kpiId = kpiId;
    }

    public Double getAverageColor() {
		return averageColor;
	}

	public void setAverageColor(Double averageColor) {
		this.averageColor = averageColor;
	}

	public String getFormattedAverage() {
		return formattedAverage;
	}

	public void setFormattedAverage(String formattedAverage) {
		this.formattedAverage = formattedAverage;
	}
	
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((average == null) ? 0 : average.hashCode());
		result = prime * result + ((averageColor == null) ? 0 : averageColor.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((kpiId == null) ? 0 : kpiId.hashCode());
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
		GroupScoreAverage other = (GroupScoreAverage) obj;
		if (average == null) {
			if (other.average != null)
				return false;
		} else if (!average.equals(other.average))
			return false;
		if (averageColor == null) {
			if (other.averageColor != null)
				return false;
		} else if (!averageColor.equals(other.averageColor))
			return false;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		if (kpiId == null) {
			if (other.kpiId != null)
				return false;
		} else if (!kpiId.equals(other.kpiId))
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

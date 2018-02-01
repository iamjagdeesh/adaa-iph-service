/**
 * 
 */
package com.emc.ps.appmod.adaa.iph.domain;

/**
 * @author bj3
 *
 */
public class KpisNew {
	
	private Integer id;
	
	private String nameEn;
	
	private String nameAr;
	
	private String denominator;
	
	private String descriptionLong;
	
	private String desiredValue;
	
	private String sourceName;
	
	private String frequency;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNameAr() {
		return nameAr;
	}

	public void setNameAr(String nameAr) {
		this.nameAr = nameAr;
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

	public String getDesiredValue() {
		return desiredValue;
	}

	public void setDesiredValue(String desiredValue) {
		this.desiredValue = desiredValue;
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
		result = prime * result + ((desiredValue == null) ? 0 : desiredValue.hashCode());
		result = prime * result + ((frequency == null) ? 0 : frequency.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nameAr == null) ? 0 : nameAr.hashCode());
		result = prime * result + ((nameEn == null) ? 0 : nameEn.hashCode());
		result = prime * result + ((sourceName == null) ? 0 : sourceName.hashCode());
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
		KpisNew other = (KpisNew) obj;
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
		if (desiredValue == null) {
			if (other.desiredValue != null)
				return false;
		} else if (!desiredValue.equals(other.desiredValue))
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
		if (nameAr == null) {
			if (other.nameAr != null)
				return false;
		} else if (!nameAr.equals(other.nameAr))
			return false;
		if (nameEn == null) {
			if (other.nameEn != null)
				return false;
		} else if (!nameEn.equals(other.nameEn))
			return false;
		if (sourceName == null) {
			if (other.sourceName != null)
				return false;
		} else if (!sourceName.equals(other.sourceName))
			return false;
		return true;
	}

}

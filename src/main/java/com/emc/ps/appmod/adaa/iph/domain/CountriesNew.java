/**
 * 
 */
package com.emc.ps.appmod.adaa.iph.domain;

/**
 * @author bj3
 *
 */
public class CountriesNew {
	
	private String countryId;

	private String shortName;

	private String countryNameEn;
	
	private String countryNameAr;
	
	private String countryCode;

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getCountryNameEn() {
		return countryNameEn;
	}

	public void setCountryNameEn(String countryNameEn) {
		this.countryNameEn = countryNameEn;
	}

	public String getCountryNameAr() {
		return countryNameAr;
	}

	public void setCountryNameAr(String countryNameAr) {
		this.countryNameAr = countryNameAr;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + ((countryId == null) ? 0 : countryId.hashCode());
		result = prime * result + ((countryNameAr == null) ? 0 : countryNameAr.hashCode());
		result = prime * result + ((countryNameEn == null) ? 0 : countryNameEn.hashCode());
		result = prime * result + ((shortName == null) ? 0 : shortName.hashCode());
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
		CountriesNew other = (CountriesNew) obj;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
			return false;
		if (countryId == null) {
			if (other.countryId != null)
				return false;
		} else if (!countryId.equals(other.countryId))
			return false;
		if (countryNameAr == null) {
			if (other.countryNameAr != null)
				return false;
		} else if (!countryNameAr.equals(other.countryNameAr))
			return false;
		if (countryNameEn == null) {
			if (other.countryNameEn != null)
				return false;
		} else if (!countryNameEn.equals(other.countryNameEn))
			return false;
		if (shortName == null) {
			if (other.shortName != null)
				return false;
		} else if (!shortName.equals(other.shortName))
			return false;
		return true;
	}
	
}

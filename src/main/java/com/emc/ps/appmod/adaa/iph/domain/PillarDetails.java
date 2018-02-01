package com.emc.ps.appmod.adaa.iph.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class PillarDetails {

	private List<Pillars> pillarList;

	public List<Pillars> getPillarList() {
		return pillarList;
	}

	public void setPillarList(List<Pillars> pillarList) {
		this.pillarList = pillarList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pillarList == null) ? 0 : pillarList.hashCode());
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
		PillarDetails other = (PillarDetails) obj;
		if (pillarList == null) {
			if (other.pillarList != null)
				return false;
		} else if (!pillarList.equals(other.pillarList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}

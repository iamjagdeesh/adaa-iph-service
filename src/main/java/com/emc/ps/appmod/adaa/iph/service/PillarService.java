package com.emc.ps.appmod.adaa.iph.service;

import java.util.List;

import com.emc.ps.appmod.adaa.iph.constants.Locale;
import com.emc.ps.appmod.adaa.iph.domain.Pillars;

/**
 * This class deals with pillar data
 * 
 * @author bj3
 *
 */
public interface PillarService {

	/**
	 * This method returns the list of pillar domain
	 * 
	 * @param locale
	 * @return
	 */
	public List<Pillars> getPillarList(Locale locale);

}

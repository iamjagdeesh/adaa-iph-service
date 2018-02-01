/**
 * 
 */
package com.emc.ps.appmod.adaa.iph.service;

import java.util.List;

import com.emc.ps.appmod.adaa.iph.constants.Locale;
import com.emc.ps.appmod.adaa.iph.entities.KpiResult;

/**
 * @author bj3
 *
 */
public interface KpiResultService {

	/**
	 * This method return the kpiResultList for the given kpiId from kpiResults
	 * 
	 * @param kpiId
	 * @param locale
	 * @return
	 */
	public List<KpiResult> getKpiResultListByKpiId(Integer kpiId, Locale locale);

}

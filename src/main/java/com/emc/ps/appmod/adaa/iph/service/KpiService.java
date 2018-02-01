package com.emc.ps.appmod.adaa.iph.service;

import java.util.List;

import com.emc.ps.appmod.adaa.iph.constants.Locale;
import com.emc.ps.appmod.adaa.iph.domain.Kpi;
import com.emc.ps.appmod.adaa.iph.domain.KpiDomainForFullStats;
import com.emc.ps.appmod.adaa.iph.domain.Kpis;
import com.emc.ps.appmod.adaa.iph.domain.KpisNew;

/**
 * This class deals with kpi data
 * 
 * @author bj3
 *
 */
public interface KpiService {

	/**
	 * This method returns the list of kpi under the given subtopic
	 * 
	 * @param subtopicId
	 * @param locale
	 * @return
	 */
	public List<Kpi> getKpisFromSubtopic(Integer subtopicId, Locale locale);

	/**
	 * This method returns kpis domain object for the given kpiId
	 * 
	 * @param kpiId
	 * @param locale
	 * @return
	 */
	public Kpis getKpis(Integer kpiId, Locale locale);

	/**
	 * This method returns the Kpis domain object for the given kpi entity
	 * 
	 * @param kpi
	 * @param locale
	 * @return
	 */
	public Kpis convertToDomainKpis(com.emc.ps.appmod.adaa.iph.entities.Kpi kpi, Locale locale);
	
	/**
	 * This method returns new kpis domain object for the given kpiId
	 * 
	 * @param kpiId
	 * @param locale
	 * @return
	 */
	public KpisNew getKpisNew(Integer kpiId, Locale locale);
	
	/**
	 * This method returns the list of kpis under the given pillar
	 * 
	 * @param pillarId
	 * @param locale
	 * @return
	 */
	public List<KpiDomainForFullStats> getKpisFromPillarId(Integer pillarId, Locale locale);
	
	/**
	 * This method returns the count of all kpis
	 * 
	 * @return
	 */
	public Integer getCountOfKpis();

}

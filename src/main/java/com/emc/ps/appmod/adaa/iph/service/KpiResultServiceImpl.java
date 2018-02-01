/**
 * 
 */
package com.emc.ps.appmod.adaa.iph.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emc.ps.appmod.adaa.iph.constants.Locale;
import com.emc.ps.appmod.adaa.iph.domain.KpiResults;
import com.emc.ps.appmod.adaa.iph.entities.KpiResult;
import com.emc.ps.appmod.adaa.iph.repo.KpiResultRepo;

/**
 * @author bj3
 *
 */
@Service
public class KpiResultServiceImpl implements KpiResultService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KpiResultServiceImpl.class);
	
	@Autowired
	private KpiResultRepo kpiResultRepo;

	/**
	 * This method return the kpiResultList for the given kpiId from kpiResults
	 * 
	 * @param kpiId
	 * @param locale
	 * @return
	 */
	@Override
	public List<KpiResult> getKpiResultListByKpiId(Integer kpiId, Locale locale) {
		
		LOGGER.debug("Getting kpiResultList for kpiId : "+kpiId);
		List<KpiResult> kpiResultEntityList = kpiResultRepo.findByKpiId(kpiId);
		return kpiResultEntityList;
	}
	
}

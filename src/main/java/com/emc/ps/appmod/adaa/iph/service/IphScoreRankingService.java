package com.emc.ps.appmod.adaa.iph.service;

public interface IphScoreRankingService {

	public void computeColorCodeAndRanking();

    void populateDefaultData();
    
    public String truncateNumber(double data);
}

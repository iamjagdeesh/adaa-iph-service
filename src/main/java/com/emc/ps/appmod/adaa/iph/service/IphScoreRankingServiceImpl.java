package com.emc.ps.appmod.adaa.iph.service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emc.ps.appmod.adaa.iph.entities.Country;
import com.emc.ps.appmod.adaa.iph.entities.CountryGroup;
import com.emc.ps.appmod.adaa.iph.entities.Kpi;
import com.emc.ps.appmod.adaa.iph.entities.KpiResult;
import com.emc.ps.appmod.adaa.iph.entities.KpiScoreNormalized;
import com.emc.ps.appmod.adaa.iph.repo.CountryGroupRepo;
import com.emc.ps.appmod.adaa.iph.repo.CountryRepo;
import com.emc.ps.appmod.adaa.iph.repo.KpiRepo;
import com.emc.ps.appmod.adaa.iph.repo.KpiResultRepo;
import com.emc.ps.appmod.adaa.iph.repo.KpiScoreNormalizedRepo;

@Service
public class IphScoreRankingServiceImpl implements IphScoreRankingService {

    @Autowired
    private KpiRepo kpiRepo;

    @Autowired
    private KpiResultRepo kpiResultRepo;

    @Autowired
    private KpiScoreNormalizedRepo kpiScoreNormalizedRepo;

    @Autowired
    private CountryGroupRepo countryGroupRepo;

    @Autowired
    private CountryRepo countryRepo;

    @PersistenceContext
    private EntityManager em;

    private static String DESIRED_HIGH = "high";
    private static String DESIRED_LOW = "low";

    private static double MIMIMUM_LIMIT = 0;
    private static double MAXIMIM_LIMIT = 5;

    private static final Logger LOGGER = LoggerFactory.getLogger(IphScoreRankingServiceImpl.class);

    @Override
    public void computeColorCodeAndRanking() {

        // Truncate the table
        // kpiScoreNormalizedRepo.tuncateTable();
        if(kpiScoreNormalizedRepo.count() > 0){
    		LOGGER.debug("No need to run this as the rows are already present!");
    		return;
    	}

        // Get the list of KPIs
        List<Kpi> kpiList = kpiRepo.findAllKpis();

        //Kpi kpi1 = kpiRepo.findOne(1);  List<Kpi> kpiList = new ArrayList<Kpi>();     kpiList.add(kpi1);

        // Find the year ranges
        String yearMinMaxRes = kpiResultRepo.findMinAndMaxYearForKpi();
        String[] yearMinMaxResArr = yearMinMaxRes.split(",");
        Integer minYear = Integer.valueOf(yearMinMaxResArr[0]);
        Integer maxYear = Integer.valueOf(yearMinMaxResArr[1]);

        // Find country groups
        List<CountryGroup> countryGroupList = countryGroupRepo.findAll();

        // List of countries having no data
        // List<Country> countryList = countryRepo.findCountiresHavingNoData();

        // Find country groups
        CountryGroup defaultCountryGroup = countryGroupRepo.findOne(1);

        // Parallelize the processing
        ExecutorService service = Executors.newFixedThreadPool(20);
        List<Future<String>> futures = new ArrayList<Future<String>>();

        // Loop through all the years
        for (int year = minYear; year <= maxYear; year++) {

            final int yearTmp = year;

            // Loop through all the KPIs
            for (Kpi kpi : kpiList) {

                Callable<String> callable = new Callable<String>() {
                    public String call() throws Exception {

                        // For each KPI, get the KPI results
                        if (yearMinMaxResArr != null && yearMinMaxResArr.length > 0) {
                            computeNormalizedScore(countryGroupList, yearTmp, kpi);

                            // Find countries having no data for the given KPI
                            List<Country> countryList = countryRepo.findCountiresHavingNoData(kpi.getId());

                            populateDefaultData(countryList, defaultCountryGroup, yearTmp, kpi);
                        }
                        return null;
                    }
                };
                futures.add(service.submit(callable));
            }
        }
        service.shutdown();

    }

    /**
     * This method will populate the default data for countries for which no KPI is present.
     */
    @Override
    public void populateDefaultData() {
        List<Country> countryList = countryRepo.findCountiresHavingNoData(1);// TODO is this required?

        // Get the list of KPIs
        List<Kpi> kpiList = kpiRepo.findAllKpis();

        // Find country groups
        CountryGroup defaultCountryGroup = countryGroupRepo.findOne(1);

        // Find the year ranges
        String yearMinMaxRes = kpiResultRepo.findMinAndMaxYearForKpi();
        String[] yearMinMaxResArr = yearMinMaxRes.split(",");
        Integer minYear = Integer.valueOf(yearMinMaxResArr[0]);
        Integer maxYear = Integer.valueOf(yearMinMaxResArr[1]);

        // Loop through all the years
        for (int year = minYear; year <= maxYear; year++) {

            // Loop through all the KPIs
            for (Kpi kpi : kpiList) {
                // populateDefaultData(countryList, defaultCountryGroup, year, kpi);
            }
        }

    }

    private void populateDefaultData(List<Country> countryList, CountryGroup defaultCountryGroup, int year, Kpi kpi) {

        for (Country country : countryList) {
            // Persist the data
            KpiScoreNormalized kpiScoreNormalized = null;
            try {
                Double normalizedDataTmp = null;
                kpiScoreNormalized = new KpiScoreNormalized();
                kpiScoreNormalized.setColorRankNormalized(normalizedDataTmp);
                kpiScoreNormalized.setCountry(country);
                kpiScoreNormalized.setData(null);
                kpiScoreNormalized.setDataFormatted(null);
                kpiScoreNormalized.setKpi(kpi);
                kpiScoreNormalized.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                kpiScoreNormalized.setYear(year);
                kpiScoreNormalized.setRank(null);
                kpiScoreNormalized.setGroupUid("GLO");
                kpiScoreNormalized.setCountryGroup(defaultCountryGroup);
                kpiScoreNormalizedRepo.save(kpiScoreNormalized);
            } catch (Exception e) {
                LOGGER.error("===============================================================");
                LOGGER.error("Error when processing the scoring and raking {} ===== {}", e.getMessage(),
                        kpiScoreNormalized.toString());
                LOGGER.error(e.getMessage());
                LOGGER.error(kpiScoreNormalized.toString());
                LOGGER.error("===============================================================");
            }
        }

        // for other groups
        /*
         * for (Country country : countryList) { List<CountryCountryGroup> countryCountryGroups =
         * country.getCountryCountryGroups(); for(CountryCountryGroup countryCountryGroup : countryCountryGroups) { //
         * Persist the data KpiScoreNormalized kpiScoreNormalized = null; try { Double normalizedDataTmp = null;
         * kpiScoreNormalized = new KpiScoreNormalized(); kpiScoreNormalized.setColorRankNormalized(normalizedDataTmp);
         * kpiScoreNormalized.setCountry(country); kpiScoreNormalized.setData(null);
         * kpiScoreNormalized.setDataFormatted(null); kpiScoreNormalized.setKpi(kpi);
         * kpiScoreNormalized.setUpdatedAt(new Timestamp(System.currentTimeMillis())); kpiScoreNormalized.setYear(year);
         * kpiScoreNormalized.setRank(null);
         * kpiScoreNormalized.setGroupUid(countryCountryGroup.getCountryGroup().getNameEn());
         * kpiScoreNormalized.setCountryGroup(countryCountryGroup.getCountryGroup());
         * kpiScoreNormalizedRepo.save(kpiScoreNormalized); } catch (Exception e) {
         * LOGGER.error("==============================================================="); LOGGER.error(
         * "Error when processing the scoring and raking {} ===== {}", e.getMessage(), kpiScoreNormalized.toString());
         * LOGGER.error(e.getMessage()); LOGGER.error(kpiScoreNormalized.toString());
         * LOGGER.error("==============================================================="); } } }
         */

    }

    /**
     * This method will compute the normalized score, calculate ranking and persist the data.
     * 
     * @param countryGroupList
     * @param year
     * @param kpi
     */
    private void computeNormalizedScore(List<CountryGroup> countryGroupList, int year, Kpi kpi) {

        for (CountryGroup countryGroup : countryGroupList) {

            List<KpiResult> kpiResult = kpiResultRepo.findByKpiIdAndYearForGroupOrderByIdAsc(kpi.getId(), year,
                    countryGroup.getId());

            List<Double> kpiScores = kpiResultRepo.findOnlyScoresByKpiIdAndYearForGroupOrderByIdAsc(kpi.getId(), year,
                    countryGroup.getId());
            kpiScores.removeAll(Collections.singleton(null));

            // Proceed only when data is available
            Double minScore = null;
            Double maxScore = null;
            Map<Double, Integer> rankMap = null;
            if (!kpiScores.isEmpty()) {

                minScore = kpiScores.get(0);
                maxScore = kpiScores.get((kpiScores.size() - 1));

                minScore = formatToTwoDecimalPlaces(minScore);
                maxScore = formatToTwoDecimalPlaces(maxScore);

                rankMap = calculateRanking(kpiScores, kpi.getDesiredValue());
            }

            calculateNormalizedScoreAndPersist(kpiResult, minScore, maxScore, kpi.getDesiredValue(), rankMap,
                    countryGroup);

        }

    }

    /**
     * THis method will calculate the ranking of the scores
     * 
     * @param kpiScores
     * @param desiredValue
     * @return
     */
    private Map<Double, Integer> calculateRanking(List<Double> kpiScoresAllData, String desiredValue) {

        List<Double> kpiScoresFormatted = new ArrayList<Double>();
        for (Double kpiScore : kpiScoresAllData) {
            Double res = formatToTwoDecimalPlaces(kpiScore);
            kpiScoresFormatted.add(res);
        }

        Set<Double> kpiScoresUnique = new HashSet<Double>();
        kpiScoresUnique.addAll(kpiScoresFormatted);

        List<Double> kpiScores = new ArrayList<Double>(kpiScoresUnique);
        Collections.sort(kpiScores);

        double[][] ranked = new double[kpiScores.size()][2];
        Map<Double, Integer> rankMap = new HashMap<Double, Integer>();

        // Construct the initial array of data. This is a 2D array. First index
        // of the 2D array will have the score. 2nd index of 2D array will be
        // set to 0 as default initially.
        for (int i = 0; i < kpiScores.size(); i++) {
            Double kpiScore = kpiScores.get(i);
            // kpiScore = formatToTwoDecimalPlaces(kpiScore);
            ranked[i][0] = kpiScore;
        }

        // If desired value is high then list has to be sorted in ascending
        // order. else in descending order.
        if (desiredValue.equals(DESIRED_LOW)) {
            Collections.reverse(kpiScores);
        }

        // Loop through the 2D array and based onthe index, update the rank.
        // Rank will be the sorted index of the value.
        for (int i = 0; i < kpiScores.size(); i++) {
            for (int n = 0; n < kpiScores.size(); n++) {
                Double data = kpiScores.get(i);
                // data = formatToTwoDecimalPlaces(data);
                if (ranked[n][0] == data && ranked[n][1] == 0) {
                    ranked[n][1] = kpiScores.size() - i;
                    rankMap.put(ranked[n][0], kpiScores.size() - i);
                }
            }
        }
        return rankMap;
    }

    /**
     * This method will calculate the normalized score and persist the data.
     * 
     * @param kpiResultLst
     * @param minScore
     * @param maxScore
     * @param desiredValue
     * @param rankMap
     * @param countryGroup
     */
    private void calculateNormalizedScoreAndPersist(List<KpiResult> kpiResultLst, Double minScore, Double maxScore,
            String desiredValue, Map<Double, Integer> rankMap, CountryGroup countryGroup) {

        // Get the minimum and maximum rank
        List<Integer> rankKeyList = new ArrayList<Integer>();
        Integer minKey = 0;
        Integer maxKey = 0;
        try {

            if (null != rankMap) {
                Collection<Integer> rankKeys = rankMap.values();
                Set<Integer> rankKeyUnique = new HashSet<Integer>();
                rankKeyUnique.addAll(rankKeys);
                rankKeyList.addAll(rankKeyUnique);
                Collections.sort(rankKeyList);

                minKey = rankKeyList.get(0);
                if (!rankKeyList.isEmpty()) {
                    maxKey = rankKeyList.get(rankKeyList.size() - 1);
                }
            }
        } catch (Exception e) {
            LOGGER.error("==========================================");
            LOGGER.error("rank map is null {}", e.getMessage());
        }

        // Loop through each of the results to find the normalized score
        List<KpiScoreNormalized> list = null;
        for (KpiResult kpiResult : kpiResultLst) {
            list = new ArrayList<KpiScoreNormalized>();
            Double data = kpiResult.getData();
            Double formattedData = formatToTwoDecimalPlaces(data);

            // Check the desired value. If desired value is high or low.
            Double normalizedData = null;
            if (formattedData != null && rankMap != null) {
                if (desiredValue.equals(DESIRED_HIGH)) {
                    normalizedData = normalize(rankMap.get(formattedData), maxKey, minKey, MIMIMUM_LIMIT,
                            MAXIMIM_LIMIT);
                } else {
                    normalizedData = normalize(rankMap.get(formattedData), minKey, maxKey, MAXIMIM_LIMIT,
                            MIMIMUM_LIMIT);
                }
            }

            // Persist the data
            KpiScoreNormalized kpiScoreNormalized = null;
            try {
                Double normalizedDataTmp = formatToTwoDecimalPlaces(normalizedData);
                kpiScoreNormalized = new KpiScoreNormalized();
                kpiScoreNormalized.setColorRankNormalized(normalizedDataTmp);
                kpiScoreNormalized.setCountry(kpiResult.getCountry());
                kpiScoreNormalized.setData(data);
                kpiScoreNormalized.setDataFormatted(formatBigNumbers(formattedData));
                kpiScoreNormalized.setKpi(kpiResult.getKpi());
                kpiScoreNormalized.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                kpiScoreNormalized.setYear(kpiResult.getYear());
                if (null != rankMap) {
                    kpiScoreNormalized.setRank(rankMap.get(formattedData));
                }
                kpiScoreNormalized.setGroupUid(countryGroup.getUid());
                kpiScoreNormalized.setCountryGroup(countryGroup);
                // list.add(kpiScoreNormalized);
                kpiScoreNormalizedRepo.save(kpiScoreNormalized);
            } catch (Exception e) {
                LOGGER.error("===============================================================");
                LOGGER.error("Error when processing the scoring and raking {} ===== {}", e.getMessage(),
                        kpiScoreNormalized.toString());
                LOGGER.error(e.getMessage());
                LOGGER.error(kpiScoreNormalized.toString());
                LOGGER.error("===============================================================");
            }
        }

        /*
         * try { kpiScoreNormalizedRepo.save(list); } catch(Exception e) {
         * LOGGER.error("===============================================================");
         * LOGGER.error(e.getMessage());
         * LOGGER.error("==============================================================="); }
         */
    }

    /**
     * This will normalize the score into the given range.
     * 
     * @param data
     * @param minimumValueAvailable
     * @param maximumValueAvailable
     * @param mimimumLimit
     * @param maximumLimit
     * @return
     */
    private double normalize(final double data, final double minimumValueAvailable, final double maximumValueAvailable,
            final double mimimumLimit, final double maximumLimit) {

        if (minimumValueAvailable == maximumValueAvailable) {
            return 5;
        }
        return ((maximumLimit - mimimumLimit) * (data - minimumValueAvailable)
                / (maximumValueAvailable - minimumValueAvailable)) + mimimumLimit;
    }

    /**
     * This method will format the big numbers into millions / billions format.
     * 
     * @param data
     * @return
     */
    private String formatBigNumbers(Double data) {
        if (null != data) {
            return truncateNumber(data);
        }
        return null;
    }

    /**
     * This method will format the data to 2 decimal places.
     * 
     * @param data
     * @return
     */
    private Double formatToTwoDecimalPlaces(Double data) {
        if (null != data) {
            DecimalFormat df = new DecimalFormat("#.##");
            data = Double.valueOf(df.format(data));
        }
        return data;
    }

    private Float formatToTwoDecimalPlaces(Float data) {
        if (null != data) {
            DecimalFormat df = new DecimalFormat("#.##");
            data = Float.valueOf(df.format(data));
        }
        return data;
    }

    /**
     * This method will format the big numbers into millions / billions format.
     * 
     * @param data
     * @return
     */
    @Override
    public String truncateNumber(double data) {
        long million = 1000000L;
        long billion = 1000000000L;
        long trillion = 1000000000000L;
        long number = Math.round(data);
        boolean negative = false;
        if (number < 0) {
            number = Math.abs(number);
            negative = true;
        }

        /*
         * if ((number >= thousand) && (number < million)) { float fraction = calculateFraction(number, thousand);
         * return Float.toString(fraction) + "K"; } else
         */

        if ((number >= million) && (number < billion)) {
            float fraction = calculateFraction(number, million);
            fraction = formatToTwoDecimalPlaces(fraction);
            fraction = checkNegativeNumber(fraction, negative);
            return Float.toString(fraction) + "M";
        } else if ((number >= billion)) {
            float fraction = calculateFraction(number, billion);
            fraction = formatToTwoDecimalPlaces(fraction);
            fraction = checkNegativeNumber(fraction, negative);
            return Float.toString(fraction) + "B";
        }

        /*
         * if ((number >= million) && (number < billion)) { float fraction = calculateFraction(number, million);
         * fraction = formatToTwoDecimalPlaces(fraction); fraction = checkNegativeNumber(fraction, negative); return
         * Float.toString(fraction) + "M"; } else if ((number >= billion) && (number < trillion)) { float fraction =
         * calculateFraction(number, billion); fraction = formatToTwoDecimalPlaces(fraction); fraction =
         * checkNegativeNumber(fraction, negative); return Float.toString(fraction) + "B"; } else if (number >=
         * trillion) { float fraction = calculateFraction(number, trillion); fraction =
         * formatToTwoDecimalPlaces(fraction); fraction = checkNegativeNumber(fraction, negative); return
         * Float.toString(fraction) + "T"; }
         */

        String valStr = new DecimalFormat("#.##").format(data);
        return valStr;
    }

    private float checkNegativeNumber(float fraction, boolean negative) {
        if (negative) {
            fraction = fraction * -1;
        }
        return fraction;

    }

    public float calculateFraction(long number, long divisor) {
        long truncate = (number * 10L + (divisor / 2L)) / divisor;
        float fraction = (float) truncate * 0.10F;
        return fraction;
    }

}

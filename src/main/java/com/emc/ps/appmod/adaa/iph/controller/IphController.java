/**
 * 
 */
package com.emc.ps.appmod.adaa.iph.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emc.ps.appmod.adaa.iph.constants.Locale;
import com.emc.ps.appmod.adaa.iph.domain.Countries;
import com.emc.ps.appmod.adaa.iph.domain.CountriesAlphabetsDomain;
import com.emc.ps.appmod.adaa.iph.domain.CountryGroupRankingsDomain;
import com.emc.ps.appmod.adaa.iph.domain.CountryGroups;
import com.emc.ps.appmod.adaa.iph.domain.KpiResultForAllCountriesAllYear;
import com.emc.ps.appmod.adaa.iph.domain.KpiResults;
import com.emc.ps.appmod.adaa.iph.domain.KpisForAllCountriesDomain;
import com.emc.ps.appmod.adaa.iph.domain.KpisForSomeCountriesDomain;
import com.emc.ps.appmod.adaa.iph.domain.KpisGroupsCountriesDomain;
import com.emc.ps.appmod.adaa.iph.domain.Pillars;
import com.emc.ps.appmod.adaa.iph.domain.ScoresObject;
import com.emc.ps.appmod.adaa.iph.service.IphScoreRankingService;
import com.emc.ps.appmod.adaa.iph.service.IphService;

/**
 * @author jawala
 */
@CrossOrigin
@RestController
@RequestMapping("/iph")
public class IphController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IphController.class);

	@Autowired
	private IphService iphService;

	@Autowired
	private IphScoreRankingService iphScoreRankingService;

	@RequestMapping(value = "/info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getInfo() {
		return new ResponseEntity<String>("IPH Service is running!", HttpStatus.OK);
	}

	/**
	 * This API returns the list of pillar objects where each object contains
	 * SubtopicList and KpiList under that particular pillar
	 * 
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/pillarList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pillars>> getPillarList(@RequestParam("locale") String locale) {
		LOGGER.info("Getting Pillar list");
		Locale localeEnum = null;
		if (StringUtils.isEmpty(locale)) {
			// set default locale
			localeEnum = Locale.EN;
		} else {
			localeEnum = Locale.convert(locale);
		}
		List<Pillars> pillars = iphService.getPillarList(localeEnum);
		return new ResponseEntity<List<Pillars>>(pillars, HttpStatus.OK);
	}

	/**
	 * This API returns the list of countries
	 * 
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/countryList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Countries>> getCountryList(@RequestParam("locale") String locale) {
		LOGGER.info("Getting Country list");
		Locale localeEnum = null;
		if (StringUtils.isEmpty(locale)) {
			// set default locale
			localeEnum = Locale.EN;
		} else {
			localeEnum = Locale.convert(locale);
		}
		List<Countries> countryList = iphService.getCountryList(localeEnum);
		return new ResponseEntity<List<Countries>>(countryList, HttpStatus.OK);
	}

	/**
	 * This API returns the list of countries grouped by first character of the
	 * countryName
	 * 
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/countryAlphaList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CountriesAlphabetsDomain>> getAlphabeticCountryList(
			@RequestParam("locale") String locale) {
		LOGGER.info("Getting alphabetic Country group list");
		Locale localeEnum = null;
		if (StringUtils.isEmpty(locale)) {
			// set default locale
			localeEnum = Locale.EN;
		} else {
			localeEnum = Locale.convert(locale);
		}
		List<CountriesAlphabetsDomain> alphabeticCountryList = iphService.getAlphabeticCountryList(localeEnum);
		return new ResponseEntity<List<CountriesAlphabetsDomain>>(alphabeticCountryList, HttpStatus.OK);
	}

	/**
	 * This API returns the map of kpiscores of all countries, all years of a
	 * particular kpi in the format used to plot world map
	 * 
	 * @param kpiId
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/kpiScoreMap/{kpiId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getKpiResultListForMap(@PathVariable Integer kpiId,
			@RequestParam("locale") String locale) {
		LOGGER.info("Getting Kpi detail for worldMap!");
		Locale localeEnum = null;
		if (StringUtils.isEmpty(locale)) {
			// set default locale
			localeEnum = Locale.EN;
		} else {
			localeEnum = Locale.convert(locale);
		}
		Map<String, Object> kpi = iphService.getKpiResultListForMap(kpiId, localeEnum);
		return new ResponseEntity<Map<String, Object>>(kpi, HttpStatus.OK);
	}

	/**
	 * This API returns the list of kpi(All kpis under the pillar) result object
	 * where in each object will contain the kpi scores and other details
	 * 
	 * @param pillarId
	 * @param countryId
	 * @param year
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/kpisScore/pillar/{pillarId}/country/{countryId}/year/{year}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KpiResults>> getAllKpiResult(@PathVariable Integer pillarId,
			@RequestParam("locale") String locale, @PathVariable String countryId, @PathVariable Integer year) {
		LOGGER.info(
				"Getting KpiScores for all kpis under the pillar and for the particular country and particular year!");
		Locale localeEnum = null;
		if (StringUtils.isEmpty(locale)) {
			// set default locale
			localeEnum = Locale.EN;
		} else {
			localeEnum = Locale.convert(locale);
		}
		List<KpiResults> kpi = iphService.getAllKpiResultList(pillarId, countryId, year, localeEnum);
		return new ResponseEntity<List<KpiResults>>(kpi, HttpStatus.OK);
	}

	/**
	 * This API returns a list of objects where in each object will have details
	 * of the country and the scores associated with it
	 * 
	 * @param pillarId
	 * @param countries
	 * @param year
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/kpisScore/pillar/{pillarId}/year/{year}/countries/{countryIds}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KpisForSomeCountriesDomain>> getAllKpiForCountries(@PathVariable Integer pillarId,
			@RequestParam("locale") String locale, @PathVariable List<String> countryIds, @PathVariable Integer year) {
		LOGGER.debug(
				"Getting KpiScores for all kpis under the pillar and for the countries in the list and particular year!");
		Locale localeEnum = null;
		if (StringUtils.isEmpty(locale)) {
			// set default locale
			localeEnum = Locale.EN;
		} else {
			localeEnum = Locale.convert(locale);
		}
		List<KpisForSomeCountriesDomain> kpi = iphService.getAllKpiForCountries(pillarId, countryIds, year, localeEnum);
		return new ResponseEntity<List<KpisForSomeCountriesDomain>>(kpi, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/kpisValue/pillar/{pillarId}/year/{year}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KpisGroupsCountriesDomain> getAllKpiScoreForPillarAndYear(@PathVariable Integer pillarId,
			@RequestParam("locale") String locale, @PathVariable Integer year) {
		LOGGER.debug(
				"Getting KpiScores for all kpis under the pillar and for all countries and groups and particular year!");
		Locale localeEnum = null;
		if (StringUtils.isEmpty(locale)) {
			// set default locale
			localeEnum = Locale.EN;
		} else {
			localeEnum = Locale.convert(locale);
		}
		KpisGroupsCountriesDomain kpi = iphService.getAllKpiScoreForPillarAndYear(pillarId, year, localeEnum);
		return new ResponseEntity<KpisGroupsCountriesDomain>(kpi, HttpStatus.OK);
	}

	/**
	 * This API returns the list of countryGroups
	 * 
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/getGroups", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CountryGroups>> getGroups(@RequestParam("locale") String locale) {
		LOGGER.info("Getting the list of country groups");
		Locale localeEnum = null;
		if (StringUtils.isEmpty(locale)) {
			// set default locale
			localeEnum = Locale.EN;
		} else {
			localeEnum = Locale.convert(locale);
		}
		List<CountryGroups> kpi = iphService.getGroups(localeEnum);
		return new ResponseEntity<List<CountryGroups>>(kpi, HttpStatus.OK);
	}

	/**
	 * This API returns list of rank objects where in each object will have
	 * details of the country in the group and the scores
	 * 
	 * @param kpiId
	 * @param year
	 * @param countryGroupId
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/ranking/kpi/{kpiId}/year/{year}/countryGroup/{countryGroupId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CountryGroupRankingsDomain>> getKpiRankingForCountryGroup(@PathVariable Integer kpiId,
			@RequestParam("locale") String locale, @PathVariable Integer year, @PathVariable Integer countryGroupId) {
		LOGGER.info("Getting rankings for countryGroup");
		Locale localeEnum = null;
		if (StringUtils.isEmpty(locale)) {
			// set default locale
			localeEnum = Locale.EN;
		} else {
			localeEnum = Locale.convert(locale);
		}
		List<CountryGroupRankingsDomain> kpiScores = iphService.getRankingsForCountryGroup(kpiId, year, countryGroupId,
				localeEnum);
		return new ResponseEntity<List<CountryGroupRankingsDomain>>(kpiScores, HttpStatus.OK);
	}

	/**
	 * This API returns the list of kpi scores of all countries for all the
	 * years for a kpi
	 * 
	 * @param kpiId
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/kpiScoreAccordion/{kpiId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KpiResultForAllCountriesAllYear>> getkpiScoreAccordion(@PathVariable Integer kpiId,
			@RequestParam("locale") String locale) {
		LOGGER.info("Getting Kpi detail for a kpiId!");
		Locale localeEnum = null;
		if (StringUtils.isEmpty(locale)) {
			// set default locale
			localeEnum = Locale.EN;
		} else {
			localeEnum = Locale.convert(locale);
		}
		List<KpiResultForAllCountriesAllYear> kpi = iphService.getAllKpiResultListForKpi(kpiId, localeEnum);
		return new ResponseEntity<List<KpiResultForAllCountriesAllYear>>(kpi, HttpStatus.OK);
	}

	/**
	 * The API is used to compute the colorScore and rank for each kpiscore and
	 * store it in KpiScoreNormalized table for further use.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/rank", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> computeColorAndRank() {
		iphScoreRankingService.computeColorCodeAndRanking();
		return new ResponseEntity<String>("Done Computing!", HttpStatus.OK);
	}

	/**
	 * This api returns the result from kpiResults table for a given kpiId
	 * 
	 * @param kpiId
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/kpiScoreAll/{kpiId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getAllFromKpiResult(@PathVariable Integer kpiId,
			@RequestParam("locale") String locale) {
		LOGGER.info("Getting all kpi results for a particular kpiId");
		Locale localeEnum = null;
		if (StringUtils.isEmpty(locale)) {
			// set default locale
			localeEnum = Locale.EN;
		} else {
			localeEnum = Locale.convert(locale);
		}
		Map<String, Object> kpi = iphService.getKpiScoreAll(kpiId, localeEnum);
		return new ResponseEntity<Map<String, Object>>(kpi, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mKpiScoreAll/{kpiId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> mGetAllFromKpiScoreNormalized(@PathVariable Integer kpiId,
			@RequestParam("locale") String locale) {
		LOGGER.info("Getting all kpi results for a particular kpiId");
		Locale localeEnum = null;
		if (StringUtils.isEmpty(locale)) {
			// set default locale
			localeEnum = Locale.EN;
		} else {
			localeEnum = Locale.convert(locale);
		}
		Map<String, Object> kpi = iphService.mGetKpiScoreAll(kpiId, localeEnum);
		return new ResponseEntity<Map<String, Object>>(kpi, HttpStatus.OK);
	}

}

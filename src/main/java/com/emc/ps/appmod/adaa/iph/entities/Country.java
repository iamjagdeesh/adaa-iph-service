package com.emc.ps.appmod.adaa.iph.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the countries database table.
 * 
 */
@Entity
@Table(name="countries")
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="alpha_3")
	private String alpha3;

	@Column(name="created_at")
	private Timestamp createdAt;

	private String icon;

	@Column(name="is_calculated")
	private Integer isCalculated;

	@Column(name="is_wri")
	private Integer isWri;

	@Column(name="name_ar")
	private String nameAr;

	@Column(name="name_en")
	private String nameEn;

	@Column(name="short_name")
	private String shortName;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	//bi-directional many-to-one association to Currency
	@ManyToOne
	private Currency currency;

	//bi-directional many-to-one association to CountryCountryGroup
	@OneToMany(mappedBy="country")
	private List<CountryCountryGroup> countryCountryGroups;

	//bi-directional many-to-one association to KpiResult
	@OneToMany(mappedBy="country")
	private List<KpiResult> kpiResults;

	//bi-directional many-to-one association to KpiScoreNormalized
	@OneToMany(mappedBy="country")
	private List<KpiScoreNormalized> kpiScoreNormalizeds;

	public Country() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAlpha3() {
		return this.alpha3;
	}

	public void setAlpha3(String alpha3) {
		this.alpha3 = alpha3;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getIsCalculated() {
		return this.isCalculated;
	}

	public void setIsCalculated(Integer isCalculated) {
		this.isCalculated = isCalculated;
	}

	public Integer getIsWri() {
		return this.isWri;
	}

	public void setIsWri(Integer isWri) {
		this.isWri = isWri;
	}

	public String getNameAr() {
		return this.nameAr;
	}

	public void setNameAr(String nameAr) {
		this.nameAr = nameAr;
	}

	public String getNameEn() {
		return this.nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Currency getCurrency() {
		return this.currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public List<CountryCountryGroup> getCountryCountryGroups() {
		return this.countryCountryGroups;
	}

	public void setCountryCountryGroups(List<CountryCountryGroup> countryCountryGroups) {
		this.countryCountryGroups = countryCountryGroups;
	}

	public CountryCountryGroup addCountryCountryGroup(CountryCountryGroup countryCountryGroup) {
		getCountryCountryGroups().add(countryCountryGroup);
		countryCountryGroup.setCountry(this);

		return countryCountryGroup;
	}

	public CountryCountryGroup removeCountryCountryGroup(CountryCountryGroup countryCountryGroup) {
		getCountryCountryGroups().remove(countryCountryGroup);
		countryCountryGroup.setCountry(null);

		return countryCountryGroup;
	}

	public List<KpiResult> getKpiResults() {
		return this.kpiResults;
	}

	public void setKpiResults(List<KpiResult> kpiResults) {
		this.kpiResults = kpiResults;
	}

	public KpiResult addKpiResult(KpiResult kpiResult) {
		getKpiResults().add(kpiResult);
		kpiResult.setCountry(this);

		return kpiResult;
	}

	public KpiResult removeKpiResult(KpiResult kpiResult) {
		getKpiResults().remove(kpiResult);
		kpiResult.setCountry(null);

		return kpiResult;
	}

	public List<KpiScoreNormalized> getKpiScoreNormalizeds() {
		return this.kpiScoreNormalizeds;
	}

	public void setKpiScoreNormalizeds(List<KpiScoreNormalized> kpiScoreNormalizeds) {
		this.kpiScoreNormalizeds = kpiScoreNormalizeds;
	}

	public KpiScoreNormalized addKpiScoreNormalized(KpiScoreNormalized kpiScoreNormalized) {
		getKpiScoreNormalizeds().add(kpiScoreNormalized);
		kpiScoreNormalized.setCountry(this);

		return kpiScoreNormalized;
	}

	public KpiScoreNormalized removeKpiScoreNormalized(KpiScoreNormalized kpiScoreNormalized) {
		getKpiScoreNormalizeds().remove(kpiScoreNormalized);
		kpiScoreNormalized.setCountry(null);

		return kpiScoreNormalized;
	}

}
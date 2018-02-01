package com.emc.ps.appmod.adaa.iph.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the country_groups database table.
 * 
 */
@Entity
@Table(name="country_groups")
@NamedQuery(name="CountryGroup.findAll", query="SELECT c FROM CountryGroup c")
public class CountryGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Integer bad;

	@Column(name="created_at")
	private Timestamp createdAt;

	private Integer good;

	@Column(name="name_ar")
	private String nameAr;

	@Column(name="name_en")
	private String nameEn;

	@Column(name="to_improve")
	private Integer toImprove;

	private String uid;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	//bi-directional many-to-one association to CountryCountryGroup
	@OneToMany(mappedBy="countryGroup")
	private List<CountryCountryGroup> countryCountryGroups;

	//bi-directional many-to-one association to KpiScoreNormalized
	@OneToMany(mappedBy="countryGroup")
	private List<KpiScoreNormalized> kpiScoreNormalizeds;

	public CountryGroup() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBad() {
		return this.bad;
	}

	public void setBad(Integer bad) {
		this.bad = bad;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getGood() {
		return this.good;
	}

	public void setGood(Integer good) {
		this.good = good;
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

	public Integer getToImprove() {
		return this.toImprove;
	}

	public void setToImprove(Integer toImprove) {
		this.toImprove = toImprove;
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<CountryCountryGroup> getCountryCountryGroups() {
		return this.countryCountryGroups;
	}

	public void setCountryCountryGroups(List<CountryCountryGroup> countryCountryGroups) {
		this.countryCountryGroups = countryCountryGroups;
	}

	public CountryCountryGroup addCountryCountryGroup(CountryCountryGroup countryCountryGroup) {
		getCountryCountryGroups().add(countryCountryGroup);
		countryCountryGroup.setCountryGroup(this);

		return countryCountryGroup;
	}

	public CountryCountryGroup removeCountryCountryGroup(CountryCountryGroup countryCountryGroup) {
		getCountryCountryGroups().remove(countryCountryGroup);
		countryCountryGroup.setCountryGroup(null);

		return countryCountryGroup;
	}

	public List<KpiScoreNormalized> getKpiScoreNormalizeds() {
		return this.kpiScoreNormalizeds;
	}

	public void setKpiScoreNormalizeds(List<KpiScoreNormalized> kpiScoreNormalizeds) {
		this.kpiScoreNormalizeds = kpiScoreNormalizeds;
	}

	public KpiScoreNormalized addKpiScoreNormalized(KpiScoreNormalized kpiScoreNormalized) {
		getKpiScoreNormalizeds().add(kpiScoreNormalized);
		kpiScoreNormalized.setCountryGroup(this);

		return kpiScoreNormalized;
	}

	public KpiScoreNormalized removeKpiScoreNormalized(KpiScoreNormalized kpiScoreNormalized) {
		getKpiScoreNormalizeds().remove(kpiScoreNormalized);
		kpiScoreNormalized.setCountryGroup(null);

		return kpiScoreNormalized;
	}

}
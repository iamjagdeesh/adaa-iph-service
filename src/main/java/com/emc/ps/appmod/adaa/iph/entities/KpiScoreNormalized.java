package com.emc.ps.appmod.adaa.iph.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * The persistent class for the kpi_score_normalized database table.
 * 
 */
@Entity
@Table(name = "kpi_score_normalized")
@NamedQuery(name = "KpiScoreNormalized.findAll", query = "SELECT k FROM KpiScoreNormalized k")
public class KpiScoreNormalized implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "color_rank_normalized")
	private Double colorRankNormalized;

	@Column(name = "created_at")
	private Timestamp createdAt;

	private Double data;

	@Column(name = "data_formatted")
	private String dataFormatted;

	@Column(name = "group_uid")
	private String groupUid;

	private Integer rank;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	private Integer year;

	// bi-directional many-to-one association to Country
	@ManyToOne
	private Country country;

	// bi-directional many-to-one association to CountryGroup
	@ManyToOne
	@JoinColumn(name = "group_id")
	private CountryGroup countryGroup;

	// bi-directional many-to-one association to Kpi
	@ManyToOne
	private Kpi kpi;

	public KpiScoreNormalized() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getColorRankNormalized() {
		return this.colorRankNormalized;
	}

	public void setColorRankNormalized(Double colorRankNormalized) {
		this.colorRankNormalized = colorRankNormalized;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Double getData() {
		return this.data;
	}

	public void setData(Double data) {
		this.data = data;
	}

	public String getDataFormatted() {
		return dataFormatted;
	}

	public void setDataFormatted(String dataFormatted) {
		this.dataFormatted = dataFormatted;
	}

	public String getGroupUid() {
		return this.groupUid;
	}

	public void setGroupUid(String groupUid) {
		this.groupUid = groupUid;
	}

	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public CountryGroup getCountryGroup() {
		return this.countryGroup;
	}

	public void setCountryGroup(CountryGroup countryGroup) {
		this.countryGroup = countryGroup;
	}

	public Kpi getKpi() {
		return this.kpi;
	}

	public void setKpi(Kpi kpi) {
		this.kpi = kpi;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		KpiScoreNormalized other = (KpiScoreNormalized) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
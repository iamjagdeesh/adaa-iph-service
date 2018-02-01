package com.emc.ps.appmod.adaa.iph.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the kpi_results database table.
 * 
 */
@Entity
@Table(name="kpi_results")
@NamedQuery(name="KpiResult.findAll", query="SELECT k FROM KpiResult k")
public class KpiResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="created_at", nullable=false)
	private Timestamp createdAt;

	private Double data;

	@Column(name="deleted_at")
	private Timestamp deletedAt;

	private Double score;

	@Column(name="updated_at", nullable=false)
	private Timestamp updatedAt;

	@Column(name="weighted_score")
	private Double weightedScore;

	@Column(nullable=false)
	private Integer year;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="country_id", nullable=false)
	private Country country;

	//bi-directional many-to-one association to Kpi
	@ManyToOne
	@JoinColumn(name="kpi_id", nullable=false)
	private Kpi kpi;

	public KpiResult() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Timestamp getDeletedAt() {
		return this.deletedAt;
	}

	public void setDeletedAt(Timestamp deletedAt) {
		this.deletedAt = deletedAt;
	}

	public Double getScore() {
		return this.score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Double getWeightedScore() {
		return this.weightedScore;
	}

	public void setWeightedScore(Double weightedScore) {
		this.weightedScore = weightedScore;
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

	public Kpi getKpi() {
		return this.kpi;
	}

	public void setKpi(Kpi kpi) {
		this.kpi = kpi;
	}

}
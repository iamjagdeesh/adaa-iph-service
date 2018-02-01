package com.emc.ps.appmod.adaa.iph.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the lite_comparisons database table.
 * 
 */
@Entity
@Table(name="lite_comparisons")
@NamedQuery(name="LiteComparison.findAll", query="SELECT l FROM LiteComparison l")
public class LiteComparison implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="comparisonable_id", nullable=false, length=255)
	private String comparisonableId;

	@Column(name="comparisonable_type", nullable=false, length=100)
	private String comparisonableType;

	@Column(name="country_ar", nullable=false, length=255)
	private String countryAr;

	@Column(name="country_en", nullable=false, length=255)
	private String countryEn;

	@Column(name="country_id", nullable=false, length=255)
	private String countryId;

	@Column(name="created_at", nullable=false)
	private Timestamp createdAt;

	private Double data;

	@Column(name="formatted_data", length=255)
	private String formattedData;

	@Column(name="formatted_score", length=255)
	private String formattedScore;

	@Column(name="formatted_value", length=255)
	private String formattedValue;

	@Column(name="group_id", nullable=false)
	private Integer groupId;

	@Column(name="group_uid", nullable=false, length=255)
	private String groupUid;

	@Column(name="ordinal_suffix", nullable=false, length=255)
	private String ordinalSuffix;

	@Column(nullable=false)
	private Integer position;

	private Double score;

	@Column(name="subtopic_id")
	private Integer subtopicId;

	@Column(name="updated_at", nullable=false)
	private Timestamp updatedAt;

	private Double value;

	@Column(name="weighted_score")
	private Double weightedScore;

	@Column(nullable=false)
	private Integer year;

	public LiteComparison() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComparisonableId() {
		return comparisonableId;
	}

	public void setComparisonableId(String comparisonableId) {
		this.comparisonableId = comparisonableId;
	}

	public String getComparisonableType() {
		return comparisonableType;
	}

	public void setComparisonableType(String comparisonableType) {
		this.comparisonableType = comparisonableType;
	}

	public String getCountryAr() {
		return countryAr;
	}

	public void setCountryAr(String countryAr) {
		this.countryAr = countryAr;
	}

	public String getCountryEn() {
		return countryEn;
	}

	public void setCountryEn(String countryEn) {
		this.countryEn = countryEn;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Double getData() {
		return data;
	}

	public void setData(Double data) {
		this.data = data;
	}

	public String getFormattedData() {
		return formattedData;
	}

	public void setFormattedData(String formattedData) {
		this.formattedData = formattedData;
	}

	public String getFormattedScore() {
		return formattedScore;
	}

	public void setFormattedScore(String formattedScore) {
		this.formattedScore = formattedScore;
	}

	public String getFormattedValue() {
		return formattedValue;
	}

	public void setFormattedValue(String formattedValue) {
		this.formattedValue = formattedValue;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupUid() {
		return groupUid;
	}

	public void setGroupUid(String groupUid) {
		this.groupUid = groupUid;
	}

	public String getOrdinalSuffix() {
		return ordinalSuffix;
	}

	public void setOrdinalSuffix(String ordinalSuffix) {
		this.ordinalSuffix = ordinalSuffix;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getSubtopicId() {
		return subtopicId;
	}

	public void setSubtopicId(Integer subtopicId) {
		this.subtopicId = subtopicId;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getWeightedScore() {
		return weightedScore;
	}

	public void setWeightedScore(Double weightedScore) {
		this.weightedScore = weightedScore;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
package com.emc.ps.appmod.adaa.iph.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the kpis database table.
 * 
 */
@Entity
@Table(name="kpis")
@NamedQuery(name="Kpi.findAll", query="SELECT k FROM Kpi k")
public class Kpi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="additional_notes_ar")
	private String additionalNotesAr;

	@Column(name="additional_notes_en")
	private String additionalNotesEn;

	@Column(name="api_code")
	private String apiCode;

	@Column(name="can_be_extrapolated")
	private Integer canBeExtrapolated;

	private String color;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="denominator_ar")
	private String denominatorAr;

	@Column(name="denominator_en")
	private String denominatorEn;

	@Column(name="description_long_ar")
	private String descriptionLongAr;

	@Column(name="description_long_en")
	private String descriptionLongEn;

	@Column(name="desired_value")
	private String desiredValue;

	@Column(name="display_type")
	private String displayType;

	@Column(name="frequent_updated")
	private Integer frequentUpdated;

	private String icon;

	@Column(name="input_divisor")
	private Integer inputDivisor;

	@Column(name="input_multiplier")
	private Integer inputMultiplier;

	@Column(name="is_calculated_kpi")
	private Integer isCalculatedKpi;

	@Column(name="is_data_source")
	private Integer isDataSource;

	@Column(name="last_calculated_at")
	private Timestamp lastCalculatedAt;

	@Column(name="name_ar")
	private String nameAr;

	@Column(name="name_en")
	private String nameEn;

	private Integer order;

	@Column(name="original_ios_id")
	private String originalIosId;

	@Column(name="quantifier_ar")
	private String quantifierAr;

	@Column(name="quantifier_en")
	private String quantifierEn;

	@Column(name="red_flag")
	private Integer redFlag;

	@Column(name="saudi_only")
	private Integer saudiOnly;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	private String url;

	@Column(name="value_type_id")
	private Integer valueTypeId;

	private Integer visible;

	private BigDecimal weight;
	
	@Column(name="data_update_frequency")
	private String dataUpdateFrequency;
	
	@Column(name="data_update_frequency_ar")
	private String dataUpdateFrequencyAr;

	//bi-directional many-to-one association to KpiResult
	@OneToMany(mappedBy="kpi")
	private List<KpiResult> kpiResults;

	//bi-directional many-to-one association to KpiScoreNormalized
	@OneToMany(mappedBy="kpi")
	private List<KpiScoreNormalized> kpiScoreNormalizeds;

	//bi-directional many-to-one association to Source
	@ManyToOne
	private Source source;

	//bi-directional many-to-one association to Subtopic
	@ManyToOne
	private Subtopic subtopic;

	public Kpi() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdditionalNotesAr() {
		return this.additionalNotesAr;
	}

	public void setAdditionalNotesAr(String additionalNotesAr) {
		this.additionalNotesAr = additionalNotesAr;
	}

	public String getAdditionalNotesEn() {
		return this.additionalNotesEn;
	}

	public void setAdditionalNotesEn(String additionalNotesEn) {
		this.additionalNotesEn = additionalNotesEn;
	}

	public String getApiCode() {
		return this.apiCode;
	}

	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}

	public Integer getCanBeExtrapolated() {
		return this.canBeExtrapolated;
	}

	public void setCanBeExtrapolated(Integer canBeExtrapolated) {
		this.canBeExtrapolated = canBeExtrapolated;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getDenominatorAr() {
		return this.denominatorAr;
	}

	public void setDenominatorAr(String denominatorAr) {
		this.denominatorAr = denominatorAr;
	}

	public String getDenominatorEn() {
		return this.denominatorEn;
	}

	public void setDenominatorEn(String denominatorEn) {
		this.denominatorEn = denominatorEn;
	}

	public String getDescriptionLongAr() {
		return this.descriptionLongAr;
	}

	public void setDescriptionLongAr(String descriptionLongAr) {
		this.descriptionLongAr = descriptionLongAr;
	}

	public String getDescriptionLongEn() {
		return this.descriptionLongEn;
	}

	public void setDescriptionLongEn(String descriptionLongEn) {
		this.descriptionLongEn = descriptionLongEn;
	}

	public String getDesiredValue() {
		return this.desiredValue;
	}

	public void setDesiredValue(String desiredValue) {
		this.desiredValue = desiredValue;
	}

	public String getDisplayType() {
		return this.displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

	public Integer getFrequentUpdated() {
		return this.frequentUpdated;
	}

	public void setFrequentUpdated(Integer frequentUpdated) {
		this.frequentUpdated = frequentUpdated;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getInputDivisor() {
		return this.inputDivisor;
	}

	public void setInputDivisor(Integer inputDivisor) {
		this.inputDivisor = inputDivisor;
	}

	public Integer getInputMultiplier() {
		return this.inputMultiplier;
	}

	public void setInputMultiplier(Integer inputMultiplier) {
		this.inputMultiplier = inputMultiplier;
	}

	public Integer getIsCalculatedKpi() {
		return this.isCalculatedKpi;
	}

	public void setIsCalculatedKpi(Integer isCalculatedKpi) {
		this.isCalculatedKpi = isCalculatedKpi;
	}

	public Integer getIsDataSource() {
		return this.isDataSource;
	}

	public void setIsDataSource(Integer isDataSource) {
		this.isDataSource = isDataSource;
	}

	public Timestamp getLastCalculatedAt() {
		return this.lastCalculatedAt;
	}

	public void setLastCalculatedAt(Timestamp lastCalculatedAt) {
		this.lastCalculatedAt = lastCalculatedAt;
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

	public Integer getOrder() {
		return this.order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getOriginalIosId() {
		return this.originalIosId;
	}

	public void setOriginalIosId(String originalIosId) {
		this.originalIosId = originalIosId;
	}

	public String getQuantifierAr() {
		return this.quantifierAr;
	}

	public void setQuantifierAr(String quantifierAr) {
		this.quantifierAr = quantifierAr;
	}

	public String getQuantifierEn() {
		return this.quantifierEn;
	}

	public void setQuantifierEn(String quantifierEn) {
		this.quantifierEn = quantifierEn;
	}

	public Integer getRedFlag() {
		return this.redFlag;
	}

	public void setRedFlag(Integer redFlag) {
		this.redFlag = redFlag;
	}

	public Integer getSaudiOnly() {
		return this.saudiOnly;
	}

	public void setSaudiOnly(Integer saudiOnly) {
		this.saudiOnly = saudiOnly;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getValueTypeId() {
		return this.valueTypeId;
	}

	public void setValueTypeId(Integer valueTypeId) {
		this.valueTypeId = valueTypeId;
	}

	public Integer getVisible() {
		return this.visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public List<KpiResult> getKpiResults() {
		return this.kpiResults;
	}

	public void setKpiResults(List<KpiResult> kpiResults) {
		this.kpiResults = kpiResults;
	}

	public KpiResult addKpiResult(KpiResult kpiResult) {
		getKpiResults().add(kpiResult);
		kpiResult.setKpi(this);

		return kpiResult;
	}

	public KpiResult removeKpiResult(KpiResult kpiResult) {
		getKpiResults().remove(kpiResult);
		kpiResult.setKpi(null);

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
		kpiScoreNormalized.setKpi(this);

		return kpiScoreNormalized;
	}

	public KpiScoreNormalized removeKpiScoreNormalized(KpiScoreNormalized kpiScoreNormalized) {
		getKpiScoreNormalizeds().remove(kpiScoreNormalized);
		kpiScoreNormalized.setKpi(null);

		return kpiScoreNormalized;
	}

	public Source getSource() {
		return this.source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public Subtopic getSubtopic() {
		return this.subtopic;
	}

	public void setSubtopic(Subtopic subtopic) {
		this.subtopic = subtopic;
	}

	public String getDataUpdateFrequency() {
		return dataUpdateFrequency;
	}

	public void setDataUpdateFrequency(String dataUpdateFrequency) {
		this.dataUpdateFrequency = dataUpdateFrequency;
	}

	public String getDataUpdateFrequencyAr() {
		return dataUpdateFrequencyAr;
	}

	public void setDataUpdateFrequencyAr(String dataUpdateFrequencyAr) {
		this.dataUpdateFrequencyAr = dataUpdateFrequencyAr;
	}

	
}
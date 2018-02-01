package com.emc.ps.appmod.adaa.iph.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the subtopics database table.
 * 
 */
@Entity
@Table(name="subtopics")
@NamedQuery(name="Subtopic.findAll", query="SELECT s FROM Subtopic s")
public class Subtopic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	//@Lob
	@Column(name="additional_notes_ar", nullable=false)
	private String additionalNotesAr;

	//@Lob
	@Column(name="additional_notes_en", nullable=false)
	private String additionalNotesEn;

	@Column(nullable=false, length=255)
	private String color;

	@Column(name="created_at", nullable=false)
	private Timestamp createdAt;

	//@Lob
	@Column(name="denominator_ar", nullable=false)
	private String denominatorAr;

	//@Lob
	@Column(name="denominator_en", nullable=false)
	private String denominatorEn;

	//@Lob
	@Column(name="description_long_ar", nullable=false)
	private String descriptionLongAr;

	//@Lob
	@Column(name="description_long_en", nullable=false)
	private String descriptionLongEn;

	//@Lob
	@Column(name="description_short_ar", nullable=false)
	private String descriptionShortAr;

	//@Lob
	@Column(name="description_short_en", nullable=false)
	private String descriptionShortEn;

	@Column(name="desired_value", nullable=false, length=1)
	private String desiredValue;

	@Column(name="display_type", nullable=false, length=1)
	private String displayType;

	@Column(nullable=false, length=255)
	private String icon;

	@Column(name="last_calculated_at", nullable=false)
	private Timestamp lastCalculatedAt;

	@Column(name="name_ar", nullable=false, length=255)
	private String nameAr;

	@Column(name="name_en", nullable=false, length=255)
	private String nameEn;

	@Column(nullable=false)
	private int order;

	//@Lob
	@Column(name="quantifier_ar", nullable=false)
	private String quantifierAr;

	//@Lob
	@Column(name="quantifier_en", nullable=false)
	private String quantifierEn;

	@Column(name="red_flag", nullable=false)
	private boolean redFlag;

	@Column(name="updated_at", nullable=false)
	private Timestamp updatedAt;

	@Column(name="value_type_id", nullable=false)
	private int valueTypeId;

	@Column(precision=10, scale=5)
	private BigDecimal weight;

	//bi-directional many-to-one association to Kpi
	@OneToMany(mappedBy="subtopic")
	private List<Kpi> kpis;

	//bi-directional many-to-one association to Pillar
	@ManyToOne
	@JoinColumn(name="pillar_id")
	private Pillar pillar;

	//bi-directional many-to-one association to Topic
	@ManyToOne
	@JoinColumn(name="topic_id", nullable=false)
	private Topic topic;

	public Subtopic() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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

	public String getDescriptionShortAr() {
		return this.descriptionShortAr;
	}

	public void setDescriptionShortAr(String descriptionShortAr) {
		this.descriptionShortAr = descriptionShortAr;
	}

	public String getDescriptionShortEn() {
		return this.descriptionShortEn;
	}

	public void setDescriptionShortEn(String descriptionShortEn) {
		this.descriptionShortEn = descriptionShortEn;
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

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

	public int getOrder() {
		return this.order;
	}

	public void setOrder(int order) {
		this.order = order;
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

	public boolean getRedFlag() {
		return this.redFlag;
	}

	public void setRedFlag(boolean redFlag) {
		this.redFlag = redFlag;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getValueTypeId() {
		return this.valueTypeId;
	}

	public void setValueTypeId(int valueTypeId) {
		this.valueTypeId = valueTypeId;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public List<Kpi> getKpis() {
		return this.kpis;
	}

	public void setKpis(List<Kpi> kpis) {
		this.kpis = kpis;
	}

	public Kpi addKpi(Kpi kpi) {
		getKpis().add(kpi);
		kpi.setSubtopic(this);

		return kpi;
	}

	public Kpi removeKpi(Kpi kpi) {
		getKpis().remove(kpi);
		kpi.setSubtopic(null);

		return kpi;
	}

	public Pillar getPillar() {
		return this.pillar;
	}

	public void setPillar(Pillar pillar) {
		this.pillar = pillar;
	}

	public Topic getTopic() {
		return this.topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}
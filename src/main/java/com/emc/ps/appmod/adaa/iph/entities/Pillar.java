package com.emc.ps.appmod.adaa.iph.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the pillars database table.
 * 
 */
@Entity
@Table(name="pillars")
public class Pillar implements Serializable {

	private static final long serialVersionUID = -7281120844539708212L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	//@Lob
	@Column(name="additional_notes_ar")
	private String additionalNotesAr;

	//@Lob
	@Column(name="additional_notes_en")
	private String additionalNotesEn;

	@Column(length=255)
	private String color;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="css_class", length=255)
	private String cssClass;

	//@Lob
	@Column(name="denominator_ar")
	private String denominatorAr;

	//@Lob
	@Column(name="denominator_en")
	private String denominatorEn;

	//@Lob
	@Column(name="description_long_ar", nullable=false)
	private String descriptionLongAr;

	//@Lob
	@Column(name="description_long_en", nullable=false)
	private String descriptionLongEn;

	@Column(name="desired_value", length=1)
	private String desiredValue;

	@Column(name="display_type", length=1)
	private String displayType;

	@Column(length=255)
	private String icon;

	@Column(name="last_calculated_at")
	private Timestamp lastCalculatedAt;

	@Column(name="name_ar", nullable=false, length=255)
	private String nameAr;

	@Column(name="name_en", nullable=false, length=255)
	private String nameEn;

	@Column(name="order")
	private int order;

	//@Lob
	@Column(name="quantifier_ar")
	private String quantifierAr;

	//@Lob
	@Column(name="quantifier_en")
	private String quantifierEn;

	@Column(name="red_flag")
	private boolean redFlag;

	@Column(name="updated_at")
	private Timestamp updatedAt;

	@Column(name="value_type_id")
	private int valueTypeId;

	//bi-directional many-to-one association to Subtopic
	@OneToMany(mappedBy="pillar")
	private List<Subtopic> subtopics;

	public Pillar() {
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

	public String getCssClass() {
		return this.cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
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

	

	public List<Subtopic> getSubtopics() {
		return this.subtopics;
	}

	public void setSubtopics(List<Subtopic> subtopics) {
		this.subtopics = subtopics;
	}

	public Subtopic addSubtopic(Subtopic subtopic) {
		getSubtopics().add(subtopic);
		subtopic.setPillar(this);

		return subtopic;
	}

	public Subtopic removeSubtopic(Subtopic subtopic) {
		getSubtopics().remove(subtopic);
		subtopic.setPillar(null);

		return subtopic;
	}

}
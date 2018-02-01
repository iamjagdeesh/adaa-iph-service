package com.emc.ps.appmod.adaa.iph.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the topics database table.
 * 
 */
@Entity
@Table(name="topics")
@NamedQuery(name="Topic.findAll", query="SELECT t FROM Topic t")
public class Topic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="created_at", nullable=false)
	private Timestamp createdAt;

	@Column(name="name_ar", nullable=false, length=255)
	private String nameAr;

	@Column(name="name_en", nullable=false, length=255)
	private String nameEn;

	@Column(name="updated_at", nullable=false)
	private Timestamp updatedAt;

	//bi-directional many-to-one association to Subtopic
	@OneToMany(mappedBy="topic")
	private List<Subtopic> subtopics;

	public Topic() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
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

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Subtopic> getSubtopics() {
		return this.subtopics;
	}

	public void setSubtopics(List<Subtopic> subtopics) {
		this.subtopics = subtopics;
	}

	public Subtopic addSubtopic(Subtopic subtopic) {
		getSubtopics().add(subtopic);
		subtopic.setTopic(this);

		return subtopic;
	}

	public Subtopic removeSubtopic(Subtopic subtopic) {
		getSubtopics().remove(subtopic);
		subtopic.setTopic(null);

		return subtopic;
	}

}
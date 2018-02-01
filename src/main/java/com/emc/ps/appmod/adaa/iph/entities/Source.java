package com.emc.ps.appmod.adaa.iph.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the sources database table.
 * 
 */
@Entity
@Table(name="sources")
@NamedQuery(name="Source.findAll", query="SELECT s FROM Source s")
public class Source implements Serializable {
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

	//bi-directional many-to-one association to Kpi
	@OneToMany(mappedBy="source")
	private List<Kpi> kpis;

	public Source() {
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

	public List<Kpi> getKpis() {
		return this.kpis;
	}

	public void setKpis(List<Kpi> kpis) {
		this.kpis = kpis;
	}

	public Kpi addKpi(Kpi kpi) {
		getKpis().add(kpi);
		kpi.setSource(this);

		return kpi;
	}

	public Kpi removeKpi(Kpi kpi) {
		getKpis().remove(kpi);
		kpi.setSource(null);

		return kpi;
	}

}
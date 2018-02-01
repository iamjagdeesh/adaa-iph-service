package com.emc.ps.appmod.adaa.iph.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the country_country_group database table.
 * 
 */
@Entity
@Table(name="country_country_group")
@NamedQuery(name="CountryCountryGroup.findAll", query="SELECT c FROM CountryCountryGroup c")
public class CountryCountryGroup implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id", nullable=false)
	private Integer id;

	@Column(name="created_at", nullable=false)
	private Timestamp createdAt;

	@Column(name="updated_at", nullable=false)
	private Timestamp updatedAt;

	//bi-directional many-to-one association to CountryGroup
	@ManyToOne
	@JoinColumn(name="country_group_id", nullable=false)
	private CountryGroup countryGroup;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="country_id", nullable=false)
	private Country country;

	public CountryCountryGroup() {
	}

	public Integer getId() {
		return id;
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

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public CountryGroup getCountryGroup() {
		return this.countryGroup;
	}

	public void setCountryGroup(CountryGroup countryGroup) {
		this.countryGroup = countryGroup;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
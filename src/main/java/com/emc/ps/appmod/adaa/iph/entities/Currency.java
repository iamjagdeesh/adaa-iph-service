package com.emc.ps.appmod.adaa.iph.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the currencies database table.
 * 
 */
@Entity
@Table(name="currencies")
@NamedQuery(name="Currency.findAll", query="SELECT c FROM Currency c")
public class Currency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=255)
	private String code;

	@Column(name="name_ar", nullable=false, length=255)
	private String nameAr;

	@Column(name="name_en", nullable=false, length=255)
	private String nameEn;

	//bi-directional many-to-one association to Country
	@OneToMany(mappedBy="currency")
	private List<Country> countries;

	public Currency() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public List<Country> getCountries() {
		return this.countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public Country addCountry(Country country) {
		getCountries().add(country);
		country.setCurrency(this);

		return country;
	}

	public Country removeCountry(Country country) {
		getCountries().remove(country);
		country.setCurrency(null);

		return country;
	}

}
package com.hibernatedemo;

import javax.persistence.*;//Javax persistance.api denilen altyapıyla geliyor.Hibernate bunun implementasyonunu yapıyor.Resmi olarak bunu öneriyor Hibernate

@Entity   //Annotation yapıyoruz.
//Entity şuan bu city nesnesi bir entitydir yani bir veritabanı nesnesidir.
@Table(name="city")

public class City {
	//Nesne oluşturup bu nesneyi DB deki City tablosuyla eşleştiriyoruz. MAP ediyoruz...
	@Id //id o datayı diğerlerinden ayıracak alan anlamına geliyor.
	@Column(name="ID")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="countryCode")
	private String countryCode;
	@Column(name="district")
	private String district;
	@Column(name="population")
	private int population;    //City nesnesini veritabanındaki City tablosuyla map etmiş olduk...
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	

}

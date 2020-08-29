package com.sapient.revenue;

public class Revenue {
	
	private String city;
	private String country;
	private String gender;
	private String currency;
	private Integer income;
	
	
	
	
	public Revenue() {
		super();
	}
	public Revenue(String city, String country, String gender, String currency, Integer income) {
		super();
		this.city = city;
		this.country = country;
		this.gender = gender;
		this.currency = currency;
		this.income = income;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Integer getIncome() {
		return income;
	}
	public void setIncome(Integer income) {
		this.income = income;
	}
	
	

}

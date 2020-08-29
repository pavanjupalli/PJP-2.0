package com.sapient.revenue;

public class CountryRevenue {
	private String country;
	private String gender;
	private Double revenue;
	
	
	
	public CountryRevenue() {
		super();
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
	public Double getRevenue() {
		return revenue;
	}
	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return country+","+gender+","+revenue+"\n";
	}
	
	

}

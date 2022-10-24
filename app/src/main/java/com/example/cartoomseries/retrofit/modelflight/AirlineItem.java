package com.example.cartoomseries.retrofit.modelflight;

import com.google.gson.annotations.SerializedName;

public class AirlineItem{

	@SerializedName("established")
	private String established;

	@SerializedName("country")
	private String country;

	@SerializedName("website")
	private String website;

	@SerializedName("name")
	private String name;

	@SerializedName("head_quaters")
	private String headQuaters;

	@SerializedName("logo")
	private String logo;

	@SerializedName("id")
	private int id;

	@SerializedName("slogan")
	private String slogan;

	public void setEstablished(String established){
		this.established = established;
	}

	public String getEstablished(){
		return established;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setWebsite(String website){
		this.website = website;
	}

	public String getWebsite(){
		return website;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setHeadQuaters(String headQuaters){
		this.headQuaters = headQuaters;
	}

	public String getHeadQuaters(){
		return headQuaters;
	}

	public void setLogo(String logo){
		this.logo = logo;
	}

	public String getLogo(){
		return logo;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setSlogan(String slogan){
		this.slogan = slogan;
	}

	public String getSlogan(){
		return slogan;
	}

	@Override
	public String toString() {
		return "AirlineItem{" +
				"established='" + established + '\'' +
				", country='" + country + '\'' +
				", website='" + website + '\'' +
				", name='" + name + '\'' +
				", headQuaters='" + headQuaters + '\'' +
				", logo='" + logo + '\'' +
				", id=" + id +
				", slogan='" + slogan + '\'' +
				'}';
	}
}
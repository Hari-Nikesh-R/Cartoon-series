package com.example.cartoomseries.retrofit.modelflight;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PassengerDto {
	private int trips;
	private int V;
	private String name;
	private String id;
	private List<AirlineItem> airline;
	private String established;
	private String country;
	private String website;

	public String getEstablished() {
		return established;
	}

	public void setEstablished(String established) {
		this.established = established;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getHeadQuaters() {
		return headQuaters;
	}

	public void setHeadQuaters(String headQuaters) {
		this.headQuaters = headQuaters;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	private String flightName;
	private String headQuaters;
	private String logo;
	private int _id;
	private String slogan;

	public void setTrips(int trips){
		this.trips = trips;
	}

	public int getTrips(){
		return trips;
	}

	public void setV(int V){
		this.V = V;
	}

	public int getV(){
		return V;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setAirline(List<AirlineItem> airline){
		this.airline = airline;
	}

	public List<AirlineItem> getAirline(){
		return airline;
	}

	@Override
	public String toString() {
		return "PassengerDto{" +
				"trips=" + trips +
				", V=" + V +
				", name='" + name + '\'' +
				", id='" + id + '\'' +
				", airline=" + airline +
				", established='" + established + '\'' +
				", country='" + country + '\'' +
				", website='" + website + '\'' +
				", flightName='" + flightName + '\'' +
				", headQuaters='" + headQuaters + '\'' +
				", logo='" + logo + '\'' +
				", _id=" + _id +
				", slogan='" + slogan + '\'' +
				'}';
	}
}
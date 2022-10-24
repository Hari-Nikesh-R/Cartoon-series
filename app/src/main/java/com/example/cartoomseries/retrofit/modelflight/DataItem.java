package com.example.cartoomseries.retrofit.modelflight;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("trips")
	private int trips;

	@SerializedName("__v")
	private int V;

	@SerializedName("name")
	private String name;

	@SerializedName("_id")
	private String id;

	@SerializedName("airline")
	private List<AirlineItem> airline;

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
		return "DataItem{" +
				"trips=" + trips +
				", V=" + V +
				", name='" + name + '\'' +
				", id='" + id + '\'' +
				", airline=" + airline +
				'}';
	}
}
package com.example.cartoomseries.retrofit.modelflight;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class FlightResponse {

	@SerializedName("totalPassengers")
	private int totalPassengers;

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("totalPages")
	private int totalPages;

	public void setTotalPassengers(int totalPassengers){
		this.totalPassengers = totalPassengers;
	}

	public int getTotalPassengers(){
		return totalPassengers;
	}

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}
}
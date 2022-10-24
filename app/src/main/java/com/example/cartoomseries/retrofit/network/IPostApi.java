package com.example.cartoomseries.retrofit.network;

import com.example.cartoomseries.retrofit.modelflight.FlightResponse;
import com.example.cartoomseries.retrofit.modelmovie.NarutoFields;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IPostApi {
     @GET("?apikey=9cfa95f8")
     Call<NarutoFields> getSearchItem(@Query("s") String searchTerm);

     @GET("v1/passenger?page=0&size=10")
     Call<FlightResponse> getTenFlightDetails();

     @GET("v1/passenger")
     Call<FlightResponse> getFlightResult(@Query("page") int page, @Query("size") int limit);

     @GET("?apikey=9cfa95f8&s=\"naruto\"")
     Call<NarutoFields> getNarutoItem();

     @GET("?apikey=9cfa95f8&s=\"ben_10\"")
     Call<NarutoFields> getBenTenItem();

     @GET("?apikey=9cfa95f8&s=\"harry_potter\"")
     Call<NarutoFields> getPotterItem();

     @GET("?apikey=9cfa95f8&s=\"king_kong\"")
     Call<NarutoFields> getKingKongItem();
    // Call<List<NarutoFields>> getSearchItemList();


   //  @GET("posts/{id}")
    // Call<PostResponse1> getOnePosts(@Path("id") Integer id);

}

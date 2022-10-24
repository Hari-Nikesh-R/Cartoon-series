package com.example.cartoomseries.adapters.interfaces;


import com.example.cartoomseries.retrofit.modelflight.AirlineItem;
import com.example.cartoomseries.retrofit.modelflight.PassengerDto;
import com.example.cartoomseries.retrofit.modelmovie.SearchItem;

public interface IUserAdapterCommunicator {
    void OnUserClick(SearchItem searchIndex, int position);
    void OnUserFlightClick(PassengerDto passengerDto, int position);
    void initApiFlightCall();
}

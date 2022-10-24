
package com.example.cartoomseries.adapters;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.cartoomseries.R;
import com.example.cartoomseries.adapters.interfaces.IUserAdapterCommunicator;
import com.example.cartoomseries.retrofit.modelflight.PassengerDto;
import java.util.List;
public class FlightLinearAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<PassengerDto> searchItemsList;
    //  DataItem searchItems;
    IUserAdapterCommunicator iUserAdapterCommunicator;
    private final int LOADER=1;
    private final int DISABLE=0;
    // private  List<AirlineItem> airlineItems;
    public FlightLinearAdapter(List<PassengerDto> searchItemsList, IUserAdapterCommunicator iUserAdapterCommunicator)
    {
        this.searchItemsList = searchItemsList;
//  this.searchItems = searchItems;
        this.iUserAdapterCommunicator = iUserAdapterCommunicator;
//   this.airlineItems = airlineItems;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("SUBSTITLE","came here");
        if(viewType==LOADER)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loader,parent,false);
            return new LoaderViewHolder(view);
        }
        else {
// View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_userdata,parent,false);
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_flight, parent, false);
            return new UserViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof UserViewHolder && position<searchItemsList.size()) {
        PassengerDto searchItems =searchItemsList.get(position);

            ((UserViewHolder) holder).passengerName.setText(searchItems.getName());
            ((UserViewHolder) holder).name.setText(searchItems.getFlightName());
            ((UserViewHolder) holder).trips.setText(searchItems.getId().replace(searchItems.getId().substring(0, 15), "*****"));
            ((UserViewHolder) holder).country.setText(searchItems.getCountry());
            ((UserViewHolder) holder).established.setText(searchItems.getEstablished());
            Glide.with(((UserViewHolder) holder).logo.getContext()).load(searchItems.getLogo()).placeholder(R.drawable.image).into(((UserViewHolder) holder).logo);
        }
        else if(holder instanceof LoaderViewHolder)
        {
            ((LoaderViewHolder) holder).progressBar.setVisibility(View.VISIBLE);
            iUserAdapterCommunicator.initApiFlightCall();
        }
        else
        {
            iUserAdapterCommunicator.initApiFlightCall();
        }
    }
    @Override
    public int getItemViewType(int position) {
        if(position==searchItemsList.size())
        {
            return LOADER;
        }
        return DISABLE;
    }
//        if(position==searchItemsList.size()-1)
//        {
//            iUserAdapterCommunicator.initApiFlightCall();
//        }
//        holder.root.setOnClickListener(view -> {
//            iUserAdapterCommunicator.OnUserFlightClick(DataItem,position);
//        });

    @Override
    public int getItemCount() {
        return searchItemsList.size()+1;
    }
    public static class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView passengerName;
        private TextView trips;
        private TextView country;
        private TextView established;
        private TextView name;
        private ImageView logo;
        private View root;
        public UserViewHolder(@NonNull View view)
        {
            super(view);
            root = view;
            passengerName = view.findViewById(R.id.tv_mainflight_passengerName);
            logo = view.findViewById(R.id.iv_mainflight_logo);
            name = view.findViewById(R.id.tv_mainflight_name);
            trips = view.findViewById(R.id.tv_mainflight_trips);
            country = view.findViewById(R.id.tv_mainflight_country);
            established = view.findViewById((R.id.tv_mainflight_established));
        }
    }
    public static class LoaderViewHolder extends RecyclerView.ViewHolder{
        private ProgressBar progressBar;
        public LoaderViewHolder(@NonNull View view) {
            super(view);
            progressBar = view.findViewById(R.id.rendering_progress_bar);
        }
    }
}



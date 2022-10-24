package com.example.cartoomseries.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.cartoomseries.R;

import com.example.cartoomseries.adapters.interfaces.IUserAdapterCommunicator;
import com.example.cartoomseries.retrofit.modelmovie.SearchItem;

import java.util.List;

public class PotterRecyclerAdapter extends RecyclerView.Adapter<PotterRecyclerAdapter.UserViewHolder> {
    private final List<SearchItem> searchItemsList;
   IUserAdapterCommunicator iUserAdapterCommunicator;

    public PotterRecyclerAdapter(List<SearchItem> searchItems,IUserAdapterCommunicator iUserAdapterCommunicator)
    {
        this.searchItemsList = searchItems;
         this.iUserAdapterCommunicator = iUserAdapterCommunicator;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("SUBSTITLE","came here");
       // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_userdata,parent,false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_potter,parent,false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        SearchItem narutoFields=searchItemsList.get(position);
        holder.title.setText(narutoFields.getTitle());
        holder.date.setText(narutoFields.getYear());
        holder.type.setText(narutoFields.getType());
        holder.imDb.setText(narutoFields.getImdbID());
        Glide.with(holder.logo.getContext()).load(narutoFields.getPoster()).placeholder(R.drawable.image).into(holder.logo);

        holder.root.setOnClickListener(view -> {
           iUserAdapterCommunicator.OnUserClick(narutoFields,position);
        });

    }

    @Override
    public int getItemCount() {
        return searchItemsList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView type;
        private TextView date;
        private TextView imDb;
        private ImageView logo;
        private View root;

        public UserViewHolder(View view)
        {
            super(view);
            root = view;
            logo = view.findViewById(R.id.potter_poster);
            title = view.findViewById(R.id.potter_title);
            type = view.findViewById(R.id.potter_type);
            date = view.findViewById((R.id.potter_year));
            imDb = view.findViewById(R.id.potter_rating);

        }

    }

}

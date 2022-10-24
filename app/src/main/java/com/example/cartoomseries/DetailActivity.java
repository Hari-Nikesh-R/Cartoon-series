package com.example.cartoomseries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cartoomseries.retrofit.modelmovie.SearchItem;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    List<SearchItem> searchItemArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_detail);
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);


            TextView title = findViewById(R.id.pd_title);
            TextView type = findViewById(R.id.pd_type);
            TextView date = findViewById(R.id.pd_date);
            TextView review = findViewById(R.id.pd_imDb);
            ImageView poster = findViewById(R.id.md_poster);

            title.setText(sharedPreferences.getString("Title", "default"));
            type.setText(sharedPreferences.getString("Type", "default"));
            date.setText(sharedPreferences.getString("Year", "default"));
            review.setText(sharedPreferences.getString("Rating", "default"));
            Glide.with(this).load(sharedPreferences.getString("Poster", "default")).placeholder(R.drawable.naruto_mainpage).into(poster);
            findViewById(R.id.detail_home).setOnClickListener(view -> {
                Intent mainIntent = new Intent(DetailActivity.this,MainActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mainIntent);




            });
            findViewById(R.id.detail_back).setOnClickListener(view -> {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("SEARCH","hello");
                onBackPressed();
            });
            findViewById(R.id.detail_search_parameter).setOnClickListener(view -> {
                EditText editText = findViewById(R.id.detail_search);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("SEARCH", editText.getText().toString());
                editor.apply();
                startActivity(new Intent(DetailActivity.this, SearchActivity.class));
                //startActivity(new Intent(DetailActivity.this,SearchActivity.class));
            });
        }

}



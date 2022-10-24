package com.example.cartoomseries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cartoomseries.adapters.BenTenRecyclerAdapter;
import com.example.cartoomseries.adapters.UserLinearRecyclerAdapter;
import com.example.cartoomseries.adapters.interfaces.IUserAdapterCommunicator;
import com.example.cartoomseries.retrofit.modelflight.AirlineItem;
import com.example.cartoomseries.retrofit.modelflight.PassengerDto;
import com.example.cartoomseries.retrofit.modelmovie.NarutoFields;
import com.example.cartoomseries.retrofit.modelmovie.SearchItem;
import com.example.cartoomseries.retrofit.network.IPostApi;
import com.example.cartoomseries.retrofit.networkHandler.RetroFitBuilderMovie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements IUserAdapterCommunicator {
    List<SearchItem> searchItemArrayList = new ArrayList<>();
    //public static String searchString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initApiNaruto();
        initApiPotter();
        initApiBenTen();
        initApiKingKong();
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        ImageButton imageButton =  findViewById(R.id.main_search_parameter);
        EditText editText = findViewById(R.id.et_search_parameter);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                String s = editText.getText().toString().trim();
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    editor.putString("SEARCH",s);
                    editor.apply();
                    if(s.equals("")){
                        Toast.makeText(MainActivity.this,"No Input Typed",Toast.LENGTH_SHORT).show();
                    }
                    else {

                        startActivity(new Intent(MainActivity.this,SearchActivity.class));
                    }

                    return true;
                }
                return false;
            }
        });
        findViewById(R.id.home_clear).setOnClickListener(view -> {
                editText.setText("");
        });
        imageButton.setOnClickListener(view -> {
            String search = editText.getText().toString().trim();
            editor.putString("SEARCH",search);
            editor.apply();
           // searchString = editText.getText().toString();
           // Log.d("STRING_WIRE",searchString);
           // System.out.println(searchString);
            startActivity(new Intent(MainActivity.this,SearchActivity.class));
            //(new Intent(MainActivity.this,SearchActivity.class));
        });
        findViewById(R.id.navigate_flight).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,FlightSplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);


        });




    }
    public void initApiNaruto() {
        Retrofit retrofit = RetroFitBuilderMovie.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        Call<NarutoFields> responses = iPostApi.getNarutoItem();
        responses.enqueue(new Callback<NarutoFields>() {
            @Override
            public void onResponse(Call<NarutoFields> call, Response<NarutoFields> response) {
                // for(NarutoFields narutoFields:response.body()) {

                Log.d("SAMPLE_TEST",String.valueOf(response.body()));
                NarutoFields narutoFields = response.body();
                Log.d("SAMPLE_TEST", String.valueOf(narutoFields.getResponse()));
                //searchItemArrayList.add(narutoFields.getSearch().get(0));
                searchItemArrayList = narutoFields.getSearch();
                Log.d("SAMPLE_TEST", String.valueOf(searchItemArrayList.size()));
                RecyclerView recyclerView = findViewById(R.id.home_naruto);
                UserLinearRecyclerAdapter userViewAdapter = new UserLinearRecyclerAdapter(searchItemArrayList,MainActivity.this);
                //recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));
                recyclerView.setAdapter(userViewAdapter);
            }


            @Override
            public void onFailure(Call<NarutoFields> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("SAMPLE_LIST", t.getMessage());
            }
        });
    }
    public void initApiPotter() {
        Retrofit retrofit = RetroFitBuilderMovie.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        Call<NarutoFields> responses = iPostApi.getPotterItem();
        responses.enqueue(new Callback<NarutoFields>() {
            @Override
            public void onResponse(Call<NarutoFields> call, Response<NarutoFields> response) {
                // for(NarutoFields narutoFields:response.body()) {

                Log.d("SAMPLE_TEST",String.valueOf(response.body()));
                NarutoFields narutoFields = response.body();
                Log.d("SAMPLE_TEST", String.valueOf(narutoFields.getResponse()));
                //searchItemArrayList.add(narutoFields.getSearch().get(0));
                searchItemArrayList = narutoFields.getSearch();
                Log.d("SAMPLE_TEST", String.valueOf(searchItemArrayList.size()));
                RecyclerView recyclerView = findViewById(R.id.home_potter);
                BenTenRecyclerAdapter userViewAdapter = new BenTenRecyclerAdapter(searchItemArrayList,MainActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                recyclerView.setAdapter(userViewAdapter);
            }


            @Override
            public void onFailure(Call<NarutoFields> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("SAMPLE_LIST", t.getMessage());
            }
        });
    }
    public void initApiBenTen() {
        Retrofit retrofit = RetroFitBuilderMovie.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        Call<NarutoFields> responses = iPostApi.getBenTenItem();
        responses.enqueue(new Callback<NarutoFields>() {
            @Override
            public void onResponse(Call<NarutoFields> call, Response<NarutoFields> response) {
                // for(NarutoFields narutoFields:response.body()) {

                Log.d("SAMPLE_TEST",String.valueOf(response.body()));
                NarutoFields narutoFields = response.body();
                Log.d("SAMPLE_TEST", String.valueOf(narutoFields.getResponse()));
                //searchItemArrayList.add(narutoFields.getSearch().get(0));
                searchItemArrayList = narutoFields.getSearch();
                Log.d("SAMPLE_TEST", String.valueOf(searchItemArrayList.size()));
                RecyclerView recyclerView = findViewById(R.id.home_ben_10);
                UserLinearRecyclerAdapter userViewAdapter = new UserLinearRecyclerAdapter(searchItemArrayList,MainActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                recyclerView.setAdapter(userViewAdapter);
            }


            @Override
            public void onFailure(Call<NarutoFields> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("SAMPLE_LIST", t.getMessage());
            }
        });
    }
    public void initApiKingKong() {
    Retrofit retrofit = RetroFitBuilderMovie.getInstance();
    IPostApi iPostApi = retrofit.create(IPostApi.class);
    Call<NarutoFields> responses = iPostApi.getKingKongItem();
        responses.enqueue(new Callback<NarutoFields>() {
        @Override
        public void onResponse(Call<NarutoFields> call, Response<NarutoFields> response) {
            // for(NarutoFields narutoFields:response.body()) {

            Log.d("SAMPLE_TEST",String.valueOf(response.body()));
            NarutoFields narutoFields = response.body();
            Log.d("SAMPLE_TEST", String.valueOf(narutoFields.getResponse()));
            //searchItemArrayList.add(narutoFields.getSearch().get(0));
            searchItemArrayList = narutoFields.getSearch();
            Log.d("SAMPLE_TEST", String.valueOf(searchItemArrayList.size()));
            RecyclerView recyclerView = findViewById(R.id.home_king_kong);
            UserLinearRecyclerAdapter userViewAdapter = new UserLinearRecyclerAdapter(searchItemArrayList,MainActivity.this);
            //recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));
            recyclerView.setAdapter(userViewAdapter);
        }


        @Override
        public void onFailure(Call<NarutoFields> call, Throwable t) {
            Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("SAMPLE_LIST", t.getMessage());
        }
    });
}
    public void initApiSearch() {
        Retrofit retrofit = RetroFitBuilderMovie.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);
        Call<NarutoFields> responses = iPostApi.getKingKongItem();
        responses.enqueue(new Callback<NarutoFields>() {
            @Override
            public void onResponse(Call<NarutoFields> call, Response<NarutoFields> response) {
                // for(NarutoFields narutoFields:response.body()) {

                Log.d("SAMPLE_TEST",String.valueOf(response.body()));
                NarutoFields narutoFields = response.body();
                Log.d("SAMPLE_TEST", String.valueOf(narutoFields.getResponse()));
                //searchItemArrayList.add(narutoFields.getSearch().get(0));
                searchItemArrayList = narutoFields.getSearch();
                Log.d("SAMPLE_TEST", String.valueOf(searchItemArrayList.size()));
                RecyclerView recyclerView = findViewById(R.id.home_king_kong);
                UserLinearRecyclerAdapter userViewAdapter = new UserLinearRecyclerAdapter(searchItemArrayList,MainActivity.this);
                //recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));
                recyclerView.setAdapter(userViewAdapter);
            }


            @Override
            public void onFailure(Call<NarutoFields> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("SAMPLE_LIST", t.getMessage());
            }
        });
    }


    @Override
    public void OnUserClick(SearchItem searchIndex, int position) {
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Title",searchIndex.getTitle());
        editor.putString("Type",searchIndex.getType());
        editor.putString("Year",searchIndex.getYear());
        editor.putString("Rating",searchIndex.getImdbID());
        editor.putString("Poster",searchIndex.getPoster());
        editor.apply();
        startActivity(new Intent(MainActivity.this,DetailActivity.class));
    }

    @Override
    public void OnUserFlightClick(PassengerDto airlineItem, int position) {

    }

    @Override
    public void initApiFlightCall() {

    }
}
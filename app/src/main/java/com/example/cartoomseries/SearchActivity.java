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
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

public class SearchActivity extends AppCompatActivity implements IUserAdapterCommunicator {
    List<SearchItem> searchItemArrayList = new ArrayList<>();
    boolean isToggle;
    RecyclerView recyclerView;
    UserLinearRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView = findViewById(R.id.search_page_result);


        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String term = sharedPreferences.getString("SEARCH","Default");
        initApiSearch(term);
        findViewById(R.id.toggle_button).setOnClickListener(view -> {
            if(isToggle) {
                isToggle = false;
               // recyclerView = findViewById(R.id.search_page_result);
               adapter = new UserLinearRecyclerAdapter(searchItemArrayList,SearchActivity.this);
               recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(adapter);
               // initApiSearch(term);

            }
            else {
                isToggle = true;
                //recyclerView = findViewById(R.id.search_page_result);
                 adapter = new UserLinearRecyclerAdapter(searchItemArrayList,SearchActivity.this);

                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));
                recyclerView.setAdapter(adapter);
            }
        });
        EditText editText = findViewById(R.id.search_again);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {

                    editor.putString("SEARCH",editText.getText().toString().trim());
                    editor.apply();
                    initApiSearch(editText.getText().toString().trim());
                   // startActivity(new Intent(SearchActivity.this,MainActivity.class));
                    return true;
                }
                return false;
            }
        });

        findViewById(R.id.search_clear).setOnClickListener(view -> {
            editText.setText("");
        });
        findViewById(R.id.seach_back).setOnClickListener(view -> {

            if(editText.getText().toString().length()>0){
                editText.setText("");
            }
            else {
               onBackPressed();
            }

        });
        findViewById(R.id.search_home).setOnClickListener(view -> {
            Intent mainIntent = new Intent(SearchActivity.this,MainActivity.class);
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);


        });
        ImageButton searchAgain = findViewById(R.id.search_parameter);
        searchAgain.setOnClickListener(view -> {
            findViewById(R.id.search_result_image).setVisibility(View.INVISIBLE);
            findViewById(R.id.search_page_result).setVisibility(View.VISIBLE);
            String s = editText.getText().toString().trim();
            editor.putString("SEARCH",s);
            editor.apply();
            if(s.equals("")){
                Toast.makeText(SearchActivity.this,"No Input Typed",Toast.LENGTH_SHORT).show();
            }
            else {
                initApiSearch(s);
            }

        });

    }
    public void initApiSearch(String term) {

        Retrofit retrofit = RetroFitBuilderMovie.getInstance();
        IPostApi iPostApi = retrofit.create(IPostApi.class);

        Call<NarutoFields> responses = iPostApi.getSearchItem(term);
        responses.enqueue(new Callback<NarutoFields>() {
            @Override
            public void onResponse(Call<NarutoFields> call, Response<NarutoFields> response) {
                // for(NarutoFields narutoFields:response.body()) {
                boolean noList=false;
              //  Log.d("SAMPLE_TEST",String.valueOf(response.body()));
                NarutoFields narutoFields = response.body();
            //    Log.d("SAMPLE_TEST", String.valueOf(narutoFields.getResponse()));
                //searchItemArrayList.add(narutoFields.getSearch().get(0));
                Log.e("STRING_Wire",narutoFields.getResponse());
                if(narutoFields.getResponse().equals("False"))
                {
                    noList=true;
                  //  startActivity(new Intent(SearchActivity.this,NoResultActivity.class));
                    findViewById(R.id.search_result_image).setVisibility(View.VISIBLE);
                    findViewById(R.id.search_page_result).setVisibility(View.INVISIBLE);
                }
                if(!noList) {
                    searchItemArrayList = narutoFields.getSearch();
//                Log.d("SAMPLE_TEST", String.valueOf(searchItemArrayList.size()));
                    RecyclerView recyclerView = findViewById(R.id.search_page_result);
                    UserLinearRecyclerAdapter userViewAdapter = new UserLinearRecyclerAdapter(searchItemArrayList, SearchActivity.this);

                    recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));

                    //   else {
                    //     recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));

                    recyclerView.setAdapter(userViewAdapter);
                }
            }


            @Override
            public void onFailure(Call<NarutoFields> call, Throwable t) {
                Toast.makeText(SearchActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("SAMPLE_LIST", t.getMessage());
            }
        });
    }

    @Override
    public void OnUserClick(SearchItem searchIndex, int position) {
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Title",searchIndex.getTitle());
        editor.putString("Type",searchIndex.getType());
        editor.putString("Year",searchIndex.getYear());
        editor.putString("Rating",searchIndex.getImdbID());
        editor.putString("Poster",searchIndex.getPoster());
        editor.apply();
        startActivity(new Intent(SearchActivity.this,DetailActivity.class));
    }

    @Override
    public void OnUserFlightClick(PassengerDto airlineItem, int position) {

    }

    @Override
    public void initApiFlightCall() {

    }
}
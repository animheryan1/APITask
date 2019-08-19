package com.example.retrofittask.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.retrofittask.Interfaces.UserService;
import com.example.retrofittask.Models.MovieModel;
import com.example.retrofittask.R;
import com.example.retrofittask.RecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<MovieModel> movieModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRetrofitCall();
    }

    private void setRetrofitCall() {
        //Step1
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.androidhive.info")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Step2
        UserService userService = retrofit.create(UserService.class);
        Call<List<MovieModel>> call = userService.getData();

        //Step3
        call.enqueue(new Callback<List<MovieModel>>() {
            @Override
            public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        movieModels = new ArrayList<>();
                        movieModels.addAll(response.body());
                        setRecyclerView();
                    } else {
                        Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<MovieModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail 2", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setRecyclerView(){
        recyclerView = findViewById(R.id.movies_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(movieModels);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}

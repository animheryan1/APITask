package com.example.retrofittask.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.retrofittask.Interfaces.UserService;
import com.example.retrofittask.Models.MovieModel;
import com.example.retrofittask.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ImageView firstImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRetrofitCall();
        firstImage = findViewById(R.id.first_image);
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
                        List<MovieModel> movieModels = response.body();
                        Toast.makeText(MainActivity.this, movieModels.get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        Picasso.get().load(movieModels.get(0).getImage()).into(firstImage);
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
}

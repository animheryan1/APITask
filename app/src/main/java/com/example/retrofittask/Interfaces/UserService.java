package com.example.retrofittask.Interfaces;

import com.example.retrofittask.Models.MovieModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("json/movies.json")
    Call<List<MovieModel>> getData();
}

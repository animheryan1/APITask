package com.example.retrofittask.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieModel {
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("image")
    @Expose
    private String image;

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }
}

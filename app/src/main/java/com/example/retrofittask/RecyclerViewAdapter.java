package com.example.retrofittask;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofittask.Models.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {
    private List<MovieModel> models;

    public RecyclerViewAdapter(List<MovieModel> models) {
        this.models = models;
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView rating;
        TextView releaseYear;

        CustomViewHolder(View view) {
            super(view);
            this.image = view.findViewById(R.id.image);
            this.title = view.findViewById(R.id.title);
            this.rating = view.findViewById(R.id.rating_value);
            this.releaseYear = view.findViewById(R.id.release_value);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
        MovieModel movie = models.get(position);

        holder.title.setText(movie.getTitle());
        holder.rating.setText(String.valueOf(movie.getRating()));
        holder.releaseYear.setText(String.valueOf(movie.getReleaseYear()));
        Picasso.get().load(movie.getImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}

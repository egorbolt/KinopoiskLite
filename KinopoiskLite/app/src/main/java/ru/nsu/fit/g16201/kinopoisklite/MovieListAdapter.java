package ru.nsu.fit.g16201.kinopoisklite;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieInfoViewHolder>  {


    @NonNull
    @Override
    public MovieInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieInfoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MovieInfoViewHolder extends RecyclerView.ViewHolder {
        public MovieInfoViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

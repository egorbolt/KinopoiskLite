package ru.nsu.fit.g16201.kinopoisklite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieInfoViewHolder>  {


    private String[] dataSet;

    public MovieListAdapter(String[] dataSet) {
        this.dataSet = dataSet;
    } //todo: в будущем это будет список фильмов (класс Movie)

    @NonNull
    @Override
    public MovieInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_big_view, parent, false);

        return new MovieListAdapter.MovieInfoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieInfoViewHolder holder, int position) {
        holder.ratingBadge.setText(dataSet[position]);
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }

    static class MovieInfoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView ratingBadge;
        MovieInfoViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.movie_poster_image_view);
            ratingBadge = view.findViewById(R.id.badge_rating);
        }
    }
}

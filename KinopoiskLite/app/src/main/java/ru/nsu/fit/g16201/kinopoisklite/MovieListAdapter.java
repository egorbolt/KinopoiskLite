package ru.nsu.fit.g16201.kinopoisklite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Movie;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieInfoViewHolder>  {

    private List<Movie> dataSet;
    private RecyclerViewMovieClickListener itemListener;

    class MovieInfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView ratingBadge;
        TextView movieTitle;
        TextView movieDescription;
        MovieInfoViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.movie_poster_image_view);
            ratingBadge = view.findViewById(R.id.badge_rating);
            movieTitle = view.findViewById(R.id.textViewMovieTitle);
            movieDescription = view.findViewById(R.id.textViewMovieDescription);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition(), dataSet.get(this.getLayoutPosition()));
        }
    }


    public MovieListAdapter(List<Movie> dataSet, RecyclerViewMovieClickListener recyclerViewMovieClickListener) {
        this.dataSet = dataSet;
        itemListener = recyclerViewMovieClickListener;

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
        holder.ratingBadge.setText("7.5");
        holder.movieTitle.setText(dataSet.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}

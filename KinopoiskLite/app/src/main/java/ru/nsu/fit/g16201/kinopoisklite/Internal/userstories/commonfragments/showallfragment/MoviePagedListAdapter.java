package ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.commonfragments.showallfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.UrlConstructor;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Movie;
import ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.reusables.RecyclerViewMovieClickListener;
import ru.nsu.fit.g16201.kinopoisklite.R;

public class MoviePagedListAdapter extends PagedListAdapter<Movie, MoviePagedListAdapter.MovieViewHolder> {

    private RecyclerViewMovieClickListener itemListener;


    MoviePagedListAdapter(@NonNull DiffUtil.ItemCallback<Movie> diffCallback, RecyclerViewMovieClickListener itemListener) {
        super(diffCallback);
        this.itemListener = itemListener;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView ratingBadge;
        TextView movieTitle;
        TextView movieDescription;
        MovieViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.movie_poster_image_view);
            ratingBadge = view.findViewById(R.id.badge_rating);
            movieTitle = view.findViewById(R.id.textViewMovieTitle);
            movieDescription = view.findViewById(R.id.textViewMovieDescription);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition(), getItem(this.getLayoutPosition()));
        }
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_big_view, parent, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = getItem(position);
        if(movie == null)
            return;

        //System.out.println(movie.getVoteAverage() + "  " +  movie.getTitle() + " " + (movie.getVoteAverage() != 0));
        if(movie.getVoteAverage() != 0) {
            holder.ratingBadge.setText(Double.toString(movie.getVoteAverage()));
            holder.ratingBadge.setVisibility(View.VISIBLE);
        }
        else
            holder.ratingBadge.setVisibility(View.GONE);

        holder.movieDescription.setText(movie.getOverview());

        holder.movieTitle.setText(movie.getTitle());

        if(movie.getPosterPath().isPresent())
            Picasso.get().load(UrlConstructor.urlSingleImage(movie.getPosterPath().get())).into(holder.imageView);
    }


}

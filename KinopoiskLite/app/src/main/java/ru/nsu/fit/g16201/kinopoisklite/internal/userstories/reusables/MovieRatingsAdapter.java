package ru.nsu.fit.g16201.kinopoisklite.internal.userstories.reusables;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.api.UrlConstructor;
import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.models.Movie;
import ru.nsu.fit.g16201.kinopoisklite.R;

public class MovieRatingsAdapter extends RecyclerView.Adapter<MovieRatingsAdapter.MoviePosterViewHolder> {
    private RecyclerViewMovieClickListener itemListener;
    private List<Movie> dataSet;

    public MovieRatingsAdapter(List<Movie> itemsData, RecyclerViewMovieClickListener recyclerViewMovieClickListener) {
        this.dataSet = itemsData;
        itemListener = recyclerViewMovieClickListener;
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class MoviePosterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        ImageView imageView;
        TextView ratingBadge, titleView;
        MoviePosterViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.movie_poster_image_view);
            ratingBadge = view.findViewById(R.id.badge_rating);
            titleView = view.findViewById(R.id.textViewMovieTitle);


            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Movie m = dataSet.get(this.getLayoutPosition());
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition(), m);
        }
    }


    @NonNull
    @Override
    public MoviePosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_poster_name, parent, false);

        return new MoviePosterViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MoviePosterViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.imageView.set(dataSet[position]); //todo
        Movie movie = dataSet.get(position);
        if(movie.getVoteAverage() != 0)
            holder.ratingBadge.setText(Double.toString(movie.getVoteAverage()));
        else
            holder.ratingBadge.setVisibility(View.INVISIBLE);
        holder.titleView.setText(movie.getTitle());

        if(movie.getPosterPath().isPresent())
            Picasso.get().load(UrlConstructor.urlSingleImage(movie.getPosterPath().get())).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}

package ru.nsu.fit.g16201.kinopoisklite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Movie;

public class MovieRatingsAdapter extends RecyclerView.Adapter<MovieRatingsAdapter.MoviePosterViewHolder> {
    private RecyclerViewMovieClickListener itemListener;
    private List<Movie> mDataset;

    public MovieRatingsAdapter(List<Movie> itemsData, RecyclerViewMovieClickListener recyclerViewMovieClickListener) {
        this.mDataset = itemsData;
        itemListener = recyclerViewMovieClickListener;

    }   //todo: в будущем это будет список фильмов (класс Movie)


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
            Movie m = new Movie();
            m.setId(342);
            //todo
            System.out.println("ASASASASASA");
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition(), m);
        }
    }


    @NonNull
    @Override
    public MoviePosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_poster_name, parent, false);

        return new MoviePosterViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MoviePosterViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.imageView.set(mDataset[position]); //todo
        Movie movie = mDataset.get(position);
        holder.ratingBadge.setText(Double.toString(movie.getVoteAverage()));
        holder.titleView.setText(movie.getTitle());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}

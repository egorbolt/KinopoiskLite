package ru.nsu.fit.g16201.kinopoisklite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieRatingsAdapter extends RecyclerView.Adapter<MovieRatingsAdapter.MoviePosterViewHolder> {
    private RecyclerViewMovieClickListener itemListener;
    private String[] mDataset;

    public MovieRatingsAdapter(String[] itemsData, RecyclerViewMovieClickListener recyclerViewMovieClickListener) {
        this.mDataset = itemsData;
        itemListener = recyclerViewMovieClickListener;

    }   //todo: в будущем это будет список фильмов (класс Movie)


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class MoviePosterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        ImageView imageView;
        TextView ratingBadge;
        MoviePosterViewHolder(View ll) {
            super(ll);
            imageView = ll.findViewById(R.id.movie_poster_image_view);
            ratingBadge = ll.findViewById(R.id.badge_rating);
        }

        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition(), dataSet.get(this.getLayoutPosition()));
        }
    }


    @NonNull
    @Override
    public MoviePosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_poster_view, parent, false);

        return new MoviePosterViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MoviePosterViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.imageView.set(mDataset[position]); //todo
        holder.ratingBadge.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

}

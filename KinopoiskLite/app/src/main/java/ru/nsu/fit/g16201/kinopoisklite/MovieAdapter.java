package ru.nsu.fit.g16201.kinopoisklite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private String[] mDataset;

    MovieAdapter(String[] itemsData) {
        this.mDataset = itemsData;
    }   //todo: в будущем это будет список фильмов (класс Movie)


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class MovieViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        ImageView imageView;
        TextView ratingBadge;
        MovieViewHolder(View ll) {
            super(ll);
            imageView = ll.findViewById(R.id.movie_poster_image_view);
            ratingBadge = ll.findViewById(R.id.badge_rating);
        }
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_view, parent, false);

        return new MovieViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
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

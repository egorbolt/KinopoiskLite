package ru.nsu.fit.g16201.kinopoisklite;

import android.view.View;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Movie;

public interface RecyclerViewMovieClickListener {
    void recyclerViewListClicked(View v, int position, Movie movie);
}
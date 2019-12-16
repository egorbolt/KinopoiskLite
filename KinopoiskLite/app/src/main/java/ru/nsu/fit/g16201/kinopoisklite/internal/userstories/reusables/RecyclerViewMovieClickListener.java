package ru.nsu.fit.g16201.kinopoisklite.internal.userstories.reusables;

import android.view.View;

import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.models.Movie;

public interface RecyclerViewMovieClickListener {
    void recyclerViewListClicked(View v, int position, Movie movie);
}
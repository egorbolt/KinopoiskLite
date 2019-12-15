package ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.commonfragments.showallfragment;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Movie;

public class MovieDataSource extends PageKeyedDataSource<Integer, Movie> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }
}

package ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.commonfragments.showallfragment;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.PagedMovieListTask;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Movie;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.PopularMovies;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.listloader.ListType;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.listloader.PagedListLoader;

public class MovieDataSource extends PageKeyedDataSource<Integer, Movie> {

    private int id;
    private ListType type;
    private PagedMovieListTask task = null;
    private static final String ERROR_TAG = "ShowAllFragment:MovieDataSource";


    public MovieDataSource(ListType type, int id)
    {
        this.type = type;
        this.id = id;
        try {
            task = PagedListLoader.loadParametrisedList(type, 1, id,"en-US");
        } catch (MalformedURLException e) {
            Log.e(ERROR_TAG, "Malformed URL" + e.getMessage());
        }
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {
        List<Movie> dataSet = new ArrayList<>();
        if(task != null) {
            try
            {
                PopularMovies movies = task.get();
                if(movies != null)
                    dataSet = movies.getResults();
            }
            catch (InterruptedException e)
            {
                Log.e(ERROR_TAG, "Can't retrieve data: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
            catch (ExecutionException e)
            {
                Log.e(ERROR_TAG, "Can't retrieve data: " + e.getMessage());
            }
        }
        callback.onResult(dataSet, null, 2);
    }

    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
        int page = params.key;

        PagedMovieListTask pageTask;
        try {
            pageTask = PagedListLoader.loadParametrisedList(type, 1, id,"en-US");
        } catch (MalformedURLException e) {
            Log.e(ERROR_TAG, "Malformed URL" + e.getMessage());
            return;
        }

        List<Movie> dataSet = new ArrayList<>();
        if(pageTask != null) {
            try
            {
                PopularMovies movies = pageTask.get();
                if(movies != null)
                    dataSet = movies.getResults();
            }
            catch (InterruptedException e)
            {
                Log.e(ERROR_TAG, "Can't retrieve data: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
            catch (ExecutionException e)
            {
                Log.e(ERROR_TAG, "Can't retrieve data: " + e.getMessage());
            }
        }
        callback.onResult(dataSet, params.key+1);

    }
}

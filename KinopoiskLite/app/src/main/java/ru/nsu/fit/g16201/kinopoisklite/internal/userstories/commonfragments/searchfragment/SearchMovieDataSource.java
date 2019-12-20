package ru.nsu.fit.g16201.kinopoisklite.internal.userstories.commonfragments.showallfragment;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.api.tasks.PagedMovieListTask;
import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.models.Movie;
import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.models.PopularMovies;
import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.listloader.ListType;
import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.listloader.PagedListLoader;

public class SearchMovieDataSource extends PageKeyedDataSource<Integer, Movie> {

    private String title;

    private PagedMovieListTask task = null;
    private static final String ERROR_TAG = "ShowAllFragment:SearchMovieDataSource";


    SearchMovieDataSource(String title)
    {
        this.title = title;
        try {
            task = ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.api.API.loadSearchedResult(title, "en-US");
        } catch (MalformedURLException e) {
            Log.e(ERROR_TAG, "Malformed URL" + e.getMessage());
        }
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movie> callback) {
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
            pageTask = PagedListLoader.loadParametrisedList(type, page, id,"en-US");
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

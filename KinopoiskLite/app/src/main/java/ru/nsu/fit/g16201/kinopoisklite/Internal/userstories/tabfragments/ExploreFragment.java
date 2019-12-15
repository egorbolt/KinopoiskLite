package ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.tabfragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.PagedMovieListTask;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Movie;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.PopularMovies;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.listloader.ListType;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.listloader.PagedListLoader;
import ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.reusables.MovieRatingsAdapter;
import ru.nsu.fit.g16201.kinopoisklite.MainActivity;
import ru.nsu.fit.g16201.kinopoisklite.R;
import ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.commonfragments.moviefragment.MovieFragment;
import ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.commonfragments.showallfragment.ShowAllFragment;

public class ExploreFragment extends Fragment {

    private static final String ERROR_TAG = "ExploreFragment";

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_explore, container, false);

        configureMovieCollection(R.id.trending_movie_collection, "Trending", ListType.TRENDING);
        configureMovieCollection(R.id.popular_movie_collection, "Popular", ListType.POPULAR);
        configureMovieCollection(R.id.upcoming_movie_collection, "Upcoming", ListType.UPCOMING);
        configureMovieCollection(R.id.top_rated_movie_collection, "Top Rated", ListType.TOP_RATED);
        configureMovieCollection(R.id.now_playing_movie_collection, "Now Playing", ListType.NOW_PLAYING);

        return view;
    }



    private void configureMovieCollection(int id, String name, ListType listType)
    {
        PagedMovieListTask task = null;
        try {
            task = PagedListLoader.loadList(listType, 1, "en-US");
        } catch (MalformedURLException e) {
            Log.e(ERROR_TAG, "Malformed URL" + e.getMessage());
        }


        View movieCollection = view.findViewById(id);

        TextView textView = movieCollection.findViewById(R.id.colection_name_text_view);
        textView.setText(name);

        MaterialButton button = movieCollection.findViewById(R.id.show_button);
        button.setTag("showAllButton" + name);
        button.setOnClickListener(v -> {
            ShowAllFragment showAllFragment = ShowAllFragment.newInstance(listType);
            notifyMainActivityFragmentIsActive(showAllFragment);
        });

        RecyclerView recyclerView = movieCollection.findViewById(R.id.recycler_view);
        recyclerView.setTag("recyclerView" + name);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);


        List<Movie> dataSet = new ArrayList<>();
        if(task != null) {
            try {
                PopularMovies movies = task.get();
                if(movies != null)
                    dataSet = movies.getResults().subList(0, 10);
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


        MovieRatingsAdapter mAdapter = new MovieRatingsAdapter(dataSet, (v, position, movie) -> {
            MovieFragment movieFragment = MovieFragment.newInstance(movie.getId());
            notifyMainActivityFragmentIsActive(movieFragment);
        });

        recyclerView.setAdapter(mAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void notifyMainActivityFragmentIsActive(Fragment fragment) {
        FragmentActivity activity = getActivity();
        if(activity != null)
            ((MainActivity)activity).setExploreTabActiveFragment(fragment);
    }


}

package ru.nsu.fit.g16201.kinopoisklite.fragments.tabfragments;

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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.API;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.PagedMovieListTask;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Movie;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.PopularMovies;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.listloader.ListType;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.listloader.PagedListLoader;
import ru.nsu.fit.g16201.kinopoisklite.MainActivity;
import ru.nsu.fit.g16201.kinopoisklite.MovieRatingsAdapter;
import ru.nsu.fit.g16201.kinopoisklite.R;
import ru.nsu.fit.g16201.kinopoisklite.RecyclerViewMovieClickListener;
import ru.nsu.fit.g16201.kinopoisklite.fragments.MovieFragment;
import ru.nsu.fit.g16201.kinopoisklite.fragments.ShowAllFragment;

public class ExploreFragment extends Fragment {
    public ExploreFragment() {
    }

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_explore, container, false);

        configureMovieCollection(R.id.popular_movie_collection, "Popular", ListType.POPULAR);
        configureMovieCollection(R.id.trending_movie_collection, "Trending", ListType.TRENDING);


        return view;
    }



    private void configureMovieCollection(int id, String name, ListType listType)
    {
        PagedMovieListTask task = null;
        try {
            task = PagedListLoader.loadList(listType, 1, "en-US");
            if(task != null)
                task.execute();
        } catch (MalformedURLException e) {
            Log.e("ExploreFragment", "Malformed URL" + e.getMessage());
        }


        View movieCollection = view.findViewById(id);

        TextView textView = movieCollection.findViewById(R.id.colection_name_text_view);
        textView.setText(name);

        MaterialButton button = movieCollection.findViewById(R.id.show_button);

        button.setOnClickListener(v -> {
            ShowAllFragment showAllFragment = ShowAllFragment.newInstance(listType);
            notifyMainActivityFragmentIsActive(showAllFragment);
        });

        RecyclerView recyclerView = movieCollection.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL)); //todo :удалить
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL)); //todo :удалить

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);


        List<Movie> dataSet = new ArrayList<>();
        if(task != null) {
            try {
                PopularMovies movies = task.get();
                if(movies != null)
                    dataSet = movies.getResults();
            } catch (ExecutionException | InterruptedException e) {
                Log.e("ExploreFragment", "Can't retrieve data: " + e.getMessage());
            }
        }


        MovieRatingsAdapter mAdapter = new MovieRatingsAdapter(dataSet, new RecyclerViewMovieClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position, Movie movie) {
                MovieFragment movieFragment = MovieFragment.newInstance(movie.getId());
                notifyMainActivityFragmentIsActive(movieFragment);
            }
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

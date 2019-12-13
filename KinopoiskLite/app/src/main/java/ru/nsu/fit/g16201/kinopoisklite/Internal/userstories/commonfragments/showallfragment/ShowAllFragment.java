package ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.commonfragments.showallfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.PagedMovieListTask;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Movie;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.PopularMovies;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.listloader.ListType;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.listloader.PagedListLoader;
import ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.commonfragments.MovieFragment;
import ru.nsu.fit.g16201.kinopoisklite.MainActivity;
import ru.nsu.fit.g16201.kinopoisklite.R;

public class ShowAllFragment extends Fragment {
    private PagedMovieListTask task = null;

    private static final String ERROR_TAG = "ShowAllFragment";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            ListType type = (ListType) bundle.getSerializable("type");

            try {
                task = PagedListLoader.loadList(type, 1, "en-US");
            } catch (MalformedURLException e) {
                Log.e(ERROR_TAG, "Malformed URL" + e.getMessage());
            }
        }
    }

    public static ShowAllFragment newInstance(ListType type) {
        ShowAllFragment showAllFragment = new ShowAllFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("type", type);
        showAllFragment.setArguments(bundle);

        return showAllFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_all, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(verticalLayoutManager);

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



        MovieListAdapter mAdapter = new MovieListAdapter(dataSet, (v, position, movie) -> {
            MovieFragment movieFragment = MovieFragment.newInstance(movie.getId());
            notifyMainActivityFragmentIsActive(movieFragment);
        });

        recyclerView.setAdapter(mAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    private void notifyMainActivityFragmentIsActive(MovieFragment movieFragment) {
        FragmentActivity activity = getActivity();
        if(activity != null)
            ((MainActivity)activity).setExploreTabActiveFragment(movieFragment);
    }


}

package ru.nsu.fit.g16201.kinopoisklite.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.PagedMovieListTask;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Movie;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.PopularMovies;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.listloader.ListType;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.listloader.PagedListLoader;
import ru.nsu.fit.g16201.kinopoisklite.MainActivity;
import ru.nsu.fit.g16201.kinopoisklite.MovieListAdapter;
import ru.nsu.fit.g16201.kinopoisklite.R;
import ru.nsu.fit.g16201.kinopoisklite.RecyclerViewMovieClickListener;

public class ShowAllFragment extends Fragment {


    private ListType type;
    private PagedMovieListTask task = null;

    public ShowAllFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            this.type = (ListType)bundle.getSerializable("type");

            try {
                task = PagedListLoader.loadList(type, 1, "en-US");
            } catch (MalformedURLException e) {
                Log.e("ShowAllFragment", "Malformed URL" + e.getMessage());
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
            catch (ExecutionException | InterruptedException e)
            {
                Log.e("ShowAllFragment", "Can't retrieve data: " + e.getMessage());
            }
        }
        /*Movie m;
        {
            m = new Movie();
            m.setId(333);
            m.setTitle("Hello");
            dataSet.add(m);
            m = new Movie();
            m.setId(521);
            m.setTitle("Gggggg");
            dataSet.add(m);
            m = new Movie();
            m.setId(932);
            m.setTitle("dfdfsfsdf");
            dataSet.add(m);
            m = new Movie();
            m.setId(113);
            m.setTitle("fsdfsdfsdfd");
            dataSet.add(m);
            m = new Movie();
            m.setId(223);
            m.setTitle("fgfdgfdgfdg");
            dataSet.add(m);
        }*/

        PagedMovieListTask task = null;
        try {
            task = PagedListLoader.loadList(type, 1, "en-US");
        } catch (MalformedURLException e) {
            Log.e("ExploreFragment", "Malformed URL" + e.getMessage());
        }

        MovieListAdapter mAdapter = new MovieListAdapter(dataSet, new RecyclerViewMovieClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position, Movie movie) {
                MovieFragment movieFragment = MovieFragment.newInstance(movie.getId());   //todo: передавать что-то, что опзволит получить нунные фильмы
                notifyMainActivityFragmentIsActive(movieFragment);
            }
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

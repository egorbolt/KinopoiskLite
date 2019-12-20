package ru.nsu.fit.g16201.kinopoisklite.internal.userstories.commonfragments.showallfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.net.MalformedURLException;
import java.util.concurrent.Executors;

import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.api.tasks.PagedMovieListTask;
import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.models.Movie;
import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.listloader.ListType;
import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.listloader.PagedListLoader;
import ru.nsu.fit.g16201.kinopoisklite.internal.userstories.commonfragments.moviefragment.MovieFragment;
import ru.nsu.fit.g16201.kinopoisklite.MainActivity;
import ru.nsu.fit.g16201.kinopoisklite.R;

public class SearchFragment extends Fragment {
    private PagedMovieListTask task = null;

    private static final String ERROR_TAG = "ShowAllFragment";
    private SearchMovieDataSource dataSource;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            String query = bundle.getString("query");
            dataSource = new SearchMovieDataSource(query);

            try {
                task = ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.api.API.loadSearchedResult(query, "en-US");
            } catch (MalformedURLException e) {
                Log.e(ERROR_TAG, "Malformed URL" + e.getMessage());
            }
        }
    }

    public static SearchFragment newInstance(String query) {
        SearchFragment showAllFragment = new SearchFragment();

        Bundle bundle = new Bundle();
        bundle.putString("query", query);
        showAllFragment.setArguments(bundle);

        return showAllFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_all, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.showAllRecyclerView);
        recyclerView.setTag("showAllRecyclerView");
        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(verticalLayoutManager);

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(20)
                .build();

        PagedList<Movie> pagedList = new PagedList.Builder<>(dataSource, config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .setNotifyExecutor(new MainThreadExecutor())
                .build();
        MoviePagedListAdapter adapter = new MoviePagedListAdapter(new DiffUtil.ItemCallback<Movie>() {
            @Override
            public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
                return oldItem.getId().equals(newItem.getId());
            }
        },
                (v, position, movie) -> {
                    MovieFragment movieFragment = MovieFragment.newInstance(movie.getId());
                    notifyMainActivityFragmentIsActive(movieFragment);
                });
        adapter.submitList(pagedList);

        recyclerView.setAdapter(adapter);


        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    private void notifyMainActivityFragmentIsActive(MovieFragment movieFragment) {
        FragmentActivity activity = getActivity();
        if(activity != null)
            ((MainActivity)activity).setSearchFragmentActive(movieFragment);
    }


}

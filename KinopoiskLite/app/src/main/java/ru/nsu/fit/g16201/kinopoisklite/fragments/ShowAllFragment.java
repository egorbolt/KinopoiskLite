package ru.nsu.fit.g16201.kinopoisklite.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Movie;
import ru.nsu.fit.g16201.kinopoisklite.MovieListAdapter;
import ru.nsu.fit.g16201.kinopoisklite.R;
import ru.nsu.fit.g16201.kinopoisklite.RecyclerViewMovieClickListener;
import ru.nsu.fit.g16201.kinopoisklite.fragments.tabfragments.ExploreFragment;

public class ShowAllFragment extends Fragment {

    private MovieFragment movieFragment;
    private Fragment activeFragment;

    public ShowAllFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            System.out.println("gfgdfgdfgfdg");
        }
    }

    public static ShowAllFragment newInstance(String key) {
        ShowAllFragment showAllFragment = new ShowAllFragment();

        Bundle bundle = new Bundle();
        bundle.putString("key", key);
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
        Movie m;
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
        }
        MovieListAdapter mAdapter = new MovieListAdapter(dataSet, getContext(), new RecyclerViewMovieClickListener() {
            @Override
            public void recyclerViewListClicked(View v, int position, Movie movie) {
                //System.out.println(movie.getTitle());

                //FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                movieFragment = MovieFragment.newInstance(movie.getId());   //todo: передавать что-то, что опзволит получить нунные фильмы
                activeFragment = movieFragment;
                //notifyMainActivityMovieFragmentIsActive(showAllFragment);
                //fragmentTransaction.hide(ShowAllFragment.this).show(movieFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
            }
        });

        recyclerView.setAdapter(mAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }


}

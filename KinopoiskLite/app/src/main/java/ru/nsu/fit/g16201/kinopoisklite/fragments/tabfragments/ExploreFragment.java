package ru.nsu.fit.g16201.kinopoisklite.fragments.tabfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import ru.nsu.fit.g16201.kinopoisklite.MainActivity;
import ru.nsu.fit.g16201.kinopoisklite.MovieRatingsAdapter;
import ru.nsu.fit.g16201.kinopoisklite.R;
import ru.nsu.fit.g16201.kinopoisklite.fragments.ShowAllFragment;

public class ExploreFragment extends Fragment {
    public ExploreFragment() {
    }

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_explore, container, false);

        String[] popularDataSet = {
                "5.6", "7.8", "4.5", "8.6", "5.5"
        };
        configureMovieCollection(R.id.popular_movie_collection, popularDataSet, "Popular");

        String[] anotherDataSet = {
                "6.6", "1.8", "4.5", "8.4", "5.7"
        };
        configureMovieCollection(R.id.another_movie_collection, anotherDataSet, "Another");


        return view;
    }


    private void configureMovieCollection(int id, String[] dataSet, String name)
    {
        View movieCollection = view.findViewById(id);

        TextView textView = movieCollection.findViewById(R.id.colection_name_text_view);
        textView.setText(name);

        MaterialButton button = movieCollection.findViewById(R.id.show_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowAllFragment showAllFragment = ShowAllFragment.newInstance("abc");
                //todo: передавать что-то, что опзволит получить нунные фильмы
                notifyMainActivityFragmentIsActive(showAllFragment);
            }
        });

        RecyclerView recyclerView = movieCollection.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL)); //todo :удалить
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL)); //todo :удалить

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);

        MovieRatingsAdapter mAdapter = new MovieRatingsAdapter(dataSet);

        recyclerView.setAdapter(mAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void notifyMainActivityFragmentIsActive(ShowAllFragment showAllFragment) {
        FragmentActivity activity = getActivity();
        if(activity != null)
            ((MainActivity)activity).setExploreTabActiveFragment(showAllFragment);
    }


}

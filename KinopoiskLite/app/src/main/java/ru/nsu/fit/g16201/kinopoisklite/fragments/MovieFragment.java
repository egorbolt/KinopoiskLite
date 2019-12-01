package ru.nsu.fit.g16201.kinopoisklite.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Movie;
import ru.nsu.fit.g16201.kinopoisklite.R;

public class MovieFragment extends Fragment {

    private Movie movie;



    public MovieFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            System.out.println("NNNNN");
        }
    }

    public static MovieFragment newInstance(int id) {
        MovieFragment movieFragment = new MovieFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        movieFragment.setArguments(bundle);

        return movieFragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        return view;
    }

}

package ru.nsu.fit.g16201.kinopoisklite.fragments.tabfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.nsu.fit.g16201.kinopoisklite.R;

public class RandomFragment extends Fragment {
    private TextView movieDescription;
    private ImageView moviePoster;

    public RandomFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_random, container, false);

        movieDescription = view.findViewById (R.id.rmDescription);
        moviePoster = view.findViewById(R.id.rmImageView);



        return view;
    }

    //todo: newInstance?
}

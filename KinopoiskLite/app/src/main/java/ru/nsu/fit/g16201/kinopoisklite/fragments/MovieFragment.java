package ru.nsu.fit.g16201.kinopoisklite.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.nsu.fit.g16201.kinopoisklite.R;

public class MovieFragment extends Fragment {
    public MovieFragment() {}


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
        View view = inflater.inflate(R.layout.fragment_lists, container, false);
        return view;
    }

}
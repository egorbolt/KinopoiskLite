package ru.nsu.fit.g16201.kinopoisklite.fragments.tabfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.nsu.fit.g16201.kinopoisklite.R;

public class ListsFragment extends Fragment {
    public ListsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lists, container, false);
        return view;
    }

    //todo: newInstance?
}

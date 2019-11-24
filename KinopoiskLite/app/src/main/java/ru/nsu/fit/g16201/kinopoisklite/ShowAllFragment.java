package ru.nsu.fit.g16201.kinopoisklite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ShowAllFragment extends Fragment {

    private OnChangeMovieListListener callback;

    public ShowAllFragment()
    {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_all, container, false);

        //View movieCollection = view.findViewById(id);

        return view;
    }


    public void setOnChangeMovieListListener(OnChangeMovieListListener callback) {
        this.callback = callback;
    }

    // This interface can be implemented by the Activity, parent Fragment,
    // or a separate test implementation.
    public interface OnChangeMovieListListener {
        public void onArticleSelected(int position);
    }

}

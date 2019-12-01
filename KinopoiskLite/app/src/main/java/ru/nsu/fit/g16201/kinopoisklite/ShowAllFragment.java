package ru.nsu.fit.g16201.kinopoisklite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShowAllFragment extends Fragment {

    public ShowAllFragment()
    {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            System.out.println("gfgdfgdfgfdg");
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_all, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(verticalLayoutManager);

        String[] dataSet = {
                "6.6", "1.8", "4.5", "8.4", "5.7"
        };

        MovieListAdapter mAdapter = new MovieListAdapter(dataSet);

        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }


}

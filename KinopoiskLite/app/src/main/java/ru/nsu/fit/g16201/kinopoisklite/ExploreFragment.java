package ru.nsu.fit.g16201.kinopoisklite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ExploreFragment extends Fragment {
    public ExploreFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        RecyclerView popularRecyclerView = view.findViewById(R.id.popular_list_view).findViewById(R.id.recycler_view);
        String[] popularDataSet = {
                "5.6", "7.8", "4.5", "8.6", "5.5"
        };
        configureRecyclerView(popularRecyclerView, popularDataSet);

        RecyclerView anotherRecyclerView = view.findViewById(R.id.another_list_view).findViewById(R.id.recycler_view);
        String[] anotherDataSet = {
                "6.6", "1.8", "4.5", "8.4", "5.7"
        };
        configureRecyclerView(anotherRecyclerView, anotherDataSet);

        return view;
    }

    //todo: newInstance?

    private void configureRecyclerView(RecyclerView recyclerView, String[] dataSet)
    {
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL)); //todo :удалить
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL)); //todo :удалить

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);

        // 3. create an adapter
        MovieAdapter mAdapter = new MovieAdapter(dataSet);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}

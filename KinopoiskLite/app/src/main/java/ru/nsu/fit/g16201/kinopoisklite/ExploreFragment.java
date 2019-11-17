package ru.nsu.fit.g16201.kinopoisklite;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

public class ExploreFragment extends Fragment implements SearchView.OnQueryTextListener {
    public ExploreFragment() {
    }

    final Fragment showAllFragment = new ShowAllFragment();

    final FragmentManager fm = getActivity().getSupportFragmentManager();

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_explore, container, false);
        setHasOptionsMenu(true);


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
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.hide(ExploreFragment.this).show(showAllFragment).commit();
            }
        });

        RecyclerView recyclerView = movieCollection.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL)); //todo :удалить
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL)); //todo :удалить

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);

        MovieAdapter mAdapter = new MovieAdapter(dataSet);

        recyclerView.setAdapter(mAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        //todo

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        //todo

        return false;
    }

    //todo: newInstance?
}

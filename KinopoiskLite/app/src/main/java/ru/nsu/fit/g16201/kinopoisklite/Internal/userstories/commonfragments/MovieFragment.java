package ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.commonfragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.API;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.MovieInfoTask;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.PagedMovieListTask;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.UrlConstructor;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Genre;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Movie;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.MovieInfo;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.PopularMovies;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.listloader.ListType;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.listloader.PagedListLoader;
import ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.commonfragments.showallfragment.ShowAllFragment;
import ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.reusables.MovieRatingsAdapter;
import ru.nsu.fit.g16201.kinopoisklite.MainActivity;
import ru.nsu.fit.g16201.kinopoisklite.R;

public class MovieFragment extends Fragment {

    private static final String ERROR_TAG = "MovieFragment";

    private View view;

    private MovieInfoTask task;

    private MovieFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            try {
                task = API.loadMovieInfo(bundle.getInt("id"), "en-US");
            } catch (MalformedURLException e) {
                Log.e("MovieFragment", "Malformed URL" + e.getMessage());
            }
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
        view = inflater.inflate(R.layout.fragment_movie, container, false);

        ImageView imageView = view.findViewById(R.id.movie_poster_image_view);

        TextView ratingBadge = view.findViewById(R.id.badge_rating);

        TextView movieTitle = view.findViewById(R.id.textViewMovieTitle);

        TextView movieDescription = view.findViewById(R.id.description_text_card).findViewById(R.id.card_text);
        ((TextView)view.findViewById(R.id.description_text_card).findViewById(R.id.card_name_text_view)).setText("Description");

        TextView movieGenres = view.findViewById(R.id.genres_text_card).findViewById(R.id.card_text);
        ((TextView)view.findViewById(R.id.genres_text_card).findViewById(R.id.card_name_text_view)).setText("Genres");



        MovieInfo movieInfo = null;
        try {
            movieInfo = task.get();
        }
        catch (InterruptedException e)
        {
            Log.e("MovieFragment", "Can't retrieve data: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        catch (ExecutionException e)
        {
            Log.e("MovieFragment", "Can't retrieve data: " + e.getMessage());
        }

        if(movieInfo != null)
        {
            configureMovieCollection(R.id.similar_movie_collection, "Similar movie", ListType.SIMILAR, movieInfo.getId());


            movieTitle.setText(movieInfo.getTitle());
            ratingBadge.setText(Double.toString(movieInfo.getVoteAverage()));
            if(movieInfo.getOverview().isPresent())
                movieDescription.setText(movieInfo.getOverview().get());
            if(movieInfo.getPosterPath().isPresent())
                Picasso.get().load(UrlConstructor.urlSingleImage(movieInfo.getPosterPath().get())).into(imageView);
            List<Genre> genres = movieInfo.getGenres();
            if(genres != null)
                movieGenres.setText(genres.stream().map(Genre::getName).collect(Collectors.joining(", ")));
        }

        return view;
    }

    private void configureMovieCollection(int id, String name, ListType listType, Integer movieId)
    {
        PagedMovieListTask task = null;
        try {
            task = PagedListLoader.loadParametrisedList(listType, 1, movieId, "en-US");
        } catch (MalformedURLException e) {
            Log.e(ERROR_TAG, "Malformed URL" + e.getMessage());
        }


        View movieCollection = view.findViewById(id);

        TextView textView = movieCollection.findViewById(R.id.colection_name_text_view);
        textView.setText(name);

        MaterialButton button = movieCollection.findViewById(R.id.show_button);
        button.setTag("showAllButton" + name);
        button.setOnClickListener(v -> {
            ShowAllFragment showAllFragment = ShowAllFragment.newInstance(listType, movieId);
            notifyMainActivityFragmentIsActive(showAllFragment);
        });

        RecyclerView recyclerView = movieCollection.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);


        List<Movie> dataSet = new ArrayList<>();
        if(task != null) {
            try {
                PopularMovies movies = task.get();
                if(movies != null) {
                    List<Movie> movieList = movies.getResults();
                    dataSet = movieList.subList(0, 10 < movieList.size() ? 10 : movieList.size());
                }
            }
            catch (InterruptedException e)
            {
                Log.e(ERROR_TAG, "Can't retrieve data: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
            catch (ExecutionException e)
            {
                Log.e(ERROR_TAG, "Can't retrieve data: " + e.getMessage());
            }
        }


        MovieRatingsAdapter mAdapter = new MovieRatingsAdapter(dataSet, (v, position, movie) -> {
            MovieFragment movieFragment = MovieFragment.newInstance(movie.getId());
            notifyMainActivityFragmentIsActive(movieFragment);
        });

        recyclerView.setAdapter(mAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    private void notifyMainActivityFragmentIsActive(Fragment fragment) {
        FragmentActivity activity = getActivity();
        if(activity != null)
            ((MainActivity)activity).setExploreTabActiveFragment(fragment);
    }
}

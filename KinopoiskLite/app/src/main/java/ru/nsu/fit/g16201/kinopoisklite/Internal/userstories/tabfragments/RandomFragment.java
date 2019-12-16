package ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.tabfragments;

import android.os.AsyncTask;
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

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.API;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.GenresListTask;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.PagedMovieListTask;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.UrlConstructor;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Genre;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.GenreList;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Movie;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.PopularMovies;
import ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.commonfragments.moviefragment.MovieFragment;
import ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.commonfragments.showallfragment.ShowAllFragment;
import ru.nsu.fit.g16201.kinopoisklite.MainActivity;
import ru.nsu.fit.g16201.kinopoisklite.R;

public class RandomFragment extends Fragment {
    private static final String ERROR_TAG = "RandomFragment";
    private TextView movieTitle, ratingBadge, movieDescription;
    private ImageView moviePoster;
    private MaterialButton button;
    public RandomFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_random, container, false);

        moviePoster = view.findViewById(R.id.movie_poster_image_view);

        ratingBadge = view.findViewById(R.id.badge_rating);

        movieTitle = view.findViewById(R.id.textViewMovieTitle);

        movieDescription = view.findViewById(R.id.description_text_card).findViewById(R.id.card_text);

        ((TextView)view.findViewById(R.id.description_text_card).findViewById(R.id.card_name_text_view)).setText("Description");

        MaterialButton button = view.findViewById(R.id.next_button);
        button.setTag("showAllButtonRandom");
        button.setOnClickListener(v -> {
            loadRandom();
        });

        loadRandom();

        return view;
    }

    static class LoadRandomMovieTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... parameter) {
            GenresListTask genresListTask = null;
            try {
                genresListTask = API.loadGenreList("en-US");
            } catch (MalformedURLException e) {
                Log.e(ERROR_TAG, "Malformed URL" + e.getMessage());
            }
            if(genresListTask != null)
            {
                try {
                    GenreList genreList = genresListTask.get();
                    List<Genre> genres = genreList.getList();

                    Genre genre = genres.get(new Random().nextInt(genres.size()));

                } catch (ExecutionException e) {
                    Log.e(ERROR_TAG, "Can't retrieve data: " + e.getMessage());
                } catch (InterruptedException e) {
                    Log.e(ERROR_TAG, "Can't retrieve data: " + e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }

            return null;
        }

        /*@Override
        protected void onPostExecute(Integer result) {
            // [... Сообщите о результате через обновление пользовательского
            // интерфейса, диалоговое окно или уведомление ...]
        }*/
    }


    private void loadRandom() {
        GenresListTask genresListTask = null;
        try {
            genresListTask = API.loadGenreList("en-US");
        } catch (MalformedURLException e) {
            Log.e(ERROR_TAG, "Malformed URL" + e.getMessage());
        }
        if(genresListTask != null)
        {
            try {
                GenreList genreList = genresListTask.get();
                List<Genre> genres = genreList.getList();

                Genre genre = genres.get(new Random().nextInt(genres.size()));
                PagedMovieListTask moviesTask = API.loadMoviesByGenre(new Random().nextInt(20), genre.getId(), "en-US");
                PopularMovies movies = moviesTask.get();
                List<Movie> movieList = movies.getResults();

                Movie movie = movieList.get(new Random().nextInt(movieList.size()));

                movieTitle.setText(movie.getTitle());

                if(movie.getVoteAverage() != 0) {
                    ratingBadge.setText(Double.toString(movie.getVoteAverage()));
                    ratingBadge.setVisibility(View.VISIBLE);
                }
                else
                    ratingBadge.setVisibility(View.GONE);

                if(movie.getOverview() != null)
                    movieDescription.setText(movie.getOverview());

                if(movie.getPosterPath().isPresent())
                    Picasso.get().load(UrlConstructor.urlSingleImage(movie.getPosterPath().get())).into(moviePoster);
                moviePoster.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MovieFragment movieFragment = MovieFragment.newInstance(movie.getId());
                        notifyMainActivityFragmentIsActive(movieFragment);                    }
                });

            } catch (ExecutionException e) {
                Log.e(ERROR_TAG, "Can't retrieve data: " + e.getMessage());
            } catch (InterruptedException e) {
                Log.e(ERROR_TAG, "Can't retrieve data: " + e.getMessage());
                Thread.currentThread().interrupt();
            } catch (MalformedURLException e) {
                Log.e(ERROR_TAG, "Malformed URL" + e.getMessage());
            }
        }

    }

    private void notifyMainActivityFragmentIsActive(Fragment fragment) {
        FragmentActivity activity = getActivity();
        if(activity != null)
            ((MainActivity)activity).setRandomTabActiveFragment(fragment);
    }

}

package ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.commonfragments.moviefragment;

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
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import me.relex.circleindicator.CircleIndicator;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.API;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.LoadCreditsTask;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.MovieInfoTask;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.PagedMovieListTask;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.PersonImagesTask;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.PicturesTask;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.UrlConstructor;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Actor;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.CrewPerson;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Genre;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Movie;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.MovieInfo;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.MovieRelatedImages;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Pictures;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.PopularMovies;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.Team;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.listloader.ListType;
import ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.commonfragments.showallfragment.ShowAllFragment;
import ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.reusables.GalleryPagerAdapter;
import ru.nsu.fit.g16201.kinopoisklite.Internal.userstories.reusables.MovieRatingsAdapter;
import ru.nsu.fit.g16201.kinopoisklite.MainActivity;
import ru.nsu.fit.g16201.kinopoisklite.R;

public class MovieFragment extends Fragment {

    private static final String ERROR_TAG = "MovieFragment";

    private View view;

    private MovieInfoTask movieInfoTask;
    private PagedMovieListTask similarMoviesTask;
    private PicturesTask picturesTask;
    private LoadCreditsTask creditsTask;

    public MovieFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            try {
                int id = bundle.getInt("id");
                movieInfoTask = API.loadMovieInfo(id, "en-US");
                similarMoviesTask = API.loadSimilar(1, id, "en-US");
                picturesTask = API.loadMoviePictures(id);
                creditsTask = API.loadCredits(id, "en-US");
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

        TextView releaseDateTextView = view.findViewById(R.id.year_text_card).findViewById(R.id.card_text);
        ((TextView)view.findViewById(R.id.year_text_card).findViewById(R.id.card_name_text_view)).setText("Release Date");

        TextView budgetTextView = view.findViewById(R.id.budget_text_card).findViewById(R.id.card_text);
        ((TextView)view.findViewById(R.id.budget_text_card).findViewById(R.id.card_name_text_view)).setText("Budget");

        TextView staffTextView = view.findViewById(R.id.staff_text_card).findViewById(R.id.card_text);
        ((TextView)view.findViewById(R.id.staff_text_card).findViewById(R.id.card_name_text_view)).setText("Crew");

        MovieInfo movieInfo = null;
        Team team = null;
        try {
            movieInfo = movieInfoTask.get();
            team = creditsTask.get();
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

        if(movieInfo != null && team != null)
        {
            configureMovieCollection(movieInfo.getId());
            configureGallery(movieInfo.getId());
            configureActorsCollection(movieInfo.getId(), team);

            movieTitle.setText(movieInfo.getTitle());

            if(movieInfo.getVoteAverage() != 0) {
                ratingBadge.setText(Double.toString(movieInfo.getVoteAverage()));
                ratingBadge.setVisibility(View.VISIBLE);
            }
            else
                ratingBadge.setVisibility(View.GONE);

            if(movieInfo.getOverview().isPresent())
                movieDescription.setText(movieInfo.getOverview().get());
            if(movieInfo.getPosterPath().isPresent())
                Picasso.get().load(UrlConstructor.urlSingleImage(movieInfo.getPosterPath().get())).into(imageView);
            List<Genre> genres = movieInfo.getGenres();
            if(genres != null)
                movieGenres.setText(genres.stream().map(Genre::getName).collect(Collectors.joining(", ")));

            if(movieInfo.getReleaseDate() != null && !movieInfo.getReleaseDate().equals(""))
                releaseDateTextView.setText(movieInfo.getReleaseDate());
            else
                budgetTextView.setText("No information about release date");

            if(movieInfo.getBudget() != 0)
                budgetTextView.setText(String.format("$%d", movieInfo.getBudget()));
            else
                budgetTextView.setText("No information about budget");

            List<CrewPerson> crew = team.getCrew();
            if(crew != null)
                staffTextView.setText(crew.stream().map(crewPerson -> String.format("%s: %s", crewPerson.getJob(), crewPerson.getName())).collect(Collectors.joining("\n")));
        }

        return view;
    }

    private void configureGallery(Integer movieId)
    {
        View picturesGalleryCard = view.findViewById(R.id.images_gallery_card);

        List<MovieRelatedImages> dataSet = new ArrayList<>();

        try {
            Pictures pictures = picturesTask.get();
            if(pictures != null) {
                List<MovieRelatedImages> picturesList = pictures.getBackdrops();
                if(picturesList.isEmpty())
                {
                    picturesGalleryCard.setVisibility(View.GONE);
                    return;
                }
                dataSet = picturesList.subList(0, 10 < picturesList.size() ? 10 : picturesList.size());
            }
        }
        catch (InterruptedException e)
        {
            Log.e(ERROR_TAG + ": Gallery", "Can't retrieve data: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        catch (ExecutionException e)
        {
            Log.e(ERROR_TAG + ": Gallery", "Can't retrieve data: " + e.getMessage());
        }

        GalleryPagerAdapter galleryPagerAdapter = new GalleryPagerAdapter(dataSet, getContext());

        ViewPager viewPager = picturesGalleryCard.findViewById(R.id.view_pager);
        viewPager.setAdapter(galleryPagerAdapter);

        CircleIndicator circleIndicator = picturesGalleryCard.findViewById(R.id.circleIndicator);
        circleIndicator.setViewPager(viewPager);
    }

    private void configureMovieCollection(Integer movieId)
    {
        View movieCollection = view.findViewById(R.id.similar_movie_collection);

        TextView textView = movieCollection.findViewById(R.id.colection_name_text_view);
        textView.setText("Similar movies");

        MaterialButton button = movieCollection.findViewById(R.id.show_button);
        button.setTag("showAllButton" + "Similar movies");
        button.setOnClickListener(v -> {
            ShowAllFragment showAllFragment = ShowAllFragment.newInstance(ListType.SIMILAR, movieId);
            notifyMainActivityFragmentIsActive(showAllFragment);
        });

        RecyclerView recyclerView = movieCollection.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);


        List<Movie> dataSet = new ArrayList<>();
        if(similarMoviesTask != null) {
            try {
                PopularMovies movies = similarMoviesTask.get();
                if(movies != null) {
                    List<Movie> movieList = movies.getResults();
                    if(movieList.isEmpty())
                    {
                        movieCollection.setVisibility(View.GONE);
                        return;
                    }
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

    private void configureActorsCollection(Integer movieId, Team team)
    {
        View actorsCollection = view.findViewById(R.id.actors_collection);
        TextView textView = actorsCollection.findViewById(R.id.colection_name_text_view);
        textView.setText("Actors");

        MaterialButton button = actorsCollection.findViewById(R.id.show_button);
        button.setVisibility(View.GONE);

        RecyclerView recyclerView = actorsCollection.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);


        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);


        List<Actor> actorList = team.getCast();
        if(actorList.isEmpty())
        {
            actorsCollection.setVisibility(View.GONE);
            return;
        }
        List<Actor> dataSet =  actorList.subList(0, 10 < actorList.size() ? 10 : actorList.size());

        List<PersonImagesTask> personImagesTasks = dataSet.stream().map((Actor actor) -> {
            try {
                return API.loadActorPictures(actor.getId());
            } catch (MalformedURLException e) {
                Log.e(ERROR_TAG, "Malformed URL" + e.getMessage());
            }
            return null;
        }).collect(Collectors.toList());
        ActorListAdapter mAdapter = new ActorListAdapter(dataSet, personImagesTasks, getContext());

        recyclerView.setAdapter(mAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    private void notifyMainActivityFragmentIsActive(Fragment fragment) {
        FragmentActivity activity = getActivity();
        if(activity != null)
            ((MainActivity)activity).setExploreTabActiveFragment(fragment);
    }
}

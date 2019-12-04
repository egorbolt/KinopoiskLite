package ru.nsu.fit.g16201.kinopoisklite.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.API;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models.MovieInfo;
import ru.nsu.fit.g16201.kinopoisklite.R;

public class MovieFragment extends Fragment {

    private MovieInfo movieInfo;

    public MovieFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            try {
                movieInfo = API.loadMovieInfo(bundle.getInt("id"), "en-US").get();
            } catch (MalformedURLException | InterruptedException | ExecutionException e) {
                e.printStackTrace();
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
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        ImageView imageView = view.findViewById(R.id.movie_poster_image_view);
        TextView ratingBadge = view.findViewById(R.id.badge_rating);
        TextView movieTitle = view.findViewById(R.id.textViewMovieTitle);
        TextView movieDescription = view.findViewById(R.id.textViewMovieDescription);

        if(movieInfo != null)
        {
            movieTitle.setText(movieInfo.getTitle());
            ratingBadge.setText(Double.toString(movieInfo.getVoteAverage()));
            if(movieInfo.getOverview().isPresent())
                movieDescription.setText(movieInfo.getOverview().get());
            if(movieInfo.getPosterPath().isPresent())
                ;//Picasso.get().load(movieInfo.getPosterPath().get()).into(imageView);
        }

        return view;
    }

}

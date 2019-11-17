package ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API;

import com.squareup.moshi.Moshi;

import java.net.MalformedURLException;
import java.net.URL;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.MovieInfoTask;

public class API {
    public static final int TIMEOUT = 10000;
    public static Moshi moshi = new Moshi.Builder().build();

    //Загружает информацию о фильме по ID с выбранной локалью
    public static MovieInfoTask loadMovieInfo(int movieID, String language) throws MalformedURLException {
        URL url = new URL(UrlConstructor.urlMovieInfo(movieID,language));
        MovieInfoTask task = new MovieInfoTask();
        task.execute(url);
        return task;
    }
}

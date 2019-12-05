package ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API;

import com.squareup.moshi.Moshi;

import java.net.MalformedURLException;
import java.net.URL;

import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.MovieInfoTask;
import ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.API.Tasks.PagedMovieListTask;

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

    //Загружает информацию о фильмах в тренде постранично
    public static PagedMovieListTask loadTrendingList(int page, String language) throws MalformedURLException {
        URL url = new URL(UrlConstructor.urlTrending(page,language));
        PagedMovieListTask task = new PagedMovieListTask();
        task.execute(url);
        return task;
    }

    //Загружает информацию о фильмах в прокате
    public static PagedMovieListTask loadNowPlayingList(int page, String language) throws MalformedURLException {
        URL url = new URL(UrlConstructor.urlNowPlaying(page,language));
        PagedMovieListTask task = new PagedMovieListTask();
        task.execute(url);
        return task;
    }

    //Загружает информацию о популярных фильмах
    public static PagedMovieListTask loadPopularList(int page, String language) throws MalformedURLException {
        URL url = new URL(UrlConstructor.urlPopular(page,language));
        PagedMovieListTask task = new PagedMovieListTask();
        task.execute(url);
        return task;
    }


}

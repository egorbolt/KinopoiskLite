package ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.api;

import com.squareup.moshi.Moshi;

import java.net.MalformedURLException;
import java.net.URL;

import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.api.tasks.GenresListTask;
import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.api.tasks.LoadCreditsTask;
import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.api.tasks.MovieInfoTask;
import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.api.tasks.PagedMovieListTask;
import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.api.tasks.PersonImagesTask;
import ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.api.tasks.PicturesTask;

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

    //Загружает информацию о топе по оценкам
    public static PagedMovieListTask loadTopRated(int page, String language) throws MalformedURLException {
        URL url = new URL(UrlConstructor.urlTopRated(page,language));
        PagedMovieListTask task = new PagedMovieListTask();
        task.execute(url);
        return task;
    }

    //Загружает информацию о выходящих скоро фильмах
    public static PagedMovieListTask loadUpcoming(int page, String language) throws MalformedURLException {
        URL url = new URL(UrlConstructor.urlUpcoming(page,language));
        PagedMovieListTask task = new PagedMovieListTask();
        task.execute(url);
        return task;
    }

    public static PagedMovieListTask loadSimilar(int page, int id, String language) throws MalformedURLException {
        URL url = new URL(UrlConstructor.urlSimilarMovies(page, id, language));
        PagedMovieListTask task = new PagedMovieListTask();
        task.execute(url);
        return task;
    }

    public static GenresListTask loadGenreList(String language) throws MalformedURLException {
        URL url = new URL(UrlConstructor.urlGenreList(language));
        GenresListTask task = new GenresListTask();
        task.execute(url);
        return task;
    }

    public static PagedMovieListTask loadMoviesByGenre(int page, int genre, String language) throws MalformedURLException {
        URL url = new URL(UrlConstructor.urlMoviesListByGenre(page,genre,language));
        PagedMovieListTask task = new PagedMovieListTask();
        task.execute(url);
        return task;
    }

    public static LoadCreditsTask loadCredits(int id, String language)throws MalformedURLException {
        URL url = new URL(UrlConstructor.urlCredits(id, language));
        LoadCreditsTask task = new LoadCreditsTask();
        task.execute(url);
        return task;
    }

    public static PicturesTask loadMoviePictures(int id) throws MalformedURLException {
        URL url = new URL(UrlConstructor.urlMovieRelatedImages(id));
        PicturesTask task = new PicturesTask();
        task.execute(url);
        return task;
    }

    public static PersonImagesTask loadActorPictures(int id) throws MalformedURLException {
        URL url = new URL(UrlConstructor.urlPersonRelatedImages(id, "en-US"));
        PersonImagesTask task = new PersonImagesTask();
        task.execute(url);
        return task;
    }
}

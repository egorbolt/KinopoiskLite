package ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlConstructor {
    private static final String HOST = "https://api.themoviedb.org/3";
    private static final String APIKEY = "api_key=e4bc0f9cee69e195923579d0cf450c48";

    static String urlTrending(int page, String language) {
        return HOST+"/trending/movie/week?"+APIKEY+"&page="+page+"&language="+language;
    }

    static String urlNowPlaying(int page, String language) {
        return HOST+"/movie/now_playing?"+APIKEY+"&page="+page+"&language="+language;
    }

    static String urlGenreList(String language){
        return HOST+"/genre/movie/list?"+APIKEY+"&language="+language;
    }

    static String urlMoviesListByGenre(int page, int id, String language) {
        return HOST+"/discover/movie?"+APIKEY+"&include_adult=false" +
                "+&page="+page+"with_genres="+id+"&language="+language;
    }

    static String urlPopular(int page, String language) {
        return HOST+"/movie/popular?"+APIKEY+"&page="+page+"&language="+language;
    }

    static String urlTopRated(int page, String language) {
        return HOST+"/movie/top_rated?"+APIKEY+"&page="+page+"&language="+language;
    }

    static String urlUpcoming(int page, String language) {
        return HOST+"/movie/upcoming?"+APIKEY+"&page="+page+"&language="+language;
    }

    static String urlMovieInfo(int id, String language) {
        return HOST+"/movie/"+id+"?"+APIKEY+"&language="+language;
    }

    static String urlSimilarMovies(int page, int id, String language) {
        return HOST+"/movie/"+id+"/similar?"+APIKEY+"&page="+page+"&language="+language;
    }

    static String urlCredits(int id, String language) {
        return HOST+"/movie/"+id+"/credits"+"?"+APIKEY+"&language="+language;
    }

    static String urlMovieRelatedImages(int id){
        return HOST+"/movie/"+id+"/images?"+APIKEY;
    }

    static String urlPersonRelatedImages(int id, String language) {
        return HOST+"/person/"+id+"/images?"+APIKEY+"&language="+language;
    }

    public static String urlSingleImage(String image) {
        return "https://image.tmdb.org/t/p/w500/"+image;
    }

    static String urlSearchMovie(String query, String language) {
        try {
            String q = URLEncoder.encode(query,"utf-8");
            return HOST+"/search/movie?"+APIKEY+"&language="+language+"&query="+q;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

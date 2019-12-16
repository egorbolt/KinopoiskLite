package ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.models;

import com.squareup.moshi.Json;

import java.util.List;
import java.util.Optional;

public class Movie {
    @Json(name = "poster_path")private String posterPath; //Optionable
    private Boolean adult;
    private String overview;
    @Json(name = "release_date")private String releaseDate;
    @Json(name = "genre_ids")private List<Integer> genreIDs;
    private Integer id;
    @Json(name = "original_title")private String originalTitle;
    @Json(name = "original_language")private String originalLanguage;
    private String title;
    @Json(name = "backdrop_path")private String backdropPath; // Optionable
    private Double popularity;
    @Json(name = "vote_count")private Integer voteCount;
    private Boolean video;
    @Json(name = "vote_average")private Double voteAverage;

    public Optional<String> getPosterPath() {
        return Optional.ofNullable(posterPath);
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Integer> getGenreIDs() {
        return genreIDs;
    }

    public void setGenreIDs(List<Integer> genreIDs) {
        this.genreIDs = genreIDs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Optional<String> getBackdropPath() {
        return Optional.ofNullable(backdropPath);
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }
}

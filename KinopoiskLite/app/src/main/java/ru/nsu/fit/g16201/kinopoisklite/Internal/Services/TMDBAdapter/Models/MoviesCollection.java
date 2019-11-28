package ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models;

import com.squareup.moshi.Json;

import java.util.Optional;

public class MoviesCollection {

    private Integer id;
    @Json(name = "backdrop_path")private String backdropPath; //optional
    private String name;
    @Json(name = "poster_path")private String posterPath; //optional

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Optional<String> getBackdropPath() {
        return Optional.ofNullable(backdropPath);
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<String> getPosterPath() {
        return Optional.ofNullable(posterPath);
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}

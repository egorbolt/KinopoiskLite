package ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.models;

import java.util.List;

public class PersonMovies {
    private List<CastElement> cast;
    private List<CrewElement> crew;
    private Integer id;

    public List<CastElement> getCast() {
        return cast;
    }

    public void setCast(List<CastElement> cast) {
        this.cast = cast;
    }

    public List<CrewElement> getCrew() {
        return crew;
    }

    public void setCrew(List<CrewElement> crew) {
        this.crew = crew;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

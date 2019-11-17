package ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models;

import java.util.ArrayList;

public class PersonMovies {
    private ArrayList<CastElement> cast;
    private ArrayList<CrewElement> crew;
    private Integer id;

    public ArrayList<CastElement> getCast() {
        return cast;
    }

    public void setCast(ArrayList<CastElement> cast) {
        this.cast = cast;
    }

    public ArrayList<CrewElement> getCrew() {
        return crew;
    }

    public void setCrew(ArrayList<CrewElement> crew) {
        this.crew = crew;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

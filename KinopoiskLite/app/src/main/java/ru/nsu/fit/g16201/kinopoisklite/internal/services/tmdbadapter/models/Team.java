package ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.models;

import java.util.List;

public class Team {
    private Integer id;
    private List<Actor> cast;
    private List<CrewPerson> crew;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Actor> getCast() {
        return cast;
    }

    public void setCast(List<Actor> cast) {
        this.cast = cast;
    }

    public List<CrewPerson> getCrew() {
        return crew;
    }

    public void setCrew(List<CrewPerson> crew) {
        this.crew = crew;
    }
}

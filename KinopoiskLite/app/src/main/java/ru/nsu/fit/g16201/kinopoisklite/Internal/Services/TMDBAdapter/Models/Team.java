package ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models;

import java.util.ArrayList;

public class Team {
    private Integer id;
    private ArrayList<Actor> cast;
    private ArrayList<CrewPerson> crew;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Actor> getCast() {
        return cast;
    }

    public void setCast(ArrayList<Actor> cast) {
        this.cast = cast;
    }

    public ArrayList<CrewPerson> getCrew() {
        return crew;
    }

    public void setCrew(ArrayList<CrewPerson> crew) {
        this.crew = crew;
    }
}

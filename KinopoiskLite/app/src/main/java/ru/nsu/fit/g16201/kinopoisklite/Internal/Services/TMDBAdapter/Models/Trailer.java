package ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models;

import java.util.ArrayList;

public class Trailer {
    private Integer id;
    private ArrayList<TrailerInfo> results;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<TrailerInfo> getResults() {
        return results;
    }

    public void setResults(ArrayList<TrailerInfo> results) {
        this.results = results;
    }
}

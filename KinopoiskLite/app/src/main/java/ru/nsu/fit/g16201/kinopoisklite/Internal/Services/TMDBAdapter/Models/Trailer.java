package ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models;

import java.util.List;

public class Trailer {
    private Integer id;
    private List<TrailerInfo> results;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<TrailerInfo> getResults() {
        return results;
    }

    public void setResults(List<TrailerInfo> results) {
        this.results = results;
    }
}

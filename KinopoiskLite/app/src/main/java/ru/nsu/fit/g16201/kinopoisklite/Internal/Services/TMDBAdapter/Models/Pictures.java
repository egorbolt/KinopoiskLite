package ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models;

import java.util.ArrayList;

public class Pictures {
    private Integer id;
    private ArrayList<MovieRelatedImages> backdrops;
    private ArrayList<MovieRelatedImages> posters;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<MovieRelatedImages> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(ArrayList<MovieRelatedImages> backdrops) {
        this.backdrops = backdrops;
    }

    public ArrayList<MovieRelatedImages> getPosters() {
        return posters;
    }

    public void setPosters(ArrayList<MovieRelatedImages> posters) {
        this.posters = posters;
    }
}

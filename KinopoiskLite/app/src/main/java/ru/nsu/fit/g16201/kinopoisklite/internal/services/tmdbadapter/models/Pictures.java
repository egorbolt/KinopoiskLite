package ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.models;

import java.util.List;

public class Pictures {
    private Integer id;
    private List<MovieRelatedImages> backdrops;
    private List<MovieRelatedImages> posters;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MovieRelatedImages> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<MovieRelatedImages> backdrops) {
        this.backdrops = backdrops;
    }

    public List<MovieRelatedImages> getPosters() {
        return posters;
    }

    public void setPosters(List<MovieRelatedImages> posters) {
        this.posters = posters;
    }
}

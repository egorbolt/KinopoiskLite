package ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.models;

import java.util.List;

public class PersonImagesInfo {
    private Integer id;
    private List<Image> profiles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Image> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Image> profiles) {
        this.profiles = profiles;
    }
}

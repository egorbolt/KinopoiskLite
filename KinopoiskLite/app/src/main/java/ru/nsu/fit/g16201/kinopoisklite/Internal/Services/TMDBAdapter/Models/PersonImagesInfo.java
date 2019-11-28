package ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models;

import java.util.ArrayList;

public class PersonImagesInfo {
    private Integer id;
    private ArrayList<Image> profiles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Image> getProfiles() {
        return profiles;
    }

    public void setProfiles(ArrayList<Image> profiles) {
        this.profiles = profiles;
    }
}

package ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models;

import com.squareup.moshi.Json;

import java.util.ArrayList;
import java.util.Optional;

public class Person {
    private String birthday; //optional
    @Json(name = "known_for_department")private String knownForDepartment;
    private String deathday; //optional
    private Integer id;
    private String name;
    @Json(name = "also_known_as")private ArrayList<String> alsoKnownAs;
    private Integer gender;
    private String biography;
    private Double popularity;
    @Json(name = "place_of_birth")private String placeOfBirth; //optional
    @Json(name = "profile_path")private String profilePath; //optional
    private Boolean adult;
    @Json(name = "imdb_id")private String imdbId;
    private String homepage; //optional

    public Optional<String> getBirthday() {
        return Optional.ofNullable(birthday);
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public void setKnownForDepartment(String knownForDepartment) {
        this.knownForDepartment = knownForDepartment;
    }

    public Optional<String> getDeathday() {
        return Optional.ofNullable(deathday);
    }

    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(ArrayList<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Optional<String> getPlaceOfBirth() {
        return Optional.ofNullable(placeOfBirth);
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Optional<String> getProfilePath() {
        return Optional.ofNullable(profilePath);
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Optional<String> getHomepage() {
        return Optional.ofNullable(homepage);
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
}

package ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.models;

import com.squareup.moshi.Json;

import java.util.Optional;

public class Actor {
    @Json(name = "cast_id") private Integer castID;
    private String character;
    private String creditID;
    private Integer gender; //Optional
    private Integer id;
    private String name;
    private Integer order;
    private String profilePath; //Optional

    public Integer getCastID() {
        return castID;
    }

    public void setCastID(Integer castID) {
        this.castID = castID;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCreditID() {
        return creditID;
    }

    public void setCreditID(String creditID) {
        this.creditID = creditID;
    }

    public Optional<Integer> getGender() {
        return Optional.ofNullable(gender);
    }

    public void setGender(Integer gender) {
        this.gender = gender;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Optional<String> getProfilePath() {
        return Optional.ofNullable(profilePath);
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}

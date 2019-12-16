package ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.models;

import com.squareup.moshi.Json;

import java.util.Optional;

public class CrewPerson {
    @Json(name = "credit_ids")private String creditId;
    private String department;
    private Integer gender; //optional
    private Integer id;
    private String job;
    private String name;
    @Json(name = "profile_path")private String profilePath; //optional

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<String> getProfilePath() {
        return Optional.ofNullable(profilePath);
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
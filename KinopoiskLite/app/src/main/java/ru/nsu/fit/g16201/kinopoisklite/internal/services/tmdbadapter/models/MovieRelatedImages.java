package ru.nsu.fit.g16201.kinopoisklite.internal.services.tmdbadapter.models;

import com.squareup.moshi.Json;

import java.util.Optional;

public class MovieRelatedImages {
    @Json(name = "aspect_ratio")private Double aspectRatio;
    @Json(name = "file_path")private String filePath;
    private Integer height;
    private String iso_639_1; //optional
    @Json(name = "vote_average")private Double voteAverage;
    @Json(name = "vote_count")private Integer voteCount;
    private Integer width;

    public Double getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(Double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Optional<String> getIso_639_1() {
        return Optional.ofNullable(iso_639_1);
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
}

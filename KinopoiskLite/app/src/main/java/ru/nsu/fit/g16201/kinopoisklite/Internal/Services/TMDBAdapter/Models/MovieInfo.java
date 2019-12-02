package ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models;

import com.squareup.moshi.Json;

import java.util.List;
import java.util.Optional;

public class MovieInfo {
    private Boolean adult;
    @Json(name = "backdrop_path")private String backdropPath; //optional
    @Json(name = "belongs_to_collection")private MoviesCollection belongsToCollection; //optional
    private Integer budget;
    private List<Genre> genres;
    private String homepage; //optional
    private Integer id;
    @Json(name = "imbd_id")private String imdbId; //optional
    @Json(name = "original_language")private String originalLanguage;
    @Json(name = "original_title")private String originalTitle;
    private String overview; //optional
    private Double popularity;
    @Json(name = "poster_path")private String posterPath; //optional
    @Json(name = "production_companies")private List<ProductionCompany> productionCompanies;
    @Json(name = "production_countries")private List<ProductionCountry> productionCountries;
    @Json(name = "release_date")private String releaseDate;
    private Integer revenue;
    private Integer runtime; //optional
    @Json(name = "spoken_language")private List<SpokenLanguage> spokenLanguages;
    private String status;
    private String tagline; //optional
    private String title;
    private Boolean video;
    @Json(name = "vote_average")private Double voteAverage;
    @Json(name = "vote_count")private Integer voteCount;

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public Optional<String> getBackdropPath() {
        return Optional.ofNullable(backdropPath);
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Optional<MoviesCollection> getBelongsToCollection() {
        return Optional.ofNullable(belongsToCollection);
    }

    public void setBelongsToCollection(MoviesCollection belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Optional<String> getHomepage() {
        return Optional.ofNullable(homepage);
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Optional<String> getImdbId() {
        return Optional.ofNullable(imdbId);
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public Optional<String> getOverview() {
        return Optional.ofNullable(overview);
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Optional<String> getPosterPath() {
        return Optional.ofNullable(posterPath);
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Optional<Integer> getRuntime() {
        return Optional.ofNullable(runtime);
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Optional<String> getTagline() {
        return Optional.ofNullable(tagline);
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
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
}

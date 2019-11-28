package ru.nsu.fit.g16201.kinopoisklite.Internal.Services.TMDBAdapter.Models;

import com.squareup.moshi.Json;

import java.util.ArrayList;

public class PopularMovies {
    private Integer page;
    private ArrayList<Movie> results;
    @Json(name = "total_results")private Integer totalResults;
    @Json(name = "total_pages")private Integer totalPages;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}

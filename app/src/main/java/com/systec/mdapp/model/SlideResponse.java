package com.systec.mdapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SlideResponse {
    @SerializedName("page")
    private long page;

    @SerializedName("results")
    private List<Slide> results;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public List<Slide> getResults() {
        return results;
    }

    public void setResults(List<Slide> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}

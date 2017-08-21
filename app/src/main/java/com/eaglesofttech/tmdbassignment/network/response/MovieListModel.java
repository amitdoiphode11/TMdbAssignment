package com.eaglesofttech.tmdbassignment.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by android on 19/8/17.
 */

public class MovieListModel {

    @SerializedName("results")
    public List<Results> results;
    @SerializedName("page")
    public int page;
    @SerializedName("total_results")
    public int total_results;
    @SerializedName("dates")
    public Dates dates;
    @SerializedName("total_pages")
    public int total_pages;

    public static class Results {
        @SerializedName("vote_count")
        public int vote_count;
        @SerializedName("id")
        public int id;
        @SerializedName("video")
        public boolean video;
        @SerializedName("vote_average")
        public double vote_average;
        @SerializedName("title")
        public String title;
        @SerializedName("popularity")
        public double popularity;
        @SerializedName("poster_path")
        public String poster_path;
        @SerializedName("original_language")
        public String original_language;
        @SerializedName("original_title")
        public String original_title;
        /*@SerializedName("genre_ids")
        public List<Genre_ids> genre_ids;*/
        @SerializedName("backdrop_path")
        public String backdrop_path;
        @SerializedName("adult")
        public boolean adult;
        @SerializedName("overview")
        public String overview;
        @SerializedName("release_date")
        public String release_date;

        public Results(int vote_count, int id, boolean video, double vote_average,
                       String title, double popularity, String poster_path,
                       String original_language, String original_title,
                       String backdrop_path, boolean adult, String overview,
                       String release_date) {
            this.vote_count = vote_count;
            this.id = id;
            this.video = video;
            this.vote_average = vote_average;
            this.title = title;
            this.popularity = popularity;
            this.poster_path = poster_path;
            this.original_language = original_language;
            this.original_title = original_title;
            this.backdrop_path = backdrop_path;
            this.adult = adult;
            this.overview = overview;
            this.release_date = release_date;
        }
    }

    public static class Dates {
        @SerializedName("maximum")
        public String maximum;
        @SerializedName("minimum")
        public String minimum;
    }

    public MovieListModel(List<Results> results, int page, int total_results, Dates dates, int total_pages) {
        this.results = results;
        this.page = page;
        this.total_results = total_results;
        this.dates = dates;
        this.total_pages = total_pages;
    }
}

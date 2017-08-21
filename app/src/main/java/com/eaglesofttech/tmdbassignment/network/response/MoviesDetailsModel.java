package com.eaglesofttech.tmdbassignment.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by android on 19/8/17.
 */

public class MoviesDetailsModel {

    @SerializedName("adult")
    public boolean adult;
    @SerializedName("backdrop_path")
    public String backdrop_path;
    @SerializedName("belongs_to_collection")
    public Belongs_to_collection belongs_to_collection;
    @SerializedName("budget")
    public int budget;
    @SerializedName("genres")
    public List<Genres> genres;
    @SerializedName("homepage")
    public String homepage;
    @SerializedName("id")
    public int id;
    @SerializedName("imdb_id")
    public String imdb_id;
    @SerializedName("original_language")
    public String original_language;
    @SerializedName("original_title")
    public String original_title;
    @SerializedName("overview")
    public String overview;
    @SerializedName("popularity")
    public double popularity;
    @SerializedName("poster_path")
    public String poster_path;
    @SerializedName("production_companies")
    public List<Production_companies> production_companies;
    @SerializedName("production_countries")
    public List<Production_countries> production_countries;
    @SerializedName("release_date")
    public String release_date;
    @SerializedName("revenue")
    public int revenue;
    @SerializedName("runtime")
    public int runtime;
    @SerializedName("spoken_languages")
    public List<Spoken_languages> spoken_languages;
    @SerializedName("status")
    public String status;
    @SerializedName("tagline")
    public String tagline;
    @SerializedName("title")
    public String title;
    @SerializedName("video")
    public boolean video;
    @SerializedName("vote_average")
    public double vote_average;
    @SerializedName("vote_count")
    public int vote_count;

    public static class Belongs_to_collection {
        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;
        @SerializedName("poster_path")
        public String poster_path;
        @SerializedName("backdrop_path")
        public String backdrop_path;
    }

    public static class Genres {
        @SerializedName("id")
        public int id;
        @SerializedName("name")
        public String name;
    }

    public static class Production_companies {
        @SerializedName("name")
        public String name;
        @SerializedName("id")
        public int id;
    }

    public static class Production_countries {
        @SerializedName("iso_3166_1")
        public String iso_3166_1;
        @SerializedName("name")
        public String name;
    }

    public static class Spoken_languages {
        @SerializedName("iso_639_1")
        public String iso_639_1;
        @SerializedName("name")
        public String name;
    }
}

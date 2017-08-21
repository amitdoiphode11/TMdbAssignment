package com.eaglesofttech.tmdbassignment.network;

import com.eaglesofttech.tmdbassignment.network.response.MovieListModel;
import com.eaglesofttech.tmdbassignment.network.response.MoviesDetailsModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by android on 19/8/17.
 */

public interface ApiInterface {
    @FormUrlEncoded
    @POST("movie/upcoming")
    Call<MovieListModel> getMovieList(@Field("api_key") String api_key);

    @GET("movie/{id}")
    Call<MoviesDetailsModel> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

}

package com.eaglesofttech.tmdbassignment.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.eaglesofttech.tmdbassignment.R;
import com.eaglesofttech.tmdbassignment.adapter.SliderPagerAdapter;
import com.eaglesofttech.tmdbassignment.network.ApiClient;
import com.eaglesofttech.tmdbassignment.network.ApiInterface;
import com.eaglesofttech.tmdbassignment.network.response.MoviesDetailsModel;
import com.eaglesofttech.tmdbassignment.utility.ConnectionDetector;
import com.eaglesofttech.tmdbassignment.utility.CustomDialog;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by android on 19/8/17.
 */

public class MovieDetails extends AppCompatActivity {
    private TextView[] dots;
    int page_position = 0;
    SliderPagerAdapter sliderPagerAdapter;
    ConnectionDetector connectionDetector;
    CustomDialog customDialog;

    int movieId = 0;
    ArrayList<String> poster_paths;

    ViewPager vp_slider;
    CoordinatorLayout coordinatorLayout;
    Toolbar toolBar;
    TextView tv_title, tv_overView;
    LinearLayout ll_dots;
    RatingBar ratingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        findViewById();


    }

    private void findViewById() {
        connectionDetector = new ConnectionDetector(this);
        customDialog = new CustomDialog(this);

         /* XML Controls */
        toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        vp_slider = (ViewPager) findViewById(R.id.vp_slider);
        ll_dots = (LinearLayout) findViewById(R.id.ll_dots);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_overView = (TextView) findViewById(R.id.tv_overView);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        if (getIntent() != null)
            movieId = getIntent().getIntExtra("id", 0);

        getMovieDetails();
        poster_paths = new ArrayList<>();
        // method for adding indicators
        addBottomDots(0);
    }

    private void getMovieDetails() {
        if (connectionDetector.isConnectingToInternet()) {
            customDialog.showProgressDialog(getString(R.string.pleaseWait));
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<MoviesDetailsModel> moviesDetailsModelCall = apiInterface.getMovieDetails(movieId, MovieList.API_KEY);
            moviesDetailsModelCall.enqueue(new Callback<MoviesDetailsModel>() {
                @Override
                public void onResponse(Call<MoviesDetailsModel> call, Response<MoviesDetailsModel> response) {
                    customDialog.hideProgressDialog();
                    if (response.isSuccessful()) {
                        tv_title.setText(response.body().original_title);
                        tv_overView.setText(response.body().overview);
                        Log.e(getClass().getSimpleName(), "" + response.body().popularity);
                        ratingBar.setRating(((Double) response.body().popularity).intValue());
                        poster_paths.add(response.body().poster_path);
                        poster_paths.add(response.body().backdrop_path);
                        //poster_paths.add(response.body().poster_path);

                        sliderPagerAdapter = new SliderPagerAdapter(MovieDetails.this, poster_paths);
                        vp_slider.setAdapter(sliderPagerAdapter);
                        vp_slider.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {
                                addBottomDots(position);
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });


                    }
                }

                @Override
                public void onFailure(Call<MoviesDetailsModel> call, Throwable t) {
                    customDialog.hideProgressDialog();
                    Log.e(getClass().getSimpleName(), t.toString());
                }
            });
        } else customDialog.mSnackBar(coordinatorLayout, getString(R.string.noInternet), -1);

    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[poster_paths.size()];


        ll_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            ll_dots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(getResources().getColor(R.color.colorAccent));
    }

}

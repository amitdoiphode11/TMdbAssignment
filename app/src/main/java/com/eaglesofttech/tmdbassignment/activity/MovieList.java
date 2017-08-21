package com.eaglesofttech.tmdbassignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.eaglesofttech.tmdbassignment.R;
import com.eaglesofttech.tmdbassignment.adapter.RvAdapterMovieList;
import com.eaglesofttech.tmdbassignment.network.ApiClient;
import com.eaglesofttech.tmdbassignment.network.ApiInterface;
import com.eaglesofttech.tmdbassignment.network.response.MovieListModel;
import com.eaglesofttech.tmdbassignment.utility.ConnectionDetector;
import com.eaglesofttech.tmdbassignment.utility.CustomDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieList extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    ConnectionDetector connectionDetector;
    CustomDialog customDialog;
    Intent intent;
    boolean isInternetPresent = false;
    public static String API_KEY = "b7cd3340a794e5a2f35e3abb820b497f";
    Toolbar toolBar;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        findViewById();
    }

    private void findViewById() {
        connectionDetector = new ConnectionDetector(this);
        customDialog = new CustomDialog(this);

        /* XML Controls */
        toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        getMovieList();
    }

    private void getMovieList() {
        isInternetPresent = connectionDetector.isConnectingToInternet();
        if (isInternetPresent) {
            //customDialog.showProgressDialog(getString(R.string.pleaseWait));
            swipeRefreshLayout.setRefreshing(true);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<MovieListModel> movieListModelCall = apiInterface.getMovieList(API_KEY);
            movieListModelCall.enqueue(new Callback<MovieListModel>() {
                @Override
                public void onResponse(Call<MovieListModel> call, Response<MovieListModel> response) {
                    if (response.isSuccessful()) {
                        //customDialog.hideProgressDialog();
                        swipeRefreshLayout.setRefreshing(false);
                        MovieListModel movieListModel = response.body();
                        if (movieListModel.results.size() != 0) {
                            recyclerView.setItemViewCacheSize(0);

                            RvAdapterMovieList rvAdapterMovieList = new RvAdapterMovieList(MovieList.this, movieListModel.results);
                            recyclerView.setAdapter(rvAdapterMovieList);

                        }
                    }
                }

                @Override
                public void onFailure(Call<MovieListModel> call, Throwable t) {
                    //customDialog.hideProgressDialog();
                    swipeRefreshLayout.setRefreshing(false);
                    Log.e(getClass().getSimpleName(), t.toString());
                }
            });
        } else customDialog.mSnackBar(coordinatorLayout, getString(R.string.noInternet), -1);
    }


    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        getMovieList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_manu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_developer:
                intent = new Intent(MovieList.this, DevelopedBy.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

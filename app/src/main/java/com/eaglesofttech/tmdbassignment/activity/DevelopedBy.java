package com.eaglesofttech.tmdbassignment.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.eaglesofttech.tmdbassignment.R;

/**
 * Created by android on 21/8/17.
 */

public class DevelopedBy extends AppCompatActivity {
    Toolbar toolBar;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developed_by);
        findViewById();
    }

    private void findViewById() {
          /* XML Controls */
        toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
    }
}

package com.eaglesofttech.tmdbassignment.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.eaglesofttech.tmdbassignment.R;

import java.util.ArrayList;

public class SliderPagerAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;
    Activity activity;
    ArrayList<String> poster_path;
    public static String Poster_w185 = "http://image.tmdb.org/t/p/w185/";

    public SliderPagerAdapter(Activity activity, ArrayList<String> poster_path) {
        this.activity = activity;
        this.poster_path = poster_path;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.custom_image_slider, container, false);
        ImageView iv_slider = (ImageView) view.findViewById(R.id.iv_slider);

        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_image_24dp);
        Glide.with(activity)
                .load(Poster_w185 + poster_path.get(position))
                .apply(requestOptions)
                .into(iv_slider);
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return poster_path.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
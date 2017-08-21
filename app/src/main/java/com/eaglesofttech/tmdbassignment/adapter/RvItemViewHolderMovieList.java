package com.eaglesofttech.tmdbassignment.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.eaglesofttech.tmdbassignment.R;
import com.eaglesofttech.tmdbassignment.network.response.MovieListModel;

/**
 * Created by android on 19/8/17.
 */

public class RvItemViewHolderMovieList extends RecyclerView.ViewHolder {
    Context context;
    ImageView iv_posterImage;
    TextView tv_movieName, tv_releaseDate, tv_adult;
    AppCompatButton acbMove;
    public static String Poster_w92 = "http://image.tmdb.org/t/p/w92/";

    public RvItemViewHolderMovieList(View itemView, Context context) {
        super(itemView);
        this.context = context;
        iv_posterImage = (ImageView) itemView.findViewById(R.id.iv_posterImage);
        tv_movieName = (TextView) itemView.findViewById(R.id.tv_movieName);
        tv_releaseDate = (TextView) itemView.findViewById(R.id.tv_releaseDate);
        tv_adult = (TextView) itemView.findViewById(R.id.tv_adult);
        acbMove = (AppCompatButton) itemView.findViewById(R.id.acbMove);
    }


    public void bind(MovieListModel.Results results) {
        tv_movieName.setText(results.original_title);
        tv_releaseDate.setText(results.release_date);

        if (results.poster_path != null) {
            RequestOptions requestOptions = new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.ic_image_24dp);
            Glide.with(context)
                    .load(Poster_w92 + results.poster_path)
                    .apply(requestOptions)
                    .into(iv_posterImage);
        } else
            // make sure Glide doesn't load anything into this view until told otherwise
            iv_posterImage.setImageDrawable(null);


        if (results.adult)
            tv_adult.setText(context.getString(R.string.adult));
        else tv_adult.setVisibility(View.INVISIBLE);
    }
}

package com.example.maddy.myapplication;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by maddy on 18/8/17.
 */

public class MovieAdapter extends ArrayAdapter {

    List<MovieGetSet> movieGetSetList;
    LayoutInflater inflater;
    int resource;


    public MovieAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<MovieGetSet> movieGetSetslist) {
        super(context, resource, movieGetSetslist);

        this.movieGetSetList = movieGetSetslist;
        this.resource = resource;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) convertView = inflater.inflate(resource,null);

        TextView tv_movie = (TextView) convertView.findViewById(R.id.tv_Movie);
        TextView tv_year = (TextView) convertView.findViewById(R.id.tv_Year);
        Log.e("adapter -----------", movieGetSetList.get(position).getMovie());
        tv_movie.setText(movieGetSetList.get(position).getMovie());
        tv_year.setText(movieGetSetList.get(position).getYear());

        return convertView;
    }
}

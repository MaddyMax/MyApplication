package com.example.maddy.myapplication;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by maddy on 30/8/17.
 */

public class ArticleAdapter extends ArrayAdapter {
    int resource;
    List<ArtilceSetGet> list;
    LayoutInflater inflater;

    public ArticleAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ArtilceSetGet> objects) {
        super(context, resource, objects);

        this.resource = resource;
        this.list = objects;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) convertView = inflater.inflate(resource,null);

        TextView tv_author = (TextView) convertView.findViewById(R.id.tv_author);
        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView tv_description = (TextView) convertView.findViewById(R.id.tv_description);

        tv_author.setText(list.get(position).getAuthor());
        tv_description.setText(list.get(position).getDescription());
        tv_title.setText(list.get(position).getTitle());



        return convertView;


    }
}

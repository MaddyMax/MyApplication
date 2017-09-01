package com.example.maddy.myapplication;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

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

        Holder holder = new Holder();

         holder.tv_author= (TextView) convertView.findViewById(R.id.tv_author);
         holder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
         holder.tv_description= (TextView) convertView.findViewById(R.id.tv_description);
         //holder.btn_link= convertView.findViewById(R.id.btn_link);
        holder.iv_image= convertView.findViewById(R.id.iv_image);
        holder.tv_published = convertView.findViewById(R.id.tv_published);


        // Then later, when you want to display image
        ImageLoader.getInstance().displayImage(list.get(position).getImageUrl(), holder.iv_image); // Default options will be used
        holder.tv_author.setText(list.get(position).getAuthor());
        holder.tv_description.setText(list.get(position).getDescription());
        holder.tv_title.setText(list.get(position).getTitle());
        holder.tv_published.setText(list.get(position).getPublished());




        return convertView;


    }

    static class Holder{
        public TextView tv_author;
        public TextView tv_title;
        public TextView tv_description;
        public Button btn_link;
        public ImageView iv_image;
        public TextView tv_published;

    }

}

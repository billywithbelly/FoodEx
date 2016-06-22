package com.example.billywithbelly.foodex;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by helen on 2016/6/20.
 */
public class locatelist extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] web;

    public locatelist(Activity context,
                    String[] web) {
        super(context, R.layout.list_locate, web);
        this.context = context;
        this.web = web;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_locate, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.locatetxt);


        txtTitle.setText(web[position]);

        return rowView;
    }
}
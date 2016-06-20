package com.example.billywithbelly.foodex;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by billywithbelly on 6/20/16.
 */
public class HtmlList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] web;

    public HtmlList(Activity context,String[] web) {
        super(context, R.layout.activity_html, web);
        this.context = context;
        this.web = web;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_activity, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.html_txt);

        txtTitle.setText(web[position]);
        return rowView;
    }
}

package com.example.billywithbelly.foodex;

/**
 * Created by helen on 2016/6/20.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class BBQList extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] web;

    public BBQList(Activity context,
                      String[] web) {
        super(context, R.layout.list_bbq, web);
        this.context = context;
        this.web = web;


    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_bbq, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.bbqtxt);


        txtTitle.setText(web[position]);


        return rowView;
    }
}
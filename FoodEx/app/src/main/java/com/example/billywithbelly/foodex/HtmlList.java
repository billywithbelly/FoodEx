package com.example.billywithbelly.foodex;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by billywithbelly on 6/20/16.
 */
public class HtmlList extends ArrayAdapter<String> {

    private Activity context;
    private String[] web;
    private Bitmap[] graph;

    public HtmlList(Activity context, String[] web, Bitmap[] graph) {
        super(context, R.layout.activity_html, web);
        this.context = context;
        this.web = web;
        this.graph = graph;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_activity, null, true);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.html_img);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.html_txt);
        
        txtTitle.setText(web[position]);
        //圖片設成圓形的～
        Bitmap circleBitmap = Bitmap.createBitmap(graph[position].getWidth(), graph[position].getHeight(), Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader(graph[position],  Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(shader);
        Canvas c = new Canvas(circleBitmap);
        c.drawCircle(graph[position].getWidth()/2, graph[position].getHeight()/2, graph[position].getWidth()/2, paint);
        //
        imageView.setImageBitmap(circleBitmap);
        return rowView;
    }
}

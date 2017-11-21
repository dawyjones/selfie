package no.nials.selfieapp.selfieapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MySelfiesFullView extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);
        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        // get intent data
        Intent i = getIntent();
        // Selected image id
        String position = i.getExtras().getString("url");
        //  ImageView imageView = (ImageView) findViewById(R.id.full_image_view);

          Picasso.with(MySelfiesFullView.this)
         .load(position)
         .into(imageView);

    }
}
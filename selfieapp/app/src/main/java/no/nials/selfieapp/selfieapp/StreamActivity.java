package no.nials.selfieapp.selfieapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

public class StreamActivity extends AppCompatActivity {

    public static ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);
        imageView = (ImageView)findViewById(R.id.imageView);


        //data = (TextView) findViewById(R.id.textView);
        //imageView2 = findViewById(R.id.imageView);

        GetSelfies process = new GetSelfies();
        process.execute();



//        String picture = data.getText().toString();
//        Log.i("SONDRE", picture);

//        byte[] imageBytes = Base64.decode(picture, Base64.DEFAULT);
//        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);



/*
        imageView3 = findViewById(R.id.imageView);
        imageView4 = findViewById(R.id.imageView);
        imageView5 = findViewById(R.id.imageView);
        imageView6 = findViewById(R.id.imageView);
        imageView7 = findViewById(R.id.imageView);
        GetSelfies process = new GetSelfies();
        process.execute();

        String picture = data.getText().toString();

        byte[] imageBytes = Base64.decode(picture, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        imageView2.setImageBitmap(decodedImage);
        */



    }


}
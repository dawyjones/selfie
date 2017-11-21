package no.nials.selfieapp.selfieapp;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class CameraSelfie extends AppCompatActivity {

    ImageView imageView;
    String picturePath;
    String ba1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Button uploadBtn = (Button)findViewById(R.id.uploadBtn);
        Button cameraBtn = (Button)findViewById(R.id.cameraBtn);
        imageView = (ImageView)findViewById(R.id.imageView);

        //lager knapper for å ta bilde og for å laste opp
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });
        //knapp for å laste opp
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload();
            }
        });


    }



    private void upload(){

        AsyncT asyncT = new AsyncT(picturePath);
        asyncT.execute();
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");

        //encode image to base64 string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

       /* Log.d("SONDRE",imageString);*/

        imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        picturePath = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        imageView.setImageBitmap(decodedImage);
        picturePath.trim();

        /*Log.d("SONDRE",picturePath);*/

    }
}

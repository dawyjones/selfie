package no.nials.selfieapp.selfieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button btnTakePicture;
    Button btnLeaderboards;
    Button btnMyPictures;
    Button btnProfile;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnTakePicture = (Button) findViewById(R.id.buttonPicture);
        btnLeaderboards = (Button) findViewById(R.id.buttonLeaderboards);
        btnMyPictures = (Button) findViewById(R.id.buttonMyPictures);
        btnProfile = (Button) findViewById(R.id.buttonProfile);
        btnLogout = (Button) findViewById(R.id.buttonLogout);
        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pictureActivity(view);
            }
        });
        btnLeaderboards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leaderBoardsActivity(view);
            }
        });
        btnMyPictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPicturesActivity(view);
            }
        });
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileActivity(view);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOutActivity(view);
            }
        });
    }

    public void pictureActivity(View view){
        Intent intent = new Intent(this, CameraSelfie.class);
        startActivity(intent);
    }
    public void leaderBoardsActivity(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void myPicturesActivity(View view){
        Intent intent = new Intent(this, MySelfiesActivity.class);
        startActivity(intent);
    }
    public void profileActivity(View view){
        Intent intent = new Intent(this, StreamActivity.class);
        startActivity(intent);
    }
    public void logOutActivity(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}

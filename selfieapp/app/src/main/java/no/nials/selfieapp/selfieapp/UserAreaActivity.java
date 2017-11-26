package no.nials.selfieapp.selfieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");


        EditText editTextName = (EditText) findViewById(R.id.editTextName);
        EditText editTextEmail = (EditText) findViewById(R.id.editTextEmail);


        editTextEmail.setText(email);
       editTextName.setText(name);
    }
}
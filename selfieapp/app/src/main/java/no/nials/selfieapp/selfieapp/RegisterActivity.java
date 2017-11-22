package no.nials.selfieapp.selfieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;

import static com.android.volley.Request.Method.POST;

/**
 * Created by Kristoffer on 11/21/2017.
 */

public class RegisterActivity extends AppCompatActivity {
    EditText editTextPhone, editTextEmail, editTextPassword, editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    editTextPhone = (EditText) findViewById(R.id.editTextPhone);
    editTextEmail = (EditText) findViewById(R.id.editTextEmail);
    editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    editTextName = (EditText) findViewById(R.id.editTextName);

        findViewById(R.id.buttonRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone1 = "123";
                final int phone = Integer.parseInt(phone1.toString());
                final String email = "frank@frank.frank";
                final String password = "12345";
                final String name = "frank";
                final String gender = "M";
                final String birthday = "1990-09-01";


             Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonResponse = new JSONArray(response);


                            for(int n = 0; n < jsonResponse.length(); n++)
                            {
                                JSONObject jsonObject = jsonResponse.getJSONObject(n);


                            }


                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                };

                RegisterRequest registerRequest = new RegisterRequest(email, password, phone, name, birthday, gender, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
          }

        });


    findViewById(R.id.textViewLogin).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final int phone = Integer.parseInt(editTextPhone.getText().toString());
            final String email = editTextEmail.getText().toString().trim();
            final String password = editTextPassword.getText().toString().trim();
            final String name = editTextName.getText().toString().trim();
            final String gender = "M";
            final String birthday = "1990-09-01";
            //if user pressed on button register
            //here we will register the user to server

            Response.Listener<String> responseListener = new Response.Listener<String>(){
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonResponse = new JSONArray(response);
                        JSONObject jsonObject = jsonResponse.getJSONObject(0);
                        boolean success = jsonObject.getBoolean("success");
                        if (success) {
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            RegisterActivity.this.startActivity(intent);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setMessage("Register failed")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            RegisterRequest registerRequest = new RegisterRequest(email, password, phone, name, birthday, gender, responseListener);
            RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
            queue.add(registerRequest);
            }


        });
    }
    }



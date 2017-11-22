package no.nials.selfieapp.selfieapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class testreg3 extends AppCompatActivity {
String Url = "http://nials.no:8080/api/api/user/newUser";
    EditText editTextPhone, editTextEmail, editTextPassword, editTextBirthday, editTextName;
    RadioGroup radioGroupGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if the user is already logged in we will directly start the profile activity
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
            return;
        }
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextBirthday = (EditText) findViewById(R.id.editTextBirthday);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        radioGroupGender = (RadioGroup) findViewById(R.id.radioGender);


        findViewById(R.id.buttonRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on button register
                //here we will register the user to server
                registerUser();
            }
        });

    }

    private void registerUser() {
        final String name = editTextName.getText().toString().trim();
        final String phone1 = editTextPhone.getText().toString().trim();
        final int phone = Integer.parseInt(phone1);
        final String email = editTextEmail.getText().toString().trim();
        final String birthday = editTextBirthday.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String gender = ((RadioButton) findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();

        //first we will do the validations


        if (TextUtils.isEmpty(phone1)) {
            editTextEmail.setError("Please enter your phonenumber");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(birthday)) {
            editTextEmail.setError("Please enter your birthday");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please enter your email");
            editTextEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Enter a password");
            editTextPassword.requestFocus();
            return;
        }

        //if it passes all the validations

        class RegisterUser extends AsyncTask<Void, Void, String> {

            private ProgressBar progressBar;
            protected String doInBackground(Void... voids) {
                final String name = editTextName.getText().toString().trim();
                final String phone1 = editTextPhone.getText().toString().trim();
                final int phone = Integer.parseInt(phone1);
                final String email = editTextEmail.getText().toString().trim();
                final String birthday = editTextBirthday.getText().toString().trim();
                final String password = editTextPassword.getText().toString().trim();



                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("name", name));
                params.add(new BasicNameValuePair("birthday", birthday));
                params.add(new BasicNameValuePair("email", email));
                params.add(new BasicNameValuePair("password", password));
                params.add(new BasicNameValuePair("phone", phone1));
                params.add(new BasicNameValuePair("gender", "male"));

                // getting JSON String
                // Note that create product url accepts POST method
                String json = makeHttpRequest(Url, params);

                return json;
            }


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
                progressBar.setVisibility(View.GONE);
                //String url = "http://nials.no:8080/api/api/user/newUser";
                try {
                    JSONObject jsonData = new JSONObject(s);
                    Boolean success = jsonData.getBoolean("success");
                    String message = jsonData.getString("message");

                    if (success) {
                        //success
                    } else {
                        // failed to Register Fine
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                    //
                    // JSONObject obj = new JSONObject(s);




            }
        }

        //executing the async task
        RegisterUser ru = new RegisterUser();
        ru.execute();
    }



    public String makeHttpRequest(String url, List<NameValuePair> params)

    {
        InputStream is = null;
        String json = "";

        // Making HTTP request
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();

            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }


        // return JSON String
        return json;

    }
}

    // constructor






    /*

    EditText editTextPhone, editTextEmail, editTextPassword, editTextBirthday, editTextName;
    RadioGroup radioGroupGender;







    protected void onPostExecute(String json) {
        // dismiss the dialog once done
        pDialog.dismiss();
        Log.d("mylog", "json = " + json);
        //parse here
    }

}*/

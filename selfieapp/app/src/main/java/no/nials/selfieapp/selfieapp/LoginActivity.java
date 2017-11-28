package no.nials.selfieapp.selfieapp;

/**
 * Created by Kami on 18.11.2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLOutput;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    EditText editTextEmail, editTextPassword;
    String email;
    String pw;
    String dataParsed2 = "";
    String singleParsed2 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Login2 login = new Login2();
        login.execute();
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);


        //if user presses on login
        //calling the method login
        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = editTextEmail.getText().toString().trim();
                pw = editTextPassword.getText().toString().trim();
                if((email.equals(dataParsed))&&pw.equals(dataParsed2)){
                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));}
                else{
                    Toast.makeText(getApplicationContext(), "Wrong email or password.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //if user presses on not registered
        findViewById(R.id.textViewRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open register screen
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }


    protected class Login2 extends AsyncTask<Void, Void, Void> {
        String email, pw;
        // public Login2(String email, String pw){
        //    this.email = email;
        //   this.pw = pw;
        //}

        @Override
        protected Void doInBackground(Void... voids) {

            String str = "http://nials.no:8080/selfie/api/user";
            URLConnection urlConn = null;
            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(str);
                urlConn = url.openConnection();
                InputStream inputStream = urlConn.getInputStream();

                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    data = data + line;
                }

                JSONArray JA = new JSONArray(data);
                for (int i = JA.length() - 1; i < JA.length(); i++) {
                    JSONObject JO = (JSONObject) JA.get(i);
                    singleParsed =
                            JO.get("email") + "";

                    dataParsed = dataParsed + singleParsed;

                    singleParsed2 =
                            JO.get("password") + "";

                    dataParsed2 = dataParsed2 + singleParsed2;



                }


                // bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

           /* StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line);
            }

            return new JSONObject(stringBuffer.toString());
        }
        catch(Exception ex)
        {
            Log.e("App", "Login2", ex);
            return null;
        }
        finally
        {
            if(bufferedReader != null)
            {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
            } catch (Exception e) {
            }
            return null;
        }
        @Override
        protected void onPostExecute (Void aVoid)
        {
            super.onPostExecute(aVoid);



        }

    }
}





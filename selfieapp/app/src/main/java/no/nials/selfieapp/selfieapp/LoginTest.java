package no.nials.selfieapp.selfieapp;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class LoginTest extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText nameEditText;
    private EditText phoneEditText;
    //private Button sendPostReqButton;
   // private Button clearButton;
    private Button buttonRegister;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = (EditText) findViewById(R.id.editTextEmail);
        passwordEditText = (EditText) findViewById(R.id.editTextPassword);
        nameEditText = (EditText) findViewById(R.id.editTextName);
        phoneEditText = (EditText) findViewById(R.id.editTextPhone);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String givenUsername = usernameEditText.getEditableText().toString();
                String givenPassword = passwordEditText.getEditableText().toString();
                String givenPhone = phoneEditText.getEditableText().toString();
                String givenGender = "M";
                String givenBirthday = "1990";
                String givenName = nameEditText.getEditableText().toString();

               // System.out.println("Given username :" + givenUsername + " Given password :" + givenPassword);

                sendPostRequest(givenUsername, givenPassword, givenPhone, givenGender, givenBirthday, givenName);
            }
        });

        findViewById(R.id.login_clear_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    usernameEditText.setText("");
                    passwordEditText.setText("");
                    passwordEditText.setCursorVisible(false);
                    passwordEditText.setFocusable(false);
                    usernameEditText.setCursorVisible(true);
                    passwordEditText.setFocusable(true);


                }
            }
        );
    }

    private void sendPostRequest(String givenUsername, String givenPassword, String givenPhone, String givenGender, String givenBirthday, String givenName) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String paramUsername = params[0];
                String paramPassword = params[1];
                String paramPhone = params[2];
                String paramGender = params[3];
                String paramBirthday = params[4];
                String paramName = params[5];



                HttpClient httpClient = new DefaultHttpClient();

                // In a POST request, we don't pass the values in the URL.
                //Therefore we use only the web page URL as the parameter of the HttpPost argument
                HttpPost httpPost = new HttpPost("http://nials.no:8080/selfie/api/user/newUser");


                // Because we are not passing values over the URL, we should have a mechanism to pass the values that can be
                //uniquely separate by the other end.
                //To achieve that we use BasicNameValuePair
                //Things we need to pass with the POST request
                BasicNameValuePair emailBasicNameValuePair = new BasicNameValuePair("email", paramUsername);
                BasicNameValuePair passwordBasicNameValuePair = new BasicNameValuePair("password", paramPassword);
                BasicNameValuePair phoneBasicNameValuePair = new BasicNameValuePair("phone", paramPhone);
                BasicNameValuePair genderBasicNameValuePair = new BasicNameValuePair("gender", paramGender);
                BasicNameValuePair birthdayBasicNameValuePair = new BasicNameValuePair("birthday", paramBirthday);
                BasicNameValuePair nameBasicNameValuePair = new BasicNameValuePair("name", paramName);

                // We add the content that we want to pass with the POST request to as name-value pairs
                //Now we put those sending details to an ArrayList with type safe of NameValuePair
                List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
                nameValuePairList.add(emailBasicNameValuePair);
                nameValuePairList.add(passwordBasicNameValuePair);
                nameValuePairList.add(phoneBasicNameValuePair);
                nameValuePairList.add(genderBasicNameValuePair);
               nameValuePairList.add(birthdayBasicNameValuePair);
                nameValuePairList.add(nameBasicNameValuePair);
                               try {
                    // UrlEncodedFormEntity is an entity composed of a list of url-encoded pairs.
                    //This is typically useful while sending an HTTP POST request.
                    UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairList);

                    // setEntity() hands the entity (here it is urlEncodedFormEntity) to the request.
                    httpPost.setEntity(urlEncodedFormEntity);

                    try {
                        // HttpResponse is an interface just like HttpPost.
                        //Therefore we can't initialize them
                        HttpResponse httpResponse = httpClient.execute(httpPost);

                        // According to the JAVA API, InputStream constructor do nothing.
                        //So we can't initialize InputStream although it is not an interface
                        InputStream inputStream = httpResponse.getEntity().getContent();

                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                        StringBuilder stringBuilder = new StringBuilder();

                        String bufferedStrChunk = null;

                        while((bufferedStrChunk = bufferedReader.readLine()) != null){
                            stringBuilder.append(bufferedStrChunk);
                        }

                        return stringBuilder.toString();

                    } catch (ClientProtocolException cpe) {
                        System.out.println("First Exception caz of HttpResponese :" + cpe);
                        cpe.printStackTrace();
                    } catch (IOException ioe) {
                        System.out.println("Second Exception caz of HttpResponse :" + ioe);
                        ioe.printStackTrace();
                    }

                } catch (UnsupportedEncodingException uee) {
                    System.out.println("An Exception given because of UrlEncodedFormEntity argument :" + uee);
                    uee.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                if(result.equals("working")){
                    Toast.makeText(getApplicationContext(), "HTTP POST is working...", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid POST req...", Toast.LENGTH_LONG).show();
                }
            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        System.out.println("*** doInBackground ** paramUsername   : " + givenUsername + " paramPassword   :" + givenPassword + "paramPhone   :" + givenPhone + "paramGender   :" + givenGender + "paramBirthday   :" + givenBirthday + "paramname   :" + givenName);

        sendPostReqAsyncTask.execute(givenUsername, givenPassword, givenPhone, givenGender, givenBirthday, givenName);

    }



}
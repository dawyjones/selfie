package no.nials.selfieapp.selfieapp;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Kami on 15.11.2017.
 */

//AsyncTask good for long running tasks
public class Networking extends AsyncTask {

    public static final int NETWORK_STATE_REGISTER = 1;

    @Override
    protected Object doInBackground(Object[] params) {

        getJson((String) params[0], (Integer) params[1]);
        return null;
    }


    private void getJson(String url, int state) {
        //Do a HTTP POST, more secure than GET
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost request = new HttpPost(url);
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

        boolean valid = false;

        switch (state) {
            case Networking.NETWORK_STATE_REGISTER:
                //Building key value pairs to be accessed on web
                postParameters.add(new BasicNameValuePair("name", "anders"));
                postParameters.add(new BasicNameValuePair("birthday", "1980"));
                postParameters.add(new BasicNameValuePair("email", "kriss@gmail.com"));
                postParameters.add(new BasicNameValuePair("password", "123456"));
                postParameters.add(new BasicNameValuePair("phone", "12345"));
                postParameters.add(new BasicNameValuePair("gender", "male"));
                valid = true;


                break;
            default:
                // Toast.makeText(c, "Unknown state", Toast.LENGTH_SHORT).show();

        }

        if (valid == true) {
            //Reads everything that comes from server
            BufferedReader bufferedReader = null;
            StringBuffer stringBuffer = new StringBuffer("");
            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postParameters);
                request.setEntity(entity);

                //Send off to server
                HttpResponse response = httpClient.execute(request);

                //Reads response and gets content
                bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String line = "";
                String LineSeparator = System.getProperty("line.separator");
                //Read back server output
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line + LineSeparator);
                }

                bufferedReader.close();
            } catch (Exception e) {
                //Toast.makeText(c, "Error during networking", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            decodeResultIntoJson(stringBuffer.toString());

            //Toast.makeText(c, "Valid details", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(c, "Invalid details", Toast.LENGTH_SHORT).show();

        }
    }

    private void decodeResultIntoJson(String response) {
    /* Example from server
    {
       "success":1,
       "message":"You have been successfully registered"
    }
     */
        if (response.contains("error")) {
            try {
                JSONObject jo = new JSONObject(response);
                String error = jo.getString("error");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            JSONObject jo = new JSONObject(response);

            String success = jo.getString("success");
            String message = jo.getString("message");
            // Toast.makeText(c, "Register successful", Toast.LENGTH_SHORT).show();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
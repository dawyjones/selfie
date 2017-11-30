package no.nials.selfieapp.selfieapp;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

class AsyncT extends AsyncTask<Void, Void, String> {

    String picture = null;
    private OnPostExecute callback = null;

    public AsyncT(String path){

        this.picture = path;
    }

    public AsyncT(String picture, OnPostExecute callback) {
        this.picture = picture;
        this.callback = callback;
    }

    public interface OnPostExecute {
        void onPostExecute();
    }


    @Override
    protected String doInBackground(Void... params) {
        try {

            // String address = "http://158.38.101.106:8080/selfie/api/picture";

            //String address = "http://158.38.22.164:8080/messenger/webapi/messages";
            String address = "http://158.38.101.106:8080/selfie/api/picture/newpicture";

            JSONObject json = new JSONObject();
            json.put("pictureJson", picture);
            json.put("author", "1");
            String requestBody = json.toString();
            System.out.println("Debug: " + requestBody);
            URL url = new URL(address);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            OutputStream outputStream = new BufferedOutputStream(urlConnection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
            writer.write(requestBody);
            writer.flush();
            writer.close();
            outputStream.close();

            InputStream inputStream;
            // get stream
            if (urlConnection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
                inputStream = urlConnection.getInputStream();
            } else {
                inputStream = urlConnection.getErrorStream();
            }
            // parse stream
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String temp, response = "";
            while ((temp = bufferedReader.readLine()) != null) {
                response += temp;
            }

        } catch (IOException | JSONException e) {
            return e.toString();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (callback != null) {
            callback.onPostExecute();
        }
    }
}
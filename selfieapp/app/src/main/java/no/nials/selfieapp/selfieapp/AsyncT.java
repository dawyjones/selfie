package no.nials.selfieapp.selfieapp;

import android.os.AsyncTask;

import org.json.JSONArray;
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

class AsyncT extends AsyncTask<String, String, String> {

    String phone, email, password, name, birthday, gender;

    public AsyncT(String email, String password, String phone, String name, String birthday, String gender){

        this.password = password;
        this.email = email;
        this.name= name;
        this.birthday = birthday;
        this.phone = phone;
        this.gender = gender;
    }



    @Override
    protected String doInBackground(String... strings) {
        try {
            String address = "http://nials.no:8080/selfie/api/user/newUser?name=" + name + "&birthday=" + birthday + "&email=" +email +"&password=" + password +"&phone=" +phone + "&gender=" + gender;
            System.out.println(address);
            JSONObject json = new JSONObject();
            json.put("name", "kys");
            json.put("birthday", "1990-01-01");
            json.put("email", "ken@ken.com");
            json.put("password", "123");
            json.put("phone", "12345678");
            json.put("gender", "m");


          //  JSONArray array = new JSONArray();


           // array.put(json);
            String requestBody = json.toString();
            System.out.println("test:  " + requestBody);
            URL url = new URL(address);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            OutputStream outputStream = new BufferedOutputStream(urlConnection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
            //outputStream.write(requestBody)
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

}
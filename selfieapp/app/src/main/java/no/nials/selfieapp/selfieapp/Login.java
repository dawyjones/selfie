package no.nials.selfieapp.selfieapp;

/**
 * Created by Kristoffer on 11/22/2017.
 */




        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import android.util.JsonReader;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;
        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Date;

public class Login extends AppCompatActivity {
    EditText  editTextEmail, editTextPassword;
    // CONNECTION_TIMEOUT and READ_TIMEOUT are in milliseconds
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;


    List<User> Userlist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        final EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        final Button bLogin = (Button) findViewById(R.id.buttonLogin);


        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AsyncFetch().execute();

            }

        });   //Make call to AsyncTask'

        findViewById(R.id.textViewRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open register screen
                startActivity(new Intent(Login.this, MainActivity.class));
            }
        });


    }



    protected class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(Login.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }


        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
                url = new URL("http://nials.no:8080/selfie/api/user");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we recieve data from json file
                //conn.setDoOutput(true);

                //con = (HttpURLConnection)urls[0].openConnection();
                JsonReader jr = new JsonReader(new InputStreamReader(conn.getInputStream()));
                jr.beginArray();
                System.out.println("HEI" + jr);

                InputStream input = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                // Pass data to onPostExecute method



                while (jr.hasNext()) {
                    System.out.println("JR has next element");
                    String email = null;
                    int id = 0;
                    String gender = null;
                    String birthday = null;
                    String name = null;
                    String password = null;
                    int phone = 123;



                    jr.beginObject();
                    while (jr.hasNext()) {
                        switch (jr.nextName()) {
                            case "email":
                                //System.out.println("HEI ID: " + jr.nextString());
                                email = jr.nextString();
                                result.append(email);
                                break;
                            /** case "size":
                             //size = jr.nextLong();
                             //break;
                             **/
                            case "password":
                                // try {
                                //version = format.parse(jr.nextString());
                                password = jr.nextString();
                                // } catch (ParseException e) {
                                //   Log.e("LoadThumb", "Failed to parse date", e);
                                // }
                                break;
                            default:
                                jr.skipValue();


                                // Pass data to onPostExecute method
                                System.out.println("Result to string: " + result.toString());


                        }
                    }
                    jr.endObject();
                    String emailtext = "teit"; String passwordtext = "teit";
                    Userlist.add(new User(id, email, password, phone, name, birthday, gender));
                    if((emailtext == email) && (passwordtext == password)){
                        startActivity(new Intent(Login.this, MenuActivity.class));

                    }
                    startActivity(new Intent(Login.this, MenuActivity.class));
                }
                System.out.println("RESULT STRING: " +result.toString());
                return (result.toString());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }

        }


        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread
            System.out.println("HEI to string :" + result.toString());
            //System.out.println("HEI :" + result);
            pdLoading.dismiss();
            List<User> data = new ArrayList<>();

            pdLoading.dismiss();
            /**for (Photo p : PhotoList) {

             }**/
            // mRVFishPrice = (RecyclerView) findViewById(R.id.fishPriceList);
            // mAdapter = new AdapterFish(MainActivity.this, Userlist);
            //  mRVFishPrice.setAdapter(mAdapter);
            // mRVFishPrice.setLayoutManager(new LinearLayoutManager(Login.this));
            /**
             try {

             JSONArray jArray = new JSONArray(result);

             // Extract data from json and store into ArrayList as class objects
             for (int i = 0; i < jArray.length(); i++) {
             System.out.println("HEI INNENFOR FORLOKKE");
             JSONObject json_data = jArray.getJSONObject(i);

             JSONObject obj = new JSONObject(response);
             String dateStr = obj.getString("birthdate");
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             Date birthDate = sdf.parse(dateStr);
             //then
             user.setBirthdate(birthDate);


             String url = json_data.getString("id");
             String date = json_data.getString("version");
             System.out.println("URL: " + url + "DATOOO: " + date);


             Photo fishData = new Photo(url, date);
             fishData.catName= json_data.getString("cat_name");
             fishData.sizeName= json_data.getString("size_name");
             fishData.price= json_data.getInt("price");
             data.add(fishData);
             }


             // Setup and Handover data to recyclerview
             mRVFishPrice = (RecyclerView) findViewById(R.id.fishPriceList);
             mAdapter = new AdapterFish(MainActivity.this, data);
             mRVFishPrice.setAdapter(mAdapter);
             mRVFishPrice.setLayoutManager(new LinearLayoutManager(MainActivity.this));

             } catch (JSONException e) {
             Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
             }
             **/
            // Setup and Handover data to recyclerview


        }


    }}

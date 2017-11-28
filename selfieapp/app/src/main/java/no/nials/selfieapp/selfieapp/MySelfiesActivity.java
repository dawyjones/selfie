package no.nials.selfieapp.selfieapp;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;

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
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class MySelfiesActivity extends AppCompatActivity {

    String[] myStrings = new String[] {"https://www.dumpaday.com/wp-content/uploads/2017/01/random-pictures-74.jpg",
            "https://gomerblog.com/wp-content/uploads/2016/05/Bear-Grylls.jpeg",
            "https://defenders.org/sites/default/files/styles/large/public/tiger-dirk-freder-isp.jpg",
            "https://images.moviepilot.com/image/upload/c_fill,h_470,q_auto:good,w_620/p0cx4svaazz1fkx3xia2.jpg",
    };

    List <Bitmap> mylist;// = Arrays.asList(myStrings);

    //Iterator itr = mylist.iterator();
    GridView simplelist;
    ArrayList selfieList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        new AsyncFetch().execute();
        super.onCreate(savedInstanceState);






    }


    protected class AsyncFetch extends AsyncTask<Void,Void,Void> {
        String data ="";
        String dataParsed = "";
        String singleParsed ="";
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://nials.no:8080/selfie/api/picture");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while(line != null){
                    line = bufferedReader.readLine();
                    data = data + line;
                }

                JSONArray JA = new JSONArray(data);

                for(int i = 0; i <JA.length(); i++){
                    JSONObject JO = (JSONObject) JA.get(i);
                    singleParsed =
                            JO.get("pictureJson") + "\n";

                    dataParsed = dataParsed + singleParsed +"\n" ;


                    selfieList.add(singleParsed);




                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            setContentView(R.layout.mygridview);
            simplelist = (GridView) findViewById(R.id.simpleGridView);
            MySelfiesListAdapter adp = new MySelfiesListAdapter(getApplicationContext(),R.layout.grid_view_items, selfieList);
            simplelist.setAdapter(adp);

            simplelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), MySelfiesFullView.class);
                    intent.putExtra("url",selfieList.get(position).toString());
                    // System.out.println("Clicked this imgURL: " +selfieList.get(position).toString());
                    startActivity(intent);
                }
            });





            //StreamActivity.data.setText(dataParsed);

                //KOMMENTERT UT FORELØBIG
          /*  byte[] imageBytes = Base64.decode(dataParsed, Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            selfieList.add(imageBytes);*/
            //StreamActivity.imageView.setImageBitmap(decodedImage);


        }
    }

}
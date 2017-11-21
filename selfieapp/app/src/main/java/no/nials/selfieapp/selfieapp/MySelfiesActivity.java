package no.nials.selfieapp.selfieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
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

    List <String> mylist = Arrays.asList(myStrings);
    Iterator itr = mylist.iterator();
    GridView simplelist;
    ArrayList selfieList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Testing: " + mylist.toString());
        setContentView(R.layout.mygridview);
        simplelist = (GridView) findViewById(R.id.simpleGridView);
        while(itr.hasNext()) {
            selfieList.add(itr.next());
            System.out.println("TESTING SELFIEList: " + selfieList.toString());
        }
        MySelfiesListAdapter adp = new MySelfiesListAdapter(this,R.layout.grid_view_items, selfieList);
        simplelist .setAdapter(adp);

    }

}
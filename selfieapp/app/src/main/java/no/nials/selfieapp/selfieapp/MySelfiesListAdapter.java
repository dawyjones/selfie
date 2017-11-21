package no.nials.selfieapp.selfieapp;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MySelfiesListAdapter extends ArrayAdapter<String> {

    ArrayList imgList = new ArrayList<>();
    Iterator itr = imgList.iterator();
  //  private final Activity context;
  //  private final String[] itemname;
   // private final List<String> urls;
    //private final Integer[] imgid;

    public MySelfiesListAdapter(Context context, int textViewRecourceId, ArrayList objects) {
        super(context, textViewRecourceId, objects);

        //System.out.println("TestingAdapterInput: " + urls.toString());
        this.imgList=objects;

       // this.itemname=itemname;
       // this.imgid=imgid;
    }
    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //TEST OMRÅDE
        System.out.println("TEST adapter: " + imgList.get(position).toString());


        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.grid_view_items, null);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);

        Picasso.with(getContext())
                .load(imgList.get(position).toString())
                .into(imageView);
        TextView textView = (TextView) v.findViewById(R.id.textView);
        //ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        //textView.setText(imgList.get(position).getbirdName());
        //imageView.setImageURI(itr.toString());
        return v;

    }

}
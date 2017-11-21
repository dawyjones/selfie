package no.nials.selfieapp.selfieapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Iterator;


public class MySelfiesListAdapter extends ArrayAdapter<String> {

    ArrayList imgList = new ArrayList<>();
    Iterator itr = imgList.iterator();

    public MySelfiesListAdapter(Context context, int textViewRecourceId, ArrayList objects) {
        super(context, textViewRecourceId, objects);
        //System.out.println("TestingAdapterInput: " + urls.toString());
        this.imgList=objects;
    }
    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //TEST OMRÅDE
       // System.out.println("TEST adapter: " + imgList.get(position).toString());

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.grid_view_items, null);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);

        Picasso.with(getContext())
                .load(imgList.get(position).toString())
                .into(imageView);
        return v;

    }

}
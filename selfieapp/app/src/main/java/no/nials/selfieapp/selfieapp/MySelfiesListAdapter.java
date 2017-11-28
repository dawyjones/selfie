package no.nials.selfieapp.selfieapp;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Iterator;


public class MySelfiesListAdapter extends ArrayAdapter<String> {

    ArrayList<String> imgList = new ArrayList();
    Iterator itr = imgList.iterator();

    public MySelfiesListAdapter(Context context, int textViewRecourceId, ArrayList objects) {
        super(context, textViewRecourceId, objects);
        //System.out.println("TestingAdapterInput: " + urls.toString());
        this.imgList = objects;
    }
    @Override
    public int getCount() {
        return super.getCount();
    }

    public ArrayList getImgList() {
        return imgList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //TEST OMRÅDE
       // System.out.println("TEST adapter: " + imgList.get(position).toString());

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.grid_view_items, null);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        System.out.println("TEST imgList size: " + imgList.size());

      /**  byte[] imageBytes = Base64.decode(dataParsed, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
**/ /*    for (int i = 0; i < imgList.size(); i++) {

            System.out.println("TEST ting : " + imgList.get(i));*/
            byte[] imageBytes = Base64.decode(imgList.get(position).toString(), Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        /*    //Byte bitkuk = imgList.get(i);
        //    butkuk


        }*/

         imageView.setImageBitmap(decodedImage);

        return v;

    }

}
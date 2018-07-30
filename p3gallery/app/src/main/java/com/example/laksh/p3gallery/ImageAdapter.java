package com.example.laksh.p3gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by laksh on 10/30/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private static final int PADDING=8;
    private static final int HEIGHT=300;
    private static final int WIDTH=300;
    private Context mContext;//This will have the context generally passed to the ImageView
    private List<Integer> mCars;//Adapter must store AdapterView's items
    private String[] w;//contains the names of the brands of the cars.
    // In the order in which cars are displayed on the grid view.

    //Storing the context and the carIds
    public ImageAdapter(Context c, List<Integer> mC,String[] brand_name){
        mContext=c;
        this.mCars=mC;
        this.w=brand_name;
    }

    //Sending the size of the mCars ie., the total number of cars
    @Override
    public int getCount(){
        return mCars.size();
    }

    //Returns the position of a particular car in the gridView
    @Override
    public long getItemId(int position){
        return mCars.get(position);
    }

    //Returns Object item at that position
    @Override
    public Object getItem(int position){return mCars.get(position);}

    //Return a ImageView for each item referenced by the image adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //if convert's view is not recycled the  intialse some attributes
        if(convertView==null){
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.main_textview, null);//Uses the main_textview layout for setting the image
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);//Text view used for the car names
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);//Image View for the displaying the images of the cars
            textView.setText(w[position]);//Set the names based on the positions of the grid item
            imageView.setImageResource(mCars.get(position));//Set the image based on the id,where id is retrieved from the Integer list mCars at position

        }else {
            grid = (View) convertView;//else use the convertView itself
        }



        return grid;


    }

}


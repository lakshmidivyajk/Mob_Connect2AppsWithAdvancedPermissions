package com.example.laksh.p3gallery;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import static com.example.laksh.p3gallery.MainActivity.EXTRA_RES_ID;

/**
 * Created by laksh on 10/30/2017.
 */

public class ImageActivity extends Activity {



    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Intent i=getIntent();  //get the intent that started this activity
        //Create a new imageView
        ImageView imageView=new ImageView(getApplicationContext());
        //Get the id of the image to be displayed. Set that image as the image for the image view
        imageView.setImageResource(i.getIntExtra(EXTRA_RES_ID,0));

        //set the image to the Content view.

        setContentView(imageView);




    }
}
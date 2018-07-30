package com.example.laksh.p3gallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public static  String EXTRA_RES_ID ="POS";
    public static  String POS = null;
    //public static Context myContext=new Context();
    public boolean b=false;
    public static  long idMain;
    private MyReciever reciever;
    //Have the image ids in a arrayList which can be used as references for the images in the project.
    private ArrayList<Integer> mThumbPlaceIds=new ArrayList<Integer>(Arrays.asList(R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.image6));
    private static final String LDS_PERMISSION =
            "edu.uic.cs478.UgosPermission" ;
    //Name of the places stored as a string array
    String[] places = {
            "Museum of Science and Industry",
            "Wrigley Field",
            "Lincoln Park Zoo",
            "Millennium Park",
            "Shedd Aquarium",
            "Adler Planetarium"

};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting the layout for the MainActivity
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("p3gallery");


     /*   IntentFilter intentFilter=new  IntentFilter("com.example.laksh.project3a1.showToast");
        reciever =new MyReciever();
            if(intentFilter!=null){
               registerReceiver(reciever,intentFilter,"com.example.lakshmidivyapermission",null);
                Toast.makeText(this,intentFilter.toString(),Toast.LENGTH_SHORT).show();

            }*/  //not required to do programmatically if including in the manifest file



        //Getting the gridView of the layout
        GridView gridView=(GridView)findViewById(R.id.gv1);
        //Registering the gridView for the ContextMenu


        //Setting the image adapter for the gridView using the ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(this,mThumbPlaceIds,places));
        //Setting onItemClickListener for the short click on the grid items to display the image in a new Activity called ImageActivity
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                Intent intent=new Intent(MainActivity.this,ImageActivity.class);
                // idMain=id;

                intent.putExtra(EXTRA_RES_ID, (int)id ); //Adding the id of the gridItem
                intent.putExtra(POS,position); //Adding the position of the gridItem
                startActivity(intent); //Start the intent to invoke the new activity

            }
        });



    }






}

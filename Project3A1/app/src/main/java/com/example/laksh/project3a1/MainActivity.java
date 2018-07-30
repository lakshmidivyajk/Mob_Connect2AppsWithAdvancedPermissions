package com.example.laksh.project3a1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        placesFragment.ListSelectionListener {

    public static String[] mPlacesArray;  //Array for storing the place names.
    public static String[] mPlacesURLArray;//Array for storing the urls for all the places.


    private final displayFragment mDisplayFragment = new displayFragment();
    private FragmentManager mFragmentManager;
    private FrameLayout mPlacesFrameLayout, mDisplayFrameLayout; //Getting the framelayouts.
    placesFragment mPF;
    displayFragment mDF;
    private static final String TAG_PLACES_FRAGMENT ="PF"; //TAG for place fragment while its being created.
    private static final String TAG_DISPLAY_FRAGMENT ="DF"; //TAG for display fragment while its being created.


    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "MainActivity";


    private static final String TOAST_INTENT =
            "com.example.laksh.project3a1.showToast";  //The name of the intent we want to broadcast
    private static final String LDS_PERMISSION ="com.example.lakshmidivyapermission" ; //The name of the permission the sender must own




    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("PROJECT3 A1"); //Statement to set the action bar with the title.

        // Get the string arrays with the places and placesURL
        mPlacesArray = getResources().getStringArray(R.array.Places);
        mPlacesURLArray = getResources().getStringArray(R.array.PlacesURL);


        // Get references to the placesFragment and to the displayFragment
        mPlacesFrameLayout = (FrameLayout) findViewById(R.id.places_fragment);
        mDisplayFrameLayout = (FrameLayout) findViewById(R.id.url_fragment);


//		mDF=(QuotesFragment)mFragmentManager.findFragmentByTag(TAG_DISPLAY_FRAGMENT);


        // Get a reference to the FragmentManager
        mFragmentManager = getFragmentManager();

        // Start a new FragmentTransaction
        FragmentTransaction fragmentTransaction = mFragmentManager
                .beginTransaction();


        //Get the retained fragments:
        mPF = (placesFragment) mFragmentManager.findFragmentByTag(TAG_PLACES_FRAGMENT);
        mDF = (displayFragment) mFragmentManager.findFragmentByTag(TAG_DISPLAY_FRAGMENT);
        //If first time create the placesFragment:
        if (mPF == null) {
            Log.d(TAG, "-----------------Places Fragment not retained SO CREATING THE FIRST INSTANCE!!!!!");
            fragmentTransaction.replace(R.id.places_fragment,
                    new placesFragment(), TAG_PLACES_FRAGMENT);

            // Commit the FragmentTransaction
            fragmentTransaction.commit();



        }
        //In else part firstly, we need to add the retained placesfragment to the framelayout.
        //Next, we need to check if the displayFragment is present or not.
        //If present, add the retained displayFragment to the framelayout else create a new one and add it to the framelayout.
        else {

            Log.d(TAG, "-----------------Places Fragment  retained");
            fragmentTransaction.replace(R.id.places_fragment,
                    mPF, TAG_PLACES_FRAGMENT);
            if (mDF != null) {
                Log.d(TAG, "-----------------Display Fragment retained MOSTLY IN LANDSCAPE MODE WITH 2 FRAGMENTS :D");
                fragmentTransaction.replace(R.id.url_fragment,
                        mDF, TAG_DISPLAY_FRAGMENT);
            } else {
                Log.d(TAG, "-----------------Display Fragment not retained MOSTLY IN LANDSCAPE MODE WITH 1 FRAGMENT BUT CREATED OTHER ONE");
                mDF = new displayFragment();

                fragmentTransaction.replace(R.id.url_fragment,
                        mDF, TAG_DISPLAY_FRAGMENT);

            }


            // Commit the FragmentTransaction
            fragmentTransaction.commit();




        }
        // Add a OnBackStackChangedListener to reset the layout when the back stack changes
        mFragmentManager
                .addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {

                        setLayout();
                    }
                });

    }

    private void setLayout( ) {

        // Determine whether the displayFragment has been added
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT) {

            if (!mDisplayFragment.isAdded()) {
                Log.d(TAG,"--------------------PORTRAIT+QUOTES NOT ADDED");

                // Make the placesFragment occupy the entire layout
                mPlacesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        MATCH_PARENT, MATCH_PARENT));
                mDisplayFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT));
            }
            else  {
                Log.d(TAG,"---------------PORTRAIT+QUOTES  ADDED");
                // Make the displayFragment occupy the entire layout

                mPlacesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT));


                mDisplayFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                        MATCH_PARENT));
            }


        }
        else
        {

            if (!mDisplayFragment.isAdded()) {
                Log.d(TAG,"-------------------LANDSCAPE+QUOTES NOT ADDED");
                // Make the placesFragment occupy the entire layout
                mPlacesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        MATCH_PARENT, MATCH_PARENT));
                mDisplayFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT));
            } else {
                Log.d(TAG,"-------------------LANDSCAPE+QUOTES  ADDED");

                // Make the placesFragment take 1/3 of the layout's width
                mPlacesFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));

                // Make the displlayFragment take 2/3's of the layout's width
                mDisplayFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 2f));
            }


        }

    }

    // Called when the user selects an item in the placesFragment
    @Override
    public void onListSelection(int index) {

        // If the displayFragment has not been added, add it now
        if (!mDisplayFragment.isAdded()) {

            // Start a new FragmentTransaction
            FragmentTransaction fragmentTransaction = mFragmentManager
                    .beginTransaction();

            // Add the displayFragment to the layout
            fragmentTransaction.replace(R.id.url_fragment,
                    mDisplayFragment,TAG_DISPLAY_FRAGMENT);

            // Add this FragmentTransaction to the backstack
            fragmentTransaction.addToBackStack(null);

            // Commit the FragmentTransaction
            fragmentTransaction.commit();

            // Force Android to execute the committed FragmentTransaction
            mFragmentManager.executePendingTransactions();
        }

        if (mDisplayFragment.getShownIndex() != index) {

            // Tell the displayFragment to show the quote string at position index
            mDisplayFragment.showQuoteAtIndex(index);

        }
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();


    }

    @Override
    protected void onRestart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }


    // Create Options Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return true;
    }
    // Process clicks on Options Menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit_Project3A1:
             //To exit the Project3A1 application we are using finish()
                finish();
                return true;
            case R.id.launch_p3_gallery:
                //To launch the p3gallery application
                //We need to send the TOAST_INTENT with LDS_PERMISSION as a broadcast.
                //The p3gallery with the LDS_PERMISSION will recieve it and launch the application.
                //Before we can send the intent broadcast, we must first check if the user has given
                //     permission to use the p3gallery app, as it defines a dangerous level permission.



                // Here, thisActivity is the current activity, check if it has required permissions
                //If not granted then request permissions using requestPermissions
                if (ContextCompat.checkSelfPermission(this,LDS_PERMISSION)!= PackageManager.PERMISSION_GRANTED)
                {
                    Log.d(TAG,"DENIED permission so requesting now.");
                        // Should we show an explanation?


                                     // No explanation needed, we can request the permission.
                                ActivityCompat.requestPermissions(this,
                                                             new String[]{LDS_PERMISSION},
                                        1);



            }//As the permission is granted, we send the intent broadcast
            else{
                    Intent aIntent=new Intent(TOAST_INTENT);
                    sendBroadcast(aIntent) ;

                }

              //  Toast.makeText(getApplicationContext(), "Intent is being broadcast",
                //    Toast.LENGTH_SHORT).show();

                return true;

            default:
                return false;
        }
    }

    //On hearing back from the user if the permission is granted
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults)
    {
            switch (requestCode)
            {
                    case 1:
                    {
                        // If request is cancelled, the result arrays are empty.
                        if (grantResults.length > 0
                         && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        // permission was granted, yay! Do the
                        // send the intent.
                            Intent aIntent=new Intent(TOAST_INTENT);
                            sendBroadcast(aIntent) ;

                        } else {

                        // permission denied, boo! Disable the
                         // Don't send any intent.
                         }
                         return;
                    }


            }
    }
}
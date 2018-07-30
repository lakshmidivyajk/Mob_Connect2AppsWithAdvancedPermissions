package com.example.laksh.p3gallery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by laksh on 10/30/2017.
 */
//Comes here after recieving the intent in the intent-filter.
public class MyReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //To launch the MainActivity so the explicit intent:
        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);
    }
}

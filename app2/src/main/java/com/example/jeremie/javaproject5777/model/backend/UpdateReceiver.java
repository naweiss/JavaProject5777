package com.example.jeremie.javaproject5777.model.backend;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.jeremie.javaproject5777.controller.AsyncTaskUpdate;
import com.example.jeremie.javaproject5777.controller.AsyncTaskUpdateActivities;

import java.util.concurrent.ExecutionException;

public class UpdateReceiver extends BroadcastReceiver {

    final String TAG = "activityContent";

    public UpdateReceiver() {
    }

    /**
     * BroadcastReceiver which updates the local database when new item was found
     * @param context - The context of this receiver
     * @param intent -The intent of this receiver, for data purposes
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        // an Intent broadcast.
        if (intent.getAction().matches("com.example.nadav.javaproject5777.update")) {
            String intentData = intent.getStringExtra("type");
            if(intentData.equals("businesses")) {
                //New businesses received
                try {
                    //AsyncTask for getting the new businesses from the content provider
                    new AsyncTaskUpdate().execute(context).get();
                    Toast.makeText(context, "businesses update", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "businesses update");
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            else if(intentData.equals("activities")) {
                //New activities received
                try {
                    //AsyncTask for getting the new activities from the content provider
                    new AsyncTaskUpdateActivities().execute(context).get();
                    Toast.makeText(context, "activities update", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "activities update");
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

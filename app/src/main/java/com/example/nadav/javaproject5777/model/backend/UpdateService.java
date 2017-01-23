package com.example.nadav.javaproject5777.model.backend;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UpdateService extends Service {
    private static final String TAG = "UpdateService";
    private DB_manager manager = DB_Factory.getDB();
    private boolean isRunning  = false;

    @Override
    public void onCreate() {
        Log.i(TAG, "Service onCreate");
        //isRunning = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG, "Service onStartCommand");

        //Creating new thread for my service
        //Always write your long running tasks in a separate thread, to avoid ANR
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Your logic that service will perform will be placed here
                //In this example we are just looping and waits for 1000 milliseconds in each loop.
                try {
                    //while (isRunning) {
                    while(true){
                        Thread.sleep(5000);//Check every 5 seconds
                        if(manager.areNewActivities()) {
                            Intent intent = new Intent(Contract.BROADCAST_URI);
                            intent.putExtra("type","activities");
                            sendBroadcast(intent);
                        }
                        else if (manager.areNewBusinesses())
                        {
                            Intent intent = new Intent(Contract.BROADCAST_URI);
                            intent.putExtra("type","businesses");
                            sendBroadcast(intent);
                        }
                    }
                }catch (Exception e) {
                }

                //Stop service once it finishes its task
                //stopSelf();
            }
        }).start();

        return Service.START_STICKY;
    }


    @Override
    public IBinder onBind(Intent arg0) {
        Log.i(TAG, "Service onBind");
        return null;
    }

    @Override
    public void onDestroy() {

        //isRunning = false;

        Log.i(TAG, "Service onDestroy");
    }
}

package com.example.jeremie.javaproject5777;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class UpdateReceiver extends BroadcastReceiver {
    public UpdateReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if (intent.getAction().matches("com.example.nadav.javaproject5777.update")) {
            String intentData = intent.getStringExtra("type");
            if(intentData.equals("businesses")) {
                Toast.makeText(context, "UPDATE", Toast.LENGTH_LONG).show();
                try {
                    new AsyncTaskUpdate().execute(context).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

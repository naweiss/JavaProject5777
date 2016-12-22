package com.example.nadav.javaproject5777.controller;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

import com.example.nadav.javaproject5777.R;
import com.example.nadav.javaproject5777.model.backend.Contract;

/**
 * Created by jerem on 22.12.16.
 */

public class ListActivities extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter
                (
                        this,
                        R.layout.item_row,
                        null,
                        new String[]{Contract.Activitie.ACTIVITIE_ACT_TYPE, Contract.Activitie.ACTIVITIE_START_DATE},
                        new int[]{R.id.itemId, R.id.itemName}
                );

        new AsyncTask<Void, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(Void... params) {
                Cursor cursor = getContentResolver().query(Contract.Activitie.ACTIVITIE_URI, null, null, null, null, null);
                return cursor;
            }

            @Override
            protected void onPostExecute(Cursor cursor) {
                super.onPostExecute(cursor);
                adapter.changeCursor(cursor);
            }
        }.execute();


        this.setListAdapter(adapter);
    }

}

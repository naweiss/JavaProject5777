package com.example.nadav.javaproject5777.controller;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

import com.example.nadav.javaproject5777.R;
import com.example.nadav.javaproject5777.model.backend.Contract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jerem on 22.12.16.
 */

public class ListActivities extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent= getIntent();
        final String value1= intent.getStringExtra("key");
        final String[] mSelectionArgs = value1 != null ? new String[] {value1} : null;

        super.onCreate(savedInstanceState);
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter
                (
                        this,
                        R.layout.item_row,
                        null,
                        new String[]{Contract.Activitie.ACTIVITIE_ID,Contract.Activitie.ACTIVITIE_PRICE, Contract.Activitie.ACTIVITIE_COUNTRY_NAME},
                        new int[]{R.id.itemId,R.id.itemType, R.id.itemName}
                );

        new AsyncTask<Void, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(Void... params) {
                Cursor cursor = getContentResolver().query(Contract.Activitie.ACTIVITIE_URI, null, null, mSelectionArgs, null, null);
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

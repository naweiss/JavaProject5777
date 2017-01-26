package com.example.jeremie.javaproject5777;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.AsyncTask;

/**
 * Created by jerem on 26.01.17.
 */

public class AsyncTaskUpdateActivities extends AsyncTask<Context, Void, Cursor> {

private ListDB_manager db_manager = ListDB_manager.newInstance();

@Override
protected Cursor doInBackground(Context... contexts) {
        if(contexts.length < 1)
        return null;
        ContentResolver resolver = contexts[0].getContentResolver();
        Cursor mCursor = resolver.query(Contract.Activitie.ACTIVITIE_URI, null, null, null, null);
        return mCursor;
        }

@Override
protected void onPostExecute(Cursor cursor) {
        super.onPostExecute(cursor);
        try {
        int i = 0;
        db_manager.clearActivities();
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
        // The Cursor is now set to the right position
        ContentValues map = new ContentValues();
        DatabaseUtils.cursorRowToContentValues(cursor,map);

        db_manager.addActivity(map);

        }
        } catch (Exception e) {
        e.printStackTrace();
        }
        }
        }

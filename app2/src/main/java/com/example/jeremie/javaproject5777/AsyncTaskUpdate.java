package com.example.jeremie.javaproject5777;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.AsyncTask;

/**
 * Created by nadav on 1/23/2017.
 * Package: com.example.jeremie.javaproject5777
 */

public class AsyncTaskUpdate extends AsyncTask<Context, Void, Cursor> {

    private ListDB_manager db_manager = ListDB_manager.newInstance();

    @Override
    protected Cursor doInBackground(Context... contexts) {
        if(contexts.length < 1)
            return null;
        ContentResolver resolver = contexts[0].getContentResolver();
        Cursor mCursor = resolver.query(Contract.Business.BUSINESS_URI, null, null, null, null);
        return mCursor;
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        super.onPostExecute(cursor);
        try {
            db_manager.clearBusinesses();
            for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                // The Cursor is now set to the right position
                ContentValues map = new ContentValues();
                DatabaseUtils.cursorRowToContentValues(cursor,map);
                db_manager.addBusiness(map);
            }
            db_manager.NotifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

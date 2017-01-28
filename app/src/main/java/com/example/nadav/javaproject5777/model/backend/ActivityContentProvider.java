package com.example.nadav.javaproject5777.model.backend;


        import android.content.ContentProvider;
        import android.content.ContentUris;
        import android.content.ContentValues;
        import android.database.Cursor;
        import android.database.MatrixCursor;
        import android.net.Uri;
        import android.support.annotation.Nullable;
        import android.util.Log;

/**
 * Created by nadav on 12/14/2016.
 * Package: com.example.nadav.javaproject5777.model.backend
 */

public class ActivityContentProvider extends ContentProvider {
    final  int e=2;
    DB_manager manager = DB_Factory.getDB();
    final String TAG = "activityContent";

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate");
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.d(TAG, "query " + uri.toString());

        String listName = uri.getLastPathSegment();
        // String s = AcademyContract.Student.STUDENT_URI.getLastPathSegment();
        switch (listName) {
            case "user":
                if(selectionArgs == null)
                    return manager.getAllUsers();//
                else {
                    MatrixCursor matrixCursor = new MatrixCursor(new String[] {"exist"});
                    matrixCursor.addRow(new Object[]{ manager.isUserExist(selectionArgs[0], selectionArgs[1]) });
                    return matrixCursor;
                }

            case "business":
                return manager.getAllBusinesses();//

            case "activitie":
                if(selectionArgs == null)
                    return manager.getAllActivity();//
                else
                    return manager.getBusinessActivity(Integer.parseInt(selectionArgs[0]));
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        Log.d(TAG, "insert " + uri.toString());

        String listName = uri.getLastPathSegment();
        long id = -1;
        switch (listName) {
            case "user":
                id = manager.addUser(contentValues);
                return ContentUris.withAppendedId(uri, id);


            case "business":
                id = manager.addBusiness(contentValues);
                return ContentUris.withAppendedId(uri, id);

            case "activitie":
                id = manager.addActivity(contentValues);
                return ContentUris.withAppendedId(uri, id);
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}



package com.example.nadav.javaproject5777.model.datasource;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.nadav.javaproject5777.model.backend.DB_manager;

/**
 * Created by jerem on 01.01.17.
 */

public class MySQL_DBmanager implements DB_manager {


    @Override
    public int addUser(ContentValues values) {
        return 0;
    }

    @Override
    public boolean addActivity(ContentValues values) {
        return false;
    }

    @Override
    public int addBusiness(ContentValues values) {
        return 0;
    }

    @Override
    public Boolean areNewActivities() {
        return null;
    }

    @Override
    public Boolean areNewBusinesses() {
        return null;
    }

    @Override
    public Boolean areNewUsers() {
        return null;
    }

    @Override
    public Cursor getAllUsers() {
        return null;
    }

    @Override
    public Cursor getAllActivity() {
        return null;
    }

    @Override
    public Cursor getBusinessActivity(int businessId) {
        return null;
    }

    @Override
    public Cursor getAllBusinesses() {
        return null;
    }
}

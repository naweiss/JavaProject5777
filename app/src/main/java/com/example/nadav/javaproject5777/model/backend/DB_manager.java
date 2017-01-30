package com.example.nadav.javaproject5777.model.backend;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.nadav.javaproject5777.model.entities.Activitie;
import com.example.nadav.javaproject5777.model.entities.Business;
import com.example.nadav.javaproject5777.model.entities.User;

import java.util.List;

/**
 * Created by jerem on 07.12.16.
 */

public interface DB_manager {

    public int addUser(ContentValues values);
    public int addActivity(ContentValues values);
    public int addBusiness(ContentValues values);
    public Boolean areNewActivities();
    public Boolean areNewBusinesses();
    public Boolean isUserExist(String name,String password);
    public Cursor getAllUsers();
    public Cursor getAllActivity();
    public Cursor getBusinessActivity(int businessId);
    public Cursor getAllBusinesses();
}

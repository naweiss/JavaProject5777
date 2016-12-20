package com.example.nadav.javaproject5777.model.datasource;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.nadav.javaproject5777.model.backend.DB_manager;
import com.example.nadav.javaproject5777.model.entities.Activitie;
import com.example.nadav.javaproject5777.model.entities.Business;
import com.example.nadav.javaproject5777.model.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nadav on 12/1/2016.
 */

public class ListDB_manager implements DB_manager{

    private List<Activitie> activities = new ArrayList<Activitie>();
    private List<Business> businesses = new ArrayList<Business>();
    private List<User> users = new ArrayList<User>();
    private Boolean new_activity = false;
    private Boolean new_user = false;
    private Boolean new_businesse = false;

    @Override
    public int addUser(ContentValues values) {
        User usr = Converter.ContentValuesToUser(values);
        users.add(usr);
        new_user = true;
        return usr.getId();
    }

    @Override
    public boolean addActivity(ContentValues values) {
        try {
            Activitie act = Converter.ContentValuesToActivitie(values);
            activities.add(act);
            new_activity = true;
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }

    }

    @Override
    public int addBusiness(ContentValues values) {
        try {
            Business bus = Converter.ContentValuesToBusiness(values);
            businesses.add(bus);
            new_businesse = true;
            return bus.getId();
        }
        catch (Exception ex)
        {
            return -1;
        }
    }

    @Override
    public Boolean areNewActivities() {
        if(new_activity) {
            new_activity = false;
            return true;
        }
        else
            return  false;
    }

    @Override
    public Boolean areNewBusinesses() {
        if(new_businesse) {
            new_businesse = false;
            return true;
        }
        else
            return  false;
    }

    @Override
    public Boolean areNewUsers() {
        if(new_user) {
            new_user = false;
            return true;
        }
        else
            return  false;
    }

    @Override
    public Cursor getAllUsers() {
        return Converter.usersListToCursor(users);
    }

    @Override
    public Cursor getAllActivity() {
        return Converter.activitieListToCursor(activities);
    }

    @Override
    public Cursor getBusinessActivity(int bussinessId) {
        List<Activitie> businessActivity = new ArrayList<Activitie>();

        for (Activitie a : activities) {
            if (a.getBusinessId() == bussinessId) {
                businessActivity.add(a);
            }
        }
        return Converter.activitieListToCursor(businessActivity);
    }

    @Override
    public Cursor getAllBusinesses() {
        return Converter.businessListToCursor(businesses);
    }
}

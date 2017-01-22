package com.example.jeremie.javaproject5777;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.jeremie.javaproject5777.entities.Activitie;
import com.example.jeremie.javaproject5777.entities.Business;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nadav on 12/1/2016.
 */

public class ListDB_manager {

    private List<Activitie> activities = new ArrayList<Activitie>();
    private List<Business> businesses = new ArrayList<Business>();


    public int addActivity(ContentValues values) {
        try {
            Activitie act = Converter.ContentValuesToActivitie(values);
            activities.add(act);

            return act.getId();
        }
        catch (Exception ex)
        {
            return -1;
        }

    }


    public int addBusiness(ContentValues values) {
        try {
            Business bus = Converter.ContentValuesToBusiness(values);
            businesses.add(bus);
            return bus.getId();
        }
        catch (Exception ex)
        {
            return -1;
        }
    }


    public List<Activitie> getAllActivity() {
        return activities;
    }


    public List<Activitie> getBusinessActivity(int bussinessId) {
        List<Activitie> businessActivity = new ArrayList<Activitie>();

        for (Activitie a : activities) {
            if (a.getBusinessId() == bussinessId) {
                businessActivity.add(a);
            }
        }
        return businessActivity;
    }


    public List<Business> getAllBusinesses() {
        return businesses;
    }
}

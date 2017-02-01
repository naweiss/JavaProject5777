package com.example.jeremie.javaproject5777.model.datasource;

import android.content.ContentValues;

import com.example.jeremie.javaproject5777.model.entities.Activitie;
import com.example.jeremie.javaproject5777.model.entities.Business;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nadav on 12/1/2016.
 * Package: ${PACKAGE_NAME}
 */

public class ListDB_manager {

    public interface NotifyDataSetChangedListener {
        void onDataSetChanged();
    }

    private NotifyDataSetChangedListener update = null;

    public void setDataSetChangedListener(NotifyDataSetChangedListener listener) {
        this.update = listener;
    }

    public void NotifyDataSetChanged(){
        if(update != null)
            update.onDataSetChanged();
    }

    private static ListDB_manager manager = null;

    public static ListDB_manager getInstance() {
        if(manager == null)
            manager = new ListDB_manager();
        return manager;
    }

    private ListDB_manager(){}

    private List<Activitie> activities = new ArrayList<>();
    private List<Business> businesses = new ArrayList<>();


    public void clearActivities(){
        activities.clear();
    }

    public void clearBusinesses(){
        businesses.clear();
    }

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
    public Business getBusiness(int bussinessId){
        Business business = new Business();
        for (Business b : businesses) {
            if (b.getId() == bussinessId)
                business = b;
        }
        return business;
    }

    public List<Activitie> getAllActivity() {
        return activities;
    }


    public List<Activitie> getBusinessActivity(int bussinessId) {
        List<Activitie> businessActivity = new ArrayList<>();

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

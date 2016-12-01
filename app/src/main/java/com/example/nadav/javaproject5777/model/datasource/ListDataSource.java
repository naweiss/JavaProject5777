package com.example.nadav.javaproject5777.model.datasource;

import com.example.nadav.javaproject5777.model.entities.Activities;
import com.example.nadav.javaproject5777.model.entities.Business;
import com.example.nadav.javaproject5777.model.entities.Users;

import java.util.ArrayList;

/**
 * Created by nadav on 12/1/2016.
 */

public class ListDataSource {
    private ArrayList<Activities> activities = new ArrayList<Activities>();
    private ArrayList<Business> businesses = new ArrayList<Business>();
    private ArrayList<Users> users = new ArrayList<Users>();

    public void addActivity(Activities act)
    {
        activities.add(act);
    }

    public void removeActivity(Activities act)
    {
        activities.remove(act);
    }

    public void addBusiness(Business bus)
    {
        businesses.add(bus);
    }

    public void removeBusiness(Business bus)
    {
        businesses.remove(bus);
    }

    public void addUsers(Users usr)
    {
        users.add(usr);
    }

    public void removeUsers(Users usr)
    {
        users.remove(usr);
    }
}

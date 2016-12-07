package com.example.nadav.javaproject5777.model.datasource;

import com.example.nadav.javaproject5777.model.backend.DB_manager;
import com.example.nadav.javaproject5777.model.entities.Activitie;
import com.example.nadav.javaproject5777.model.entities.Business;
import com.example.nadav.javaproject5777.model.entities.User;

import java.util.ArrayList;

/**
 * Created by nadav on 12/1/2016.
 */

public class ListDB_manager implements DB_manager{

    private ArrayList<Activitie> activities = new ArrayList<Activitie>();
    private ArrayList<Business> businesses = new ArrayList<Business>();
    private ArrayList<User> users = new ArrayList<User>();

    @Override
    public void addUser(User usr) {

    }

    @Override
    public void addActivity(Activitie activitie) {

    }

    @Override
    public void addBusiness(Business business) {

    }

    @Override
    public boolean checkIfNewActivityOrBusiness() {
        return false;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return null;
    }

    @Override
    public ArrayList<Activitie> getAllActivity() {
        return null;
    }

    @Override
    public ArrayList<Business> getAllBusinesses() {
        return null;
    }


    /**public void addActivity(Activitie act)
    {
        activities.add(act);
    }

    public void removeActivity(Activitie act)
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

    public void addUsers(User usr)
    {
        users.add(usr);
    }

    public void removeUsers(User usr)
    {
        users.remove(usr);
    }
     **/
}

package com.example.nadav.javaproject5777.model.datasource;

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
    public Boolean checkIfNewActivityOrBusiness() {
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public List<Activitie> getAllActivity() {
        return null;
    }

    @Override
    public List<Activitie> getBusinessActivity(Business business) {
        return null;
    }

    @Override
    public List<Business> getAllBusinesses() {
        return null;
    }

}

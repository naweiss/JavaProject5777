package com.example.nadav.javaproject5777.model.backend;

import com.example.nadav.javaproject5777.model.entities.Activitie;
import com.example.nadav.javaproject5777.model.entities.Business;
import com.example.nadav.javaproject5777.model.entities.User;

import java.util.ArrayList;

/**
 * Created by jerem on 07.12.16.
 */

public interface DB_manager {

    public void addUser(User usr);
    public void addActivity(Activitie activitie);
    public void addBusiness(Business business);
    public boolean checkIfNewActivityOrBusiness();
    public ArrayList<User>getAllUsers();
    public ArrayList<Activitie>getAllActivity();
    public ArrayList<Business>getAllBusinesses();



}

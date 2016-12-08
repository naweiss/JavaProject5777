package com.example.nadav.javaproject5777.model.backend;

import com.example.nadav.javaproject5777.model.entities.Activitie;
import com.example.nadav.javaproject5777.model.entities.Business;
import com.example.nadav.javaproject5777.model.entities.User;

import java.util.List;

/**
 * Created by jerem on 07.12.16.
 */

public interface DB_manager {

    public void addUser(User usr);
    public void addActivity(Activitie activitie);
    public void addBusiness(Business business);
    public Boolean checkIfNewActivityOrBusiness();
    public List<User> getAllUsers();
    public List<Activitie>getAllActivity();
    public List<Activitie>getBusinessActivity(Business business);
    public List<Business>getAllBusinesses();
}

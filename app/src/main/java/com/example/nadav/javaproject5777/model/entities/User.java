package com.example.nadav.javaproject5777.model.entities;

/**
 * Created by nadav on 12/1/2016.
 */

public class User {
    private static int count =0;

    private int id;
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String name, String password) {
        this.id = count++;
        this.name = name;
        this.password = password;
    }


}
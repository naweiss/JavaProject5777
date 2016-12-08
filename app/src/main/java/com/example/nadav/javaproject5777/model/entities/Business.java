package com.example.nadav.javaproject5777.model.entities;

import android.provider.ContactsContract;

import java.net.URL;

/**
 * Created by nadav on 12/1/2016.
 */

public class Business {
    private static int count =0;
    private int id;
    private String name;
    private Addresss address;
    private String phone;
    private String email;//^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]+)$
    private URL link;

    public Business(Addresss address, String email, URL link, String name, String phone) {
        this.id = count++;
        this.address = address;
        this.email = email;
        this.link = link;
        this.name = name;
        this.phone = phone;
    }

    public Addresss getAddress() {
        return address;
    }

    public void setAddress(Addresss address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

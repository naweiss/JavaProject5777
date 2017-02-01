package com.example.jeremie.javaproject5777.entities;

import java.net.URL;

/**
 * Created by nadav on 12/25/2016.
 * Package: com.example.nadav.testproject
 */

public class Business {

    private static int count =0;
    private int id;
    private String name;
    private Address address;
    private String phone;
    private String email;
    private URL link;

    public Business() {

    }

    public Business(Address address, String email, URL link, String name, String phone) {
        this.id = count++;
        this.address = address;
        this.email = email;
        this.link = link;
        this.name = name;
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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

    @Override
    public String toString() {
        return name+" "+address +" "+email +" "+link +" "+name +" "+phone;
    }
}

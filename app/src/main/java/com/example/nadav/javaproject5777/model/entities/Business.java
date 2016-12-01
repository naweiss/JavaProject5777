package com.example.nadav.javaproject5777.model.entities;

import java.net.URL;

/**
 * Created by nadav on 12/1/2016.
 */

public class Business {
    private int id;
    private String name;
    private String[] address = new String[3];
    private String phone;
    private String email;
    private URL link;

    public Business(String[] address, String email, int id, URL link, String name, String phone) {
        this.address = address;
        this.email = email;
        this.id = id;
        this.link = link;
        this.name = name;
        this.phone = phone;
    }

    public String[] getAddress() {
        return address;
    }

    public void setAddress(String[] address) {
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

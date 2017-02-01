package com.example.jeremie.javaproject5777.entities;

/**
 * Created by nadav on 12/25/2016.
 * Package: com.example.nadav.testproject
 */

public class Address {
    private String Country;
    private String City;
    private String Street;
    private int zipCode;

    public Address(String country,String city, String street, int zip) {
        City = city;
        Country = country;
        zipCode = zip;
        Street = street;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zip) {
        zipCode = zip;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    //Added
    @Override
    public String toString() {
        return zipCode+": "+Country+", "+City+", "+Street;
    }
}

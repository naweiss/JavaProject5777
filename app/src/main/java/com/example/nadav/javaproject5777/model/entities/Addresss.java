package com.example.nadav.javaproject5777.model.entities;

/**
 * Created by nadav on 12/8/2016.
 */

public class Addresss {

    private String Country;
    private String City;
    private String Street;
    private int HouseNumber;

    public Addresss(String city, String country, int houseNumber, String street) {
        City = city;
        Country = country;
        HouseNumber = houseNumber;
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

    public int getHouseNumber() {
        return HouseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        HouseNumber = houseNumber;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }
}

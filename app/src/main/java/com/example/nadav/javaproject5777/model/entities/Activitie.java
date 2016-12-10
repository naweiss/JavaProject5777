package com.example.nadav.javaproject5777.model.entities;

import java.util.Date;

public class Activitie {
    private ActivityType actType;
    private String countryName;
    private Date startDate;
    private Date endDate;
    private double price;
    private String description;
    private int businessId;

    public Activitie(int businessId,ActivityType actType, String countryName, String description, Date endDate, double price, Date startDate) {
        this.businessId = businessId;
        this.actType = actType;
        this.countryName = countryName;
        this.description = description;
        this.endDate = endDate;
        this.price = price;
        this.startDate = startDate;
    }

    public Activitie() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityType getActType() {
        return actType;
    }

    public void setActType(ActivityType actType) {
        this.actType = actType;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


}

package com.example.nadav.javaproject5777.model.datasource;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.util.Log;

import com.example.nadav.javaproject5777.controller.MainActivity;
import com.example.nadav.javaproject5777.model.backend.Contract;
import com.example.nadav.javaproject5777.model.backend.DB_manager;
import com.example.nadav.javaproject5777.model.backend.UpdateService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by jerem on 01.01.17.
 */

public class MySQL_DBmanager implements DB_manager {

    private final String UserName="naweiss";
    private final String WEB_URL = "http://"+UserName+".vlab.jct.ac.il/travel/";

    private boolean usersFlag = false;
    private boolean businessFlag = false;
    private boolean activityFlag = false;

    public void printLog(String message)
    {
        Log.d(this.getClass().getName(),"\n"+message);
    }
    public void printLog(Exception message)
    {
        Log.d(this.getClass().getName(),"Exception-->\n"+message);
    }
    @Override
    public int addUser(ContentValues values) {
        try {
            String result = PHPtools.POST(WEB_URL + "addUser.php", values);
            int id = Integer.parseInt(result);
            if (id > 0)
                usersFlag=true;
            printLog("addUser:\n" + result);
            return id;
        } catch (IOException e) {
            printLog("addUser Exception:\n" + e);
            return -1;
        }
    }

    @Override
    public int addActivity(ContentValues values) {
        try {
            String result = PHPtools.POST(WEB_URL + "/addActivity.php", values);
            int id = Integer.parseInt(result);
            if (id > 0)
                activityFlag=true;
            printLog("addActivity:\n" + result);
            return id;
        } catch (IOException e) {
            printLog("addActivity Exception:\n" + e);
            return -1;
        }
    }

    @Override
    public int addBusiness(ContentValues values) {

        try {
            String result = PHPtools.POST(WEB_URL + "addBusiness.php", values);
            int id = Integer.parseInt(result);
            if (id > 0)
                businessFlag=true;
            printLog("addBusiness:\n" + result);
            return id;
        } catch (IOException e) {
            printLog("addBusiness Exception:\n" + e);
            return -1;
        }
    }

    @Override
    public Boolean areNewActivities() {
        if(activityFlag) {
            activityFlag = false;
            return true;
        }
        else
            return  false;
    }

    @Override
    public Boolean areNewBusinesses() {
        if(businessFlag) {
            businessFlag = false;
            return true;
        }
        else
            return  false;
    }

    @Override
    public Boolean areNewUsers() {
        if(usersFlag) {
            usersFlag = false;
            return true;
        }
        else
            return  false;
    }

    @Override
    public Boolean isUserExist(String name,String password) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name",name);
            contentValues.put("password",password);
            String result = PHPtools.POST(WEB_URL + "isUserExist.php", contentValues);
            if (result.equals("user exist")) {
                printLog("foundUser:\n" + result);
                return true;
            }else {
                printLog("notFoundUser:\n" + result);
                return false;
            }
        } catch (IOException e) {
            printLog("Find Exception:\n" + e);
            return false;
        }
    }

    @Override
    public Cursor getAllUsers() {
        try {
            MatrixCursor matrixCursor = new MatrixCursor(new String[]
                    {
                            Contract.User.USER_ID,
                            Contract.User.USER_NAME,
                            Contract.User.USER_PASSWORD
                    });
            String str = PHPtools.GET(WEB_URL + "getUsers.php");
            JSONArray array = new JSONObject(str).getJSONArray("users");


            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = null;

                jsonObject = array.getJSONObject(i);

                matrixCursor.addRow(new Object[]{
                        jsonObject.getInt(Contract.User.USER_ID),
                        jsonObject.getString(Contract.User.USER_NAME),
                        jsonObject.getString(Contract.User.USER_PASSWORD)

                });
            }
            return matrixCursor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Cursor getAllActivity()
    {
        try {
            MatrixCursor matrixCursor = new MatrixCursor(new String[]
                    {
                            Contract.Activitie.ACTIVITIE_ID,
                            Contract.Activitie.ACTIVITIE_ACT_TYPE,
                            Contract.Activitie.ACTIVITIE_BUSSINES_ID,
                            Contract.Activitie.ACTIVITIE_COUNTRY_NAME,
                            Contract.Activitie.ACTIVITIE_DESCTIPTION,
                            Contract.Activitie.ACTIVITIE_START_DATE,
                            Contract.Activitie.ACTIVITIE_END_DATE,
                            Contract.Activitie.ACTIVITIE_PRICE
                    });
            String str = PHPtools.GET(WEB_URL + "/getActivities.php");
            JSONArray array = new JSONObject(str).getJSONArray("activities");


            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = null;

                jsonObject = array.getJSONObject(i);

                matrixCursor.addRow(new Object[]{
                        jsonObject.getInt(Contract.Activitie.ACTIVITIE_ID),
                        jsonObject.getString(Contract.Activitie.ACTIVITIE_ACT_TYPE),
                        jsonObject.getInt(Contract.Activitie.ACTIVITIE_BUSSINES_ID),
                        jsonObject.getString(Contract.Activitie.ACTIVITIE_COUNTRY_NAME),
                        jsonObject.getString(Contract.Activitie.ACTIVITIE_DESCTIPTION),
                        jsonObject.getString(Contract.Activitie.ACTIVITIE_START_DATE),
                        jsonObject.getString(Contract.Activitie.ACTIVITIE_END_DATE),
                        jsonObject.getDouble(Contract.Activitie.ACTIVITIE_PRICE)
                });
            }
            return matrixCursor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Cursor getBusinessActivity(int businessId) {

        try {
            MatrixCursor matrixCursor = new MatrixCursor(new String[]
                    {
                            Contract.Activitie.ACTIVITIE_ID,
                            Contract.Activitie.ACTIVITIE_ACT_TYPE,
                            Contract.Activitie.ACTIVITIE_BUSSINES_ID,
                            Contract.Activitie.ACTIVITIE_COUNTRY_NAME,
                            Contract.Activitie.ACTIVITIE_DESCTIPTION,
                            Contract.Activitie.ACTIVITIE_START_DATE,
                            Contract.Activitie.ACTIVITIE_END_DATE,
                            Contract.Activitie.ACTIVITIE_PRICE
                    });
            ContentValues contentValues = new ContentValues();
            contentValues.put("id",businessId);
            String result = PHPtools.POST(WEB_URL + "getActivitiesOffBusiness.php", contentValues);
            JSONArray array = new JSONObject(result).getJSONArray("activities");


            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = null;

                jsonObject = array.getJSONObject(i);

                matrixCursor.addRow(new Object[]{
                        jsonObject.getInt(Contract.Activitie.ACTIVITIE_ID),
                        jsonObject.getString(Contract.Activitie.ACTIVITIE_ACT_TYPE),
                        jsonObject.getInt(Contract.Activitie.ACTIVITIE_BUSSINES_ID),
                        jsonObject.getString(Contract.Activitie.ACTIVITIE_COUNTRY_NAME),
                        jsonObject.getString(Contract.Activitie.ACTIVITIE_DESCTIPTION),
                        jsonObject.getString(Contract.Activitie.ACTIVITIE_START_DATE),
                        jsonObject.getString(Contract.Activitie.ACTIVITIE_END_DATE),
                        jsonObject.getDouble(Contract.Activitie.ACTIVITIE_PRICE)
                });
            }
            return matrixCursor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Cursor getAllBusinesses() {
        try {
            MatrixCursor matrixCursor = new MatrixCursor(new String[]
                    {
                            Contract.Business.BUSINESS_ID,
                            Contract.Business.BUSINESS_NAME,
                            Contract.Business.BUSINESS_ADDRESS_STREET,
                            Contract.Business.BUSINESS_ADDRESS_CITY,
                            Contract.Business.BUSINESS_ADDRESS_ZIPCODE,
                            Contract.Business.BUSINESS_ADDRESS_COUNTRY,
                            Contract.Business.BUSINESS_PHONE,
                            Contract.Business.BUSINESS_EMAIL,
                            Contract.Business.BUSINESS_LINK
                    });
            String str = PHPtools.GET(WEB_URL + "/getBusinesses.php");
            JSONArray array = new JSONObject(str).getJSONArray("businesses");


            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = null;

                jsonObject = array.getJSONObject(i);

                matrixCursor.addRow(new Object[]{
                        jsonObject.getInt(Contract.Business.BUSINESS_ID),
                        jsonObject.getString(Contract.Business.BUSINESS_NAME),
                        jsonObject.getString(Contract.Business.BUSINESS_ADDRESS_STREET),
                        jsonObject.getString(Contract.Business.BUSINESS_ADDRESS_CITY),
                        jsonObject.getInt(Contract.Business.BUSINESS_ADDRESS_ZIPCODE),
                        jsonObject.getString(Contract.Business.BUSINESS_ADDRESS_COUNTRY),
                        jsonObject.getString(Contract.Business.BUSINESS_PHONE),
                        jsonObject.getString(Contract.Business.BUSINESS_EMAIL),
                        jsonObject.getString(Contract.Business.BUSINESS_LINK)
                });
            }
            return matrixCursor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

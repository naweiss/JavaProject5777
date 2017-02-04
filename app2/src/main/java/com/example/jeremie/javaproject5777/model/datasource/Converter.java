package com.example.jeremie.javaproject5777.model.datasource;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import com.example.jeremie.javaproject5777.model.backend.Contract;
import com.example.jeremie.javaproject5777.model.entities.Activitie;
import com.example.jeremie.javaproject5777.model.entities.ActivityType;
import com.example.jeremie.javaproject5777.model.entities.Address;
import com.example.jeremie.javaproject5777.model.entities.Business;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by nadav on 12/10/2016.
 * Package: ${PACKAGE_NAME}
 * class to convert object to contectValues and contrary
 */

class Converter {
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public static ContentValues businessToContentValues(Business business) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.Business.BUSINESS_ID, business.getId());
        contentValues.put(Contract.Business.BUSINESS_NAME, business.getName());
        contentValues.put(Contract.Business.BUSINESS_ADDRESS_COUNTRY, business.getAddress().getCountry());
        contentValues.put(Contract.Business.BUSINESS_ADDRESS_CITY, business.getAddress().getCity());
        contentValues.put(Contract.Business.BUSINESS_ADDRESS_STREET, business.getAddress().getStreet());
        contentValues.put(Contract.Business.BUSINESS_ADDRESS_ZIPCODE, business.getAddress().getZipCode());
        contentValues.put(Contract.Business.BUSINESS_PHONE, business.getPhone());
        contentValues.put(Contract.Business.BUSINESS_EMAIL, business.getEmail());
        contentValues.put(Contract.Business.BUSINESS_LINK, business.getLink().toString());

        return contentValues;
    }


    public static ContentValues activitieToContentValues(Activitie activitie) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.Activitie.ACTIVITIE_ID,activitie.getId());
        contentValues.put(Contract.Activitie.ACTIVITIE_ACT_TYPE, activitie.getActType().toString());
        contentValues.put(Contract.Activitie.ACTIVITIE_BUSSINES_ID, activitie.getBusinessId());
        contentValues.put(Contract.Activitie.ACTIVITIE_COUNTRY_NAME, activitie.getCountryName());
        contentValues.put(Contract.Activitie.ACTIVITIE_DESCTIPTION, activitie.getDescription());
        contentValues.put(Contract.Activitie.ACTIVITIE_START_DATE, df.format(activitie.getEndDate()));
        contentValues.put(Contract.Activitie.ACTIVITIE_END_DATE, df.format(activitie.getStartDate()));
        contentValues.put(Contract.Activitie.ACTIVITIE_PRICE, activitie.getPrice());
        return contentValues;
    }

    static Activitie ContentValuesToActivitie(ContentValues contentValues) throws Exception {
        Activitie act = new Activitie();
        act.setId(contentValues.getAsInteger(Contract.Activitie.ACTIVITIE_ID));
        act.setActType(ActivityType.valueOf(contentValues.getAsString(Contract.Activitie.ACTIVITIE_ACT_TYPE)));
        act.setBusinessId(contentValues.getAsInteger(Contract.Activitie.ACTIVITIE_BUSSINES_ID));
        act.setCountryName(contentValues.getAsString(Contract.Activitie.ACTIVITIE_COUNTRY_NAME));
        act.setDescription(contentValues.getAsString(Contract.Activitie.ACTIVITIE_DESCTIPTION));
        act.setStartDate(df.parse(contentValues.getAsString(Contract.Activitie.ACTIVITIE_START_DATE)));
        act.setEndDate(df.parse(contentValues.getAsString(Contract.Activitie.ACTIVITIE_END_DATE)));
        act.setPrice(contentValues.getAsDouble(Contract.Activitie.ACTIVITIE_PRICE));

        return act;
    }



    static Business ContentValuesToBusiness(ContentValues contentValues) throws Exception{

        Business bus = new Business();
        bus.setId(contentValues.getAsInteger(Contract.Business.BUSINESS_ID));
        bus.setName(contentValues.getAsString(Contract.Business.BUSINESS_NAME));
        Address addresss = new Address(contentValues.getAsString(Contract.Business.BUSINESS_ADDRESS_COUNTRY),
                contentValues.getAsString(Contract.Business.BUSINESS_ADDRESS_CITY),
                contentValues.getAsString(Contract.Business.BUSINESS_ADDRESS_STREET),
                contentValues.getAsInteger(Contract.Business.BUSINESS_ADDRESS_ZIPCODE));
        bus.setAddress(addresss);
        bus.setPhone(contentValues.getAsString(Contract.Business.BUSINESS_PHONE));
        bus.setEmail(contentValues.getAsString(Contract.Business.BUSINESS_EMAIL));
        bus.setLink(new URL(contentValues.getAsString(Contract.Business.BUSINESS_LINK)));

        return bus;
    }



    public static Cursor activitieListToCursor(List<Activitie> activities) {
        String[] columns = new String[]
                {
                        Contract.Activitie.ACTIVITIE_ID,
                        Contract.Activitie.ACTIVITIE_ACT_TYPE,
                        Contract.Activitie.ACTIVITIE_COUNTRY_NAME,
                        Contract.Activitie.ACTIVITIE_START_DATE,
                        Contract.Activitie.ACTIVITIE_END_DATE,
                        Contract.Activitie.ACTIVITIE_DESCTIPTION,
                        Contract.Activitie.ACTIVITIE_PRICE,
                        Contract.Activitie.ACTIVITIE_BUSSINES_ID
                };

        MatrixCursor matrixCursor = new MatrixCursor(columns);

        for (Activitie a : activities) {
            matrixCursor.addRow(new Object[]{a.getId(),a.getActType(), a.getCountryName(),df.format(a.getStartDate()),df.format(a.getEndDate()),a.getDescription(),a.getPrice(),a.getBusinessId()});
        }

        return matrixCursor;
    }

    public static Cursor businessListToCursor(List<Business> businesses) {
        String[] columns = new String[]
                {
                        Contract.Business.BUSINESS_ID,
                        Contract.Business.BUSINESS_NAME,
                        Contract.Business.BUSINESS_ADDRESS_COUNTRY,
                        Contract.Business.BUSINESS_ADDRESS_CITY,
                        Contract.Business.BUSINESS_ADDRESS_STREET,
                        Contract.Business.BUSINESS_ADDRESS_ZIPCODE,
                        Contract.Business.BUSINESS_EMAIL,
                        Contract.Business.BUSINESS_PHONE,
                        Contract.Business.BUSINESS_LINK,
                };

        MatrixCursor matrixCursor = new MatrixCursor(columns);

        for (Business b : businesses) {
            matrixCursor.addRow(new Object[]
                    {
                            b.getId(),
                            b.getName(),
                            b.getAddress().getCountry(),
                            b.getAddress().getCity(),
                            b.getAddress().getStreet(),
                            b.getAddress().getZipCode(),
                            b.getEmail(),
                            b.getPhone(),
                            b.getLink()
                    });
        }

        return matrixCursor;
    }
}

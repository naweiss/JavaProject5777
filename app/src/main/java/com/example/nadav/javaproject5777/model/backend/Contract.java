package com.example.nadav.javaproject5777.model.backend;

import android.net.Uri;

/**
 * Created by nadav on 12/10/2016.
 * Package: ${PACKAGE_NAME}
 */

public class Contract {
    /**
     * The authority for the contacts provider
     */
    public static final String AUTHORITY = "com.example.nadav.javaproject5777";
    /**
     * A content:// style uri to the authority for the contacts provider
     */
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);
    public static final String BROADCAST_URI = AUTHORITY+"/update";

    public static class User {
        public static final String USER_ID = "_ID";
        public static final String USER_NAME = "name";
        public static final String USER_PASSWORD = "password";
        /**
         * The content:// style URI for this table
         */
        public static final Uri USER_URI = Uri.withAppendedPath(AUTHORITY_URI, "user");
    }

    public static class Business {
        public static final String BUSINESS_ID = "_ID";
        public static final String BUSINESS_NAME = "name";
        public static final String BUSINESS_ADDRESS_COUNTRY = "country";
        public static final String BUSINESS_ADDRESS_CITY = "city";
        public static final String BUSINESS_ADDRESS_STREET = "street";
        public static final String BUSINESS_ADDRESS_ZIPCODE = "zipeCode";
        public static final String BUSINESS_PHONE = "phone";
        public static final String BUSINESS_EMAIL = "email";
        public static final String BUSINESS_LINK = "link";
        /**
         * The content:// style URI for this table
         */
        public static final Uri BUSINESS_URI = Uri.withAppendedPath(AUTHORITY_URI, "business");
    }

    public static class Activitie {
        public static final String ACTIVITIE_ID ="_ID";
        public static final String ACTIVITIE_ACT_TYPE = "actType";
        public static final String ACTIVITIE_COUNTRY_NAME = "countryName";
        public static final String ACTIVITIE_START_DATE = "startDate";
        public static final String ACTIVITIE_END_DATE = "endDate";
        public static final String ACTIVITIE_PRICE = "price";
        public static final String ACTIVITIE_DESCTIPTION = "description";
        public static final String ACTIVITIE_BUSSINES_ID = "businessId";
        /**
         * The content:// style URI for this table
         */
        public static final Uri ACTIVITIE_URI = Uri.withAppendedPath(AUTHORITY_URI, "activitie");
    }
}

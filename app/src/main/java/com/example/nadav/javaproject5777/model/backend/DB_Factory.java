package com.example.nadav.javaproject5777.model.backend;

import com.example.nadav.javaproject5777.model.datasource.ListDB_manager;

/**
 * Created by jerem on 07.12.16.
 */

public class DB_Factory {

    private static DB_manager instence = null;
    public static DB_manager getDB (){
         if(instence == null)
             instence = new ListDB_manager();
        return instence;
    }
}

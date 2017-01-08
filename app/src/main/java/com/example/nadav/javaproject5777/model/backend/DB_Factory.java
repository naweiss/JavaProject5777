package com.example.nadav.javaproject5777.model.backend;

import com.example.nadav.javaproject5777.model.datasource.ListDB_manager;
import com.example.nadav.javaproject5777.model.datasource.MySQL_DBmanager;

/**
 * Created by jerem on 07.12.16.
 */

public class DB_Factory {

    private static DB_manager instence = null;
    public static DB_manager getDB (){
         if(instence == null)
             instence = new MySQL_DBmanager();
        return instence;
    }
}

package com.example.nadav.javaproject5777.model.backend;

import com.example.nadav.javaproject5777.model.datasource.ListDB_manager;

/**
 * Created by jerem on 07.12.16.
 */

public class DB_Factory {

    static DB_Factory db= null;
    public  DB_Factory getDB (){
         return new ListDB_manager();
    }
}

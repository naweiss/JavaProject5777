package com.example.nadav.javaproject5777.controller;


import android.content.ContentValues;
import android.content.Intent;

import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nadav.javaproject5777.R;
import com.example.nadav.javaproject5777.model.backend.Contract;
import com.example.nadav.javaproject5777.model.datasource.Converter;
import com.example.nadav.javaproject5777.model.entities.Business;
import com.example.nadav.javaproject5777.model.entities.Address;

import java.net.URL;

/**
 * Created by jerem on 15.12.16.
 */

public class AddBusiness extends AppCompatActivity implements View.OnClickListener {
    private Button add;
    private EditText id;
    private EditText name;
    private EditText address;
    private EditText city;
    private EditText zipCode;
    private EditText country;
    private EditText phoneNumber;
    private EditText email;
    private EditText link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        findview();

    }
    public void findview(){
        id = (EditText)findViewById(R.id.idbusiness);
        name = (EditText)findViewById(R.id.name_business);
        address = (EditText)findViewById(R.id.address_business);
        city = (EditText)findViewById(R.id.city_business);
        country = (EditText)findViewById(R.id.business_country);
        zipCode = (EditText)findViewById(R.id.zipCode_business);
        phoneNumber = (EditText)findViewById(R.id.bsuiness_phone);
        email = (EditText)findViewById(R.id.email_business);
        link = (EditText)findViewById(R.id.LinkBusiness);

        add = (Button)findViewById(R.id.button_addBusiness);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        addBusiness();

    }

    public void addBusiness(){

        final Uri uri= Contract.Activitie.ACTIVITIE_URI;
        Business business=new Business();
        Address addr = new Address(
                this.country.getText().toString(),
                this.city.getText().toString(),
                this.address.getText().toString(),
                Integer.valueOf(this.zipCode.getText().toString())
        );
        try {
            business.setId(Integer.valueOf(this.id.getText().toString()));
            business.setName(this.name.getText().toString());
            business.setAddress(addr);
            business.setPhone(this.phoneNumber.getText().toString());
            business.setEmail(this.email.getText().toString());
            business.setLink(new URL(this.link.getText().toString()));
        }catch (Exception ex){}

        final ContentValues contentValues = Converter.businessToContentValues(business);
        new AsyncTask<Void,Void,Void>()
        {
            @Override
            protected Void doInBackground(Void... params) {
                getContentResolver().insert(uri,contentValues);
                Cursor users = getContentResolver().query(uri, null, null, null, null, null);
                //Toast.makeText(AddUser.this,new Integer(users.getCount()).toString(),Toast.LENGTH_SHORT).show();
                return null;
            }
        }.execute();
         Toast.makeText(this,"new business added",Toast.LENGTH_LONG).show();
         Intent mainScreen = new Intent(AddBusiness.this,MainActivity.class);
         startActivity(mainScreen);
    }

}

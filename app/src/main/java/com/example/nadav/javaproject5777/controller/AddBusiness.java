package com.example.nadav.javaproject5777.controller;


import android.content.ContentUris;
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
import java.util.regex.Pattern;

/**
 * Created by jerem on 15.12.16.
 */

public class AddBusiness extends AppCompatActivity implements View.OnClickListener {

    private Button add;
    private EditText nameEditText;
    private EditText streetEditText;
    private EditText cityEdiText;
    private EditText zipCodeEditText;
    private EditText countryEditText;
    private EditText phoneNumberEditText;
    private EditText emailEditText;
    private EditText linkEditText;
    String name;
    String street;
    String city;
    String zipCode;
    String country;
    String phone;
    String email;
    String link;
    private static final Pattern validPhone = Pattern.compile("^0[0-9]{8}$");
    private static final Pattern validMobile = Pattern.compile("^05[0-9]{8}$");
    private static final Pattern validEmail = Pattern.compile("^\\s*[a-zA-Z0-9.-_]+@[a-zA-Z0-9.-_]+\\.[a-zA-Z]{2,4}\\s*$");
    private static final Pattern validUrl = Pattern.compile("^http(s)?:\\/\\/(www\\.)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        findview();

    }
    public void findview(){
        nameEditText = (EditText)findViewById(R.id.name_business);
        streetEditText = (EditText)findViewById(R.id.address_business);
        cityEdiText = (EditText)findViewById(R.id.city_business);
        countryEditText = (EditText)findViewById(R.id.business_country);
        zipCodeEditText = (EditText)findViewById(R.id.zipCode_business);
        phoneNumberEditText = (EditText)findViewById(R.id.bsuiness_phone);
        emailEditText = (EditText)findViewById(R.id.email_business);
        linkEditText = (EditText)findViewById(R.id.LinkBusiness);

        //add = (Button)findViewById(R.id.button_addBusiness);
        //add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        name = nameEditText.getText().toString();
        street = streetEditText.getText().toString();
        city = cityEdiText.getText().toString();
        country = countryEditText.getText().toString();
        zipCode = zipCodeEditText.getText().toString();
        phone = phoneNumberEditText.getText().toString();
        email = emailEditText.getText().toString();
        link = linkEditText.getText().toString();
        if(!isValid())
            Toast.makeText(this,"The sign up failed",Toast.LENGTH_SHORT).show();
        else
            addBusiness();

    }

    public void addBusiness(){

        final Uri uri= Contract.Business.BUSINESS_URI;
        Business business=new Business();
        Address addr = new Address(
                this.countryEditText.getText().toString(),
                this.cityEdiText.getText().toString(),
                this.streetEditText.getText().toString(),
                Integer.valueOf(this.zipCodeEditText.getText().toString())
        );
        try {
            business.setName(this.nameEditText.getText().toString());
            business.setAddress(addr);
            business.setPhone(this.phoneNumberEditText.getText().toString());
            business.setEmail(this.emailEditText.getText().toString());
            business.setLink(new URL(this.linkEditText.getText().toString()));
        }catch (Exception ex){}

        final ContentValues contentValues = Converter.businessToContentValues(business);
        new AsyncTask<Void,Void,Boolean>()
        {
            @Override
            protected Boolean doInBackground(Void... params) {
                Uri ans = getContentResolver().insert(uri,contentValues);
                long id = ContentUris.parseId(ans);
                //Cursor users = getContentResolver().query(uri, null, null, null, null, null);
                //Toast.makeText(AddUser.this,new Integer(users.getCount()).toString(),Toast.LENGTH_SHORT).show();
                return (id != -1);
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                if(aBoolean) {
                    Toast.makeText(getBaseContext(), "new business added", Toast.LENGTH_LONG).show();
                    Intent mainScreen = new Intent(AddBusiness.this, MainActivity.class);
                    startActivity(mainScreen);
                }
                else
                    Toast.makeText(getBaseContext(), "failed", Toast.LENGTH_LONG).show();
            }
        }.execute();
    }

    private boolean isValid() {
        Boolean valid = true;
        if(name.isEmpty()) {
            nameEditText.setError("Please enter a username.");
            valid = false;
        }
        if (street.isEmpty()){
            streetEditText.setError("Please enter a street.");
            valid=false;
        }
        if(city.isEmpty()) {
            cityEdiText.setError("Please enter a city.");
            valid = false;
        }
        if(country.isEmpty()) {
            countryEditText.setError("Please enter a country.");
            valid = false;
        }
        if(zipCode.isEmpty()) {
            zipCodeEditText.setError("Please enter a zipCode.");
            valid = false;
        }

        if(phone.isEmpty()) {
            cityEdiText.setError("Please enter a phoneNumber.");
            valid = false;
        }
        else if(!validPhone.matcher(phone).matches() && !validMobile.matcher(phone).matches()){
            phoneNumberEditText.setError("Please enter a valid phone number.");
            valid =false;
        }
        if (email.isEmpty()) {
            emailEditText.setError("Please enter an email.");
            valid = false;
        }
        else if (!validEmail.matcher(email).matches()){
            emailEditText.setError("Please enter a valid email.");
            valid = false;
        }
        if(link.isEmpty()){
            linkEditText.setError("Please enter a link.");
            valid = false;
        }
        else if(!validUrl.matcher(link).matches()){
            linkEditText.setError("Please enter a valid link.");
            valid = false;
        }

        return valid;
    }

}

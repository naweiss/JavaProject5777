package com.example.nadav.javaproject5777.controller;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.Preference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nadav.javaproject5777.R;
import com.example.nadav.javaproject5777.model.backend.Contract;
import com.example.nadav.javaproject5777.model.datasource.Converter;
import com.example.nadav.javaproject5777.model.entities.Address;
import com.example.nadav.javaproject5777.model.entities.Business;

import java.net.URL;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //regex for check validity off inout
    private static final Pattern validPhone = Pattern.compile("^0[0-9]{8}$");
    private static final Pattern validMobile = Pattern.compile("^05[0-9]{8}$");
    private static final Pattern validEmail = Pattern.compile("^\\s*[a-zA-Z0-9.-_]+@[a-zA-Z0-9.-_]+\\.[a-zA-Z]{2,4}\\s*$");
    private static final Pattern validUrl = Pattern.compile("^http(s)?:\\/\\/(www\\.)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$");

    private final String pref = "MYPREF";
    private Button addActy;
    private Button addBusiness;

    private Button go;
    private Button logout;
    private EditText editTextId;
    //editText for new business

    private EditText nameEditText;
    private EditText streetEditText;
    private EditText cityEdiText;
    private EditText zipCodeEditText;
    private EditText countryEditText;
    private EditText phoneNumberEditText;
    private EditText emailEditText;
    private EditText linkEditText;
    String name, street , city, zipCode, country ,phone ,email ,link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addActy = (Button)findViewById(R.id.AddActivity_button);
        addBusiness = (Button)findViewById(R.id.AddBusiness_button);

        addActy.setOnClickListener(this);
        addBusiness.setOnClickListener(this);
        go = (Button)findViewById(R.id.buttonGO);
        logout = (Button)findViewById(R.id.logout);
        logout.setOnClickListener(this);
        editTextId = (EditText) findViewById(R.id.editTextID);
        go.setOnClickListener(this);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(),"segoe_print.ttf");
        TextView welcome = (TextView) findViewById(R.id.textViewWelcome);
        SharedPreferences preferences = getSharedPreferences(pref,MODE_PRIVATE);
        welcome.setText("Welcome "+ preferences.getString("NAME",null));
        welcome.setTypeface(myTypeface);

    }

    @Override
    public void onClick(View view) {
         if(view == addBusiness){
             //Intent activityScreen = new Intent(MainActivity.this,AddActivity.class);
             //startActivity(activityScreen);

             AlertDialog.Builder addBusinessDialog= new AlertDialog.Builder(MainActivity.this);
             View viewBusiness = getLayoutInflater().inflate(R.layout.activity_business,null);
             nameEditText = (EditText)viewBusiness.findViewById(R.id.name_business);
             streetEditText = (EditText)viewBusiness.findViewById(R.id.address_business);
             cityEdiText = (EditText)viewBusiness.findViewById(R.id.city_business);
             countryEditText = (EditText)viewBusiness.findViewById(R.id.business_country);
             zipCodeEditText = (EditText)viewBusiness.findViewById(R.id.zipCode_business);
             phoneNumberEditText = (EditText)viewBusiness.findViewById(R.id.bsuiness_phone);
             emailEditText = (EditText)viewBusiness.findViewById(R.id.email_business);
             linkEditText = (EditText) viewBusiness.findViewById(R.id.LinkBusiness);

             Button add = (Button) viewBusiness.findViewById(R.id.button_addBusiness);
             addBusinessDialog.setView(viewBusiness);
             final AlertDialog dialog = addBusinessDialog.create();
             dialog.show();
             add.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     name = nameEditText.getText().toString();
                     street = streetEditText.getText().toString();
                     city = cityEdiText.getText().toString();
                     country = countryEditText.getText().toString();
                     zipCode = zipCodeEditText.getText().toString();
                     phone = phoneNumberEditText.getText().toString();
                     email = emailEditText.getText().toString();
                     link = linkEditText.getText().toString();
                     if (!isValid())
                         Toast.makeText(MainActivity.this, "The sign up failed", Toast.LENGTH_SHORT).show();
                     else {
                         addBusiness();
                         dialog.dismiss();
                     }
                 }
             });



         }
        if(view == addActy){
            Intent activityScreen = new Intent(MainActivity.this,AddActivity.class);
            startActivity(activityScreen);
        }

        if(view == go){
            Intent activityScreen = new Intent(MainActivity.this,ListActivities.class);
            String str = editTextId.getText().toString();
            activityScreen.putExtra("key",str);
            startActivity(activityScreen);
        }
        if(view == logout){
            SharedPreferences preferences = getSharedPreferences(pref,MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            if(preferences.contains("REMEMBER_ME")){
                editor.remove("REMEMBER_ME");
                editor.commit();
            }
            Intent intent = new Intent(MainActivity.this,Login.class);
            startActivity(intent);
        }
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
        new AsyncTask<Void,Void,Void>()
        {
            @Override
            protected Void doInBackground(Void... params) {
                getContentResolver().insert(uri,contentValues);
                //Cursor users = getContentResolver().query(uri, null, null, null, null, null);
                //Toast.makeText(AddUser.this,new Integer(users.getCount()).toString(),Toast.LENGTH_SHORT).show();
                return null;
            }
        }.execute();
        Toast.makeText(this,"new business added",Toast.LENGTH_LONG).show();
        //Intent mainScreen = new Intent(AddBusiness.this,MainActivity.class);
        //startActivity(mainScreen);
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
            phoneNumberEditText.setError("Please enter a phoneNumber.");
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

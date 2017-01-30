package com.example.nadav.javaproject5777.controller;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.Preference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nadav.javaproject5777.R;
import com.example.nadav.javaproject5777.model.backend.Contract;
import com.example.nadav.javaproject5777.model.backend.UpdateService;
import com.example.nadav.javaproject5777.model.datasource.Converter;
import com.example.nadav.javaproject5777.model.entities.Activitie;
import com.example.nadav.javaproject5777.model.entities.ActivityType;
import com.example.nadav.javaproject5777.model.entities.Address;
import com.example.nadav.javaproject5777.model.entities.Business;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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

    //ressources for new activities
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Calendar date = Calendar.getInstance();
    private TextView start;
    private TextView finish;
    private TextView insertStartDate;
    private TextView insertFinishDate;
    private ImageButton startDate;
    private ImageButton finishDate;
    private Button addActivity;
    private EditText price;
    private EditText countryActivity;
    private EditText description;
    private Spinner typeOfactivities;
    private Spinner businessName;
    private Cursor bussineses = null;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, UpdateService.class);
        startService(intent);

        toolbar =(Toolbar)findViewById(R.id.logout1);
        setSupportActionBar(toolbar);

        addActy = (Button)findViewById(R.id.AddActivity_button);
        addBusiness = (Button)findViewById(R.id.AddBusiness_button);

        addActy.setOnClickListener(this);
        addBusiness.setOnClickListener(this);
        logout = (Button)findViewById(R.id.logout);
        logout.setOnClickListener(this);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(),"segoe_print.ttf");
        TextView welcome = (TextView) findViewById(R.id.textViewWelcome);
        SharedPreferences preferences = getSharedPreferences(pref,MODE_PRIVATE);
        welcome.setText("Welcome "+ preferences.getString("NAME",null));
        welcome.setTypeface(myTypeface);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
         if(view == addBusiness){

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
            //Intent activityScreen = new Intent(MainActivity.this,AddActivity.class);
            //startActivity(activityScreen);
            AlertDialog.Builder addActivityDialog= new AlertDialog.Builder(MainActivity.this);
            View viewActivity = getLayoutInflater().inflate(R.layout.activity_activities,null);
            startDate = (ImageButton) viewActivity.findViewById(R.id.imageButton_start);
            insertStartDate = (TextView) viewActivity.findViewById(R.id.txtDateStart);
            insertFinishDate = (TextView) viewActivity.findViewById(R.id.txtDateFinish);
            finishDate = (ImageButton)viewActivity.findViewById(R.id.imageButton_finish);
            start = (TextView)viewActivity.findViewById(R.id.textViewStart);
            finish = (TextView)viewActivity.findViewById(R.id.textViewFinish);
            addActivity = (Button)viewActivity.findViewById(R.id.button_addActivity);
            price = (EditText) viewActivity.findViewById(R.id.priceActivity);
            countryActivity = (EditText)viewActivity.findViewById(R.id.country_activity);
            description = (EditText)viewActivity.findViewById(R.id.description);
            businessName = (Spinner)viewActivity.findViewById(R.id.businessId_activity);
            typeOfactivities = (Spinner) viewActivity.findViewById(R.id.spinner_typeActivities);

            try {
                AsyncTask task = new bussinesIds().execute();
                ArrayAdapter<String> adapter  = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, (List<String>)task.get());
                businessName.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
                finish();
            }
            startDate.setOnClickListener(this);
            finishDate.setOnClickListener(this);
            addActivity.setOnClickListener(this);

            addActivityDialog.setView(viewActivity);
            final AlertDialog dialog1 = addActivityDialog.create();
            dialog1.show();

            startDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateDateStart();
                }
            });
            finishDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateDatefinish();
                }
            });
            addActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addActivity();
                    dialog1.dismiss();
                }
            });

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
    private void updateTextStart(){
        insertStartDate.setText(dateFormat.format(date.getTime()));
        Toast.makeText(this,start.getText().toString(),Toast.LENGTH_LONG).show();
    }
    private void updateTextFinish(){
        insertFinishDate.setText(dateFormat.format(date.getTime()));
    }
    private void updateDateStart(){
        new DatePickerDialog(this,c,date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DAY_OF_MONTH)).show();
    }
    private void updateDatefinish(){
        new DatePickerDialog(this,d,date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DAY_OF_MONTH)).show();
    }
    DatePickerDialog.OnDateSetListener c =new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            date.set(Calendar.YEAR,year);
            date.set(Calendar.MONTH,month);
            date.set(Calendar.DAY_OF_MONTH,day);
            updateTextStart();
        }
    };
    DatePickerDialog.OnDateSetListener d =new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            date.set(Calendar.YEAR,year);
            date.set(Calendar.MONTH,month);
            date.set(Calendar.DAY_OF_MONTH,day);
            updateTextFinish();
        }
    };
    public void addActivity() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        final Uri uri= Contract.Activitie.ACTIVITIE_URI;
        Activitie act=new Activitie();
        try {

            act.setActType(ActivityType.valueOf(this.typeOfactivities.getSelectedItem().toString().replace(' ', '_').toUpperCase()));
            act.setPrice(Double.parseDouble(this.price.getText().toString()));
            int id = -1;
            bussineses.moveToFirst();
            do{
                if (this.businessName.getSelectedItem().toString().equals(bussineses.getString(1)))
                    id = Integer.valueOf(bussineses.getString(0));
            }while (bussineses.moveToNext());
            if(id == -1)
                throw  new Exception("Invalid Bussines");
            act.setBusinessId(id);
            act.setCountryName(this.countryActivity.getText().toString());
            act.setDescription(this.description.getText().toString());
            act.setStartDate(df.parse(this.insertStartDate.getText().toString()));

            act.setEndDate(df.parse(this.insertFinishDate.getText().toString()));
        }catch (Exception ex){}

        final ContentValues contentValues = Converter.activitieToContentValues(act);
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

        Toast.makeText(this,"new activity added",Toast.LENGTH_LONG).show();


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
    private class bussinesIds extends AsyncTask<Void,Void,List<String>>
    {
        @Override
        protected List<String> doInBackground(Void... params) {
            bussineses = getContentResolver().query(Contract.Business.BUSINESS_URI, null, null, null, null);
            if(bussineses == null)
                return null;
            List<String> names = new ArrayList<>();
            while (bussineses.moveToNext()) {
                names.add(bussineses.getString(1));
            }
            bussineses.moveToFirst();
            return names;
        }
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

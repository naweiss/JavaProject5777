package com.example.nadav.javaproject5777.controller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutionException;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nadav.javaproject5777.R;
import com.example.nadav.javaproject5777.model.backend.Contract;
import com.example.nadav.javaproject5777.model.datasource.Converter;
import com.example.nadav.javaproject5777.model.entities.Activitie;
import com.example.nadav.javaproject5777.model.entities.ActivityType;
import com.example.nadav.javaproject5777.model.entities.User;

/**
 * Created by jerem on 15.12.16.
 */

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Calendar date = Calendar.getInstance();
    private TextView start;
    private TextView finish;
    private ImageButton startDate;
    private ImageButton finishDate;
    private Button add;
    private EditText price;
    private EditText country;
    private EditText description;
    private Spinner typeOfactivities;
    private Spinner businessName;
    private Cursor bussineses = null;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        startDate = (ImageButton) findViewById(R.id.imageButton_start);
        finishDate = (ImageButton)findViewById(R.id.imageButton_finish);
        start = (TextView)findViewById(R.id.textViewStart);
        finish = (TextView)findViewById(R.id.textViewFinish);
        add = (Button)findViewById(R.id.button_add);
        price = (EditText) findViewById(R.id.priceActivity);
        country = (EditText)findViewById(R.id.country_activity);
        description = (EditText)findViewById(R.id.description);
        businessName = (Spinner)findViewById(R.id.businessId_activity);
        typeOfactivities = (Spinner) findViewById(R.id.spinner_typeActivities);
        try {
            AsyncTask task = new bussinesIds().execute();
            ArrayAdapter<String> adapter  = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, (List<String>)task.get());
            businessName.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
        //typeOfactivities.setAdapter(new ArrayAdapter<ActivityType>(this,android.R.layout.simple_spinner_item,ActivityType.values()));
        startDate.setOnClickListener(this);
        finishDate.setOnClickListener(this);
        add.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        if(view == startDate)
            updateDateStart();
        if(view == finishDate)
            updateDatefinish();
        if(view == add)
            addActivity();

    }

    private void updateTextStart(){
        start.setText(dateFormat.format(date.getTime()));
        Toast.makeText(this,start.getText().toString(),Toast.LENGTH_LONG).show();
    }
    private void updateTextFinish(){
        finish.setText(dateFormat.format(date.getTime()));
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
//            ActivityType a = (ActivityType) typeOfactivities.getSelectedItem();
//            act.setActType(a);

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
            act.setCountryName(this.country.getText().toString());
            act.setDescription(this.description.getText().toString());
            act.setStartDate(df.parse(this.start.getText().toString()));

            act.setEndDate(df.parse(this.finish.getText().toString()));
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
        Intent mainScreen = new Intent(AddActivity.this,MainActivity.class);
        startActivity(mainScreen);

    }


}

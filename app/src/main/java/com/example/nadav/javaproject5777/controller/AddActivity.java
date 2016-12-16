package com.example.nadav.javaproject5777.controller;


import java.text.DateFormat;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nadav.javaproject5777.R;

/**
 * Created by jerem on 15.12.16.
 */

public class AddActivity extends AppCompatActivity  {
    DateFormat dateFormat = DateFormat.getDateInstance();
    Calendar date = Calendar.getInstance();
    private TextView start;
    private TextView finish;
    private ImageButton startDate;
    private ImageButton finishDate;
    private Button add;



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        startDate = (ImageButton) findViewById(R.id.imageButton_start);
        finishDate = (ImageButton)findViewById(R.id.imageButton_finish);
        start = (TextView)findViewById(R.id.textViewStart);
        finish = (TextView)findViewById(R.id.textViewFinish);
        add = (Button)findViewById(R.id.add);

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDateStart();
            }
        });
        finishDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDatefinish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addActivity();
            }
        });

    }


    private void updateTextStart(){
        start.setText(dateFormat.format(date.getTime()));
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

    public void addActivity()
    {
        Toast.makeText(this,"new activity added",Toast.LENGTH_LONG).show();
        Intent mainScreen = new Intent(AddActivity.this,MainActivity.class);
        startActivity(mainScreen);

    }


}

package com.example.nadav.javaproject5777.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nadav.javaproject5777.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button addActy;
    private Button addBusiness;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addActy = (Button)findViewById(R.id.AddActivity_button);
        addBusiness = (Button)findViewById(R.id.AddBusiness);
        addActy.setOnClickListener(this);
        addBusiness.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
         if(view == addActy){
             Intent activityScreen = new Intent(MainActivity.this,AddActivity.class);
             startActivity(activityScreen);
         }
        if(view == addBusiness){
            Intent activityScreen = new Intent(MainActivity.this,AddBusiness.class);
            startActivity(activityScreen);
        }


    }
}

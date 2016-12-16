package com.example.nadav.javaproject5777.controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nadav.javaproject5777.R;

/**
 * Created by jerem on 15.12.16.
 */

public class AddBusiness extends AppCompatActivity implements View.OnClickListener {
    private Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        add = (Button)findViewById(R.id.button_addBusiness);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        addBusiness();

    }

    public void addBusiness(){
        Toast.makeText(this,"new business added",Toast.LENGTH_LONG).show();
        Intent mainScreen = new Intent(AddBusiness.this,MainActivity.class);
        startActivity(mainScreen);
    }
}

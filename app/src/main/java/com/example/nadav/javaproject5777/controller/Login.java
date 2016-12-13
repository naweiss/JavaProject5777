package com.example.nadav.javaproject5777.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nadav.javaproject5777.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText userName = (EditText)findViewById(R.id.username);
        final EditText password = (EditText)findViewById(R.id.password);
        Button btnLogin = (Button) findViewById(R.id.connect);
        CheckBox checkBox = (CheckBox) findViewById(R.id.remember);


        SharedPreferences preferences=getSharedPreferences("MYPREF",MODE_PRIVATE);
        if(preferences.contains("NAME")){
            userName.setText(preferences.getString("NAME",null));
        }
        if(preferences.contains("PASSWORD")){
            password.setText(preferences.getString("PASSWORD",null));
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                SharedPreferences preferences=getSharedPreferences("MYPREF",MODE_PRIVATE);
                String user = userName.getText().toString();
                String pass = password.getText().toString();

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("NAME",user);
                editor.putString("PASSWORD",pass);
                editor.commit();

                    Toast.makeText(Login.this,"save name and password",Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex){
                    Toast.makeText(Login.this,"failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

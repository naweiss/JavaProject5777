package com.example.nadav.javaproject5777.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompatSideChannelService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nadav.javaproject5777.R;
import com.example.nadav.javaproject5777.model.backend.Contract;

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
                try {
                    boolean existUser = false;
                    SharedPreferences preferences = getSharedPreferences("MYPREF", MODE_PRIVATE);
                    final String user = userName.getText().toString();
                    final String pass = password.getText().toString();
                    AsyncTask task=new Task().execute(user, pass);
                    Object a = task.get();
                    existUser = (Boolean)a ;
                    if (existUser) {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("NAME", user);
                        editor.putString("PASSWORD", pass);
                        editor.commit();
                        Toast.makeText(Login.this, "save name and password", Toast.LENGTH_SHORT).show();

                        Intent registerScreen = new Intent(Login.this, MainActivity.class);
                        startActivity(registerScreen);
                    } else {
                        Toast.makeText(Login.this, "Unknown user", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    Toast.makeText(Login.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnRegister = (Button)findViewById(R.id.newUser);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerScreen = new Intent(Login.this,AddUser.class);
                startActivity(registerScreen);
            }
        });

    }
    private class Task extends AsyncTask<String,Void,Boolean>
    {
        @Override
        protected Boolean doInBackground(String... params) {
            String user = params[0];
            String pass = params[1];
            Cursor users = getContentResolver().query(Contract.User.USER_URI, null, null, null, null);
            boolean flag = false;
            while (users.moveToNext()) {
                if (user.equals(users.getString(users.getColumnIndex(Contract.User.USER_NAME))) &&
                        pass.equals(users.getString(users.getColumnIndex(Contract.User.USER_PASSWORD)))) {
                    flag = true;
                    break;
                }
            }
            return flag;
        }
    }
}

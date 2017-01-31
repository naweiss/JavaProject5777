package com.example.nadav.javaproject5777.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
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

    private final String pref = "MYPREF";
    private SharedPreferences preferences;
    private EditText userName;
    private EditText password;
    private Button btnLogin;
    private CheckBox checkBox;

    /**
     * Call the function to initialize the views and connect the layout to the activity.
     * Also load in the edittext the user data from the shared preferences (if any),
     * and in case the "remember me" button has been selected, then you go directly to the mainActivity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();

        final String user = userName.getText().toString();
        final String pass = password.getText().toString();
        final boolean isChecked = checkBox.isChecked();

        preferences = getSharedPreferences(pref,MODE_PRIVATE);
        if(preferences.contains("REMEMBER_ME")){
            Intent registerScreen = new Intent(Login.this, MainActivity.class);
            startActivity(registerScreen);
        }
        if(preferences.contains("NAME")){
            userName.setText(preferences.getString("NAME",null));
        }
        if(preferences.contains("PASSWORD")){
            password.setText(preferences.getString("PASSWORD",null));
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            /**
             * Function that when we click on the login button it checks if the user exists. If so, update the username
             * and password in the shared preference and switch to mainActivity otherwise a toast error appears.
             * @param view
             */
            @Override
            public void onClick(View view) {
                try {
                    boolean existUser = false;
                    AsyncTask task=new isUserExist().execute(user, pass);
                    existUser = (Boolean)task.get();
                    if (existUser) {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("NAME", user);
                        editor.putString("PASSWORD", pass);
                        if(isChecked)
                            editor.putString("REMEMBER_ME", "True");
                        editor.commit();
                        Toast.makeText(Login.this, "save name and password", Toast.LENGTH_SHORT).show();

                        Intent registerScreen = new Intent(Login.this, MainActivity.class);
                        startActivity(registerScreen);
                    }
                    else {
                        Toast.makeText(Login.this, "Unknown user", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    Toast.makeText(Login.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Button btnRegister = (Button)findViewById(R.id.newUser);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            /**
             * function when we click the button sign up we go to the Adduser activity
             * @param view
             */
            @Override
            public void onClick(View view) {

                Intent registerScreen = new Intent(Login.this,AddUser.class);
                startActivity(registerScreen);
            }
        });

    }

    /**
     * Initializes views
     */
    public void findView(){
         userName = (EditText)findViewById(R.id.username);
         password = (EditText)findViewById(R.id.password);
         btnLogin = (Button) findViewById(R.id.connect);
         checkBox = (CheckBox) findViewById(R.id.remember);

    }

    /**
     * function to check if user exists
     */
    private class isUserExist extends AsyncTask<String,Void,Boolean>
    {
        @Override
        protected Boolean doInBackground(String... params) {
            String user = params[0];
            String pass = params[1];
            Cursor users = getContentResolver().query(Contract.User.USER_URI, null, null, params, null);
            users.moveToNext();
            boolean flag = "true".equals(users.getString(users.getColumnIndex("exist")));
            return flag;
        }
    }
}

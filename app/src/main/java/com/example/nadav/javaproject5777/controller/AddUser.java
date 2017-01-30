package com.example.nadav.javaproject5777.controller;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nadav.javaproject5777.R;
import com.example.nadav.javaproject5777.model.backend.Contract;
import com.example.nadav.javaproject5777.model.datasource.Converter;
import com.example.nadav.javaproject5777.model.entities.User;

/**
 * Created by jerem on 15.12.16.
 */

public class AddUser extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);
        findViews();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
    private String username;
    private String checkPass;
    private String confirmPass;
    private EditText editTextAddUser;
    private EditText editTextAddPassword;
    private EditText editTextConfirmPassword;
    private Button buttonAddUser;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-12-15 12:51:45 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        editTextAddUser = (EditText)findViewById( R.id.editText_addUser );
        editTextAddPassword = (EditText)findViewById( R.id.editText_addPassword );
        editTextConfirmPassword = (EditText)findViewById( R.id.editText_confirmPassword );
        buttonAddUser = (Button)findViewById( R.id.button_addUser );

        buttonAddUser.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2016-12-15 12:51:45 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == buttonAddUser ) {
            username = editTextAddUser.getText().toString();
            checkPass = editTextAddPassword.getText().toString();
            confirmPass =editTextConfirmPassword.getText().toString();

            if(!isValid())
                Toast.makeText(this,"The sign up failed",Toast.LENGTH_SHORT).show();

            else {
                addUser();
                // Handle clicks for buttonAddUser
            }

        }
    }
    public void addUser(){

        final Uri uri=Contract.User.USER_URI;
        User user=new User();
        user.setName(this.editTextAddUser.getText().toString());
        user.setPassword(this.editTextAddPassword.getText().toString());


        final ContentValues contentValues = Converter.userToContentValues(user);
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
        Toast.makeText(this,"new user added",Toast.LENGTH_LONG).show();
        Intent mainScreen = new Intent(AddUser.this,MainActivity.class);
        startActivity(mainScreen);
    }
    // validating password with retype password
    private boolean isValid() {
         Boolean valid = true;
        if (username.isEmpty()) {
            editTextAddUser.setError("Please enter a username");
            valid = false;
        }
        if (checkPass.isEmpty() || checkPass.length() < 6){
            editTextAddPassword.setError("Please enter a valid password");
            valid=false;
        }
        else if(!checkPass.equals(confirmPass)) {
            editTextConfirmPassword.setError("Confirm password did not match");
            valid = false;
        }
        return valid;
    }
}

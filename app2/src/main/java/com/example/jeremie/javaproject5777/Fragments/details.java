package com.example.jeremie.javaproject5777.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jeremie.javaproject5777.Business_details;
import com.example.jeremie.javaproject5777.R;
import com.example.jeremie.javaproject5777.entities.Business;

import static com.example.jeremie.javaproject5777.AsyncTaskUpdate.db_manager;

/**
 * Created by jerem on 24.01.17.
 */

public class details extends Fragment {

     Business business = new Business();
    TextView address;
    TextView Country;
    TextView email;
    TextView link;
    TextView phone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.content_details, container, false);

        phone = (TextView) rootView.findViewById(R.id.textviewContentPhone);
        Country = (TextView) rootView.findViewById(R.id.textviewContentCountry);
        address = (TextView) rootView.findViewById(R.id.textviewContentAddress);
        email = (TextView) rootView.findViewById(R.id.tvEmail);
        Country = (TextView) rootView.findViewById(R.id.tvLink);

        String id = getArguments().getString("data");
        //business = db_manager.getBusiness(Integer.valueOf(id));
        //phone.setText("12345666");
         address.setText(id);
        //phone.setText(business.getPhone());
        //address.setText(business.getAddress().getStreet().toString()+","+business.getAddress().getCity().toString());
        //Country.setText(business.getAddress().getZipCode()+","+business.getAddress().getCountry().toString());
        //email.setText(business.getEmail());
        //link.setText(business.getLink().toString());
        return rootView;
    }
}

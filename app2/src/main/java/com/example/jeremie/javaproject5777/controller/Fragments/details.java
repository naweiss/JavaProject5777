package com.example.jeremie.javaproject5777.controller.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jeremie.javaproject5777.model.datasource.ListDB_manager;
import com.example.jeremie.javaproject5777.R;
import com.example.jeremie.javaproject5777.model.entities.Business;

/**
 * Created by jerem on 24.01.17.
 * Package: ${PACKAGE_NAME}
 */

public class details extends Fragment {

    private ListDB_manager db_manager = ListDB_manager.getInstance();

    Business business = new Business();
    TextView address;
    TextView email;
    TextView link;
    TextView phone;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.content_details, container, false);

        phone = (TextView) rootView.findViewById(R.id.textviewContentPhone);
        address = (TextView) rootView.findViewById(R.id.textviewContentAddress);
        email = (TextView) rootView.findViewById(R.id.tvEmail);
        link = (TextView) rootView.findViewById(R.id.tvLink);

        int id = getActivity().getIntent().getIntExtra("ID",-1);
        business = db_manager.getBusiness(id);
        phone.setText(business.getPhone().substring(0,3)+"-"+business.getPhone().substring(3,6)+"-"+business.getPhone().substring(6));
        address.setText(business.getAddress().toString());
        email.setText(business.getEmail());
        link.setText(business.getLink().toString());

        return rootView;
    }
}

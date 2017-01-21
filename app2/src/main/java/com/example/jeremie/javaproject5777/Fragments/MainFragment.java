package com.example.jeremie.javaproject5777.Fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jeremie.javaproject5777.Address;
import com.example.jeremie.javaproject5777.Business;
import com.example.jeremie.javaproject5777.RecyclerViewAdapter;
import com.example.jeremie.javaproject5777.R;
import com.example.jeremie.javaproject5777.RecyclerViewAdapter;

import java.net.URL;

public class MainFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        try {
            // specify an adapter (see also next example)
            Business[] myDataset = new Business[]
                    {new Business(new Address("Israel", "Hifa", "Arlozorov", 031231), "avi_levi@gmail.com", new URL("http://www.google.com"), "avi levi", "056-712-3123")
                            , new Business(new Address("Israel", "Jerusalem", "Arlozorov", 031231), "avi@gmail.com", new URL("http://stackoverflow.com"), "avi cohen", "056-745-6456")
                            , new Business(new Address("Israel", "Hifa", "Hamacabim", 013213), "david123@jct.ac.il", new URL("http://www.123.456.com"), "david malcha", "050-567-9789")
                            ,new Business(new Address("Israel", "Hifa", "Arlozorov", 031231), "avi_levi@gmail.com", new URL("http://www.google.com"), "avi levi", "056-712-3123")
                            ,new Business(new Address("Israel", "Hifa", "Arlozorov", 031231), "avi_levi@gmail.com", new URL("http://www.google.com"), "avi levi", "056-712-3123")
                            ,new Business(new Address("Israel", "Hifa", "Arlozorov", 031231), "avi_levi@gmail.com", new URL("http://www.google.com"), "avi levi", "056-712-3123")};
            mAdapter = new RecyclerViewAdapter(myDataset,view.getContext());
            mRecyclerView.setAdapter(mAdapter);
        }catch (Exception e){}
        return view;
    }
}

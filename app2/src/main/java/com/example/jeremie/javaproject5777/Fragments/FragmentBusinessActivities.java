package com.example.jeremie.javaproject5777.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jeremie.javaproject5777.ListDB_manager;
import com.example.jeremie.javaproject5777.R;
import com.example.jeremie.javaproject5777.RecyclerViewAdapterActivities;

/**
 * Created by jerem on 28.01.17.
 */

public class FragmentBusinessActivities extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ListDB_manager db_manager = ListDB_manager.getInstance();

    public static FragmentBusinessActivities newInstance(int type) {
        FragmentBusinessActivities frag = new FragmentBusinessActivities();

        Bundle args = new Bundle();
        args.putInt("ID", type);
        frag.setArguments(args);
        return frag;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        try {
            mAdapter = new RecyclerViewAdapterActivities(R.layout.cardview_activities,db_manager.getAllActivity());
            mRecyclerView.setAdapter(mAdapter);
        }catch (Exception e){}
    }

}


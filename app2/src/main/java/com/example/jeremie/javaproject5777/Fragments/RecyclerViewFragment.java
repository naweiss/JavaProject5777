package com.example.jeremie.javaproject5777.Fragments;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.MenuPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jeremie.javaproject5777.AsyncTaskUpdate;
import com.example.jeremie.javaproject5777.AsyncTaskUpdateActivities;
import com.example.jeremie.javaproject5777.Business_details;
import com.example.jeremie.javaproject5777.Contract;
import com.example.jeremie.javaproject5777.Converter;
import com.example.jeremie.javaproject5777.ListDB_manager;
import com.example.jeremie.javaproject5777.MenuActivity;
import com.example.jeremie.javaproject5777.RecyclerViewAdapterActivities;
import com.example.jeremie.javaproject5777.UpdateableRecyclerViewAdapter;
import com.example.jeremie.javaproject5777.entities.Address;
import com.example.jeremie.javaproject5777.entities.Business;
import com.example.jeremie.javaproject5777.R;
import com.example.jeremie.javaproject5777.RecyclerViewAdapter;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jerem on 19.01.17.
 */

public class RecyclerViewFragment extends Fragment  {

    private RecyclerView mRecyclerView;
    private UpdateableRecyclerViewAdapter mAdapter;
    private ListDB_manager db_manager = ListDB_manager.newInstance();

    public static RecyclerViewFragment newInstance(int type) {
        RecyclerViewFragment f = new RecyclerViewFragment();

        Bundle args = new Bundle();
        args.putInt("index", type);
        f.setArguments(args);
        return f;
        //return new RecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        int index = args.getInt("index", -1);
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        //permet un affichage sous forme liste verticale
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        try {

            //penser à passer notre Adapter (ici : TestRecyclerViewAdapter) à un RecyclerViewMaterialAdapter
            if(index == 0){
                mAdapter = new RecyclerViewAdapter(db_manager.getAllBusinesses(), getActivity().getBaseContext());}
            else if(index==1){
                mAdapter = new RecyclerViewAdapterActivities(db_manager.getAllActivity(), getActivity().getBaseContext());
            }
            db_manager.setDataSetChangedListener(new ListDB_manager.NotifyDataSetChangedListener() {
                @Override
                public void onDataSetChanged() {
                    mAdapter.Update(db_manager);
                    mRecyclerView.setAdapter(new RecyclerViewMaterialAdapter(mAdapter));
                    mAdapter.notifyDataSetChanged();
                }
            });
            //mRecyclerView.setAdapter(new RecyclerViewMaterialAdapter(mAdapter));
            mRecyclerView.setAdapter(new RecyclerViewMaterialAdapter(mAdapter));

            //notifier le MaterialViewPager qu'on va utiliser une RecyclerView
            MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);

        }catch (Exception e){}
    }


}
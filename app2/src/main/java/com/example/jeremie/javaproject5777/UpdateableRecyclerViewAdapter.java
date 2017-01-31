package com.example.jeremie.javaproject5777;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.jeremie.javaproject5777.entities.Business;


import java.util.List;

/**
 * Created by nadav on 1/30/2017.
 * Package: com.example.jeremie.javaproject5777
 */
interface Updatable{
    public void Update(ListDB_manager manager);
}
public abstract class UpdateableRecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements Updatable{
}



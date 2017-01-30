package com.example.jeremie.javaproject5777;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v4.util.Pair;

import com.example.jeremie.javaproject5777.entities.Activitie;
import com.example.jeremie.javaproject5777.entities.Business;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.jeremie.javaproject5777.R.id.backgroundImageView;
import static com.example.jeremie.javaproject5777.R.id.imageView;

/**
 * Created by nadav on 12/25/2016.
 * Package: com.example.nadav.testproject
 */

public class RecyclerViewAdapterActivities extends UpdateableRecyclerViewAdapter<RecyclerViewAdapterActivities.ViewHolder>  {
    private List<Activitie> mDataset;
    private Context context;

    @Override
    public void Update(ListDB_manager manager) {
        List<Activitie> lst = manager.getAllActivity();
        mDataset.clear();
        mDataset.addAll(lst);
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title;
        public ImageView imageView;
        public String activityType;
        public int Id = 0;
        public ViewHolder(View v) {
            super(v);
            title = (TextView)v.findViewById(R.id.descriptionActivity);
            imageView = (ImageView) v.findViewById(R.id.imageActivity);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapterActivities(List<Activitie>myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapterActivities.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_activities, parent, false);
        // set the view's size, margins, paddings and layout parameters
        //...

        final ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.Id = mDataset.get(position).getId();
        holder.title.setText(mDataset.get(position).getDescription());
        holder.activityType =mDataset.get(position).getActType().toString();
        if(holder.activityType == "CRUISE")
           holder.imageView.setImageResource(R.drawable.cruise);
        else if(holder.activityType == "ENTERTAINMENT")
            holder.imageView.setImageResource(R.drawable.entertainment);
        else
            holder.imageView.setImageResource(R.drawable.hotel);



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v4.util.Pair;

import com.example.jeremie.javaproject5777.entities.Activitie;
import com.example.jeremie.javaproject5777.entities.ActivityType;
import com.example.jeremie.javaproject5777.entities.Business;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.example.jeremie.javaproject5777.R.id.backgroundImageView;
import static com.example.jeremie.javaproject5777.R.id.imageView;

/**
 * Created by nadav on 12/25/2016.
 * Package: com.example.nadav.testproject
 */

public class RecyclerViewAdapterActivities extends FilterAdapter<Activitie> {

    public RecyclerViewAdapterActivities(int resource, List<Activitie> objects) {
        super(resource, objects);
    }
    
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        View v = holder.itemView;
        final TextView title = (TextView)v.findViewById(R.id.descriptionActivity);
        final ImageView imageView = (ImageView) v.findViewById(R.id.imageActivity);
        final TextView price = (TextView) v.findViewById(R.id.priceActivities);
        final ImageButton chieldView = (ImageButton) v.findViewById(R.id.buttonChield);
        final TextView date = (TextView) v.findViewById(R.id.txtDate);
        final TextView country = (TextView) v.findViewById(R.id.txtcountry);
        final LinearLayout linearLayout1 = (LinearLayout) v.findViewById(R.id.visibleCardview);
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        title.setText(get(position).getDescription());
        price.setText(Double.toString(get(position).getPrice()));
        String activityType =get(position).getActType().toString();
        country.setText(get(position).getCountryName());
        date.setText(df.format(get(position).getStartDate())+" - "+df.format(get(position).getEndDate()));

        if(activityType == "CRUISE")
           imageView.setImageResource(R.drawable.cruise);
        else if(activityType == "ENTERTAINMENT")
            imageView.setImageResource(R.drawable.entertainment);
        else if(activityType == "SKI")
            imageView.setImageResource(R.drawable.ski);
        else if(activityType == "TOURIST")
            imageView.setImageResource(R.drawable.tourist);
        else
            imageView.setImageResource(R.drawable.hotel);

        chieldView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayout1.getVisibility() == View.GONE) {
                    linearLayout1.setVisibility(View.VISIBLE);
                    chieldView.setImageResource(R.drawable.ic_expand_less_black_24dp);
                } else {
                    linearLayout1.setVisibility(View.GONE);
                    chieldView.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });
    }

}
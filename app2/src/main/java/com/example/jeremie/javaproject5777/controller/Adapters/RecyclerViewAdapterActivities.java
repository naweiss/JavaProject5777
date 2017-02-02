package com.example.jeremie.javaproject5777.controller.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jeremie.javaproject5777.R;
import com.example.jeremie.javaproject5777.model.entities.Activitie;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by nadav on 12/25/2016.
 * Package: com.example.nadav.testproject
 */

public class RecyclerViewAdapterActivities extends FilterAdapter<Activitie> {

    //the constants value of the header view
    private static final int TYPE_PLACEHOLDER = Integer.MIN_VALUE;
    //the size taken by the header
    private int mPlaceholderSize = 1;

    public RecyclerViewAdapterActivities(int resource, List<Activitie> objects) {
        super(resource, objects);
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mPlaceholderSize)
            return TYPE_PLACEHOLDER;
        else
            return super.getItemViewType(position - mPlaceholderSize); //call getItemViewType on the adapter, less mPlaceholderSize
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + mPlaceholderSize;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_PLACEHOLDER: {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(com.github.florent37.materialviewpager.R.layout.material_view_pager_placeholder, parent, false);
                return new ViewHolder(view);
            }
            default:
                return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public Activitie get(int position) {
        if (position < mPlaceholderSize)
            return new Activitie();
        else
            return super.get(position- mPlaceholderSize);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_PLACEHOLDER)
            return;
        View v = holder.itemView;
        final TextView title = (TextView)v.findViewById(R.id.descriptionActivity);
        final ImageView imageView = (ImageView) v.findViewById(R.id.imageActivity);
        final TextView price = (TextView) v.findViewById(R.id.priceActivities);
        final ImageButton chieldView = (ImageButton) v.findViewById(R.id.buttonChield);
        final TextView date = (TextView) v.findViewById(R.id.txtDate);
        final TextView country = (TextView) v.findViewById(R.id.txtcountry);
        final LinearLayout linearLayout1 = (LinearLayout) v.findViewById(R.id.visibleCardview);
        final DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        title.setText(get(position).getDescription());
        price.setText(Double.toString(get(position).getPrice()));
        String activityType =get(position).getActType().toString();
        country.setText(get(position).getCountryName());
        date.setText(df.format(get(position).getStartDate())+" - "+df.format(get(position).getEndDate()));

        if(activityType.equals("CRUISE"))
           imageView.setImageResource(R.drawable.cruise);
        else if(activityType.equals("ENTERTAINMENT"))
            imageView.setImageResource(R.drawable.entertainment);
        else if(activityType.equals("SKI"))
            imageView.setImageResource(R.drawable.ski);
        else if(activityType.equals("TOURIST"))
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
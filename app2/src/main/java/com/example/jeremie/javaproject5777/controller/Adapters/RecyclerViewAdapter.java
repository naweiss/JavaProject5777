package com.example.jeremie.javaproject5777.controller.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.jeremie.javaproject5777.model.backend.ExtractFavicon;
import com.example.jeremie.javaproject5777.R;
import com.example.jeremie.javaproject5777.controller.Business_details;
import com.example.jeremie.javaproject5777.model.entities.Business;

import java.util.List;


/**
 * Created by nadav on 12/25/2016.
 * Package: com.example.nadav.testproject
 */
public class RecyclerViewAdapter extends FilterAdapter<Business> {

    //the constants value of the header view
    private static final int TYPE_PLACEHOLDER = Integer.MIN_VALUE;
    //the size taken by the header
    private int mPlaceholderSize = 1;

    private Context context;
    public RecyclerViewAdapter(int resource,Context context, List<Business> objects) {
        super(resource, objects);
        this.context = context;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public int getItemViewType(int position) {
        if (position < mPlaceholderSize)
            return TYPE_PLACEHOLDER;
        else
            return super.getItemViewType(position - mPlaceholderSize); //call getItemViewType on the adapter, less mPlaceholderSize
    }

    /**
     * Return the number of records and the Placeholder
     */
    @Override
    public int getItemCount() {
        return super.getItemCount() + mPlaceholderSize;
    }

    /**
     * Create view for the records
     * @param parent - The Parent of the view
     * @param viewType - The type of the view
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_PLACEHOLDER: {
                // Place holder for the tabs
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(com.github.florent37.materialviewpager.R.layout.material_view_pager_placeholder, parent, false);
                return new ViewHolder(view);
            }
            default:
                // The regular record view
                return super.onCreateViewHolder(parent, viewType);
        }
    }

    /**
     * Get record at given place
     * @param position - The position of the record in the list
     * @return
     */
    @Override
    public Business get(int position) {
        if (position < mPlaceholderSize)
            return new Business();
        else
            return super.get(position- mPlaceholderSize);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(getItemViewType(position) == TYPE_PLACEHOLDER)
                return;
        View v = holder.itemView;
        final TextView name = (TextView)v.findViewById(R.id.name);
        final TextView address = (TextView)v.findViewById(R.id.address);
        final TextView email = (TextView)v.findViewById(R.id.email);
        final TextView phone = (TextView)v.findViewById(R.id.phone);
        final ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        final ProgressBar bar = (ProgressBar) v.findViewById(R.id.progressBar);

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        name.setText(get(position).getName());
        phone.setText(get(position).getPhone());
        address.setText(get(position).getAddress().toString());
        email.setText(get(position).getEmail());

        /**
         * When click on item is preformed go to new activity and show the item
         */
        v.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Business_details.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                imageView.buildDrawingCache();
                intent.putExtra("Image", imageView.getDrawingCache());
                intent.putExtra("ID", get(position).getId());
                context.startActivity(intent);
            }
        });

        /**
         * AsyncTask for downloading the image of the business
         */
        new AsyncTask<String,Void,Void>(){
            Bitmap myBitmap = null;

            @Override
            protected void onPreExecute() {
                bar.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.INVISIBLE);
            }

            @Override
            protected Void doInBackground(String... strings) {
                if(strings.length > 0)
                    myBitmap = ExtractFavicon.getFavicon(
                            BitmapFactory.decodeResource(context.getResources(),R.drawable.file_not_found)
                            , strings[0], 50);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                bar.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(ExtractFavicon.getRoundedCornerBitmap(myBitmap,myBitmap.getWidth()/10));
            }
        }.execute(get(position).getLink().toString());
    }
}
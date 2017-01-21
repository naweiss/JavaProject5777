package com.example.jeremie.javaproject5777;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by nadav on 12/25/2016.
 * Package: com.example.nadav.testproject
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Business[] mDataset;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;
        public TextView address;
        public TextView email;
        public TextView phone;
        public ImageView imageView;
        public ProgressBar bar;
        public ViewHolder(View v) {
            super(v);
            name = (TextView)v.findViewById(R.id.name);
            address = (TextView)v.findViewById(R.id.address);
            email = (TextView)v.findViewById(R.id.email);
            phone = (TextView)v.findViewById(R.id.phone);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            bar = (ProgressBar) v.findViewById(R.id.progressBar);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapter(Business[] myDataset, Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        //...
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.name.setText(mDataset[position].getName());
        holder.phone.setText(mDataset[position].getPhone());
        holder.address.setText(mDataset[position].getAddress().toString());
        holder.email.setText(mDataset[position].getEmail());
        new AsyncTask<String,Void,Void>(){
            Bitmap myBitmap = null;

            @Override
            protected void onPreExecute() {
                holder.bar.setVisibility(View.VISIBLE);
                holder.imageView.setVisibility(View.INVISIBLE);
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
                holder.bar.setVisibility(View.INVISIBLE);
                holder.imageView.setVisibility(View.VISIBLE);
                holder.imageView.setImageBitmap(ExtractFavicon.getRoundedCornerBitmap(myBitmap,5));
            }
        }.execute(mDataset[position].getLink().toString());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
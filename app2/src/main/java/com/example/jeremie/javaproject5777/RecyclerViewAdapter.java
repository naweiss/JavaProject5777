package com.example.jeremie.javaproject5777;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jeremie.javaproject5777.entities.Business;

import java.util.List;


/**
 * Created by nadav on 12/25/2016.
 * Package: com.example.nadav.testproject
 */
public class RecyclerViewAdapter extends UpdateableRecyclerViewAdapter<RecyclerViewAdapter.ViewHolder> {
    private List<Business> mDataset;
    private Context context;

    @Override
    public void Update(ListDB_manager manager) {
        mDataset.clear();
        mDataset.addAll(manager.getAllBusinesses());
    }

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
        public int Id = 0;
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
    public RecyclerViewAdapter(List<Business>myDataset, Context context) {
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

        final ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, Business_details.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ImageView img = vh.imageView;
                img.buildDrawingCache();
                intent.putExtra("Image", img.getDrawingCache());
                intent.putExtra("ID", vh.Id);
                String transitionName = "image";
                context.startActivity(intent);
                    //ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            //(Activity) v.getContext(),img,transitionName);
                    //ActivityCompat.startActivity(context, intent, options.toBundle());

                }

        });
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.Id = mDataset.get(position).getId();
        holder.name.setText(mDataset.get(position).getName());
        holder.phone.setText(mDataset.get(position).getPhone());
        holder.address.setText(mDataset.get(position).getAddress().toString());
        holder.email.setText(mDataset.get(position).getEmail());
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
                holder.imageView.setImageBitmap(ExtractFavicon.getRoundedCornerBitmap(myBitmap,myBitmap.getWidth()/10));
            }
        }.execute(mDataset.get(position).getLink().toString());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
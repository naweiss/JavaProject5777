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
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jeremie.javaproject5777.entities.Business;


import java.util.List;


/**
 * Created by nadav on 12/25/2016.
 * Package: com.example.nadav.testproject
 */
public class RecyclerViewAdapter extends FilterAdapter<Business> {

    private Context context;
    public RecyclerViewAdapter(int resource,Context context, List<Business> objects) {
        super(resource, objects);
        this.context = context;
    }
    // Replace the contents of a view (invoked by the layout manager)

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
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

        v.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, Business_details.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ImageView img = imageView;
                img.buildDrawingCache();
                intent.putExtra("Image", img.getDrawingCache());
                intent.putExtra("ID", get(position).getId());
                context.startActivity(intent);
            }
        });

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
package com.example.jeremie.javaproject5777;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.jeremie.javaproject5777.entities.Business;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by nadav on 1/30/2017.
 * Package: com.example.jeremie.javaproject5777
 */

public abstract class FilterAdapter<T> extends RecyclerView.Adapter<FilterAdapter.ViewHolder> implements Filterable {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    private Filter mFilter;
    private List<T> mObjects;
    private List<T> mOriginalValues;
    private final int mResource;

    public FilterAdapter(int resource,List<T> objects) {
        this.mObjects = new ArrayList<T>(objects);
        this.mResource = resource;
    }

    @Override
    public FilterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(mResource, parent, false);
        return new FilterAdapter.ViewHolder(v);
    }

    public void clear() {
        if (mOriginalValues != null) {
            mOriginalValues.clear();
        } else {
            mObjects.clear();
        }
        notifyDataSetChanged();
    }

    public void addAll(List<T> objects) {
        if (mOriginalValues != null) {
            mOriginalValues.addAll(objects);
        } else {
            mObjects.addAll(objects);
        }
        notifyDataSetChanged();
    }

    public T get(int position){
        return mObjects.get(position);
    }
    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new MyFilter();
        }
        return mFilter;
    }

    private class MyFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {


            final FilterResults results = new FilterResults();

            if (mOriginalValues == null) {
                mOriginalValues = new ArrayList<>(mObjects);
            }

            if (constraint == null || constraint.length() == 0) {
                final ArrayList<T> list = new ArrayList<>(mOriginalValues);
                results.values = list;
                results.count = list.size();
            } else {
                final String prefixString = constraint.toString().toLowerCase();
                final ArrayList<T> values = new ArrayList<>(mOriginalValues);

                final int count = values.size();
                final ArrayList<T> newValues = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    final T value = values.get(i);
                    final String valueText = value.toString().toLowerCase();

                    // First match against the whole, non-splitted value
                    if (valueText.startsWith(prefixString)) {
                        newValues.add(value);
                    } else {
                        final String[] words = valueText.split(" ");
                        for (String word : words) {
                            if (word.startsWith(prefixString)) {
                                newValues.add(value);
                                break;
                            }
                        }
                    }
                }

                results.values = newValues;
                results.count = newValues.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mObjects = (List<T>) results.values;
            notifyDataSetChanged();
        }
    }
}


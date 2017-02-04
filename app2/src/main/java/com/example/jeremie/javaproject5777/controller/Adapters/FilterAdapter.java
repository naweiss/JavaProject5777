package com.example.jeremie.javaproject5777.controller.Adapters;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nadav on 1/30/2017.
 * Package: com.example.jeremie.javaproject5777
 */

/**
 * General Adapter which extends the basic RecyclerView.Adapter
 * Support Filtering and making most of the work for you
 * @param <T> Type of records
 */
public abstract class FilterAdapter<T> extends RecyclerView.Adapter<FilterAdapter.ViewHolder> implements Filterable {
    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View v) {
            super(v);
        }
    }


    private Filter mFilter;
    private List<T> mObjects;
    private List<T> mOriginalValues;
    private final int mResource;

    /**
     *  Constructor
     * @param resource - resource id of layout for each record
     * @param objects - list of the records to show
     */
    FilterAdapter(int resource, List<T> objects) {
        this.mObjects = new ArrayList<>(objects);
        this.mResource = resource;
    }

    /**
     *  Create new view from a record
     * @param parent - The Parent of the view
     * @param viewType - The type of the view
     * @return
     */
    @Override
    public FilterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(mResource, parent, false);
        return new FilterAdapter.ViewHolder(v);
    }

    /**
     *  Clear the list of records and update the layout [notifyDataSetChanged]
     */
    public void clear() {
        if (mOriginalValues != null) {
            mOriginalValues.clear();
        } else {
            mObjects.clear();
        }
        notifyDataSetChanged();
    }

    /**
     *  Add list of record to the existing records
     *      and update the layout [notifyDataSetChanged]
     * @param objects list of record
     */
    public void addAll(List<T> objects) {
        if (mOriginalValues != null) {
            mOriginalValues.addAll(objects);
        } else {
            mObjects.addAll(objects);
        }
        notifyDataSetChanged();
    }

    /**
     *  Get record at given place
     * @param position - The position of the record in the list
     * @return
     */
    public T get(int position){
        return mObjects.get(position);
    }

    /**
     *  Get the number of records
     * @return
     */
    @Override
    public int getItemCount() {
        return mObjects.size();
    }

    /**
     *  Get filter object for filtering the records
     * @return
     */
    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new MyFilter();
        }
        return mFilter;
    }

    /**
     * General filter class
     */
    private class MyFilter extends Filter {

        /**
         *  Create new list of the filtered records
         * @param constraint string to look for
         * @return the list of the filtered records
         */
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {


            final FilterResults results = new FilterResults();

            /**
             * Create backup of the records
             */
            if (mOriginalValues == null) {
                mOriginalValues = new ArrayList<>(mObjects);
            }

            if (constraint == null || constraint.length() == 0) {
                /**
                 * If there is no constraint return all the records
                 */
                final ArrayList<T> list = new ArrayList<>(mOriginalValues);
                results.values = list;
                results.count = list.size();
            } else {
                final String prefixString = constraint.toString().toLowerCase();
                final ArrayList<T> values = new ArrayList<>(mOriginalValues);

                /**
                 * Run on every record and look for the given constraint in its toString
                 *      by splitting to words
                 */
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

        /**
         *  Return the filtered list and update layout
         * @param constraint
         * @param results
         */
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mObjects = (List<T>) results.values;
            notifyDataSetChanged();
        }
    }
}


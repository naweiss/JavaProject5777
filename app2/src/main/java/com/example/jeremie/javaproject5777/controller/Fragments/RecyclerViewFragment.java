package com.example.jeremie.javaproject5777.controller.Fragments;
import android.app.SearchManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import com.example.jeremie.javaproject5777.controller.Adapters.FilterAdapter;
import com.example.jeremie.javaproject5777.model.datasource.ListDB_manager;
import com.example.jeremie.javaproject5777.R;
import com.example.jeremie.javaproject5777.controller.Adapters.RecyclerViewAdapter;
import com.example.jeremie.javaproject5777.controller.Adapters.RecyclerViewAdapterActivities;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import static android.content.Context.SEARCH_SERVICE;

/**
 * Created by jerem on 19.01.17.
 * Package: ${PACKAGE_NAME}
 */

public class RecyclerViewFragment extends Fragment  {

    private RecyclerView mRecyclerView;
    private FilterAdapter mAdapter;
    private ListDB_manager db_manager = ListDB_manager.getInstance();

    public static RecyclerViewFragment newInstance(int type) {
        final RecyclerViewFragment f = new RecyclerViewFragment();
        Bundle args = new Bundle();
        args.putInt("index", type);
        f.setArguments(args);
        return f;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        int index = args.getInt("index", -1);
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        //Allows a display in vertical list form
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        try {

            //if the index is 0 we use the adapter for the tabs Business in the menu activity (show all businesses)
            if(index == 0){
                mAdapter = new RecyclerViewAdapter(R.layout.card_view_row,getActivity().getBaseContext(),db_manager.getAllBusinesses());
            }
            //if the index is 1 we use the adapter for the tabs activitie in the menu activity(show all activities)
            else if(index==1){
                mAdapter = new RecyclerViewAdapterActivities(R.layout.cardview_activities,db_manager.getAllActivity());
            }
            mRecyclerView.setAdapter(mAdapter);

            //Notify the MaterialViewPager that a RecyclerView will be used
            MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);

        }catch (Exception ignored){}
    }

    /**
     * function to manage the searchview
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.search_option_menu, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getContext().getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ((Filterable)mRecyclerView.getAdapter()).getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu,inflater);
    }
}
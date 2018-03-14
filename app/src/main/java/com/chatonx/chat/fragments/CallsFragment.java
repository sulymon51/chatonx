package com.chatonx.chat.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.chatonx.chat.apps.R;
import com.chatonx.chat.activities.SettingsActivity;
import com.chatonx.chat.activities.AboutUserActivity;
import com.chatonx.chat.adapters.CallsAdapter;
import com.chatonx.chat.models.CallsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CallsFragment extends Fragment {

    public RecyclerView mCallsList;
    public View mView;
    public List<CallsModel> mCallsModel;
    public CallsAdapter mCallsAdapter;
    int incoming;


    public CallsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_calls, container, false);
        initUI();
        setHasOptionsMenu(true);
        return mView;
    }

    //Method to add menu items to the toolbar
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.calls_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.status) {
            Intent startIntent = new Intent(getActivity(), AboutUserActivity.class);
            this.startActivity(startIntent);
        }
        if (item.getItemId() == R.id.settings) {
            Intent startIntent = new Intent(getActivity(), SettingsActivity.class);
            this.startActivity(startIntent);
        }
        return true;
    }

    //Method and views initializer
    public void initUI() {
        mCallsList = (RecyclerView) mView.findViewById(R.id.callsList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mCallsModel = new ArrayList<CallsModel>();
        mCallsAdapter = new CallsAdapter(getActivity(), mCallsModel);
        mCallsList.setLayoutManager(layoutManager);
        mCallsList.setAdapter(mCallsAdapter);
        prepareItems();

    }

    //Method to add items to the recyclerview
    private void prepareItems() {
        mCallsModel.add(new CallsModel(R.drawable.image1, "+233 54 406 9752", "May31, 17:20", "incoming", R.drawable.ic_video));
        mCallsModel.add(new CallsModel(R.drawable.image1, "+233 54 406 9752", "May 31, 17:10", R.drawable.ic_call));
        mCallsModel.add(new CallsModel(R.drawable.image1, "+233 54 406 9752", "May 31, 16:59", "incoming", R.drawable.ic_call));
        mCallsModel.add(new CallsModel(R.drawable.image1, "+233 54 406 9752", "May 31, 16:30", "incoming", R.drawable.ic_video));
        mCallsModel.add(new CallsModel(R.drawable.image5, "Mom", "May 31, 12:05", R.drawable.ic_video));
        mCallsModel.add(new CallsModel(R.drawable.image5, "Mom", "May31, 17:20", "incoming", R.drawable.ic_video));
        mCallsModel.add(new CallsModel(R.drawable.image5, "Mom", "May 31, 17:10", R.drawable.ic_call));
        mCallsModel.add(new CallsModel(R.drawable.image3, "My Bro", "May 31, 16:59", "incoming", R.drawable.ic_call));
        mCallsModel.add(new CallsModel(R.drawable.image4, "+233 23 123 2242", "May 31, 16:30", "incoming", R.drawable.ic_video));
        mCallsModel.add(new CallsModel(R.drawable.image5, "Mom", "May 31, 12:05", R.drawable.ic_video));
        mCallsModel.add(new CallsModel(R.drawable.image4, "+233 23 123 2242", "May 31, 16:30", "incoming", R.drawable.ic_video));
        mCallsModel.add(new CallsModel(R.drawable.image5, "Mom", "May 31, 12:05", R.drawable.ic_video));
        mCallsModel.add(new CallsModel(R.drawable.image3, "My Bro", "May 31, 16:59", "incoming", R.drawable.ic_call));
        mCallsModel.add(new CallsModel(R.drawable.image4, "+233 23 123 2242", "May 31, 16:30", "incoming", R.drawable.ic_video));
    }

}

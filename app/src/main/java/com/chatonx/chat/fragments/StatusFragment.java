package com.chatonx.chat.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chatonx.chat.apps.R;
import com.chatonx.chat.adapters.StatusAdapter;
import com.chatonx.chat.models.StatusModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends Fragment {

    public RecyclerView mRecentStatusList;
    public View mView;
    public List<StatusModel> mRecentStatusModel;
    public StatusAdapter mRecentStatusAdapter;


    public StatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_status, container, false);
        initUI();
        setHasOptionsMenu(true);
        return mView;
    }


    //Method to add menu items to the toolbar
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.status_menu, menu);
    }

    //Method and views initializer
    public void initUI() {

        mRecentStatusList = (RecyclerView) mView.findViewById(R.id.recentStatusList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecentStatusModel = new ArrayList<StatusModel>();
        mRecentStatusAdapter = new StatusAdapter(getActivity(), mRecentStatusModel);
        mRecentStatusList.setLayoutManager(layoutManager);
        mRecentStatusList.setHasFixedSize(true);
        mRecentStatusAdapter.notifyDataSetChanged();
        mRecentStatusList.setAdapter(mRecentStatusAdapter);
        prepareRecentUpdatesItems();

    }

    // This is a simple method to add items to our recyclerview
    private void prepareRecentUpdatesItems() {
        mRecentStatusModel.add(new StatusModel(R.drawable.image1, "+233 54 406 9752 ", "17:20"));
        mRecentStatusModel.add(new StatusModel(R.drawable.image2, "+233 54 102 6859 ", "17:10"));
        mRecentStatusModel.add(new StatusModel(R.drawable.image3, "John Westley", "16:59"));
        mRecentStatusModel.add(new StatusModel(R.drawable.image4, "Alex Gordon ", "16:30"));
        mRecentStatusModel.add(new StatusModel(R.drawable.image1, "+233 54 406 9752 ", "17:20"));
        mRecentStatusModel.add(new StatusModel(R.drawable.image2, "+233 54 102 6859 ", "17:10"));
        mRecentStatusModel.add(new StatusModel(R.drawable.image3, "John Westley", "16:59"));
        mRecentStatusModel.add(new StatusModel(R.drawable.image4, "Alex Gordon ", "16:30"));
    }
}
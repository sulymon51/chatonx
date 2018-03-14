package com.chatonx.chat.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chatonx.chat.apps.R;
import com.chatonx.chat.adapters.AboutAdapter;
import com.chatonx.chat.helpers.ClickListener;
import com.chatonx.chat.helpers.DividerItemDecoration;
import com.chatonx.chat.helpers.RecyclerItemClickListener;
import com.chatonx.chat.models.AboutModel;

import java.util.ArrayList;
import java.util.List;

public class AboutUserActivity extends AppCompatActivity {

    public RecyclerView mStatusList;
    public View mView;
    LinearLayoutManager layoutManager;
    private Toolbar toolbar;
    TextView status;
    public List<AboutModel> mAboutModel;
    public AboutAdapter mAboutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_user);
        initUI();
    }

    public void initUI(){
        mStatusList = (RecyclerView) findViewById(R.id.statusList);
        status = (TextView) findViewById(R.id.status);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mAboutModel = new ArrayList<AboutModel>();
        mAboutAdapter = new  AboutAdapter(this, mAboutModel);
        mStatusList.setLayoutManager(layoutManager);
        mStatusList.setAdapter(mAboutAdapter);
        mStatusList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        prepareItems();

        mStatusList.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),
                mStatusList, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                // Here we set the user's status to the item clicked in the recyclerview
                // Fro instance, when "Available" or "At school is clicked, it is set as the user's current status"
                AboutModel item = mAboutModel.get(position);
                    status.setText(item.getTitle());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // This is a simple method to add items to our recyclerview
    private void prepareItems() {
        mAboutModel.add(new AboutModel("Available"));
        mAboutModel.add(new AboutModel("Busy"));
        mAboutModel.add(new AboutModel("At School"));
        mAboutModel.add(new AboutModel("At the movies"));
        mAboutModel.add(new AboutModel("At work"));
        mAboutModel.add(new AboutModel("Batery about to die"));
        mAboutModel.add(new AboutModel("Can't talk, ChatUp only"));
        mAboutModel.add(new AboutModel("In a meeting"));
        mAboutModel.add(new AboutModel("At the gym"));
        mAboutModel.add(new AboutModel("Sleeping"));
        mAboutModel.add(new AboutModel("Urgent call only"));
        mAboutModel.add(new AboutModel("Going to the beach"));
        mAboutModel.add(new AboutModel("Feeling lonely"));
    }
}

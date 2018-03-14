package com.chatonx.chat.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.chatonx.chat.apps.R;
import com.chatonx.chat.adapters.SettingsAdapter;
import com.chatonx.chat.helpers.ClickListener;
import com.chatonx.chat.helpers.RecyclerItemClickListener;
import com.chatonx.chat.models.SettingsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{
    public RecyclerView mSettingsList;
    public View mView;
    private Toolbar toolbar;
    public List<SettingsModel> mSettingsModel;
    public SettingsAdapter mSettingsAdapter;
    public ImageView profilePic;
    Context context;
    Activity mActivity;
    Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initUI();
        setupWindowAnimations();
    }

    private void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        // Re-enter transition is executed when returning to this activity
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        getWindow().setReenterTransition(slideTransition);
        getWindow().setExitTransition(slideTransition);
      }
    }

    //Method and views initializer
    public void initUI(){

        mSettingsList = (RecyclerView) findViewById(R.id.settingsList);
        profilePic = (ImageView) findViewById(R.id.picture);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mSettingsModel = new ArrayList<>();
        mSettingsAdapter = new SettingsAdapter(this, mSettingsModel);
        mSettingsList.setLayoutManager(layoutManager);
        mSettingsList.setAdapter(mSettingsAdapter);
        mSettingsList.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),
                mSettingsList, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (position) {
                    case 0:
                        mIntent = new Intent(SettingsActivity.this, AccountSettingsActivity.class);
                        startActivity(mIntent);
                        break;
                    case 1:
                        mIntent = new Intent(SettingsActivity.this, ChatSettingsActivity.class);
                        startActivity(mIntent);
                        break;
                    case 2:
                        mIntent = new Intent(SettingsActivity.this, NotificationSettingsActivity.class);
                        startActivity(mIntent);
                        break;
                    case 3:
                        mIntent = new Intent(SettingsActivity.this, DataUsageSettingsActivity.class);
                        startActivity(mIntent);
                        break;
                    case 4:
                        mIntent = new Intent(SettingsActivity.this, ContactsSettingsActivity.class);
                        startActivity(mIntent);
                        break;
                    case 5:
                        mIntent = new Intent(SettingsActivity.this, AboutAndHelpActivity.class);
                        startActivity(mIntent);
                        break;
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareItems();

        //Method to setup our ActionBar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Picasso.with(getApplicationContext())
                .load(R.drawable.image4)
                .error(R.drawable.image4)
                .into(profilePic);
        profilePic.setOnClickListener(this);
    }

    //Add items to our recyclerview
    private void prepareItems() {
        mSettingsModel.add(new SettingsModel(R.drawable.ic_key_variant, R.string.account,
        SettingsModel.SETTINGS_ACCOUNT));
        mSettingsModel.add(new SettingsModel(R.drawable.ic_action_chat, R.string.chats,
        SettingsModel.DRAWER_ITEM_TAG_GIT_HUB));
        mSettingsModel.add(new SettingsModel(R.drawable.ic_action_notification, R.string.notif,
        SettingsModel.DRAWER_ITEM_TAG_LEFT_MENUS));
        mSettingsModel.add(new SettingsModel(R.drawable.ic_chart_donut, R.string.data,
        SettingsModel.DRAWER_ITEM_TAG_LINKED_IN));
        mSettingsModel.add(new SettingsModel(R.drawable.ic_account_multiple, R.string.contacts,
        SettingsModel.DRAWER_ITEM_TAG_GIT_HUB));
        mSettingsModel.add(new SettingsModel(R.drawable.ic_help, R.string.help,
                SettingsModel.DRAWER_ITEM_TAG_LINKED_IN));
        mSettingsAdapter.notifyDataSetChanged();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.picture:
                Intent photoIntent = new Intent(this, ProfileSettingsActivity.class);
                startActivity(photoIntent);

                View sharedView = profilePic;
                String transitionName = getString(R.string.imageTransition);

                ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(SettingsActivity.this, sharedView, transitionName);
                startActivity(photoIntent, transitionActivityOptions.toBundle());
                break;
        }
    }
}

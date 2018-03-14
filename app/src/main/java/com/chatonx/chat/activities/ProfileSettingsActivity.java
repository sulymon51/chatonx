package com.chatonx.chat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.chatonx.chat.apps.R;


public class ProfileSettingsActivity extends AppCompatActivity implements View.OnClickListener{
    public LinearLayout changeAbout, changeUsername;
    public Intent mIntent;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initUI();

    }

    private void initUI() {
        changeAbout = (LinearLayout) findViewById(R.id.changeAbout);
        changeUsername = (LinearLayout) findViewById(R.id.changeUsername);

        changeAbout.setOnClickListener(this);
        changeUsername.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changeAbout:
                mIntent = new Intent(this, AboutUserActivity.class);
                startActivity(mIntent);
                break;
            case R.id.changeUsername:
                mIntent = new Intent(this, MessagingActivity.class);
                startActivity(mIntent);
                break;
        }
    }
}

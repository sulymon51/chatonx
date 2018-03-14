package com.chatonx.chat.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chatonx.chat.apps.R;

public class AddGroupParticipantsActivity extends AppCompatActivity {

    private TextView title, next;
    private ImageView newBroadcast;
    private LinearLayout backLayout, nextLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group_participants);
        initUI();
    }

    private void initUI(){
        title = (TextView) findViewById(R.id.title);
        next = (TextView) findViewById(R.id.next);
        newBroadcast = (ImageView) findViewById(R.id.select);
        backLayout = (LinearLayout) findViewById(R.id.backLayout);

        int color = getResources().getColor(R.color.colorPrimaryDark);
        title.setText("New group");
        next.setText("CREATE");
        newBroadcast.setColorFilter(color);
    }
}

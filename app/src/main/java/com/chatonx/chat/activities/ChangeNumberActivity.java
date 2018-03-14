package com.chatonx.chat.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chatonx.chat.apps.R;


public class ChangeNumberActivity extends AppCompatActivity {

    private TextView title, next;
    private ImageView back, check;
    private LinearLayout backLayout, nextLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_number);

        initUI();
    }

    private void initUI(){
        title = (TextView) findViewById(R.id.title);
        next = (TextView) findViewById(R.id.next);
        back = (ImageView) findViewById(R.id.back);
        backLayout = (LinearLayout) findViewById(R.id.backLayout);
        nextLayout = (LinearLayout) findViewById(R.id.nextLayout);

        title.setText(R.string.change_num);
        next.setText("NEXT");
    }

}

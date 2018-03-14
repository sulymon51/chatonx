package com.chatonx.chat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chatonx.chat.apps.R;


public class VoiceCallActivity extends AppCompatActivity implements View.OnClickListener{
    public TextView userProfileName;
    public ImageView volume, chat, mute;
    public FloatingActionButton endCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_call);
        initUI();
    }

    public void initUI(){
        endCall = (FloatingActionButton) findViewById(R.id.end);
        volume = (ImageView) findViewById(R.id.volume);
        chat = (ImageView) findViewById(R.id.chat);
        mute = (ImageView) findViewById(R.id.mute);
        endCall.getResources().getColor(R.color.red);
        endCall.setOnClickListener(this);
        volume.setOnClickListener(this);
        chat.setOnClickListener(this);
        mute.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.end:
                finish();
                break;
            case R.id.volume:
                volume.setImageResource(R.drawable.ic_action_volume_muted);
                break;
            case R.id.chat:
                Intent intent = new Intent(this, MessagingActivity.class);
                startActivity(intent);
                break;
            case R.id.mute:
                mute.setImageResource(R.drawable.ic_action_mic);
                break;
        }
    }

}

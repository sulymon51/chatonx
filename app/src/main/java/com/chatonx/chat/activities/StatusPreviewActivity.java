package com.chatonx.chat.activities;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;

import com.chatonx.chat.apps.R;

import java.util.Timer;
import java.util.TimerTask;

public class StatusPreviewActivity extends AppCompatActivity implements View.OnClickListener{
    private static int SPLASH_TIME_OUT = 10000;
    LinearLayout root, reply;
    final Context context = this;
    private Context mContext;
    private Activity mActivity;
    private Timer autoUpdate;
    ObjectAnimator animation;

    private LinearLayout mLinearLayout;

    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_status_preview);
        initUI();
    }

    private void initUI(){
        root = (LinearLayout) findViewById(R.id.imageLayout);
        reply = (LinearLayout) findViewById(R.id.replyLayout);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        animation.setDuration(10000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) { }

            @Override
            public void onAnimationEnd(Animator animator) {
                //do something when the countdown is complete
            }

            @Override
            public void onAnimationCancel(Animator animator) { }

            @Override
            public void onAnimationRepeat(Animator animator) { }
        });

        animation.start();
        timer();

        root.setOnClickListener(this);
        reply.setOnClickListener(this);
    }

    public void timer(){
        autoUpdate = new Timer();
        autoUpdate.schedule(new TimerTask() {
            @Override
            public void run() {
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageLayout:
                finish();
                break;
            case R.id.replyLayout:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    animation.pause();
                }
                autoUpdate.cancel();

                final Dialog dialog = new Dialog(context,
                        R.style.Dialog);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.status_reply_item);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.getWindow().setAttributes(lp);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            animation.resume();
                            autoUpdate.purge();
                        }
                    }
                });
                // show it
                dialog.show();
                break;
        }
    }
}

package com.chatonx.chat.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chatonx.chat.apps.R;
import com.chatonx.chat.animation.SupportAnimator;
import com.chatonx.chat.animation.ViewAnimationUtils;
import com.chatonx.chat.animation.ViewAudioProxy;
import com.chatonx.chat.helpers.Main;
import com.chatonx.chat.models.MessagingModel;
import com.chatonx.chat.widget.CircularButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MessagingActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView messageList;
    private CircularButton send,record;
    private TextView recordTimeText;
    private View recordPanel, messageItem;
    private View slideText;
    private float startedDraggingX = -1;
    private float distCanMove = dp(80);
    private long startTime = 0L;
    final int MIN_INTERVAL_TIME = 2000;
    long mStartTime;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    private Timer timer;
    private boolean hidden = true;
    private boolean isVissible = true;
    private Intent mIntent = null;
    public List<MessagingModel> mCallsModel;
    public MessagingModel messagesItem;
    public LinearLayoutManager layoutManager;
    private List<MessagingModel> mMessages = new ArrayList<MessagingModel>();
    private ImageView enter, video, camera, gallery, photo,  audio, location, contact;
    View rootView;
    ImageView attach, options, call;
    private EditText messageField;
    private TextView profile;
    private ImageView emojiButton;
    Activity mActivity;
    private Context mContext;
    public LinearLayout mRevealView, back, viewUser, voiceCall, videoCall, option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
        initView();
    }

    //Our view initializer
    private void initView() {

        rootView = findViewById(R.id.chat_layout);
        mRevealView = (LinearLayout) findViewById(R.id.reveal_items);
        back = (LinearLayout) findViewById(R.id.back);
        viewUser = (LinearLayout) findViewById(R.id.viewUser);
        voiceCall = (LinearLayout) findViewById(R.id.voiceCall);
        voiceCall = (LinearLayout) findViewById(R.id.voiceCall);
        videoCall = (LinearLayout) findViewById(R.id.videoCall);
        option = (LinearLayout) findViewById(R.id.option);
        messageList = (RecyclerView) findViewById(R.id.messageList);
        messageField = (EditText) findViewById(R.id.messageField);
        send = (CircularButton) findViewById(R.id.send);
        record = (CircularButton) findViewById(R.id.record);
        attach = (ImageView) findViewById(R.id.attach);
        camera = (ImageView) findViewById(R.id.camera);
        emojiButton = (ImageView) findViewById(R.id.emojiButton);

        recordPanel = findViewById(R.id.record_panel);
        messageItem = findViewById(R.id.messageItem);
        recordTimeText = (TextView) findViewById(R.id.recording_time_text);
        slideText = findViewById(R.id.slideText);
        TextView textView = (TextView) findViewById(R.id.slideToCancelTextView);
        textView.setText("SlideToCancel");
        mRevealView.setVisibility(View.GONE);

        messageField.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    send.setVisibility(View.VISIBLE);
                    record.setVisibility(View.GONE);
                    attach.setVisibility(View.GONE);
                    camera.setVisibility(View.GONE);
                }else{
                    send.setVisibility(View.GONE);
                    record.setVisibility(View.VISIBLE);
                    attach.setVisibility(View.VISIBLE);
                    camera.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                send.setVisibility(View.GONE);
                record.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        record.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) slideText
                        .getLayoutParams();
                params.leftMargin = dp(30);
                slideText.setLayoutParams(params);
                ViewAudioProxy.setAlpha(slideText, 1);
                startedDraggingX = -1;
                // startRecording();
                startrecord();
                recordPanel.setVisibility(View.VISIBLE);
                messageItem.setVisibility(View.GONE);
                record.getParent()
                        .requestDisallowInterceptTouchEvent(true);
                recordPanel.setVisibility(View.VISIBLE);
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP
                    || motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
                startedDraggingX = -1;
                stoprecord();
                recordPanel.setVisibility(View.GONE);
                messageItem.setVisibility(View.VISIBLE);
                // stopRecording(true);
            } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                float x = motionEvent.getX();
                if (x < -distCanMove) {
                    stoprecord();
                    recordPanel.setVisibility(View.GONE);
                    messageItem.setVisibility(View.VISIBLE);
                    // stopRecording(false);
                }
                x = x + ViewAudioProxy.getX(record);
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) slideText
                        .getLayoutParams();
                if (startedDraggingX != -1) {
                    float dist = (x - startedDraggingX);
                    params.leftMargin = dp(30) + (int) dist;
                    slideText.setLayoutParams(params);
                    float alpha = 1.0f + dist / distCanMove;
                    if (alpha > 1) {
                        alpha = 1;
                    } else if (alpha < 0) {
                        alpha = 0;
                    }
                    ViewAudioProxy.setAlpha(slideText, alpha);
                }
                if (x <= ViewAudioProxy.getX(slideText) + slideText.getWidth()
                        + dp(30)) {
                    if (startedDraggingX == -1) {
                        startedDraggingX = x;
                        distCanMove = (recordPanel.getMeasuredWidth()
                                - slideText.getMeasuredWidth() - dp(48)) / 2.0f;
                        if (distCanMove <= 0) {
                            distCanMove = dp(80);
                        } else if (distCanMove > dp(80)) {
                            distCanMove = dp(80);
                        }
                    }
                }
                if (params.leftMargin > dp(30)) {
                    params.leftMargin = dp(30);
                    slideText.setLayoutParams(params);
                    ViewAudioProxy.setAlpha(slideText, 1);
                    startedDraggingX = -1;
                }
            }
            view.onTouchEvent(motionEvent);
            return true;
        });

        attach.setOnClickListener(this);
        back.setOnClickListener(this);
        viewUser.setOnClickListener(this);
        voiceCall.setOnClickListener(this);
        videoCall.setOnClickListener(this);
        option.setOnClickListener(this);

    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.attach:
                if (isVissible) {
                    showRevealItem();
                }else {
                    hideRevealView();
                }
                break;
            case R.id.send:
                break;
            case R.id.back:
                finish();
                break;
            case R.id.viewUser:
                mIntent = new Intent(this, UserProfileActivity.class);
                startActivity(mIntent);
                break;
            case R.id.voiceCall:
                mIntent = new Intent(this, VoiceCallActivity.class);
                startActivity(mIntent);
                break;
            case R.id.videoCall:
                mIntent = new Intent(this, VoiceCallActivity.class);
                startActivity(mIntent);
                break;
            case R.id.option:
                PopupMenu popup = new PopupMenu(this, v);
                popup.getMenuInflater()
                        .inflate(R.menu.messaging_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.contact:
                            mIntent = new Intent(MessagingActivity.this, UserProfileActivity.class);
                            startActivity(mIntent);
                            break;
                        case R.id.more:
                            PopupMenu popup1 = new PopupMenu(MessagingActivity.this, v);
                            popup1.getMenuInflater().inflate(R.menu.messaging_menu_more, popup1.getMenu());

                            popup1.show();
                            break;
                    }
                    return true;
                });
                popup.show();

                break;
            default:
                    hideRevealView();
                break;
        }

    }

    //This is our circular reveal layout. when the user clicks on the attach icon in
    // the messaging section, it pops up a menu with a circular reveal

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


    private void startrecord() {
        // TODO Auto-generated method stub
        startTime = SystemClock.uptimeMillis();
        timer = new Timer();
        MyTimerTask myTimerTask = new MyTimerTask();
        timer.schedule(myTimerTask, 1000, 1000);
        vibrate();
    }

    private void stoprecord() {
        // TODO Auto-generated method stub
        if (timer != null) {
            timer.cancel();
        }
        if (recordTimeText.getText().toString().equals("00:00")) {
            return;
        }
        recordTimeText.setText("00:00");
        vibrate();
    }

    private void vibrate() {
        // TODO Auto-generated method stub
        try {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int dp(float value) {
        return (int) Math.ceil(1 * value);
    }

    private class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            long timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            long timeSwapBuff = 0L;
            long updatedTime = timeSwapBuff + timeInMilliseconds;
            final String recordTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(updatedTime) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(updatedTime)), TimeUnit.MILLISECONDS.toSeconds(updatedTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(updatedTime)));
            runOnUiThread(() -> {
                try {
                    if (recordTimeText != null)
                        recordTimeText.setText(recordTime);
                } catch (Exception e) {
                    Main.LogCat("Exception record MessagesActivity");
                }

            });
        }
    }

    //This is our circular reveal layout. when the user clicks on the attach icon in
    // the messaging section, it pops up a menu with a circular reveal

    private void showRevealItem() {

        int cx = (mRevealView.getLeft() + mRevealView.getRight());
        int cy = mRevealView.getTop();
        int radius = Math.max(mRevealView.getWidth(), mRevealView.getHeight());

        //Below Android LOLIPOP Version
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            SupportAnimator animator =
                    ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, 0, radius);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setDuration(5000);

            SupportAnimator animator_reverse = animator.reverse();

            if (hidden) {
                mRevealView.setVisibility(View.VISIBLE);
                animator.start();
                hidden = false;
            } else {
                animator_reverse.addListener(new SupportAnimator.AnimatorListener() {
                    @Override
                    public void onAnimationStart() {

                    }

                    @Override
                    public void onAnimationEnd() {
                        mRevealView.setVisibility(View.INVISIBLE);
                        hidden = true;

                    }

                    @Override
                    public void onAnimationCancel() {

                    }

                    @Override
                    public void onAnimationRepeat() {

                    }
                });
                animator_reverse.start();
            }
        }
        // Android LOLIPOP And ABOVE Version
        else {
            if (hidden) {
                Animator anim = android.view.ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, 0, radius);
                mRevealView.setVisibility(View.VISIBLE);
                anim.start();
                hidden = false;
            } else {
                Animator anim = android.view.ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, radius, 0);
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mRevealView.setVisibility(View.INVISIBLE);
                        hidden = true;
                    }
                });
                anim.start();
            }
        }
    }

    //Method to hide our circular reveal layout
    private void hideRevealView() {
        if (mRevealView.getVisibility() == View.VISIBLE) {
            mRevealView.setVisibility(View.GONE);
            hidden = true;
        }
    }
}

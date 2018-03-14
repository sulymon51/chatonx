package com.chatonx.chat.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chatonx.chat.apps.R;
import com.chatonx.chat.adapters.UsersAdapter;
import com.chatonx.chat.models.UsersModel;

import java.util.ArrayList;
import java.util.List;


public class NewGroupActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView title, participants;
    private LinearLayout back, nextLayout;
    public RecyclerView mGroupParticipants;
    public List<UsersModel> mUsersModel;
    public UsersAdapter mUsersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);

        initUI();
        setupWindowAnimations();
    }

    private void setupWindowAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Re-enter transition is executed when returning to this activity
            Slide slideTransition = new Slide();
            slideTransition.setSlideEdge(Gravity.START);
            slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
            getWindow().setReenterTransition(slideTransition);
            getWindow().setExitTransition(slideTransition);
        }
    }

    private void initUI(){
        title = (TextView) findViewById(R.id.title);
        participants = (TextView) findViewById(R.id.participants);
        back = (LinearLayout) findViewById(R.id.btnLeft);

        mGroupParticipants = (RecyclerView) findViewById(R.id.participantList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mUsersModel = new ArrayList<UsersModel>();
        mUsersAdapter= new UsersAdapter(this, mUsersModel);
        mGroupParticipants.setLayoutManager(layoutManager);
        mUsersAdapter.notifyItemInserted(mUsersModel.size()-1);
        mGroupParticipants.setAdapter(mUsersAdapter);
        getParcitipants();

        title.setText("New group");
        participants.setText("Add participants");
        back.setOnClickListener(this);
    }

    // This is a simple method to add items to our recyclerview
    private void getParcitipants() {
        UsersModel model = new UsersModel(R.drawable.image1, "Evander Hollified", "Hey am Evander");
        UsersModel model2 = new UsersModel(R.drawable.image2, "Georgette Einstein", "Good job fellez");
        UsersModel model3 = new UsersModel(R.drawable.image3, "John Westley", "Am feeling good");
        UsersModel model4 = new UsersModel(R.drawable.image4, "Alex Gordon ", "Am available");
        UsersModel model5 = new UsersModel(R.drawable.image1, "Mark", "Battery about to die");
        UsersModel model6 = new UsersModel(R.drawable.image2, "Emmanuel Doe ", "what do you say");
        UsersModel model7 = new UsersModel(R.drawable.image3, "John Zuckerberg", "Am so pissed");
        UsersModel model8 = new UsersModel(R.drawable.image4, "Jane Doe", "Jame where are you");
        UsersModel model9 = new UsersModel(R.drawable.image1, "Mark", "Battery about to die");
        UsersModel model10 = new UsersModel(R.drawable.image2, "Emmanuel Doe ", "what do you say");
        UsersModel model11 = new UsersModel(R.drawable.image3, "John Zuckerberg", "Am so pissed");
        UsersModel model12 = new UsersModel(R.drawable.image4, "Jane Doe", "Jame where are you");
        mUsersModel.add(model);
        mUsersModel.add(model2);
        mUsersModel.add(model3);
        mUsersModel.add(model4);
        mUsersModel.add(model5);
        mUsersModel.add(model6);
        mUsersModel.add(model7);
        mUsersModel.add(model8);
        mUsersModel.add(model9);
        mUsersModel.add(model10);
        mUsersModel.add(model11);
        mUsersModel.add(model12);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btnLeft:
            finish();
            break;
        }

    }
}

package com.chatonx.chat.activities;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chatonx.chat.apps.R;
import com.chatonx.chat.adapters.ContactsAdapter;
import com.chatonx.chat.helpers.ClickListener;
import com.chatonx.chat.helpers.RecyclerItemClickListener;
import com.chatonx.chat.models.ContactsModel;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity  implements View.OnClickListener{
    public RecyclerView mContactsList;
    public View mView;
    public List<ContactsModel> mContactsModel;
    GestureDetectorCompat gestureDetector;
    public ContactsAdapter mContactsAdapter;
    public LinearLayout search, searchLayout, toolbarLayout, back;
    public ImageView backIcon, searchIcon;
    public TextView title;
    Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        initUI();
        setupToolbar();

    }

    //Method and views initializer
    public void initUI() {

        mContactsList = (RecyclerView) findViewById(R.id.contactsList);
        search = (LinearLayout) findViewById(R.id.btnRight);
        back = (LinearLayout) findViewById(R.id.btnLeft);
        backIcon = (ImageView) findViewById(R.id.iconLeft);
        searchIcon = (ImageView) findViewById(R.id.iconRight);
        title = (TextView) findViewById(R.id.title);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mContactsModel = new ArrayList<ContactsModel>();
        mContactsAdapter = new ContactsAdapter(this, mContactsModel);
        mContactsList.setLayoutManager(layoutManager);
        mContactsAdapter.notifyItemInserted(mContactsModel.size()-1);
        mContactsList.setAdapter(mContactsAdapter);

         //This is our custom RecyclerItemClickListener that can be used to perform certain click events such as long clicks and short clicks
        mContactsList.addOnItemTouchListener(new RecyclerItemClickListener(this, mContactsList, new ClickListener() {
        @Override
        public void onClick(View view, int position) {

        }

        @Override
        public void onLongClick(View view, int position) {
            // Start the CAB using the ActionMode.Callback defined above

        }
        }));
        back.setOnClickListener(this);

        prepareItems();
}

    // This is a simple method to add items to our recyclerview
    private void prepareItems() {
        mContactsModel.add(new ContactsModel(R.drawable.image12, "Bestie ", "MOBILE", "Cuzzy are you coming to the beach!"));
        mContactsModel.add(new ContactsModel(R.drawable.image6, "Emmanuel Doe ", "MOBILE", "Hey there! I am using ChatUp"));
        mContactsModel.add(new ContactsModel(R.drawable.image10, "Fish ", "MOBILE", "Hey there! I am using ChatUp"));
        mContactsModel.add(new ContactsModel(R.drawable.image7, "Gifty Lad ", "MOBILE", "Hey there! I am using ChatUp"));
        mContactsModel.add(new ContactsModel(R.drawable.image8, "Samuel Arthur ", "MOBILE", "Off to work"));
        mContactsModel.add(new ContactsModel(R.drawable.image9, "Sandy MacCathy ", "MOBILE", "Hey there! I am using ChatUp"));
        mContactsModel.add(new ContactsModel(R.drawable.image13, "Teacher", "MOBILE", "Thank you lord"));
        mContactsModel.add(new ContactsModel(R.drawable.user, "Augustine ", "+233 27 039 5893", R.string.invite));
        mContactsModel.add(new ContactsModel(R.drawable.user, "Chris ", "233 20 554 4257", R.string.invite));
        mContactsModel.add(new ContactsModel(R.drawable.user, "Kim ", "+233 23 123 2242", R.string.invite));
    }
    /*Method setup the toolbar
    /*Here we add the icons and give it a title
    */
    private void setupToolbar() {
        backIcon.setImageResource(R.drawable.ic_action_back);
        searchIcon.setImageResource(R.drawable.ic_search);
        title.setText("Contacts");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLeft:
                finish();
                break;
            default:
                break;
        }
    }
}

package com.chatonx.chat.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chatonx.chat.apps.R;
import com.chatonx.chat.adapters.ContactsAdapter;
import com.chatonx.chat.models.ContactsModel;
import com.futuremind.recyclerviewfastscroll.FastScroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SelectContactActivity extends AppCompatActivity {

    public RecyclerView mContactsList;
    public View mView;
    public List<ContactsModel> mContactsModel;
    FastScroller fastScroller;

    public ContactsAdapter mContactsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__contact);
        initUI();
    }


    public void initUI() {

        mContactsList = (RecyclerView) findViewById(R.id.contactsList);
        fastScroller = (FastScroller) findViewById(R.id.fastscroll);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mContactsModel = new ArrayList<ContactsModel>();
        mContactsAdapter= new ContactsAdapter(this, mContactsModel);
        mContactsList.setLayoutManager(layoutManager);
        mContactsList.setAdapter(mContactsAdapter);
        //has to be called AFTER RecyclerView.setAdapter()
        fastScroller.setRecyclerView(mContactsList);
        prepareItems();
    }

    private void prepareItems() {
        mContactsModel.add(new ContactsModel(R.drawable.image1, "Bestie ", "Cuzzy are you coming to the beach!", "MOBILE"));
        mContactsModel.add(new ContactsModel(R.drawable.image2, "Emmanuel Doe ", "Hey there! I am using ChatUp", "MOBILE"));
        mContactsModel.add(new ContactsModel(R.drawable.image3, "Fish ", "Hey there! I am using ChatUp", "MOBILE"));
        mContactsModel.add(new ContactsModel(R.drawable.image4, "Gifty Lad ", "Hey there! I am using ChatUp", "MOBILE"));
        mContactsModel.add(new ContactsModel(R.drawable.image5, "My Bro ", "Hey there! I am using ChatUp", "MOBILE"));
        mContactsModel.add(new ContactsModel(R.drawable.image6, "Samuel Arthur ", "Off to work", "MOBILE"));
        mContactsModel.add(new ContactsModel(R.drawable.image7, "Sandy MacCathy ", "Hey there! I am using ChatUp", "MOBILE"));
        mContactsModel.add(new ContactsModel(R.drawable.image8, "Teacher", "Thank you lord", "MOBILE"));
        mContactsModel.add(new ContactsModel(R.drawable.image9, "Bestie ", "Cuzzy are you coming to the beach!", "MOBILE"));
        mContactsModel.add(new ContactsModel(R.drawable.image10, "Emmanuel Doe ", "Hey there! I am using ChatUp", "MOBILE"));
        mContactsModel.add(new ContactsModel(R.drawable.image11, "Fish ", "Hey there! I am using ChatUp", "MOBILE"));
        mContactsModel.add(new ContactsModel(R.drawable.image12, "Gifty Lad ", "Hey there! I am using ChatUp", "MOBILE"));
        mContactsModel.add(new ContactsModel(R.drawable.user, "John Doe ", "Hmmm", "MOBILE"));
        mContactsModel.add(new ContactsModel(R.drawable.user, "Mom ", "Son, have you heard from your brother?", "MOBILE"));
        mContactsModel.add(new ContactsModel(R.drawable.image13, "My Bro ", "Hey there! I am using ChatUp", "MOBILE"));
        mContactsModel.add(new ContactsModel(R.drawable.image14, "Samuel Arthur ", "Off to work", "MOBILE"));
        mContactsModel.add(new ContactsModel(R.drawable.image15, "Sandy MacCathy ", "Hey there! I am using ChatUp", "MOBILE"));
        mContactsModel.add(new ContactsModel(R.drawable.image13, "Teacher", "Thank you lord", "MOBILE"));


        Collections.sort(mContactsModel, new Comparator<ContactsModel>() {
            @Override
            public int compare(ContactsModel lhs, ContactsModel rhs) {
                return lhs.getUsername().compareTo(rhs.getUsername());
            }
        });
        mContactsAdapter.notifyDataSetChanged();
    }
}

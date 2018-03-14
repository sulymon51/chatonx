package com.chatonx.chat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.chatonx.chat.apps.R;
import com.chatonx.chat.activities.NewBroadcastActivity;
import com.chatonx.chat.activities.NewGroupActivity;
import com.chatonx.chat.activities.SelectContactActivity;
import com.chatonx.chat.activities.SettingsActivity;
import com.chatonx.chat.activities.StarredMessagesActivity;
import com.chatonx.chat.adapters.ChatsAdapter;
import com.chatonx.chat.models.ChatsModel;

import java.util.ArrayList;
import java.util.List;

public class ChatsFragment extends Fragment {

    public RecyclerView mChatsList;
    public View mView;
    public List<ChatsModel> mChatsModel;
    public ChatsAdapter mChatsAdapter;

    public ChatsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_chats, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setShowHideAnimationEnabled(true);
        initUI();
        setHasOptionsMenu(true);
        return mView;
    }

    //Method to add menu items to the toolbar
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.chats_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.new_group) {
            Intent startIntent = new Intent(getActivity(), NewGroupActivity.class);
            this.startActivity(startIntent);
        }
        if (item.getItemId() == R.id.new_broadcast) {
            Intent startIntent = new Intent(getActivity(), NewBroadcastActivity.class);
            this.startActivity(startIntent);
        }
        if (item.getItemId() == R.id.web) {
            Intent startIntent = new Intent(getActivity(), SelectContactActivity.class);
            this.startActivity(startIntent);
        }
        if (item.getItemId() == R.id.starred) {
            Intent startIntent = new Intent(getActivity(), StarredMessagesActivity.class);
            this.startActivity(startIntent);
        }
        if (item.getItemId() == R.id.settings) {
            Intent startIntent = new Intent(getActivity(), SettingsActivity.class);
            this.startActivity(startIntent);
        }
        return true;
    }

    //Method and views initializer
    public void initUI() {

        mChatsList = (RecyclerView) mView.findViewById(R.id.chatsList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        mChatsModel = new ArrayList<ChatsModel>();
        mChatsAdapter = new ChatsAdapter(getActivity(), mChatsModel);
        mChatsList.setLayoutManager(layoutManager);
        mChatsList.setHasFixedSize(true);
        mChatsAdapter.notifyDataSetChanged();
        mChatsList.setAdapter(mChatsAdapter);
        prepareItems();
    }

    // Method to add dummy items to our recyclerview
    private void prepareItems() {
        mChatsModel.add( new ChatsModel(R.drawable.image1, "+233 54 406 9752 ", "17:20", "what do you thing of this template?", "2"));
        mChatsModel.add( new ChatsModel(R.drawable.image2, "Envato Community Group", "17:10", "John Doe: Hello", "99", 2));
        mChatsModel.add( new ChatsModel(R.drawable.image3, "+233 20 554 4257 ", "16:59", "Good Evening", "10"));
        mChatsModel.add( new ChatsModel(R.drawable.image4, "+233 23 123 2242 ", "16:30", "Hey Cuzzy... Is it still on?", "1"));
        mChatsModel.add( new ChatsModel(R.drawable.image5, "+233 27 039 5893 ", "12:05", "Pm", "1"));
        mChatsModel.add( new ChatsModel(R.drawable.image6, "Manuel Doe ", "YESTERDAY", "Hey! long time"));
        mChatsModel.add( new ChatsModel(R.drawable.image7, "John Doe ", "YESTERDAY", "Are you a Ghanaian?"));
        mChatsModel.add( new ChatsModel(R.drawable.image8, "Gifty Lad ", "YESTERDAY", "Where do you come from?"));
        mChatsModel.add( new ChatsModel(R.drawable.image9, "Samuel Arthur ", "YESTERDAY", "Off to work pal"));
        mChatsModel.add( new ChatsModel(R.drawable.image10, "Sandy MacCathy ", "YESTERDAY", "Hey Sweetheart! am missing u already"));
        mChatsModel.add( new ChatsModel(R.drawable.image11, "Fish ", "31/05/16", "Hmmm!"));
        mChatsModel.add( new ChatsModel(R.drawable.image12, "Mom ", "30/05/16", "Son, have you heard from your brother?"));
        mChatsModel.add( new ChatsModel(R.drawable.image13, "My Bro ", "30/05/16", "Yo Bro! is mom at home"));
        mChatsModel.add( new ChatsModel(R.drawable.image14, "Bestie ", "29/05/16", "Cuzzy are you coming to the beach?"));
        mChatsModel.add( new ChatsModel(R.drawable.image15, "Teacher", "29/05/16", "Did you come to school today?"));

    }

}

package com.epicodus.concertaid.ui;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.concertaid.Constants;
import com.epicodus.concertaid.R;
import com.epicodus.concertaid.adapters.FirebaseEventListAdapter;
import com.epicodus.concertaid.models.Event;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedEventListActivity extends AppCompatActivity {

    private Query mQuery;
    private Firebase mFirebaseEventsRef;
    private FirebaseEventListAdapter mAdapter;
    private SharedPreferences mSharedPreferences;


    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        ButterKnife.bind(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        mFirebaseEventsRef = new Firebase(Constants.FIREBASE_URL_EVENTS);

        setUpFirebaseQuery();
        setUpRecyclerView();
    }

    private void setUpFirebaseQuery() {
        String userUid = mSharedPreferences.getString(Constants.KEY_UID, null);
        String event = mFirebaseEventsRef.child(userUid).toString();
        mQuery = new Firebase(event);
    }

    private void setUpRecyclerView() {
        mAdapter = new FirebaseEventListAdapter(mQuery, Event.class);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }
}

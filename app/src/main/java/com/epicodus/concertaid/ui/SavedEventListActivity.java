package com.epicodus.concertaid.ui;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.epicodus.concertaid.Constants;
import com.epicodus.concertaid.R;
import com.epicodus.concertaid.adapters.FirebaseEventListAdapter;
import com.epicodus.concertaid.models.Event;
import com.epicodus.concertaid.util.OnStartDragListener;
import com.epicodus.concertaid.util.SimpleItemTouchHelperCallback;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedEventListActivity extends AppCompatActivity implements OnStartDragListener {

    private Query mQuery;
    private Firebase mFirebaseEventsRef;
    private FirebaseEventListAdapter mAdapter;
    private SharedPreferences mSharedPreferences;
    private ItemTouchHelper mItemTouchHelper;


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
        mQuery = new Firebase(event).orderByChild("index");
    }

    private void setUpRecyclerView() {
        mAdapter = new FirebaseEventListAdapter(mQuery, Event.class, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    protected void onPause() {
        super.onPause();
        String uid = mSharedPreferences.getString(Constants.KEY_UID, null);
        for (Event event : mAdapter.getItems()) {
            String pushID = event.getPushId();
            event.setIndex(Integer.toString(mAdapter.getItems().indexOf(event)));
            mFirebaseEventsRef.child(uid)
                    .child(pushID)
                    .setValue(event);
        }
    }
}

package com.epicodus.concertaid.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.MotionEventCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.concertaid.Constants;
import com.epicodus.concertaid.R;
import com.epicodus.concertaid.models.Event;
import com.epicodus.concertaid.util.FirebaseRecyclerAdapter;
import com.epicodus.concertaid.util.ItemTouchHelperAdapter;
import com.epicodus.concertaid.util.OnStartDragListener;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import java.util.Collections;

/**
 * Created by kylederrick on 5/6/16.
 */
public class FirebaseEventListAdapter extends FirebaseRecyclerAdapter<EventViewHolder, Event> implements ItemTouchHelperAdapter {

    private final OnStartDragListener mDragStartListener;
    private Context mContext;

    public FirebaseEventListAdapter(Query query, Class<Event> itemClass, OnStartDragListener dragStartListener) {
        super(query, itemClass);
        mDragStartListener = dragStartListener;
    }
    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.event_list_item_drag, parent, false);
        return new EventViewHolder(view, getItems());
    }

    @Override
    public void onBindViewHolder(final EventViewHolder holder, int position) {
        holder.bindEvent(getItem(position));
        holder.mEventImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(getItems(), fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String uid = sharedPreferences.getString(Constants.KEY_UID, null);
        Firebase ref = new Firebase(Constants.FIREBASE_URL_EVENTS).child(uid);
        String restaurantKey = getItem(position).getPushId();
        ref.child(restaurantKey).removeValue();
    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }

    @Override
    protected void itemAdded(Event item, String key, int position) {

    }

    @Override
    protected void itemChanged(Event oldItem, Event newItem, String key, int position) {

    }

    @Override
    protected void itemRemoved(Event item, String key, int position) {

    }

    @Override
    protected void itemMoved(Event item, String key, int oldPosition, int newPosition) {

    }
}

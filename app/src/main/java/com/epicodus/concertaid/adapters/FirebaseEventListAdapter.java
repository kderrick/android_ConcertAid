package com.epicodus.concertaid.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.concertaid.R;
import com.epicodus.concertaid.models.Event;
import com.epicodus.concertaid.util.FirebaseRecyclerAdapter;
import com.firebase.client.Query;

/**
 * Created by kylederrick on 5/6/16.
 */
public class FirebaseEventListAdapter extends FirebaseRecyclerAdapter<EventViewHolder, Event> {
    public FirebaseEventListAdapter(Query query, Class<Event> itemClass) {
        super(query, itemClass);
    }
    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list_item, parent, false);
        return new EventViewHolder(view, getItems());
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        holder.bindEvent(getItem(position));
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

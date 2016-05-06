package com.epicodus.concertaid.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.concertaid.R;
import com.epicodus.concertaid.models.Event;
import com.epicodus.concertaid.ui.EventDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


    public class EventViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.eventImageView) ImageView mEventImageView;
        @Bind(R.id.eventNameTextView) TextView mEventNameTextView;
        //        @Bind(R.id.detailsTextView) TextView mDetailsTextView;
        private Context mContext;
        private ArrayList<Event> mEvents = new ArrayList<>();

        public EventViewHolder(View itemView, ArrayList<Event> events) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
            mEvents = events;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();
                    Intent intent = new Intent(mContext, EventDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("events", Parcels.wrap(mEvents));
                    mContext.startActivity(intent);
                }
            });
        }
        public void bindEvent(Event event) {
            mEventNameTextView.setText(event.getEventTitle());
//          mDetailsTextView.setText(event.getEventTitle());
        }
    }
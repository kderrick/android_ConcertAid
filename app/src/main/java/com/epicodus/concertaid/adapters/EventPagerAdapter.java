package com.epicodus.concertaid.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.app.Fragment;

import com.epicodus.concertaid.models.Event;
import com.epicodus.concertaid.ui.EventDetailFragment;

import java.util.ArrayList;

/**
 * Created by kylederrick on 5/2/16.
 */
public class EventPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Event> mEvents;
    private String mSource;

    public EventPagerAdapter(FragmentManager fm, ArrayList<Event> events, String source) {
        super(fm);
        mEvents = events;
        mSource = source;
    }

    @Override
    public Fragment getItem(int position) {
        return EventDetailFragment.newInstance(mEvents.get(position), mSource);
    }

    @Override
    public int getCount() {
        return mEvents.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mEvents.get(position).getEventTitle();
    }

}

package com.epicodus.concertaid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.epicodus.concertaid.Constants;
import com.epicodus.concertaid.R;
import com.epicodus.concertaid.adapters.EventPagerAdapter;
import com.epicodus.concertaid.models.Event;
import com.epicodus.concertaid.util.ScaleAndFadePageTransformer;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EventDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private EventPagerAdapter adapterViewPager;
    ArrayList<Event> mEvents = new ArrayList<>();
    private String mSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);
        mEvents = Parcels.unwrap(getIntent().getParcelableExtra("events"));
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));
        Intent intent = getIntent();
        mSource = intent.getStringExtra(Constants.KEY_SOURCE);
        adapterViewPager = new EventPagerAdapter(getSupportFragmentManager(), mEvents, mSource);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
        mViewPager.setPageTransformer(true, new ScaleAndFadePageTransformer());

    }
}

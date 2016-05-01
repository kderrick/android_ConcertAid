package com.epicodus.concertaid.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.epicodus.concertaid.R;
import com.epicodus.concertaid.adapters.EventListAdapter;
import com.epicodus.concertaid.models.Event;
import com.epicodus.concertaid.services.BandsInTownService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DisplayListActivity extends AppCompatActivity {
    public ArrayList<Event> mEvents = new ArrayList<>();
    public static final String TAG = DisplayListActivity.class.getSimpleName();
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.tvDisplayZipCode) TextView mTVDisplayZipCode;
    private EventListAdapter mAdapter;
//    @Bind(R.id.lvDisplayConcerts) ListView mLVDisplayConcerts;
//    private String[] concerts = new String[] {"Concert1", "COncert2", "Concert3", "Concert4", "Concert5", "Conert6", "Concert7"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        ButterKnife.bind(this);



        Intent intent = getIntent();
        String userArtist = intent.getStringExtra("userArtist");
        String userCity = intent.getStringExtra("userCity");
        String userState = intent.getStringExtra("userState");
        getEvents(userArtist, userCity, userState);
    }

    private void getEvents(String userArtist, String userCity, String userState) {

        final BandsInTownService bandsInTownService = new BandsInTownService();

        bandsInTownService.findEvents(userArtist, userCity, userState, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {

                mEvents = bandsInTownService.processResults(response);

                DisplayListActivity.this.runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            mAdapter = new EventListAdapter(getApplicationContext(), mEvents);
                            mRecyclerView.setAdapter(mAdapter);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DisplayListActivity.this);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setHasFixedSize(true);


                    }
                });
            }
        });
    }

}
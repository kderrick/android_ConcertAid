package com.epicodus.concertaid;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DisplayActivity extends AppCompatActivity {
    public static final String TAG = DisplayActivity.class.getSimpleName();
    @Bind(R.id.tvDisplayZipCode) TextView mTVDisplayZipCode;
    @Bind(R.id.lvDisplayConcerts) ListView mLVDisplayConcerts;
    private String[] concerts = new String[] {"Concert1", "COncert2", "Concert3", "Concert4", "Concert5", "Conert6", "Concert7"};


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

//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, concerts);
//        mLVDisplayConcerts.setAdapter(adapter);
    }
    private void getEvents(String userArtist, String userCity, String userState) {
        BandsInTownService.findEvents(userArtist, userCity, userState, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        Log.v(TAG, jsonData);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
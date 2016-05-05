package com.epicodus.concertaid;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by kylederrick on 5/5/16.
 */
public class ConcertAidApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}

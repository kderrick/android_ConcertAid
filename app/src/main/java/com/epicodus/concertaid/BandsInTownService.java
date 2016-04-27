package com.epicodus.concertaid;

import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by kylederrick on 4/27/16.
 */
public class BandsInTownService {
    public static final String TAG = MainActivity.class.getSimpleName();

    public static void findEvents(String artist, String city, String state, Callback callback) {


        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BANDS_IN_TOWN_BASE_URL).newBuilder();
        urlBuilder.addPathSegment(artist + "/");
        urlBuilder.addPathSegment("events/recommended");
        urlBuilder.addQueryParameter("location", city + "," + state);
        urlBuilder.addQueryParameter("radius", "25");
        urlBuilder.addQueryParameter("app_id", Constants.BANDS_IN_TOWN_ID);
        urlBuilder.addQueryParameter("api_version", "2.0");
        urlBuilder.addQueryParameter("format", "json");

//        http://api.bandsintown.com/artists/M83/events/recommended?location=city,state&radius=25&app_id=kderrick&api_version=2.0&format=json

        String url = urlBuilder.build().toString();
        Log.d(TAG, url);
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }
}

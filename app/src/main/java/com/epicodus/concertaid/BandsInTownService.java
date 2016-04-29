package com.epicodus.concertaid;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by kylederrick on 4/27/16.
 */
public class BandsInTownService {
    public static final String TAG = MainActivity.class.getSimpleName();

    public static void findEvents(String artist, String city, String state, Callback callback) {


        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BANDS_IN_TOWN_BASE_URL).newBuilder();
        urlBuilder.addPathSegment(artist);
        urlBuilder.addPathSegment("events");
        urlBuilder.addPathSegment("recommended");
        urlBuilder.addQueryParameter("location", city + "," + state);
        urlBuilder.addQueryParameter("radius", "25");
        urlBuilder.addQueryParameter("app_id", Constants.BANDS_IN_TOWN_ID);
        urlBuilder.addQueryParameter("api_version", "2.0");
        urlBuilder.addQueryParameter("format", "json");

        String url = urlBuilder.build().toString();
//        Log.d(TAG, url);
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public ArrayList<Event> processResults(Response response) {
        ArrayList<Event> events = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject bandsInTownJSON = new JSONObject(jsonData);
//                Log.d(TAG, bandsInTownJSON.toString());
                JSONArray artistsJSON = bandsInTownJSON.getJSONArray("artists");
                for (int i = 0; i < artistsJSON.length(); i++) {
                    JSONObject eventJSON = artistsJSON.getJSONObject(i);
                    String name = eventJSON.getString("name");

                    Event event = new Event(name);
                    events.add(event);


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return events;
        }
    }

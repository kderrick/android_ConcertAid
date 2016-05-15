package com.epicodus.concertaid.services;

import com.epicodus.concertaid.Constants;
import com.epicodus.concertaid.ui.MainActivity;
import com.epicodus.concertaid.models.Event;

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
        urlBuilder.addQueryParameter("radius", "50");
        urlBuilder.addQueryParameter("app_id", Constants.BANDS_IN_TOWN_ID);
        urlBuilder.addQueryParameter("api_version", "2.0");
        urlBuilder.addQueryParameter("format", "json");

        String url = urlBuilder.build().toString();
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
//                { represents a json object node
//                [ represents a json array node
                JSONArray bandsInTownJSON = new JSONArray(jsonData);
                for (int i = 0; i < bandsInTownJSON.length(); i++) {
                    JSONObject eventJSON = bandsInTownJSON.getJSONObject(i);
                    String eventTitle = eventJSON.getString("title");
                    String formattedDateTime = eventJSON.getString("formatted_datetime");
                    String eventFacebookRSVP = eventJSON.getString("facebook_rsvp_url");
                    String eventCityState = eventJSON.getString("formatted_location");
                    String eventArtistName = eventJSON.getJSONArray("artists").getJSONObject(0).getString("name");
                    String eventArtistImageURL = eventJSON.getJSONArray("artists").getJSONObject(0).getString("thumb_url");

                    ArrayList<String> eventImageURLArray = new ArrayList<>();
                    JSONArray eventArtistsArray = eventJSON.getJSONArray("artists");
                    for (int y = 0; y < eventArtistsArray.length(); y++) {
                        JSONObject eventArtist = eventArtistsArray.getJSONObject(y);
                        eventImageURLArray.add(eventArtistsArray.get(y).toString());
                    }



                    Event event = new Event(eventTitle, eventFacebookRSVP, eventImageURLArray);
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

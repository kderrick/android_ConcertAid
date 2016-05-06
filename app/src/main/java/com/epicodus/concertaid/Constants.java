package com.epicodus.concertaid;

/**
 * Created by kylederrick on 4/27/16.
 */
public class Constants {

    public static final String BANDS_IN_TOWN_ID = BuildConfig.BANDS_IN_TOWN_ID;
    public static final String BANDS_IN_TOWN_BASE_URL = "http://api.bandsintown.com/artists/";
    public static final String BANDS_IN_TOWN_ARTIST_QUERY_PARAMETER = "artist";
    public static final String BANDS_IN_TOWN_CITY_QUERY_PARAMETER = "city";
    public static final String BANDS_IN_TOWN_STATE_QUERY_PARAMETER = "state";
    public static final String BANDS_IN_TOWN_FILLER1_QUERY_PARAMETER = "/events/recommended?location=";
    public static final String BANDS_IN_TOWN_FILLER2_QUERY_PARAMETER = ",";
    public static final String BANDS_IN_TOWN_FILLER3_QUERY_PARAMETER = "&radius=10&app_id=";
    public static final String BANDS_IN_TOWN_FILLER4_QUERY_PARAMETER = "&api_version=2.0&format=json";

    public static final String PREFERENCES_CITY_KEY = "city";
    public static final String PREFERENCES_STATE_KEY = "state";
    public static final String FIREBASE_URL = BuildConfig.FIREBASE_ROOT_URL;
    public static final String FIREBASE_LOCATION_EVENTS = "events";
    public static final String FIREBASE_URL_RESTAURANTS = FIREBASE_URL + "/" + FIREBASE_LOCATION_EVENTS;



}

//    public static final String exampleURL = "http://api.bandsintown.com/artists/explosions%20in%20the%20sky/events/recommended?location=portland,or&radius=25&app_id=YOUR_APP_ID&api_version=2.0&format=json"
//}

package com.epicodus.concertaid;

/**
 * Created by kylederrick on 4/27/16.
 */
public class Constants {

    public static final String BANDS_IN_TOWN_ID = BuildConfig.BANDS_IN_TOWN_ID;
    public static final String BANDS_IN_TOWN_BASE_URL = "http://api.bandsintown.com/artists/";

    public static final String PREFERENCES_CITY_KEY = "city";
    public static final String PREFERENCES_STATE_KEY = "state";
    public static final String FIREBASE_URL = BuildConfig.FIREBASE_ROOT_URL;
    public static final String FIREBASE_EVENTS = "events";
    public static final String FIREBASE_URL_EVENTS = FIREBASE_URL + "/" + FIREBASE_EVENTS;

    public static final String FIREBASE_PROPERTY_USERS = "users";
    public static final String FIREBASE_PROPERTY_EMAIL = "email";
    public static final String KEY_UID = "UID";
    public static final String FIREBASE_URL_USERS = FIREBASE_URL + "/" + FIREBASE_PROPERTY_USERS;
    public static final String KEY_USER_EMAIL = "email";



}

//    public static final String exampleURL = "http://api.bandsintown.com/artists/explosions%20in%20the%20sky/events/recommended?location=portland,or&radius=25&app_id=YOUR_APP_ID&api_version=2.0&format=json"
//}

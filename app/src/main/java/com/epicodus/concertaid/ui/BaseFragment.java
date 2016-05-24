package com.epicodus.concertaid.ui;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;

import com.epicodus.concertaid.Constants;
import com.firebase.client.Firebase;

/**
 * Created by kylederrick on 5/23/16.
 */
public class BaseFragment extends Fragment {
    public SharedPreferences mSharedPreferences;
    public  SharedPreferences.Editor mSharedPreferencesEditor;
    public Firebase mFirebaseRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mSharedPreferencesEditor = mSharedPreferences.edit();
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);
    }

    public void addToSharedPreferences(String city, String state) {
        mSharedPreferencesEditor.putString(Constants.PREFERENCES_CITY_KEY, city).apply();
        mSharedPreferencesEditor.putString(Constants.PREFERENCES_STATE_KEY, state).apply();

    }

    public void logout() {
        mFirebaseRef.unauth();
        Intent intent  = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

}

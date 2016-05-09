package com.epicodus.concertaid.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.concertaid.Constants;
import com.epicodus.concertaid.R;
import com.firebase.client.Firebase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.textViewWelcome) TextView mTextViewWelcome;
    @Bind(R.id.editTextCity) EditText mEditTextCity;
    @Bind(R.id.editTextState) EditText mEditTextState;
    @Bind(R.id.editTextArtist) EditText mEditTextArtist;
    @Bind(R.id.submitButton) Button mSubmitButton;
    @Bind(R.id.savedEventsButton) Button mSavedEventsButton;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Firebase mFirebaseRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        mSavedEventsButton.setOnClickListener(this);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        mTextViewWelcome.setText("Welcome");

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mSubmitButton.setOnClickListener(this);

        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);
    }

    @Override
    public void onClick(View v) {
        if (v == mSubmitButton) {
            String userArtist = mEditTextArtist.getText().toString();
            String userCity = mEditTextCity.getText().toString();
            String userState = mEditTextState.getText().toString();
            if(!(userCity).equals("") && !(userState).equals("")) {
                addToSharedPreferences(userCity, userState);
            }

            Intent intent = new Intent(WelcomeActivity.this, DisplayListActivity.class);
            intent.putExtra("userArtist", userArtist);
            startActivity(intent);
        }

        if (v == mSavedEventsButton) {
            Intent intent = new Intent(WelcomeActivity.this, SavedEventListActivity.class);
            startActivity(intent);
        }
    }
    private void addToSharedPreferences(String userCity, String userState) {
        mEditor.putString(Constants.PREFERENCES_CITY_KEY, userCity).apply();
        mEditor.putString(Constants.PREFERENCES_STATE_KEY, userState).apply();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    protected void logout() {
        mFirebaseRef.unauth();
        takeUserToLoginScreenOnUnAuth();
    }
    private void takeUserToLoginScreenOnUnAuth() {
        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}

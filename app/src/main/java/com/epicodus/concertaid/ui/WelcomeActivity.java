package com.epicodus.concertaid.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.concertaid.Constants;
import com.epicodus.concertaid.R;
import com.epicodus.concertaid.models.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = WelcomeActivity.class.getSimpleName();
    @Bind(R.id.textViewWelcome) TextView mTextViewWelcome;
    @Bind(R.id.editTextCity) EditText mEditTextCity;
//    @Bind(R.id.editTextState) EditText mEditTextState;
    @Bind(R.id.editTextArtist) EditText mEditTextArtist;
    @Bind(R.id.submitButton) Button mSubmitButton;
    @Bind(R.id.savedEventsButton) Button mSavedEventsButton;
    @Bind(R.id.states_spinner) Spinner mStatesSpinner;


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Firebase mFirebaseRef;
    private ValueEventListener mUserRefListener;
    private Firebase mUserRef;
    private String mUId;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        mSavedEventsButton.setOnClickListener(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUId = mSharedPreferences.getString(Constants.KEY_UID, null);
        mUserRef = new Firebase(Constants.FIREBASE_URL_USERS).child(mUId);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.states_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // Apply the adapter to the spinner
        mStatesSpinner.setAdapter(adapter);

        mUserRefListener = mUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                mTextViewWelcome.setText("Welcome, " + user.getName());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d(TAG, "Read failed");
            }
        });

        Intent intent = getIntent();
//        String userName = intent.getStringExtra("userName");
        mTextViewWelcome.setText("Welcome");

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mSubmitButton.setOnClickListener(this);

        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/MUSICNET.ttf");
        mTextViewWelcome.setTypeface(tf);
    }

    @Override
    public void onClick(View v) {
        if (v == mSubmitButton) {
            String userArtist = mEditTextArtist.getText().toString();


            String userCity = mEditTextCity.getText().toString();
            String userState = mStatesSpinner.getSelectedItem().toString();
            if(!(userCity).equals("") && !(userState).equals("")) {
                addToSharedPreferences(userCity, userState);
            }

            Intent intent = new Intent(WelcomeActivity.this, DisplayListActivity.class);
            if(userArtist.length() == 0) {
                Toast.makeText(WelcomeActivity.this, "Please Enter An Artist", Toast.LENGTH_SHORT).show();
            } else {
                intent.putExtra("userArtist", userArtist);
                startActivity(intent);

            }
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

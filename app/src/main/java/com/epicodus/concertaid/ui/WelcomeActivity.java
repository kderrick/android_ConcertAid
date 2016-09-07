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
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public class WelcomeActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = WelcomeActivity.class.getSimpleName();
    //BIND VIEWS
    @Bind(R.id.textViewWelcome) TextView mTextViewWelcome;
    @Bind(R.id.editTextCity) EditText mEditTextCity;
    @Bind(R.id.editTextArtist) EditText mEditTextArtist;
    @Bind(R.id.submitButton) Button mSubmitButton;
    @Bind(R.id.savedEventsButton) Button mSavedEventsButton;
    @Bind(R.id.states_spinner) Spinner mStatesSpinner;
    @Bind(R.id.relativeLayout) RelativeLayout mRelativeLayout;
    @Bind(R.id.imageView) ImageView mImageView;

    //INITIALIZE FIELDS
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

        //SET ONCLICKLISTENERS
        mSavedEventsButton.setOnClickListener(this);
        mSubmitButton.setOnClickListener(this);
        mRelativeLayout.setOnClickListener(this);
        mTextViewWelcome.setOnClickListener(this);
        mImageView.setOnClickListener(this);

        //GET SHARED PREFERENCES AND SHARED PREF EDITOR
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        //GET USER ID FROM SHARED PREFS
        mUId = mSharedPreferences.getString(Constants.KEY_UID, null);

        //GET USER REFERENCE FROM FIREBASE
        mUserRef = new Firebase(Constants.FIREBASE_URL_USERS).child(mUId);

        //GET REFERENCE TO FIREBASE
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.states_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // Apply the adapter to the spinner
        mStatesSpinner.setAdapter(adapter);

        //SETS FONT
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/MUSICNET.ttf");
        mTextViewWelcome.setTypeface(tf);


        // Attach an listener to read the data at our user reference
        mUserRefListener = mUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
//              SETS TEXT ON WELCOMEACTIVITY TO USER'S NAME
                mTextViewWelcome.setText("Welcome, " + user.getName());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d(TAG, "Read failed");
            }
        });
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

            //DOESNT HIDE KEYBOARD YET
            if((v == mTextViewWelcome) || (v == mRelativeLayout) || (v == mImageView)) {
                hideSoftKeyboard(this);
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

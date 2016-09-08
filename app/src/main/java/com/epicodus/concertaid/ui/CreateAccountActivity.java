package com.epicodus.concertaid.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.epicodus.concertaid.Constants;
import com.epicodus.concertaid.R;
import com.epicodus.concertaid.models.User;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateAccountActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = CreateAccountActivity.class.getSimpleName();

    //BIND VIEWS
    @Bind(R.id.createUserButton) Button mCreateUserButton;
    @Bind(R.id.nameEditText) EditText mNameEditText;
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.confirmPasswordEditText) EditText mConfirmPasswordEditText;
    @Bind(R.id.loginTextView) TextView mLoginTextView;
    @Bind(R.id.signUpTextView) TextView mSignUpTextView;
    @Bind(R.id.linearLayout) LinearLayout mLinearLayout;

    //INITIALIZE FIELDS
    private Firebase mFirebaseRef;
    private SharedPreferences.Editor mSharedPreferencesEditor;
    private SharedPreferences mSharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);
        //CREATE FIREBASE REFERENCE
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);

//      SET ONCLICK LISTENERS
        mCreateUserButton.setOnClickListener(this);
        mLoginTextView.setOnClickListener(this);
        mLinearLayout.setOnClickListener(this);
        mSignUpTextView.setOnClickListener(this);

        //SET SHARED PREFERENCES
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mSharedPreferencesEditor = mSharedPreferences.edit();

//      SET FONT
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/MUSICNET.ttf");
        mSignUpTextView.setTypeface(tf);
    }

    @Override
    public void onClick(View view) {
        if (view == mCreateUserButton) {
            createNewUser();
        }
        if (view == mLoginTextView) {
            Intent loginIntent = new Intent(CreateAccountActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
            finish();
        }
        if ((view == mSignUpTextView) || (view == mLinearLayout)) {
            hideSoftKeyboard(this);
        }
    }
    public void createNewUser() {
        final String name = mNameEditText.getText().toString();
        final String email = mEmailEditText.getText().toString();
        final String password = mPasswordEditText.getText().toString();
        final String confirmPassword = mConfirmPasswordEditText.getText().toString();

        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(name);
        boolean validPassword = isValidPassword(password, confirmPassword);
        if (!validEmail || !validName || !validPassword) return;

        mFirebaseRef.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                String uid = result.get("uid").toString();
                createUserInFirebaseHelper(name, email, uid);
                mFirebaseRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        if (authData != null) {
                            String userUid = authData.getUid();
                            String userInfo = authData.toString();
                            Log.d(TAG, "Currently logged in: " + userInfo);
                            mSharedPreferencesEditor.putString(Constants.KEY_UID, userUid).apply();
                            Intent intent = new Intent(CreateAccountActivity.this, WelcomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }
                    }
                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        switch (firebaseError.getCode()) {
                            case FirebaseError.INVALID_EMAIL:
                            case FirebaseError.USER_DOES_NOT_EXIST:
                                mEmailEditText.setError("Please check that you entered your email correctly");
                                break;
                            case FirebaseError.INVALID_PASSWORD:
                                mEmailEditText.setError(firebaseError.getMessage());
                                break;
                            case FirebaseError.NETWORK_ERROR:
                                showErrorToast("There was a problem with the network connection");
                                break;
                            default:
                                showErrorToast(firebaseError.toString());
                        }
                    }
                });
            }


            @Override
            public void onError(FirebaseError firebaseError) {
                Log.d(TAG, "error occurred " +
                        firebaseError);
            }
        });
    }


    private void createUserInFirebaseHelper(final String name, final String email, final String uid) {
        final Firebase userLocation = new Firebase(Constants.FIREBASE_URL_USERS).child(uid);
        User newUser = new User(name, email);
        userLocation.setValue(newUser);
    }

    //VERIFIES EMAIL ENTERED MATCHES STANDARD EMAIL @ PATTERN
    private boolean isValidEmail(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mEmailEditText.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    //CHECKS NAME FIELD IS NOT SUBMITTED BLANK
    private boolean isValidName(String name) {
        if (name.equals("")) {
            mNameEditText.setError("Please enter your name");
            return false;
        }
        return true;
    }

    //    CHECK IF PASSWORD IS AT LEAST 8 characters with an upper, lower, number and special and matches confirm password
    private boolean isValidPassword(String password, String confirmPassword) {
        if ((password.length() < 8) || (password.equals(password.toLowerCase()) || (password.equals(password.toUpperCase())) || (password.matches("[A-Za-z0-9 ]*")) || (!password.matches(".*\\d+.*")))){
            mPasswordEditText.setError("Please create a password containing at least 8 characters total, at least 1 uppercase character, at least 1 lowercase character and at least 1 special character");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mPasswordEditText.setError("Passwords do not match");
            return false;
        }
        return true;
    }
}

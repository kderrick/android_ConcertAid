package com.epicodus.concertaid.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.epicodus.concertaid.Constants;
import com.epicodus.concertaid.R;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    public static final String TAG = LoginActivity.class.getSimpleName();

    //BIND VIEWS
    @Bind(R.id.registerTextView) TextView mRegisterTextView;
    @Bind(R.id.passwordLoginButton) Button mPasswordLoginButton;
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.linearLayout) LinearLayout mLinearLayout;
    @Bind(R.id.signUpTextView) TextView mLoginTextView;

//  INITIALIZE FIELDS
    private Firebase mFirebaseRef;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mSharedPreferencesEditor;
    private ProgressDialog mAuthProgressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //SET CLICK LISTENERS
        mRegisterTextView.setOnClickListener(this);
        mPasswordLoginButton.setOnClickListener(this);
        mLinearLayout.setOnClickListener(this);
        mLoginTextView.setOnClickListener(this);


        //SET SHARED PREFERENCES MANAGER AND EDITOR
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        mSharedPreferencesEditor = mSharedPreferences.edit();

        //SET FIREBASE REFERENCE
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);


        //SET DIALOG FOR AUTHENTICATION
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Database...");
        mAuthProgressDialog.setCancelable(false);


        //
        String signupEmail = mSharedPreferences.getString(Constants.KEY_USER_EMAIL, null);
        if (signupEmail != null) {
            mEmailEditText.setText(signupEmail);
        }

        //SETS FONT LOGIN TITLE
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/MUSICNET.ttf");
        mLoginTextView.setTypeface(tf);


    }

    @Override
    public void onClick(View view) {
        if (view == mPasswordLoginButton) {
            loginWithPassword();
        }
        if (view == mRegisterTextView) {
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent);
            finish();
        }
        //HIDES SOFTKEYBOARD IF CLICKED OUTSIDE EDIT TEXT
        if (view == mLinearLayout || (view == mLoginTextView)) {
            hideSoftKeyboard(this);
        }
    }

    public void loginWithPassword() {
        final String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        if (email.equals("")) {
            mEmailEditText.setError("Please enter your email");
        }
        if (password.equals("")) {
            mPasswordEditText.setError("Password cannot be blank");
        }

        mAuthProgressDialog.show();

        mFirebaseRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                mSharedPreferencesEditor.putString(Constants.KEY_USER_EMAIL, email).apply();
                mAuthProgressDialog.dismiss();
                if (authData != null) {
                    String userUid = authData.getUid();
                    mSharedPreferencesEditor.putString(Constants.KEY_UID, userUid).apply();
                    Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                mAuthProgressDialog.dismiss();
                switch (firebaseError.getCode()) {
                    case FirebaseError.INVALID_EMAIL:
                    case FirebaseError.USER_DOES_NOT_EXIST:
                        mEmailEditText.setError("Please check that you entered your email correctly");
                        break;
                    case FirebaseError.INVALID_PASSWORD:
                        mPasswordEditText.setError(firebaseError.getMessage());
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

}

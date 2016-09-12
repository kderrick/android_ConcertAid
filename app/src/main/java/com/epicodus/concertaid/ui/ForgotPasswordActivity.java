package com.epicodus.concertaid.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.concertaid.Constants;
import com.epicodus.concertaid.R;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ForgotPasswordActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordResetButton) Button mPasswordResetButton;

    //INITIALIZE FIELDS
    private Firebase mFirebaseRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);

        //SET FIREBASE REFERENCE
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);

        //SET ONCLICK LISTENERS
        mPasswordResetButton.setOnClickListener(this);


    }

    public void forgotPassword(String userEmail) {
        Firebase.ResultHandler handler = new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(FirebaseError firebaseError) {

            }

        };
        mFirebaseRef.resetPassword(userEmail, handler );
    }


    @Override
    public void onClick(View view) {
        if(view == mPasswordResetButton ) {
            forgotPassword(mEmailEditText.getText().toString());
        }
    }
}

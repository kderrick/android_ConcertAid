package com.epicodus.concertaid.ui;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.concertaid.Constants;
import com.epicodus.concertaid.R;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.summaryTextView) TextView mSummaryTextView;
    @Bind(R.id.deleteAccountButton) Button mDeleteAccountButton;
    @Bind(R.id.userEmailEditText) EditText mUserEmailEditText;
    @Bind(R.id.userPasswordEditText) EditText mUserPasswordEditText;

    private Firebase mFirebaseRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);
        mDeleteAccountButton.setOnClickListener(this);


        //GET REFERENCE TO FIREBASE
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);

        //SETS FONT FOR TITLE
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/MUSICNET.ttf");
        mSummaryTextView.setTypeface(tf);

    }

    @Override
    public void onClick(View view) {
        if(view == mDeleteAccountButton) {
            AlertDialog.Builder builder = new AlertDialog.Builder(UserProfileActivity.this);
            LayoutInflater inflater = UserProfileActivity.this.getLayoutInflater();
            builder.setView(inflater.inflate(R.layout.delete_user_dialog, null));

            builder.setMessage(R.string.dialog_message)
                    .setTitle(R.string.dialog_title);
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });
            builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    String userEmail = mUserEmailEditText.getText().toString();
                    String userPassword = mUserPasswordEditText.getText().toString();
                    mFirebaseRef.removeUser(userEmail, userPassword, );
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void deleteUser(String userEmail, String userPassword) {
        Firebase.ResultHandler handler = new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
//                NEED TO MAKE SURE GOES BACK TO LOGIN
                Toast.makeText(UserProfileActivity.this, "User deleted Successflly", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Toast.makeText(UserProfileActivity.this, "There was an error, please try again", Toast.LENGTH_LONG).show();
            }

        };
        mFirebaseRef.removeUser(userEmail, userPassword, handler );
    }
}

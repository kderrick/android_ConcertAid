package com.epicodus.concertaid.ui;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.concertaid.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.summaryTextView) TextView mSummaryTextView;
    @Bind(R.id.deleteAccountButton) Button mDeleteAccountButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);
        mDeleteAccountButton.setOnClickListener(this);

        //SETS FONT FOR TITLE
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/MUSICNET.ttf");
        mSummaryTextView.setTypeface(tf);

    }

    @Override
    public void onClick(View view) {
        if(view == mDeleteAccountButton) {
            AlertDialog.Builder builder = new AlertDialog.Builder(UserProfileActivity.this);
            builder.setMessage(R.string.dialog_message)
                    .setTitle(R.string.dialog_title);
            builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });
            AlertDialog dialog = builder.create();
            System.out.println("CLICKED IT");
        }
    }
}

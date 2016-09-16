package com.epicodus.concertaid.ui;

import android.graphics.Typeface;
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

        //SETS FONT FOR TITLE
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/MUSICNET.ttf");
        mSummaryTextView.setTypeface(tf);
    }

    @Override
    public void onClick(View view) {
        if(view == mDeleteAccountButton) {

        }
    }
}

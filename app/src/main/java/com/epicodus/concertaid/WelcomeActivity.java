package com.epicodus.concertaid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.tvWelcome) TextView mTVWelcome;
    @Bind(R.id.etZipCode) EditText mEDZipCode;
    @Bind(R.id.submitButton) Button mSubmitZipButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        mTVWelcome.setText("Welcome " + userName);

        mSubmitZipButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mSubmitButton) {
            String userZip = mEDZipCode.getText().toString();

            Intent intent = new Intent(WelcomeActivity.this, DisplayActivity.class);
            intent.putExtra("userZip", userZip);
            startActivity(intent);
        }
    }
}

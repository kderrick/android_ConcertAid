package com.epicodus.concertaid.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.concertaid.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.textViewWelcome) TextView mTextViewWelcome;
    @Bind(R.id.editTextCity) EditText mEditTextCity;
    @Bind(R.id.editTextState) EditText mEditTextState;
    @Bind(R.id.editTextArtist) EditText mEditTextArtist;
    @Bind(R.id.submitButton) Button mSubmitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        mTextViewWelcome.setText("Welcome " + userName);

        mSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mSubmitButton) {
            String userArtist = mEditTextArtist.getText().toString();
            String userCity = mEditTextCity.getText().toString();
            String userState = mEditTextState.getText().toString();

            Intent intent = new Intent(WelcomeActivity.this, DisplayActivity.class);
            intent.putExtra("userArtist", userArtist);
            intent.putExtra("userCity", userCity);
            intent.putExtra("userState", userState);
            startActivity(intent);
        }
    }
}

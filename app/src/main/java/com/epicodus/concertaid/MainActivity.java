package com.epicodus.concertaid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.submitLoginButton) Button mSubmitLoginButton;
    @Bind(R.id.editTextUsername) EditText mEditTextUsername;
    @Bind(R.id.editTextPassword) EditText mEditTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSubmitLoginButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == mSubmitLoginButton) {
            String userName = mEditTextUsername.getText().toString();
            String userPassword = mEditTextPassword.getText().toString();

            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            intent.putExtra("userName", userName);
            startActivity(intent);
        }
    }
}

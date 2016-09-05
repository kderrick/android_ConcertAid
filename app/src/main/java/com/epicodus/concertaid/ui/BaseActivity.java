package com.epicodus.concertaid.ui;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {

    public  void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void showErrorToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}


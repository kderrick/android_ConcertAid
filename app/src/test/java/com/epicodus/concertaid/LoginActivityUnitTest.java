package com.epicodus.concertaid;

import android.os.Build;
import android.widget.TextView;

import com.epicodus.concertaid.ui.LoginActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertTrue;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)



public class LoginActivityUnitTest {

    private LoginActivity activity;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(LoginActivity.class);
    }

    @Test
    public void validateTextViewContent() {
        TextView appNameTextView = (TextView) activity.findViewById(R.id.textView4);
        assertTrue("Login".equals(appNameTextView.getText().toString()));
    }


}

package com.epicodus.concertaid;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.concertaid.ui.CreateAccountActivity;
import com.epicodus.concertaid.ui.LoginActivity;
import com.epicodus.concertaid.ui.WelcomeActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.robolectric.Shadows.shadowOf;

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
        TextView textView4 = (TextView) activity.findViewById(R.id.signUpTextView);
        assertTrue("Login".equals(textView4.getText().toString()));
    }

    @Test
    public void validateRegisterTextViewGoesToCreateAccountActivity() {
        TextView registerTextView = (TextView) activity.findViewById(R.id.registerTextView);
        registerTextView.performClick();
        ShadowActivity shadowActivity = shadowOf(activity);
        Intent createAccountIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(createAccountIntent);
        assertThat(shadowIntent.getComponent().getClassName(), equalTo(CreateAccountActivity.class.getName()));
    }

    @Test
    public void validatePasswordLoginButtonGoesToWelcomeActivity() {
        Button passwordLoginButton = (Button) activity.findViewById(R.id.passwordLoginButton);
        EditText emailEditText = (EditText) activity.findViewById(R.id.emailEditText);
        EditText passwordEditText = (EditText) activity.findViewById(R.id.passwordEditText);
        emailEditText.setText("boom@boom.com");
        passwordEditText.setText("boom");


        passwordLoginButton.performClick();
//        ShadowActivity shadowActivity = shadowOf(activity);
        Intent welcomeActivityIntent = new Intent(activity, WelcomeActivity.class);
        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(welcomeActivityIntent));



//        System.out.println(welcomeActivityIntent + "THIS IS THE OUTPUT PRINTING");
//        ShadowIntent shadowIntent = shadowOf(welcomeActivityIntent);
//        assertThat(shadowIntent.getComponent().getClassName(), equalTo(WelcomeActivity.class.getName()));
    }

}

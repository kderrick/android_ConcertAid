package com.epicodus.concertaid;


import android.support.test.rule.ActivityTestRule;

import com.epicodus.concertaid.ui.MainActivity;

import org.junit.Rule;

public class MainActivityInstrumentationTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);
}
